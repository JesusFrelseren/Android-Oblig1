package no.erlendhall.oblig1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class JTotalCostFragment extends Fragment {
    private double previousBase, inNok;
    private Bundle fragmentBundle;
    OnUpdateCurrency callback;

    public JTotalCostFragment newInstance() {
        previousBase = 1.0;
        inNok = 0.0;
        fragmentBundle = new Bundle();
        return new JTotalCostFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc_total, container, false);
        String[] items = getResources().getStringArray(R.array.currencies);
        String[] currencyBases = getResources().getStringArray(R.array.currency_bases);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), R.layout.spinner_currency_style, items);
        adapter.setDropDownViewResource(R.layout.spinner_currency_style_item);

        Spinner spinner = view.findViewById(R.id.cur_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                TextView txtSum = getView().findViewById(R.id.lbl_sum);
                double total = Double.valueOf((String)txtSum.getText());
                updateCurrency(i, total);
                Spinner spinner = getView().findViewById(R.id.cur_spinner);
                String call = (String)spinner.getSelectedItem();

                Double amount = baseConversion(i);
                callback.onCurrencySelected(" " + amount + " " + call);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Not implemented
            }
        });


        Button btnCalc = view.findViewById(R.id.btn_calc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Spinner spinner = getView().findViewById(R.id.cur_spinner);
                String call = (String)spinner.getSelectedItem();
                int i = spinner.getSelectedItemPosition();
                updateSum(i);
                String amount = String.valueOf(baseConversion(i));
                callback.onCurrencySelected(" " + amount + call);
            }
        });

        return view;
    }

    private void updateCurrency(int pos, double total) {
        View view = getView();
        TextView txtSum = view.findViewById(R.id.lbl_sum);
        TextView txtCurrency = view.findViewById(R.id.lbl_currency);

        String[] currency = getResources().getStringArray(R.array.currencies);
        String[] currencyBases = getResources().getStringArray(R.array.currency_bases);

        if(!currency[pos].equals("NOK")) {
            inNok = total / previousBase;
        }

        double sum = inNok * Double.valueOf(currencyBases[pos]);
        String sumFormatted = String.format(Locale.ENGLISH, "%.2f", sum);
        txtSum.setText(sumFormatted);
        //txtCurrency.setText(currency[pos]);
        previousBase = Double.valueOf(currencyBases[pos]);
    }

    private void updateSum(int i) {
        View view = getView();
        EditText numDays = view.findViewById(R.id.antall_dager);
        EditText ticket = view.findViewById(R.id.place_ticket_cost);
        EditText exp = view.findViewById(R.id.avg_expenditure);

        String strNumDays = String.valueOf(numDays.getText());
        String strTicket = String.valueOf(ticket.getText());
        String strExp = String.valueOf(exp.getText());

        Double sum = (Double.valueOf(strNumDays) * Double.valueOf(strExp))
                + Double.valueOf(strTicket);

        Double base = Double.valueOf(getResources().getStringArray(R.array.currency_bases)[i]);
        Double sumConverted = sum * base;
        TextView txtSum = view.findViewById(R.id.lbl_sum);
        String sumConvertedString = String.format(Locale.ENGLISH,"%.2f", sumConverted);

        txtSum.setText(sumConvertedString);
    }

    private Double baseConversion(int i) {
        String[] bases = getResources().getStringArray(R.array.currency_bases);
        return 100 * Double.valueOf(bases[i]);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnUpdateCurrency)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }

    }

    public interface OnUpdateCurrency {

        void onCurrencySelected(String i);
    }
}
