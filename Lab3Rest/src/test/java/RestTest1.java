import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.junit.Assert.*;

public class RestTest1 {

    @Test

    public void restAssuredTest1() {


        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
        Response response = null;

        try{
            response = (Response) RestAssured.given().when().get("/users/5");

        }catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(200, response.getStatusCode());

    }
}