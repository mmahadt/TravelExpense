package com.example.travelexpenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
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
import com.takisoft.preferencex.PreferenceFragmentCompat;


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

        final TextView tv_net_res = view.findViewById(R.id.tv_net_cost_per_person_val);

        final TextView tv_per_person_cost = view.findViewById(R.id.tv_cost_per_person_val);

        final TextView tv_wear_per_person = view.findViewById(R.id.tv_wear_per_person_val);

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
                double finalValue = Math.ceil( result );
                String result_string = String.format("%.0f", finalValue);
                tv_net_res.setText(result_string);
                tv_per_person_cost.setText(String.format("%.1f", costPerPerson ));
                tv_wear_per_person.setText(String.format("%.1f", wearPerPerson ));

            }
        });
    }
}

