package methods;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class GetMethod {
	Response response;
  @Test
  public void execute() {
	   response = given()
			  				.header("User-Agent", "Chrome/39.0.2171.95")
			  				.when()
			  				.get("/echo")
			  				.then()
			  				.extract().response();
	  System.out.println(response.asString());
  }
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://reqbin.com";
  }

  @AfterTest
  public void afterTest() {
	  System.out.println(response.statusCode());
  }

}
