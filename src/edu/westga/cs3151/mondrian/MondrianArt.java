package edu.westga.cs3151.mondrian;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

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

	private double minWidth;
	private double minHeight;
	private double maxLineWidth;

	private Random randomIntGenerator = new Random(WIDTH);


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


	@Override
	public void start(Stage primaryStage) {
		try {
			this.minHeight = this.displayDialog("Enter desired minimum height: ", "Minimum height: ");
			this.minWidth = this.displayDialog("Enter desired mimimum length: ", "Minimum width: ");
			this.maxLineWidth = this.displayDialog("Enter desired maximum line width: ", "Maximum line width: ");

			Canvas canvas = new Canvas(600, 600);
			GraphicsContext gc = canvas.getGraphicsContext2D();

			this.drawRectangle(gc, canvas.getWidth(), canvas.getHeight());

			Group group = new Group();
			group.getChildren().add(canvas);
			Scene scene = new Scene(new BorderPane(group), WIDTH, HEIGHT);
			primaryStage.setTitle("Mondrian Art by Justin Smith");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Example code displaying a dialog to get user input
	 */
	private double displayDialog(String header, String contentText) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Settings");
		dialog.setHeaderText(header);
		dialog.setContentText(contentText);
		Optional<String> result = dialog.showAndWait();
		
		try {
			var input = Double.parseDouble(result.get());
			if (input < 1) {
				this.displayDialog("Enter a number higher than 0", contentText);
			}
		} catch (NumberFormatException e) {
			return this.displayDialog("Invalid entry. Please enter a number.", contentText);
		} catch (NoSuchElementException e) {
			System.exit(0);
		}

		return Double.parseDouble(result.get());
	}


	/**
	 * Recursive code creating a collection of rectangles
	 */
	private void drawRectangle(GraphicsContext gc, double width, double height) {
		//setup 
		gc.setStroke(Color.BLACK);
		
		//expansion: random line width
		int randomLineWidth = this.randomIntGenerator.nextInt((int) this.maxLineWidth) + 1;
		gc.setLineWidth(randomLineWidth);
		
		//determine if region should be split or not - DONE
		if (width < this.minWidth && height < this.minHeight) {
			return;
		}
		
		//determine is region should be split vertically or horizontally
		if (width < height) {
			//draw horizontal line
			//determine split of region randomly
			//TODO
		} else {
			//draw vertical line
			//determine split of region randomly
			//TODO
		}
		
		//draw random region and fill with random color
		//generate random color for fill
		int redByte = this.randomIntGenerator.nextInt(256);
		int greenByte = this.randomIntGenerator.nextInt(256);
		int blueByte = this.randomIntGenerator.nextInt(256);
		Color randomColor = Color.rgb(redByte, greenByte, blueByte);
		gc.setFill(randomColor);
		
		//recursively call this method
		//TODO
		
		//expansions:
		//	use pattern fill instead of solid fill
		//TODO
		
		
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
