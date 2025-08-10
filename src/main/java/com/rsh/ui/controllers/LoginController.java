package com.rsh.ui.controllers;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;


/**
 * LoginController
 */
public class LoginController {
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final String BASE_URL = "http://localhost:8080/contact-backend/api";

  /**
     * Sends login request to backend
     * @param username The username entered by the user
     * @param password The password entered by the user
     * @return The backend's raw JSON response as a string
     * @throws Exception if HTTP request fails
     */

  public String login(String username, String password) throws Exception {

    String json = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(BASE_URL + "/login"))
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(json))
      .build();
    HttpResponse <String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }

  
}
