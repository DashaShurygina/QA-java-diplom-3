package testData.client.userData;

import org.apache.commons.lang3.RandomStringUtils;
import testData.getAndSet.CreateUserRequest;

public class GenerateUserData {
    private static final String DOMAIN = "@yandex.ru";

    public static String generateRandomEmail() {
        String email_beginning = RandomStringUtils.randomAlphabetic(7);
        return email_beginning + DOMAIN;
    }

    public static CreateUserRequest getRandomCreateUserRequest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(generateRandomEmail());
        createUserRequest.setPassword(RandomStringUtils.randomAlphabetic(7));
        createUserRequest.setName(RandomStringUtils.randomAlphabetic(7));

        return createUserRequest;
    }

    public static CreateUserRequest getDataCreatedUser(String email, String password, String name) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(email);
        createUserRequest.setPassword(password);
        createUserRequest.setName(name);

        return createUserRequest;
    }
}
