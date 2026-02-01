package com.api.curd.function;

import java.io.File;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateDataSenddata {
	
	public void post() {
	//request
	File file = new File("./src/main/resources/Simple.json");
	RequestSpecification rsObjPost = RestAssured.given();
	rsObjPost.body(file);
	
	//Response
	Response resPost = rsObjPost.post("https://httpbin.org/post");
	//Test cases & Validation
			SoftAssert sf = new SoftAssert();
			sf.assertTrue(resPost.statusCode()==200);//int/double eqaul == & String : equals()
			sf.assertTrue(resPost.time()<2000);
			sf.assertTrue(resPost.contentType().contains("json"));
			sf.assertTrue(! resPost.body().asString().equals(null));
			
			sf.assertTrue(resPost.body().asString().contains("employeeId"));
			sf.assertTrue(resPost.body().asString().contains("name"));
			//value = json parser = JsonPath
			JsonPath jp = resPost.jsonPath();
			sf.assertTrue(jp.get("json.employeeId").equals("EMP001"));
			sf.assertTrue(jp.get("json.name").equals("David"));
			sf.assertAll();
	
	}
	
	public static void main(String[] args) {
		CreateDataSenddata obj = new CreateDataSenddata();
		obj.post();
	}

}
