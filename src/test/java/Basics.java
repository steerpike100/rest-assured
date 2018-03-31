import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class Basics {

    @Test
    public void testOne() {
        RestAssured.baseURI="https://maps.googleapis.com";

        given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key", "AIzaSyADqtYWAgww4sVZUE8JyeZoUA0WWOon0XQ");
        when().
                get("maps/api/place/nearbysearch/json").
        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Sydney")).
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
                header("Server", "scaffolding on HTTPServer2");

    }
}
