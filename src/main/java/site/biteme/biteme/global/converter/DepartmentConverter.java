package site.biteme.biteme.global.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import site.biteme.biteme.domain.student.component.Department;
@Component
public class DepartmentConverter implements Converter<String, Department> {
    @Override
    public Department convert(String source) {
        return Department.from(source);
    }
}
