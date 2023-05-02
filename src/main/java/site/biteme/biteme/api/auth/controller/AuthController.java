package site.biteme.biteme.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.biteme.biteme.api.auth.dto.StudentSignInDto;
import site.biteme.biteme.api.common.SingleRspsTemplate;
import site.biteme.biteme.domain.auth.AuthService;
import site.biteme.biteme.domain.jwt.dto.TokenDto;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    // 로그인
    @PostMapping("/student/sign-in")
    public SingleRspsTemplate<TokenDto> signIn(@RequestBody @Valid StudentSignInDto.Request singInRequest){
        TokenDto tokenDto = authService.signIn(singInRequest.getEmail(), singInRequest.getPassword());
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), tokenDto);
    }

}




















