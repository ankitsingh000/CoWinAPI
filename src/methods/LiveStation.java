package methods;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LiveStation {
	Response response;
	@Test
  public void fetchData() {
		response = given()
					//.urlEncodingEnabled(true)
					.param("jFromStationInput", "HOWRAH JN HWH")
					.param("jToStationInput","")
					.param("nHr", "2")
					.param("appLang", "en")
					.param("jStnName","")
					.param("jStation","")
					.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36")
					.header("Content-Type","application/x-www-form-urlencoded")
					//.cookie("JSESSIONID","lGHdwK28Ex0yYsMo9Im7CVUSoUe3gF1g4UmUGpj0.ntes_host1:host1_server1\"; TS0147a324=01ea7166bce369523aaa1d0d5e5e7eec6e65eff3f972c44317885f9791dcbd263be738b8402d3e8a1e04528129adb1926dad49584d4c9660f7ad3103acab14b8e222b380ce4be0546c9ced0aea748e3a6ea1ba3b60; _ga=GA1.3.1524626118.1633776857; _gid=GA1.3.335877484.1633776857; SERVERID=cch7fw87sfs2; TS0109848f=01ea7166bcd2c0f2077e7d28174e8be03eb183791b72c44317885f9791dcbd263be738b8402d3e8a1e04528129adb1926dad49584d6b0e0fae6b44dc29cb737f99d4877a57; TS00000000076=0801361661ab280005b01b09a86d5e1d48abd0b793f06a2d74908ac738d67abb160303d01da6e6679d35f768f0de114608b49c800309d000fa0319ccc6cd99ba36a6da2e10b4f356e637587410ff01796e013f37ab38455a4b7d5291215e21db2870a934b3bbae3b6b42062ad27ea8fa8e7c562688ee8a9c26c286a9aae2c6b0924bab10c1dc23ce7b140f7936581f94c6bea07feb5899130c8945263ff6dc4cb5783f133c393369c890d646b4163376620712e54a37dc1f449bc7d2268b03b8ae865de8c49657296667b4e44438459e5e8be5afd5b182cef2b3d161f249ec04fa06e73bfd77c95df398b5e41d6b9dd479470ba935a1343d43bef9c9143c67900838b1b17388649c; TSPD_101_DID=0801361661ab280005b01b09a86d5e1d48abd0b793f06a2d74908ac738d67abb160303d01da6e6679d35f768f0de114608b49c8003063800b49c4355ccd155b1cb377d669afb3292ba22b56039b4f06e0c1a49a04eededc95287bdfa1947bd522821aaf6a061aeff9e46b0c4ecf3c3cf; _gat_gtag_UA_89952719_2=1; __gads=ID=e52f8447b0a6ee7b:T=1633776859:S=ALNI_MYhVbIZ48eqfvH2OG4oPIjhOquu3w; TSbedae8c8027=0801361661ab2000bee5da6c2856e6fb99401653a5abf7f638383265578fe7cf3e9beb5e35805ab20845225320113000d7e69cd6dd42ab6fce3c8869d7fbcce73fafb02a1727d55171168062799cfad13f86c7ab64baa41dac4e08ed35a2bc83")
					.when()
					.post("https://enquiry.indianrail.gov.in/mntes/q?opt=LiveStation&subOpt=show")
					.then()
					.extract().response();
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
  }
  @BeforeTest
  public void beforeTest() {
	  //RestAssured.baseURI="https://enquiry.indianrail.gov.in/mntes/";
  }

  @AfterTest
  public void afterTest() {
  }

}
