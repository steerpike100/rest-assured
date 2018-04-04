import files.Payload;
import files.Resources;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics5 {


    @Test
    public void doSomeStuff() {
        RestAssured.baseURI = "https://maps.googleapis.com";

        Response res = given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "500").
                param("key", "AIzaSyADqtYWAgww4sVZUE8JyeZoUA0WWOon0XQ").
                when().
                get("maps/api/place/nearbysearch/json").
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Sydney")).
                extract().response();

        JsonPath json = ReusableMethods.rawToJson(res);
        int count = json.get("results.size");
        for(int i=0; i<count; i++){
            System.out.println(json.getString("results[" + i + "].name"));
        }
    }
}
