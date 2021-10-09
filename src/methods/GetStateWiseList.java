package methods;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class GetStateWiseList {
    ExtentHtmlReporter htmlReporter;
    
    ExtentReports extent;
    //helps to generate the logs in test report.
    ExtentTest test;

	Response response;
  @Test(dataProvider = "values")
  public void f(String n, String s) {
	  System.out.println(n+s);
	  response = given()
			  .header("user-agent"," Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")
			  .param("state_id",n)
			  .param("district_id", s)
			  .param("date", "2021-09-18")
			  .when()
			  .get("/api/v1/reports/v2/getVacPublicReports")
			  .then()
			  .extract().response();
	  System.out.println(response.statusCode());
	  test = extent.createTest("TC 01","Validate Response code");
	  Assert.assertEquals(200, response.statusCode());
	  test.pass("pass");

  }
  @Test(dependsOnMethods="f")
  public void printResponse() {
	  System.out.println(response.asPrettyString());
	  test = extent.createTest("TC_02","Validate the response structure");
	  Assert.assertTrue(true);
	  test.pass("Response has been printed");
  }
  @Test(dependsOnMethods="printResponse")
  public void validateJsonPath() {
	 JsonPath json = response.jsonPath();
	 List<String> vaccineDate=new ArrayList<String>();
	 vaccineDate = json.get("last30DaysVaccination.total");
	 System.out.println("---------------------");
	 test = extent.createTest("TC_03", "Validate the number of days");
	 Assert.assertEquals(vaccineDate.size(), 30);
  }
  @Test(dependsOnMethods = "validateJsonPath")
  public void ageWiseData() {
	  JsonPath json = response.jsonPath();
	  List<Integer> age18=new ArrayList<Integer>();
	  age18=json.get("weeklyVacAgeWiseReport.vac_18_45");
	  int sum=0;
	  for (int age : age18) {
		sum+=age;
	}
	 
	  test.pass("Total Vaccinated"+sum);
  }

  @DataProvider(name="values")
  public Object[][] dp() {
    return new Object[][] {
   //new Object[] { "2", "10" },
     new Object[] { "34", "664" },
    };
  }
  @BeforeTest
  public void beforeTest() {
	  RestAssured.baseURI="https://api.cowin.gov.in";
	  htmlReporter = new ExtentHtmlReporter("C:\\Users\\Ankit\\Desktop\\report.html");
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("html");
	  extent.flush();
  }

}
