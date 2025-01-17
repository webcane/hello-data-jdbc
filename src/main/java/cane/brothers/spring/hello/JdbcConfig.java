package cane.brothers.spring.hello;

import cane.brothers.game.IGuessNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.sql.JDBCType;
import java.sql.SQLType;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Configuration
class JdbcConfig extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return Arrays.asList(new ArrayToGuessNumberConverter(),
                new GuessNumberWritingConverter(),
                new UUIDWritingConverter());
    }

    @ReadingConverter
    static class ArrayToGuessNumberConverter implements Converter<Integer[], IGuessNumber> {

        @Override
        public IGuessNumber convert(Integer[] source) {
            int[] d = ArrayUtils.toPrimitive(source);
            return new GuessNumber(d);
        }
    }

    @WritingConverter
    static class GuessNumberWritingConverter implements Converter<IGuessNumber, JdbcValue> {

        @Override
        public JdbcValue convert(IGuessNumber source) {
            var sqlType = new SQLType() {
                @Override
                public String getName() {
                    return "Integer[]";
                }
                @Override
                public String getVendor() {
                    return "Postgres";
                }
                @Override
                public Integer getVendorTypeNumber() {
                    return JDBCType.ARRAY.getVendorTypeNumber();
                }
            };
            return JdbcValue.of(source.getDigits(), sqlType);
        }
    }

    @WritingConverter
    static class UUIDWritingConverter implements Converter<UUID, JdbcValue> {
        @Override
        public JdbcValue convert(UUID source) {
            var sqlType = new SQLType() {
                @Override
                public String getName() {
                    return "uuid";
                }
                @Override
                public String getVendor() {
                    return "Postgres";
                }
                @Override
                public Integer getVendorTypeNumber() {
                    return JDBCType.OTHER.getVendorTypeNumber();
                }
            };
            return JdbcValue.of(source, sqlType);
        }
    }
}
