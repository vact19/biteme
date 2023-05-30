package site.biteme.biteme.domain.student.component;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Department {
    IT("IT융합자율학부"), HUMANITIES("인문융합자율학부"),
    SOCIAL("사회융합자율학부"), MEDIA("미디어콘텐츠학부");

    @JsonValue
    private final String desc;

    Department(String desc) {
        this.desc = desc;
    }

    public static Department from(String department){
        return Department.valueOf(department.toUpperCase());
    }
}
