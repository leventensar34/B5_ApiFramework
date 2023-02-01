package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class DevExBodyTest {

    String devExUrl = "http://eurotech.study";

    String petURl = "https://petstore.swagger.io/v2";

    @Test
    public void devExVerifyWithPath() {

        /*

{
  "id": 565,
  "email": "ensar1905@gmail.com",
  "name": "Ensar1905",
  "company": "Eurotech",
  "status": "Other",
  "profileId": 318
}
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 565)
                .when().get(devExUrl + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"status\") = " + response.path("status"));
        System.out.println("response.path(\"profiled\") = " + response.path("profiled"));

        int actualId=response.path("id");
        assertEquals(actualId,565);
        assertEquals(response.path("email"),"ensar1905@gmail.com");
        assertEquals(response.path("name"),"Ensar1905");
        assertEquals(response.path("company"),"Eurotech");

    }

    @Test
    public void petStoreVerifyWithPet() {

        /*
        TASK https://petstore.swagger.io/#/pet/findPetsByStatus

        Given accept content type Json
        And path param is 5
        When user GET s request to /pet/5
        Then verify response code is 200
        verify id is 5
        verify name is doggie
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get(petURl + "/pet/{id}");
        int actualId=5;
        assertEquals(response.statusCode(),200);
        assertEquals(actualId,5);
        assertEquals(response.path("name"),"doggie");

    }
}
