package site.biteme.biteme.api.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.student.Student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddQuestionDto {
    @Getter
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String title;
        @NotNull
        private Category category;
        @NotBlank
        private String content;
        List<MultipartFile> imageFiles;

        // State는 In progress로 고정, Student
        public Question toEntity(Student student) {
            return Question.builder()
                    .title(title)
                    .category(category)
                    .content(content)
                    .imageUrls(null) // 제외하기보다는 url이 없다는 것을 명시
                    .ownerStudent(student)
                    .build();
        }
    }
}
