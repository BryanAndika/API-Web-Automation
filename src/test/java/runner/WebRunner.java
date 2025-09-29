package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        // Tambahkan semua paket yang berisi kode yang ingin dijalankan.
        // Biasanya ini mencakup Hooks, Step Definitions, dan Page Objects (jika Page Objects memiliki code yang dipanggil oleh Cucumber).
        glue = {"stepDef", "pages", "helper"}, // Tambahkan 'pages' dan 'helper' jika ada code yang dipanggil di sini
        features = {"src/test/java/features"},
        tags = "@web"
)
public class WebRunner {
}