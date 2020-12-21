package Sudoku;

import Sudoku.buildLogic.SudokuBuildLogic;
import Sudoku.userinterface.IUserInterfaceContract;
import Sudoku.userinterface.UserInterfaceImplementation;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {

    private IUserInterfaceContract.View guiImplementation;

    @Override
    public void start(Stage primaryStage) throws Exception{
        guiImplementation = new UserInterfaceImplementation(primaryStage);
        try {
            SudokuBuildLogic.build(guiImplementation);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
