package org.acme.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class JsonHandler {

    @Inject
    private ObjectMapper objectMapper;

    public <T> String toJson(T object) throws Exception {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new Exception("Could not parse object of type " + object.getClass().getName() + " to json");
        }
    }

    public <T> JsonNode toJsonNode(T object) throws Exception {
        return this.toJsonNode(this.toJson(object));
    }

    public JsonNode toJsonNode(String json) throws Exception {
        try {
            return this.objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new Exception("Could not map json to JsonNode");
        }
    }

    public <T> T fromObject(Object obj, Class<T> type) {
        return this.objectMapper.convertValue(obj, type);
    }

    public ObjectNode createObjectNode() {
        return this.objectMapper.createObjectNode();
    }

    public ArrayNode createArrayNode() {
        return this.objectMapper.createArrayNode();
    }
}
