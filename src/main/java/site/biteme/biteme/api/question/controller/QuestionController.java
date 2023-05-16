package site.biteme.biteme.api.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.biteme.biteme.api.common.SingleRspsTemplate;
import site.biteme.biteme.api.question.dto.AddQuestionDto;
import site.biteme.biteme.domain.question.QuestionService;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentService;
import site.biteme.biteme.global.exception.ErrorCode;
import site.biteme.biteme.global.exception.file.FileIOException;
import site.biteme.biteme.global.resolver.StudentEmail;
import site.biteme.biteme.util.FileService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final StudentService studentService;
    private final FileService fileService;

    @GetMapping("/questions/images")
    public void getImage(HttpServletResponse response, @RequestParam(required = true) String url) {
        byte[] bytes = fileService.getByteArr(url);

        response.setContentType("image/*");
        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileIOException(ErrorCode.FILE_CANNOT_BE_SENT);
        }
    }

    // 질문 등록
    @PostMapping("/questions")
    public ResponseEntity<SingleRspsTemplate<String>> createQuestion(@ModelAttribute @Valid
                                                                         AddQuestionDto.Request questionRequest,
                                                                     @StudentEmail String email){
        Student student = studentService.findByEmail(email);
        questionService.save(questionRequest.toEntity(student), questionRequest.getImageFiles());

        SingleRspsTemplate<String> rspsTemplate = new SingleRspsTemplate<>(HttpStatus.CREATED.value(), "question created");
        return ResponseEntity.status(HttpStatus.CREATED).body(rspsTemplate);
    }

//    @GetMapping
//    public String test(@ModelAttribute Request request){
//        System.out.println(request.getImageFiles().getClass().getSimpleName());
//        if (request.getImageFiles() == null) {
//            System.out.println("null");
//        } else {
//            System.out.println("not null");
//        }
//        if (request.getImageFiles().isEmpty()) {
//            System.out.println("비어있음");
//        } else {
//            System.out.println("비어있지 않음");
//        }
//
//        if (request.getImageFiles().get(2).isEmpty()) {
//            System.out.println("원소 비어있음");
//        } else {
//            System.out.println("원소 비어있지 않음");
//        }
//        return "test";
//    }
//    @AllArgsConstructor
//    @Getter
//    static class Request {
//        List<MultipartFile> imageFiles;
//    }


}





















