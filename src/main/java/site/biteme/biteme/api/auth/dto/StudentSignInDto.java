package site.biteme.biteme.api.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class StudentSignInDto {
    @NoArgsConstructor
    @Getter
    public static class Request {
        @Email(message = "Email 형식이어야 합니다")
        private String email;
        @Length(min = 4, max = 20, message = "비밀번호는 4~20자 사이여야 합니다")
        private String password;
    }
}
