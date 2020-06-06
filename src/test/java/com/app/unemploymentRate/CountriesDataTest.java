package com.app.unemploymentRate;

import com.app.unemploymentRate.JsonObjects.CountriesData;
import com.app.unemploymentRate.JsonObjects.FactoryCountriesData;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CountriesDataTest {

    @Test
    void testHighestAndLowestEmployeeCountriesByJson() throws IOException, JSONException {
        testBestWorstCountriesUnemployedRate("src/test/testData/testData1.json",
                "Australia, Austria, Belgium",
                "Canada, Chile Czech Republic");

        testBestWorstCountriesUnemployedRate("src/test/testData/testData2.json",
                "Germany, Slovenia, United States",
                "Luxembourg, United Kingdom, Denmark");

        testBestWorstCountriesUnemployedRate("src/test/testData/testData3.json",
                "Euro area (15 countries), France, Canada",
                "Estonia, Poland, Ireland");
    }

    void testBestWorstCountriesUnemployedRate(String path, String highestUnemployedCountries,
                                              String lowestUnemployedCountries)
            throws IOException, JSONException {

        String contents = new String(Files.readAllBytes(Paths.get(path).toAbsolutePath()));
        CountriesData test1 = new FactoryCountriesData().getCountriesData(contents);

        for (String country : test1.getCountriesWithHighestUR().keySet()) {
            assertTrue(highestUnemployedCountries.contains(country), country + " should be in the list:[" +
                    highestUnemployedCountries + "] but it is not");
            assertFalse(lowestUnemployedCountries.contains(country), country + " should not be list:[" +
                    lowestUnemployedCountries + "but it is");
        }

        for (String country : test1.getCountriesWithLowestUR().keySet()) {
            assertTrue(lowestUnemployedCountries.contains(country), country + " should be in the list:[" +
                    highestUnemployedCountries + "] but it is not");
            assertFalse(highestUnemployedCountries.contains(country), country + " should not be list:[" +
                    lowestUnemployedCountries + "but it is");;
        }
    }
}
