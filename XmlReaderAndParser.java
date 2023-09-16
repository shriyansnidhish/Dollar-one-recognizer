// import org.w3c.dom.Document;
// import org.w3c.dom.Element;
// import org.w3c.dom.NodeList;
// import org.xml.sax.SAXException;
// import javax.xml.parsers.DocumentBuilder;
// import javax.xml.parsers.DocumentBuilderFactory;
// import javax.xml.parsers.ParserConfigurationException;
// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;

// public class XmlReaderAndParser {
//     public static OfflineRecognizerDto XmlReadAndParse(String filename) {
//         File xmlFile = new File(filename);
//         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//         DocumentBuilder dBuilder = null;
//         try {
//             dBuilder = dbFactory.newDocumentBuilder();
//         } catch (ParserConfigurationException e) {
//             e.printStackTrace();
//         }
//         Document document = null;
//         try {
//             document = dBuilder.parse(xmlFile);
//         } catch (SAXException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         document.getDocumentElement().normalize();
//         Element documentElement = document.getDocumentElement();
//         String name = documentElement.getAttribute("Name");
//         // Extract the name of gesture
//         String gestureName = name.substring(0, name.length() - 2);
//         NodeList nodeList = document.getElementsByTagName("Point");
//         ArrayList<Point> points = new ArrayList<>();
//         for (int i = 0; i < nodeList.getLength(); i++) {
//             points.add(new Point(Double.parseDouble(nodeList.item(i).getAttributes().getNamedItem("X").getNodeValue()),
//                     Double.parseDouble(nodeList.item(i).getAttributes().getNamedItem("Y").getNodeValue())));
//         }
//         return new OfflineRecognizerDto(gestureName, points);
//     }
// }