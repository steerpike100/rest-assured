import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.Resources;
import files.Payload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics3 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\paxport-automation\\rest-assured\\src\\main\\java\\files\\env.properties");
        prop.load(fis);
        prop.get("HOST");
    }

    @Test
    public void addAndDeletePlace(){

        RestAssured.baseURI= prop.getProperty("HOST");

        Response res =

        given().
                queryParam("key", "AIzaSyADqtYWAgww4sVZUE8JyeZoUA0WWOon0XQ").
                body(Payload.getPostData()).
                when().post(Resources.placePostData()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                              body("status", equalTo("OK")).extract().response();

        String responseString = res.asString();
        System.out.println(responseString);
        JsonPath js = new JsonPath(responseString);

        String placeID = js.get("place_id");
        System.out.println(placeID);

        given().
                queryParam("key", prop.getProperty("KEY")).
                body("{\"place_id\" : \"" + placeID + "\"}").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));
    }
}
