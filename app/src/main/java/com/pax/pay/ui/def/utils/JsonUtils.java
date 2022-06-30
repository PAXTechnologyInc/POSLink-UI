package com.pax.pay.ui.def.utils;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paxus.utils.log.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    @NonNull
    public static <T> List<T> readListFromJSON(InputStream inputStream, Class<T> clz) {
        List<T> list = new ArrayList<>();
        if (inputStream != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            try {
                list = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(list.getClass(), clz));
            } catch (IOException e) {
                Logger.e(e);
            }
        }
        return list;
    }
} 