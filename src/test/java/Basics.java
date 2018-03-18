import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Basics {

    public static void main(String[] args) {
        RestAssured.baseURI="https://maps.googleapis.com";

        given().
                params("loction", "-33.8670522,151.1957362").
                params("radius", "500").
                params("key", "AIzaSyDc8BMYmvlaFDU6cgQ5gXFwmxVbLk0wQVc");
        when().
                get("maps/api/place/nearbysearch/json").
        then().
                assertThat().statusCode(200);

    }
}
