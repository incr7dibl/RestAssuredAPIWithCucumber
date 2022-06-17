package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResourceEnum;
import resources.TestDataBuilder;
import resources.Utils;

public class MapStepDefinitions extends Utils {

	ResponseSpecification resSpec;
	TestDataBuilder builder = new TestDataBuilder();
	Response response;
	RequestSpecification res;
	static String place_id;

	@Given("Add place payload with {},{},{}")
	public void add_place_payload_with_harsh_english_kolhapur(String name, String langauge, String address)
			throws IOException {
		res = given().spec(getReqSpec()).body(builder.getPlace(name, langauge, address));
	}

	@When("user call {string} with post http request")
	public void user_call_with_post_http_request(String resource) {
		APIResourceEnum resourceName = APIResourceEnum.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().post(resourceName.getResource()).then().spec(resSpec).extract().response();
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int int1) {
		assertEquals(int1, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		assertEquals(value, getResponseValue(response, key));
	}

	@Then("verify place_Id is created maps to {} using {string}")
	public void verify_place_id_is_created_maps_to_harsh_using(String expectedName, String resource)
			throws IOException {
		place_id = getResponseValue(response, "place_id");
		res = given().spec(getReqSpec()).queryParam("place_id", place_id);

		APIResourceEnum resourceName = APIResourceEnum.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().get(resourceName.getResource()).then().spec(resSpec).extract().response();

		String actualName = getResponseValue(response, "name");

		assertEquals(expectedName, actualName);
	}

	@Given("DeleteplaceAPI payload")
	public void deleteplace_api_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(getReqSpec()).body(builder.getDeletePlacePayload(place_id));
	}

	@When("User {string} with place_ID")
	public void user_with_place_id(String string) {
		// Write code here that turns the phrase above into concrete actions
		APIResourceEnum resourceName = APIResourceEnum.valueOf(string);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post(resourceName.getResource()).then().spec(resSpec).extract().response();
	}

}
