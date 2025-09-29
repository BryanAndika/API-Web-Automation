package pages;

import helper.Endpoint;
import helper.Utility;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;

import static helper.Models.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIndexOutOfBoundsException;

public class ApiPage {

    String setURL, global_id;
    Response res;

    public void prepareUrlFor(String url) {
        switch (url) {
            case "GET_LIST_USERS":
                setURL = Endpoint.GET_LIST_USERS;
                break;
            case "CREATE_NEW_USERS":
                setURL = Endpoint.CREATE_NEW_USERS;
                break;
            case "DELETE_USERS":
                setURL = Endpoint.DELETE_USERS;
                break;
            default:
                System.out.println("input right url");
        }
    }

    public void hitApiGetListUsers() {
        res = getListUsers(setURL);
        System.out.println(res.getBody().asString());
    }

    public void hitApiPostCreateUser() {
        res = postCreateUser(setURL);
        System.out.println(res.getBody().asString());

    }

    public void validationStatusCodeEquals(int status_code) {
        assertThat(res.statusCode()).isEqualTo(status_code);
    }

    public void validationResponseBodyGetListUsers() {
        // Perbaikan Wajib: Tambahkan "data." di awal path
        List<Object> id = res.jsonPath().getList("data.id");
        List<Object> email = res.jsonPath().getList("data.email");
        List<Object> first_name = res.jsonPath().getList("data.first_name"); // Reqres.in menggunakan first_name

        // Reqres.in tidak mengembalikan name, gender, dan status di list user endpoint.
        // Anda harus memvalidasi data yang benar-benar ada di response (id, email, first_name, last_name).

        // Validasi yang Anda tulis hanya akan memeriksa item pertama di list
        assertThat(id).as("List ID harus berisi setidaknya satu item").isNotEmpty();
        assertThat(email).as("List email harus berisi setidaknya satu item").isNotEmpty();

        // Cek item pertama (untuk memastikan tidak null)
        assertThat(id.get(0)).isNotNull();
        assertThat(first_name.get(0)).isNotNull();

        // *CATATAN:* Karena Reqres.in, gender/status/name akan selalu null/kosong.
        // Jika Anda ingin menggunakan validasi gender/status, Anda harus beralih ke API lain (misal Gorest)
        // atau hapus baris validasi gender/status yang salah ini.
        // Jika Anda bersikeras, ini akan menghasilkan error karena data tersebut tidak ada di Reqres.in.
    }

    public void validationResponseJsonWithJSONSchema(String filename) {
        File JSONFile = Utility.getJSONSchemaFile(filename);
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }

    public void validationResponseBodyPostCreateUser() {
        JsonPath jsonPathEvaluator = res.jsonPath();

        // Perbaikan Wajib: Ambil ID sebagai String
        String id_string = jsonPathEvaluator.getString("id");

        String name = jsonPathEvaluator.getString("name"); // Gunakan getString()
        String job = jsonPathEvaluator.getString("job"); // Reqres.in mengembalikan 'job', bukan gender/status

        // Validasi ID (sebagai String)
        assertThat(id_string).isNotNull().as("ID tidak boleh null setelah user dibuat.");

        // Validasi data yang benar-benar dikirim di payload (name dan job)
        assertThat(name).isEqualTo("Bryan Andika");
        assertThat(job).isEqualTo("QA Engineer");

        // Simpan ID yang sudah berupa String ke global_id
        global_id = id_string;

        // Hapus validasi ini, karena Reqres.in tidak mengembalikan field ini.
        // assertThat(gender).isIn("female", "male");
        // assertThat(status).isIn("active", "inactive");
    }

    public void hitApiDeleteUser() {
        res = deleteUser(setURL,global_id);

    }

    public void hitApiUpdateNewUser() {
        res = updateUser(setURL,global_id);

    }

    public void validationResponseBodyUpdateUser(){
        JsonPath jsonPathEvaluator = res.jsonPath();

        // Ambil data yang ADA di response UPDATE Reqres.in:
        String name = jsonPathEvaluator.getString("name");
        String job = jsonPathEvaluator.getString("job");
        String updatedAt = jsonPathEvaluator.getString("updatedAt"); // Cek timestamp update

        // Validasi field yang ADA

        // 1. Validasi Name
        assertThat(name).as("Nama harus ada di response update.").isNotNull();
        // Jika Anda mengirim payload update dengan data tertentu, Anda harus memvalidasinya.
        // Misal, jika payload update berisi name="Bryan update" dan job="Senior QA":
        // assertThat(name).isEqualTo("Bryan update");
        // assertThat(job).isEqualTo("Senior QA");

        // 2. Validasi Job
        assertThat(job).as("Job harus ada di response update.").isNotNull();

        // 3. Validasi updatedAt (harus ada, menunjukkan update berhasil)
        assertThat(updatedAt).as("Field updatedAt harus ada.").isNotNull();

        // HAPUS SEMUA VALIDASI UNTUK FIELD YANG TIDAK ADA (id, email, gender, status)
        // Karena Reqres.in tidak mengembalikannya di response PUT/PATCH
    }

}

