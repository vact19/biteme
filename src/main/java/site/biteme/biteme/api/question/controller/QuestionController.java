package site.biteme.biteme.api.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.biteme.biteme.api.common.SingleRspsTemplate;
import site.biteme.biteme.api.question.dto.WriteQuestionDto;
import site.biteme.biteme.domain.question.QuestionService;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentService;
import site.biteme.biteme.global.resolver.StudentEmail;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final StudentService studentService;


    @GetMapping("/")
    public void getImage(HttpServletResponse response) throws IOException {
        InputStream imageStream = new FileInputStream("C:/Users/woo/myimage/" + "6fee38a0-fc8c-4a94-8f2f-d1bb3a99548e.png");
        byte[] bytes = imageStream.readAllBytes();
        imageStream.close();
        System.err.println(Arrays.toString(bytes));
        response.setContentType("image/*");
        response.getOutputStream().write(bytes);
    }

    // 질문 등록
    @PostMapping("/questions")
    public ResponseEntity<SingleRspsTemplate<String>> createQuestion(@ModelAttribute @Valid
                                                                         WriteQuestionDto.Request questionRequest,
                                                                     @StudentEmail String email){
        Student student = studentService.findByEmail(email);
        questionService.save(questionRequest.toEntity(student), questionRequest.getImageFiles());

        // 3. 201 응답😝
        return ResponseEntity.ok(new SingleRspsTemplate<>(HttpStatus.CREATED.value(), "question created"));
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





















