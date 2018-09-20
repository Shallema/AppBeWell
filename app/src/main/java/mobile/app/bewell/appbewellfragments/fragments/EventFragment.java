package mobile.app.bewell.appbewellfragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import mobile.app.bewell.appbewellfragments.R;
import mobile.app.bewell.appbewellfragments.models.api.EventInfo;
import mobile.app.bewell.appbewellfragments.webapi.RequestApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment implements RequestApi.IRequestEvent {

    private View vue;
    private Context context;

    private TextView tvNom, tvDescription, tvLieu, tvDate, tvInscription;

    private RequestApi requestApi;


    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vue = inflater.inflate(R.layout.fragment_event, container, false);

        context = vue.getContext();

        initializeElements();

        requestApi = new RequestApi();
        requestApi.setCallback(this);
        requestApi.execute();

        return vue;
    }

    // Retour et set du contenu

    @Override
    public void onRequestResult(EventInfo temps) {
        if (temps != null) {
            String name = temps.getName();
            String description = temps.getDescription();
            String lieu = temps.getLieu();
            String date = temps.getDateComplete();

            tvNom.setText("Nom : " + name);
            tvDescription.setText("Description : " + description);
            tvLieu.setText("Lieu : " + lieu);
            tvDate.setText("Date : " + date);
        } else Toast.makeText(context, "Aucun événement à afficher pour l'instant", Toast.LENGTH_SHORT).show();
    }

    //Region Save pour les TextView

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("nom", tvNom.getText().toString());
        outState.putString("descr", tvDescription.getText().toString());
        outState.putString("lieu", tvLieu.getText().toString());
        outState.putString("date", tvDate.getText().toString());
    }

    //endregion

    private void initializeElements() {
        tvNom = vue.findViewById(R.id.tv_event_nom);
        tvDescription = vue.findViewById(R.id.tv_event_descr);
        tvLieu = vue.findViewById(R.id.tv_event_lieu);
        tvDate = vue.findViewById(R.id.tv_event_debut);
        tvInscription = vue.findViewById(R.id.tv_event_inscr);
    }

}
