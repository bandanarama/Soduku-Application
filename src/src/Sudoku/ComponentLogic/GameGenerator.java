package Sudoku.ComponentLogic;

import Sudoku.domain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Sudoku.domain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {
    public static int[][] getNewGameGrid() {
        return unsolvedGame(getSolvedGame());
    }

    private static int[][] unsolvedGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (solvable == false){
            SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);

            int index = 0;
            while(index < 40) {
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if(solvableArray[xCoordinate][yCoordinate] != 0) {
                    solvableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }
            int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);

            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);
        }
        return solvableArray;
    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        for(int value = 1; value <= GRID_BOUNDARY; value++){
            int allocation = 0;
            int interrupt = 0;

            List<Coordinates> allocationTracker = new ArrayList<>();

            int attempts = 0;

            while(allocation < GRID_BOUNDARY) {
                if(interrupt > 200) {
                    allocationTracker.forEach(coordinates -> {
                        newGrid[coordinates.getX()][coordinates.getY()] = 0;
                    });
                    interrupt = 0;
                    allocation = 0;
                    allocationTracker.clear();
                    attempts++;

                    if(attempts > 500) {
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (newGrid[xCoordinate][yCoordinate] == 0) {
                    newGrid[xCoordinate][yCoordinate] = value;

                    if(GameLogic.sudokuIsInvalid(newGrid)){
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    } else {
                        allocationTracker.add(new Coordinates(xCoordinate,yCoordinate));
                        allocation++;
                    }
                }
            }
        }
        return newGrid;
    }

    private static void clearArray(int[][] newGrid) {
        for( int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for( int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
