package site.biteme.biteme.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.biteme.biteme.domain.answer.Answer;
import site.biteme.biteme.domain.answer.AnswerRepository;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.common.PointAmount;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.question.QuestionRepository;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentRepository;
import site.biteme.biteme.domain.student.component.Department;
import site.biteme.biteme.domain.student.component.Major;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class Init {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    @PostConstruct
    public void init() {
        Student student = Student.builder()
                .email("ex@email.com")
                .name("name")
                .password(passwordEncoder.encode("pppp"))
                .major(Major.COMPUTER)
                .department(Department.IT)
                .build();
        student.getPointStatus().increasePoint(PointAmount.ANSWER_ACCEPTED);
        student.getPointStatus().increasePoint(PointAmount.ANSWER_ACCEPTED);
        student.getPointStatus().increasePoint(PointAmount.ANSWER_ACCEPTED);
        studentRepository.save(student);


        Question question = Question.builder()
                .title("title")
                .category(Category.AI)
                .content("content")
                .imageUrls(null)
                .ownerStudent(student)
                .build();
        questionRepository.save(question);

        Answer answer = Answer.builder()
                .content("content")
                .question(question)
                .ownerStudent(student)
                .build();
        answerRepository.save(answer);




    }


}
