package no.erlendhall.oblig1;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView txt_by;
    Button btnNext;
    String currencyCode;
    private DrawerLayout dlCountries;
    private ListView lstCountries;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = new Intent(this, CalcReiseActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();

        btnNext = findViewById(R.id.til_calc);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("c", currencyCode);
                startActivity(intent);

            }
        });


    }

    /*
    * When spinner-item is selected, the layout gets updated
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        updateLayout(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
    }

    protected void initDrawer() {
        /*dlCountries = findViewById(R.id.drawer_layout);
        String[] countries_menu = getResources().getStringArray(R.array.countries);
        lstCountries = findViewById(R.id.list);*/

    }


    protected void updateLayout(int index) {
        Resources res = getResources();
        String[] currencyCodes = res.getStringArray(R.array.currencies);
        currencyCode = currencyCodes[index];

        //Landnavn
        String[] countries = res.getStringArray(R.array.countries);
        TextView txt_land = findViewById(R.id.lbl_countries_name);
        txt_land.setText(countries[index]);

        // Hotell
        String[] hotels = res.getStringArray(R.array.hotels);
        TextView txt_hotel = findViewById(R.id.txt_hotel);
        txt_hotel.setText(hotels[index]);

        //By
        String[] byer = res.getStringArray(R.array.cities);
        txt_by = findViewById(R.id.txt_by);
        txt_by.setText(byer[index]);

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
        }

        //


    }
}
