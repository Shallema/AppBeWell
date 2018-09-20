package mobile.app.bewell.appbewellfragments.fragments;


import android.app.ProgressDialog;
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

import mobile.app.bewell.appbewellfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EspacePersoFragment extends Fragment {

    private Context context;
    private View vue;

    private int counter;

    private EditText etLogin, etPwd;
    private Button btnEnter;
    private TextView tvEssais, tvReg;
    private TextView tvForgotPwd;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;


    public EspacePersoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vue = inflater.inflate(R.layout.fragment_espace_perso, container, false);
        context = vue.getContext();

        initializeElement();

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser(); //Check dans Firebase si l'utilisateur s'est déjà inscrit

       //if(user != null) {
           //getActivity().finish();
           //switchFragment(new InsidePersoFragment());
       //}

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation(etLogin.getText().toString(), etPwd.getText().toString());
            }
        });

        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegistrationActivity();
            }
        });

        tvForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new ForgotPasswordFragment());
            }
        });

        return vue;
    }

    private void initializeElement() {
        etLogin = vue.findViewById(R.id.et_perso_login);
        etPwd = vue.findViewById(R.id.et_perso_pwd);

        tvEssais = vue.findViewById(R.id.tv_perso_nbessais);
        tvReg = vue.findViewById(R.id.tv_perso_signup);
        tvForgotPwd = vue.findViewById(R.id.tv_perso_forgotpwd);

        btnEnter = vue.findViewById(R.id.btn_perso_enter);

        tvEssais.setText("Nombre d'essais restants : 3");

        progressDialog = new ProgressDialog(context);
    }

    private void validation(String userName, String userPassword) {

        progressDialog.setMessage("Ne partez pas, ça va arriver");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    //Toast.makeText(context,"Youpie !", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }
                else {
                    Toast.makeText(context, "Erreur Login", Toast.LENGTH_SHORT).show();
                    counter = 3;
                    counter--;
                    tvEssais.setText("Nombre d'essais restants: " + counter);
                    progressDialog.dismiss();
                    if(counter == 0) {
                        btnEnter.setEnabled(false);
                    }
                }
            }
        });
    }

    private void goToRegistrationActivity() {
        switchFragment(new RegisterFragment());
    }

    private void goToInsidePersoActivity() {
        switchFragment(new InsidePersoFragment());
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();

        if(emailFlag) {
            goToInsidePersoActivity();
        }
        else {
            Toast.makeText(context, "Vérifier votre adresse email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();

    }
}
