package site.biteme.biteme.domain.question;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.common.BaseTimeEntity;
import site.biteme.biteme.domain.common.Category;
import site.biteme.biteme.domain.student.Student;

import javax.persistence.*;
import java.util.List;

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
    private Category category;
    @Lob
    private String content;
    private State state; // 질문 상태. In progress, Done
    @ElementCollection
    private List<String> imageUrls;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false) // 누가 질문을 작성했는지
    private Student student;

    @Builder
    public Question(String title, Category category, String content, List<String> imageUrls, Student student) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.imageUrls = imageUrls;
        this.student = student;
    }
}













