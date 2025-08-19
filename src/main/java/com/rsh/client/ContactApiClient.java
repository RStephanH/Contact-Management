package com.rsh.client;

import com.rsh.ui.dto.ContactDTO;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.*;
import java.util.*;

public class ContactApiClient {
    private static final String BASE_URL = "http://localhost:8080/contact-backend/api/contacts";
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    private final ObjectMapper mapper = new ObjectMapper();

    public ContactDTO createContact(ContactDTO dto) throws Exception {
        String json = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) {
            return gson.fromJson(response.body(), ContactDTO.class);
        } else {
            throw new RuntimeException("Erreur API: " + response.statusCode());
        }
    }

  public List<ContactDTO> getContactsByUserId(String userId) throws Exception {
        String url = BASE_URL + "/user/" + userId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ContactDTO[] dtoArray = mapper.readValue(response.body(), ContactDTO[].class);
        return Arrays.asList(dtoArray);
    }

    public List<ContactDTO> getAllContacts() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ContactDTO[] contacts = gson.fromJson(response.body(), ContactDTO[].class);
            return Arrays.asList(contacts);
        } else {
            throw new RuntimeException("Erreur API: " + response.statusCode());
        }
    }
}

