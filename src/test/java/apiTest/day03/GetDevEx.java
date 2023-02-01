package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetDevEx {

    String devExUrl = "http://eurotech.study";

    @Test
    public void test1() {

/*
        Given accept type is Json
        When user send GETS request to /api/profile
        Then verify that response status code is 200
        and verify that body contains "Microsoft"
         */

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(devExUrl + "/api/profile");
        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertTrue(response.body().asString().contains("Microsoft"));

    }

    @Test
    public void headersTest() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExUrl + "/api/profile");
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        System.out.println("response.header(\"Content-Lenght\") = " + response.header("Content-Lenght"));

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
       // Assert.assertEquals(response.header("Content-Lenght"),"278733");

        //Date bu sekilde verify edilemez
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));

    }
}
