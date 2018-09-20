package mobile.app.bewell.appbewellfragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.GregorianCalendar;

import mobile.app.bewell.appbewellfragments.R;
import mobile.app.bewell.appbewellfragments.modelsdb.DataIMC;

/**
 * A simple {@link Fragment} subclass.
 */
public class IMCFragment extends Fragment {

    private View vue;
    private Context context;

    private EditText etPoids, etTaille;
    private Button btnCalcul;
    private TextView tvResultat;

    private FirebaseAuth firebaseAuth;

    public IMCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vue = inflater.inflate(R.layout.fragment_imc, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        initializeElements();

        btnCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculIMC();
            }
        });

        return vue;
    }

    private void calculIMC() {
        String poidsEnString = etPoids.getText().toString();
        String tailleEnString = etTaille.getText().toString();

        if (poidsEnString != null && !"".equals(poidsEnString) && tailleEnString != null && !"".equals(tailleEnString)) {
            float poidsInput = Float.parseFloat(poidsEnString);
            float tailleInput = Float.parseFloat(tailleEnString) / 100;

            float imc = poidsInput / (tailleInput * tailleInput);

            affichageImc(imc);
        }
    }

    private void affichageImc(float imc) {
        String imcInterpretation;

        if (Float.compare(imc, 16f) <= 0) imcInterpretation = "Anorexie ou dénutrition";

        else if (Float.compare(imc, 16f) > 0 && Float.compare(imc, 18.5f) <= 0)
            imcInterpretation = "Maigreur";

        else if (Float.compare(imc, 18.5f) > 0 && Float.compare(imc, 25f) <= 0)
            imcInterpretation = "Corpulence normale";

        else if (Float.compare(imc, 25f) > 0 && Float.compare(imc, 30f) <= 0)
            imcInterpretation = "Surpoids";

        else if (Float.compare(imc, 30f) > 0 && Float.compare(imc, 35f) <= 0)
            imcInterpretation = "Obésité modérée (Classe 1)";

        else if (Float.compare(imc, 35f) > 0 && Float.compare(imc, 40f) <= 0)
            imcInterpretation = "Obésité élevé (Classe 2)";

        else imcInterpretation = "Obésite morbide ou massive";

        String result = imc + "\n" + imcInterpretation;
        tvResultat.setText(result);

        if(firebaseAuth.getCurrentUser() != null) {

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            long timestamp = (new GregorianCalendar()).getTimeInMillis();
            DataIMC dataIMC = new DataIMC(imc, imcInterpretation, timestamp);

            DatabaseReference myDb = firebaseDatabase.getReference();

            String timeKey = String.valueOf(dataIMC.getTimestampUser());
            myDb.child("ICMs").child(firebaseAuth.getUid()).push().setValue(dataIMC);
        }
    }

    private void initializeElements() {
        etPoids = vue.findViewById(R.id.et_imc_poids);
        etTaille = vue.findViewById(R.id.et_imc_taille);

        tvResultat = vue.findViewById(R.id.tv_calcul_imc_resultat);

        btnCalcul = vue.findViewById(R.id.btn_imc_calcul);
    }
}