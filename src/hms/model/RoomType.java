/**
 * Room type static constants
 */

package hms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author team slam
 */
public class RoomType {
    
    public static String DNN = "DNN";
    public static String DNH = "DNH";
    public static String DSN = "DSN";
    public static String DSH = "DSH";
    public static String QNN = "QNN";
    public static String QNH = "QNH";
    public static String QSN = "QSN";
    public static String QSH = "QSH";
    public static String KNN = "KNN";
    public static String KNH = "KNH";
    public static String KSN = "KSN";
    public static String KSH = "KSH";
    
    public static ObservableList<String> getRoomTypes() {
        ObservableList<String> roomTypes = FXCollections.observableArrayList();
        roomTypes.addAll(
                RoomType.DNN,
                RoomType.DNH,
                RoomType.DSN,
                RoomType.DSH,
                RoomType.QNN,
                RoomType.QNH,
                RoomType.QSN,
                RoomType.QSH,
                RoomType.KNN,
                RoomType.KNH,
                RoomType.KSN,
                RoomType.KSH );
        return roomTypes;
    }
}
