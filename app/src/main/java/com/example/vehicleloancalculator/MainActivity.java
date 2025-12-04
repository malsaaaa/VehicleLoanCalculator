package com.example.vehicleloancalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private EditText etVehiclePrice, etDownPayment, etLoanPeriod, etInterestRate;
    private TextView tvLoanAmount, tvTotalInterest, tvTotalPayment, tvMonthlyPayment;
    private Button btnCalculate; // Removed btnAbout
    private BottomNavigationView bottomNavigationView; // New: Declare BottomNavigationView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views
        etVehiclePrice = findViewById(R.id.etVehiclePrice);
        etDownPayment  = findViewById(R.id.etDownPayment);
        etLoanPeriod   = findViewById(R.id.etLoanPeriod);
        etInterestRate = findViewById(R.id.etInterestRate);

        tvLoanAmount     = findViewById(R.id.tvLoanAmount);
        tvTotalInterest  = findViewById(R.id.tvTotalInterest);
        tvTotalPayment   = findViewById(R.id.tvTotalPayment);
        tvMonthlyPayment = findViewById(R.id.tvMonthlyPayment);

        btnCalculate = findViewById(R.id.btnCalculate);

        // New: Bind BottomNavigationView using the ID from the XML layout
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Calculate button logic
        btnCalculate.setOnClickListener(v -> calculateLoan());

        // Set the default selected item to 'Home' when the activity starts
        bottomNavigationView.setSelectedItemId(R.id.menu_home);

        // New: Handle Bottom Navigation Item Selection
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {
                    // Already on the main calculator screen
                    return true;
                } else if (itemId == R.id.menu_about) {
                    // Open AboutActivity
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }
    // This ensures that when the user returns from AboutActivity (via the Back button),
    // the 'Home' tab is visibly selected on the BottomNavigationView.
    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        }
    }

    private void calculateLoan() {
        try {
            double vehiclePrice = Double.parseDouble(etVehiclePrice.getText().toString());
            double downPayment  = Double.parseDouble(etDownPayment.getText().toString());
            int loanPeriodYears = Integer.parseInt(etLoanPeriod.getText().toString());
            double interestRate = Double.parseDouble(etInterestRate.getText().toString());

            double loanAmount    = vehiclePrice - downPayment;
            double totalInterest = loanAmount * (interestRate / 100.0) * loanPeriodYears;
            double totalPayment  = loanAmount + totalInterest;
            double monthlyPayment = totalPayment / (loanPeriodYears * 12.0);

            tvLoanAmount.setText(String.format("Loan Amount: RM %.2f", loanAmount));
            tvTotalInterest.setText(String.format("Total Interest: RM %.2f", totalInterest));
            tvTotalPayment.setText(String.format("Total Payment: RM %.2f", totalPayment));
            tvMonthlyPayment.setText(String.format("Monthly Payment: RM %.2f", monthlyPayment));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
        }
    }
}