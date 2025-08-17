package com.rsh.ui;

import com.rsh.ui.controllers.SignupController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SignupWindow {
    private SignupController signupController = new SignupController();

    public Scene create(Stage stage) {
        Label lblTitle = new Label("Sign Up");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField tfFirstName = new TextField();
        tfFirstName.setPromptText("First Name");

        TextField tfLastName = new TextField();
        tfLastName.setPromptText("Last Name");

        TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");

        TextField tfUsername = new TextField();
        tfUsername.setPromptText("Username");

        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Password");

        PasswordField pfConfirmPassword = new PasswordField();
        pfConfirmPassword.setPromptText("Confirm Password");

        Button btnSignup = new Button("Sign Up");
        btnSignup.setOnAction(e -> {
            try {
                // Validate password confirmation
                if (!pfPassword.getText().equals(pfConfirmPassword.getText())) {
                    showAlert(Alert.AlertType.WARNING, "Password Mismatch",
                            "Passwords do not match. Please try again.");
                    return;
                }

                String result = signupController.signup(
                        tfFirstName.getText(),
                        tfLastName.getText(),
                        tfEmail.getText(),
                        tfUsername.getText(),
                        pfPassword.getText()
                );
                showAlert(Alert.AlertType.INFORMATION, "Signup Successful",
                        "Account created for " + tfUsername.getText() + "\nResponse: " + result);
                stage.setScene(new LoginWindow().create(stage));
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Signup Failed", ex.getMessage());
            }
        });

        Button btnGoLogin = new Button("Already have an account? Login");
        btnGoLogin.setOnAction(e -> stage.setScene(new LoginWindow().create(stage)));

        VBox layout = new VBox(10,
                lblTitle,
                tfFirstName,
                tfLastName,
                tfEmail,
                tfUsername,
                pfPassword,
                pfConfirmPassword,
                btnSignup,
                btnGoLogin
        );
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        return new Scene(layout, 350, 450);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

