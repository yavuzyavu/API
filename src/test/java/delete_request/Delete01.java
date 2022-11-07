package delete_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Delete01 extends JsonplaceholderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {

        //Set  The Url

        spec.pathParams("first","todos","second",198);

        //Set the Expected Data
        Map<String,String> expectedData = new HashMap<>();

        //Set The Request and Get The Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");

        //Do Assertion
        Map actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

        assertEquals(200,response.getStatusCode());

        //1. Yol
        assertEquals(expectedData, actualData);

        //2. Yol
        assertTrue(actualData.isEmpty());

        //3. Yol
        assertEquals(0,actualData.size());

    }
}



