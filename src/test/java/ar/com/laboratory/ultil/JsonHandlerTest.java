package ar.com.laboratory.ultil;

import ar.com.laboratory.util.JsonHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonHandlerTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JsonHandler jsonHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("toJson returns valid json string for given object")
    void toJsonReturnsValidJsonString() throws Exception {
        Object object = new Object();
        when(objectMapper.writeValueAsString(object)).thenReturn("{}");

        String result = jsonHandler.toJson(object);

        assertEquals("{}", result);
    }

    @Test
    @DisplayName("toJson throws exception when object cannot be parsed")
    void toJsonThrowsExceptionWhenObjectCannotBeParsed() throws Exception {
        Object object = new Object();
        when(objectMapper.writeValueAsString(object)).thenThrow(JsonProcessingException.class);
        assertThrows(Exception.class, () -> jsonHandler.toJson(object));
    }

    @Test
    @DisplayName("toJsonNode returns valid JsonNode for given object")
    void toJsonNodeReturnsValidJsonNodeForObject() throws Exception {
        Object object = new Object();
        when(objectMapper.writeValueAsString(object)).thenReturn("{}");
        when(objectMapper.readTree("{}")).thenReturn(mock(JsonNode.class));

        JsonNode result = jsonHandler.toJsonNode(object);

        assertNotNull(result);
    }

    @Test
    @DisplayName("toJsonNode returns valid JsonNode for given json string")
    void toJsonNodeReturnsValidJsonNodeForJsonString() throws Exception {
        String jsonString = "{}";
        when(objectMapper.readTree(jsonString)).thenReturn(mock(JsonNode.class));

        JsonNode result = jsonHandler.toJsonNode(jsonString);

        assertNotNull(result);
    }

    @Test
    @DisplayName("toJsonNode throws exception when json string cannot be mapped")
    void toJsonNodeThrowsExceptionWhenJsonStringCannotBeMapped() throws Exception {
        String jsonString = "{}";
        when(objectMapper.readTree(jsonString)).thenThrow(JsonProcessingException.class);

        assertThrows(Exception.class, () -> jsonHandler.toJsonNode(jsonString));
    }

    @Test
    @DisplayName("fromObject returns valid object for given type")
    void fromObjectReturnsValidObjectForGivenType() {
        Object object = new Object();
        Class<Object> type = Object.class;
        when(objectMapper.convertValue(object, type)).thenReturn(object);

        Object result = jsonHandler.fromObject(object, type);

        assertEquals(object, result);
    }

    @Test
    @DisplayName("createObjectNode returns valid ObjectNode")
    void createObjectNodeReturnsValidObjectNode() {
        when(objectMapper.createObjectNode()).thenReturn(mock(ObjectNode.class));

        ObjectNode result = jsonHandler.createObjectNode();

        assertNotNull(result);
    }
}