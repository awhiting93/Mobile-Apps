package awhiting18.myapplication;

// Created By: Andrew Whiting
// Date: 10/19/2015
// Class: CS335 - Mobile Applications Programming
// This activity acts as a controller for an enrollment model

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class StudentListActivity extends Activity {

    // App widgets that need to be accounted for
    private AttendanceList attendanceList;
    private TextView nameView;
    private RadioButton presentButton;
    private RadioButton absentButton;
    private RadioButton tardyButton;
    private RadioButton unknownButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);


        // Associate widget objects with their Resources
        Button nextButton = (Button)findViewById(R.id.nextButton);
        Button previousButton = (Button)findViewById(R.id.previousButton);
        Button finishButton = (Button)findViewById(R.id.finishButton);

        // Additional app widgets that must be accounted for and their associated Resources
        nameView = (TextView)findViewById(R.id.studentNameView);
        presentButton = (RadioButton)findViewById(R.id.presentButton);
        absentButton = (RadioButton)findViewById(R.id.absentButton);
        tardyButton = (RadioButton)findViewById(R.id.tardyButton);
        unknownButton = (RadioButton)findViewById(R.id.unknownButton);

        // List of prepopulated student names for the class enrollment list
        String[] studentNames = getResources().getStringArray(R.array.students);
        attendanceList = new AttendanceList(studentNames);

        if(savedInstanceState != null) {
            attendanceList = new AttendanceList(studentNames, savedInstanceState.getStringArray("studentStatuses"));
            attendanceList.setCurrentStudent(savedInstanceState.getInt("currentStudent"));
        }

        nameView.setText(attendanceList.getCurrentStudentName());

        // Moves to the next student in the class list
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStudentStatus();
                attendanceList.nextStudent();
                nameView.setText(attendanceList.getCurrentStudentName());
                showStudentStatus();
            }
        });

        // Moves to the previous student in the class list
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStudentStatus();
                attendanceList.previousStudent();
                nameView.setText(attendanceList.getCurrentStudentName());
                showStudentStatus();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStudentStatus();
                Intent stats = new Intent(StudentListActivity.this, StatsActivity.class);
                stats.putExtra("numberPresent", attendanceList.getNumberPresent());
                stats.putExtra("numberAbsent", attendanceList.getNumberAbsent());
                stats.putExtra("numberTardy", attendanceList.getNumberTardy());
                stats.putExtra("numberUnknown", attendanceList.getNumberUnknown());
                stats.putExtra("numberTotal", attendanceList.getStudentTotal());
                startActivity(stats);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle studentList){
        super.onSaveInstanceState(studentList);
        studentList.putStringArray("studentStatuses", attendanceList.getStudentStatuses());
        studentList.putInt("currentStudent", attendanceList.getCurrentStudent());
    }

    // Sets the status for the current student based on the radio button selected
    public void setStudentStatus(){
        if(presentButton.isChecked())
            attendanceList.setCurrentStudentStatus(Student.PRESENT);
        else if(absentButton.isChecked())
            attendanceList.setCurrentStudentStatus(Student.ABSENT);
        else if(tardyButton.isChecked())
            attendanceList.setCurrentStudentStatus(Student.TARDY);
        else if(unknownButton.isChecked())
            attendanceList.setCurrentStudentStatus(Student.UNKNOWN);
    }

    // Updates the radio button selected based on the current student's attendance status
    public void showStudentStatus(){
        if(attendanceList.getCurrentStudentStatus().equals(Student.PRESENT))
            presentButton.setChecked(true);
        else if(attendanceList.getCurrentStudentStatus().equals(Student.ABSENT))
            absentButton.setChecked(true);
        else if(attendanceList.getCurrentStudentStatus().equals(Student.TARDY))
            tardyButton.setChecked(true);
        else if(attendanceList.getCurrentStudentStatus().equals(Student.UNKNOWN))
            unknownButton.setChecked(true);
    }
}
