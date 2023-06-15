package site.biteme.biteme.domain.common;

import site.biteme.biteme.domain.student.Student;

public interface Comment {
    Long getId();
    String getContent();
    Student getOwnerStudent();
}
