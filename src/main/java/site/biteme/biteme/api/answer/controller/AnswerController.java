package site.biteme.biteme.api.answer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import site.biteme.biteme.domain.answer.AnswerService;

@RequiredArgsConstructor
@RestController
public class AnswerController {
    private final AnswerService answerService;

    // todo 답변 등록
    //  1. 답변 dto 받고
    //  2. 답변 entity로 변환
    //  3. 답변 등록
}
