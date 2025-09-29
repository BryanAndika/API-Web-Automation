package helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static helper.Utility.generateRandomEmail;

public class Models {

    private static RequestSpecification request;

    // Konstanta untuk API Key Reqres.in
    private static final String API_KEY_HEADER_NAME = "x-api-key";
    private static final String API_KEY_VALUE = "reqres-free-v1"; // Nilai API Key Anda

    public static void setupHeaders() {
        request = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                // --- Tambahkan API Key Reqres.in di sini ---
                .header(API_KEY_HEADER_NAME, API_KEY_VALUE);

        // Baris di bawah ini adalah untuk GoreST.
        // Karena host Anda adalah reqres.in, ini harus tetap dikomentari atau dihapus.
        // .header("Authorization", "Bearer 61097afbbf02ab6fa79ccbff95855e26d20fe7ba69f95ac466f387df867db49e");
    }

    public static Response getListUsers(String endpoint) {
        setupHeaders();
        return request.when().get(endpoint);
    }

    public static Response postCreateUser(String endpoint) {
        // Data yang Anda gunakan di sini adalah format Reqres.in/GoreST,
        // namun Reqres.in hanya memerlukan "name" dan "job" untuk POST.
        // Karena Anda mungkin beralih API, kita biarkan payloadnya kompleks.
        String name = "Bryan Andika";
        String gender = "male"; // Akan diabaikan Reqres.in
        String email = generateRandomEmail(); // Akan diabaikan Reqres.in
        String status = "active"; // Akan diabaikan Reqres.in
        String job = "QA Engineer"; // Tambahkan job untuk Reqres.in

        JSONObject payload = new JSONObject();
        payload.put("name", name);
        payload.put("job", job); // Reqres.in memerlukan "job" untuk POST
        // payload.put("gender", gender); // Biarkan ini untuk GoreST
        // payload.put("email", email);   // Biarkan ini untuk GoreST
        // payload.put("status", status); // Biarkan ini untuk GoreST

        setupHeaders();
        return request.body(payload.toString()).when().post(endpoint);
    }

    public static Response deleteUser(String endpoint, String user_id) {
        setupHeaders();
        String finalendpoint = endpoint + "/" + user_id;
        return request.when().delete(finalendpoint);
    }

    public static Response updateUser(String endpoint, String user_id) {
        setupHeaders();

        String name = "Bryan update";
        String gender = "male";
        String email = generateRandomEmail();
        String status = "active";
        String job = "Senior QA"; // Tambahkan job untuk Reqres.in

        JSONObject payload = new JSONObject();
        payload.put("name", name);
        payload.put("job", job);
        // payload.put("gender", gender);
        // payload.put("email", email);
        // payload.put("status", status);

        String finalendpoint = endpoint + "/" + user_id;
        return request.body(payload.toString()).when().patch(finalendpoint);
    }
}