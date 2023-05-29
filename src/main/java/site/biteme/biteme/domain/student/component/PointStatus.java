package site.biteme.biteme.domain.student.component;

import lombok.Getter;
import site.biteme.biteme.domain.common.PointAmount;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
public class PointStatus {
    @Enumerated(EnumType.STRING)
    private Rank rank; // 서비스 내 등급
    private int totalPoint;
    private int currentPoint;

    public PointStatus() {
        this.rank = Rank.FRESHMAN;
        this.totalPoint = 0;
        this.currentPoint = 0;
    }

    public void increasePoint(PointAmount pointAmount) {
        int pointToIncrease = pointAmount.getAmount();
        this.totalPoint += pointToIncrease;
        this.currentPoint += pointToIncrease;
        this.rank = calcRank();
    }

    private Rank calcRank() {
        if (this.totalPoint < Rank.SPOILED.getGoalPoint()) {
            return Rank.FRESHMAN;
        }
        if (this.totalPoint < Rank.FOSSIL.getGoalPoint()) {
            return Rank.SPOILED;
        }
        if (this.totalPoint < Rank.GOSU.getGoalPoint()) {
            return Rank.FOSSIL;
        }
        if (this.totalPoint < Rank.PROFESSOR.getGoalPoint()) {
            return Rank.GOSU;
        }
        return Rank.PROFESSOR;
    }
}



















