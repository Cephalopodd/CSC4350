/**
 * RoomStatusCode 
 * 
 * This data type is a convenience to encapsulate the 
 * available room status codes. Room status codes indicate
 * the status of a room, including whether the room is:
 * clean, dirty, inspected, vacant dirty,
 * or out of service.
 *
 */
package hms.model;

/**
 *
 * @author Team SLAM
 */
public enum ReservationStatusCode {
    PENDING,CHECKEDIN,CHECKEDOUT,CANCELLED
}