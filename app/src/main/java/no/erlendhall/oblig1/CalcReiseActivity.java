package no.erlendhall.oblig1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


//todo: Skal nå kalkulere totale reisekostnader, CalcReise.kt fjernes
public class CalcReiseActivity extends AppCompatActivity {
    String currencyCode;
    TextView conversion;
    TravelCalculator tc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Bundle extras = getIntent().getExtras();

        //todo: sett inn nytt CurrencyManagerFragment

        if(!(extras.getString("c") == null)) {
            currencyCode = extras.getString("c");
        }

        // Display currency conversion
        tc = new TravelCalculator();
        conversion = findViewById(R.id.conversion);
        Double output = tc.getBases().get(currencyCode) * 100.0;
        conversion.setText(" " + output + currencyCode);


        // EditText listener
        EditText numDays = findViewById(R.id.antall_dager);
        numDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // useless override
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals("")) {
                    int numDays = Integer.parseInt(charSequence.toString());
                    updateLayout(numDays);
                }
                //todo: Send totalsum og valuta til CurrencyManager

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // useless override
            }
        });



    }

    private void updateLayout(int numDays) {

        // Show total travel costs
        Double sum = tc.CalculateStay(currencyCode, numDays);
        TextView txt = findViewById(R.id.lbl_sum);
        String sumRounded = String.format("%.2f", sum);
        txt.setText(sumRounded + "NOK");
    }


}
