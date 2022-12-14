package com.example.snakeandladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Random;

public class PlayAreacontroller {
    @FXML
    Text number;
    @FXML
    Text changeturn;
    @FXML
    ImageView Player1,Player2;
    int turn=1;

    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeandladderCoordinateChanges;//saving start and point for the snake and ladder

    @FXML
    public void roll(MouseEvent event){
    //System.out.print("dice is rolling");
        getSnakeandLadderCoordinates();
    Random random=new Random();
    int rolling = random.nextInt(6)+1; //generating rondom numbers range of random numbers is 0-5 so add 1 to make it 1-6
    number.setText(" "+rolling); //for the output
        //System.out.print(" "+rolling+" ");

        if(turn==1) {
            Pair<Double,Double> moveCoordinates= movement(rolling,Player1.getTranslateX(),Player1.getTranslateY());
            //transalate gives x and y coordinates and the data will br in double value

            Player1.setTranslateX(moveCoordinates.getKey());
            Player1.setTranslateY(moveCoordinates.getValue());

            if(snakeandladderCoordinateChanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                Player1.setTranslateX(snakeandladderCoordinateChanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                Player1.setTranslateY(snakeandladderCoordinateChanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(Player1.getTranslateX(),Player1.getTranslateY());
        }
        else{
            Pair<Double,Double> moveCoordinates= movement(rolling,Player2.getTranslateX(),Player2.getTranslateY());
            //transalate gives x and y coordinates and the data will br in double value

            Player2.setTranslateX(moveCoordinates.getKey());
            Player2.setTranslateY(moveCoordinates.getValue());

            if(snakeandladderCoordinateChanges.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                Player2.setTranslateX(snakeandladderCoordinateChanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                Player2.setTranslateY(snakeandladderCoordinateChanges.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(Player2.getTranslateX(),Player2.getTranslateY());
        }
        if(rolling!=6) {
            if (turn == 1) { //changing the players turn only if the number on dice is not 6
                turn = 2;
                changeturn.setText("Player 2's turn");
            } else {
                turn = 1;
                changeturn.setText("Player 1's turn");
            }
        }
}
   Pair<Double,Double> movement(int rolling, double X, double Y){
       Double moveX=X;
       Double moveY=Y;


       if(moveY%100==0) { //bcz odd rows are divided by 100 i.e. 0,100,200 and even rows by 50 i.e. 50,150...
           moveX += rolling * 50;//update x
           if (moveX > 500) {//X is out of bound bring it to the next row from bottom
               moveX = 500 * 2 - moveX + 50;//+50 bcz for x coordinate the 1st box is 50 and for y coordinate 1st box is 0
               moveY -= 50; //update y
           }
       }
       else{
           moveX -= rolling * 50;//update x
           if(moveX<50){
               if(moveY==-450)
                   return (new Pair<>(X,Y));
               moveX=-1*(moveX-50); //-50 to move up in y and moving right
               moveY-=50;
           }
       }
       return new javafx.util.Pair<>(moveX,moveY);
   }

   void checkWin(Double X,Double Y){
       Alert alert=new Alert(Alert.AlertType.INFORMATION); //kind of alert needed
       alert.setHeaderText("Winner");
        if(X==50 && Y==-450){
            if(turn==1) {
                alert.setContentText("Player 1 has won");//context of alert box
                alert.showAndWait(); //alert box waits for an action
                //System.out.println("Player 1 has won");
            }
            else {
                alert.setContentText("Player 2 has won");//context of alert box
                alert.showAndWait();
                //System.out.println("Player 2 has won");
            }
        }
   }
   void getSnakeandLadderCoordinates(){
       snakeandladderCoordinateChanges=new HashMap<>();//initialize hashmap
       snakeandladderCoordinateChanges.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(200.0,0.0),new Pair<>(350.0,-50.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(200.0,-50.0),new Pair<>(350.0,0.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(450.0,0.0),new Pair<>(500.0,-150.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(50.0,-100.0),new Pair<>(100.0,-200.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(400.0,-100.0),new Pair<>(200.0,-400.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(500.0,-250.0),new Pair<>(350.0,-300.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(350.0,-250.0),new Pair<>(350.0,-150.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(100.0,-300.0),new Pair<>(100.0,-50.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(200.0,-300.0),new Pair<>(50.0,-250.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(500.0,-350.0),new Pair<>(500.0,-450.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(50.0,-350.0),new Pair<>(50.0,-450.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(350.0,-400.0),new Pair<>(200.0,-150.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(400.0,-450.0),new Pair<>(400.0,-350.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(300.0,-450.0),new Pair<>(300.0,-350.0));//add values
       snakeandladderCoordinateChanges.put(new Pair<>(150.0,-450.0),new Pair<>(100.0,-350.0));//add values

   }
}
