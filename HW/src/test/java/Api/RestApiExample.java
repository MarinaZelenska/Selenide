package Api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


public class RestApiExample {

    @Test
    public void getAllEmployeesTest() {
        given()
                .log().all()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("1", "2", "3"));

    }


    @Test
    public void negativeGetAllEmployeesTest() {
        given()
                .log().all()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", not("333"));

    }


    @Test
    public void getEmployeesByIdTest() {
        Employee expectedEmployee = new Employee("Tiger Nixon", 320800, 61, "");
        EmployeeResponse responseExpected = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .log().all()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, responseExpected);

    }

    @Test
    public void negativeGetEmployeesByIdTest() {
        Employee expectedEmployee = new Employee("Tiger Nixon", 50000, 61, "");
        EmployeeResponse responseExpected = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponse response = given()
                .log().all()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertNotEquals(response, responseExpected);

    }


    @Test
    public void postEmployeesTest() {
        PostEmployeeModel employeeModel = new PostEmployeeModel("QWERTY", "500000", "27");
        EmployeeResponse responseExpected = new EmployeeResponse("success", new Employee(), "Successfully! Record has been added.");
        EmployeeResponse response = given()
                .log().all()
                .with()
                .body(employeeModel)
                .post("http://dummy.restapiexample.com/api/v1/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(response, responseExpected);


    }


    @Test
    public void negativePostEmployeesTest() {
        PostEmployeeModel employeeModel = new PostEmployeeModel("QWERTY", "500000", "27");
        EmployeeResponse responseExpected = new EmployeeResponse("success", new Employee(), "Successfully! Record has been added.");
        given()
                .log().all()
                .with()
                .body(employeeModel)
                .post("http://dummy.restapiexample.com/api/v7/create")
                .then()
                .log().all()
                .assertThat()
                .statusCode(405);
    }

    @Test
    public void deleteAllEmployeesTest() {
        given()
                .log().all()
                .when()
                .delete("http://dummy.restapiexample.com/api/v1/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data", equalTo("2"))
                .body("message", equalTo("Successfully! Record has been deleted"));


    }


    @Test
    public void negativeDeleteAllEmployeesTest() {
        given()
                .log().all()
                .when()
                .delete("http://dummy.restapiexample.com/api/v8/delete/test")
                .then()
                .log().all()
                .assertThat()
                .statusCode(405);



    }

    @Test
    public void putEmployeesTest() {
        PostEmployeeModel employeeModel = new PostEmployeeModel("QWERTY", "500000", "27");
        given()
                .log().all()
                .with()
                .body(employeeModel)
                .put("http://dummy.restapiexample.com/api/v1/update/21")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Successfully! Record has been updated."));

    }


    @Test
    public void negativePutEmployeesTest() {
        PostEmployeeModel employeeModel = new PostEmployeeModel("QWERTY", "500000", "27");
        given()
                .log().all()
                .with()
                .body(employeeModel)
                .put("http://dummy.restapiexample.com/api/v1/delete/21")
                .then()
                .log().all()
                .assertThat()
                .statusCode(405);


    }









}
