package mobile.app.bewell.appbewellfragments.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import mobile.app.bewell.appbewellfragments.R;

public class MainFragment extends Fragment {

    private View vue;
    private Context context;

    private GridLayout mainGrid;

    private CardView cvConcept, cvMotivation, cvContact, cvInfos, cvCours, cvCalcul, cvEspacePerso;

    private FirebaseAuth firebaseAuth;


    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vue = inflater.inflate(R.layout.fragment_main, container, false);
        context = vue.getContext();

        firebaseAuth = FirebaseAuth.getInstance();

        initializeElements();

        onClickMethods(mainGrid);

        setHasOptionsMenu(true);

        return vue;
    }

    private void logout() {
        firebaseAuth.signOut();
        switchFragment(new MainFragment());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutMenu:
                logout();
                break;
            case R.id.profileMenu:
                switchFragment(new ProfileFragment());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeElements() {
        mainGrid = vue.findViewById(R.id.gl_main);

        cvConcept = vue.findViewById(R.id.cv_main_concept);
        cvMotivation = vue.findViewById(R.id.cv_main_motivation);
        cvContact = vue.findViewById(R.id.cv_main_contact);
        cvInfos = vue.findViewById(R.id.cv_main_infos);
        cvCours = vue.findViewById(R.id.cv_main_cours);
        cvCalcul = vue.findViewById(R.id.cv_main_calcul);
        cvEspacePerso = vue.findViewById(R.id.cv_main_espaceperso);
    }

    public void onClickMethods(GridLayout mainGrid) {
        cvConcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new ConceptFragment());
            }
        });

        cvMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new MotivationFragment());
            }
        });

        cvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new ContactFragment());
            }
        });

        cvCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new CalculFragment());
            }
        });

        cvEspacePerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth.getCurrentUser() != null) {
                    switchFragment(new InsidePersoFragment());
                } else {
                    switchFragment(new EspacePersoFragment());
                }
            }
        });

        cvInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new EventFragment());
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();
    }
}
