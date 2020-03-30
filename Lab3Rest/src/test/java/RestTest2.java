import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.*;

public class RestTest2 {

    @Test

    public void restAssuredTest2() {

        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
        Response response = null;

        try{
            response = (Response) RestAssured.given().when().get("/posts/7");

        }catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(200, response.getStatusCode());
    }

}