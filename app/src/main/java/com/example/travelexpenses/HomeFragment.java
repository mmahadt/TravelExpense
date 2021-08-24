package com.example.travelexpenses;

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

        final EditText et_kg = view.findViewById(R.id.et_kg);

        final EditText et_feet = view.findViewById(R.id.et_feet);

        final EditText et_inches = view.findViewById(R.id.et_inches);
        final TextView tv_res = view.findViewById(R.id.tv_res);

        final Button bt_convert = view.findViewById(R.id.bt_convert);

        bt_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kgs = et_kg.getText().toString();
                String ft = et_feet.getText().toString();
                String inches = et_inches.getText().toString();

                if (kgs.length() == 0 || "".equals(kgs)) {
                    Toast.makeText(getActivity(), "Please enter some value in kgs field.", Toast.LENGTH_SHORT).show();
                } else if (ft.length() == 0 || "".equals(ft)) {
                    Toast.makeText(getActivity(), "Please enter some value in feet field.", Toast.LENGTH_SHORT).show();
                } else if (inches.length() == 0 || "".equals(inches)) {
                    Toast.makeText(getActivity(), "Please enter some value in inches field.", Toast.LENGTH_SHORT).show();
                } else {

                    double feet = Double.parseDouble(ft);
                    double inch = Double.parseDouble(inches);
                    double meters = (feet * 0.3048) + (inch * 0.0254);
                    double kg = Double.parseDouble(kgs);
                    double bmi = kg / (meters * meters);
                    String bmi_string =String.format("%.1f", bmi);

                    if  (bmi<18.5){
                        tv_res.setText("BMI = " + bmi_string + " Underweight");
                    }else if(bmi<24.9){
                        tv_res.setText("BMI = " + bmi_string + " Normal");
                    }else if(bmi<29.9){
                        tv_res.setText("BMI = " + bmi_string + " Overweight");
                    }else {
                        tv_res.setText("BMI = " + bmi_string + " Obese");
                    }

                }
            }
        });
    }
}

