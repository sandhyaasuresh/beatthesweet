package app.mad.beatthesweet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Dialog;
import java.util.Calendar;

import io.opencensus.tags.Tag;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "MainActivity";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView tv = (TextView) getActivity().findViewById(R.id.textView);
        tv.setText("Date Diagnosed Changed to: ");

        String stringOfDate = day + "/" + month + "/" + year;
        tv.setText(tv.getText() + stringOfDate);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "CP3: DatePickerFragment onAttach() invoked");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CP3: DatePickerFragment onCreate() invoked");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "CP3: DatePickerFragment onViewCreated() invoked");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "CP3: DatePickerFragment onActivityCreated() invoked");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "CP3: DatePickerFragment onResume() invoked");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "CP3: DatePickerFragment onPause() invoked");
    }
}
