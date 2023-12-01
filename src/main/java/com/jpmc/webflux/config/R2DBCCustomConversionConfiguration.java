package com.jpmc.webflux.config;

//import io.r2dbc.spi.ConnectionFactories;
//import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.H2Dialect;
//import org.springframework.data.r2dbc.dialect.MySqlDialect;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Configuration
public class R2DBCCustomConversionConfiguration {

    @Bean
    public R2dbcCustomConversions h2customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new DateConverter());
        converters.add(new ZonedDateTimeConverter());
        converters.add(new LocaleDateTimeConverter());
        converters.add(new SqlDateConverter());
        return R2dbcCustomConversions.of(H2Dialect.INSTANCE, converters);
    }

   /* @Bean
    public R2dbcCustomConversions mysqlcustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new DateConverter());
        converters.add(new ZonedDateTimeConverter());
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE, converters);
    }
*/
    static class DateConverter implements Converter<LocalDateTime, Date> {
        @Override
        public Date convert(LocalDateTime localDate) {

            if(null == localDate) return null;

            return Date.valueOf(localDate.toLocalDate());
        }
    }

    static class ZonedDateTimeConverter implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(ZonedDateTime localDate) {

            if(null == localDate) return null;

            return Date.valueOf(localDate.toLocalDate());
        }
    }

    static class LocaleDateTimeConverter implements Converter<LocalDateTime, Date> {
        @Override
        public Date convert(LocalDateTime localDate) {

            if(null == localDate) return null;

            return Date.valueOf(localDate.toLocalDate());
        }
    }

    static class SqlDateConverter implements Converter<Date, LocalDateTime> {
        @Override
        public LocalDateTime convert(Date localDate) {

            if(null == localDate) return null;

            return localDate.toLocalDate().atStartOfDay();
        }
    }
}
