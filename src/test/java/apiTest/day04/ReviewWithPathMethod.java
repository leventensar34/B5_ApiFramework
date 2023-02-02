package apiTest.day04;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ReviewWithPathMethod {

    String devExUrl = "http://eurotech.study";

    String petURl = "https://petstore.swagger.io/v2";


    @Test
    public void getAllProfiles() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExUrl + "/api/profile");

        assertEquals(response.statusCode(),200);
       // response.prettyPrint();

        //Finding first element ID
       int firstID= response.path("id[0]");
        System.out.println("firstID = " + firstID);

        //Finding last element ID
        int lastID=response.path("id[-1]");
        System.out.println("lastID = " + lastID);

        System.out.println("----------------------------");

        //Finding second element COMPANY
        String secondCompany=response.path("company[1]");
        System.out.println("secondCompany = " + secondCompany);

        System.out.println("----------------------------");

        //Get first skills' skills as list

        List<String> firstSkills=response.path("skills[0]");

        for (String skill : firstSkills) {
            System.out.println("skill = " + skill);
        }

       // System.out.println("firstSkills.size() = " + firstSkills.size());

        Object firstSkillSecondElement = response.path("skills[0][1]");
        System.out.println("firstSkillSecondElement = " + firstSkillSecondElement);

        System.out.println("----------------------------");


        Map<String,Object> firstUserMap=response.path("user[0]");
        System.out.println("firstUserMap = " + firstUserMap);
        for (String user : firstUserMap.keySet()) {
          //  System.out.println("user"+user);
            System.out.println(user+" : "+firstUserMap.get(user));

        }
       // firstUserMap.forEach((x,y)-> System.out.println(x+" : "+y));

        System.out.println("----------------------------");

        Object firstUserId = response.path("user[0].id");
        System.out.println("firstUserId = " + firstUserId);

        Object secondUserId = response.path("user[1].id"); //user.id[1] de ayni sonucu verir ama tavsiye  edilmez
        System.out.println("secondUserId = " + secondUserId);

        // Get all user IDs

        List<String> userIDs=response.path("user.id");
        System.out.println("userIDs.size() = " + userIDs.size());
        System.out.println("userIDs = " + userIDs);

        System.out.println("----------------------------");

        List<Integer> allIDs=response.path("id");
        for (Integer id : allIDs) {
            System.out.println("id ="+id);
        }

    }
}
