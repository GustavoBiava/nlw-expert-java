package com.rocketseat.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

	@PostMapping("/verifyIfHasCertification")
	public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyCertificationDTO) {
		var result = verifyIfHasCertificationUseCase.execute(verifyCertificationDTO);
		if(result) {
			return "Estudante já fez a prova";
		}
		return "Estudante pode fazer a prova";
	}

}
