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
 * Stores information and handles functionality for the ModrianArt class
 * 
 * @author CS3151
 * 		   jsmit124
 * 
 * @version 1.0
 */
public class MondrianArt extends Application {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	private double minWidth;
	private double minHeight;
	private double maxLineWidth;

	private Random randomIntGenerator = new Random(); 


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
			this.minHeight = this.displayDialog("Enter desired minimum height: ", "Minimum height: ", HEIGHT / 2);
			this.minWidth = this.displayDialog("Enter desired mimimum width: ", "Minimum width: ", WIDTH / 2);
			this.maxLineWidth = this.displayDialog("Enter desired maximum line width: ", "Maximum line width: ", 50);

			Canvas canvas = new Canvas(600, 600);
			GraphicsContext gc = canvas.getGraphicsContext2D();

			this.handleRectangle(gc, canvas.getWidth(), canvas.getHeight(), 0, 0);

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
	 * Displays a dialog to get user input and returns input
	 */
	private double displayDialog(String header, String contentText, int upperBound) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Settings");
		dialog.setHeaderText(header);
		dialog.setContentText(contentText);
		Optional<String> result = dialog.showAndWait();
		
		try {
			var input = Double.parseDouble(result.get());
			if (input < 1 || input > upperBound) {
				this.displayDialog("Enter a number higher than 0 or try a smaller number", contentText, upperBound);
			}
		} catch (NumberFormatException e) {
			return this.displayDialog("Invalid entry. Please enter a number.", contentText, upperBound);
		} catch (NoSuchElementException e) {
			System.exit(0);
		}

		return Double.parseDouble(result.get());
	}


	/**
	 * Recursive code creating a collection of rectangles
	 */
	private void handleRectangle(GraphicsContext gc, double width, double height, double startX, double startY) {
		if (width <= this.minWidth || height <= this.minHeight) {
			return;
		}
		gc.setStroke(Color.BLACK);
		int newWidth, newHeight;
		
		this.drawRectangleOnCanvas(gc, width, height, startX, startY);
		
		if (width / 2 >= this.minWidth || height / 2 >= this.minHeight) {
			if ((width - this.minWidth) < (height - this.minHeight)) {
				System.out.println("Height value: " + height);
				int randomSplit = this.randomIntGenerator.nextInt((int) height + 1 - ((int) this.minHeight * 2)) + (int) this.minHeight;
				newWidth = (int) width;
				newHeight = randomSplit;
				
				this.handleRectangle(gc, newWidth, newHeight, startX, startY);
				this.handleRectangle(gc, width, height - newHeight, startX, startY + newHeight);
			} else {
				int randomSplit = this.randomIntGenerator.nextInt((int) width + 1 - ((int) this.minWidth * 2)) + (int) this.minWidth;
				newWidth = randomSplit;
				newHeight = (int) height;
				
				this.handleRectangle(gc, newWidth, newHeight, startX, startY);
				this.handleRectangle(gc, width - newWidth, newHeight, startX + newWidth, startY);
			}
		}
	}


	/*
	 * Draws the rectangle of specified dimensions onto the canvas
	 */
	private void drawRectangleOnCanvas(GraphicsContext gc, double width, double height, double startX, double startY) {
		int randomLineWidth = this.randomIntGenerator.nextInt((int) this.maxLineWidth) + 1;
		gc.setLineWidth(randomLineWidth);
		
		int redByte = this.randomIntGenerator.nextInt(256);
		int greenByte = this.randomIntGenerator.nextInt(256);
		int blueByte = this.randomIntGenerator.nextInt(256);
		Color randomColor = Color.rgb(redByte, greenByte, blueByte);
		gc.setFill(randomColor);
		gc.fillRect(startX, startY, width, height);
		
		this.drawBoxSurroundingRectangle(gc, width, height, startX, startY);
	}


	/*
	 * Draws lines around the box with specified starting and ending values
	 */
	private void drawBoxSurroundingRectangle(GraphicsContext gc, double width, double height, double startX,
			double startY) {
		gc.strokeLine(startX, startY, startX + width, startY);
		gc.strokeLine(startX, startY, startX, startY + height);
		gc.strokeLine(startX + width, startY, startX + width, startY + height);
		gc.strokeLine(startX, startY + height, startX + width, startY + height);
	}

}
