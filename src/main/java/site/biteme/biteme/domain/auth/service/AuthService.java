package site.biteme.biteme.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.biteme.biteme.domain.jwt.dto.TokenDto;
import site.biteme.biteme.domain.jwt.service.TokenManager;
import site.biteme.biteme.domain.student.StudentService;
import site.biteme.biteme.global.exception.ErrorCode;
import site.biteme.biteme.global.exception.auth.AuthenticationException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthService {
    private final EmailVerificationService emailVerificationService;
    private final JavaMailSender javaMailSender;
    private final StudentService studentService;
    private final TokenManager tokenManager;

    // todo 재발급 토큰 관리
    public TokenDto signIn(String email, String password) {
        // 1. email, password로 검증
        studentService.validatePassword(email, password);

        // 2. 토큰 생성
        return tokenManager.createTokenDto(email);
    }

    public void sendEmailVerificationCode(String email) {
        // 코드 생성 후 20분간 기억한다.
        String verificationCode  = emailVerificationService.generateCode(email);

        // 생성된 코드로 이메일을 전송한다.
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("이메일 인증받아라");
        message.setText("너의 인증코드는 " + verificationCode);
        try {
            javaMailSender.send(message);
        } catch (MailException e){
            throw new AuthenticationException(ErrorCode.EMAIL_CANNOT_BE_SENT);
        }
    }
}















