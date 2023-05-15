package site.biteme.biteme.global.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import site.biteme.biteme.domain.jwt.service.TokenManager;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class StudentEmailArgResolver implements HandlerMethodArgumentResolver {
    private final TokenManager tokenManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean hasEmailAnnotation = parameter.hasParameterAnnotation(StudentEmail.class);
        boolean hasString = String.class.isAssignableFrom(parameter.getParameterType());

        // 파라미터를 분석해 조건이 맞으면 아래 resolveArgument 를 실행시킴.
        return hasEmailAnnotation && hasString;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 사용자 지정 어노테이션을 활용, 아래 로직을 자동 수행해 리턴함함
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        // "Bearer " 제거. "Bearer " 가 있다는 것은 Interceptor 에서 검증함.
        token = token.substring(7);
        return tokenManager.getMemberEmail(token);
    }
}
