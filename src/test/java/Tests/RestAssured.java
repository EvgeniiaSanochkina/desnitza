package Tests;

import API_Handlers.API_Handlers;
import Data.DataHelper;
import Tests.Objects.JsonObjectFeedback;
import Tests.Objects.JsonObjectPlaylist;
import Tests.Objects.JsonObjectVideo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RestAssured {
    @Test
    public void testPostFeedbackRestAssured() {
        String email = DataHelper.generateEmail();
        JsonObjectFeedback newObject = new JsonObjectFeedback(email);
        Response response = API_Handlers.makePostRequestFeedback(newObject);
        assert response.statusCode() == 202;
    }

    @Test
    public void testPostNewsRestAssured() {
        String title = DataHelper.generateTile();
        String description = DataHelper.generateDescription();
        Response response = API_Handlers.makePostRequestNews(title, description);
        assert response.statusCode() == 201;
        assert Objects.equals(response.jsonPath().getString("title"), title);
        assert Objects.equals(response.jsonPath().getString("description"), description);
    }

    @Test
    public void testPostPartnersRestAssured() {
        File file = DataHelper.generateFile1();
        String partnerName = DataHelper.generatePartnerName();
        String link = DataHelper.generateLink();
        Response response = API_Handlers.makePostRequestPartner(partnerName, link, file);
        assert response.statusCode() == 201;
        assert Objects.equals(response.jsonPath().getString("title"), partnerName);
        assert Objects.equals(response.jsonPath().getString("link"), link);
    }

    @Test
    public void testPostPlaylistRestAssured() {
        String title = DataHelper.generateTile();
        JsonObjectPlaylist newObject = new JsonObjectPlaylist(title);
        Response response = API_Handlers.makePostRequestPlaylist(newObject);
        assert response.statusCode() == 200;
        assert Objects.equals(response.jsonPath().getString("title"), title);
    }

    @org.junit.jupiter.api.Test
    public void testNewTourRestAssured(){
        String title = DataHelper.generateTile();
        String description = DataHelper.generateDescription();
        String tourDate = "2024-12-31T12:00:00";
        Response response = API_Handlers.makePostRequestTour(title, description, tourDate);
        assert response.statusCode() == 201;
        assert Objects.equals(response.jsonPath().getString("title"), title);
        assert Objects.equals(response.jsonPath().getString("description"), description);
        assert Objects.equals(response.jsonPath().getString("tourDate"), tourDate);
    }

    @org.junit.jupiter.api.Test
    public void testNewVideoRestAssured(){
        String title = DataHelper.generateTile();
        int playlistId = 1;
        JsonObjectVideo newObject = new JsonObjectVideo(title, playlistId);
        Response response = API_Handlers.makePostRequestVideo(newObject);
        assert response.statusCode() == 201;
        assert Objects.equals(response.jsonPath().getString("title"), title);
        assert Objects.equals(response.jsonPath().getInt("playlistId"), playlistId);
    }


    @Test
    public void postRequestCreatesNewWorship() {

        Response response = API_Handlers.makePostRequestWorshipSchedule();
        assert response.statusCode() == 201; // баг: не требуется токен

        List<String> dateTime, type, comment;
        List<Integer> churchId;

        churchId = JsonPath.from(response.getBody().asString()).get("churchId");
        dateTime = JsonPath.from(response.getBody().asString()).get("dateTime");
        type = JsonPath.from(response.getBody().asString()).get("type");
        comment = JsonPath.from(response.getBody().asString()).get("comment");

        assert Arrays.equals((new Object[]{churchId.get(0)}), new Object[]{1});
        assert Arrays.equals((new Object[]{dateTime.get(0)}), new Object[]{"2024-04-10T07:00:00"});
        assert Arrays.equals((new Object[]{type.get(0)}), new Object[]{"RestAssured"});
        assert Arrays.equals((new Object[]{comment.get(0)}), new Object[]{"RestAssured"});

    }
}
