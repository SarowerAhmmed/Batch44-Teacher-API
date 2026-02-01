package com.api.curd.function;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RemoveData {

	public void deleteData() {

		// request & response
		Response resObj = RestAssured.delete("https://httpbin.org/delete");

		// Test cases & Validation
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resObj.statusCode() == 200);// int/double eqaul == & String : equals()
		sf.assertTrue(resObj.time() < 2000);
		sf.assertTrue(resObj.contentType().contains("json"));
		sf.assertTrue(!resObj.body().asString().equals(null));

		sf.assertTrue(resObj.body().asString().contains("json"));
		// value = json parser = JsonPath
		JsonPath jp = resObj.jsonPath();

		try {
			sf.assertTrue(jp.get("json").toString().equals("null"));
		} catch (Exception e) {
		}

		sf.assertAll();

	}

	public static void main(String[] args) {
		RemoveData obj = new RemoveData();
		obj.deleteData();
	}

}
