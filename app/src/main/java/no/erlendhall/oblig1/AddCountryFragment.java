package no.erlendhall.oblig1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.Nullable;


public class AddCountryFragment extends Fragment {
    OnNewCountryAdded callback;

    public AddCountryFragment newInstance() {
        return new AddCountryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @android.support.annotation.Nullable
            ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_country, container, false);

        Button btnAddCountry = view.findViewById(R.id.btn_add_country);
        btnAddCountry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText country = getView().findViewById(R.id.input_country);
                String countryS = String.valueOf(country.getText());

                EditText city = getView().findViewById(R.id.input_city);
                String cityS = String.valueOf(city.getText());

                EditText hotel = getView().findViewById(R.id.input_hotel);
                String hotelS = String.valueOf(hotel.getText());

                callback.onNewCountryButtonListener(countryS, cityS, hotelS);

                getView().setVisibility(View.GONE);


            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            callback = (OnNewCountryAdded)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }


    public interface OnNewCountryAdded {

        //Sends the information back to interfaced activity
        void onNewCountryButtonListener(String countryName, String cityName, String hotelName);
    }
}
