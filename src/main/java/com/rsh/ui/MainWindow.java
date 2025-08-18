package com.rsh.ui;

import com.rsh.model.User;
import com.rsh.model.ContactFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainWindow {

    private final User loggedUser;
    private final ObservableList<ContactFX> contactList = FXCollections.observableArrayList();

    public MainWindow(User loggedUser) {
        this.loggedUser = loggedUser;

        // Exemple de données (plus tard tu remplaceras par un appel au backend)
        contactList.add(new ContactFX("1", "Alice", "Smith", "alice@example.com", "123456789", loggedUser.getUserId()));
        contactList.add(new ContactFX("2", "Bob", "Johnson", "bob@example.com", "987654321", loggedUser.getUserId()));
    }

    public Scene create(Stage stage) {
        // ===================== Header =====================
        HBox header = new HBox(10);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #2c3e50; -fx-alignment: center-left;");

        Label lblWelcome = new Label("Welcome, " + loggedUser.getFullName());
        lblWelcome.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        Button btnLogout = new Button("Logout");
        btnLogout.setOnAction(e -> stage.setScene(new LoginWindow().create(stage)));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(lblWelcome, spacer, btnLogout);

        // ===================== Sidebar =====================
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setStyle("-fx-background-color: #34495e;");
        sidebar.setPrefWidth(150);

        Button btnContacts = new Button("Contacts");
        Button btnAddContact = new Button("Add Contact");
        Button btnSettings = new Button("Settings");

        btnContacts.setMaxWidth(Double.MAX_VALUE);
        btnAddContact.setMaxWidth(Double.MAX_VALUE);
        btnSettings.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(btnContacts, btnAddContact, btnSettings);

        // ===================== Main Content =====================
        BorderPane mainContent = new BorderPane();
        mainContent.setPadding(new Insets(10));

        // TableView pour les contacts (⚡ utilise ContactFX maintenant)
        TableView<ContactFX> contactTable = new TableView<>();
        contactTable.setItems(contactList);

        TableColumn<ContactFX, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(c -> c.getValue().firstNameProperty().concat(" ").concat(c.getValue().lastNameProperty()));
        colName.setPrefWidth(150);

        TableColumn<ContactFX, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(c -> c.getValue().emailProperty());
        colEmail.setPrefWidth(200);

        TableColumn<ContactFX, String> colPhone = new TableColumn<>("Phone");
        colPhone.setCellValueFactory(c -> c.getValue().phoneProperty());
        colPhone.setPrefWidth(150);

        contactTable.getColumns().addAll(colName, colEmail, colPhone);

        // Double-clic pour éditer
        contactTable.setRowFactory(tv -> {
            TableRow<ContactFX> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    ContactFX selected = row.getItem();
                    showEditContactDialog(stage, selected);
                }
            });
            return row;
        });

        // Label par défaut
        Label lblPlaceholder = new Label("Select an option from the sidebar");
        mainContent.setCenter(lblPlaceholder);

        // ===================== Sidebar Button Actions =====================
        btnContacts.setOnAction(e -> mainContent.setCenter(contactTable));
        btnAddContact.setOnAction(e -> showAddContactForm(mainContent));
        btnSettings.setOnAction(e -> mainContent.setCenter(new Label("User Settings")));

        // ===================== Layout =====================
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setLeft(sidebar);
        root.setCenter(mainContent);

        return new Scene(root, 800, 600);
    }

    // ===================== Add Contact Form =====================
    private void showAddContactForm(BorderPane mainContent) {
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));

        TextField tfFirstName = new TextField();
        tfFirstName.setPromptText("First Name");
        TextField tfLastName = new TextField();
        tfLastName.setPromptText("Last Name");
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");
        TextField tfPhone = new TextField();
        tfPhone.setPromptText("Phone");

        Button btnSubmit = new Button("Add Contact");
        btnSubmit.setOnAction(e -> {
            ContactFX newContact = new ContactFX(
                String.valueOf(contactList.size() + 1), // simple auto-id pour l'instant
                tfFirstName.getText(),
                tfLastName.getText(),
                tfEmail.getText(),
                tfPhone.getText(),
                loggedUser.getUserId()
            );
            contactList.add(newContact);
            mainContent.setCenter(new Label("Contact added!"));
        });

        form.getChildren().addAll(tfFirstName, tfLastName, tfEmail, tfPhone, btnSubmit);
        mainContent.setCenter(form);
    }

    // ===================== Edit Contact Dialog =====================
    private void showEditContactDialog(Stage stage, ContactFX contact) {
        Stage dialog = new Stage();
        dialog.setTitle("Edit Contact");

        VBox form = new VBox(10);
        form.setPadding(new Insets(10));

        TextField tfFirstName = new TextField(contact.getFirstName());
        TextField tfLastName = new TextField(contact.getLastName());
        TextField tfEmail = new TextField(contact.getEmail());
        TextField tfPhone = new TextField(contact.getPhone());

        Button btnSave = new Button("Save");
        btnSave.setOnAction(e -> {
            contact.setFirstName(tfFirstName.getText());
            contact.setLastName(tfLastName.getText());
            contact.setEmail(tfEmail.getText());
            contact.setPhone(tfPhone.getText());
            dialog.close();
        });

        form.getChildren().addAll(tfFirstName, tfLastName, tfEmail, tfPhone, btnSave);
        Scene scene = new Scene(form, 300, 250);
        dialog.setScene(scene);
        dialog.initOwner(stage);
        dialog.show();
    }
}

