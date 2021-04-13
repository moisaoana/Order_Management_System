package sample.presentation;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmptyTextFieldsErrorWindow extends Stage {
    public EmptyTextFieldsErrorWindow() {
        BorderPane borderPane=new BorderPane();
        borderPane.setStyle("-fx-background-color: aliceblue");
        this.setWidth(300);
        this.setHeight(100);
        this.setTitle("Error");
        Text text = new Text("Please fill all the required text fields!");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        text.setFill(Color.ORANGERED);
        borderPane.setCenter(text);

    Scene scene = new Scene(borderPane);
    this.setScene(scene);
    this.show();
}
}
