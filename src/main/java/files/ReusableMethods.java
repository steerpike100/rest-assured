package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static XmlPath rawToXml(Response r) {

        String response = r.asString();
        XmlPath xml = new XmlPath(response);

        return xml;
    }

    public static JsonPath rawToJson(Response r) {
        String response = r.asString();
        JsonPath json = new JsonPath(response);

        return json;

    }

    public static String getSessionKey(){

        RestAssured.baseURI="http://localhost:8080";
        Response res = given().header("Content-Type", "application/json").
                body("{\"username\": \"stevejbartholomew@gmail.com\", \"password\": \"Afflu3nza1\"}").
                when().
                post("/rest/auth/1/session").
                then().
                statusCode(200).
                extract().response();
        JsonPath js = ReusableMethods.rawToJson(res);
        String sessionid = js.get("session.value");

        return sessionid;
    }

//    public static String createComment()

}
