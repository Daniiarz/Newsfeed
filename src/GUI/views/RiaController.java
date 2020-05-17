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

public class RiaController {

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
    private ListView<String[]> riaList;

    ObservableList<String[]> data = FXCollections.observableArrayList();

    @FXML
    void initialize(){

        for (String[] arr: Person.ria_news){
            data.add(arr);
        }
        riaList.setItems(data);
        riaList.setCellFactory(param -> new ListCell<String[]>(){
            @Override
            protected void updateItem(String[] item, boolean empty){
                super.updateItem(item, empty);
                if (empty || item == null || item[0] == null) {
                    setText(null);
                } else {
                    HBox hBox = new HBox();
                    VBox vBox = new VBox();
                    Label label = new Label(item[2]);
                    Label status = new Label();
                    Hyperlink hyperlink = new Hyperlink(item[0]);
                    //Need to add function
                    Button addToFav = new Button("Add to Favorites");
                    hyperlink.setOnAction(event -> {
                        try{
                            java.awt.Desktop.getDesktop().browse(new URI(item[1]));
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    });

                    HBox hBox2 = new HBox();
                    hBox2.setSpacing(15);

                    String img_url;
                    if (!item[3].equals("none")){
                        ImageView view = new ImageView(new Image(item[3], 218, 122, false, false, true));
                        hBox.getChildren().addAll(view, vBox);
                        img_url = item[3];
                    }
                    else {
                        ImageView view = new ImageView(new Image(
                                "https://imrussia.org/images/stories/IMR_in_the_Media/Welcomes_Pardon/ria-novosti_hd.jpg",
                                218, 122, false, false, true));
                        hBox.getChildren().addAll(view, vBox);
                        img_url = "https://imrussia.org/images/stories/IMR_in_the_Media/Welcomes_Pardon/ria-novosti_hd.jpg";
                    }
                    hBox.setSpacing(20);
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
                                        field("description_or_date_pub", item[2]).
                                        field("link", item[1]).
                                        field("image_link", img_url)
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
                    label.setFont(Font.font(16));
                    hyperlink.setFont(Font.font(20));
                    vBox.getChildren().addAll(hyperlink, label, hBox2);
                    vBox.setSpacing(20);

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
