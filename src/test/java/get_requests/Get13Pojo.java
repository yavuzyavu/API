package get_requests;

import base_url.GorrestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestPojo;
import pojos.GorestDataPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Get13Pojo extends GorrestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
    "meta": null,
    "data": {
        "id": 2508,
        "name": "Dhananjay Chopra",
        "email": "chopra_dhananjay@graham-bode.info",
        "gender": "female",
        "status": "active"
    }
}
    */

    @Test
    public void get13Pojo() {

        //Set the Url
        spec.pathParams("first","users","second",2508);

        //Set The Expected Data
        GorestDataPojo gorestDataPojo =new GorestDataPojo(2508,"Dhananjay Chopra","chopra_dhananjay@graham-bode.info",
                "female","active");

        GoRestPojo expectedData = new GoRestPojo(null,gorestDataPojo);
        System.out.println("expectedData = " + expectedData);

        //Set The Ruquest and Get the Response
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();
       
       //Do Assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(gorestDataPojo.getId(),actualData.getData().getId());
        assertEquals(gorestDataPojo.getName(),actualData.getData().getName());
        assertEquals(gorestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(gorestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(gorestDataPojo.getStatus(),actualData.getData().getStatus());



    }
}
