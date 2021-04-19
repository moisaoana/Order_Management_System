package sample.presentation;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * This UI class opens a window that notifies the user about an error
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class ErrorWindow extends Stage {
    /**
     * The constructor that generates the window
     * @param string A String representing the message to be displayed in the window based on the error that has occurred
     */
    public ErrorWindow(String string) {
        BorderPane borderPane=new BorderPane();
        borderPane.setStyle("-fx-background-color: aliceblue");
        this.setWidth(400);
        this.setHeight(100);
        this.setTitle("Error");
        Text text = new Text(string);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        text.setFill(Color.ORANGERED);
        borderPane.setCenter(text);
        Scene scene = new Scene(borderPane);
        this.setScene(scene);
        this.show();
    }
}
