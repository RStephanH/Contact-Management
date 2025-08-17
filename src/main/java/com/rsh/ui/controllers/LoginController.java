package com.rsh.ui.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsh.ui.dto.LoginRequest;
import com.rsh.ui.dto.LoginResponse;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

public class LoginController {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/contact-backend/api";

  public LoginResponse login(String username, String password) throws Exception {
    LoginRequest loginRequest = new LoginRequest(username, password);
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(loginRequest);

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/login"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    return mapper.readValue(response.body(), LoginResponse.class);
}

}

