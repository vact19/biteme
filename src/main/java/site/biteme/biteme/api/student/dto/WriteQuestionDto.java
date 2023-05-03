package site.biteme.biteme.api.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import site.biteme.biteme.domain.common.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class WriteQuestionDto {
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
    }
}
