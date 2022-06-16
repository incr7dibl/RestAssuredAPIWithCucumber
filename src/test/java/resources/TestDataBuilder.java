package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuilder {

	public AddPlace getPlace(String name, String langauge, String address) {
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress(address);
		place.setWebsite("google.com");
		place.setLanguage(langauge);
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		place.setTypes(types);
		place.setLocation(location);

		return place;

	}
}
