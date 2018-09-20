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
public class SeuilAnaerobieFragment extends Fragment {

    private View vue;
    private Context context;

    private EditText etInput;
    private Button btnCalcul;
    private TextView tvResultat;


    public SeuilAnaerobieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vue = inflater.inflate(R.layout.fragment_seuil_anaerobie, container, false);
        context = vue.getContext();

        initializeElements();

        btnCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculSeuilAnaerobie();
            }
        });

        return vue;
    }

    private void calculSeuilAnaerobie() {
        String fcmEnString = etInput.getText().toString();

        if (fcmEnString != null && !"".equals(fcmEnString)) {
            float fcmInput = Float.parseFloat(fcmEnString) / 10;

            float seuil = fcmInput * 9;

            affichageSeuil(seuil);
        }
    }

    private void affichageSeuil(float seuil) {
        String seuilResultat = "Votre seuil d'anaérobie est : " + seuil;

        tvResultat.setText(seuilResultat);
    }

    private void initializeElements() {
        etInput = vue.findViewById(R.id.et_anaer_fcminput);
        btnCalcul = vue.findViewById(R.id.btn_anaer_calcul);
        tvResultat = vue.findViewById(R.id.tv_anaer_resultat);

    }

}
