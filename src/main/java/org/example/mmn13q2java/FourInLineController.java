package org.example.mmn13q2java;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FourInLineController {
    private final int WIDTH_OF_TABLE = 600;
    private final int HEIGHT_OF_TABLE = 350;
    private final int ROWS = 6;
    private final int COLUMNS = 7;
    private final int PLAYER1 =0;
    private final int PLAYER2 =1;
    private boolean gameOver = false;
    private int[][] board = new int[ROWS][COLUMNS];
    private int counter = 0;
    private int counterGrid1 = 4;
    private int counterGrid2 = 4;
    private int counterGrid3 = 4;
    private int counterGrid4 = 4;
    private int counterGrid5 = 4;
    private int counterGrid6 = 4;
    private int counterGrid7 = 4;

    @FXML
    private GridPane grid;

    @FXML
    void clearPressed(ActionEvent event) {
        grid.getChildren().clear();
        initialize();
        grid.setDisable(false); // Enable the grid again
        gameOver = false; // Reset game over flag
    }

    private void drawRectangle(GridPane gridPane) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                Rectangle rectangle = new Rectangle((double) WIDTH_OF_TABLE / COLUMNS, (double) HEIGHT_OF_TABLE / ROWS); // Set the width and height of the rectangle
                rectangle.setFill(Color.TRANSPARENT); // Set fill color to transparent
                rectangle.setStroke(Color.BLACK); // Set stroke color
                rectangle.setStrokeWidth(2); // Set stroke width
                gridPane.add(rectangle, j, i); // Add the rectangle to the grid pane
            }
        }
    }
    public void handleMousePressedOnGrid(MouseEvent event) {
        int x = (int) (event.getX()/86);
        switch (x){
            case 0:
                drawInGrid(grid, counterGrid1,x);
                counterGrid1--;
                break;
            case 1:
                drawInGrid(grid, counterGrid2,x);
                counterGrid2--;
               break;
            case 2:
                drawInGrid(grid, counterGrid3,x);
                counterGrid3--;
                break;
            case 3:
                drawInGrid(grid, counterGrid4,x);
                counterGrid4--;
                break;
            case 4:
                drawInGrid(grid, counterGrid5,x);
                counterGrid5--;
                break;
            case 5:
                drawInGrid(grid, counterGrid6,x);
                counterGrid6--;
                break;
            case 6:
                drawInGrid(grid, counterGrid7,x);
                counterGrid7--;
                break;

        }



    }
    private int checkForWin(int player) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j <= COLUMNS - 4; j++) {
                if (board[i][j] == player && board[i][j + 1] == player &&
                        board[i][j + 2] == player && board[i][j + 3] == player) {
                    return player;
                }
            }
        }
        // Check vertically
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == player && board[i + 1][j] == player &&
                        board[i + 2][j] == player && board[i + 3][j] == player) {
                    return player;
                }
            }
        }
        // Check diagonally (from top-left to bottom-right)
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = 0; j <= COLUMNS - 4; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player &&
                        board[i + 2][j + 2] == player && board[i + 3][j + 3] == player) {
                    return player;
                }
            }
        }

        // Check diagonally (from top-right to bottom-left)
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = COLUMNS - 1; j >= 3; j--) {
                if (board[i][j] == player && board[i + 1][j - 1] == player &&
                        board[i + 2][j - 2] == player && board[i + 3][j - 3] == player) {
                    return player;
                }
            }
        }

        return -1; // No winner found
    }
    private void showWinningPlayer(int player) {
        gameOver = true; // Set game over flag
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winning Player");
        alert.setHeaderText(null);
        alert.setContentText("Player "+(player+1)+"  win!");
        alert.showAndWait();
        grid.setDisable(true);
    }
    private void drawInGrid(GridPane gridPane, int gridCounter,int column) {
        if (gridCounter >= 0 && !gameOver) {
            if (counter >= 0) {
                if (counter % 2 == 0) {
                    drawCircle(gridPane, Color.RED, gridCounter,column);
                    board[gridCounter][column] = PLAYER1;
                   if(checkForWin(PLAYER1)!=-1)
                       showWinningPlayer(PLAYER1);
                } else {
                    drawCircle(gridPane, Color.BLUE, gridCounter,column);
                    board[gridCounter][column] = PLAYER2;
                    if(checkForWin(PLAYER2)!=-1)
                        showWinningPlayer(PLAYER2);
                }
                counter++;
            }
        }

    }
    @FXML
    void initialize() {
        grid.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleMousePressedOnGrid(event);
            }
        });
        drawRectangle(grid);
        addTextToGrid(grid);
        counterGrid1 = 4;
        counterGrid2 = 4;
        counterGrid3 = 4;
        counterGrid4 = 4;
        counterGrid5 = 4;
        counterGrid6 = 4;
        counterGrid7 = 4;
        counter = 0;
        resetBoard();
    }
    private void resetBoard () {
        for (int i=0; i<ROWS ;i++){
            for (int j=0; j<COLUMNS; j++){
                board[i][j] = -1;
            }
        }
    }
    private void drawCircle(GridPane gridPane, Color color, int row,int col) {
        Circle circle = new Circle();
        circle.setFill(color);
        circle.setRadius(25);
        circle.setStroke(color);
        circle.setStrokeWidth(2);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(circle, HPos.CENTER);
        gridPane.add(circle, col, row);

    }

private void addTextToGrid(GridPane gridPane) {
    for (int i = 1; i <=COLUMNS; i++) {
        Text textNode = new Text(" " + i);
        textNode.setFont(Font.font(44));
        textNode.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(textNode, HPos.CENTER);
        gridPane.add(textNode, i-1, ROWS-1); // Add the text to the last row of the grid
    }
}
}
