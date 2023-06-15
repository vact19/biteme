package site.biteme.biteme.api.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.biteme.biteme.api.answer.dto.AddAnswerDto;
import site.biteme.biteme.api.common.SingleRspsTemplate;
import site.biteme.biteme.domain.answer.AnswerService;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.question.QuestionService;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentService;
import site.biteme.biteme.global.resolver.StudentEmail;

@RequiredArgsConstructor
@RestController
public class AnswerController {
    private final AnswerService answerService;
    private final StudentService studentService;
    private final QuestionService questionService;

    // 답변 등록
    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<SingleRspsTemplate<String>> createAnswer(@RequestBody AddAnswerDto.Request answerRequest
                                                                                                , @PathVariable Long questionId, @StudentEmail String email){
        //Todo 아래작업 하나의 메서드 안에서 처리. 질문자가 답변을 등록할 수 없도록

        // 어떤 질문에 대한 답변인지, 누가 답변하는지
        Student student = studentService.findByEmail(email);
        Question question = questionService.findById(questionId);

        // 저장
        answerService.save(answerRequest.toEntity(student, question));

        SingleRspsTemplate<String> rspsTemplate = new SingleRspsTemplate<>(201, "answer created");
        return ResponseEntity.status(HttpStatus.CREATED).body(rspsTemplate);
    }




}
