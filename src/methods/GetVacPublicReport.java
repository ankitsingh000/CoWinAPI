package methods;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class GetVacPublicReport {
	Response response;
	ExtentHtmlReporter htmlreport;
	ExtentReports extent;
	ExtentTest test;
	
  @Test
  public void sendGetRequest() {
	  test = extent.createTest("Sending GetRequest");
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  Date date = new Date();
	  System.out.println(dateFormat.format(date));
	  response = given()
			  		.header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36")
			  		.param("state_id", "")
			  		.param("district_id", "")
			  		.param("date",dateFormat.format(date))
			  		.when()
			  		.get("/api/v1/reports/v2/getVacPublicReports?state_id=&district_id=&date=2021-10-03")
			  		.then()
			  		.extract().response();
	  System.out.println(response.getStatusCode());
	  try {
		  Assert.assertEquals(response.getStatusCode(), 200);
		  test.pass("The response code has been validated");
	  }catch(AssertionError e){
		  test.fail("Wrong message");
	  }
	  
  }
  @Test
  public void validateDate() {
	  System.out.println(response.asPrettyString());
  }
  @BeforeTest
  public void beforeTest() {
	  htmlreport = new ExtentHtmlReporter("C:\\Users\\Ankit\\Desktop\\vaccinationstat.html");
	  extent = new ExtentReports();
	  extent.attachReporter(htmlreport);
	  RestAssured.baseURI="https://api.cowin.gov.in";
  }

  @AfterTest
  public void afterTest() {
	  extent.flush();
  }

}
