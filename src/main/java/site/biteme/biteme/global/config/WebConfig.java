package site.biteme.biteme.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.biteme.biteme.global.interceptor.AuthenticationInterceptor;
import site.biteme.biteme.global.resolver.StudentEmailArgResolver;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // ArgResolver, Interceptor 등을 스프링 빈으로 등록함으로써 AOP 대상 적용, 생명주기 관리, @Lazy 지연초기화 등 사용 가능.

    // Interceptors
    private final AuthenticationInterceptor authenticationInterceptor;
    // ArgumentResolvers
    private final StudentEmailArgResolver studentEmailArgResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .order(1)   // 인증 인터셉터를 첫 번째로 수행
                .addPathPatterns("/**")     // 이 경로를 대상으로 동작
                .excludePathPatterns("/", "/students/sign-in", "/students/sign-up", "/auth/email")  // 이 경로는 검사 제외*/
                ;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
         resolvers.add(studentEmailArgResolver);
    }
}










