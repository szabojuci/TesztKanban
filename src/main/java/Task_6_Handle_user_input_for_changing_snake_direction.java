public class SnakeInputHandler {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static Direction handleDirectionInput(Direction currentDirection, char inputChar) {
        switch (Character.toLowerCase(inputChar)) {
            case 'w': // Up
                return (currentDirection != Direction.DOWN) ? Direction.UP : currentDirection;
            case 's': // Down
                return (currentDirection != Direction.UP) ? Direction.DOWN : currentDirection;
            case 'a': // Left
                return (currentDirection != Direction.RIGHT) ? Direction.LEFT : currentDirection;
            case 'd': // Right
                return (currentDirection != Direction.LEFT) ? Direction.RIGHT : currentDirection;
            default:
                return currentDirection; // Ignore unhandled input
        }
    }
}
