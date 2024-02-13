package com.rocketseat.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.rocketseat.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.rocketseat.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = verifyIfHasCertificationUseCase
                .execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if (hasCertification) {
            throw new Exception("Você já possui essa certificação!");
        }

        var questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertification = new ArrayList<>();
        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers().stream().forEach(qa -> {
            QuestionEntity question = questionsEntity.stream().filter(qe -> qe.getId().equals(qa.getQuestionId()))
                    .findFirst().get();

            var correctAlternative = question.getAlternatives().stream()
                    .filter(alternative -> alternative.getIsCorrect()).findFirst().get();

            if (correctAlternative.getId().equals(qa.getAlternativeId())) {
                qa.setIsCorrect(true);
                correctAnswers.incrementAndGet();
            } else {
                qa.setIsCorrect(false);
            }

            var answersCertificationEntity = AnswersCertificationsEntity.builder().answerId(qa.getAlternativeId())
                    .questionId(qa.getQuestionId()).isCorrect(qa.getIsCorrect()).build();
            answersCertification.add(answersCertificationEntity);
        });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;

        if (student.isEmpty()) {
            var newStudent = StudentEntity.builder().email(dto.getEmail()).build();
            studentRepository.save(newStudent);
            studentId = newStudent.getId();
        } else {
            studentId = student.get().getId();
        }

        CertificationStudentEntity certification = CertificationStudentEntity.builder().studentId(studentId)
                .technology(dto.getTechnology()).grade(correctAnswers.get()).build();

        var savedCertification = certificationStudentRepository.save(certification);

        answersCertification.stream().forEach(answerCertification -> {
            answerCertification.setCertificationId(certification.getId());
            answerCertification.setCertificationStudentEntity(certification);
        });

        certification.setAnswersCertificationsEntity(answersCertification);

        certificationStudentRepository.save(certification);

        return savedCertification;
    }

}
