package com.example.vehicleloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etVehiclePrice, etDownPayment, etLoanPeriod, etInterestRate;
    private TextView tvLoanAmount, tvTotalInterest, tvTotalPayment, tvMonthlyPayment;
    private Button btnCalculate, btnAbout;

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
        btnAbout     = findViewById(R.id.btnAbout);

        // Calculate button logic
        btnCalculate.setOnClickListener(v -> calculateLoan());

        // About button -> open AboutActivity
        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
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
