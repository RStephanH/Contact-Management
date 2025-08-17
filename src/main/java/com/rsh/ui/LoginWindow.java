package com.rsh.ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import com.rsh.ui.controllers.LoginController;

public class LoginWindow {

    private LoginController loginController = new LoginController();

    public Scene create(Stage stage) {

        Label lblTitle = new Label("Login");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField tfUsername = new TextField();
        tfUsername.setPromptText("Username");

        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Password");

        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e -> {
            String username = tfUsername.getText().trim();
            String password = pfPassword.getText().trim();

            // Prevent sending empty inputs
            if (username.isEmpty() || password.isEmpty()) {
                showAlert(AlertType.WARNING, "Input Error", "Username and password cannot be empty.");
                return;
            }

            try {
                // LoginController should throw an Exception or return a special value on failure
                String result = loginController.login(username, password);

                if (result != null && result.contains("Login successful")) {
                    showAlert(AlertType.INFORMATION, "Login Successful", "Welcome " + username);
                    // TODO: navigate to dashboard scene
                } else {
                    showAlert(AlertType.WARNING, "Login Failed", "Invalid username or password.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(AlertType.ERROR, "Connection Error", "Unable to connect to server.\n" + ex.getMessage());
            }
        });

        Button btnGoSignup = new Button("No account? Sign Up");
        btnGoSignup.setOnAction(e -> stage.setScene(new SignupWindow().create(stage)));

        VBox layout = new VBox(10, lblTitle, tfUsername, pfPassword, btnLogin, btnGoSignup);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        return new Scene(layout, 300, 250);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

