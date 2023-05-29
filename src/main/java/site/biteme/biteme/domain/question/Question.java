package site.biteme.biteme.domain.question;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import site.biteme.biteme.domain.common.BaseTimeEntity;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.question.component.QuestionState;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.global.exception.BusinessException;
import site.biteme.biteme.global.exception.ErrorCode;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Lob
    private String content;
    @Enumerated(EnumType.STRING)
    private QuestionState questionState; // 질문 상태. In progress, Done. 기본값은 In progress

    @Setter
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imageUrls;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_student_id", nullable = false, updatable = false) // 누가 질문을 작성했는지
    private Student ownerStudent;

    @Builder
    public Question(String title, Category category, String content, List<String> imageUrls, Student ownerStudent) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.imageUrls = imageUrls;
        this.ownerStudent = ownerStudent;

        this.questionState = QuestionState.IN_PROGRESS;
    }
    public void checkOwner(String email) {
        String writerEmail = ownerStudent.getEmail();
        if (! writerEmail.equals(email)) {
            log.error("토큰 소유자와 질문글의 작성자가 일치하지 않아 채택 불가");
            throw new BusinessException(ErrorCode.NOT_OWNER_OF_QUESTION);
        }
    }

    /** 이미 Done 상태인 질문을 In_Progress 로 만들 일이 없으므로
     *   setStatus(QuestionState questionState) 으로 만들지 않는다.*/
    public void setStateDone() {
        if (this.questionState == QuestionState.DONE) {
            throw new BusinessException(ErrorCode.QUESTION_ALREADY_DONE);
        }
        this.questionState = QuestionState.DONE;
    }
}













