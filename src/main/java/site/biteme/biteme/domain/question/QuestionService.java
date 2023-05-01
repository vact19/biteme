package site.biteme.biteme.domain.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
}
