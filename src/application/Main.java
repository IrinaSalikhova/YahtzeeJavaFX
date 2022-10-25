package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private Button roll;
	private boolean[] clicked;
	private Button[] dice;
	private Image[] diceImages;
	private Label[] hints;    // display textfields for hints
	 private Label[] scores;    // display textfields for scores
	   private String[] hintLabels = {  // Labels of 18 hints
	      "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes", 
	      "Bonus 35", "Upper total", 
	      "3 of a kind", "4 of a kind", "Full house", "Small straight", "Large straight", "Yahtzee", "Chance",
	      "Yahtzee bonus", "Lower total", "Total"
	   };
		private int rollCounter;
		private int moveCounter;
		private ScoreBoard myScoreboard = new ScoreBoard();
		private int[] valueOfTable;
		private Dice myDice = new Dice();

		
	   EventHandler rollhandler = evt -> {
	if (moveCounter != 13) {
		   if (rollCounter == 0) {
		
			   myDice.roll();
			   for (int i = 0; i<dice.length; ++i) {
				   int x = myDice.get(i).dieValue();
		    	   ImageView image = new ImageView(diceImages[x-1]);
			       image.setFitHeight(80);
			       image.setPreserveRatio(true);
			       dice[i].setGraphic(image);
			       dice[i].setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			   }
			   rollCounter ++;
			   myScoreboard.countScore(myDice);
				  for (int i = 0; i < scores.length; ++i) {
				    	
				  scores[i].setText(String.valueOf(myScoreboard.get(i).boardElementValue()));
				  }
		   }
		   else if (rollCounter > 0 && rollCounter < 3) {
			   myDice.roll();
			   for (int i = 0; i<dice.length; ++i) {
				   int x = myDice.get(i).dieValue();
		    	   ImageView image = new ImageView(diceImages[x-1]);
			       image.setFitHeight(80);
			       image.setPreserveRatio(true);
			       dice[i].setGraphic(image);
			       dice[i].setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			   }
			   rollCounter ++;
			   myScoreboard.countScore(myDice);
				  for (int i = 0; i < scores.length; ++i) {
				    	
				  scores[i].setText(String.valueOf(myScoreboard.get(i).boardElementValue()));
				  }
		   }
		   else {
		   System.out.print("next move!"); 
		   }
	}
	else {
		Alert alertFinal = new Alert(AlertType.INFORMATION);
		alertFinal.setTitle("Game over");
		alertFinal.setHeaderText("Congratilations!");
		alertFinal.setContentText("Your result is " + String.valueOf(myScoreboard.get(17).boardElementValue()));

		alertFinal.showAndWait();
	}
	   };
	   

	   
	public void start(Stage primaryStage) throws Exception {

		rollCounter = 0;
		moveCounter = 0;
		
		// roll button
		  roll = new Button("Roll dice");
	         roll.setOnAction(rollhandler);  // Register event handler
	         roll.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  // full-width
	         roll.setStyle("-fx-background-color: #1A090D; -fx-text-fill: #F4F1BB; -fx-font-weight: bold;");
	         roll.setCursor(Cursor.HAND);
	         
	         
	  // images for dice
	    diceImages = new Image[6];
	    diceImages[0] = new Image("C:\\Users\\irish\\eclipse-workspace\\YahtzeeJavaFX\\src\\dice1.png");
	    diceImages[1] = new Image("C:\\Users\\irish\\eclipse-workspace\\YahtzeeJavaFX\\src\\dice2.png");
	    diceImages[2] = new Image("C:\\Users\\irish\\eclipse-workspace\\YahtzeeJavaFX\\src\\dice3.png");
	    diceImages[3] = new Image("C:\\Users\\irish\\eclipse-workspace\\YahtzeeJavaFX\\src\\dice4.png");
	    diceImages[4] = new Image("C:\\Users\\irish\\eclipse-workspace\\YahtzeeJavaFX\\src\\dice5.png");
	    diceImages[5] = new Image("C:\\Users\\irish\\eclipse-workspace\\YahtzeeJavaFX\\src\\dice6.png");
	    
	         
	  // Setup a HBox for 5 dice
	   HBox diceBox = new HBox();
	   diceBox.setPadding(new Insets(15, 15, 15, 15));
	   diceBox.setSpacing(15);
	   diceBox.setAlignment(Pos.CENTER);
	   diceBox.setStyle("-fx-background-color: #A7ADC6;");
	   
	   clicked = new boolean[5];
	   for (int i = 0; i< clicked.length; ++i) {
		   clicked[i] = false;
	   }
	   
	   dice = new Button[5];
	   for (int i = 0; i < dice.length; ++i) {
	       dice[i] = new Button();
	       dice[i].setPadding(new Insets(0, 0, 0, 0));;
	       dice[i].setMaxSize(80,80);  // full-width
	       dice[i].setAlignment(Pos.CENTER);
	       dice[i].setCursor(Cursor.HAND);
	       int k = i;
	       dice[i].setOnAction((click) -> {
	    	   if (rollCounter == 0) {
	    	   }
	    	   else {
	    		   if (clicked[k]) {
	    	   dice[k].setStyle("-fx-border-color: #ED6A5A; -fx-border-width: 0px;");
	    	   }
	    	   else {
		    	   dice[k].setStyle("-fx-border-color: #ED6A5A; -fx-border-width: 5px;");
		    	   }
	    	   myDice.get(k).holdDie();
	    	   clicked[k] = !clicked[k];
	    	   }
	       });
	       
	      
	       ImageView image = new ImageView(diceImages[0]);
	       image.setFitHeight(80);
	       image.setPreserveRatio(true);
	       dice[i].setGraphic(image);
	       dice[i].setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	       diceBox.getChildren().add(dice[i]);  
	    }

	  // Setup a GridPane for 2x18 TextField scoretable
	
	 	int numCols = 2;
	    int numRows = 18;
	    GridPane paneScoreboard = new GridPane();
	    paneScoreboard.setPadding(new Insets(15, 15, 15, 15));  // top, right, bottom, left
	    paneScoreboard.setVgap(10);  // Vertical gap between nodes
	    paneScoreboard.setHgap(15);  // Horizontal gap between nodes
	    paneScoreboard.setStyle("-fx-background-color: #A7ADC6; -fx-text-fill: #1A090D; -fx-font-weight: bold;");
	    // Setup 2 columns of equal width, fill parent
	    ColumnConstraints[] columns = new ColumnConstraints[numCols];
	    for (int i = 0; i < numCols; ++i) {
	       columns[i] = new ColumnConstraints();
	       columns[i].setHgrow(Priority.ALWAYS) ;  // Allow column to grow
	       columns[i].setFillWidth(true);  // Ask nodes to fill space for column
	       paneScoreboard.getColumnConstraints().add(columns[i]);
	    }
	    
	 // Setup TextFields and add to GridPane; and event handler
	    hints = new Label[18];
	    for (int i = 0; i < hints.length; ++i) {
	       hints[i] = new Label(hintLabels[i]);
	       hints[i].setMaxSize(100,100);  // full-width
	       hints[i].setAlignment(Pos.CENTER);
	       paneScoreboard.add(hints[i], 0, i);  // control, col, row
	    }
	    
	    		
	    scores = new Label[18];
	    for (int i = 0; i < scores.length; ++i) {
	    	
	    	scores[i] = new Label(String.valueOf(myScoreboard.get(i).boardElementValue()));
	    	scores[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);  // full-width
	       paneScoreboard.add(scores[i], 1, i);  // control, col, row
	       scores[i].setAlignment(Pos.CENTER);
	    }
	    
	    for (int i = 0; i < 6; ++i) {
	    	   int k = i;
	    	   scores[i].setStyle(" -fx-text-fill: #56667A; ");
	    	   scores[i].setCursor(Cursor.HAND);
		       scores[i].setOnMouseClicked((click) -> {
		    	   myScoreboard.get(k).holdBoardElement();
		  
		    	   scores[k].setOnMouseClicked(null);
		    	   scores[k].setCursor(Cursor.DEFAULT);
		    	   scores[k].setStyle(" -fx-text-fill: #1A090D; ");
		    	   moveCounter ++;
		    	   rollCounter = 0;
		    	   if (moveCounter == 13) {
		    		   Alert alertFinal = new Alert(AlertType.INFORMATION);
		    			alertFinal.setTitle("Game over");
		    			alertFinal.setHeaderText("Congratulations!");
		    			alertFinal.setContentText("Your result is " + String.valueOf(myScoreboard.get(17).boardElementValue()));

		    			alertFinal.showAndWait();
		    	   }
		    	  Dice newMove = new Dice();
		    	   myScoreboard.countScore(newMove);
					  for (int j = 0; j < scores.length; ++j) {
					    	
					  scores[j].setText(String.valueOf(myScoreboard.get(j).boardElementValue()));
					  }
					  for (int j = 0; j < dice.length; ++j) {
						  if (clicked[j]) {
					    	   dice[j].setStyle("-fx-border-color: #ED6A5A; -fx-border-width: 0px;");
					    	  
					    	   myDice.get(j).holdDie();
					    	   clicked[j] = !clicked[j];
						  }		    	   
					  }
		       });
		     
	    }
	    for (int i = 8; i < 15; ++i) {
	    	  int k = i;
	    	   scores[i].setStyle(" -fx-text-fill: #56667A; ");
	    	   scores[i].setCursor(Cursor.HAND);
		       scores[i].setOnMouseClicked((click) -> {
		    	   myScoreboard.get(k).holdBoardElement();
		  
		    	   scores[k].setOnMouseClicked(null);
		    	   scores[k].setCursor(Cursor.DEFAULT);
		    	   scores[k].setStyle(" -fx-text-fill: #1A090D; ");
		    	   moveCounter ++;
		    	   rollCounter = 0;
		    	   if (moveCounter == 13) {
		    		   Alert alertFinal = new Alert(AlertType.INFORMATION);
		    			alertFinal.setTitle("Game over");
		    			alertFinal.setHeaderText("Congratulations!");
		    			alertFinal.setContentText("Your result is " + String.valueOf(myScoreboard.get(17).boardElementValue()));

		    			alertFinal.showAndWait();
		    	   }
		    	   Dice newMove = new Dice();
		    	   myScoreboard.countScore(newMove);
					  for (int j = 0; j < scores.length; ++j) {
					    	
					  scores[j].setText(String.valueOf(myScoreboard.get(j).boardElementValue()));
					  }
					  for (int j = 0; j < dice.length; ++j) {
						  if (clicked[j]) {
					    	   dice[j].setStyle("-fx-border-color: #ED6A5A; -fx-border-width: 0px;");
					    	  
					    	   myDice.get(j).holdDie();
					    	   clicked[j] = !clicked[j];
						  }		    	   					    	   
				  }
		       });
	    }
	    

		BorderPane root = new BorderPane();
		 root.setPadding(new Insets(0, 0, 0, 0));  // top, right, bottom, left
		 
	root.setTop(diceBox);
	root.setCenter(roll);
		    root.setBottom(paneScoreboard); // Center zone contains the GridPane of Buttons
		 root.setStyle("-fx-border-color: #ED6A5A; -fx-border-width: 10px; -fx-font-size: 14px;");;   
		Scene scene = new Scene(root,550,735);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		 primaryStage.setTitle("Yahtzee");
		primaryStage.show();

	    }
	

	public static void main(String[] args) {
		launch(args);
	}
}
