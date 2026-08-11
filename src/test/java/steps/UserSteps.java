package steps;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.github.javafaker.Faker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import userPojo.Users;
import utils.DateTimeUtil;
import utils.UserSpecBuilder;

public class UserSteps {

    RequestSpecification reqs;
    Response res;

    String userName = "NewUser1";
    String updatedUserName;

    Users user1;
    Faker faker = new Faker();


    @Given("the user creation payload is prepared")
    public void the_user_creation_payload_is_prepared() throws FileNotFoundException, IOException {

        user1 = new Users();

        int id = faker.number().numberBetween(1, 10000);
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16);
        String phone = faker.phoneNumber().cellPhone();
        int userStatus = faker.number().numberBetween(0, 5);

        user1.setId(id);
        user1.setUsername(userName);
        user1.setFirstname(firstname);
        user1.setLastname(lastname);
        user1.setEmail(email);
        user1.setPassword(password);
        user1.setPhone(phone);
        user1.setUserStatus(userStatus);

        List<Users> users = new ArrayList<>();
        users.add(user1);

        reqs = given()
                .spec(UserSpecBuilder.reqSpec())
                .header("Content-Type", "application/json")
                .body(users)
                .log()
                .all();
    }


    @When("the user sends a POST request to create the user")
    public void the_user_sends_a_post_request_to_create_the_user() {

        res = reqs
                .when()
                .post("/createWithList");

        res.then().log().all();
    }


    @Then("the post response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {

        res.then()
                .statusCode(expectedStatusCode);

        res.then()
                .header("Content-Type", "application/json");
    }


    @When("the user sends a GET request using the username")
    public void the_user_sends_a_get_request_using_the_username() throws FileNotFoundException, IOException {

        res = given()
                .spec(UserSpecBuilder.reqSpec())
                .pathParam("userName", userName)
                .header("Accept", "application/json")
                .when()
                .get("/{userName}");
    }


    @Then("the user details should be retrieved successfully")
    public void the_user_details_should_be_retrieved_successfully() {

        res.then()
                .statusCode(200);
    }


    @Given("the user update payload is prepared with a new username")
    public void the_user_update_payload_is_prepared_with_a_new_username() throws FileNotFoundException, IOException {

        updatedUserName =
                "Update_" + DateTimeUtil.getCurrentDateTime();

        user1 = new Users();

        int id = faker.number().numberBetween(1, 10000);
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16);
        String phone = faker.phoneNumber().cellPhone();
        int userStatus = faker.number().numberBetween(0, 5);

        user1.setId(id);
        user1.setUsername(updatedUserName);
        user1.setFirstname(firstname);
        user1.setLastname(lastname);
        user1.setEmail(email);
        user1.setPassword(password);
        user1.setPhone(phone);
        user1.setUserStatus(userStatus);

        reqs = given()
                .spec(UserSpecBuilder.reqSpec())
                .header("Content-Type", "application/json")
                .pathParam("userName", userName)
                .body(user1)
                .log()
                .all();
    }


    @When("the user sends a PUT request to update the user")
    public void the_user_sends_a_put_request_to_update_the_user() {

        res = reqs
                .when()
                .put("/{userName}");

        res.then().log().all();
    }


    @Then("the response status code should be 200")
    public void the_response_status_code_should_be_200() {

        res.then()
                .statusCode(200);

        userName = updatedUserName;
    }


    @When("the user sends a DELETE request for the user")
    public void the_user_sends_a_delete_request_for_the_user() throws FileNotFoundException, IOException {

        res = given()
                .spec(UserSpecBuilder.reqSpec())
                .pathParam("userName", userName)
                .when()
                .delete("/{userName}");

        res.then().log().all();
    }


    @Then("the response status code should be 200 and the user should be deleted")
    public void the_response_status_code_should_be_200_and_the_user_should_be_deleted() {

        res.then()
                .statusCode(200);

        String deletedUsername =
                res.jsonPath().getString("message");

        Assert.assertEquals(
                userName,
                deletedUsername,
                "user deletion failed"
        );
    }
}
