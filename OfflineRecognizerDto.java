
import java.util.ArrayList;

// Model class for OfflineRecognizerAlgorithm class
// Used to create multiple object of above class with different properties and values
// consists of multiple parameterized constructor for initializing different property of same object
public class OfflineRecognizerDto {

    public final String gestureName;
    public final double accuracyScore;
    public final ArrayList<Point> points;
    public final int gestureNumber;
    public OfflineRecognizerDto(String gestureName, double accuracyScore) {
        this.gestureName = gestureName;
        this.accuracyScore = accuracyScore;
        this.points = null;
        this.gestureNumber = 0;
    }
    public OfflineRecognizerDto(String gestureName, ArrayList<Point> points) {
        this.gestureName = gestureName;
        this.accuracyScore = 0;
        this.points = points;
        this.gestureNumber = 0;
    }
    public OfflineRecognizerDto(String gestureName, double accuracyScore, int gestureNumber) {
        this.gestureName = gestureName;
        this.accuracyScore = accuracyScore;
        this.points = null;
        this.gestureNumber = gestureNumber;
    }
    public OfflineRecognizerDto(String gestureName, ArrayList<Point> points, int gestureNumber) {
        this.gestureName = gestureName;
        this.accuracyScore = 0;
        this.points = points;
        this.gestureNumber = gestureNumber;
    }
}