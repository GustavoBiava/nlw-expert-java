package com.rocketseat.certification_nlw.modules.students.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswersDTO {

    private UUID questionId;
    private UUID alternativeId;
    private Boolean isCorrect;
}
