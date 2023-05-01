package site.biteme.biteme.api.common;

import lombok.Getter;

// 응답 템플릿
// 응답 DTO가 리스트가 아닐 때
@Getter
public class SingleRspsTemplate<T> {
    private int statusCode;
    private T data;

    public SingleRspsTemplate(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
