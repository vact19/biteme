package site.biteme.biteme.global.converter;

import org.springframework.core.convert.converter.Converter;
import site.biteme.biteme.domain.common.Major;

public class MajorConverter implements Converter<String, Major> {
    @Override
    public Major convert(String source) {
        return Major.from(source);
    }
}
