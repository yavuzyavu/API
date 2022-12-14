package get_requests;

import base_url.GorrestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GorrestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
    And
        The female users are less than or equals to male users
 */

    @Test
    public void get11() {
        //Set The Url
        spec.pathParams("first","users");

        //

        //3.Send The Request And
       Response response= given().spec(spec).when().get("/{first}");
       response.prettyPrint();

       //
        response.then().assertThat().statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                "meta.pagination,links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data",hasSize(10),
                "data.status",hasItem("active"),
                "data.name",hasItems("Sujata Chaturvedi","Pres. Amarnath Dhawan","Navin Panicker"));

        //kadin ve erkek sayisini  karsilastirma
        //1. Yol
     List<String> genders=  response.jsonPath().getList("data.gender");

     int female =0;
     for(String w : genders){

         if(w.equalsIgnoreCase("femele")){
             female++;
         }

     }
        assertTrue(female<=genders.size()-female);

     //2. yol  kadin erkek sayilarini Grovy ile bulalim.

       List<String> femaleNames = response.jsonPath().getList("data.findAll{it.gender=='female}");
        System.out.println("femaleNames = " + femaleNames);;

        List<String> maleNames = response.jsonPath().getList("data.findAll{it.gender=='male}");
        System.out.println("maleNames = " + maleNames);

        assertTrue(femaleNames.size()<=maleNames.size());

    }
}
