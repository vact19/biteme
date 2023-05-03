package site.biteme.biteme.domain.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.biteme.biteme.global.exception.BusinessException;
import site.biteme.biteme.global.exception.ErrorCode;
import site.biteme.biteme.global.exception.auth.AuthenticationException;

import javax.validation.ConstraintViolationException;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public Student signUp(Student student) {
        try {
            return studentRepository.save(student);
        } catch (ConstraintViolationException e) { // email unique 제약조건 위반 시
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_REGISTERED);
        }
    }

    public void validatePassword(String email, String password) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("로그인 시도, email: {}, 이메일이 일치하지 않습니다.", email);
                    return new AuthenticationException(ErrorCode.MISMATCHED_SIGNIN_INFO);
                });
        if (!passwordEncoder.matches(password, student.getPassword())) {
            // printf 스타일로 로그 출력,
            log.error("로그인 시도, email: {}, pwd: {}, 비밀번호가 일치하지 않습니다.", email, password);
            throw new AuthenticationException(ErrorCode.MISMATCHED_SIGNIN_INFO);
        }
    }
}
