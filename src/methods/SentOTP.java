package methods;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class SentOTP {
	Response response; 
	ExtentHtmlReporter report;
	ExtentReports extent;
	ExtentTest test;
  @Test
  public void sendOtp() {
	  JSONObject requestParameters = new JSONObject();
	  requestParameters.put("mobile", "7044496717");
	  requestParameters.put("secret", "U2FsdGVkX18XI8Ha8IHyUhqxKf7ztD7EZwcb9KAehdCwQQXwN84jK4lAywgntuC9I4wjKayQGk9ZsSb2BQfkxQ==");
	  response = given()
			  	.header("user-agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
			  	.contentType(ContentType.JSON)
			  	.and()
			  	.body(requestParameters.toJSONString())
			  	.when()
			  	.post("/api/v2/auth/generateMobileOTP")
			  	.then()
			  	.extract().response();
	  test = extent.createTest("Validate the status code");
	  Assert.assertEquals(response.getStatusCode(), 20);
	  test.pass("The test case has pass");
			  	
  }
  @Test(dependsOnMethods = "sendOtp", alwaysRun = true)
  public void fetchTxnId() {
	  JsonPath jsonPath = response.jsonPath();
	  String txnId = jsonPath.getString("txnId");
	  System.out.println(txnId);
  }
  @BeforeTest
  public void beforeTest() {
	  System.out.println(System.getProperty("user.dir"));
	  report = new ExtentHtmlReporter("C:\\Users\\Ankit\\Desktop\\report.html");
	  extent = new ExtentReports();
	  extent.attachReporter(report);
	  RestAssured.baseURI="https://cdn-api.co-vin.in";
  }

  @AfterTest
  public void afterTest() {
	  extent.flush();
	  
  }

}
