package apiTest.day08;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI="http://eurotech.study";
    }

    @Test
    public void addNewExperience() {

        /*
        {
  "title": "string",
  "company": "string",
  "location": "string",
  "from": "YYYY-MM-DD",
  "to": "YYYY-MM-DD",
  "current": false,
  "description": "string"
    }
         */

        Map<String,Object> experienceBody=new HashMap<>();
        experienceBody.put("title","Director");
        experienceBody.put("company","Google");
        experienceBody.put("location","Istanbul");
        experienceBody.put("from","2007-01-02");
        experienceBody.put("to","2008-02-10");
        experienceBody.put("current",false);
        experienceBody.put("description","Ne yorulan yillar");

        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .header("x-auth-token",Authorization.getToken())
                .and()
                .body(experienceBody)
                .when()
                .post("api/profile/experience")
                .then()
                .assertThat().statusCode(201);



    }

    @Test
    public void updateExperiencePutMethod() {

        Map<String,Object> experienceBody=new HashMap<>();
        experienceBody.put("title","Director");
        experienceBody.put("company","GS");
        experienceBody.put("location","Florya/Istanbul");
        experienceBody.put("from","2007-01-02");
        experienceBody.put("to","2008-02-10");
        experienceBody.put("current",false);
        experienceBody.put("description","Güzel yillardi");

        given().log().all()
                .and().contentType(ContentType.JSON)
                .and()
                .headers(Authorization.getAccessToken("ensar1905@gmail.com","Gs1905"))
                .and()
                .body(experienceBody)
                .when()
                .put("api/profile/experience/386")
                .then()
                .log().all()
                .assertThat().statusCode(204);



    }

    @Test
    public void updateExperiencePATCHMethod() {

        Map<String,Object> experienceBody=new HashMap<>();
        experienceBody.put("title","CEO");
        experienceBody.put("company","GS-FAN");


        given().log().all()
                .and().contentType(ContentType.JSON)
                .and()
                .headers(Authorization.getAccessToken("ensar1905@gmail.com","Gs1905"))
                .and()
                .pathParam("id",386)
                .and()
                .body(experienceBody)
                .when()
                .patch("api/profile/experience/{id}")
                .then()
                .log().all()
                .assertThat().statusCode(204);

    }

    @Test
    public void deleteExperienceDELETE() {

        given().log().all()
                .and().contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",Authorization.getToken())
                .and()
                .pathParam("id",318)
                .delete("api/profile/experience/{id}")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
