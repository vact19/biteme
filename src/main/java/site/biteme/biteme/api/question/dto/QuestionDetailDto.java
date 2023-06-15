package site.biteme.biteme.api.question.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import site.biteme.biteme.api.student.dto.StudentIdAndNameDto;
import site.biteme.biteme.domain.answer.Answer;
import site.biteme.biteme.domain.answer.comment.AnswerComment;
import site.biteme.biteme.domain.answer.component.AnswerState;
import site.biteme.biteme.domain.common.Comment;
import site.biteme.biteme.domain.question.Question;
import site.biteme.biteme.domain.question.comment.QuestionComment;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 질문 상세페이지 조회
 *   1. 질문
 *     0. 질문 식별자
 *     1. 질문작성자
 *       0. 질문작성자 식별자
 *       1. 질문작성자 이름
 *     2. 질문제목
 *     3. 질문내용
 *     4. 질문첨부사진(여러장일 수도 있다.)
 *     5. 질문댓글 개수
 *   2. 답변(의 목록)
 *     0. 답변식별자
 *     1. 답변작성자
 *       0. 답변작성자 식별자
 *       1. 답변작성자 이름
 *     2. 답변내용
 *     3. 답변상태
 *     4. 답변댓글 개수
 */
public class QuestionDetailDto {

    @Getter
    public static class Response {
        private QuestionDto question;
        private List<AnswerDto> answers;

        /**
         *  파라미터가 많고 내부 구현이 짧으면 바로 빌더를 부르고,
         *  파라미터가 적고 내부 구현이 길면 (Entity 받아서 DTO 매핑하는 등)
         *  정적 메서드 of() 안에서 빌더를 부르는 식으로 구현하기로 했다.
         */
        // MultipleBagFetchException 때문에 질문의 답변리스트는
        @Builder
        public Response(Question question, List<Answer> answerList){
            this.question = QuestionDto.of(question, answerList.size(), question.getQuestionComments());
            this.answers = AnswerDto.of(answerList);
        }

        @Getter
        @Builder(access = AccessLevel.PRIVATE)
        static class QuestionDto{
            private Long id;
            private StudentIdAndNameDto student;
            private String title;
            private String content;
            private List<String> images;
            private int answerCount;
            private int commentCount;
            private List<CommentDto> comments;

            static QuestionDto of(Question question, int answerCount, List<QuestionComment> questionComments){
                int commentCount = question.getQuestionComments().size();
                List<CommentDto> commentDtos = CommentDto.of(questionComments);

                return QuestionDto.builder()
                        .id(question.getId())
                        .student(StudentIdAndNameDto.of(question.getOwnerStudent()))
                        .title(question.getTitle())
                        .content(question.getContent())
                        .images(question.getImageUrls())
                        .answerCount(answerCount)
                        .commentCount(commentCount)
                        .comments(commentDtos)
                        .build();
            }

        }

        @Getter
        @Builder(access = AccessLevel.PRIVATE)
        static class AnswerDto{
            private Long id;
            private StudentIdAndNameDto student;
            private String content;
            private AnswerState state;
            private int commentCount;
            private List<CommentDto> comments;

            static List<AnswerDto> of(List<Answer> answerList) {
                return answerList.stream()
                        .map(AnswerDto::of)
                        .collect(Collectors.toList());
            }

            static AnswerDto of(Answer answer) {
                List<AnswerComment> answerComments = answer.getAnswerComments();
                int commentCount = answerComments.size();
                return AnswerDto.builder()
                        .id(answer.getId())
                        .student(StudentIdAndNameDto.of(answer.getOwnerStudent()))
                        .content(answer.getContent())
                        .state(answer.getAnswerState())
                        .commentCount(commentCount)
                        .comments(CommentDto.of(answerComments))
                        .build();
            }

        }

        @Builder(access = AccessLevel.PRIVATE)
        @Getter
        static class CommentDto{
            private Long id;
            private StudentIdAndNameDto student;
            private String content;

            static List<CommentDto> of(List<? extends Comment> comments) {
                return comments.stream()
                        .map(CommentDto::of)
                        .collect(Collectors.toList());
            }

            static CommentDto of(Comment comment) {
                return CommentDto.builder()
                        .id(comment.getId())
                        .student(StudentIdAndNameDto.of(comment.getOwnerStudent()))
                        .content(comment.getContent())
                        .build();
            }
        }





    }
}
