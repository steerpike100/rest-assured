import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics2 {

    @Test
    public void postData(){
        RestAssured.baseURI="https://maps.googleapis.com";
        given().
                queryParam("key", "AIzaSyADqtYWAgww4sVZUE8JyeZoUA0WWOon0XQ").
                body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -33.8669710,\n" +
                        "    \"lng\": 151.1958750\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Google Shoes!\",\n" +
                        "  \"phone_number\": \"(02) 9374 4000\",\n" +
                        "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                        "  \"types\": [\"shoe_store\"],\n" +
                        "  \"website\": \"http://www.google.com.au/\",\n" +
                        "  \"language\": \"en-AU\"\n" +
                        "}\n" +
                        "  ").when().
                              post("/maps/api/place/add/json").
                              then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                              body("status", equalTo("OK"));
    }
}
