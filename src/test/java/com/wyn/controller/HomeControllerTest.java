package com.wyn.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {
	
	/** The port. */
	@LocalServerPort
	private int port;

	/** The rest documentation. */
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	
	private RequestSpecification documentationSpec;
	
	@Before
	public void setup() {

		String basePath = System.getProperty("server.base");
		if (basePath == null) {
			basePath = "/product";
		}
		RestAssured.basePath = basePath;

		String baseHost = System.getProperty("server.host");
		if (baseHost == null) {
			baseHost = "http://localhost";
		}
		RestAssured.baseURI = baseHost;
		documentationSpec = new RequestSpecBuilder().addFilter(documentationConfiguration(restDocumentation)).build();
	}


	@Test
	public void testGreeting() throws Exception {
		
		Response response = given(documentationSpec).port(port)
				.filter(document("home", preprocessRequest(prettyPrint()), // <1>
						preprocessResponse(prettyPrint()),
						pathParameters(parameterWithName("userName").description("Name of user"))))
				.when().get("/{userName}","Yogi");
		assertEquals(HttpStatus.OK.value(), response.statusCode());
	}
	
}
