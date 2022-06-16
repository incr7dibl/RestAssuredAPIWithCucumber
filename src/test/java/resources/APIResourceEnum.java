package resources;

import pojo.AddPlace;

public enum APIResourceEnum {

	addPlaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");

	private String resource;

	APIResourceEnum(String resource) {
		this.resource = resource;

	}

	public String getResource() {
		return resource;
	}

}
