package com.app.unemploymentRate;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import parser.JSONstatParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JSONstatParserTest {

    @Test
    void testGetMostRecentYear() throws IOException, JSONException {
        String contents1 = new String(Files.readAllBytes(Paths.get("src/test/testData/testData1.json").toAbsolutePath()));
        String contents2 = new String(Files.readAllBytes(Paths.get("src/test/testData/testData4.json").toAbsolutePath()));

        assertEquals("2014", JSONstatParser.getMostRecentYear(contents1));
        assertEquals("2020", JSONstatParser.getMostRecentYear(contents2));
    }
}
