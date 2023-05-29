package site.biteme.biteme.domain.student.component;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // json 직렬화 시 한글 설명 desc로 반환됨
public enum Rank {
    FRESHMAN("신입생"),  SPOILED("쉰입생"),
    FOSSIL("화석"),  GOSU("고인물"), PROFESSOR("교수님")

    ;

    private final String desc;

    Rank(String desc) {
        this.desc = desc;
    }

    // 역직렬화를 위함. RankConverter 에서 사용.
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static Rank from(String rank){
        return Rank.valueOf(rank.toUpperCase());
    }
}
