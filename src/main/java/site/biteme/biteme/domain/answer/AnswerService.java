package site.biteme.biteme.domain.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.biteme.biteme.domain.question.QuestionRepository;
import site.biteme.biteme.domain.student.StudentRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public Answer save(Answer answer) {
        studentRepository.save(answer.getStudent()); // persist or merge
        questionRepository.save(answer.getQuestion()); // persist or merge

        return answerRepository.save(answer);
    }
}
