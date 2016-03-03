package awhiting18.myapplication;

// Created By: Andrew Whiting
// Date: 10/19/2015
// Class: CS335 - Mobile Applications Programming
// This class represents a single student and their attendance status

public class Student {
    // Constants to represent all possible attendance statuses
    public static final String PRESENT = "Present";
    public static final String ABSENT  = "Absent";
    public static final String TARDY   = "Tardy";
    public static final String UNKNOWN = "Unknown";

    // Member variables
    private String mName,
                   mAttendanceStatus;

    // Constructors
    public Student(String name){
        mName = name;
        mAttendanceStatus = PRESENT;
    }

    public Student(String name, String attendanceStatus){
        mName = name;
        mAttendanceStatus = attendanceStatus;
    }

    // Class member setters and getters
    public String  getName            ()                         {return mName;}
    public void setName               (String mName)             {this.mName = mName;}
    public String  getAttendanceStatus()                         {return mAttendanceStatus;}
    public void setAttendanceStatus   (String mAttendanceStatus) {this.mAttendanceStatus = mAttendanceStatus;}

}
