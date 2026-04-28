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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloController {
    // registr
    @FXML
    private TextField LastName;

    @FXML
    private TextField Logn1;

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
    private AnchorPane RegUser;

    @FXML
    private CheckBox Women;

    @FXML
    private String Gender;

    @FXML
    private ImageView myImageView;


    // welcome

    @FXML
    private Button Vixod;

    @FXML
    void OnActionVixod(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение выхода");
        alert.setHeaderText("Вы действительно хотите выйти?");
        alert.setContentText("Нажмите OK для выхода или Cancel для отмены.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) Vixod.getScene().getWindow();
            stage.close();
        }

    }

    //вход
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Login;

    @FXML
    private PasswordField Password;
    DBConnector dbConnector = new DBConnector();

    @FXML
    private Button Reg1;

    @FXML
    private Button Vxod;

    @FXML
    void onActionVxod(ActionEvent event) throws IOException {

        String login = Login.getText().trim();
        String password = Password.getText().trim();

        if (dbConnector.TableConn(login, password)) {
            Stage stage = (Stage) Vxod.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1150, 700);
            stage.setTitle("Главная!");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка авторизации");
            alert.setHeaderText("Неверный логин или пароль.");
            alert.setContentText("Пожалуйста, проверьте введенные данные.\nХотите попробовать зарегистрироваться?");

            ButtonType registerButton = new ButtonType("Зарегистрироваться");
            ButtonType cancelButton = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(registerButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == registerButton) {
                Stage stage = (Stage) Vxod.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
                stage.setTitle("Регистрация!");
                stage.setScene(scene);
                stage.show();
            }
        }
    }


    @FXML
    void initialize() {
        dbConnector.DBConn();
        assert Login != null : "fx:id=\"Login\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Password != null : "fx:id=\"Password\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Vxod != null : "fx:id=\"Vxod\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Vixod != null : "fx:id=\"Vixod\" was not injected: check your FXML file 'main.fxml'.";
        assert LastName != null : "fx:id=\"LastName\" was not injected: check your FXML file 'registration.fxml'.";
        assert Logn1 != null : "fx:id=\"Logn1\" was not injected: check your FXML file 'registration.fxml'.";
        assert Men != null : "fx:id=\"Men\" was not injected: check your FXML file 'registration.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'registration.fxml'.";
        assert Otmena != null : "fx:id=\"Otmena\" was not injected: check your FXML file 'registration.fxml'.";
        assert Password1 != null : "fx:id=\"Password1\" was not injected: check your FXML file 'registration.fxml'.";
        assert Registr != null : "fx:id=\"Registr\" was not injected: check your FXML file 'registration.fxml'.";
        assert Women != null : "fx:id=\"Women\" was not injected: check your FXML file 'registration.fxml'.";
        assert Reg1 != null : "fx:id=\"Reg1\" was not injected: check your FXML file 'hello-view.fxml'.";


    }


    public void OnActionReg1(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) Reg1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
            stage.setTitle("Вход в систему");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


