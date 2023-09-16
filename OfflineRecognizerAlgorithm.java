import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OfflineRecognizerAlgorithm {

    // creates n-best list
    public static ArrayList<OfflineRecognizerDto> offlineRecognizer(OfflineRecognizerDto points,
            ArrayList<ArrayList<OfflineRecognizerDto>> templates) {

        ArrayList<OfflineRecognizerDto> nbestList = new ArrayList<>();
        // OneDollarRecognitionAlgorithm recognitionAlgorithm = new
        // OneDollarRecognitionAlgorithm();
        for (int i = 0; i < templates.size(); i++) {
            // double val = Double.POSITIVE_INFINITY;
            // int gestureNumber = 0;
            for (int j = 0; j < templates.get(i).size(); j++) {
                double distance = distanceAtBestAngle(points.points, templates.get(i).get(j).points);

                // if (distance < val) {
                //     val = distance;
                //     //gestureNumber = j + 1; //FIX : SN
                // }

                double score = 1 - distance / (.5 * Math.sqrt(250 * 250 + 250 * 250));

                nbestList.add(new OfflineRecognizerDto(templates.get(i).get(j).gestureName, score, templates.get(i).get(j).gestureNumber));
            }

            // double score = 1 - val / (.5 * Math.sqrt(250 * 250 + 250 * 250));

            // creating n-best list by storing best score of all the gestures, gesture type
            // and gesture number
           // nbestList.add(new OfflineRecognizerDto(templates.get(i).get(0).gestureName, score, templates.get(i).get(j).gestureNumber));
        }
        return sortList(nbestList);
    }

    public static ArrayList<OfflineRecognizerDto> sortList(ArrayList<OfflineRecognizerDto> nbestList) {
        // temporary list used for sorting
        ArrayList<OfflineRecognizerDto> t = new ArrayList<>();
        while (!nbestList.isEmpty()) {
            int bestGesture = 0;
            double highestScore = Double.NEGATIVE_INFINITY;
            // search list for best gesture score and index
            for (int i = 0; i < nbestList.size(); i++) {
                if (nbestList.get(i).accuracyScore > highestScore) {
                    highestScore = nbestList.get(i).accuracyScore;
                    bestGesture = i;
                }
            }
            t.add(nbestList.get(bestGesture));
            nbestList.remove(bestGesture);
        }

        return t;
    }
    private static double distanceAtBestAngle(List<Point> points, ArrayList<Point> T) {
    double a = -0.25 * Math.PI;
    double b = 0.25 * Math.PI;
    double delta = 0.5*(-1+Math.sqrt(5));
    double threshold = Math.toRadians(2);

    double x1 = delta*a +(1 - delta)*b;
    double x2 = (1-delta)*a + delta*b;

    double f1 = distanceAtAngle(x1,points,T);
    double f2 = distanceAtAngle(x2, points, T);
    // double x1 = phi(a, points, T);
    // double x2 = phi(b, points, T);
    while (Math.abs(b - a) > threshold) {
    if(f1 < f2) {
    b = x2;
    x2 = x1;
    f2 = f1;
    x1 = delta*a +(1 - delta)*b;
    f1 = distanceAtAngle(x1, points, T);
    } else {
    a = x1;
    x1 = x2;
    f1 = f2;
    x2 = (1-delta)*a + delta*b;
    f2 = distanceAtAngle(x2, points, T);
    }
    }
    return Math.min(f1, f2);
    }
    // public static double DistanceAtBestAngle(ArrayList<Point> points, ArrayList<Point> template, double angleA,
    //         double angleB, double angleDelta) {
    //     double phi = 1.0 / 2.0 * (-1 + Math.sqrt(5));

    //     double x1 = phi * angleA + (1.0 - phi) * angleB;
    //     double f1 = DistanceAtAngle(points, template, x1);

    //     double x2 = (1.0 - phi) * angleA + phi * angleB;
    //     double f2 = DistanceAtAngle(points, template, x2);

    //     while (Math.abs(angleB - angleA) > angleDelta) {
    //         if (f1 < f2) {
    //             angleB = x2;
    //             x2 = x1;
    //             f2 = f1;
    //             x1 = phi * angleA + (1.0 - phi) * angleB;
    //             f1 = DistanceAtAngle(points, template, x1);
    //         } else {
    //             angleA = x1;
    //             x1 = x2;
    //             f1 = f2;
    //             x2 = (1.0 - phi) * angleA + phi * angleB;
    //             f2 = DistanceAtAngle(points, template, x2);
    //         }
    //     }

    //     return Math.min(f1, f2);
    // }

    private static double distanceAtAngle(double angle, List<Point> points, ArrayList<Point> T) {
        OneDollarRecognitionAlgorithm recognitionAlgorithm = new OneDollarRecognitionAlgorithm();
        List<Point> newPoints = recognitionAlgorithm.rotateBy(points, angle,new OneDollarAlgorithmImpl().centroid(points));
        return pathDistance(newPoints, T);
    }
    // public static double DistanceAtAngle(ArrayList<Point> arrayList, ArrayList<Point> template, double radians) {
    //     OneDollarRecognitionAlgorithm recognitionAlgorithm = new OneDollarRecognitionAlgorithm();
    //     ArrayList<Point> newPoints = recognitionAlgorithm.rotateBy(arrayList, radians,
    //             new OneDollarAlgorithmImpl().centroid(arrayList));
    //     double d = pathDistance(newPoints, template);
    //     return d;
    // }

    private static double pathDistance(List<Point> a, ArrayList<Point> b) {
        OneDollarRecognitionAlgorithm recognitionAlgorithm = new OneDollarRecognitionAlgorithm();
        double d = 0;
        for (int i = 0; i < Math.min(b.size(),a.size()); i++) {
            d += recognitionAlgorithm.distance(a.get(i), b.get(i));
        }
        return d / Math.min(b.size(),a.size());
    }
    // public static double PathDistance(ArrayList<Point> point1, ArrayList<Point> point2) {
    //     double distance = 0.0;
    //     OneDollarRecognitionAlgorithm recognitionAlgorithm = new OneDollarRecognitionAlgorithm();
    //     for (int i = 0; i < point1.size() && i < point2.size(); i++) {
    //         distance += recognitionAlgorithm.distance(point1.get(i), point2.get(i));
    //     }

    //     return distance / point1.size();
    // }

    public static OfflineRecognizerDto XmlReadAndParse(String filename) {
        File xmlFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = dBuilder.parse(xmlFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();
        Element documentElement = document.getDocumentElement();
        String name = documentElement.getAttribute("Name");
        // Extract the name of gesture
        String gestureName = name.substring(0, name.length() - 2);
        NodeList nodeList = document.getElementsByTagName("Point");
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            points.add(new Point(Double.parseDouble(nodeList.item(i).getAttributes().getNamedItem("X").getNodeValue()),
                    Double.parseDouble(nodeList.item(i).getAttributes().getNamedItem("Y").getNodeValue())));
        }
        return new OfflineRecognizerDto(gestureName, points);
    }

    // buidling gesture array by reading specfic user and all its gesture and
    // storing it into list
    public static ArrayList<OfflineRecognizerDto> gatherGestures(String user, String gestureType) {
        ArrayList<OfflineRecognizerDto> templates = new ArrayList<>();

        String filename = "";
        String folderName = "xml_logs/";
        //String gestureSpeed = "/medium/";
        String gestureNumber = "";
        String fileExt = ".xml";
        for (int i = 1; i <= 10; i++) {
            if (i != 10) {
                gestureNumber = "0" + i;
            } else {
                gestureNumber = Integer.toString(i);
            }
            // create file name
            filename = folderName + user +"/"+ gestureType + gestureNumber + fileExt;
            // read the file, process the points, add to templates
            OfflineRecognizerDto temp = processPoints(XmlReadAndParse(filename));
            templates.add(new OfflineRecognizerDto(temp.gestureName, temp.points, Integer.parseInt(gestureNumber)));
        }
        return templates;
    }

    // Method to perform all the gesture recognizing steps
    public static OfflineRecognizerDto processPoints(OfflineRecognizerDto template) {
        OneDollarAlgorithmImpl recognizerImpl = new OneDollarAlgorithmImpl();
        OneDollarRecognitionAlgorithm recognize = new OneDollarRecognitionAlgorithm();
        ArrayList<Point> resampledPoints = recognizerImpl.resample(template.points);
        // finds the indicative angle
        Point centroid = recognizerImpl.centroid(resampledPoints);
        Point firstPoint = resampledPoints.get(0);
        double slope = Math.atan2((firstPoint.yCoordinate - centroid.yCoordinate),
                (firstPoint.xCoordinate - centroid.xCoordinate));

        ArrayList<Point> rotatedPoints = recognize.rotateBy(resampledPoints, -1 * slope, centroid);
        recognizerImpl.scale(rotatedPoints);
        ArrayList<Point> translatedPoints = recognizerImpl.translate(rotatedPoints);
        OfflineRecognizerDto processedPoints = new OfflineRecognizerDto(template.gestureName, translatedPoints);

        return processedPoints;
    }

    public static void main(String[] args) {
        // OneDollarRecognitionAlgorithm recognitionAlgorithm = new
        // OneDollarRecognitionAlgorithm();
        String user = "";
        String gestureType = "";
        // List of list for storing every details for each user
        ArrayList<ArrayList<OfflineRecognizerDto>> detailPerUser = new ArrayList<>();
        ArrayList<Double> avgAccuracy = new ArrayList<>();
        double totalAvgAccuracy = 0.0;
        try {
            File logFileCsv = new File("logfileBase.csv");
            if (!logFileCsv.exists()) {
                logFileCsv.createNewFile();
            } else {
                // overwrite file if already exists
                FileOutputStream writer = new FileOutputStream("logfileBase.csv", false);
                writer.write(("").getBytes());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write into log file create above
        try {
            FileOutputStream fileWrite = new FileOutputStream("logfileBase.csv", true);
            // File Discription (according to sample log file provided)
            fileWrite.write(
                    ("Recognition Log: [Shriyans Nidhish, Smridhi Bhat] // [$1 Recognizer] // [$1 recognizer dataset] // USER-DEPENDENT RANDOM-100,,,,,,,,,,,\n")
                            .getBytes());
            // Setting column header
            fileWrite.write(
                    ("User[all-users],GestureType[all-gestures-types],RandomIteration[1to100],#ofTrainingExamples[E],TotalSizeOfTrainingSet[count],TrainingSetContents[specific-gesture-instances],Candidate[specific-instance],RecoResultGestureType[what-was-recognized],CorrectIncorrect[1or0],RecoResultScore,RecoResultBestMatch[specific-instance],RecoResultNBestSorted[instance-and-score]\n")
                            .getBytes());
            for (int userID = 1; userID <= 6; userID++) {
                if (userID < 10) {
                    user = "s" + "0" + userID;
                } else {
                    user = "s" + userID;
                }

                String getGestureType[] = new String[] {
                    "arrow",
                    "caret",
                    "check",
                    "circle",
                    "delete",
                    "left_curly_brace",
                    "left_sq_bracket",
                    "pigtail",
                    "zigzag",
                    "rectangle",
                    "right_curly_brace",
                    "right_sq_bracket",
                    "star",
                    "triangle",
                    "v",
                    "x"
                };

                // creating user data with all its gesture type and gesture number
                for (int gestureID = 0; gestureID < 16; gestureID++) {
                    gestureType = getGestureType[gestureID];
                    detailPerUser.add(gatherGestures(user, gestureType));
                }
                for (int iterationNumber = 0; iterationNumber < 10; iterationNumber++) {
                // for training sample sizes 1 to 9
                for (int exampleSize = 1; exampleSize <= 9; exampleSize++) {
                   
                        // randomly accessing the user data
                        for (int l = 0; l < detailPerUser.size(); l++) {
                            Collections.shuffle(detailPerUser.get(l));
                        }
                        // building arraylist to store testing set of size k
                        ArrayList<ArrayList<OfflineRecognizerDto>> testingSet = new ArrayList<ArrayList<OfflineRecognizerDto>>();
                        for (int gestureID = 0; gestureID < 16; gestureID++) {
                            ArrayList<OfflineRecognizerDto> tempSet = new ArrayList<>();
                            for (int trainingSampleNumber = 0; trainingSampleNumber < exampleSize; trainingSampleNumber++) {
                                tempSet.add(detailPerUser.get(gestureID).get(trainingSampleNumber));
                            }
                            testingSet.add(tempSet);
                        }

                        // for each gesture type
                        for (int gestureID = 0; gestureID < 16; gestureID++) {
                            // build n-best list
                            ArrayList<OfflineRecognizerDto> nbestList = offlineRecognizer(
                                    detailPerUser.get(gestureID).get(9),
                                    testingSet);
                            // Start writing data into file
                            // Write user id
                            fileWrite.write((user + ",").getBytes());
                            // Gesture type
                            fileWrite.write((getGestureType[gestureID] + ",").getBytes());
                            // Iteration number (k + 1)
                            fileWrite.write((iterationNumber + 1 + ",").getBytes());
                            fileWrite.write((exampleSize + ",").getBytes());
                            fileWrite.write(((exampleSize) * 16 + ",").getBytes());
                            // Write training set contents
                            fileWrite.write(("\"{").getBytes());
                            for (int m = 0; m < testingSet.size(); m++) {
                                for (int n = 0; n < testingSet.get(m).size(); n++) {
                                    fileWrite.write((user + "-" + detailPerUser.get(m).get(n).gestureName + "-"
                                            + detailPerUser.get(m).get(n).gestureNumber + ",").getBytes());
                                }
                            }
                            fileWrite.write(("}\",").getBytes());
                            fileWrite.write((user + "-" + detailPerUser.get(gestureID).get(5).gestureName + "-"
                                    + detailPerUser.get(gestureID).get(9).gestureNumber + ",").getBytes());
                            fileWrite.write((nbestList.get(0).gestureName + ",").getBytes());
                            if (nbestList.get(0).gestureName.equals(detailPerUser.get(gestureID).get(9).gestureName)) {
                                fileWrite.write((1 + ",").getBytes());
                            } else {
                                fileWrite.write((0 + ",").getBytes());
                            }
                            fileWrite.write((String.format("%.2f", nbestList.get(0).accuracyScore) + ",").getBytes());
                            avgAccuracy.add(nbestList.get(0).accuracyScore);
                            fileWrite.write(
                                    (user + "-" + nbestList.get(0).gestureName + "-" + nbestList.get(0).gestureNumber
                                            + ",").getBytes());
                            fileWrite.write(("\"{").getBytes());
                            for (int m = 0; m < nbestList.size(); m++) {
                                fileWrite.write((user + "-" + nbestList.get(m).gestureName + "-"
                                        + nbestList.get(m).gestureNumber + ","
                                        + String.format("%.2f", nbestList.get(m).accuracyScore)).getBytes());
                                // fileWrite.write((nbestList.get(m).gestureName + "," +
                                // nbestList.get(m).accuracyScore).getBytes());
                                if (m != nbestList.size() - 1) {
                                    fileWrite.write((",").getBytes());
                                }
                            }
                            fileWrite.write(("}\",").getBytes());
                            fileWrite.write(("\n").getBytes());

                        }

                    }
                }
                System.out.println("User "+ (userID)+" Completed:) ");
            }
            for (double i : avgAccuracy) {
                totalAvgAccuracy += i;
            }
            String averageAccuracy = String.format("%.2f", (totalAvgAccuracy / avgAccuracy.size())*100);
            fileWrite.write(("Total Average Accuracy" + ",").getBytes());
            fileWrite.write((averageAccuracy).getBytes());
            fileWrite.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Log File Generated.");
    }

    // // identifies correct gesture type based on int value
    // public static String getGestureType(int gestureNumber) {
    //     String gestureName = "";
    //     switch (gestureNumber) {
    //         case 0:
    //             gestureName = "arrow";
    //             break;
    //         case 1:
    //             gestureName = "caret";
    //             break;
    //         case 2:
    //             gestureName = "check";
    //             break;
    //         case 3:
    //             gestureName = "circle";
    //             break;
    //         case 4:
    //             gestureName = "delete";
    //             break;
    //         case 5:
    //             gestureName = "left_curly_brace";
    //             break;
    //         case 6:
    //             gestureName = "left_sq_bracket";
    //             break;
    //         case 7:
    //             gestureName = "pigtail";
    //             break;
    //         case 8:
    //             gestureName = "zigzag";
    //             break;
    //         case 9:
    //             gestureName = "rectangle";
    //             break;
    //         case 10:
    //             gestureName = "right_curly_brace";
    //             break;
    //         case 11:
    //             gestureName = "right_sq_bracket";
    //             break;
    //         case 12:
    //             gestureName = "star";
    //             break;
    //         case 13:
    //             gestureName = "triangle";
    //             break;
    //         case 14:
    //             gestureName = "v";
    //             break;
    //         case 15:
    //             gestureName = "x";
    //             break;
    //     }
    //     return gestureName;
    // }

}