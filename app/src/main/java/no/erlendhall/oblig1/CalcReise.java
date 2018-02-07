package no.erlendhall.oblig1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


public class CalcReise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Bundle extras = getIntent().getExtras();

        if(!(extras.getString("c") == null)) {
            String countryCode = extras.getString("c");
        }

    }


}
