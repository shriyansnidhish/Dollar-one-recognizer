

##                          Course : CIS6930: Human-Centered Input Recognition Algorithms (HCIRA)
#                                  Implementation of $1 Gesture Recognition Algorithm
** Description :**
------
In the scope of our recent project, our primary objective was to design and develop a sophisticated user interface. This interface was tailored to precisely detect and recognize a set of 16 predetermined unistroke gestures. The foundation of our gesture recognition process was the renowned $1 recognition algorithm. This algorithm not only provided the robustness required for accurate recognition but also ensured swift response times, thereby enhancing user experience. By focusing on these specific gestures, we aimed to provide a streamlined and intuitive interaction platform for users, ensuring that the most common gestures were efficiently recognized and processed.". Furthermore, this was extended to evaluate the algorithm's accuracy by testing its recognition capabilities with three additional input modes: laptop mousepad, touch with a stylus, and touch without a stylus.

**$1 Algorithm Reference :**
-----
[Jacob O. Wobbrock, Andrew D. Wilson, and Yang Li. 2007. Gestures without libraries, toolkits or training: a $1 recognizer for user interface prototypes. In Proceedings of the 20th annual ACM symposium on User interface software and technology (UIST '07). ACM, New York, NY, USA, 159-168.](https://dl.acm.org/doi/abs/10.1145/1294211.1294238?casa_token=5FbPgYWhczkAAAAA:4i8ClOkuH7NJi2HkSZyMKIVGQPU7wq1l-hK2YiOxAsKQh1fMx4WqrydSZqOj2aUGWhr7ApxtG-uZ)

**Project Goal:**
-----
The primary objective of this project is to adapt and implement the renowned $1 Gesture recognizer algorithm to recognize and differentiate among 16 distinct gestures.

**Detailed Goals**:

**Offline Recognition Module Development:**
Purpose: To facilitate the in-depth analysis of gesture data.
Features: This module will be designed to process stored gesture data, allowing researchers and developers to analyze recognition patterns and refine the algorithm as needed.

**Data Collection Module Development:**
Purpose: To capture and accumulate user-generated gesture data for subsequent offline examination.
Features: This module will provide an intuitive user interface, ensuring that users can effortlessly input gestures. It will be optimized to collect high-quality data points that are essential for robust offline analysis.

**Performance Evaluation:**
Purpose: To assess the efficiency and accuracy of the implemented $1 Gesture recognizer algorithm.
Metrics: Recognition accuracy will be the primary metric. The success of the algorithm will be gauged based on its ability to correctly identify the 16 predefined gestures from the collected data.

<img width="302" alt="image" src="https://github.com/shriyansnidhish/Dollar-one-recognizer/assets/38129645/73725ac8-847c-4f7c-af23-86478a16cc70">

 
**Project #2 (Extension of Project1) Goals:**
-----
*	Modify the system to collect samples of all 16 gestures in three more different input modes (Mouse, Touch with stylus, Touch without stylus).
*	Create a setup module to simplify the process of collecting the dataset.
*	Measure performance of system for the new datasets and compare the same.


**Implemented Functionality:**
----

**Live Gesture Identification Interface:**

*The interface detects certain single-stroke motions.
*Users have the flexibility to sketch motions on the interface using devices like a mouse, touch pen, or even their finger.
*Once the drawing action ends, the system immediately showcases the identified Gesture Label and a corresponding similarity metric.
*There's a Reset option to help users wipe the interface clear.

**Gesture Input Collection Interface:**
*The screen shows the Gesture label, a guiding image, fundamental guidelines, and a tally indicating the count of motions input.
*To avoid user weariness, various gesture inputs are gathered in a shuffled sequence.
*There's a Re-sketch option for users to make corrections.
*The Save option archives the input in XML format.
*If the Save option is clicked without a drawing, an alert prompts users to make a sketch.
*An alert surfaces once 10 out of 16 gesture samples are done.
*A final notification appears when the entire task concludes.

**Analysis for Offline Gesture Detection:**
*Pull gesture specifics from the XML document, which houses coordinate data and other attributes such as User ID, Sequence Count, Gesture Label, and more.
*Conduct User-Specific evaluation: Compare the selected prototype gesture with template examples from the same individual.
*Execute User-Unrelated assessment: Contrast a randomly picked prototype gesture with template examples from a different, arbitrary user.

**Technologies Used**
----
* Language : Java
* IDE : Microsoft Visual Studio Code
* Version Control : Github repository

**Installation and Execution**
-----
**Installation**
*	Java : The computer must have Java compiling and runtime capabilities available.
*	Code file: Download all files into "DollarOneRecognizer" folder
*	Dataset : Download the dataset (XML Folder : https://depts.washington.edu/acelab/proj/dollar/index.html) and unzip the folder. Move it to " DollarOneRecognizer /xml_logs"
*	online Templates : For the 16 default gestures, no setup needed.

**Compile files**
```
cd <folder_name>
javac OneDollarUiImpl.java
javac OfflineRecognizerAlgorithm.java
```
Note : Both files need to be compiled always since there are functions being used across the files.

**Run Online recognizer**

**Run Data collector**
```
java OneDollarUiImpl
```

**Run Offline recognizer**
```
java OfflineRecognizerAlgorithm
```

**Implementation**
-----











<br> <br>**Snapshots of GUI**
<br> <img width="408" alt="image" src="https://github.com/shriyansnidhish/Dollar-one-recognizer/assets/38129645/f4c9b63f-f00b-4d5a-afaf-ba6d784ad4a9">
 

 

**Analysis and Results**
----
**Offline Recognition Test :** </br>Recognition Accuracy using User Dependent Analysis for the 16 pre-defined gestures.
The recognition accuracy is plotted against the template size for all types of modality (Trackpad, Touch with stylus, Touch without stylus).

<img width="457" alt="image" src="https://github.com/shriyansnidhish/Dollar-one-recognizer/assets/38129645/ad36fa6b-aa75-4e53-a402-3f6b3e630e0f">


**Ghost Heatmap Analysis:**
----

<img width="500" alt="image" src="https://github.com/shriyansnidhish/Dollar-one-recognizer/assets/38129645/802bede6-4d04-4b90-a269-3e9e34b869cf">
<img width="500" alt="image" src="https://github.com/shriyansnidhish/Dollar-one-recognizer/assets/38129645/93243dc8-83fe-4b1c-94f6-e1c42ba68de8">
<img width="500" alt="image" src="https://github.com/shriyansnidhish/Dollar-one-recognizer/assets/38129645/691604dd-81e1-4cce-8b8f-da2fe5fe0517">


The speed at which the original authors collected data was consistent, in contrast to the varied drawing speeds of our own participants, leading to greater sample variability.
</br>Contrary to our initial assumptions, the mouse-based data was not the most reliable but rather the least consistent and comparatively imprecise. We found that the trackpad yielded the highest accuracy in our gesture recognizer, whereas mouse inputs were the least accurate. Touchscreen inputs, whether made with or without a stylus, showed comparable precision. By employing GHoST analysis, we discerned that certain gestures demonstrated inconsistencies depending on the input method used, relative to other gestures executed with the same or differing input technologies.
</br>One anticipated outcome that did materialize was the incremental improvement in accuracy, which plateaued at a specific value as we expanded the training dataset.

**References:**
----
**$1 Algorithm:**
</br>Jacob O. Wobbrock, Andrew D. Wilson, and Yang Li. 2007. Gestures without libraries, toolkits or training: a $1 recognizer for user interface prototypes. In Proceedings of the 20th annual ACM symposium on User interface software and technology (UIST '07). ACM, New York, NY, USA, 159-168. https://doi.org/10.1145/1294211.1294238

**Gesture Templates:**
</br>https://depts.washington.edu/acelab/proj/dollar/index.html

**XML Files for Offline Recognition**
</br>https://depts.washington.edu/acelab/proj/dollar/index.html
