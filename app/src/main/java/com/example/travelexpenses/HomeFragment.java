package com.example.travelexpenses;

//import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
//import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
//import com.takisoft.preferencex.PreferenceFragmentCompat;


public class HomeFragment extends Fragment {

    AutoCompleteTextView et_car_avg;

    EditText et_days;

    EditText et_distance_kms;

    EditText et_num_of_people;

    EditText et_petrol_per_ltr;

    TextView tv_net_res;

    TextView tv_per_person_cost;

    TextView tv_wear_per_person;

    Button bt_calculate;

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);
        return viewGroup;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        et_car_avg = view.findViewById(R.id.et_car_avg);

        et_days = view.findViewById(R.id.et_days);

        et_distance_kms = view.findViewById(R.id.et_distance_kms);

        et_num_of_people = view.findViewById(R.id.et_num_of_people);

        et_petrol_per_ltr = view.findViewById(R.id.et_petrol_per_ltr);

        tv_net_res = view.findViewById(R.id.tv_net_cost_per_person_val);

        tv_per_person_cost = view.findViewById(R.id.tv_cost_per_person_val);

        tv_wear_per_person = view.findViewById(R.id.tv_wear_per_person_val);

//        final Button bt_calculate = view.findViewById(R.id.bt_calculate);

        sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());

        et_car_avg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                calculate();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_days.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                calculate();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_petrol_per_ltr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                calculate();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_distance_kms.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                calculate();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_num_of_people.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                calculate();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



//        bt_calculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });


    }
    public void calculate (){

        String carAvg = et_car_avg.getText().toString();
        String days = et_days.getText().toString();
        String distance = et_distance_kms.getText().toString();
        String noOfPeople = et_num_of_people.getText().toString();
        String petrolPerLitre = et_petrol_per_ltr.getText().toString();

        if (!carAvg.matches("")&&!days.matches("")&&!distance.matches("")&&!noOfPeople.matches("")&&!petrolPerLitre.matches("")) {


            double carAverage = Double.parseDouble(carAvg);
            double numOfDays = Double.parseDouble(days);
            double dist = Double.parseDouble(distance);
            double numOfPeople = Double.parseDouble(noOfPeople);
            double petrolPerLtr = Double.parseDouble(petrolPerLitre);

            String tire_usage_life = sharedPreferences.getString("tire_usage_life", "70000");
            String cost_tires_set = sharedPreferences.getString("cost_tires_set", "25000");
            String mobil_oil_usage_life = sharedPreferences.getString("mobil_oil_usage_life", "3000");
            String mobil_oil_cost = sharedPreferences.getString("mobil_oil_cost", "2000");
            String battery_usage_life = sharedPreferences.getString("battery_usage_life", "3");
            String battery_cost = sharedPreferences.getString("battery_cost", "4000");
            String suspension_usage_life = sharedPreferences.getString("suspension_usage_life", "3");
            String suspension_cost = sharedPreferences.getString("suspension_cost", "25000");

            double tire_usage_life_val = Double.parseDouble(tire_usage_life);
            double cost_tires_set_val = Double.parseDouble(cost_tires_set);
            double mobil_oil_usage_life_val = Double.parseDouble(mobil_oil_usage_life);
            double mobil_oil_cost_val = Double.parseDouble(mobil_oil_cost);
            double battery_usage_life_val = Double.parseDouble(battery_usage_life);
            double battery_cost_val = Double.parseDouble(battery_cost);
            double suspension_usage_life_val = Double.parseDouble(suspension_usage_life);
            double suspension_cost_val = Double.parseDouble(suspension_cost);

            double tirePerKmCost = cost_tires_set_val / tire_usage_life_val;
            double mobilOilPerKmCost = mobil_oil_cost_val / mobil_oil_usage_life_val;
            double batteryPerDayCost = battery_cost_val / (battery_usage_life_val * 365);
            double suspensionPerDayCost = suspension_cost_val / (suspension_usage_life_val * 365);

            double tireWear = tirePerKmCost * dist;
            double batteryWear = batteryPerDayCost * numOfDays;
            double suspensionWear = suspensionPerDayCost * numOfDays;
            double mobileOilUse = mobilOilPerKmCost * dist;

            double costPerPerson = ((dist / carAverage) * petrolPerLtr) / numOfPeople;
            double wearPerPerson = (tireWear + batteryWear + suspensionWear + mobileOilUse) / numOfPeople;

            double result = costPerPerson + wearPerPerson;
            double finalValue = Math.ceil(result);
            String result_string = String.format("%.0f", finalValue);
            tv_net_res.setText(result_string);
            if (finalValue > 999) {
                tv_net_res.setTextColor(Color.parseColor("#cf0000"));
            }
            else
            {
                tv_net_res.setTextColor(Color.parseColor("#808080"));
            }
            tv_per_person_cost.setText(String.format("%.1f", costPerPerson));
            tv_wear_per_person.setText(String.format("%.1f", wearPerPerson));

        }
        else{
            tv_net_res.setText("0");
            tv_per_person_cost.setText("0");
            tv_wear_per_person.setText("0");
        }
    }
}