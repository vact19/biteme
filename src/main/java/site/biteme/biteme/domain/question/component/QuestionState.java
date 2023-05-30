package site.biteme.biteme.domain.question.component;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

// 질문 상태. In progress, Done
@Getter
public enum QuestionState {
    IN_PROGRESS("답변대기중"), DONE("채택완료")
    ;

    @JsonValue
    private final String desc;

    QuestionState(String desc) {
        this.desc = desc;
    }
}
