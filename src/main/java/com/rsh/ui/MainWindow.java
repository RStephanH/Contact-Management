package com.rsh.ui;

import com.rsh.model.User;
import com.rsh.model.ContactFX;
import com.rsh.ui.dto.ContactDTO;
import com.rsh.mapper.ContactMapper;
import com.rsh.client.ContactApiClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;


public class MainWindow {

  private final User loggedUser;
  private final ObservableList<ContactFX> contactList = FXCollections.observableArrayList();
  public MainWindow(User loggedUser) {
    this.loggedUser = loggedUser;

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

    // TableView for contacts 
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

    // Delete Column
    TableColumn<ContactFX, Void> colDelete = new TableColumn<>("Action");

    colDelete.setCellFactory(tc -> new TableCell<>() {
      private final Button btnDelete = new Button("Delete");

    {
        btnDelete.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-cursor: hand;");
        btnDelete.setOnAction(e -> {
          ContactFX contact = getTableView().getItems().get(getIndex());

          // Validation Box
          Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
          confirmAlert.setTitle("Confirm Deletion");
          confirmAlert.setHeaderText("Delete Contact");
          confirmAlert.setContentText("Are you sure you want to delete " 
            + contact.getFirstName() + " " + contact.getLastName() + " ?");

          confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
              try {
                ContactApiClient apiClient = new ContactApiClient();
                boolean status = apiClient.deleteContact(Long.parseLong(contact.getContactId())); // API call
                contactList.remove(contact); // retirer de l'UI
                if(status){
                  // Success Notification
                  Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                  successAlert.setTitle("Contact Deleted");
                  successAlert.setHeaderText(null);
                  successAlert.setContentText("Contact deleted successfully!");
                  successAlert.showAndWait();
                }
              } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete contact!");
                alert.showAndWait();
              }
            }
          });
        });
      }

      @Override
      protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setGraphic(null);
        } else {
          setGraphic(btnDelete);
        }
      }
    });

    contactTable.getColumns().addAll(colName, colEmail, colPhone, colDelete);



    // Double-click to edit
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

    // Default label
    Label lblPlaceholder = new Label("Select an option from the sidebar");
    mainContent.setCenter(lblPlaceholder);

    // ===================== Sidebar Button Actions =====================
    btnContacts.setOnAction(e -> {
      ContactApiClient apiClient = new ContactApiClient();
      try {
        List<ContactDTO> dtoList = apiClient.getContactsByUserId(loggedUser.getUserId());
        contactList.clear();
        dtoList.forEach(dto -> contactList.add(ContactMapper.toFX(dto)));
      } catch (Exception ex) {
        ex.printStackTrace();
        mainContent.setCenter(new Label("Failed to load contacts."));
      }

      mainContent.setCenter(contactTable);
    });

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
      ContactApiClient apiClient = new ContactApiClient();

      ContactDTO dto = new ContactDTO(null, tfFirstName.getText(), tfLastName.getText(), tfEmail.getText(), tfPhone.getText(), loggedUser.getUserId());
      try {
        ContactDTO savedDTO = apiClient.createContact(dto);
        ContactFX savedFX = ContactMapper.toFX(savedDTO);
        contactList.add(savedFX);
        mainContent.setCenter(new Label("Contact added!"));
      } catch (Exception ex) {
        ex.printStackTrace();
      }

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
      try {
        // Create DTO from ContactFX
        ContactDTO dto = new ContactDTO(contact.getContactId(),tfFirstName.getText(), tfLastName.getText(), tfEmail.getText(), tfPhone.getText(), contact.getUserId()                                                  );

        // Call the API for the update
        ContactApiClient apiClient = new ContactApiClient();
        ContactDTO updated = apiClient.updateContact(Long.parseLong(contact.getContactId()), dto);

        // Updating ContactFX object with backend response
        contact.setFirstName(updated.getFirstName());
        contact.setLastName(updated.getLastName());
        contact.setEmail(updated.getEmail());
        contact.setPhone(updated.getPhone());
        dialog.close();
      } catch (Exception ex) {
        ex.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Error during the contact update!");
        alert.showAndWait();
      }
    });

    form.getChildren().addAll(tfFirstName, tfLastName, tfEmail, tfPhone, btnSave);
    Scene scene = new Scene(form, 300, 250);
    dialog.setScene(scene);
    dialog.initOwner(stage);
    dialog.show();
  }
}

