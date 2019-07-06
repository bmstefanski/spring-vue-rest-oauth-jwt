package pl.bmstefanski.example.infrastructure.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class ObjectToMapConverter implements Converter<Map<String, Object>, Object> {

  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public Map<String, Object> convert(Object dto) {
    return OBJECT_MAPPER.convertValue(dto, new TypeReference<Map<String, Object>>() {});
  }

}
