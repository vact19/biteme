package site.biteme.biteme.domain.question;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import site.biteme.biteme.domain.common.BaseTimeEntity;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.student.Student;

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
    private State state = State.IN_PROGRESS; // 질문 상태. In progress, Done. 기본값은 In progress
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
    }
}













