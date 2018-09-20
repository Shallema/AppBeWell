package mobile.app.bewell.appbewellfragments.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mobile.app.bewell.appbewellfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements OnMapReadyCallback {

    private View vue;
    private Context context;

    private GoogleMap gMap;

    private CardView cvTel, cvMail, cvFacebook, cvInstagram, cvEssaigratuit, cvClock, cvTarifs;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vue = inflater.inflate(R.layout.fragment_contact, container, false);
        context = vue.getContext();

        initializeElements();

        mapFragment();

        onClickMethods();

        return vue;
    }

    private void initializeElements() {
        cvTel = vue.findViewById(R.id.cv_contact_tel);
        cvMail = vue.findViewById(R.id.cv_contact_mail);
        cvFacebook = vue.findViewById(R.id.cv_contact_facebook);
        cvInstagram = vue.findViewById(R.id.cv_contact_insta);
        cvEssaigratuit = vue.findViewById(R.id.cv_contact_essaigratuit);
        cvClock = vue.findViewById(R.id.cv_contact_horaire);
        cvTarifs = vue.findViewById(R.id.cv_contact_tarifs);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng bewell = new LatLng(50.805660, 4.358212);
        googleMap.addMarker(new MarkerOptions().position(bewell)
                .title("Sur BeWell"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bewell, 19f));
    }

    private void onClickMethods() {
        cvTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionCall();
            }
        });

        cvMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionSendInfoMail("info@bewell.brussels", "Question");
            }
        });

        cvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToFacebook("https://www.facebook.com/be.well.pts/");
            }
        });

        cvInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionGoToInsta("https://www.instagram.com/bewell.personaltrainingstudio/?hl=fr");
            }
        });

        cvEssaigratuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cvClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new HoraireFragment());
            }
        });

        cvTarifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void mapFragment() {
        //Fragment pour la Map
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void actionCall() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle("Appeler?");

        alertDialog.setMessage("02 375 56 64");

        alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });

        alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "02 375 56 64"));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(callIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(callIntent);
                }
            }
        });

        alertDialog.show();
    }

    private void actionSendInfoMail(String address, String subject) {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:info@bewell.brussels"));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (mailIntent.resolveActivity(getActivity().getPackageManager()) != null) startActivity(mailIntent);
    }

    private void actionGoToFacebook(String url) {
        Uri webPage = Uri.parse(url);
        Intent FbIntent = new Intent(Intent.ACTION_VIEW, webPage);
        if(FbIntent.resolveActivity(getContext().getPackageManager()) != null) startActivity(FbIntent);
    }

    private void actionGoToInsta(String url) {
        Uri webPage = Uri.parse(url);
        Intent InstaIntent = new Intent(Intent.ACTION_VIEW, webPage);
        if(InstaIntent.resolveActivity(getContext().getPackageManager()) != null) startActivity(InstaIntent);
    }

    private void actionSendMail(String address, String subject, String message) {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:info@bewell.brussels"));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT, message);
        if (mailIntent.resolveActivity(getActivity().getPackageManager()) != null) startActivity(mailIntent);
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).addToBackStack(null).commit();
    }
}
