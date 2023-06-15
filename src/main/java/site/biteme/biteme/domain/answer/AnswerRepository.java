package site.biteme.biteme.domain.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.biteme.biteme.domain.question.Question;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("Select a From Answer a Join Fetch a.ownerStudent where a.id = :answerId")
    Optional<Answer> findByIdFetchOwner(Long answerId);

    @Query("Select Distinct a From Answer a Join Fetch a.ownerStudent Left Join Fetch a.answerComments   where a.question = :question")
    List<Answer> findAllByQuestionFetchOwnerAndAnswerComments(Question question);
}