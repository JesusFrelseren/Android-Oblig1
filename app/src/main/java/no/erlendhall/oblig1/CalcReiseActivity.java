package no.erlendhall.oblig1;


import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//todo: Skal nå kalkulere totale reisekostnader, CalcReise.kt fjernes
public class CalcReiseActivity extends AppCompatActivity {
    String currencyCode;
    TextView conversion;
    TravelCalculator tc;
    private FragmentTransaction transaction;
    private TotalCostFragment totalCostFragment;
    private BaseCurrencyFragment baseCurrencyFragment;
    Bundle fragmentBundle;
    private Spinner spinCurrencies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        //Bundle extras = getIntent().getExtras();


        //Load fragments
        totalCostFragment = new TotalCostFragment();
        baseCurrencyFragment = new BaseCurrencyFragment();

        //onCreate currency defaults to NOK
        fragmentBundle = new Bundle();
        fragmentBundle.putString("currencycode", "NOK");
        baseCurrencyFragment.setArguments(fragmentBundle);
        totalCostFragment.setArguments(fragmentBundle);

        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_base_container, baseCurrencyFragment);
        transaction.add(R.id.fragment_calc_container, totalCostFragment);
        transaction.commit();

    }



    private void updateLayout(int numDays) {

        // Show total travel costs
        Double sum = tc.CalculateStay(currencyCode, numDays);
        TextView txt = findViewById(R.id.lbl_sum);
        String sumRounded = String.format("%.2f", sum);
        txt.setText(sumRounded + "NOK");
    }

    private void setCurrency(String newCode, String newCurrency) {
        fragmentBundle.putString(newCode, newCurrency);
        baseCurrencyFragment.setArguments(fragmentBundle);
        totalCostFragment.setArguments(fragmentBundle);
    }


}


//Load spinner with currencies


        /*
        String[] spinner_content = res.getStringArray(R.array.currencies);
        List<String> spinnerArray = new List<>(Arrays.asList(spinner_content));
        spinCurrencies = findViewById(R.id.cur_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, R.layout.support_simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinCurrencies.setAdapter(adapter);

    }
        /*
        if(!(extras.getString("c") == null)) {
            currencyCode = extras.getString("c");
        }

        // Display currency conversion
        tc = new TravelCalculator();
        conversion = findViewById(R.id.conversion);
        Double output = tc.getBases().get(currencyCode) * 100.0;
        conversion.setText(" " + output + currencyCode);

*/
