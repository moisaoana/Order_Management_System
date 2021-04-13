package sample.presentation;

import javafx.scene.Scene;
import sample.start.Main;

public class ProductsWindow {
    private Scene clientScene;
    private Main main;
    private Scene menuScene;
    public void setMain(Main main){
        this.main = main;
    }
    public void setClientScene(Scene scene){
        this.clientScene = scene;
    }
    public void setMenuScene(Scene scene){
        this.menuScene = scene;
    }
}
