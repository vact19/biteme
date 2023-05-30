package site.biteme.biteme.domain.student.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Rank {
    FRESHMAN("신입생", 0),  SPOILED("쉰입생", 100),
    FOSSIL("화석", 300),  GOSU("고인물", 500), PROFESSOR("교수님", 1500)
    ;

    @JsonValue
    private final String desc;
    private final int goalPoint;

    Rank(String desc, int goalPoint) {
        this.desc = desc;
        this.goalPoint = goalPoint;
    }

    // 역직렬화를 위함. RankConverter 에서 사용.
    @JsonCreator
    public static Rank from(String rank){
        return Rank.valueOf(rank.toUpperCase());
    }
}
