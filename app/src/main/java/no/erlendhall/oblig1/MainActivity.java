package no.erlendhall.oblig1;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements AddCountryFragment.OnNewCountryAdded  {
    TextView txt_by;
    Button btnNext, btnAddCountry;
    private DrawerLayout dlCountries;
    private FragmentTransaction transaction;
    private ArrayList<String> countries, cities, hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = new Intent(this, CalcReiseActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countries = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.countries)));
        cities = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cities)));
        hotels = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.hotels)));
        initMenu();

        btnNext = findViewById(R.id.til_calc);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);

            }
        });
        btnAddCountry = findViewById(R.id.btn_goto_fragment);
        btnAddCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View hide = findViewById(R.id.main);
                hide.setVisibility(View.INVISIBLE);
                transaction = getFragmentManager().beginTransaction();
                AddCountryFragment frag = new AddCountryFragment();
                transaction.add(R.id.add_country_layout, frag);
                transaction.commit();
            }
        });


    }


    //Initializes a ListView
    protected void initMenu() {
        dlCountries = findViewById(R.id.drawer_layout);
        dlCountries.setScrimColor(Color.TRANSPARENT);
        String[] regCountries = countries.toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.listview_style, regCountries);

        ListView lstCountries = findViewById(R.id.nav_countries);
        lstCountries.setAdapter(adapter);
        lstCountries.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));


        lstCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateLayout(i);
                dlCountries.closeDrawer(Gravity.START);

            }
        });

    }

    //Adds a new country with info from fragment and updates ListView in Drawer
    protected void addNewCountry(String country, String city, String hotel) {
        countries.add(country);
        cities.add(city);
        hotels.add(hotel);
        initMenu();
    }

    //Is called whenever user clicks an item in ListView Drawer
    protected void updateLayout(int index) {

        //Landnavn
        String[] countriesS = countries.toArray(new String[0]);
        TextView txt_land = findViewById(R.id.lbl_countries_name);
        txt_land.setText(countriesS[index]);

        // Hotell
        String[] hotelsS = hotels.toArray(new String[0]);
        TextView txt_hotel = findViewById(R.id.txt_hotel);
        txt_hotel.setText(hotelsS[index]);

        //By
        String[] cititesS = cities.toArray(new String[0]);
        txt_by = findViewById(R.id.txt_by);
        txt_by.setText(cititesS[index]);

        //Flagg
        ImageView img = findViewById(R.id.flag);
        switch(index) {
            case 0:
                img.setImageResource(R.mipmap.peruvian_flag_foreground);
                break;
            case 1:
                img.setImageResource(R.mipmap.mexico_foreground);
                break;
            case 2:
                img.setImageResource(R.mipmap.russia_foreground);
                break;
            case 3:
                img.setImageResource(R.mipmap.netherlands_foreground);
                break;
            case 4:
                img.setImageResource(R.mipmap.australia_foreground);
                break;
            default:
                img.setImageResource(R.mipmap.flag_template_foreground);
        }

    }

    //Receives names of country, city and hotel from AddCountryFragment
    @Override
    public void onNewCountryButtonListener(String countryName, String cityName, String hotelName) {
        findViewById(R.id.main).setVisibility(View.VISIBLE);
        Toast.makeText(getBaseContext(), countryName + " was added.", Toast.LENGTH_LONG).show();
        addNewCountry(countryName, cityName, hotelName);
    }
}
