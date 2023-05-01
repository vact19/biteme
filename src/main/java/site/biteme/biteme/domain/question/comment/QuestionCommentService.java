package site.biteme.biteme.domain.question.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionCommentService {
    private final QuestionCommentRepository questionCommentRepository;
}
