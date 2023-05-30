package site.biteme.biteme.api.question.dto;

import lombok.Builder;
import lombok.Getter;
import site.biteme.biteme.api.student.dto.StudentIdAndNameDto;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.question.component.QuestionState;

import java.util.List;

public class QuestionListDto {
    @Getter
    @Builder
    public static class Response {
        private Long id;
        private String title;
        private QuestionState state;
        private Category category;
        private StudentIdAndNameDto student;

        public static List<Response> of(List<Question> questions) {
            return questions.stream()
                    .map(Response::of)
                    .collect(java.util.stream.Collectors.toList());
        }

        public static Response of(Question question) {
            return Response.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .state(question.getQuestionState())
                    .category(question.getCategory())
                    .student(StudentIdAndNameDto.of(question.getOwnerStudent()))
                    .build();
        }
    }
}
