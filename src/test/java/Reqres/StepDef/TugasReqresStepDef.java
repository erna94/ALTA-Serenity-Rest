package Reqres.StepDef;

import Reqres.API.TugasReqresAPI;
import Reqres.Responses.TugasReqresResponses;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class TugasReqresStepDef {
    @Steps
    TugasReqresAPI tugasReqresAPI;

    @Given("Get single user with id {int}")
    public void getSingleUserWithId(int id) {
        tugasReqresAPI.getSingleUser(id);
    }

    @When("Send request get single user")
    public void sendRequestGetSingleUser() {
        SerenityRest.when().get(TugasReqresAPI.GET_SINGLE_USER);
    }

    @Then("Should return status code {int} OK")
    public void shouldReturnStatusCodeOK(int ok) {
        SerenityRest.then().statusCode(200);
    }

    @And("Get single user assert json validation")
    public void getSingleUserAssertJsonValidation() {
       // SerenityRest.then().body(TugasReqresResponses.ID,equalTo(id));
        File jsonFile = new File(TugasReqresAPI.JSON_File+"/GetSingleUserJsonValidation.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }
//    @And("Get single user assert json validation")
//    public void responseBodyShouldContainDataUserWithId(int id) {
//        // SerenityRest.then().body(TugasReqresResponses.ID,equalTo(id));
//
//    }

    //  Scenario : Get single user not found
    @When("Send request get single user with id not found")
    public void sendRequestGetSingleUserWithIdNotFound() {
        SerenityRest.when().get(TugasReqresAPI.GET_SINGLE_USER_NOT_FOUND);
    }

    @Then("Should return status code {int} not found")
    public void shouldReturnStatusCodeNotFound(int id) {
        SerenityRest.then().statusCode(404);
    }

    //Post Register
    @Given("Post register user")
    public void postRegisterUser() {
        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/CreateRegisterUser.json");
        tugasReqresAPI.postRegister(jsonFiles);
    }

    @When("Send request post register")
    public void sendRequestPostRegister() {
        SerenityRest.when().post(TugasReqresAPI.POST_REGISTER_USER);
    }

//    @Then("Status code should be {int} OK")
//    public void statusCodeShouldBeCreated(int created) {
//        SerenityRest.then().statusCode(201);
//    }
    @And("Post create register assert json validation")
    public void postCreateRegisterAssertJsonValidation() {
        File jsonFile = new File(TugasReqresAPI.JSON_File+"/PostRegisterUserJsonValidation.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }
    @And("Response body should contain id {int} and token {string}")
    public void responseBodyShouldContainIdAndToken(int id, String token) {
        SerenityRest.then()
                .body(TugasReqresResponses.ID_User,equalTo(id))
                .body(TugasReqresResponses.TOKEN,equalTo(token));
    }

    @Given("Put update user with id {string} and with UpdateUserWithoutName.json file")
    public void putUpdateUserWithIdAndWithUpdateUserWithoutNameJsonFile(String id) {
        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/UpdateUserWithoutName.json");
        tugasReqresAPI.put_UpdateUser(jsonFiles, id);
    }

    @When("Send request put for update user")
    public void sendRequestPutForUpdateUser() {
        SerenityRest.when().put(TugasReqresAPI.PUT_UPDATE_USER);;
    }

    @And("response body should contain job {string}")
    public void responseBodyShouldContainJob(String job) {
        SerenityRest.then().body(TugasReqresResponses.JOB,equalTo(job));
    }
    @Given("Put update user with id {string} and with UpdateUserWithoutJob.json file")
    public void putUpdateUserWithIdAndWithUpdateUserWithoutJobJsonFile(String id) {
        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/UpdateUserWithoutJob.json");
        tugasReqresAPI.put_UpdateUser(jsonFiles, id);
    }
    @And("response body should contain name {string}")
    public void responseBodyShouldContainName(String name) {
        SerenityRest.then().body(TugasReqresResponses.NAME,equalTo(name));
    }

    @Given("Delete user with parameter {string}")
    public void deleteUserWithParameter(String parameter) {
        tugasReqresAPI.deleteUser(parameter);
    }

    @When("Send request delete user")
    public void sendRequestDeleteUser() {
        SerenityRest.when().delete(TugasReqresAPI.DELETE_USER);
    }

    @Given("Post login user with valid data on json file")
    public void postLoginUserWithValidDataOnJsonFile() {
        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/LoginWithValidEmailAndPassword.json");
        tugasReqresAPI.postLoginUser(jsonFiles);
    }
    @When("Send request post login user")
    public void sendRequestPostLoginUser() {
        SerenityRest.when().post(TugasReqresAPI.POST_LOGIN);

    }
    @And("post login user assert json validation")
    public void postLoginUserAssertJsonValidation() {
        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/LoginJsonValidation.json");
        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFiles));
    }

    @Given("Post login user with invalid password on json file")
    public void requestPostLoginUserWithInvalidPasswordOnJsonFile() {
        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/LoginWithValidEmailAndInvalidPassword.json");
        tugasReqresAPI.postLoginUser(jsonFiles);
    }

    @Given("Post login user with invalid data on json file")
    public void postLoginUserWithInvalidDataOnJsonFile() {
        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/LoginWithInvalidEmailAndInvalidPassword.json");
        tugasReqresAPI.postLoginUser(jsonFiles);
    }

    @Then("Should return status code {int} bad request")
    public void shouldReturnStatusCodeBadRequest(int id) {
        SerenityRest.then().statusCode(404);
    }


    //Patch Update User
//    @Given("Patch user with id {int}")
//    public void patchUserWithId(int id) {
//        File jsonFiles = new File(TugasReqresAPI.JSON_File+"/PatchUser.json");
//        tugasReqresAPI.patchUser(jsonFiles, id);
//    }
//
//    @When("Send request patch user")
//    public void sendRequestPatchUser() {
//        SerenityRest.when().patch(TugasReqresAPI.PATCH_USER);
//    }
//
//    @And("Response body should contain name {string} and job {string}")
//    public void responseBodyShouldContainNameAndJob(String name, String job) {
//        SerenityRest.then()
//                .body(TugasReqresResponses.NAME,equalTo(name))
//                .body(TugasReqresResponses.JOB,equalTo(job));
//    }
//
//    @And("Patch user assert json validation")
//    public void patchUserAssertJsonValidation() {
//        File jsonFile = new File(TugasReqresAPI.JSON_File+"/PatchUserJsonValidation.json");
//        SerenityRest.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
//    }




//    @And("Should get empty json for user with id {int}")
//    public void failedGetSingleUserWithIdId(int id) {
//        Object array[] = new Object[0];
//        new MatcherAssert().assertThat(array, Matchers.emptyArray());
//    }

}
