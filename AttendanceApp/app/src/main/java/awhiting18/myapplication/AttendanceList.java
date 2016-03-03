package awhiting18.myapplication;

// Created By: Andrew Whiting
// Date: 10/19/2015
// Class: CS335 - Mobile Applications Programming
// This class represents a list of students and their attendance status'

import java.util.ArrayList;

public class AttendanceList{
    public static final int MAX_STUDENTS = 999;
    // Member variables
    private ArrayList<Student> mStudentsEnrolled = new ArrayList<>(); // An enrollment is an array of students
    private int                mCurrentStudent   = 0;                 // Current student being accessed

    // Populates the enrollment based off of an array of student names
    public AttendanceList(String[] studentNames){
        for(String studentName : studentNames) {
            mStudentsEnrolled.add(new Student(studentName));
        }
    }

    public AttendanceList(String[] studentNames, String[] attendanceStatuses){
        for(int studentCounter = 0; studentCounter < studentNames.length; studentCounter++) {
            mStudentsEnrolled.add(new Student(studentNames[studentCounter], attendanceStatuses[studentCounter]));
        }
    }

    // Adds one student
    public void addStudent(String studentName){
        mStudentsEnrolled.add(new Student(studentName));
    }

    // Sets the current student in the enrollment
    public void setCurrentStudent(int currentStudent){
        if(currentStudent < mStudentsEnrolled.size() && currentStudent >= 0)
            mCurrentStudent = currentStudent;
    }

    // Gets the current student in the enrollment
    public int getCurrentStudent(){ return mCurrentStudent;}

    // Moves to the next student in the enrollment
    public void nextStudent(){
        // Only go forward if not already at the end
        if(mCurrentStudent < mStudentsEnrolled.size() - 1)
            mCurrentStudent++;
    }

    // Moves to the previous student in the enrollment
    public void previousStudent(){
        // Only go back if not already at the beginning
        if(mCurrentStudent > 0)
            mCurrentStudent--;
    }

    // Gets the name of the current student
    public String getCurrentStudentName(){
        return mStudentsEnrolled.get(mCurrentStudent).getName();
    }

    // Gets the attendance status of the current student
    public String getCurrentStudentStatus(){
        return mStudentsEnrolled.get(mCurrentStudent).getAttendanceStatus();
    }

    // Sets the attendance status of the current student
    public void setCurrentStudentStatus(String status){
        mStudentsEnrolled.get(mCurrentStudent).setAttendanceStatus(status);
    }

    // Gets the number of students that were present
    public int getNumberPresent(){
        int numberPresent = 0;
        for(Student currentStudent : mStudentsEnrolled){
            if(currentStudent.getAttendanceStatus().equals(Student.PRESENT))
                numberPresent++;
        }
        return numberPresent;
    }

    // Gets the number of students that were absent
    public int getNumberAbsent(){
        int numberAbsent = 0;
        for(Student currentStudent : mStudentsEnrolled){
            if(currentStudent.getAttendanceStatus().equals(Student.ABSENT))
                numberAbsent++;
        }
        return numberAbsent;
    }

    // Gets the number of students that were tardy
    public int getNumberTardy(){
        int numberTardy = 0;
        for(Student currentStudent : mStudentsEnrolled){
            if(currentStudent.getAttendanceStatus().equals(Student.TARDY))
                numberTardy++;
        }
        return numberTardy;
    }

    // Gets the number of students with an unknown attendance status
    public int getNumberUnknown(){
        int numberUnknown = 0;
        for(Student currentStudent : mStudentsEnrolled){
            if(currentStudent.getAttendanceStatus().equals(Student.UNKNOWN))
                numberUnknown++;
        }
        return numberUnknown;
    }

    // Gets the total number of students enrolled in the class
    public int getStudentTotal(){
        return mStudentsEnrolled.size();
    }

    public String[] getStudentStatuses(){
        String[] studentStatuses = new String[MAX_STUDENTS];
        for(int studentCounter = 0; studentCounter < mStudentsEnrolled.size(); studentCounter++){
            studentStatuses[studentCounter] = mStudentsEnrolled.get(studentCounter).getAttendanceStatus();
        }
        return studentStatuses;
    }
}
