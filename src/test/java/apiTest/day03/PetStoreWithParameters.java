package apiTest.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class PetStoreWithParameters {

    /*
    @BeforeClass
    public void beforeClass() {

        baseURI="http://eurotech.study";
    }

    @Test
    public void testName() {

        Response response = RestAssured.get("/api/profile");
        response.prettyPrint();
    }

     */

    String petURl="https://petstore.swagger.io/v2";

    @Test
    public void pathParamPetStore() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petURl + "/pet/13");

        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);


    }

    @Test
    public void pathParamPet2() {

        int petID=13;
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", petID)  //pathParam icindeki String tanimlama key ile get kismindaki süslü parantez icindeki ayni olmali.
                .when().get(petURl + "/pet/{id}");
        Assert.assertEquals(response.statusCode(),200);

    }
}
