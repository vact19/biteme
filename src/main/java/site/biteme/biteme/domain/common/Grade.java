package site.biteme.biteme.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // json 직렬화 시 한글 설명 desc로 반환됨
public enum Grade {
    FRESHMAN("1학년"),  SOPHOMORE("2학년"),
    JUNIOR("3학년"),  SENIOR("4학년"), GRADUATE("졸업생");

    private final String desc;

    Grade(String desc) {
        this.desc = desc;
    }

    // 역직렬화를 위함. RankConverter 에서 사용.
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static Grade from(String grade){
        return Grade.valueOf(grade.toUpperCase());
    }
}
