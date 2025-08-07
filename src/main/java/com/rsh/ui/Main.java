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
        Button openSignup = new Button("Open Signup Window");
        openSignup.setOnAction(e -> {
            new SignupWindow().show();
        });

        StackPane root = new StackPane(openSignup);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main App");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
        }
    }