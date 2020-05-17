package GUI.views;

import GUI.Main;
import Requests.SignUpRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField regEmailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField LastNameField;

    @FXML
    private PasswordField regPassField;

    @FXML
    private PasswordField regPassConfirm;

    @FXML
    private Button SignupButton;

    @FXML
    private Button backToLogIn;

    @FXML
    private Label response_label;


    @FXML
    void initialize() {
        SignupButton.setOnAction(e -> {
            try{
                String response = SignUpRequest.SignUpRequestMake(regEmailField.getText(), nameField.getText(),
                        LastNameField.getText(), regPassField.getText(), regPassConfirm.getText());
                if (response.equals("true")){
                    try{
                        Parent tableViewParent = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
                        Scene LoginScene = new Scene(tableViewParent);

                        //This line gets the Stage information
                        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                        window.setScene(LoginScene);
                        window.show();
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                else {
                    response_label.setText(response);
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        backToLogIn.setOnAction(e -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("LogInView.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);

                //This line gets the Stage information
                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }
}
