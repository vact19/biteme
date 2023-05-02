package site.biteme.biteme.api.student.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;
import site.biteme.biteme.domain.common.Department;
import site.biteme.biteme.domain.common.Major;
import site.biteme.biteme.domain.common.Rank;
import site.biteme.biteme.domain.student.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class StudentSignUpDto {

    @Getter
    @NoArgsConstructor
    public static class Request {
        @Length(min = 2, max = 10, message = "이름은 2~10자 사이여야 합니다")
        private String name;
        @Email(message = "Email 형식이어야 합니다")
        private String email;
        @Length(min = 4, max = 20, message = "비밀번호는 4~20자 사이여야 합니다")
        private String password;
        @NotNull
        private Major major;
        @NotNull
        private Department department;

        public Student toEntity(PasswordEncoder passwordEncoder){
            String encodedPassword = passwordEncoder.encode(this.password);
            return Student.builder()
                    .name(name)
                    .email(email)
                    .password(encodedPassword)
                    .major(major)
                    .department(department)
                    .rank(Rank.FRESHMAN)
                    .build();
        }
    }


}
