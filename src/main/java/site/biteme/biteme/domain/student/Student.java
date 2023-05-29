package site.biteme.biteme.domain.student;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.common.BaseTimeEntity;
import site.biteme.biteme.domain.student.component.Department;
import site.biteme.biteme.domain.student.component.Major;
import site.biteme.biteme.domain.student.component.PointStatus;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Major major;
    @Enumerated(EnumType.STRING)
    private Department department;

    @Embedded
    private PointStatus pointStatus;


    @Builder
    public Student(String name, String email, String password, Major major, Department department) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.major = major;
        this.department = department;
    }

    // Todo 포인트를 올리는 메서드
    //      @Embedded로 포인트, 랭크를 관리하는 엔티티 만들고
    //      Util 클래스 안에서 등급, 포인트 증감 관리하자.
    public void increasePoint() {

    }
}
