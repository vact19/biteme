package site.biteme.biteme.domain.answer.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerCommentService {
    private final AnswerCommentRepository answerCommentRepository;
}
