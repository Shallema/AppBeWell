package mobile.app.bewell.appbewellfragments.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobile.app.bewell.appbewellfragments.R;

public class ConceptFragment extends Fragment {

    private View vue;

    private Context context;

    private TextView tvTitle, tvFit, tvCoursColl, tvCoursIndiv, tvParam;

    public ConceptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vue = inflater.inflate(R.layout.fragment_concept, container, false);
        context = vue.getContext();

        initializeElements();

        onClickMethods();

        return vue;
    }

    private void onClickMethods() {
        tvFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("http://www.bewell.brussels/concept/");
            }
        });

        tvCoursColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("http://www.bewell.brussels/cours/cours-collectifs/");
            }
        });

        tvCoursIndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("http://www.bewell.brussels/cours/cours-individuels/");
            }
        });

        tvParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("http://www.bewell.brussels/paramedical/");
            }
        });
    }

    public void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if(intent.resolveActivity(getContext().getPackageManager()) != null) startActivity(intent);
    }

    private void initializeElements() {
        tvFit = vue.findViewById(R.id.tv_fit);
        tvCoursColl = vue.findViewById(R.id.tv_cours_coll);
        tvCoursIndiv = vue.findViewById(R.id.tv_cours_indiv);
        tvParam = vue.findViewById(R.id.tv_param);
    }
}
