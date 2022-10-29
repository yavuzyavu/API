package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class Get04b extends RestfulBaseUrl {

     /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

 */

    @Test
    public void test04() {

        //Set The Url
        spec.pathParam("first","booking").queryParams("firstname","Almedin","lastname","Alikadic");
        
        //Set The Expected Data

//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion

        assertEquals(200,response.statusCode());
        assertTrue(response.asString().contains("bookingid"));
    }
}
