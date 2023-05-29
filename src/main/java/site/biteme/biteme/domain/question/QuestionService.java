package site.biteme.biteme.domain.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.biteme.biteme.domain.answer.Answer;
import site.biteme.biteme.domain.answer.AnswerService;
import site.biteme.biteme.domain.common.PointAmount;
import site.biteme.biteme.domain.student.Student;
import site.biteme.biteme.domain.student.StudentRepository;
import site.biteme.biteme.global.exception.BusinessException;
import site.biteme.biteme.global.exception.ErrorCode;
import site.biteme.biteme.util.FileService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final StudentRepository studentRepository;
    private final FileService fileService;

    @Transactional
    public Question save(Question question, List<MultipartFile> imageFiles) {
        studentRepository.save(question.getOwnerStudent()); // persist or merge

        // List 가 null 일 경우를 검사한다. List 의 MultipartFile 원소가 Empty 인 경우는 FileService 에서 검사한다.
        if (imageFiles == null) {
            return questionRepository.save(question);
        }
        // question 은 image url이 없는 상태.
        // 1. imageFiles를 먼저 처리한다.
        List<String> strings = fileService.saveAll(imageFiles);

        // 2. setter를 통해 image url을 question에 저장한다.
        question.setImageUrls(strings);

        // 3. 마지막으로 Question 저장.
        return questionRepository.save(question);
    }

    @Transactional
    public void acceptAnswer(Long questionId, Long answerId, String email) {
        /** 아래 작업들은 하나의 트랜잭션 안에서 실행되므로 예외시 롤백*/
        // 1.토큰 소유자와 질문글의 작성자가 일치하는지 확인.
        Question question = findByIdFetchOwner(questionId);
        question.checkOwner(email);

        // 2. 질문글 QuestionState Done으로 전환(내부에서 이미 Done인지 확인)
        question.setStateDone();

        // 3. 답변글 Status Accepted로 전환(내부에서 이미 Accepted인지 확인)
        Answer answer = answerService.findByIdFetchOwner(answerId);
        answer.setStatusAccepted();

        // 4. 답변글의 답변자 포인트 증가
        Student answerOwnerStudent = answer.getOwnerStudent();
        answerOwnerStudent.getPointStatus().increasePoint(PointAmount.ANSWER_ACCEPTED);
    }

    public Question findById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.QUESTION_NOT_FOUND));
    }
    public Question findByIdFetchOwner(Long questionId) {
        return questionRepository.findByIdFetchOwner(questionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.QUESTION_NOT_FOUND));
    }


}



















