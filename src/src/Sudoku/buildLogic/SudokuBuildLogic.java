package Sudoku.buildLogic;

import Sudoku.ComponentLogic.GameLogic;
import Sudoku.domain.IStorage;
import Sudoku.domain.SudokuGame;
import Sudoku.persistence.LocalStorageImplementation;
import Sudoku.userinterface.IUserInterfaceContract;
import Sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImplementation();
        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameDate(initialState);
        }
        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
