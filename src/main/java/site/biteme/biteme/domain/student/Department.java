package site.biteme.biteme.domain.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // json 직렬화 시 한글 설명 desc로 반환됨
public enum Department {
    IT("IT융합자율학부"), HUMANITIES("인문융합자율학부"),
    SOCIAL("사회융합자율학부"), MEDIA("미디어콘텐츠학부");

    private final String desc;

    Department(String desc) {
        this.desc = desc;
    }

    // 역직렬화를 위함. Department Converter에서 사용.
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static Department from(String department){
        return Department.valueOf(department.toUpperCase());
    }
}
