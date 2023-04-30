package site.biteme.biteme.global.converter;

import org.springframework.core.convert.converter.Converter;
import site.biteme.biteme.domain.common.Department;

public class DepartmentConverter implements Converter<String, Department> {
    @Override
    public Department convert(String source) {
        return Department.from(source);
    }
}
