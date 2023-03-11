package site.project.accountinfoapp.configuration.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
public class DecodeConfig implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        log.info(String.valueOf(response));
        return new GsonDecoder().decode(response, type);
    }
}
