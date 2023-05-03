package site.biteme.biteme.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import site.biteme.biteme.domain.common.Department;
import site.biteme.biteme.domain.common.Major;
import site.biteme.biteme.domain.common.Rank;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentService;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class Init {
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Student student = Student.builder()
                .email("ex@email.com")
                .name("name")
                .password(passwordEncoder.encode("password"))
                .major(Major.AI)
                .department(Department.IT)
                .rank(Rank.FRESHMAN)
                .build();
        studentService.signUp(student);
    }
}
