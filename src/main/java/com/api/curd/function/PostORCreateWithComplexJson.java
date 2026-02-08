package com.api.curd.function;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostORCreateWithComplexJson {

	public static void main(String[] args) {
		File jsonFile = new File("./src/main/resources/employee.json");

		RequestSpecification rqs = RestAssured.given();
		// Test data in body

		rqs.body(jsonFile);
		rqs.header("Content-Type", "application/json");

		Response responseObj = rqs.post("https://httpbin.org/post");// send
		 responseObj.prettyPrint();
		System.out.println("===========Key =============");
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(responseObj.body().asString().contains("employeeId"));
		sa.assertTrue(responseObj.body().asString().contains("name"));
		sa.assertTrue(responseObj.body().asString().contains("skills"));
		sa.assertTrue(responseObj.body().asString().contains("projects"));

		// =================== value ===========================

		JsonPath jp = responseObj.jsonPath();// Json parser(break json)=JsonPath

		// ====================simple/single value ================
//		sa.assertTrue(jp.get("json.employeeId").toString().equals("EMP001"));
//		sa.assertTrue(jp.get("json.name").toString().equals("David"));
//		sa.assertTrue(jp.get("json.salary").equals(8000));
		
		System.out.println(jp.get("json.employeeId").toString());

		System.out.println("============Simple array============");

		// skill
		List<String> skillValues = jp.getList("json.skills");
		System.out.println(skillValues);
		
		System.out.println("============ only Map============");

		Map<String, Object> data = jp.getMap("json.data");
		System.out.println(data);
		System.out.println("============ Array+ Map============");
		
		List<Map<String,String>> projects = jp.getList("json.projects");
		System.out.println(projects);
		
		
		
		
		
		
		
		
		

	

//		sa.assertTrue(jp.get("json.skills").toString().contains("Java"));
//		sa.assertTrue(jp.get("json.skills").toString().contains("Selenium"));
//		sa.assertTrue(jp.get("json.skills").toString().contains("RestAssured"));
//
//		System.out.println("============Complex array============");
//
//		sa.assertTrue(jp.get("json.projects.projectId").toString().contains("P101"));
//		sa.assertTrue(jp.get("json.projects.projectName").toString().contains("Banking App"));
//
//		sa.assertAll();
//		// interview = can validate with map
//
//		// java 8= loop ==> foreach loop
//		List<Map<String, Object>> projects = responseObj.jsonPath().getList("json.projects");
//
//		projects.forEach(project -> System.out.println(project.get("projectId") + " -> " + project.get("projectName")));
//		List<String> fullJsonList = responseObj.jsonPath().getList("json.skills");
//
//		System.out.println(fullJsonList);
//		Map<String, Object> data = responseObj.jsonPath().getMap("json.data");
//
//		System.out.println(data);
//
//		System.out.println("*******String **********");
//		String dataString = responseObj.jsonPath().getString("data");
//		JsonPath dataJson = new JsonPath(dataString);
//		Map<String, Object> dataMap = dataJson.getMap("");
//		System.out.println(dataMap);
//
//		dataMap.forEach((k, v) -> {
//			System.out.println(k + " = " + v);
//		});
////
//		System.out.println("*******value in java MAP **********");
//		// JsonPath jp = responseObj.jsonPath();
//		// Json locator/xpath =get() ==> Object>> convert String =ToString()
//		// Map Json locator/xpath =getMap() ==> Java Map(K,V)
//		Map<String, Object> myMap = jp.getMap("json");// new HashMap<>()
//		System.out.println(myMap);
////each value from Map==> loop
//		myMap.forEach((k, v) -> {
//			System.out.println("Key = " + k);
//			System.out.println("Value = " + v);
//		});
//
	}
}
