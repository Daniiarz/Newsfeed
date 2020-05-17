package GUI.views;

import Requests.LoginRequest;
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

public class LogInController {

    @FXML
    private PasswordField passField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField emailField;

    @FXML
    private Button signupButton;

    @FXML
    private Button PassRecoveryButton;

    @FXML
    private Label response_label;


    @FXML
    void initialize(){
        loginButton.setOnAction(e -> {
            try{
                if (LoginRequest.LoginRequestMake(emailField.getText(), passField.getText())){
                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainPageView.fxml"));
                    Scene tableViewScene = new Scene(tableViewParent);

                    //This line gets the Stage information
                    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                    window.setScene(tableViewScene);
                    window.show();
                }
                else {
                    System.out.println("error");
                    response_label.setText("Wrong Email or Password");
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        signupButton.setOnAction(e -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("SignupView.fxml"));
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
