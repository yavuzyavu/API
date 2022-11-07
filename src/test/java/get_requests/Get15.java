package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
    "firstname": "Guoqiang",
    "lastname": "Morante Briones",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */

    @Test
    public void get15() {

        //Set the URL
        spec.pathParams("first", "booking", "second", 22);

        //Set the Expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expactedData = new BookingPojo("Guoqiang", "Morante Briones",
                111, true, bookingDatesPojo, "Breakfast");


        //Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);

        //Soft Assertion
        //1. Adim : SoftAssert objesi Olustur
        SoftAssert softAssert = new SoftAssert();

        //2. Adim Assertion Yop
        softAssert.assertEquals(actualData.getFirstname(),expactedData.getFirstname(),"Firstname uyusmadi");
        softAssert.assertEquals(actualData.getLastname(),expactedData.getLastname(),"Lastname uyusmadi");
        softAssert.assertEquals(actualData.getTotalprice(),expactedData.getTotalprice(),"totalprice uyusmadi");
        softAssert.assertEquals(actualData.getDepositpaid(),expactedData.getDepositpaid(),"Depositpaid uyusmadi");
        softAssert.assertEquals(actualData.getAdditionalneeds(),expactedData.getAdditionalneeds(),"Lastname uyusmadi");

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin(),"Checkin uyusmadi");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout(),"Checkout uyusmadi");

        softAssert.assertAll();


        //Hard Assertion
        assertEquals(200,response.getStatusCode());
        assertEquals(expactedData.getFirstname(),actualData.getFirstname());
        assertEquals(expactedData.getLastname(),actualData.getLastname());
        assertEquals(expactedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expactedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expactedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());



    }
}
