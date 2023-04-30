package site.biteme.biteme.global.converter;

import org.springframework.core.convert.converter.Converter;
import site.biteme.biteme.domain.common.Grade;

public class GradeConverter implements Converter<String, Grade> {

    @Override
    public Grade convert(String source) {
        return Grade.from(source);
    }
}
