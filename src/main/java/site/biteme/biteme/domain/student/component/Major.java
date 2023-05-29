package site.biteme.biteme.domain.student.component;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 *  Category 는 Major 와 내용이 거의 비슷한데,
 *  Category 는 게시글의 카테고리
 *  Major 는 학생(회원)이 가지는 정보
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT) // json 직렬화 시 한글 설명 desc로 반환됨
public enum Major {
    // IT학부
    SOFTWARE("소프트웨어전공"),  COMPUTER("컴퓨터공학전공"),
    INFORMATION("정보통신전공"),  AI("인공지능전공"), DPT_IT("IT융합자율학부"),

    // 인문학부
    ENGLISH("영어학전공"), JAPANESE("일어일본학"), CHINESE("중어중국학"),
    DPT_HUMANITIES("인문융합자율학부"),

    // 사회학부
    ECONOMICS("경제학전공"), BUSINESS("경영학전공"), POLITICAL("정치학전공"),
    SOCIOLOGY("사회학"), SOCIAL_WELFARE("사회복지학"),
    DPT_SOCIAL("사회융합자율학부"),

    // 미디어콘텐츠학부
    JOURNALISM("언론학"), DIGITAL("디지털콘텐츠학"),
    DPT_MEDIA("미디어콘텐츠학부"),

    ;
    private final String desc;

    Major(String desc) {
        this.desc = desc;
    }
    // 역직렬화를 위함. CategoryConverter 에서 사용.
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static Major from(String major){
        return Major.valueOf(major.toUpperCase());
    }


}
