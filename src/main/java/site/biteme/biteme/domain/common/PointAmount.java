package site.biteme.biteme.domain.common;

import lombok.Getter;

@Getter
public enum PointAmount {
    ANSWER_ACCEPTED(30),
    ;

    private int amount;

    PointAmount(int amount) {
        this.amount = amount;
    }
}
