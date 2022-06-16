package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	private static RequestSpecification reqSpec;

	public RequestSpecification getReqSpec() throws IOException {
		if (reqSpec == null) {
			PrintStream logStream = new PrintStream(new FileOutputStream("logs.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseUri"))
					.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(logStream))
					.addFilter(ResponseLoggingFilter.logResponseTo(logStream)).build();
			return reqSpec;
		}
		return reqSpec;
	}

	public String getGlobalProperty(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\mohit\\eclipse-workspace\\APICucumberBDDFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public String getResponseValue(Response reponse, String key) {

		String resp = reponse.asString();

		JsonPath jsonPath = new JsonPath(resp);
		return jsonPath.getString(key);
	}
}
