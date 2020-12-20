package Sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {

    private IUserInterfaceContract.View guiImplementation;

    @Override
    public void start(Stage primaryStage) throws Exception{
        guiImplementation = new userInterfaceContract(primaryStage);
        try {
            sudokuBuildLogic.build(guiImplementation);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
