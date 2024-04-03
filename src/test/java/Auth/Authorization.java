package Auth;
//import Constants.AppConstants;
import Data.DataHelper;
import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Authorization {
    static Dotenv dotenv = Dotenv.load();
    public static String userName = dotenv.get("USER");
    public static String passwordName = dotenv.get("PASSWORD");
    public static String getAccessToken() {

        Response response = given()
                .auth().preemptive().basic(userName, passwordName)
                .when()
                .baseUri(DataHelper.baseURL)
                .get("/auth/login")
                .then()
                .extract().response();

        return response.jsonPath().getString("accessToken");
        // а можно написать response.json().accessToken?
    }
}
