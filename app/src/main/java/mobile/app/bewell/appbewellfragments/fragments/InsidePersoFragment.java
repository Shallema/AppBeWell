package mobile.app.bewell.appbewellfragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import mobile.app.bewell.appbewellfragments.R;
import mobile.app.bewell.appbewellfragments.modelsdb.DataIMC;
import mobile.app.bewell.appbewellfragments.modelsdb.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class InsidePersoFragment extends Fragment {

    private Context context;
    private View vue;

    private CardView cvInside;

    private TextView tvIMC, tvIntepretationIMC, tvFrequentation;

    private Button btnRecupImc, btnRecupFrequ;

    //private ListView lvIMC, lvFrequentation;
    //List<DataIMC> listIMC = new ArrayList<>();
    //ArrayAdapter<DataIMC> adapter;
    //List<String> imcList;

    private String imcUser, interpretationUser;
    private long timestampUser;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public InsidePersoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vue = inflater.inflate(R.layout.fragment_inside_perso, container, false);
        context = vue.getContext();

        initializeElements();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("ICMs").child(firebaseAuth.getUid());

        //Récupération des infos de la BD
        Query query = databaseReference.limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    DataIMC dataIMC = data.getValue(DataIMC.class);
                    tvIMC.setText(dataIMC.getImcUser() + "");
                    tvIntepretationIMC.setText(dataIMC.getInterpretationUser());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        //region first test LV
//        final ArrayAdapter<DataIMC> adapter = new ArrayAdapter<DataIMC>(context, android.R.layout.simple_list_item_1, listIMC);

//        imcList = new ArrayList<>();
//
//        databaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.getUid());
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot items: dataSnapshot.getChildren()) {
//                    dataIMC = items.getValue(DataIMC.class);
//
//
//                }
//
//                adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
//                lvIMC.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        //endregion

        //region Old link to DB
//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
////                imcUser = (String) dataSnapshot.child(imcUser).getValue();
////                interpretationUser = (String) dataSnapshot.child(interpretationUser).getValue();
////
////                listIMC.add(interpretationUser);
//
//               listIMC = dataSnapshot.get(DataIMC.class);
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        //endregion

        //region not working LV
//
//        Query query = FirebaseDatabase.getInstance()
//                .getReference()
//                .child("ICMs")
//                .limitToLast(50);
//
//        FirebaseListOptions<DataIMC> options = new FirebaseListOptions.Builder<DataIMC>()
//                .setQuery(query, DataIMC.class)
//                .setLayout(android.R.layout.two_line_list_item)
//                .build();
//
//        FirebaseListAdapter<DataIMC> adapter = new FirebaseListAdapter<DataIMC>(options) {
//            @Override
//            protected void populateView(View v, DataIMC model, int position) {
//                ((TextView)v.findViewById(android.R.id.text1)).setText(String.valueOf(model.getImcUser()));
//                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getInterpretationUser());
//            }
//        };

        //lvIMC.setAdapter(adapter);

        //endregion

        return vue;
    }

    private void initializeElements() {

        cvInside = vue.findViewById(R.id.cv_perso_inside);

        tvIMC = vue.findViewById(R.id.tv_perso_imcdb);
        tvIntepretationIMC = vue.findViewById(R.id.tv_perso_interprdb);
        tvFrequentation = vue.findViewById(R.id.tv_perso_passagesdb);
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
    }
}
