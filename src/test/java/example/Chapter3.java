package example;

import com.tngtech.java.junit.dataprovider.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@RunWith(DataProviderRunner.class)
public class Chapter3 {
    @DataProvider
    public static Object[][] zipCodesAndPlaces(){
    return new Object[][]{
            {"us", "90210", "Beverly Hills"},
            {"us", "12345", "Schenectady"},
            {"ca", "B2R", "Waverley"}
    };
}
    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills(String countryCode, String zipCode, String expectedPlaceName){
        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
                get("http://zippopotam.us/{countryCode}/{zipCode}").
        then().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }
}
