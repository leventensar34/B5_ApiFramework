package apiTest.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequestDemo {

    /* Task
    Given accept type and Content type is JSON
    And request JSON body is
    {
  "email": "mertens@gmail.com",
  "password": "Test1234",
  "name": "Mertens",
  "google": "mertensB",
  "facebook": "mertensG",
  "github": "mertens95"
   }
   When user send POST request to api/users
   Then status code 200
   And token should be created
     */

    @BeforeClass
    public void beforeClass() {
        baseURI="http://eurotech.study";
    }

    @Test
    public void postNewUser() {

        String jsonBody="{\n" +
                "          \"email\": \"usainBold23@eurotech.com\",\n" +
                "          \"password\": \"Test1234\",\n" +
                "          \"name\": \"UsainBold\",\n" +
                "          \"google\": \"UsainB\",\n" +
                "          \"facebook\": \"UsainFace\",\n" +
                "          \"github\": \"Usain21\"\n" +
                "        }";
        Response response = given().accept(ContentType.JSON) //hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) //hey api I am sending jsonBody
                .and()
                .body(jsonBody)
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser2() {
        Map<String,Object> requestMap=new HashMap<>();
        requestMap.put("email","usainbold23@eurotech.net");
        requestMap.put("password","Test1234");
        requestMap.put("name","useinBold");
        requestMap.put("google","usain");
        requestMap.put("facebook","usainbold23@eurotech.net");
        requestMap.put("github","usainbold23@eurotech.net");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap) //serialization
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));


    }

    @Test
    public void postNewUser3() {

        NewUserInfo newUserInfo=new NewUserInfo();

        newUserInfo.setEmail("usainbold23@eurotech.set");
        newUserInfo.setPassword("Test1234");
        newUserInfo.setName("useinset1");
        newUserInfo.setGoogle("usaingoogle");
        newUserInfo.setFacebook("setface");
        newUserInfo.setGithub("githubset");


        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser4() {

        NewUserInfo newUserInfo=new NewUserInfo("usainbold30@eurotech.com",
                "Test1234","usainGson","Gsongoogle","Gsonbook","githubGson");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));


    }
}

