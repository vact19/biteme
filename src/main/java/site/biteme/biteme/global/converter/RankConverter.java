package site.biteme.biteme.global.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import site.biteme.biteme.domain.student.component.Rank;
@Component
public class RankConverter implements Converter<String, Rank> {
    @Override
    public Rank convert(String source) {
        return Rank.from(source);
    }
}
