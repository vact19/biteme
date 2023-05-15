package site.biteme.biteme.domain.student;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.common.BaseTimeEntity;

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
    @Enumerated(EnumType.STRING)
    private Rank rank; // 서비스 내 등급

    // todo 랭크를 올리기 위한 포인트를 어떻게 운용할 것인지. 별도의 엔티티로? 그냥 학생엔티티 메서드로?
    // todo 유틸리티 클래스? 생각해보자.
    private int point = 0;


    @Builder
    public Student(String name, String email, String password, Major major, Department department, Rank rank) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.major = major;
        this.department = department;
        this.rank = rank;
    }
}
