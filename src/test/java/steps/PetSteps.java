package steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import petPojo.Category;
import petPojo.Pet;
import petPojo.Tag;
import utils.SpecBuilder;

public class PetSteps {

    RequestSpecification reqs;
    Response res;

    // Pet ID used for POST, Upload and GET chaining
    int petId;

    @Given("create pet api payload")
    public void create_pet_api_payload() {

        petId = 7;

        Pet obj = new Pet();

        obj.setId(petId);

        Category category = new Category();
        category.setId(0);
        category.setName("String");
        obj.setCategory(category);

        obj.setName("Doggesh");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("Url1String");
        obj.setPhotoUrls(photoUrls);

        Tag tag1 = new Tag();
        tag1.setId(0);
        tag1.setName("string");

        List<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        obj.setTags(tags);

        obj.setStatus("available");

        reqs = given()
                .spec(SpecBuilder.reqSpec())
                .header("Content-Type", "application/json")
                .body(obj);
    }


    @When("user calls post request to crate pet")
    public void user_calls_post_request_to_crate_pet() {

        res = reqs
                .when()
                .post("/pet");
    }


    @Then("He should get status code as {int}")
    public void he_should_get_status_code_as(Integer int1) {

        // 1. STATUS CODE VALIDATION
        res.then().statusCode(int1);

        // 2. HEADER VALIDATION
        res.then()
                .header("Content-Type", containsString("application/json"));

        // 3. RESPONSE NOT NULL
        /*    Assert.assertNotNull(res);

        // 4. ROOT LEVEL VALIDATION (JSON PATH)
        res.then()
            .body("id", notNullValue())
            .body("name", equalTo("Doggesh Bhai"))
            .body("status", equalTo("available"));

        // 5. DATA TYPE VALIDATION
        res.then()
            .body("id", instanceOf(Long.class))
            .body("name", instanceOf(String.class))
            .body("photoUrls", instanceOf(List.class));

        // 6. NESTED OBJECT VALIDATION (CATEGORY)
        res.then()
            .body("category", notNullValue())
            .body("category.id", greaterThanOrEqualTo(0))
            .body("category.name", not(emptyOrNullString()));

        // 7. ARRAY VALIDATION (photoUrls)
        res.then()
            .body("photoUrls", not(empty()))
            .body("photoUrls[0]", notNullValue());

        // 8. LIST OF OBJECTS VALIDATION (tags)
        res.then()
            .body("tags", not(empty()))
            .body("tags[0].id", greaterThanOrEqualTo(0))
            .body("tags[0].name", not(emptyOrNullString()));

        // 9. FULL RESPONSE MAPPING (POJO)
        Pet resObj = res.as(Pet.class);

        String name  = resObj.getTags().get(0).getName();

        System.out.println( resObj.getPhotoUrls().get(0));


        // 10. POJO LEVEL ASSERTIONS
        Assert.assertNotNull(resObj);
        Assert.assertNotNull(resObj.getId());
        Assert.assertEquals(resObj.getName(), "Doggesh");
        Assert.assertEquals(resObj.getStatus(), "available");

        Assert.assertNotNull(resObj.getCategory());
        Assert.assertTrue(resObj.getCategory().getId() >= 0);

        Assert.assertNotNull(resObj.getPhotoUrls());
        Assert.assertTrue(resObj.getPhotoUrls().size() > 0);

        Assert.assertNotNull(resObj.getTags());
        Assert.assertTrue(resObj.getTags().size() > 0);

        // 11. EXTRA VALIDATIONS (EDGE / ROBUST)

        // Response time
        Assert.assertTrue(res.getTime() < 3000, "Response time is too high");

        // Response body not empty
        Assert.assertTrue(res.getBody().asString().length() > 0);*/

        // Logging (useful for debugging)
        System.out.println("Response Body:");
        res.prettyPrint();
    }


    @Given("create file & metadata payload")
    public void create_file_metadata_payload() {

        reqs = given()
                .spec(SpecBuilder.reqSpec())
                .formParam("additionalMetadata", "sample meta data")
                .multiPart(
                        "file",
                        new File("src/test/resources/Test Data/pet.jpg")
                )
                .pathParam("PetId", petId);
    }


    @When("user calls post request to update the pet data")
    public void user_calls_post_request_to_update_the_pet_data() {

        res = reqs
                .when()
                .post("/pet/{PetId}/uploadImage");
    }


    @When("user hits get request using pet id")
    public void user_hits_get_request_using_pet_id()
    {
    	petId = 7;

        res = given()
                .spec(SpecBuilder.reqSpec())
                .pathParam("id", petId)
                .when()
                .get("/pet/{id}");
    }


    @Then("he should get pet details")
    public void he_should_get_pet_details() {

        res.then().statusCode(200);

        Pet petName = res.as(Pet.class);
        
        System.out.println(petName + "----------))))))))))");

      Assert.assertEquals(petName.getName(),"Doggesh");
    }
}