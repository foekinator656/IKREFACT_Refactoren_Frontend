package nl.hsleiden.ipsen2.bouncer.front.views;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ViewHelper {
  public static final ArrayList<Vec2d> coordinates = new ArrayList<>();
  private static final String RED = "#FF0000";
  private static final String BLUE = "#0000FF";
  private static final String GREEN = "#00FF00";
  private static final String YELLOW = "#FFFF00";

  /**
   * Set location of JavaFX node by passing x and y coordinates
   *
   * @param node The JavaFX node you want to position
   * @param x Target X Coordinate for your node
   * @param y Target Y Coordinate for your node
   */
  public static void setNodeCoordinates(Node node, int x, int y) {
    node.setTranslateX(x);
    node.setTranslateY(y);
  }

  /**
   * Apply a JavaFX drop shadow effect to a node
   *
   * @param node The JavaFX node you want to apply a drop shadow to
   */
  public static void applyDropShadow(Node node) {
    DropShadow shadow = new DropShadow();
    shadow.setColor(Color.BLACK);
    shadow.setRadius(2);
    shadow.setOffsetX(1);
    shadow.setOffsetX(1);
    node.setEffect(shadow);
  }

  /**
   * Creates a KeezBoard Logo JavaFX Node
   *
   * @param img Pass a null Image object
   * @param fitHeight Desired height of the logo
   * @return Returns an ImageView Node of the logo
   */
  public static ImageView createLogo(Image img, int fitHeight) throws FileNotFoundException {

    img = new Image(loadResource("/assets/branding/keez.png"));

    ImageView imageView = new ImageView(img);
    imageView.setPreserveRatio(true);
    imageView.setFitHeight(fitHeight);
    ViewHelper.applyDropShadow(imageView);

    return imageView;
  }

  /**
   * Creates a UI Divider for the GameBoard View to seperate UI elements
   *
   * @param width Width of the Divider in pixels
   * @param height Height of the Divider in pixels
   * @return Returns a rectangle Node with specified width and height
   */
  public static Rectangle createUIDividers(int width, int height) {
    Rectangle rect = new Rectangle();

    rect.setHeight(height);
    rect.setWidth(width);
    rect.setStyle("-fx-fill: grey; -fx-stroke: black; -fx-stroke-width: 5;" + "");
    rect.setStrokeType(StrokeType.INSIDE);

    return rect;
  }

  /**
   * Creates a label formatted as a header
   *
   * @param txt Text in the label
   * @return returns label formatted as header with text
   */
  public static Label headerLabelBuilder(String txt) {
    Label lbl = new Label();

    lbl.setPrefWidth(580);
    lbl.setPrefHeight(50);
    lbl.setText(txt);
    lbl.setStyle("-fx-font-family: 'Comic Sans MS';-fx-font-size: 30; -fx-text-fill: #000000");

    return lbl;
  }

  public static InputStream loadResource(String resourcePath) throws FileNotFoundException {
    InputStream resourceStream = ViewHelper.class.getResourceAsStream(resourcePath);
    if (resourceStream == null)
      throw new FileNotFoundException("The resource at " + resourcePath + " could not be found.");
    return resourceStream;
  }

  /**
   * Creates a label to display the player who's turn it currently is with appropriate formatting
   *
   * @param player player number who's turn it is currently
   * @return returns label with formatted player number and color
   */
  public static Label playersTurnDisplay(int player) {
    String playerColor = null;
    String playerText = "Player ";
    String suffix = "'s turn";

    Label lbl = new Label();

    try {
      switch (player) {
        case 0:
          {
            playerColor = RED;
            playerText += "1" + suffix;
            break;
          }
        case 1:
          {
            playerColor = GREEN;
            playerText += "2" + suffix;
            break;
          }
        case 2:
          {
            playerColor = BLUE;
            playerText += "3" + suffix;
            break;
          }
        case 3:
          {
            playerColor = YELLOW;
            playerText += "4" + suffix;
            break;
          }
        default:
          {
            throw new IllegalStateException("Unexpected value: " + player);
          }
      }
    } catch (IllegalStateException e) {
      e.printStackTrace();
    }
    lbl.setText(playerText);
    lbl.setStyle(
        ""
            + " -fx-font-family: 'Comic Sans MS';"
            + " -fx-text-fill: #000000;"
            + " -fx-background-color: grey;"
            + " -fx-font-size: 30;"
            + " -fx-label-padding: 15;"
            + " -fx-border-width: 5;"
            + " -fx-border-color: "
            + playerColor
            + ";");
    lbl.setMaxWidth(250);
    lbl.setMinWidth(250);

    return lbl;
  }

  /**
   * Creates a label to display the round number in a more human-readable format with appropriate
   * styling
   *
   * @param roundNumber the current round number
   * @param startOffset set to 0 if round counting starts at 1, set to 0 if counting starts at 1
   * @return returns label with formatted round number
   */
  public static VBox roundNumberDisplayBuilder(int roundNumber, int startOffset) {
    String css = "-fx-font-family: 'Comic Sans MS'; -fx-font-size: 120; -fx-text-fill: #000000";
    roundNumber = roundNumber + startOffset;

    VBox vbx = new VBox();
    Label roundNumberHeader = ViewHelper.headerLabelBuilder("Round Number:");

    Label roundNumberLabel = new Label();
    roundNumberLabel.setStyle(css);
    roundNumberLabel.setText(String.valueOf(((roundNumber / 3) + 1)));
    roundNumberLabel.setTranslateX(55);

    Label subroundNumberHeader = ViewHelper.headerLabelBuilder("Sub Round:");

    Label subroundNumberLabel = new Label();
    subroundNumberLabel.setStyle(css);
    subroundNumberLabel.setText(String.valueOf(roundNumber % 3));
    subroundNumberLabel.setTranslateX(55);

    vbx.getChildren()
        .addAll(roundNumberHeader, roundNumberLabel, subroundNumberHeader, subroundNumberLabel);

    return vbx;
  }

  /**
   * Creates the vertical text display for the CARDS text in the gameboard
   *
   * @param text Text to make vertically displayed
   * @return returns vbox with vertical text
   */
  public static VBox verticalTextDisplayBuilder(String text) {
    String[] chars = text.split("(?!^)");
    ArrayList<String> charsAsStrings = new ArrayList<>();
    Collections.addAll(charsAsStrings, chars);

    VBox vbx = new VBox();

    for (String charsAsString : charsAsStrings) {
      Text txt = new Text();
      txt.setText(charsAsString);
      txt.setStyle(
          " -fx-text-fill: #000000;"
              + " -fx-font-size: 27.5;"
              + " -fx-font-family: 'Comic Sans MS';");
      vbx.getChildren().add(txt);
    }

    return vbx;
  }

  public static Label winnerLabelBuilder(boolean hasWon, String winningTeam) {
    Label lbl = new Label();
    String text = "";

    if (hasWon) {
      text += "You win! ";
    } else {
      text += "You lost! Team " + winningTeam + " wins!";
    }
    lbl.setText(text);
    lbl.setStyle("-fx-font-family: 'Comic Sans MS';-fx-font-size: 30; -fx-text-fill: #000000");

    return lbl;
  }

  public static Button buttonBuilder(String txt) {
    Button btn = new Button();

    btn.setPrefWidth(200);
    btn.setPrefHeight(125);
    btn.setText(txt);
    btn.setStyle("-fx-font-size: 20;");
    ViewHelper.applyDropShadow(btn);

    return btn;
  }
}
