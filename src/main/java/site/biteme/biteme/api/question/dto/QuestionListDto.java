package site.biteme.biteme.api.question.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import site.biteme.biteme.api.student.dto.StudentIdAndNameDto;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.question.component.QuestionState;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionListDto {
    @Getter
    @Builder
    public static class Response {
        private QuestionDto question;
        private StudentIdAndNameDto student;

        public static List<Response> of(List<Question> questions) {
            return questions.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }

        public static Response of(Question question) {
            QuestionDto questionDto = QuestionDto.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .state(question.getQuestionState())
                    .category(question.getCategory())
                    .build();
            return Response.builder()
                    .question(questionDto)
                    .student(StudentIdAndNameDto.of(question.getOwnerStudent()))
                    .build();
        }

        @Getter
        @Builder(access = AccessLevel.PRIVATE)
        static class QuestionDto{
            private Long id;
            private String title;
            private QuestionState state;
            private Category category;
        }
    }
}
