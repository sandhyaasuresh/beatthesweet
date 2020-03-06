package app.mad.beatthesweet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WeightActivityFinal extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private TextView weightTextView;
    private EditText weightUpdateEditText;
    User user;
    private String TAG = "WeightActivityFinal";
    Double number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_final);
        databaseManager = new DatabaseManager() {
            public void onFetchWeight(User user) {
            setWeight(user);
        }
            public void onDeleteSuccess() {
                redirect();
            }
        };
        weightTextView = findViewById(R.id.weightTV);
        weightUpdateEditText = findViewById(R.id.weightUpdateEditText);
        databaseManager.retrieveWeightDB();
        Log.d(TAG, "Weight CP4: eeeee" + number);
        //setWeight(number);
    }
    public void onClickUpdate (View v) {
        Double updatedWeight = Double.parseDouble(weightUpdateEditText.getText().toString());
        user.setWeight(updatedWeight);
        databaseManager.updateWeight(user);
        setWeight(user);
    }

    public void onClickDelete(View view) {
        databaseManager.deleteUser(user);
    }
    public void redirect() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void setWeight (User user)  {
        this.user = user;
        if(user.getWeight() == null) {
            weightTextView.setText("none");
        }
        else {
            weightTextView.setText(user.getWeight().toString());
        }
    }
}
