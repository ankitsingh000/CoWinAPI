package methods;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
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

public class PostRequest {
	Response response;
	String txnId;
	 ExtentHtmlReporter report ;
	  ExtentReports extent;
  @Test
  public void postMethod() {
	 
	  JSONObject requestParams = new JSONObject();
	  requestParams.put("mobile", "7349403893");
	  requestParams.put("secret", "U2FsdGVkX18DIO8I64Dg5TOIOLq52Ithl7KRGRGxI0pYasBkbsxpR9STOhuol+U0JuTMLolYYGUknVlQEd6m4w==");
	  response = given()
			  	.header("user-agent"," Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
			  	.contentType(ContentType.JSON)
			  	.and()
			  	.body(requestParams.toJSONString())
			  	.when()
			  	.post("/api/v2/auth/generateMobileOTP")
			  	.then()
			  	.extract().response();
	  Assert.assertEquals(response.statusCode(), 200);
	  System.out.println(response.statusCode());
  }
  @Test(dependsOnMethods = "postMethod")
  public void extractTxnId() {
	  JsonPath json =response.jsonPath();
	  txnId = json.getString("txnId");
	  System.out.println(txnId);
  }
  @BeforeTest
  public void beforeTest() {
	  System.out.println(System.getProperty("user.dir"));
	  report = new ExtentHtmlReporter(System.getProperty("user.dir")+"\test-output\report1.html");
	  extent = new ExtentReports();
	  extent.attachReporter(report);
	  
	  RestAssured.baseURI="https://cdn-api.co-vin.in";
  }

  @AfterTest
  public void afterTest() {
  extent.flush();
  }

}
