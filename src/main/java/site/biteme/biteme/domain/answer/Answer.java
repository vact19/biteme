package site.biteme.biteme.domain.answer;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.common.BaseTimeEntity;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.student.Student;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 답변 내용
    @Lob
    private String content;
    // 어떤 질문에 대한 답변인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    // 누가 작성했는지
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Builder
    public Answer(String content, Question question, Student student) {
        this.content = content;
        this.question = question;
        this.student = student;
    }
}

















