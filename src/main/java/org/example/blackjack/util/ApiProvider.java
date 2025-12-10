package org.example.blackjack.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiProvider {

    public static String requestApi(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return getResponse.body();
    }

    public static JsonObject parseToJson(String textFormatJson){
        return (new Gson()).fromJson(textFormatJson,JsonObject.class);
    }

    public static List<JsonObject> getListJsonObjects(JsonObject json , String nameObjects){

        return json.get(nameObjects)
                .getAsJsonArray()
                .asList()
                .stream()
                .map(JsonElement::getAsJsonObject)
                .toList();
    }
}
