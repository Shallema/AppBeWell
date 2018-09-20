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

import mobile.app.bewell.appbewellfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FMCFragment extends Fragment {

    private View vue;
    private Context context;

    private Button btnFemme, btnHomme;
    private EditText etInputAge;
    private TextView tvResultat;


    public FMCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vue = inflater.inflate(R.layout.fragment_fmc, container, false);

        context = vue.getContext();

        initializeElements();

        onClickMethods();

        return vue;
    }


    private void calculFCMFemme() {
        String ageEnString = etInputAge.getText().toString();

        if (ageEnString != null && !"".equals(ageEnString)) {
            float inputAge = Float.parseFloat(ageEnString);
            float fcm = 226 - inputAge;

            affichageFCM(fcm);

        }
    }

    private void calculFCMHomme() {
        String ageEnString = etInputAge.getText().toString();

        if (ageEnString != null && !"".equals(ageEnString)) {
            float inputAge = Float.parseFloat(ageEnString);
            float fcm = 220 - inputAge;

            affichageFCM(fcm);

        }
    }

    private void affichageFCM(float fcm) {
        String fcmResultat = "Votre fréquence cardiaque maximale est: " + fcm + "BPM";

        tvResultat.setText(fcmResultat);
    }

    private void initializeElements() {
        btnFemme = vue.findViewById(R.id.btn_fcm_femme);
        btnHomme = vue.findViewById(R.id.btn_fcm_homme);
        etInputAge = vue.findViewById(R.id.et_fcm_age);
        tvResultat = vue.findViewById(R.id.tv_fcm_resultat);
    }

    private void onClickMethods() {
        btnFemme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculFCMFemme();
            }
        });

        btnHomme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculFCMHomme();
            }
        });

    }

}
