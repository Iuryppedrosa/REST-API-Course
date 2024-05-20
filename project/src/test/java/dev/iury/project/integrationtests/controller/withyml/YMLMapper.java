package dev.iury.project.integrationtests.controller.withyml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.logging.Logger;

public class YMLMapper extends ObjectMapper {
    private Logger logger = Logger.getLogger(YMLMapper.class.getName());

    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;
    protected TypeFactory typeFactory;

    public YMLMapper() {
        objectMapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        typeFactory = TypeFactory.defaultInstance();
    }

    @SuppressWarnings("rawtypes")
    public Object deserialize(ObjectMapperDeserializationContext context) {
        try {
            String dataToDeserialize = context.getDataToDeserialize().asString();
            Class type = (Class)context.getType();

            logger.info("Trying deserialize object of type" + type);

            return objectMapper.readValue(dataToDeserialize, type);
        } catch (JsonMappingException e) {
            logger.severe("Error deserializing object");
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            logger.severe("Error deserializing object");
            e.printStackTrace();
        }
        return null;
    }

    public Object serialize(ObjectMapperSerializationContext context) {
        try {
            return objectMapper.writeValueAsString(context.getObjectToSerialize());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
