package com.app.unemploymentRate;


import com.app.unemploymentRate.factory.FactoryCountriesData;
import com.app.unemploymentRate.model.CountryRate;
import com.app.unemploymentRate.util.CountryRateUtil;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import parser.JSONstatParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CountriesDataTest {

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
        ArrayList<CountryRate> dataFromJsonStat = (ArrayList<CountryRate>) FactoryCountriesData.getCountriesData(contents);
        String mostRecentYear = JSONstatParser.getMostRecentYear(contents);

        HashMap<String, Double> countriesWithHighestUR = CountryRateUtil.
                getTopCountriesWithWorstRateInYear(dataFromJsonStat, 3, mostRecentYear);

        HashMap<String, Double> countriesWithLowestUR = CountryRateUtil.
                getTopCountriesWithBestRateInYear(dataFromJsonStat, 3, mostRecentYear);

        for (String country : countriesWithHighestUR.keySet()) {
            assertTrue(highestUnemployedCountries.contains(country), country + " should be in the list:[" +
                    highestUnemployedCountries + "] but it is not");
            assertFalse(lowestUnemployedCountries.contains(country), country + " should not be list:[" +
                    lowestUnemployedCountries + "but it is");
        }

        for (String country : countriesWithLowestUR.keySet()) {
            assertTrue(lowestUnemployedCountries.contains(country), country + " should be in the list:[" +
                    highestUnemployedCountries + "] but it is not");
            assertFalse(highestUnemployedCountries.contains(country), country + " should not be list:[" +
                    lowestUnemployedCountries + "but it is");
        }
    }
}
