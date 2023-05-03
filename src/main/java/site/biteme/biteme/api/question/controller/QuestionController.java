package site.biteme.biteme.api.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.biteme.biteme.api.common.SingleRspsTemplate;
import site.biteme.biteme.api.student.dto.WriteQuestionDto;
import site.biteme.biteme.domain.jwt.service.TokenManager;
import site.biteme.biteme.domain.question.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class QuestionController {
    private static String imageUrl;
    private final QuestionService questionService;
    @Value("${file.upload.path}")// yml 설정파일
    private String fileUploadPath;
    private final TokenManager tokenManager;

    @GetMapping("/questionsImageTest")
    public void getAllQuestions(HttpServletResponse response) throws IOException {
        InputStream imageStream = new FileInputStream(fileUploadPath + imageUrl);
        byte[] bytes = imageStream.readAllBytes();
        imageStream.close();

        response.setContentType("image/*");
        response.getOutputStream().write(bytes);
    }
    @PostMapping("/questions")
    public ResponseEntity<SingleRspsTemplate<String>> createQuestion(@ModelAttribute @Valid
                                                                         WriteQuestionDto.Request questionRequest,
                                                                     HttpServletRequest request) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String email = tokenManager.getMemberEmail(authorizationHeader.split(" ")[1]);

        // 1. dto의 이미지를 로컬에 저장
        // todo 지금 사진 하나만 저장하는거임
        MultipartFile multipartFile = questionRequest.getImageFiles().get(0);
        String originalFileName = multipartFile.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();
        int pos = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(pos + 1);
        String storedFileName = email + uuid + "." + ext;
        imageUrl = storedFileName;
        multipartFile.transferTo(new File(fileUploadPath + storedFileName));
        // 2. 이미지 파일명을 db에 저장

        // 3. 201 응답
        return ResponseEntity.ok(new SingleRspsTemplate<>(HttpStatus.CREATED.value(), storedFileName));
    }
}
