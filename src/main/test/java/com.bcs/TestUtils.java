package com.nike.pes.parc;

import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    public static InputStream loadTestResourceStream(String resourcePath) throws IOException {
        return new ClassPathResource(resourcePath).getInputStream();
    }

    public static ObjectNode loadJsonResource(String resourcePath) throws IOException {
        return (ObjectNode) Jackson.getObjectMapper().readTree(loadTestResourceStream(resourcePath));
    }
}
