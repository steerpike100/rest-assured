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

public class Basics6 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("C:\\paxport-automation\\rest-assured\\src\\main\\java\\files\\env.properties");
        prop.load(fis);
        prop.get("HOST");
    }

    @Test
    public void jiraAPI() {
       //create Defect
        Response res = given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKey()).
                body("{\n" +
                        "    \"body\": \"ibrahimavic.\"\n" +
                        "}").
        when().
        post("/rest/api/2/issue/10004/comment").
        then().
        statusCode(201).
        extract().response();

        JsonPath js = ReusableMethods.rawToJson(res);
        String id = js.get("id");
        System.out.println(id);

        }
    }

