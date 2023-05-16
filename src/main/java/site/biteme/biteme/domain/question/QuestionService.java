package site.biteme.biteme.domain.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.biteme.biteme.domain.student.StudentRepository;
import site.biteme.biteme.util.FileService;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;
    private final FileService fileService;

    @Transactional
    public Question save(Question question, List<MultipartFile> imageFiles) {
        studentRepository.save(question.getStudent()); // persist or merge

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
}
