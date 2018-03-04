package no.erlendhall.oblig1;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


//todo: Skal nå kalkulere totale reisekostnader, CalcReise.kt fjernes
public class CalcReiseActivity extends AppCompatActivity implements JTotalCostFragment.OnUpdateCurrency {

    private FragmentTransaction transaction;
    private JTotalCostFragment totalCostFragment;
    private BaseCurrencyFragment baseCurrencyFragment;
    Bundle fragmentBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        //Bundle extras = getIntent().getExtras();


        //Load fragments
        totalCostFragment = new JTotalCostFragment();
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



    @Override
    public void onCurrencySelected(String i) {
        baseCurrencyFragment = new BaseCurrencyFragment();
        fragmentBundle.putString("currencycode", " " + i);
        baseCurrencyFragment.setArguments(fragmentBundle);
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_base_container, baseCurrencyFragment);
        transaction.commit();
    }
}


