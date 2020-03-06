package app.mad.beatthesweet;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager implements DefineDBEvents{
    private FirebaseFirestore firebaseFirestore;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    private String TAG = "WeightActivityFinal.java";
    private String documentId;
    private Double number = 0.0;

    public void retrieveWeightDB () {
        db.collection("usersCollection").document(currentUser.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                String email = currentUser.getEmail();
                                if(document.exists()) {
                                    number = displayContents(document);
                                    onFetchWeight(new User(email, number));
                                    documentId = document.getId();
                                    Log.d(TAG, document.getId() + " WeightCP4 => " + document.getData());
                                }
                                else {
                                    onFetchWeight(new User(email, 0.0));
                                }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void updateWeight (User user) {
        Map<String, Object> enterDataMap = new HashMap<>();
        enterDataMap.put("weight", user.getWeight());
        Log.d(TAG, "WeightCP4: WEIGHTTTTTT" + user.getWeight());
        db.collection("usersCollection").document(user.getEmail())
                .set(user, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "WeightCP4: DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void deleteUser (User user) {
        db.collection("usersCollection").document(user.getEmail())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "WeightCP4: Successfully deleted account!");
                        mAuth.signOut();
                        onDeleteSuccess();
//                        Intent signOutIntent = new Intent(WeightActivity.this, MainActivity.class);
//                        startActivity(signOutIntent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "CP4: Error deleting account ",e);
                    }
                });
    }

    private Double displayContents(DocumentSnapshot document) {
        Double numberWeightForDisplay = Double.parseDouble (document.getData().get("weight")+"");
        return numberWeightForDisplay;
    }

    public void onFetchWeight(User user) {}

    @Override
    public void onDeleteSuccess() {

    }
}
