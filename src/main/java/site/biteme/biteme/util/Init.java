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
import java.util.ArrayList;
import java.util.List;

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
        /**
         *  Question 1은 답변 2개, Question 2는 답변 0개
         */
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


        Student student2 = Student.builder()
                .email("ex2@email.com")
                .name("name2")
                .password(passwordEncoder.encode("pppp"))
                .major(Major.BUSINESS)
                .department(Department.MEDIA)
                .build();
        studentRepository.save(student2);

        List<String> strings = new ArrayList<>();
        strings.add("6fee38a0-fc8c-4a94-8f2f-d1bb3a99548e.png");
        strings.add("3b1e58ae-ef9c-4049-a34a-130f428f6877.png");
        strings.add("d1dd09b0-a61a-4f25-b230-38eac79c4a46.jpg");

        Question question = Question.builder()
                .title("김효중너무멋있다.")
                .category(Category.AI)
                .content("김효중이왜멋있는지에대한설명")
                .imageUrls(strings)
                .ownerStudent(student)
                .build();
        questionRepository.save(question);

        Question question2 = Question.builder()
                .title("김효중★이세돌")
                .category(Category.AI)
                .content("국가적 손실")
                .imageUrls(strings)
                .ownerStudent(student2)
                .build();

        questionRepository.save(question2);

        Answer answer = Answer.builder()
                .content("content")
                .question(question)
                .ownerStudent(student)
                .build();
        answerRepository.save(answer);

        Answer answer2 = Answer.builder()
                .content("content")
                .question(question)
                .ownerStudent(student2)
                .build();
        answerRepository.save(answer2);
    }


}
