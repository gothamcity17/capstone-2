package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main extends Application {

    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    // Boolean tracks who's move it is
    private boolean redMove = true;
    // The SECOND Grid of disk, with columns and rows
    private Disk[][] grid = new Disk[COLUMNS][ROWS];
    // The Pane for the root of the disks
    private Pane diskRoot = new Pane();

    public static String ply1 = null;
    public static String ply2 = null;

    private Parent createContent() {
        Pane root = new Pane();
        // Method adds disk root to the grid, like an overlay
        root.getChildren().add(diskRoot);

        Shape gridShape = makeGrid();
        root.getChildren().add(gridShape);
        root.getChildren().addAll(makeColumns());

        return root;
    }

    // The very initial grid, in object Shape
    // Has not functionality, besides creating the shape of the board
    private Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);

                shape = Shape.subtract(shape, circle);
            }
        }
        shape.setFill(Color.BLUE);

        return shape;
    }

    // *****Attempt to separate responsibility through refactoring
    // The functionality for the cursor hovering on top of the column, yellow transparent color
    // Shows the cursor
    private List<Rectangle> makeColumns() {
        ArrayList<Rectangle> list = new ArrayList<>();

        // Note this is not the final variable that gets reassigned
        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            // Code for mouse functionality
            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            // Created the final variable, reassigned as column
            final int column = x;

            // Function that helps place the disk
            // Disk is placed when the hovered mouse is clicked
            rect.setOnMouseClicked(e -> placeDisk(new Disk(redMove), column));
            // Method redMove states that if it is not the red disk, then it comes back as false
            // False is the other disk, the yellow

            list.add(rect);
        }

        return list;
    }

    // However, in order to place the actual disk, we need to pass the disk itself
    private void placeDisk(Disk disk, int column) {
        // Selects rows, goes from 0, and counts disks as they fall down
        int row = ROWS - 1;
        do {
            // If the disk is in the column or row, and it is present, we return true stating there is a value inside
            if (!getDisk(column, row).isPresent())
                // If not then it's declared empty, and we can continue
                break;

            row--;
        } while (row >= 0);

        if (row < 0)
            // Return if we fail to find a proper row that is empty
            return;

        grid[column][row] = disk;
        // Visual part of the game, the disk root
        // Basically adds the disks onto the disk Pane
        diskRoot.getChildren().add(disk);
        // Translates for the X variable
        disk.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

        // The current row, basically after updating the disk root
        final int currentRow = row;

        // This is ONLY the animation of the disks falling down, set at 0.5 seconds
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disk);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        // Animation for both the row and the column
        animation.setOnFinished(e -> {
            if (gameEnded(column, currentRow)) {
                gameOver();
                // Kill the game
            }

            // Switches the players, if not redMove must be other move
            redMove = !redMove;
        });
        animation.play();
    }


    // The lambda streams used to determine if the game has ended
    // First pass in the column and the row of the disk that was added
    // Then determine result obtained from four key directions
    private boolean gameEnded(int column, int row) {

        // Lambda streams used for:
        // 1) Fewer lines of code
        // 2) Process elements in a sequential or parallel matter
        // 3) Higher CPU efficiency
        // The four values checked are below
        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3)
                // Will give the stream of rows, and map to a point
                .mapToObj(r -> new Point2D(column, r))
                // This method collects them in a list
                .collect(Collectors.toList());

        List<Point2D> horizontal = IntStream.rangeClosed(column - 3, column + 3)
                .mapToObj(c -> new Point2D(c, row))
                .collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3, row - 3);
        // The method rangeClosed has the parameters for the range
        List<Point2D> diagonalTopLeft = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> topLeft.add(i, i))
                .collect(Collectors.toList());

        Point2D botLeft = new Point2D(column - 3, row + 3);
        List<Point2D> diagonalBotLeft = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> botLeft.add(i, -i))
                .collect(Collectors.toList());

        // The game has ended if checkRange returns as true, if not no result
        return checkRange(vertical) || checkRange(horizontal)
                || checkRange(diagonalTopLeft) || checkRange(diagonalBotLeft);
    }

    // *****Attempt if rangeClosed is reached, reset diskRoot, the Pane where disks are stacked
    // *****Else if continue and check other options
    // *****Would have to make boolean public
    // *****Or use something like if (shape.gameEnded() {} ), will have to rethink

    // This is the helper method
    private boolean checkRange(List<Point2D> points) {
        int connect = 0;

        // Obtains columns, first get X, then get Y
        for (Point2D p : points) {
            int column = (int) p.getX();
            int row = (int) p.getY();

            // The gets disk from that position found
            // If the disk isn't there, then the connect will not continue
            Disk disk = getDisk(column, row).orElse(new Disk(!redMove));
            // If it is the red disk, it wil first check the red's move
            if (disk.red == redMove) {
                connect++;
                if (connect == 4) {
                    // *****Maybe put reset here?
                    return true;
                }
            } else {
                connect = 0;
            }
        }
        // If the connect of the link isn't found, then it will return as false, meaning game will continue
        return false;
    }

    // *****Another place where if the game ends, attempt to reset the grid
    private void gameOver() {

        // If red move says Winner is Red, if else says Winner is Yellow
        // *****Implement a way to insert player name, such as "Winner is (redMove ? player + "wins")
        System.out.println("Game over! " + (redMove ? ply1 : ply2) + " has come out as victorious!");
        System.exit(0);

        // *****Should be a good place to set an empty array or grid
        // ***** If you can print out the results normally, must be targeted somewhere
        // *****Maybe include a popup message saying Game Over, with a button called reset, might take a lot of time
        // *****Reset method can the set the array to zero when clicked
    }

    // The method the returns the disk, given the coordinates
    // Optional disk, instead of null, to return optional values
    // Int column represents x while int row represents y
    private Optional<Disk> getDisk(int column, int row) {
        // Checks for the boundaries when dealing with the grid/array
        if (column < 0 || column >= COLUMNS
                || row < 0 || row >= ROWS)
            return Optional.empty();

        // Basically an object we are returning, if anything to return
        return Optional.ofNullable(grid[column][row]);
    }

    // Origin where the disk is created
    // Inherited from Circle
    // *****Could be in separate file
    // *****Maybe abstract class where extending disk to diskRed or diskYellow
    private static class Disk extends Circle {
        // Boolean for red disk or yellow disk, could make char instead
        // Only one field
        private final boolean red;
        // Place to construct the disk
        public Disk(boolean red) {
            // Calls the super constructor, aka radius of tile size, and color
            // If red passes as red color, if not yellow
            super(TILE_SIZE / 2, red ? Color.RED : Color.YELLOW);
            this.red = red;

            // Sets the center properly
            // Inherited the properties from the circle we are extending from
            setCenterX(TILE_SIZE / 2);
            setCenterY(TILE_SIZE / 2);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setTitle("Connect Four");
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        PlayerNames ply = new PlayerNamesImpl(); // Example of interface
        WelcomeMessage msg = new WelcomeMessageImpl(); // Example of abstract

        ply1 =  ply.capturePlayer1Name();
        System.out.println(msg.welcomeMessagePlayer1() + ply1 + ". Who will be your challenger?");

        ply2 = ply.capturePlayer2Name();
        System.out.println(msg.welcomeMessagePlayer2() + ply2 + ", you have a strong opponent!");

      /*  Previously used:
      Reading data using readLine
      System.out.println("Enter Player 1 name");
      Player1 = reader.readLine();

      System.out.println("Enter Player 2 name");
      Player2 = reader.readLine();

      // And the Print Line message was:
      System.out.println("Name of Player 1 " + Player1);
      System.out.println("Name of Player 1 " + Player1)
      */

        launch(args);
    }
}
