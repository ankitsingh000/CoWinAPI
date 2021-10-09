package methods;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;

public class CowinLiveStatus {
	Response response;
  @Test
  public void f() {
	  response = given()
			  	.header("user-agent"," Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
	  			.when()
	  			.get("/api/v1/reports/getLiveVaccination")
	  			.then()
	  			.extract().response();
	  System.out.println(response.asString());
  }
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://cdn-api.co-vin.in";
  }

}
