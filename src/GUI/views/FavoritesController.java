package GUI.views;

import GUI.Person;
import Requests.FavoritesRequest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URI;
import java.util.Collections;

public class FavoritesController {

    @FXML
    private Button NyTimesButton;

    @FXML
    private Button kg24Button;

    @FXML
    private Button riaButton;

    @FXML
    private Button backToMainButton;

    @FXML
    private Button FavoritesButton;

    @FXML
    private ListView<String[]> favoritesList;

    ObservableList<String[]> data = FXCollections.observableArrayList();

    @FXML
    void initialize(){
        for (String[] arr: FavoritesRequest.FavoritesRequestMake()){
            data.add(arr);
        }
        favoritesList.setItems(data);

        favoritesList.setCellFactory(param -> new ListCell<String[]>(){
            @Override
            protected void updateItem(String[] item, boolean empty){
                super.updateItem(item, empty);
                if (empty || item == null || item[0] == null) {
                    setText(null);
                }
                else{
                    VBox vBox = new VBox();
                    Label timePublished = new Label(item[1]);
                    Hyperlink Title = new Hyperlink(item[0]);
                    Button deleteFromFav = new Button("Delete From Favorites");
                    Title.setFont(Font.font(20));
                    timePublished.setFont(Font.font(16));
                    deleteFromFav.setFont(Font.font(14));
                    vBox.setSpacing(10);
                    Title.setOnAction(event -> {
                        try{
                            java.awt.Desktop.getDesktop().browse(new URI(item[2]));
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    });
                    ImageView view = new ImageView(new Image(item[3], 205, 137, false, false, true));
                    vBox.getChildren().addAll(Title, timePublished, deleteFromFav);
                    HBox hBox = new HBox();
                    hBox.getChildren().addAll(view, vBox);
                    hBox.setSpacing(15);
                    deleteFromFav.setOnAction(e -> {
                        try{
                            HttpResponse<String> postRequest = Unirest.delete(item[4]).
                                    header("Authorization", "Token " + Person.token)
                                    .asString();
                            System.out.println(postRequest.getStatus());
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
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
                    setGraphic(hBox);

                }
            }
        });
        backToMainButton.setOnAction(e -> {
            try{
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainPageView.fxml"));
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


    }

}