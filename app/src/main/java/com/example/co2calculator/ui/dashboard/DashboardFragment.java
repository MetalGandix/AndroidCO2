package com.example.co2calculator.ui.dashboard;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.co2calculator.R;

public class DashboardFragment extends Fragment {

    private TextView co2TextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        co2TextView = root.findViewById(R.id.text_dashboard);
        return root;
    }

    // Metodo per aggiornare il consumo di CO₂
    public void updateCo2Data(double co2) {
        if (co2TextView != null) {
            co2TextView.setText("Consumo di CO₂: " + co2 + " g");
        }
    }
}
