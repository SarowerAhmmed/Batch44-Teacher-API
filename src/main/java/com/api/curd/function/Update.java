package com.api.curd.function;

import java.io.File;

import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Update {
	
	public void updateData() {
		//request
		File file = new File("./src/main/resources/update.json");
		RequestSpecification rsObjPut = RestAssured.given();
		rsObjPut.body(file);
		
		//Response
		Response resPut = rsObjPut.put("https://httpbin.org/put");
		//Test cases & Validation
				SoftAssert sf = new SoftAssert();
				sf.assertTrue(resPut.statusCode()==200);//int/double eqaul == & String : equals()
				sf.assertTrue(resPut.time()<2000);
				sf.assertTrue(resPut.contentType().contains("json"));
				sf.assertTrue(! resPut.body().asString().equals(null));
				
				sf.assertTrue(resPut.body().asString().contains("employeeId"));
				sf.assertTrue(resPut.body().asString().contains("name"));
				//value = json parser = JsonPath
				JsonPath jp = resPut.jsonPath();
				sf.assertTrue(jp.get("json.employeeId").equals("EMP002"));
				sf.assertTrue(jp.get("json.name").equals("Jhon"));
				sf.assertAll();
		
		
	}
	
	public static void main(String[] args) {
		Update obj = new Update();
		obj.updateData();
	}

}
