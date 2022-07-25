package Reqres.API;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import io.restassured.http.ContentType;
import java.io.File;

public class TugasReqresAPI {
    public static final String URL = "https://reqres.in";
    public static final String DIR = System.getProperty("user.dir");

    public static final String JSON_File = DIR+"/src/test/resources/json";
    public static String GET_SINGLE_USER = URL+"/api/users/{id}";
    public static String GET_SINGLE_USER_NOT_FOUND = URL+"/api/users/{id}";
    public static String POST_REGISTER_USER = URL+"/api/register";
    public static String PATCH_USER = URL+"api/users/{id}";
    public static String PUT_UPDATE_USER = URL+"/api/users/{id}";
    public static String DELETE_USER = URL+"/api/users/{id}";
    public static String POST_LOGIN = URL+"/api/login";

    @Step("Get single user with id")
    public void getSingleUser(int id){
        SerenityRest.given()
                .pathParam("id", id)
                .get(GET_SINGLE_USER);
    }

    @Step("Get single user not found")
    public void getSingleUserNotFound(int id){
        SerenityRest.given()
                .pathParam("id", id)
                .get(GET_SINGLE_USER_NOT_FOUND);
    }
    @Step("Post Register User")
    public void postRegister(File json) {
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

//    @Step("Patch user")
//    public void patchUser(File json, int idUser) {
//        SerenityRest.given()
//                .pathParam("id", idUser)
//                .contentType(ContentType.JSON)
//                .body(json);
//    }

    @Step("Put update user")
    public void put_UpdateUser(File json, String id){
        SerenityRest.given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }
    @Step("Delete user")
    public void deleteUser(String id) {
        SerenityRest.given()
                .pathParam("id", id);
    }
    @Step("Post login user")
    public void postLoginUser(File json){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }



}
