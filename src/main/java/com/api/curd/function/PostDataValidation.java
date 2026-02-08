package com.api.curd.function;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostDataValidation {
	Response resp;

	public void getPostsetup() {// ****************

		// json file path
		File jsonData = new File("./src/main/resources/employee.json");
		// request
		RequestSpecification rqs = RestAssured.given();
		rqs.body(jsonData);
		rqs.header("Content-Type", "application/json");
		// response
		resp = rqs.post("https://httpbin.org/post");
		resp.print();

	}

	public void basicValidation() {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resp.statusCode() == 200);
		sa.assertTrue(resp.time() < 4000);
		sa.assertTrue(resp.contentType().contains("json"));
		boolean nullstatus = !resp.body().asString().equals(null);
		sa.assertTrue(nullstatus);
		sa.assertAll();
	}

	public void getKeyValidation(String key) {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(resp.body().asString().contains(key));
	}

	public void getSimpleSingleValueValidation(String singleDataKey, String Value) {

		JsonPath jp = resp.jsonPath();
		System.out.println(jp.get(singleDataKey).toString());
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(jp.get(singleDataKey).toString().contains(Value));
		sa.assertAll();
	}

	public void getArrayValueValidation(String singleDataKey, String value) {

		JsonPath jp = resp.jsonPath();
		List<String> myArrayBValue = jp.getList(singleDataKey);
		//"skills": ["Java", "Selenium", "RestAssured"],
		//index =  0,1,2
		// foreach =java 8 ==> old basic & enhance
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(myArrayBValue.get(0).contains(value));
		
		sa.assertAll();
	}

	public void getMapValueValidation(String singleDataKey,String key, String value) {

		JsonPath jp = resp.jsonPath();
		Map<String, Object> data = jp.getMap(singleDataKey);
		// foreach =java 8 ==> old basic & enhance
		SoftAssert sa = new SoftAssert();
		System.out.println(data);
		System.out.println(data.get("name"));
		sa.assertTrue(data.get(key).toString().equals(value));
		
		
		sa.assertAll();
	}

	public void getArrayWithMapValueValidation() {

		JsonPath jp = resp.jsonPath();
		List<Map<String,Object>> projects=jp.getList("json.projects");
		
		Map<String,Object> eachProject =projects.get(0);
		System.out.println(eachProject);
		System.out.println(eachProject.get("projectId"));
		System.out.println(eachProject.get("projectName"));
		SoftAssert sa = new SoftAssert();
		//sa.assertTrue(eachProject.get("projectId").toString().equals("101"));
		//sa.assertTrue(eachProject.get("projectName").toString().equals("Banking App"));
	
		
		
	}

	public static void main(String[] args) {
		PostDataValidation obj = new PostDataValidation();
		obj.getPostsetup();
		obj.basicValidation();
		obj.getKeyValidation("employeeId");
		obj.getKeyValidation("name");
		obj.getKeyValidation("projects");
		obj.getSimpleSingleValueValidation("json.name", "David");
		obj.getArrayValueValidation("json.skills", "Java");
		//obj.getMapValueValidation("json.data", "John");
		obj.getArrayWithMapValueValidation();
	}

}
