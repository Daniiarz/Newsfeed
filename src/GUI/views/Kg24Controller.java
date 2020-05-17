package GUI.views;

import GUI.Person;
import Parsers.Parser;
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

public class Kg24Controller {

    @FXML
    private Button NyTimesButton;

    @FXML
    private Button kg24Button;

    @FXML
    private Button riaButton;

    @FXML
    private Button FavoritesButton;

    @FXML
    private Button backToMainButton;

    @FXML
    private ListView<String[]> Kg24List;

    ObservableList<String[]> data = FXCollections.observableArrayList();

    @FXML
    void initialize(){
        for (String[] arr: Parser.get24kgNews()){
            data.add(arr);
        }
        Kg24List.setItems(data);
        Kg24List.setCellFactory(param -> new ListCell<String[]>(){
            @Override
            protected void updateItem(String[] item, boolean empty){
                super.updateItem(item, empty);
                if (empty || item == null || item[0] == null) {
                    setText(null);
                }
                else{
                    VBox vBox = new VBox();
                    Label status = new Label();
                    Label timePublished = new Label(item[1]);
                    Hyperlink Title = new Hyperlink(item[0]);
                    Button addToFav = new Button("Add to Favorites");
                    Title.setFont(Font.font(20));
                    timePublished.setFont(Font.font(16));
                    addToFav.setFont(Font.font(14));
                    vBox.setSpacing(20);

                    HBox hBox2 = new HBox();
                    hBox2.setSpacing(15);

                    addToFav.setOnAction(e -> {
                        if (FavoritesRequest.CheckFavorites(item[0])){
                            status.setStyle("-fx-color: red");
                            status.setText("Article already added to Favorites");

                        }
                        else {
                            try{
                                HttpResponse<String> postRequest = Unirest.post("http://localhost:8000/articles/").
                                        header("Authorization", "Token " + Person.token).
                                        field("title", item[0]).
                                        field("description_or_date_pub", item[1]).
                                        field("link", item[2]).
                                        field("image_link", "https://gdb.rferl.org/B80F2868-B60A-413E-A52D-B89388B9EC09_w1200_r1_s.jpg")
                                        .asString();
                                System.out.println(postRequest.getStatus());
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                            status.setStyle("-fx-color: green");
                            status.setText("Article added to Favorites");
                        }

                    });
                    hBox2.getChildren().addAll(addToFav, status);

                    Title.setOnAction(event -> {
                        try{
                            java.awt.Desktop.getDesktop().browse(new URI(item[2]));
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    });
                    vBox.getChildren().addAll(Title, timePublished, hBox2);
                    setGraphic(vBox);

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
