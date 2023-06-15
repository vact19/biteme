package site.biteme.biteme.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.biteme.biteme.domain.answer.Answer;
import site.biteme.biteme.domain.answer.AnswerRepository;
import site.biteme.biteme.domain.answer.comment.AnswerComment;
import site.biteme.biteme.domain.answer.comment.AnswerCommentRepository;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.common.PointAmount;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.question.QuestionRepository;
import site.biteme.biteme.domain.question.comment.QuestionComment;
import site.biteme.biteme.domain.question.comment.QuestionCommentRepository;
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
    private final AnswerCommentRepository answerCommentRepository;
    private final QuestionCommentRepository questionCommentRepository;

    @Transactional
    @PostConstruct
    public void init() {
        /**
         *  Question 1은 답변 2개, Question 2는 답변 0개
         */
        Student student1 = Student.builder()
                .email("ex@email.com")
                .name("name")
                .password(passwordEncoder.encode("pppp"))
                .major(Major.COMPUTER)
                .department(Department.IT)
                .build();
        student1.getPointStatus().increasePoint(PointAmount.ANSWER_ACCEPTED);
        student1.getPointStatus().increasePoint(PointAmount.ANSWER_ACCEPTED);
        student1.getPointStatus().increasePoint(PointAmount.ANSWER_ACCEPTED);
        studentRepository.save(student1);


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

        Question question1 = Question.builder()
                .title("김효중너무멋있다.")
                .category(Category.AI)
                .content("김효중이왜멋있는지에대한설명")
                .imageUrls(strings)
                .ownerStudent(student1)
                .build();
        questionRepository.save(question1);

        Question question2 = Question.builder()
                .title("질문이있어요")
                .category(Category.AI)
                .content("국가적 손실")
                .imageUrls(strings)
                .ownerStudent(student2)
                .build();

        questionRepository.save(question2);

        Answer answer1 = Answer.builder()
                .content("질문1-답변1")
                .question(question1)
                .ownerStudent(student1)
                .build();
        answerRepository.save(answer1);

        Answer answer2 = Answer.builder()
                .content("질문1-답변2")
                .question(question1)
                .ownerStudent(student2)
                .build();
        answerRepository.save(answer2);

        QuestionComment questionComment1 = QuestionComment.builder()
                .content("질문1-댓글1")
                .question(question1)
                .ownerStudent(student2)
                .build();
        QuestionComment questionComment2 = QuestionComment.builder()
                .content("질문1-댓글2")
                .question(question1)
                .ownerStudent(student2)
                .build();
        questionCommentRepository.save(questionComment1);
        questionCommentRepository.save(questionComment2);

        AnswerComment answerComment1 = AnswerComment.builder()
                .content("질문1-답변1-댓글1")
                .answer(answer1)
                .ownerStudent(student2)
                .build();
        AnswerComment answerComment2 = AnswerComment.builder()
                .content("질문1-답변1-댓글2")
                .answer(answer1)
                .ownerStudent(student2)
                .build();
        answerCommentRepository.save(answerComment1);
        answerCommentRepository.save(answerComment2);
    }


}
