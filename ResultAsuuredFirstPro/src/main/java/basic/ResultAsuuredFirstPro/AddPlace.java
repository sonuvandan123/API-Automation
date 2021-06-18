package basic.ResultAsuuredFirstPro;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddPlace {

	public static void main(String[] args)
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().queryParam("key","qaclick123").
		header("Content-Type","application/json").
		body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Indian House\",\r\n"
				+ "  \"phone_number\": \"(+91) 9876253621\",\r\n"
				+ "  \"address\": \"29, BraJil, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}").when().post("/maps/api/place/add/json")
		          .then().log().all().assertThat().statusCode(200)
		          .body("scope",equalTo("APP"))
		          .header("Server","Apache/2.4.18 (Ubuntu)");
		
	}

}
