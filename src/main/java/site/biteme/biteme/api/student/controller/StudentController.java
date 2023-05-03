package site.biteme.biteme.api.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.biteme.biteme.api.common.SingleRspsTemplate;
import site.biteme.biteme.api.student.dto.StudentSignUpDto;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class StudentController {
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/students/sign-up")
    public ResponseEntity<SingleRspsTemplate<String>> signUp(@RequestBody @Valid StudentSignUpDto.Request signUpRequest){
        Student student = studentService.signUp(signUpRequest.toEntity(passwordEncoder));
        SingleRspsTemplate<String> rspsTemplate = new SingleRspsTemplate<>(HttpStatus.CREATED.value(),
                student.getName() + " is created");
        return ResponseEntity.status(HttpStatus.CREATED).body(rspsTemplate);
    }




}























