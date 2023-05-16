package site.biteme.biteme.api.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.biteme.biteme.api.common.SingleRspsTemplate;
import site.biteme.biteme.api.question.dto.AddQuestionDto;
import site.biteme.biteme.domain.question.QuestionService;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentService;
import site.biteme.biteme.global.resolver.StudentEmail;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final StudentService studentService;
//    private final FileService fileService;

//    @GetMapping("/questions/images")
//    public void getImage(HttpServletResponse response, @RequestParam String url) {
//        byte[] bytes = fileService.getByteArr(url);
//
//
//
//        response.setContentType("image/*");
//        response.getOutputStream().write(bytes);
//    }

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





















