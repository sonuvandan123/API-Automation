package basic.ResultAsuuredFirstPro;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPlaceAndGetOneJsonKeyData {

	public static void main(String[] args) {
		// for response use extract().response().asString();
		// use jsonPath class and and .get method()
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").
		header("Content-Type","application/json").
		body(PayLoad.payLoad()).when().post("/maps/api/place/add/json")
		          .then().log().all().assertThat().statusCode(200)
		          .body("scope",equalTo("APP"))
		          .header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		//System.out.println(response.);
		JsonPath js=new JsonPath(response);
		String placeId= js.get("place_id");
		System.out.println(placeId);
		
		given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"Noida, India\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all()
		.body("msg",equalTo("Address successfully updated"));
		
		String response2=given().queryParam("key","qaclick123").queryParam("place_id",placeId).header("Content-Type","application/json")
		.when().get("/maps/api/place/get/json")
		.then().header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		JsonPath js2=PayLoad.rawtoJson(response2);
		String address=js2.get("address");	
	    Assert.assertEquals(address, "Noida, India");
		//System.out.println("response is"+response2);
		
	    
	}

}
