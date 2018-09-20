package mobile.app.bewell.appbewellfragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mobile.app.bewell.appbewellfragments.R;
import mobile.app.bewell.appbewellfragments.modelsdb.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private View vue;
    private Context context;

    private EditText etProfileName, etProfileAge, etProfileEmail;
    private Button btnEdit;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vue = inflater.inflate(R.layout.fragment_profile, container, false);

        context = vue.getContext();

        initializeElements();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid());

        //Données rapatriées dans le fragment
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                etProfileAge.setText(userProfile.getAge());
                etProfileEmail.setText(userProfile.getEmail());
                etProfileName.setText(userProfile.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Remplir cette méthode
            }
        });

        return vue;
    }

    private void initializeElements() {
        etProfileName = vue.findViewById(R.id.et_profile_username);
        etProfileAge = vue.findViewById(R.id.et_profile_age);
        etProfileEmail = vue.findViewById(R.id.et_profile_email);

        btnEdit = vue.findViewById(R.id.btn_profile_update);
    }

    //Non-utilisée mais servira lors de l'edit des données
    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();
    }

}
