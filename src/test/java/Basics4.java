import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.ReusableMethods;

public class Basics4 {

    @Test
    public void postData() throws IOException {
        String postData = generateStringFromResouces("C:\\Users\\steveb\\Desktop\\postdata.xml");
        RestAssured.baseURI = "https://maps.googleapis.com";
        Response res = given().queryParam("key", "AIzaSyADqtYWAgww4sVZUE8JyeZoUA0WWOon0XQ")
                .body(postData)
                .when()
                .post("/maps/api/place/add/xml")
                .then().assertThat().statusCode(200).and().contentType(ContentType.XML)
                .extract().response();

        XmlPath xml = ReusableMethods.rawToXml(res);

        System.out.println((String) xml.get("PlaceAddResponse.place_id"));
    }

    public static String generateStringFromResouces(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
