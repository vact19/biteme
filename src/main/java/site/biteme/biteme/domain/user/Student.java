package site.biteme.biteme.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.biteme.biteme.domain.common.Department;
import site.biteme.biteme.domain.common.Grade;
import site.biteme.biteme.domain.common.Major;
import site.biteme.biteme.domain.common.Rank;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Major major;
    @Enumerated(EnumType.STRING)
    private Department department;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    // todo 랭크를 올리기 위한 포인트를 어떻게 운용할 것인지. 별도의 엔티티로? 그냥 학생엔티티 메서드로?
    // todo 유틸리티 클래스? 생각해보자.
    // todo 글, 답변, 질문댓글 구현
    private int point = 0;


    @Builder
    public Student(String name, String email, String password, Major major, Department department, Rank rank, Grade grade) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.major = major;
        this.department = department;
        this.rank = rank;
        this.grade = grade;
    }
}
