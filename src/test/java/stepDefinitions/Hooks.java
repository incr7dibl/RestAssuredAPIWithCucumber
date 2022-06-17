package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
import io.cucumber.java.bs.I.Is;

public class Hooks {
	@Before("@Deleteplace")
	public void beforeScenario() throws IOException {
		MapStepDefinitions mapsDef = new MapStepDefinitions();
		if (MapStepDefinitions.place_id == null) {
			mapsDef.add_place_payload_with_harsh_english_kolhapur("Baba", "Kolhapuri", "Kodoli");
			mapsDef.user_call_with_post_http_request("addPlaceAPI");
			mapsDef.verify_place_id_is_created_maps_to_harsh_using("Baba", "getPlaceAPI");
		}

	}
}
