import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APItests {

    @Test(description = "APItests.test1")
    void test1(){
        Response response = RestAssured.get("https://catfact.ninja/facts");

        System.out.println("Response: " +response.asString());
        System.out.println("Status code is: " +response.getStatusCode());
        System.out.println("Header: " +response.getHeader("content-type"));

    }


@Test(description = "APItests.test2")
        void test2(){
            Response response = RestAssured.get("https://catfact.ninja/fact");

            System.out.println("Response: " +response.asString());
            System.out.println("Status je: " +response.getStatusCode());
            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 200);

    }

        @Test(description = "APItests.test3")
        void test3(){
              Response resp = given().
                param("data").
                when().
                get("https://catfact.ninja/facts");


        System.out.println("Response: "+resp.asString());

    }
        @Test(description = "APItests.test3")
        void getResponseHeaders(){
        Response response = RestAssured.get("https://catfact.ninja/fact");
        String facts = response.jsonPath().getString("fact");
        System.out.println(facts);

    }
        @Test

        public static Response doGetRequest(String endpoint) {
            RestAssured.defaultParser = Parser.JSON;

            return
                    given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                            when().get(endpoint).
                            then().contentType(ContentType.JSON).extract().response();
        }

        public static <JsonArray, JsonObject> void main(String[] args) {
            Response response = doGetRequest("https://catfact.ninja/facts");

            List<String> jsonResponse = response.jsonPath().getList("data");

            System.out.println("Počet položek v sekci data: " + jsonResponse.size());

            //base URI with Rest Assured class
            RestAssured.baseURI = "https://catfact.ninja/facts";

            //obtain Response from GET request
            Response res = given()
                    .when()
                    .get("https://catfact.ninja/facts");

            //convert JSON to string
            JsonPath j = new JsonPath(res.asString());

            //get values of JSON array after getting array size
            int s = j.getInt("data.size()");
            for(int i = 0; i < s; i++) {
                String fact = j.getString("data["+i+"].fact");
                String length = j.getString("data["+i+"].length");
                System.out.println("Fakta "+ i +": " +fact);
                System.out.println("Pocet znaku "+ i +".faktu: " + length);
            }


        }
}
