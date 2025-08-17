package com.rsh.ui.controllers;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

/**
 * SignupController
 */
public class SignupController {
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final String BASE_URL = "http://localhost:8080/contact-backend/api";

  /**
     * Sends signup request to backend
     * @param firstName The user's first name
     * @param lastName  The user's last name
     * @param email     The user's email
     * @param username  The desired username
     * @param password  The desired password
     * @return The backend's raw JSON response as a string
     * @throws Exception if HTTP request fails
     */
  public String signup(String firstName, String lastName, String email,
                       String username, String password) throws Exception {

    // build JSON request body
    String json = String.format(
      "{\"firstName\":\"%s\", \"lastName\":\"%s\", \"email\":\"%s\", \"username\":\"%s\", \"password\":\"%s\"}",
      firstName, lastName, email, username, password
    );

    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(BASE_URL + "/signup"))
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(json))
      .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }
}

