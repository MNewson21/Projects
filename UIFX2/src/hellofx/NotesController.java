package hellofx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class NotesController {

    @FXML
    private TextField noteField;

    @FXML
    private ListView<String> notesList;

    private static final String SUPABASE_URL = "https://tpzmmjktbrvwncuahjka.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRwem1tamt0YnJ2d25jdWFoamthIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTUxMDc3OTgsImV4cCI6MjA3MDY4Mzc5OH0.XWvc1XfKseFHBJMXUQBpx-KfKmZJvI5ztuefFcIlcM8";
    
    private HttpClient httpClient = HttpClient.newHttpClient();
    private String accessToken;
    private String userId;

    //associates visible text to the database id so we can tell what each note contains rather than the id
    private Map<String, String> noteMap = new LinkedHashMap<>();

    public void setUser(String accessToken, String userId) {
        this.accessToken = accessToken;
        this.userId = userId;
        loadNotes();
    }

    @FXML
    private void saveNote() {
        String note = noteField.getText().trim();
        if (note.isEmpty()) return;

        String selectedContent = notesList.getSelectionModel().getSelectedItem();

        if (selectedContent != null && noteMap.containsKey(selectedContent)) {
            String noteId = noteMap.get(selectedContent);
            updateNote(noteId, note);
        } else {
            insertNote(note);
        }
    }

    private void insertNote(String note) {
        try {
            String insertUrl = SUPABASE_URL + "/rest/v1/notes";
            JSONObject json = new JSONObject();
            json.put("content", note);
            json.put("user_id", userId);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(insertUrl))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                JSONArray arr = new JSONArray(response.body());
                JSONObject createdNote = arr.getJSONObject(0);
                String id = createdNote.getString("id");
                String content = createdNote.getString("content");

                String displayText = truncate(content);
                noteMap.put(displayText, id);
                notesList.getItems().add(displayText);
                noteField.clear();
                notesList.getSelectionModel().clearSelection();
            } else {
                showAlert("Error", "Failed to save note: " + response.statusCode() + " - " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }

    private void updateNote(String noteId, String note) {
        try {
            String updateUrl = SUPABASE_URL + "/rest/v1/notes?id=eq." + noteId;
            JSONObject json = new JSONObject();
            json.put("content", note);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(updateUrl))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 204) {
                String displayText = truncate(note);
                String oldDisplay = getKeyByValue(noteMap, noteId);

                if (oldDisplay != null) {
                    notesList.getItems().remove(oldDisplay);
                    noteMap.remove(oldDisplay);
                }

                noteMap.put(displayText, noteId);
                notesList.getItems().add(displayText);

                notesList.refresh();
                noteField.clear();
                notesList.getSelectionModel().clearSelection();
            } else {
                showAlert("Error", "Failed to update note: " + response.statusCode() + " - " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    private void deleteNote() {
        String selectedContent = notesList.getSelectionModel().getSelectedItem();
        if (selectedContent == null) return;

        String noteId = noteMap.get(selectedContent);

        try {
            String deleteUrl = SUPABASE_URL + "/rest/v1/notes?id=eq." + noteId;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(deleteUrl))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer " + accessToken)
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 204) {
                noteMap.remove(selectedContent);
                notesList.getItems().remove(selectedContent);
                noteField.clear();
            } else {
                showAlert("Error", "Failed to delete note: " + response.statusCode() + " - " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }

    private void loadNotes() {
        try {
            String fetchUrl = SUPABASE_URL + "/rest/v1/notes?select=*&user_id=eq." + userId;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fetchUrl))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer " + accessToken)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray notesArray = new JSONArray(response.body());
                for (int i = 0; i < notesArray.length(); i++) {
                    JSONObject note = notesArray.getJSONObject(i);
                    String id = note.getString("id");
                    String content = note.getString("content");
                    String displayText = truncate(content);
                    noteMap.put(displayText, id);
                    notesList.getItems().add(displayText);
                }

                // Load note into TextField when clicked
                notesList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        String noteId = noteMap.get(newVal);
                        noteField.setText(contentFromId(noteId));
                    }
                });

            } else {
                showAlert("Error", "Failed to load notes: " + response.body());
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", e.getMessage());
        }
    }

    //shorten display for notes that are too long to display
    private String truncate(String text) {
        return text.length() > 30 ? text.substring(0, 30) + "..." : text;
    }

    //find key by value in the map
    private String getKeyByValue(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) return entry.getKey();
        }
        return null;
    }

    private String contentFromId(String noteId) {
        try {
            String url = SUPABASE_URL + "/rest/v1/notes?id=eq." + noteId + "&select=content";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", SUPABASE_ANON_KEY)
                    .header("Authorization", "Bearer " + accessToken)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONArray arr = new JSONArray(response.body());
                if (arr.length() > 0) {
                	
                    return arr.getJSONObject(0).getString("content");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
