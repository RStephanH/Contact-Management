package com.rsh.ui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class LoginWindow {
    public Scene create(Stage stage) {
        Label lblTitle = new Label("Login");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField tfUsername = new TextField();
        tfUsername.setPromptText("Username");

        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Password");

        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e -> {
            String username = tfUsername.getText();
            String password = pfPassword.getText();

            if (username.equals("admin") && password.equals("1234")) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome " + username);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
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
