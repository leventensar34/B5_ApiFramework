package apiTest.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DevExWithJsonPath {

    String devExUrl = "http://eurotech.study";

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 74)
                .when().get(devExUrl + "/api/profile/userQuery");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        System.out.println("response.path(\"email\") = " + response.path("email"));

        System.out.println("***********JsonPath Method*******************");

        JsonPath jsonData = response.jsonPath();

        int userId = jsonData.getInt("id");
        System.out.println("userId = " + userId);

        String emailJson = jsonData.getString("email");
        String nameJson = jsonData.getString("name");
        String companyJson = jsonData.getString("company");
        String statusJson = jsonData.getString("status");
        int profileIdJson = jsonData.getInt("profileId");

        System.out.println("emailJson = " + emailJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("companyJson = " + companyJson);
        System.out.println("statusJson = " + statusJson);
        System.out.println("profileIdJson = " + profileIdJson);


    }

    @Test
    public void task() {

        /*
        Given accept type is json
        And query param 29
        Status code 200
        Content Type application Json
        verify user information with using JsonPath
        {
    "id": 29,
    "email": "oyku@gmail.com",
    "name": "oyku",
    "company": "Microsoft",
    "status": "Student or Learning",
    "profileId": 11
}
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 29)
                .when().get(devExUrl + "/api/profile/userQuery");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        JsonPath jsonData1 = response.jsonPath();

        int idJson = jsonData1.getInt("id");
        String emailJson = jsonData1.getString("email");
        String nameJson = jsonData1.getString("name");
        String companyJson = jsonData1.getString("company");
        String statusJson = jsonData1.getString("status");
        int profileIdJson = jsonData1.getInt("profileId");

        System.out.println("emailJson = " + emailJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("companyJson = " + companyJson);
        System.out.println("statusJson = " + statusJson);
        System.out.println("profileIdJson = " + profileIdJson);


    }

    @Test
    public void test2() {

        Response response = RestAssured.get(devExUrl + "/api/profile");
        assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();

        int secondUserId = jsonPath.getInt("id[1]");
        System.out.println("secondUserId = " + secondUserId);

        List<Object> allCompanies = jsonPath.getList("company");
        System.out.println("allCompanies = " + allCompanies);

        Map<String, Object> secondUserInfo = jsonPath.getMap("user[1]");
        System.out.println("secondUserInfo = " + secondUserInfo);
        System.out.println("secondUserInfo.get(\"name\") = " + secondUserInfo.get("name"));

        secondUserInfo.forEach((x, y) -> System.out.println(x + " : " + y));

        System.out.println("--------------------------------");

        List<String> secondUserSkills=jsonPath.getList("skills[1]");
        System.out.println("secondUserSkills = " + secondUserSkills);

        System.out.println();
        System.out.println("*************GPATH Method*******************");

        //get all user names which has github as null

        List<Object> listGithubNull=jsonPath.getList("user.findAll{it.github==null}.name");
        //it means if.                                                      github!=null seklindede yapilabilir.
        System.out.println("listGithubNull = " + listGithubNull);

        List<Object> listName=jsonPath.getList("user.findAll{it.id>500}.name");
        System.out.println("listName = " + listName);


    }
}
