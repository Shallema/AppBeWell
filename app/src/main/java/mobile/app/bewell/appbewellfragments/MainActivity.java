package mobile.app.bewell.appbewellfragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import mobile.app.bewell.appbewellfragments.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchFragments();

    }

    private void launchFragments() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, new MainFragment()).commit();

    }
}
