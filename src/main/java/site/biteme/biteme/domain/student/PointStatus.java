package site.biteme.biteme.domain.student;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
public class PointStatus {
    @Enumerated(EnumType.STRING)
    private Rank rank; // 서비스 내 등급
    private int totalPoint = 0;
    private int currentPoint = 0;
}
