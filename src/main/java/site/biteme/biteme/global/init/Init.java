package site.biteme.biteme.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import site.biteme.biteme.domain.student.Department;
import site.biteme.biteme.domain.student.Major;
import site.biteme.biteme.domain.student.Rank;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentRepository;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class Init {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Student student = Student.builder()
                .email("ex@email.com")
                .name("name")
                .password(passwordEncoder.encode("pppp"))
                .major(Major.AI)
                .department(Department.IT)
                .rank(Rank.FRESHMAN)
                .build();
        studentRepository.save(student);

    }
}
