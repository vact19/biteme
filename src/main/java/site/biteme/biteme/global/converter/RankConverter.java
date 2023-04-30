package site.biteme.biteme.global.converter;

import org.springframework.core.convert.converter.Converter;
import site.biteme.biteme.domain.common.Rank;

public class RankConverter implements Converter<String, Rank> {
    @Override
    public Rank convert(String source) {
        return Rank.from(source);
    }
}
