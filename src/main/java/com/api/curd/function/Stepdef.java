package com.api.curd.function;

import java.io.File;

import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Stepdef {

	Response resp;

	@Given("setup post UPL & file json file")
	public void setup_post_upl_file_json_file() {
		File jsonData = new File("./src/main/resources/employee.json");
		String baseUrl = "https://httpbin.org/post";
	}

	@When("send request and get response for post")
	public void send_request_and_get_response_for_post() {

		SimpleWayPost obj = new SimpleWayPost();
		resp = obj.getPostSetup();
	}

	@Then("valide API basic valition")
	public void valide_api_basic_valition() {
		SimpleWayPost obj = new SimpleWayPost();
		obj.basicValidation(resp);
	}

	@Then("validate json key {string}")
	public void validate_json_key(String key) {
		System.out.println("Key from json =" + key);
		SimpleWayPost obj = new SimpleWayPost();
		obj.getKeyValidation(resp, key);
	}

	@Then("validate json value {string}")
	public void validate_json_value(String exampleTablevalue) {

		System.out.println("Feature file value = " + exampleTablevalue);
		//simple value
		SimpleWayPost obj = new SimpleWayPost();
		if (exampleTablevalue.contains("EMP001") || exampleTablevalue.contains("David")
				|| exampleTablevalue.contains("8000")) {
			if (exampleTablevalue.contains("EMP001")) {
				obj.getSimpleValueValidation(resp, "json.employeeId", exampleTablevalue);
			}
			if (exampleTablevalue.contains("David")) {
				obj.getSimpleValueValidation(resp, "json.name", exampleTablevalue);
			}
			if (exampleTablevalue.contains("8000")) {
				obj.getSimpleValueValidation(resp, "json.salary", exampleTablevalue);
			}

		}
		
		//Array
		if (exampleTablevalue.contains("Selenium")) {//array
			obj.getJsonDataAsArray(resp, "json.skills", 1, exampleTablevalue);
		}
		//Map
		if (exampleTablevalue.contains("John")) {//Map
			obj.getjsonMapData(resp, "json.data", "name", exampleTablevalue);
			
		}
		//Array+map
		if (exampleTablevalue.contains("Banking App") || exampleTablevalue.contains("101")) {//Array +Map
			if(exampleTablevalue.contains("Banking App")) {
				obj.getjsonDatainArrayAndMap(resp, "json.projects", 0, "projectName", exampleTablevalue);
				
			}
			if(exampleTablevalue.contains("101")) {
				obj.getjsonDatainArrayAndMap(resp, "json.projects", 0, "projectId", exampleTablevalue);
			}
			
			
		}
	}

}
