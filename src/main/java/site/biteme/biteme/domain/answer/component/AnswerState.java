package site.biteme.biteme.domain.answer.component;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AnswerState {
    ACCEPTED("채택완료"), NOT_ACCEPTED("채택 대기중");

    @JsonValue
    private final String desc;

    AnswerState(String desc) {
        this.desc = desc;
    }
}
