package GUI.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PassResetController {

    @FXML
    private TextField resetEmailField;

    @FXML
    private PasswordField resetPassField;

    @FXML
    private PasswordField resetPassConfirm;

    @FXML
    private Button resetConfirmationButton;

    @FXML
    private Button backToLoginButton;

    @FXML
    void initialize() {
        backToLoginButton.setOnAction(e -> {
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
        });
    }
}
