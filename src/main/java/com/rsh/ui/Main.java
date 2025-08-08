package com.rsh.ui;
import com.rsh.ui.SignupWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Contact Management");
        primaryStage.setScene(new LoginWindow().create(primaryStage)); // Start with Login
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        }
    }