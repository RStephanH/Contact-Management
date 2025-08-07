package com.rsh.ui;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SignupWindow {
    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Signup");

        // UI elements
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button signupButton = new Button("Sign Up");

        signupButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // TODO: Save user to database or call AuthService
            System.out.println("Registered: " + username);
            stage.close(); // Close window after signup
        });

        VBox layout = new VBox(10, usernameLabel, usernameField, passwordLabel, passwordField, signupButton);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
