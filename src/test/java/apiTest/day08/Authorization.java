package apiTest.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Authorization {

    @BeforeClass
    public void beforeClass() {
        baseURI="http://eurotech.study";
    }

    @Test
    public void test1() {

        String loginBody="{\n" +
                "  \"email\": \"usainBold23@eurotech.com\",\n" +
                "  \"password\": \"Test1234\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(loginBody)
                .when()
                .post("api/auth");

       assertEquals(response.statusCode(),200);

       String token=response.path("token");
        System.out.println("token = " + token);

    }

    @Test
    public static String getToken() {

        Map<String,Object> tokenMap=new HashMap<>();
        tokenMap.put("email","ensar1905@gmail.com");
        tokenMap.put("password","Gs1905");

        Response response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("api/auth");

        assertEquals(response.statusCode(),200);

        String token=response.path("token");

        return token;
    }

    @Test
    public static Map<String,Object> getAccessToken(String email,String password) {

        Map<String,Object> tokenMap=new HashMap<>();
        tokenMap.put("email",email);
        tokenMap.put("password",password);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("api/auth");

        String token=response.path("token");

        Map<String,Object> authorization=new HashMap<>();
        authorization.put("x-auth-token",token);

        return authorization;

    }
}
