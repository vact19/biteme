package site.biteme.biteme.api.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.biteme.biteme.domain.student.Student;

@Getter
@AllArgsConstructor
public class StudentIdAndNameDto {
    private Long id;
    private String name;

    public static StudentIdAndNameDto of(Student ownerStudent) {
        return new StudentIdAndNameDto(ownerStudent.getId(), ownerStudent.getName());
    }
}
