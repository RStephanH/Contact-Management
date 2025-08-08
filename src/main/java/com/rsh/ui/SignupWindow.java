package com.rsh.ui;

import com.rsh.model.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class SignupWindow {
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

        Button btnSignup = new Button("Sign Up");
        btnSignup.setOnAction(e -> {
            User newUser = new User(
                    tfFirstName.getText(),
                    tfLastName.getText(),
                    tfEmail.getText(),
                    tfUsername.getText(),
                    pfPassword.getText()
            );
            showAlert(Alert.AlertType.INFORMATION, "Signup Successful",
                    "Account created for " + newUser.getClass().getSimpleName() + " - " + tfUsername.getText());
            stage.setScene(new LoginWindow().create(stage));
        });

        Button btnGoLogin = new Button("Already have an account? Login");
        btnGoLogin.setOnAction(e -> stage.setScene(new LoginWindow().create(stage)));

        VBox layout = new VBox(10, lblTitle, tfFirstName, tfLastName, tfEmail, tfUsername, pfPassword, btnSignup, btnGoLogin);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-alignment: center;");

        return new Scene(layout, 350, 400);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
