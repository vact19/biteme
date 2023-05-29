package site.biteme.biteme.domain.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("Select a From Answer a Join Fetch a.ownerStudent where a.id = :answerId")
    Optional<Answer> findByIdFetchOwner(Long answerId);
}
