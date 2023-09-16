Human-Centered Input Recognition Algorithms - Project 1 Part 5

Group members:-
- Shriyans Nidhish
- Smridhi Bhat

Submission info:-
- This submission contains readme.txt, Project1Part5.zip, Project1Part5.mp4, logfileBase.csv, heatmap-image.bmp, feature-data.csv

Steps to run the project:-
- Unzip "Project1Part5.zip"
- Import the unzipped folder "Project1Part5" or all the files inside the folder into IDE
- Compile the OfflineRecognizerAlgorithm.java file
  javac OfflineRecognizerAlgorithm.java
- Run the OfflineRecognizerAlgorithm class file
  java OfflineRecognizerAlgorithm


Goals achieved:
a) run an offline recognition test with $1 (using your code from Part 3) on your new dataset (from Part 4);
- The code is updated in the "OfflineRecognizerAlgorithm.java" file to read user data from xml_logs folder for 6 users and perform offline recognition with the dataset.


b) output the result of the recognition tests to a log file (same format as Part 3);
- Every iteration log is added to the log file and the code can be found at OfflineRecognizerAlgorithm.java main() method at line number 236 to line number 388.


c) run your data through the GHOST heatmap toolkit;
- After running the data through GHOST heat map toolkit, the heatmap-image.bmp and feature-data.csv is exported for further analysis.


d) extract at least one insight you notice about your users’ gesture articulation.
- After analyzing the heatmap toolkit's output using our data, we found that there are more differences in the gestures like "pigtail, Left_curly_braces" among many users. Also, we observed that collecting user data through touchscreen has less accuracy as compared to data collected through mousepad.

