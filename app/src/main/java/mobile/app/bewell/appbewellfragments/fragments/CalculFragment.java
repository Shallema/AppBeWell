package mobile.app.bewell.appbewellfragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import mobile.app.bewell.appbewellfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalculFragment extends Fragment {

    private View vue;

    private Context context;

    private CardView cvIMC, cvFCM, cvAnaer;

    private GridLayout mainGrid;


    public CalculFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vue = inflater.inflate(R.layout.fragment_calcul, container, false);
        context = vue.getContext();

        initializeElements();

        onClickMethods(mainGrid);

        return vue;
    }

    private void initializeElements() {
        cvIMC = vue.findViewById(R.id.cv_calcul_imc);
        cvFCM = vue.findViewById(R.id.cv_calcul_pouls);
        cvAnaer = vue.findViewById(R.id.cv_calcul_seuil);
    }

    public void onClickMethods(GridLayout mainGrid) {
        cvIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new IMCFragment());
            }
        });

        cvFCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new FMCFragment());
            }
        });

        cvAnaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new SeuilAnaerobieFragment());
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();
    }
}

