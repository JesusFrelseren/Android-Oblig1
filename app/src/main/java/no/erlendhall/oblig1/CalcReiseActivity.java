package no.erlendhall.oblig1;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class CalcReiseActivity extends AppCompatActivity implements JTotalCostFragment.OnUpdateCurrency {

    private FragmentTransaction transaction;
    private BaseCurrencyFragment baseCurrencyFragment;
    Bundle fragmentBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        //Load fragments
        JTotalCostFragment totalCostFragment = new JTotalCostFragment();
        baseCurrencyFragment = new BaseCurrencyFragment();

        //NOK is the default baseline currency
        fragmentBundle = new Bundle();
        fragmentBundle.putString("currencycode", "NOK");
        baseCurrencyFragment.setArguments(fragmentBundle);
        totalCostFragment.setArguments(fragmentBundle);

        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_base_container, baseCurrencyFragment);
        transaction.add(R.id.fragment_calc_container, totalCostFragment);
        transaction.commit();

    }


    //Here the activity receices a cc (currency code) from JTotalCostFragment as a string.
    //It uses the string to create a new BaseCurrencyFragment
    @Override
    public void onCurrencySelected(String cc) {
        baseCurrencyFragment = new BaseCurrencyFragment();
        fragmentBundle.putString("currencycode", " " + cc);
        baseCurrencyFragment.setArguments(fragmentBundle);
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_base_container, baseCurrencyFragment);
        transaction.commit();
    }
}


