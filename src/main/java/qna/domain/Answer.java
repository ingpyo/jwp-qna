package qna.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import qna.NotFoundException;
import qna.UnAuthorizedException;

@Entity
@Table(name = "answer")
public class Answer extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long writerId;

  private Long questionId;
  @Lob
  private String contents;
  private boolean deleted = false;

  public Answer(User writer, Question question, String contents) {
    this(null, writer, question, contents);
  }

  public Answer(Long id, User writer, Question question, String contents) {
    this.id = id;

    if (Objects.isNull(writer)) {
      throw new UnAuthorizedException();
    }

    if (Objects.isNull(question)) {
      throw new NotFoundException();
    }

    this.writerId = writer.getId();
    this.questionId = question.getId();
    this.contents = contents;
  }

  protected Answer() {

  }

  public boolean isOwner(User writer) {
    return this.writerId.equals(writer.getId());
  }

  public void toQuestion(Question question) {
    this.questionId = question.getId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getWriterId() {
    return writerId;
  }

  public void setWriterId(Long writerId) {
    this.writerId = writerId;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public String toString() {
    return "Answer{" +
        "id=" + id +
        ", writerId=" + writerId +
        ", questionId=" + questionId +
        ", contents='" + contents + '\'' +
        ", deleted=" + deleted +
        '}';
  }
}
