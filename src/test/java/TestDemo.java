import org.codehaus.groovy.util.ComplexKeyHashMap;
import org.testng.annotations.Test;
//import static io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
/*
given()
when()
then()
 */
public class TestDemo {
	String id;
	@Test
	public void GetUser() {
		given()
		.urlEncodingEnabled(false)
		.header("x-api-key","reqres-free-v1")
		.when()
		.get("https://reqres.in/api/users/2")
		.then()
		.statusCode(200)
		.body("data.id", equalTo(2))
		.log().all();

	}
	//@Test(priority = 1)
	public void createUser()
	{

		Map<String, Object> data = new HashMap<>();
		data.put("name", "Taniya");
		data.put("job", "HR");

		id= given()
				.urlEncodingEnabled(false)
				.header("x-api-key","reqres-free-v1")
				.contentType(ContentType.JSON)
				.body(data)
				.when()
				.post("https://reqres.in/api/users")
				.jsonPath().getString("id");
		/* .then()
	      .log().all()
	      .statusCode(201);*/
	}
	//@Test(priority = 2,dependsOnMethods = {"createUser"})
	public void updateUser()
	{

		Map<String, Object> data = new HashMap<>();
		data.put("name", "Jana");
		data.put("job", "HR");
		given()
		.urlEncodingEnabled(false)
		.header("x-api-key","reqres-free-v1")
		.contentType(ContentType.JSON)
		.body(data)
		.when()
		.put("https://reqres.in/api/users"+id)
		.then()
		//.statusCode(201)
		.log().all();
	}
	//@Test(priority = 2,dependsOnMethods = {"createUser"})
	public void deleteUser()
	{
		given()
		.urlEncodingEnabled(false)
		.header("x-api-key","reqres-free-v1")
		.then()
		.when()
		.delete("https://reqres.in/api/users"+id)
		.then()
		.statusCode(204)
		.log().all();
	}
}
