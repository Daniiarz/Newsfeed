package GUI.views;

import GUI.Main;
import GUI.Person;
import Parsers.FirstPageLogic;
import Parsers.Parser;
import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URI;

public class MainPageController {

    @FXML
    private ImageView firstImage;

    @FXML
    private Button NyTimesButton;

    @FXML
    private Button kg24Button;

    @FXML
    private Button riaButton;

    @FXML
    private Button FavoritesButton;

    @FXML
    private Button logOutButton;


    @FXML
    private ImageView secondImage;

    @FXML
    private Hyperlink secondHL;

    @FXML
    private ImageView thirdImage;

    @FXML
    private Hyperlink thirdHl;

    @FXML
    private ImageView fourthImage;

    @FXML
    private Hyperlink fourthHL;

    @FXML
    private ImageView fifthImage;

    @FXML
    private Hyperlink fifthHL;

    @FXML
    private Hyperlink firstArticleHyperLink;

    @FXML
    private ImageView sixthImage;

    @FXML
    private Hyperlink sixthHl;

    @FXML
    private ImageView seventhImg;

    @FXML
    private Hyperlink sevethHl;

    @FXML
    void initialize(){
        Person.Refresh();
        //First Article
        String[] requiredFirstP = FirstPageLogic.getFirstArticle();
        Image getFistImage = new Image(requiredFirstP[0], true);
        firstImage.setImage(getFistImage);
        firstArticleHyperLink.setOnAction(e -> {
            try{
                java.awt.Desktop.getDesktop().browse(new URI(requiredFirstP[2]));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        firstArticleHyperLink.setText(requiredFirstP[1]);
        //Second Article
        for (int i = 1; i <= 2; i++){
                switch (i){
                    case 1:
                        secondHL.setText(Person.kg_news.get(i)[0]);
                        secondImage.setImage(new Image("https://gdb.rferl.org/B80F2868-B60A-413E-A52D-B89388B9EC09_w1200_r1_s.jpg", true));
                        String url = Person.kg_news.get(i)[2];
                        secondHL.setOnAction(e -> {
                            try{
                                java.awt.Desktop.getDesktop().browse(new URI(url));
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                        });
                        break;
                    case 2:
                        fourthHL.setText(Person.kg_news.get(i)[0]);
                        fourthImage.setImage(new Image("https://gdb.rferl.org/B80F2868-B60A-413E-A52D-B89388B9EC09_w1200_r1_s.jpg", true));
                        String url2 = Person.kg_news.get(i)[2];
                        fourthHL.setOnAction(e -> {
                            try{
                                java.awt.Desktop.getDesktop().browse(new URI(url2));
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                        });
                }
        }
        String[] thirdArticle = Person.ny_news.get(1);
        thirdHl.setText(thirdArticle[0]);
        Image image3 = new Image(thirdArticle[3], true);
        thirdHl.setOnAction(e -> {
            try{
                java.awt.Desktop.getDesktop().browse(new URI(thirdArticle[2]));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        thirdImage.setImage(image3);

        String[] sixthArticle = Person.ny_news.get(2);
        sixthHl.setText(sixthArticle[0]);
        Image image6 = new Image(sixthArticle[3], true);
        sixthHl.setOnAction(e -> {
            try{
                java.awt.Desktop.getDesktop().browse(new URI(sixthArticle[2]));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        sixthImage.setImage(image6);
        for (int i = 1; i <= 2; i++) {
            if (i == 1){
                String url = Person.ria_news.get(i)[1];
                fifthHL.setOnAction(e -> {
                    try{
                        java.awt.Desktop.getDesktop().browse(new URI(url));
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                });
                if (!Person.ria_news.get(i)[3].equals("none")){
                    Image image5 = new Image(Person.ria_news.get(i)[3], true);
                    fifthImage.setImage(image5);
                }else {
                    Image image5 = new Image("https://imrussia.org/images/stories/IMR_in_the_Media/Welcomes_Pardon/ria-novosti_hd.jpg", true);
                    fifthImage.setImage(image5);
                }

                fifthHL.setText(Person.ria_news.get(i)[0]);
            }
            else {
                String url = Person.ria_news.get(i)[1];
                sevethHl.setOnAction(e -> {
                    try{
                        java.awt.Desktop.getDesktop().browse(new URI(url));
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                });
                if (!Person.ria_news.get(i)[3].equals("none")){
                    Image image5 = new Image(Person.ria_news.get(i)[3]);
                    seventhImg.setImage(image5);
                }else {
                    Image image5 = new Image("https://imrussia.org/images/stories/IMR_in_the_Media/Welcomes_Pardon/ria-novosti_hd.jpg", true);
                    seventhImg.setImage(image5);
                }
                sevethHl.setText(Person.ria_news.get(i)[0]);
            }
        }

        NyTimesButton.setOnAction(e -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("NyTimesView.fxml"));
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
        kg24Button.setOnAction(e -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("Kg24View.fxml"));
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
        riaButton.setOnAction(e -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("RiaView.fxml"));
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
        logOutButton.setOnAction(e -> {
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
        FavoritesButton.setOnAction(e -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("FavoritesView.fxml"));
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
