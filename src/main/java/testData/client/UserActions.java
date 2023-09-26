package testData.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import testData.getAndSet.CreateUserRequest;
import testData.getAndSet.LoginUserRequest;


import static io.restassured.RestAssured.given;

public class UserActions extends BaseClient {
    private static final String userBaseUri = "/api/auth";
    @Step("Создать пользователя")
    public static ValidatableResponse create(CreateUserRequest createUserRequest) {
        return given()
                .spec(getSpec())
                .body(createUserRequest)
                .when()
                .post(userBaseUri + "/register")
                .then();
    }
    @Step("Авторизоваться под пользователем")
    public static ValidatableResponse login(LoginUserRequest loginUserRequest) {
        return given()
                .spec(getSpec())
                .body(loginUserRequest)
                .when()
                .post(userBaseUri + "/login")
                .then();
    }
    @Step("Удалить пользователя")
    public static ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(userBaseUri + "/user")
                .then();
    }
}
