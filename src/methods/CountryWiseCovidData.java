package methods;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class CountryWiseCovidData {

	public static void main(String[] args) {
		RestAssured.baseURI="https://covid-193.p.rapidapi.com";
		Response response = given()
							.header("X-RapidAPI-Host","covid-193.p.rapidapi.com")
							.header("X-RapidAPI-Key","e6b23cb427msh4c0dda0a151bfa3p13eaa5jsn021042a813dd")							
							.when()
							.get("/statistics")
							.then()
							.extract().response();
		System.out.println(response.asPrettyString());
									

	}

}
