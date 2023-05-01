package site.biteme.biteme.global.interceptor;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.biteme.biteme.domain.jwt.constant.GrantType;
import site.biteme.biteme.domain.jwt.constant.TokenType;
import site.biteme.biteme.domain.jwt.service.TokenManager;
import site.biteme.biteme.global.exception.ErrorCode;
import site.biteme.biteme.global.exception.auth.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;
    /**
     *  인터셉터는 디스패처 서블릿과 컨트롤러 사이에서 동작한다.
     *  즉 preHandle 은. 컨트롤러 동작 직전에 벌어지는 일.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("AuthenticationInterceptor preHandler");

        //  1. authorization 필수 체크. 헤더 부분에 Authorization 이 없으면 지정한 예외를 발생시킴
        //  토큰 유무 확인
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(authorizationHeader)){
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        //  2. authorization Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");
        // GrantType.BEARER.getType() 은 "Bearer"문자열 반환
        if(authorizations.length < 2 || (!GrantType.BEARER.getType().equals(authorizations[0]))){
            throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }

        //  3. 토큰 검증
        String token = authorizations[1]; // Bearer 뒤의 토큰 몸통 부분
        if(!tokenManager.validateToken(token)){
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }

        //  4. 토큰 타입 검증
        String tokenType = tokenManager.getTokenType(token);
        // tokenType.equals(TokenType.ACCESS.name()); 해당 코드는 NPE 발생 가능성이 있음. NULL을 피하는 코드 작성하자
        if(!TokenType.ACCESS.name().equals(tokenType)) { // ACCESS 토큰이 아니면
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        //  5. 액세스 토큰 만료 시작 검증
        // 일단 isTokenExpired() 인자로 넣을 토큰만료일 Date 객체를 가져옴
        Claims tokenClaims = tokenManager.getTokenClaims(token);
        Date expiration = tokenClaims.getExpiration();

        if(tokenManager.isTokenExpired(expiration)) {
            throw new AuthenticationException(ErrorCode.ACCESS_TOKEN_EXPIRED);
        }

        return true; // 정상 처리
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("AuthenticationInterceptor postHandler");
    }
    /**
     *  PostHandle 과 afterCompletion 의 차이?
     *  애플리케이션 실행 로직에서 오류 발생 시
     *  afterCompletion 은 실행되지만, postHandle 은 실행되지 않는다.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("AuthenticationInterceptor afterCompletion");
    }
}






















