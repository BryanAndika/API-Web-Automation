package helper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiUtils {

    private static final String BASE_HOST = Endpoint.host_gorest;
    // Ambil API Key dari Endpoint
    private static final String API_KEY = Endpoint.API_KEY;

    public static Response get(String path) {
        return RestAssured.given()
                // --- Masukkan Header API Key di sini ---
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_HOST + path);
    }

    public static Response post(String path, Object body) {
        return RestAssured.given()
                // --- Masukkan Header API Key di sini ---
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(BASE_HOST + path);
    }

    // ... method delete juga harus memiliki header yang sama
}
