package Reqres;


import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import Reqres.ReqresAPI;
import Reqres.ReqresResponses;
import net.thucydides.core.annotations.Steps;
import net.serenitybdd.rest.SerenityRest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.not;


public class ReqresStepDef {
    @Steps
    ReqresAPI reqresAPI;


    @Given("Get list user with parameter {string}")
    public void getListUserWithParameter(String page) {
        reqresAPI.getListUser(page);
    }

    @Then("Status code should be {int} OK")
    public void statusCodeShouldBeOK(int ok) {
        SerenityRest.then().statusCode(200);
    }
    @And("Response body should contain first name {string} and last name {string}")
    public void responseBodyShouldContainFirstNameAndLastName(String firstName, String lastName) {
        SerenityRest.then().body(ReqresResponses.FIRST_NAME,equalTo(firstName));
        SerenityRest.then().body(ReqresResponses.LAST_NAME,equalTo(lastName));
    }

    //POST create new user - positive case
    @Given("Post create new user with valid json file")
    public void postCreateNewUserWithValidJsonFile() {
        File jsonFiles = new File(ReqresAPI.DIR+"/src/test/resources/Json/CreateUser.json");
        reqresAPI.postCreateUser(jsonFiles);
    }
    @When("Send request post create user")
    public void sendRequestPostCreateUser() {
        SerenityRest.when().post(ReqresAPI.POST_CREATE_USER);
    }

//    @When("Send request post create user")
//    public void sendRequestCreateNewUser() {
//
//        SerenityRest.when().post(reqresAPI.POST_CREATE_NEW_USER);
//    }
    @Then("Status code should be {int} Created")
    public void statusCodeShouldBeCreated(int created) {
        SerenityRest.then().statusCode(201);
//        Object obj = new JSONParser().parse(new FileReader(Constant.JSON_DATA +"/CreateUser.json"));
//        JSONObject jsonObject = (JSONObject) obj;
//        String nameJson = (String) jsonObject.get("name");
//        String jobJson = (String) jsonObject.get("job");
//        SerenityRest.then().statusCode(201)
//                .body("name",equalTo(nameJson))
//                .body("job",equalTo(jobJson));
    }

    @And("Response body should contain name {string} and job {string}")
    public void responseBodyShouldContainNameAndJob(String name, String job) {
        SerenityRest.then().body(ReqresResponses.NAME,equalTo(name));
        SerenityRest.then().body(ReqresResponses.JOB,equalTo(job));
    }

    @Given("Put update user with id {string} and with valid json file")
    public void putUpdateUserWithIdAndWithValidJsonFile(String id) {
        File jsonFiles = new File(ReqresAPI.DIR+"/src/test/resources/Json/UpdateUser.json");
        reqresAPI.putUpdateUser(jsonFiles, id);
    }

    @When("Send request put update user")
    public void sendRequestPutUpdateUser() {
        SerenityRest.when().put(ReqresAPI.PUT_UPDATE_USER);
    }

    @Given("Delete user with id {int}")
    public void deleteUserWithId(int id) {
        reqresAPI.deleteUser(id);

    }

    @When("Send request delete user")
    public void sendRequestDeleteUser() {
        SerenityRest.when().delete(ReqresAPI.DELETE_USER);
    }

    @Then("Status code should be {int} No content")
    public void statusCodeShouldBeNoContent(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }


//    @And("Validation Json schema")
//    public void validationJsonSchema() throws IOException, ParseException {
//        Object obj = new JSONParser().parse(new FileReader(Constant.JSON_DATA +"/JSONValidation.json"));
//        JSONObject jsonSchema = (JSONObject) obj;
//        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(String.valueOf(jsonSchema)));
//    }



}
