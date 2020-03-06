package app.mad.beatthesweet;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DialogFragment;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Log.d("Menu","Menu Activity Start");
        TextView tv = findViewById(R.id.user_email);
        tv.setText(mAuth.getCurrentUser().getEmail());
    }


    public void onClickSignOut(View view) {
        mAuth.signOut();

        Intent signOutIntent = new Intent(this, MainActivity.class);
        startActivity(signOutIntent);
    }

    public void onClickUpdateInfo(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");
    }
    public void onClickWeight(View v) {
        Intent intent = new Intent(this, WeightActivityFinal.class);
        startActivity(intent);
    }
}
