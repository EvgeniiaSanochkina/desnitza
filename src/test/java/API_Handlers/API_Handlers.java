package API_Handlers;

import Auth.Authorization;
import Data.DataHelper;
import Tests.Objects.JsonObjectChurch;
import Tests.Objects.JsonObjectFeedback;
import Tests.Objects.JsonObjectPlaylist;
import Tests.Objects.JsonObjectVideo;
import io.restassured.response.Response;

import java.io.File;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class API_Handlers {
    static String accessToken = Authorization.getAccessToken();
    public static Response makePostRequestFeedback(JsonObjectFeedback newObject) {
        return given()
                .contentType("application/json")
                .body(newObject)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/feedback/subscribe")
                .then()
                .extract().response();
    }

    public static Response makePostRequestChurch() {
        String name = DataHelper.generatePartnerName();
        String site = DataHelper.generateLink();
        JsonObjectChurch newObject = new JsonObjectChurch(name, site);
        File file = DataHelper.generateFile1();

        return given()
                .header("Authorization", "Bearer " + accessToken)
                .multiPart("json", newObject)
                .multiPart("file", file)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/churches")
                .then()
                .extract().response();
    }

    public static Response makePostRequestNews(String title, String description) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .multiPart("title", title)
                .multiPart("description", description)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/news")
                .then()
                .extract().response();
    }

    public static Response makePostRequestPartner(String partnerName, String link, File file){

        return  given()
                .header("Authorization", "Bearer " + accessToken)
                .multiPart("title", partnerName)
                .multiPart("link", link)
                .multiPart("imageFile", file)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/partners")
                .then()
                .extract().response();
    }

    public static Response makePostRequestPlaylist(JsonObjectPlaylist newObject) {

        return given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType("application/json")
                .body(newObject)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/playlists")
                .then()
                .extract().response();
    }

    public static Response makePostRequestTour(String title, String description, String tourDate) {

        return given().
                header("Authorization", "Bearer " + accessToken)
                .multiPart("title", title)
                .multiPart("description", description)
                .multiPart("tourDate", tourDate)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/tour")
                .then()
                .extract().response();
    }

    public static Response makePostRequestVideo(JsonObjectVideo newObject) {

        return given().
                header("Authorization", "Bearer " + accessToken)
                .contentType("application/json")
                .body(newObject)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/playlists/videos")
                .then()
                .extract().response();
    }

    public static Response makePostRequestWorshipSchedule() {
        String json = "[{\"churchId\": 1, \n" +
                "\"worships\": [{\"date\": \"2024-04-10T07:00:00\", \"worshipType\": \"RestAssured\",\"comment\": \"RestAssured\"}]}]";

        return given()
//                .header("Authorization", "Bearer " + accessToken)
                .contentType("application/json")
                .body(json)
                .when()
                .baseUri(DataHelper.baseURL)
                .post("/worship/schedule")
                .then()
                .extract().response();
    }
}
