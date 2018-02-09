package com.chinaopensource.apiserver.common.util.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * create by lzl ON 2018/02/08
 */
public class LocalDateTimeSerializer  extends StdSerializer<LocalDateTime> {

    public LocalDateTimeSerializer() {
        this(null);
    }

    public LocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        Date date = Date.from(value.atZone(TimeUtils.DEFAULT_TIMEZONE).toInstant());
        jgen.writeNumber(date.getTime());
    }
}
