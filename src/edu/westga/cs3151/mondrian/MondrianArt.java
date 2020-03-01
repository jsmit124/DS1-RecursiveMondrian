package edu.westga.cs3151.mondrian;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class MondrianArt
 * 
 * @author CS3151
 */
public class MondrianArt extends Application {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.displayDialog();

			Canvas canvas = new Canvas(600, 600);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			this.createGraphics(gc);

			Group group = new Group();
			group.getChildren().add(canvas);
			Scene scene = new Scene(new BorderPane(group), WIDTH, HEIGHT);
			primaryStage.setTitle("Mondrian Art by Firstname Lastname");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Entry point
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param args Not used
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Example code displaying a dialog to get user input
	 */
	private void displayDialog() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Settings");
		dialog.setHeaderText("Enter desired minimum region length: ");
		dialog.setContentText("Length: ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("You entered: " + result.get());
		} else {
			System.out.println("No length was returned by the dialog. Did you cancel or close the dialog?");
		}
		
		try {
			Double.parseDouble(result.get());
		} catch (NumberFormatException e) {
			System.out.println("Invalid entry. Please enter a number.");
			this.displayDialog();
		}
	}

	/**
	 * Example code creating a collection of rectangles
	 */
	private void createGraphics(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		gc.fillRect(0, 0, 300, 600);
		
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(20);
		gc.strokeLine(290, 10, 290, 590);

		gc.setFill(Color.color(0.9, 0.3, 0));
		gc.fillRect(300, 0, 300, 300);

		gc.setFill(Color.YELLOW);
		gc.fillRect(300, 300, 300, 300);
		
		gc.setStroke(Color.GREEN);
		gc.setLineWidth(30);
		gc.strokeLine(315, 300, 585, 300);
	}
}
