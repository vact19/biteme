package site.biteme.biteme.domain.answer;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.answer.component.AnswerState;
import site.biteme.biteme.domain.common.BaseTimeEntity;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.global.exception.BusinessException;
import site.biteme.biteme.global.exception.ErrorCode;

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
    @Enumerated(EnumType.STRING)
    private AnswerState answerState;
    // 어떤 질문에 대한 답변인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    // 누가 작성했는지
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_student_id", nullable = false)
    private Student ownerStudent;

    @Builder
    private Answer(String content, Question question, Student ownerStudent) {
        this.content = content;
        this.question = question;
        this.ownerStudent = ownerStudent;

        this.answerState = AnswerState.NOT_ACCEPTED;
    }

    public void setStatusAccepted() {
        if (answerState == AnswerState.ACCEPTED) {
            throw new BusinessException(ErrorCode.ANSWER_NOT_FOUND);
        }
        this.answerState = AnswerState.ACCEPTED;
    }
}

















