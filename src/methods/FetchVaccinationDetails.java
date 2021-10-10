package methods;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class FetchVaccinationDetails {
  Response response;
	@Test
  public void sendGetRequest() {
		response = given()
					.header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36")
					.param("district_id","725")
					.param("date","10-10-2021")
					.when()
					.get("/api/v2/appointment/sessions/public/calendarByDistrict")
					.then()
					.extract().response();
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
  }
	@Test(dependsOnMethods = "sendGetRequest")
	public void dataValidation() {
		System.out.println(response.asPrettyString());
		JsonPath path = response.jsonPath();
		String centreId=path.getString("centers[1].center_id");
		System.out.println(centreId);
		
	}
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://cdn-api.co-vin.in/";
  }

  @AfterTest
  public void afterTest() {
  }

}
