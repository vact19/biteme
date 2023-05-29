package site.biteme.biteme.api.answer.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.answer.Answer;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.student.Student;

import javax.validation.constraints.NotBlank;

public class AddAnswerDto {
    @Getter
    @NoArgsConstructor
    public static class Request{
        @NotBlank
        private String content;

        public Answer toEntity(Student student, Question question){
            return Answer.builder()
                    .content(content)
                    .question(question)
                    .ownerStudent(student)
                    .build();
        }
    }
}
