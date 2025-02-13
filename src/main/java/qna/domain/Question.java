package qna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 100)
  private String title;
  @Lob
  private String contents;
  private Long writerId;
  @Column(nullable = false)
  private boolean deleted = false;

  public Question() {
  }

  public Question(String title, String contents) {
    this(null, title, contents);
  }

  public Question(Long id, String title, String contents) {
    this.id = id;
    this.title = title;
    this.contents = contents;
  }

  public Question writeBy(User writer) {
    this.writerId = writer.getId();
    return this;
  }

  public boolean isOwner(User writer) {
    return this.writerId.equals(writer.getId());
  }

  public void addAnswer(Answer answer) {
    answer.toQuestion(this);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public Long getWriterId() {
    return writerId;
  }

  public void setWriterId(Long writerId) {
    this.writerId = writerId;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Question question = (Question) o;

    return getId() != null ? getId().equals(question.getId()) : question.getId() == null;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Question{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", contents='" + contents + '\'' +
        ", writerId=" + writerId +
        ", deleted=" + deleted +
        '}';
  }
}
