package com.api.curd.function;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleWayPost {

	public Response getPostSetup() {
		// json file path
		File jsonData = new File("./src/main/resources/employee.json");
		// request
		RequestSpecification rqs = RestAssured.given();
		rqs.body(jsonData);
		rqs.header("Content-Type", "application/json");
		// response
		Response resp = rqs.post("https://httpbin.org/post");
		// resp.print();

		return resp;
	}

	public void basicValidation(Response resp) {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resp.statusCode() == 200);
		sa.assertTrue(resp.time() < 4000);
		sa.assertTrue(resp.contentType().contains("json"));
		boolean nullstatus = !resp.body().asString().equals(null);
		sa.assertTrue(nullstatus);
		sa.assertAll();
	}

	public void getKeyValidation(Response resp, String key) {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resp.body().asString().contains(key));
	}



	public void getjsonDatainArrayAndMap(Response resp,String  jsonKey,int index,String jsonMapKey,String emampleTableValue) {
		JsonPath jp = resp.jsonPath();

		// Array+ Map Value ==> projects
		List<Map<String, Object>> projects = jp.getList(jsonKey);
		
		Map<String, Object> eachProject =projects.get(index);//index
		
		String value= eachProject.get(jsonMapKey).toString();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(value.contains(emampleTableValue));

	}

	public void getjsonMapData(Response resp, String jsonKey, String mapKey, String Validationvalue) {
		JsonPath jp = resp.jsonPath();

		// Map Value
		Map<String, Object> data = jp.getMap(jsonKey);

		SoftAssert sa = new SoftAssert();
		sa.assertTrue(data.get(mapKey).toString().contains(Validationvalue));
	}

	public void getSimpleValueValidation(Response resp, String jsonKey, String Validationvalue) {
		JsonPath jp = resp.jsonPath();
		// simple value
		String keyvalue = jp.get(jsonKey).toString();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(keyvalue.contains(Validationvalue));
	}

	public void getJsonDataAsArray(Response resp, String jsonKey, int index, String Validationvalue) {
		JsonPath jp = resp.jsonPath();

		// Array Value
		List<String> skills = jp.getList(jsonKey);
		String keyvalue = skills.get(index).toString();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(keyvalue.contains(Validationvalue));

	}

}
