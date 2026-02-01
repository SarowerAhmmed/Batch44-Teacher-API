package com.api.curd.function;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadData {

	public void get() {

		//request & response
		Response resObj =RestAssured.get("https://httpbin.org/get");
		
		//Test cases & Validation
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resObj.statusCode()==200);//int/double eqaul == & String : equals()
		sf.assertTrue(resObj.time()<2000);
		sf.assertTrue(resObj.contentType().contains("json"));
		sf.assertTrue(! resObj.body().asString().equals(null));
		
		sf.assertTrue(resObj.body().asString().contains("Host"));
		//value = json parser = JsonPath
		JsonPath jp = resObj.jsonPath();
		sf.assertTrue(jp.get("headers.Host").equals("httpbin.org"));
		sf.assertAll();
	}

	public static void main(String[] args) {
		ReadData obj = new ReadData();
		obj.get();
	}

}
