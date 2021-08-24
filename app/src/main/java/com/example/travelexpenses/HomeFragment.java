package com.example.travelexpenses;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);
        return viewGroup;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final EditText et_car_avg = view.findViewById(R.id.et_car_avg);

        final EditText et_days = view.findViewById(R.id.et_days);

        final EditText et_distance_kms = view.findViewById(R.id.et_distance_kms);

        final EditText et_num_of_people = view.findViewById(R.id.et_num_of_people);

        final EditText et_petrol_per_ltr = view.findViewById(R.id.et_petrol_per_ltr);

        final TextView tv_res = view.findViewById(R.id.tv_res);

        final Button bt_calculate = view.findViewById(R.id.bt_calculate);

        final SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());

        bt_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carAvg = et_car_avg.getText().toString();
                String days = et_days.getText().toString();
                String distance = et_distance_kms.getText().toString();
                String noOfPeople = et_num_of_people.getText().toString();
                String petrolPerLitre = et_petrol_per_ltr.getText().toString();

//                if (kgs.length() == 0 || "".equals(kgs)) {
//                    Toast.makeText(getActivity(), "Please enter some value in kgs field.", Toast.LENGTH_SHORT).show();
//                } else if (ft.length() == 0 || "".equals(ft)) {
//                    Toast.makeText(getActivity(), "Please enter some value in feet field.", Toast.LENGTH_SHORT).show();
//                } else if (inches.length() == 0 || "".equals(inches)) {
//                    Toast.makeText(getActivity(), "Please enter some value in inches field.", Toast.LENGTH_SHORT).show();
//                } else {

                double carAverage = Double.parseDouble(carAvg);
                double numOfDays = Double.parseDouble(days);
                double dist = Double.parseDouble(distance);
                double numOfPeople = Double.parseDouble(noOfPeople);
                double petrolPerLtr = Double.parseDouble(petrolPerLitre);

                String tire_usage_life = sharedPreferences.getString("tire_usage_life", "0");
                String cost_tires_set = sharedPreferences.getString("cost_tires_set", "0");
                String mobil_oil_usage_life = sharedPreferences.getString("mobil_oil_usage_life", "0");
                String mobil_oil_cost = sharedPreferences.getString("mobil_oil_cost", "0");
                String battery_usage_life = sharedPreferences.getString("battery_usage_life", "0");
                String battery_cost = sharedPreferences.getString("battery_cost", "0");
                String suspension_usage_life = sharedPreferences.getString("suspension_usage_life", "0");
                String suspension_cost = sharedPreferences.getString("suspension_cost", "0");

                double tire_usage_life_val = Double.parseDouble("tire_usage_life");
                double cost_tires_set_val = Double.parseDouble("cost_tires_set");
                double mobil_oil_usage_life_val = Double.parseDouble("mobil_oil_usage_life");
                double mobil_oil_cost_val = Double.parseDouble("mobil_oil_cost");
                double battery_usage_life_val = Double.parseDouble("battery_usage_life");
                double battery_cost_val = Double.parseDouble("battery_cost");
                double suspension_usage_life_val = Double.parseDouble("suspension_usage_life");
                double suspension_cost_val = Double.parseDouble("suspension_cost");

                double tirePerKmCost = cost_tires_set_val / tire_usage_life_val;
                double mobilOilPerKmCost = mobil_oil_cost_val / mobil_oil_usage_life_val;
                double batteryPerDayCost = battery_cost_val / (battery_usage_life_val * 365);
                double suspensionPerDayCost = suspension_cost_val / (suspension_usage_life_val * 365);

                double tireWear = tirePerKmCost * dist;
                double batteryWear = batteryPerDayCost * numOfDays;
                double suspensionWear = suspensionPerDayCost * numOfDays;
                double mobileOilUse = mobilOilPerKmCost * dist;

//                double meters = (feet * 0.3048) + (inch * 0.0254);
//                double kg = Double.parseDouble(kgs);
//                double bmi = kg / (meters * meters);
//                String bmi_string = String.format("%.1f", bmi);
//
//                if (bmi < 18.5) {
//                    tv_res.setText("BMI = " + bmi_string + " Underweight");
//                } else if (bmi < 24.9) {
//                    tv_res.setText("BMI = " + bmi_string + " Normal");
//                } else if (bmi < 29.9) {
//                    tv_res.setText("BMI = " + bmi_string + " Overweight");
//                } else {
//                    tv_res.setText("BMI = " + bmi_string + " Obese");
//                }

//                }
            }
        });
    }
}

