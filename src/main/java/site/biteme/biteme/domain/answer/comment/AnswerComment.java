package site.biteme.biteme.domain.answer.comment;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.answer.Answer;
import site.biteme.biteme.domain.common.BaseTimeEntity;
import site.biteme.biteme.domain.common.Comment;
import site.biteme.biteme.domain.student.Student;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AnswerComment extends BaseTimeEntity implements Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    // 어떤 답변에 대한 댓글인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;
    // 누가 작성했는지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student ownerStudent;

    @Builder
    private AnswerComment(String content, Answer answer, Student ownerStudent) {
        this.content = content;
        this.answer = answer;
        this.ownerStudent = ownerStudent;
    }
}



























