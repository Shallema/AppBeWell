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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobile.app.bewell.appbewellfragments.R;
import mobile.app.bewell.appbewellfragments.modelsdb.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private Context context;
    private View vue;

    private String nom, email, password, age;

    private EditText etNom, etEmail, etPswd, etAge;
    private Button btnInscr;
    private TextView tvLogin;

    private FirebaseAuth firebaseAuth;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vue = inflater.inflate(R.layout.fragment_register, container, false);

        context = vue.getContext();

        initializeElements();

        firebaseAuth = FirebaseAuth.getInstance();

        onClickMethods();

        return vue;
    }

    private void inscription() {
        String user_email = etEmail.getText().toString().trim();
        String user_password = etPswd.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    sendEmailVerification();
                } else
                    Toast.makeText(context, "Erreur d'authentification", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToLoginActivity() {
        switchFragment(new EspacePersoFragment());
    }

    private Boolean validation() {
        Boolean resultat = false;

        nom = etNom.getText().toString();
        //prenom = etPrenom.getText().toString();
        password = etPswd.getText().toString();
        email = etEmail.getText().toString();
        age = etAge.getText().toString();
        //username = etUsername.getText().toString();

        if (nom.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()) {
            Toast.makeText(context, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
            btnInscr.setEnabled(false);
        } else
            resultat = true;

        return resultat;
    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(context, "Vous êtes bien enregistré, un email de vérification vous a été envoyé", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        //getActivity().finish();
                        goToLoginActivity();
                    } else {
                        Toast.makeText(context, "L'émail de vérification n'a pu être envoyé", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
//        UserProfile userProfile = new UserProfile(age, email, nom);
//
//        myRef.setValue(userProfile);
//        myRef.push();

        UserProfile userProfile = new UserProfile(age, email, nom);

        DatabaseReference myDb = firebaseDatabase.getReference();
        myDb.child("Users").child(firebaseAuth.getUid()).setValue(userProfile);

    }

    private void initializeElements() {
        etNom = vue.findViewById(R.id.et_reg_nom);
        etAge = vue.findViewById(R.id.et_reg_age);
        etEmail = vue.findViewById(R.id.et_reg_email);
        etPswd = vue.findViewById(R.id.et_reg_pwd);

        btnInscr = vue.findViewById(R.id.btn_reg_inscr);

        tvLogin = vue.findViewById(R.id.tv_reg_login);

    }

    private void onClickMethods() {
        btnInscr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) inscription();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginActivity();
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();
    }

}
