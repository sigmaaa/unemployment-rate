package com.app.unemploymentRate;

import com.app.unemploymentRate.factory.FactoryCountriesData;
import com.app.unemploymentRate.model.CountryRate;
import com.app.unemploymentRate.util.CountryRateUtil;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CountryRateUtilTest {

    private final int maxOutputSizeForTestData = 36;
    private final int testOutputSize1 = 100;
    private final int testOutputSize2 = 3;
    private final int testOutputSize3 = -5;

    @Test
    void testGetTopCountriesWithBestRateInYearOutputLength() throws IOException, JSONException {
        String contents1 = new String(Files.readAllBytes(Paths.get("src/test/testData/testData1.json").toAbsolutePath()));
        ArrayList<CountryRate> countryRates = (ArrayList<CountryRate>) FactoryCountriesData.getCountriesData(contents1);
        boolean resultEquals = false;

        if (maxOutputSizeForTestData == CountryRateUtil.getTopCountriesWithBestRateInYear(countryRates, testOutputSize1, "2004").size()) {
            resultEquals = true;
        }
        assertTrue(resultEquals, "must be equals to max output size for test data");

        resultEquals = testOutputSize2 == CountryRateUtil.getTopCountriesWithBestRateInYear(countryRates, testOutputSize2, "2004").size();
        assertTrue(resultEquals, "must be equals to 3");

        resultEquals = 0 == CountryRateUtil.getTopCountriesWithBestRateInYear(countryRates, testOutputSize3, "2004").size();

        assertTrue(resultEquals, "must be equals to 0");
    }

    @Test
    void testGetTopCountriesWithWorstRateInYearOutputLength() throws IOException, JSONException {
        String contents1 = new String(Files.readAllBytes(Paths.get("src/test/testData/testData1.json").toAbsolutePath()));
        ArrayList<CountryRate> countryRates = (ArrayList<CountryRate>) FactoryCountriesData.getCountriesData(contents1);
        boolean resultEquals = false;

        if (maxOutputSizeForTestData == CountryRateUtil.getTopCountriesWithWorstRateInYear(countryRates, testOutputSize1, "2004").size()) {
            resultEquals = true;
        }
        assertTrue(resultEquals, "must be equals to max output size for test data");

        resultEquals = testOutputSize2 == CountryRateUtil.getTopCountriesWithWorstRateInYear(countryRates, testOutputSize2, "2004").size();
        assertTrue(resultEquals, "must be equals to 3");

        resultEquals = 0 == CountryRateUtil.getTopCountriesWithWorstRateInYear(countryRates, testOutputSize3, "2004").size();

        assertTrue(resultEquals, "must be equals to 0");
    }
}
