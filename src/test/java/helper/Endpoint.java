package helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Endpoint {

    public static final String host_gorest = "https://reqres.in";

    public static final String API_KEY = "reqres-free-v1";

    public static final String GET_LIST_USERS = host_gorest + "/api/users";

    public static final String CREATE_NEW_USERS = host_gorest + "/api/users";

    public static final String DELETE_USERS = host_gorest + "/api/users";
}
