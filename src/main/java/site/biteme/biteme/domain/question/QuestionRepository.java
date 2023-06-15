package site.biteme.biteme.domain.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("Select q From Question q Join Fetch q.ownerStudent where q.id = :questionId")
    Optional<Question> findByIdFetchOwner(Long questionId);

    @Query("Select q From Question q Join Fetch q.ownerStudent Left Join Fetch q.imageUrls where q.id = :questionId")
    Optional<Question> findByIdFetchOwnerAndImageUrls(Long questionId);
}
