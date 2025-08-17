package hellofx;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField emailinput;

    @FXML
    private TextField passinput;

    private static final String SUPABASE_URL = "https://tpzmmjktbrvwncuahjka.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRwem1tamt0YnJ2d25jdWFoamthIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTUxMDc3OTgsImV4cCI6MjA3MDY4Mzc5OH0.XWvc1XfKseFHBJMXUQBpx-KfKmZJvI5ztuefFcIlcM8";
    
    
    private HttpClient httpClient = HttpClient.newHttpClient();

    @FXML
    private void signin() {
        String email = emailinput.getText();
        String password = passinput.getText();

        try {
            String loginUrl = SUPABASE_URL + "/auth/v1/token?grant_type=password";
            String jsonBody = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

            HttpRequest loginRequest = HttpRequest.newBuilder()
                    .uri(URI.create(loginUrl))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> loginResponse = httpClient.send(loginRequest, HttpResponse.BodyHandlers.ofString());

            if (loginResponse.statusCode() != 200) {
                showAlert("Login Failed", "Status: " + loginResponse.statusCode() + "\n" + loginResponse.body());
                return;
            }

            JSONObject loginJson = new JSONObject(loginResponse.body());
            String accessToken = loginJson.getString("access_token");

            String fetchUrl = SUPABASE_URL + "/rest/v1/details?select=*";
            HttpRequest fetchRequest = HttpRequest.newBuilder()
                    .uri(URI.create(fetchUrl))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> fetchResponse = httpClient.send(fetchRequest, HttpResponse.BodyHandlers.ofString());

            if (fetchResponse.statusCode() != 200) {
                showAlert("Fetch Failed", "Status: " + fetchResponse.statusCode() + "\n" + fetchResponse.body());
                return;
            }

            JSONArray detailsArray = new JSONArray(fetchResponse.body());
            StringBuilder result = new StringBuilder("Details Table:\n");
            for (int i = 0; i < detailsArray.length(); i++) {
                JSONObject row = detailsArray.getJSONObject(i);
                result.append("UserId: ").append(row.getInt("UserId"))
                        .append(", Name: ").append(row.getString("Name"))
                        .append(", Password: ").append(row.getString("Password"))
                        .append(", Created: ").append(row.getString("created_at"))
                        .append("\n");
            }

            

            String userId = loginJson.getJSONObject("user").getString("id"); // unique user id


            FXMLLoader loader = new FXMLLoader(getClass().getResource("notes.fxml"));
            Parent root = loader.load();

            NotesController notesController = loader.getController();
            notesController.setUser(accessToken, userId); // pass token and user ID

            Scene scene = new Scene(root);
            Stage stage = (Stage) emailinput.getScene().getWindow();
    		stage.setTitle("Notes");

            stage.setScene(scene);
            stage.show();


            
            
            

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }
    
    @FXML
    private void signup() {
    	String email = emailinput.getText();
    	String password = passinput.getText();
        try {
            String signupUrl = SUPABASE_URL + "/auth/v1/signup";
            String jsonBody = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(signupUrl))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            showAlert("Sign Up Response", response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
