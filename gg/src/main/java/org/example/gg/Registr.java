package org.example.gg;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Registr {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private String Gender;
    DBConnector dbConnector = new DBConnector();

    @FXML
    private TextField LastName;

    @FXML
    private TextField Login1;

    @FXML
    private CheckBox Men;

    @FXML
    private TextField Name;

    @FXML
    private Button Otmena;

    @FXML
    private PasswordField Password1;

    @FXML
    private Button Registr;

    @FXML
    private CheckBox Women;

    @FXML
    private Button Reg1;

    @FXML
    void OnActionMen(ActionEvent event) {
        Men.setText("Мужской");
        Gender=Men.getText();
        System.out.println(Men.getText());

    }

    @FXML
    void OnActionOtmena(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение выхода");
        alert.setHeaderText("Вы действительно хотите выйти?");
        alert.setContentText("Нажмите OK для выхода или Cancel для отмены.");

        Optional<ButtonType> result = alert.showAndWait();
        Stage stage = (Stage) Registr.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        stage.setTitle("Вход в систему");
        stage.setScene(scene);
        stage.show();
        }


    @FXML
    void OnActionRegistr(ActionEvent event) throws IOException {
        String login = Login1.getText();
        String password = Password1.getText();
        String name = Name.getText();
        String lastName = LastName.getText();

        if (login != null && !login.trim().isEmpty() && !login.equals("?") &&
                password != null && !password.trim().isEmpty() && !password.equals("?") &&
                Gender != null && !Gender.isEmpty() &&
                name != null && !name.trim().isEmpty() && !name.equals("?") &&
                lastName != null && !lastName.trim().isEmpty() && !lastName.equals("?")) {

            boolean isRegistr= dbConnector.RegUser(login, password, Gender, name, lastName);

            if (isRegistr) {
                Stage stage = (Stage) Registr.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
                stage.setTitle("Вход в систему");
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Ошибка регистрации");
                alert.setContentText("Пользователь с таким логином уже существует или произошла ошибка БД!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Пожалуйста, заполните все поля корректно!");
            alert.showAndWait();
        }

    }

    @FXML
    void OnActionWomen(ActionEvent event) {
        Women.setText("Женский");
        Gender=Women.getText();
        System.out.println(Women.getText());
    }

    @FXML
    void initialize() {
        assert Gender != null : "fx:id=\"Gender\" was not injected: check your FXML file 'registration.fxml'.";
        assert LastName != null : "fx:id=\"LastName\" was not injected: check your FXML file 'registration.fxml'.";
        assert Login1 != null : "fx:id=\"Login1\" was not injected: check your FXML file 'registration.fxml'.";
        assert Men != null : "fx:id=\"Men\" was not injected: check your FXML file 'registration.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'registration.fxml'.";
        assert Otmena != null : "fx:id=\"Otmena\" was not injected: check your FXML file 'registration.fxml'.";
        assert Password1 != null : "fx:id=\"Password1\" was not injected: check your FXML file 'registration.fxml'.";
        assert Registr != null : "fx:id=\"Registr\" was not injected: check your FXML file 'registration.fxml'.";
        assert Women != null : "fx:id=\"Women\" was not injected: check your FXML file 'registration.fxml'.";

    }
}

