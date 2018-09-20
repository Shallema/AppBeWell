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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.app.bewell.appbewellfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment {

    private Context context;
    private View vue;

    private EditText etPwdEmail;
    private Button btnReset;

    private FirebaseAuth firebaseAuth;


    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        vue = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        context = vue.getContext();

        initializeElements();

        firebaseAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = etPwdEmail.getText().toString().trim();

                if(userEmail.equals("")) {
                    Toast.makeText(context, "Entrez votre adresse email d'inscription", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(context, "Mot de passe récupéré, email envoyé !", Toast.LENGTH_SHORT).show();
                                switchFragment(new EspacePersoFragment());
                            }
                            else {
                                Toast.makeText(context, "Erreur lors de l'envoi du mot de passe de récupération", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        return vue;
    }

    private void initializeElements() {
        etPwdEmail = vue.findViewById(R.id.et_pwd_email);
        btnReset = vue.findViewById(R.id.btn_pwd_reset);
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();

    }

}
