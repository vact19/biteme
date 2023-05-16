package site.biteme.biteme.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.question.QuestionRepository;
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
    private final QuestionRepository questionRepository;

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

        Question question = Question.builder()
                .title("title")
                .category(Category.AI)
                .content("content")
                .imageUrls(null)
                .student(student)
                .build();
        questionRepository.save(question);
    }
}
