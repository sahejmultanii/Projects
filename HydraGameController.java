package com.example.smultan7_assignment1;

//required imported classes
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Random;

public class HydraGameController {

    //images that will be used for the hydra heads
    Image head1 = new Image("file:src/main/resources/com/example/smultan7_assignment1/HeadSize1.png");
    Image head2 = new Image("file:src/main/resources/com/example/smultan7_assignment1/HeadSize2.png");
    Image head3 = new Image("file:src/main/resources/com/example/smultan7_assignment1/HeadSize3.png");
    Image head4 = new Image("file:src/main/resources/com/example/smultan7_assignment1/HeadSize4.png");
    Image head5 = new Image("file:src/main/resources/com/example/smultan7_assignment1/HeadSize5.png");
    Image head6 = new Image("file:src/main/resources/com/example/smultan7_assignment1/HeadSize6.png");

    //array of images created to be used
    Image headArr[] = {head1, head2, head3, head4, head5, head6};
    //arraylist used to check collisions
    private ArrayList <ImageView> hydraPosition = new ArrayList<>();

    private int headCount = 0;//counter to keep track of the number of heads

    //controls and container created to make the hydra grid and the buttons to play and reset the game
    @FXML
    private Button playButton;
    @FXML
    private Slider slideSize;
    @FXML
    private AnchorPane hydraGrid;

    private static int randomHeadNum(){//method to choose if 2 heads get created or 3 per click
       return (int) ( 2 + (Math.random() * (4-2)));
    }

    @FXML
    private void imgAdder(int i){//method sets the dimensions of the images and will also see if there is a collision
        ImageView hydra = new ImageView();
        Random randomValue = new Random();

        //heads are set to the appropriate dimensions
        hydra.setFitWidth(40);
        hydra.setFitHeight(40);
        hydra.setImage(headArr[i]);

        //adds head to random positions
        hydraGrid.getChildren().add(hydra);
        hydraPosition.add(hydra);
        hydra.setTranslateY(randomValue.nextInt(700));
        hydra.setTranslateX(randomValue.nextInt(700));

        while(collision(hydra)){//checks collision status and adds new heads
            hydra.setTranslateY(randomValue.nextInt(700));
            hydra.setTranslateX(randomValue.nextInt(700));

            hydraGrid.setOnMouseClicked(mouseEvent -> {
                hydraGrid.getChildren().remove(hydra);
                headCount++;
                victory();//displays victory message

                //goes through each case
                if (hydra.getImage().equals(headArr[1])) {
                    for (int n = 0; n < randomHeadNum(); n++) {
                        imgAdder(0);
                    }
                } else if (hydra.getImage().equals(headArr[2])) {
                    for (int n = 0; n < randomHeadNum(); n++) {
                        imgAdder(1);
                    }
                } else if (hydra.getImage().equals(headArr[3])) {
                    for (int n = 0; n < randomHeadNum(); n++) {
                        imgAdder(2);
                    }
                } else if (hydra.getImage().equals(headArr[4])) {
                    for (int n = 0; n < randomHeadNum(); n++) {
                        imgAdder(3);
                    }
                } else if (hydra.getImage().equals(headArr[4])) {
                    for (int n = 0; n < randomHeadNum(); n++) {
                        imgAdder(4);
                    }
                }
            });
        }
    }

    private boolean collision(ImageView hydraHeadLocation){//method is used to see if there is a collision between hydra
        boolean collision = false;
        for(ImageView n : hydraPosition){
            if (hydraHeadLocation !=n){
                if (n.getBoundsInParent().intersects(hydraHeadLocation.getBoundsInParent())){
                    collision = true;
                    break;
                }
            }
        }
        return collision;
    }

    @FXML
    protected void resetButtonClicked() {//method for when rest button is clicked
        playButton.setDisable(false);
        slideSize.setDisable(false);
        hydraGrid.getChildren().clear();
        slideSize.setValue(2);
    }

    @FXML
    protected void playButtonClicked() {//method for when the play button is clicked
        Random randomValue = new Random();
        int numSlider = (int) slideSize.getValue(); //gets user value from slider and adjust accordingly
        playButton.setDisable(true);
        slideSize.setDisable(true);
        ImageView hydra = new ImageView();
        hydra.setImage(headArr[numSlider-1]);

        //hydra heads are 40x40 pixels and are positioned randomly
        hydra.setFitWidth(40);
        hydra.setFitHeight(40);
        hydra.setTranslateY(randomValue.nextInt(700));
        hydra.setTranslateX(randomValue.nextInt(700));
        hydraGrid.getChildren().add(hydra);
        hydraPosition.add(hydra);

        hydraGrid.setOnMouseClicked(mouseEvent -> {
            hydraGrid.getChildren().remove(hydra);
            headCount++;

            //goes through each case
            if (hydra.getImage().equals(headArr[5])) {
                for (int n = 0; n < randomHeadNum(); n++) {
                    imgAdder(4);
                }
            } else if (hydra.getImage().equals(headArr[4])) {
                for (int n = 0; n < randomHeadNum(); n++) {
                    imgAdder(4);
                }
            } else if (hydra.getImage().equals(headArr[3])) {
                for (int n = 0; n < randomHeadNum(); n++) {
                    imgAdder(2);
                }
            } else if (hydra.getImage().equals(headArr[2])) {
                for (int n = 0; n < randomHeadNum(); n++) {
                    imgAdder(1);
                }
            } else if (hydra.getImage().equals(headArr[1])) {
                for (int n = 0; n < randomHeadNum(); n++) {
                    imgAdder(0);
                }
            }
        });
    }

    private void victory(){//method used to show a victory message
        if (hydraGrid.getChildren().isEmpty()){
            Label banner = new Label("CONGRATS! YOU CUT THE HEADS OFF OF " + headCount + " HYDRAS!");
            hydraGrid.getChildren().add(banner);

            //victory message font settings
            banner.setFont(new Font("Times New Roman", 30));
            banner.setTextFill(Color.color(0,0.6,0.8));

            //message position
            banner.setTranslateY(300);
            banner.setTranslateX(350);
            banner.setAlignment(Pos.TOP_CENTER);
        }
    }
}