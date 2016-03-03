package awhiting18.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by awhiting18 on 11/13/2015.
 */
public class DateFragment extends DialogFragment {
    private DatePicker ourDate;

    @Override
    public Dialog onCreateDialog(Bundle sis){
        Date myDate = (Date)getArguments().getSerializable("fred");

        Calendar myCal = Calendar.getInstance();
        myCal.setTime(myDate);
        int year = myCal.get(myCal.YEAR);
        int month = myCal.get(myCal.MONTH);;
        int day = myCal.get(myCal.DAY_OF_MONTH);;

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        ourDate = (DatePicker)v.findViewById(R.id.myDatePicker);
        ourDate.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year  = ourDate.getYear();
                        int month = ourDate.getMonth();
                        int day   = ourDate.getDayOfMonth();
                        Date myDateValue = new GregorianCalendar(year, month, day).getTime();
                        Intent i = new Intent();
                        i.putExtra("newDate", myDateValue);

                        getTargetFragment().onActivityResult(0, Activity.RESULT_OK, i);
                    }
                })
                .setTitle("Testing this fragment")
                .setPositiveButton("Ok", null)
                .setNeutralButton("Huh?", null)
                .setNegativeButton("Cancel", null)
                .create();
    }
}
