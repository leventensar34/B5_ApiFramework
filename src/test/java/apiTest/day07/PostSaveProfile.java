package apiTest.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class PostSaveProfile {

    @BeforeClass
    public void beforeClass() {
        baseURI="http://eurotech.study";
    }


    @Test
    public void postNewUser() {

        //create new user
        //verify with token
        //Save user profile with using token

        NewUserInfo newUserInfo=new NewUserInfo("usainprof33@eurotech.com",
                "Test1234","usainprof","profgoogle","profbook","githubprof");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));

        String token=response.path("token");

        String profileBody="{\n" +
                "  \"company\": \"Eurotechb5\",\n" +
                "  \"website\": \"eurotech@study.com\",\n" +
                "  \"location\": \"Koln\",\n" +
                "  \"status\": \"QA\",\n" +
                "  \"skills\": \"HTML,CSS,Javascript\",\n" +
                "  \"githubusername\": \"B5github\",\n" +
                "  \"youtube\": \"B5youtube\",\n" +
                "  \"twitter\": \"string\",\n" +
                "  \"facebook\": \"string\",\n" +
                "  \"linkedin\": \"string\",\n" +
                "  \"instagram\": \"string\"\n" +
                "}";

       response= given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("api/profile");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test
    public void postNewUserAndVerify() {

        String email="galatasaray@futbol.com";
        String password="1905";
        String name="Duboa";
        String google="GSgoogle";
        String facebook="GSfacebook";
        String github="GSgithub";
        String company="Amazon";

        Map<String,Object> requestMap=new HashMap<>();

        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("name",name);
        requestMap.put("google",google);
        requestMap.put("facebook",facebook);
        requestMap.put("github",github);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);

        String token=response.path("token");

        Map<String,Object> profileBody=new HashMap<>();
        profileBody.put("company",company);
        profileBody.put("website","www.amazon.com");
        profileBody.put("location","Koln");
        profileBody.put("status","employer");
        profileBody.put("skills","google");
        profileBody.put("githubusername","jjgithub");
        profileBody.put("youtube","jjtube");
        profileBody.put("twitter","jjathome");
        profileBody.put("facebook","jjbook");
        profileBody.put("linkedin","jjlinkedin");
        profileBody.put("instagram","jjinsta");


        response= given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("api/profile");

        assertEquals(response.statusCode(),200);

        //verify body
        int id=response.path("user.id");
        given().accept(ContentType.JSON)
                .and()
                .queryParam("id",id)
                .when()
                .get("api/profile/userQuery");

        assertEquals(response.statusCode(),200);

        //verify with path
        assertEquals(response.path("name"),name);
        assertEquals(response.path("company"),company);

        // verify using hamcrest matcher
        given().accept(ContentType.JSON)
                .and()
                .queryParam("id",id)
                .when()
                .get("api/profile/userQuery")
                .then()
                .assertThat()
                .body("email", Matchers.equalTo(email),
                        "company",Matchers.equalTo(company))
                .log().all();

    }

    @Test
    public void task() {
        //register new user
        //save new user profile
        //verify user information using JSONPATH and Hamcrest Matcher
        //at least 2 different user
    }
}
