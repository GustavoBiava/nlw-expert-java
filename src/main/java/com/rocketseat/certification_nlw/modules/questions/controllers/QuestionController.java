package com.rocketseat.certification_nlw.modules.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import com.rocketseat.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.rocketseat.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = questionRepository.findByTechnology(technology);
        var toMap = result.stream().map(question -> mapQuestionToDTO(question)).toList();
        
        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity questionEntity) {
        var questionResultDTO = QuestionResultDTO.builder().id(questionEntity.getId()).description(questionEntity.getDescription()).technology(questionEntity.getTechnology()).build();
        List<AlternativesResultDTO> alternativesResultDTOs = questionEntity.getAlternatives().stream().map(alternative -> mapAlternativeToDTO(alternative)).toList();
        questionResultDTO.setAlternatives(alternativesResultDTOs);

        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeToDTO(AlternativesEntity alternativeEntity) {
        return AlternativesResultDTO.builder().id(alternativeEntity.getId()).description(alternativeEntity.getDescription()).build();
    }
}
