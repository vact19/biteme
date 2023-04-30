package site.biteme.biteme.global.converter;

import org.springframework.core.convert.converter.Converter;
import site.biteme.biteme.domain.common.Category;

public class CategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String source) {
        return Category.from(source);
    }
}
