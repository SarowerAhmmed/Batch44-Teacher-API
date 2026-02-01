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
	Response resObj;
	Response resObjdelete;
	Response resPost;
	Response resPut;

	@Given("put get url")
	public void put_get_url() {

	}

	@When("send get request & response")
	public void send_get_request_response() {
		resObj = RestAssured.get("https://httpbin.org/get");

	}

	@Then("validate get data with test cases")
	public void validate_get_data_with_test_cases() {
		// Test cases & Validation
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resObj.statusCode() == 200);// int/double eqaul == & String : equals()
		sf.assertTrue(resObj.time() < 2000);
		sf.assertTrue(resObj.contentType().contains("json"));
		sf.assertTrue(!resObj.body().asString().equals(null));

		sf.assertTrue(resObj.body().asString().contains("Host"));
		// value = json parser = JsonPath
		JsonPath jp = resObj.jsonPath();
		sf.assertTrue(jp.get("headers.Host").equals("httpbin.org"));
		sf.assertAll();

	}

	@Given("put post url")
	public void put_post_url() {

	}

	@When("send post request and get response")
	public void send_post_request_and_get_response() {
		// request
		File file = new File("./src/main/resources/Simple.json");
		RequestSpecification rsObjPost = RestAssured.given();
		rsObjPost.body(file);

		// Response
		resPost = rsObjPost.post("https://httpbin.org/post");

	}

	@Then("validate post data with test cases")
	public void validate_post_data_with_test_cases() {
		// Test cases & Validation
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resPost.statusCode() == 200);// int/double eqaul == & String : equals()
		sf.assertTrue(resPost.time() < 2000);
		sf.assertTrue(resPost.contentType().contains("json"));
		sf.assertTrue(!resPost.body().asString().equals(null));

		sf.assertTrue(resPost.body().asString().contains("employeeId"));
		sf.assertTrue(resPost.body().asString().contains("name"));
		// value = json parser = JsonPath
		JsonPath jp = resPost.jsonPath();
		sf.assertTrue(jp.get("json.employeeId").equals("EMP001"));
		sf.assertTrue(jp.get("json.name").equals("David"));
		sf.assertAll();

	}

	@Given("put put url")
	public void put_put_url() {

	}

	@When("send put request and get response")
	public void send_put_request_and_get_response() {

		// request
		File file = new File("./src/main/resources/update.json");
		RequestSpecification rsObjPut = RestAssured.given();
		rsObjPut.body(file);

		// Response
		resPut = rsObjPut.put("https://httpbin.org/put");
	}

	@Then("validate put data with test cases")
	public void validate_put_data_with_test_cases() {

		// Test cases & Validation
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resPut.statusCode() == 200);// int/double eqaul == & String : equals()
		sf.assertTrue(resPut.time() < 2000);
		sf.assertTrue(resPut.contentType().contains("json"));
		sf.assertTrue(!resPut.body().asString().equals(null));

		sf.assertTrue(resPut.body().asString().contains("employeeId"));
		sf.assertTrue(resPut.body().asString().contains("name"));
		// value = json parser = JsonPath
		JsonPath jp = resPut.jsonPath();
		sf.assertTrue(jp.get("json.employeeId").equals("EMP002"));
		sf.assertTrue(jp.get("json.name").equals("Jhon"));
		sf.assertAll();
	}

	@Given("put delete url")
	public void put_delete_url() {

	}

	@When("send delete request & response")
	public void send_delete_request_response() {
		resObjdelete = RestAssured.delete("https://httpbin.org/delete");

	}

	@Then("validate delete data with test cases")
	public void validate_delete_data_with_test_cases() {
		// Test cases & Validation
		SoftAssert sf = new SoftAssert();
		sf.assertTrue(resObjdelete.statusCode() == 200);// int/double eqaul == & String : equals()
		sf.assertTrue(resObjdelete.time() < 2000);
		sf.assertTrue(resObjdelete.contentType().contains("json"));
		sf.assertTrue(!resObjdelete.body().asString().equals(null));

		sf.assertTrue(resObjdelete.body().asString().contains("json"));
		// value = json parser = JsonPath
		JsonPath jp = resObjdelete.jsonPath();

		try {
			sf.assertTrue(jp.get("json").toString().equals("null"));
		} catch (Exception e) {
		}

		sf.assertAll();

	}
}
