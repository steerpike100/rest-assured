import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Basics8 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\paxport-automation\\rest-assured\\src\\main\\java\\files\\env.properties");
        prop.load(fis);
        prop.get("HOST");
    }

    @Test
    public void jiraAPI() {
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).
                body("{simple comment}").
                when().put("/rest/api/2/issue/10004/comment/10003").then().statusCode(201).extract().response();

        }
    }

