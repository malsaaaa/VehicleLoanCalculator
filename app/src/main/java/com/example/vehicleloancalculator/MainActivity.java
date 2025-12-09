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

/**
 * MainActivity
 * This class handles the main logic for the Vehicle Loan Calculator app.
 * It manages user input, performs the financial calculations, handles errors,
 * and manages navigation to the About screen.
 */
public class MainActivity extends AppCompatActivity {

    // UI Components: Input fields
    private EditText etVehiclePrice, etDownPayment, etLoanPeriod, etInterestRate;

    // UI Components: Output displays
    private TextView tvLoanAmount, tvTotalInterest, tvTotalPayment, tvMonthlyPayment;

    // UI Components: Action triggers and navigation
    private Button btnCalculate;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Link this Java class to the XML layout file (activity_main.xml)
        setContentView(R.layout.activity_main);

        // -----------------------------------------------------------
        // 1. View Binding
        // -----------------------------------------------------------
        // Initialize Input fields
        etVehiclePrice = findViewById(R.id.etVehiclePrice);
        etDownPayment  = findViewById(R.id.etDownPayment);
        etLoanPeriod   = findViewById(R.id.etLoanPeriod);
        etInterestRate = findViewById(R.id.etInterestRate);

        // Initialize Output fields
        tvLoanAmount     = findViewById(R.id.tvLoanAmount);
        tvTotalInterest  = findViewById(R.id.tvTotalInterest);
        tvTotalPayment   = findViewById(R.id.tvTotalPayment);
        tvMonthlyPayment = findViewById(R.id.tvMonthlyPayment);

        // Initialize Buttons and Navigation
        btnCalculate = findViewById(R.id.btnCalculate);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // -----------------------------------------------------------
        // 2. Event Listeners
        // -----------------------------------------------------------

        // specific listener for the "Calculate" button
        // Uses a lambda expression to call the calculateLoan() method
        btnCalculate.setOnClickListener(v -> calculateLoan());

        // Set the default selected item to 'Home' to ensure the UI matches the current activity
        bottomNavigationView.setSelectedItemId(R.id.menu_home);

        // Listener for Bottom Navigation Item Selection
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_home) {
                    // Current Activity: Do nothing or refresh if needed
                    return true;
                } else if (itemId == R.id.menu_about) {
                    // Create an Intent to switch from MainActivity to AboutActivity
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                    return true; // Return true to indicate the event was handled
                }
                return false;
            }
        });
    }

    /**
     * onResume
     * Called when the activity will start interacting with the user.
     * We use this to ensure the Bottom Navigation bar highlights "Home"
     * when the user returns from the About screen (e.g., via the Back button).
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        }
    }

    /**
     * calculateLoan
     * Retrieves user input, converts strings to numbers, performs the loan math,
     * and updates the TextViews. Handles empty inputs via try-catch.
     */
    private void calculateLoan() {
        // 1. Get raw strings from input fields
        String strPrice = etVehiclePrice.getText().toString();
        String strDownPayment = etDownPayment.getText().toString();
        String strPeriod = etLoanPeriod.getText().toString();
        String strRate = etInterestRate.getText().toString();


        // ERROR HANDLING 1: Check if input fields are empty
        if (strPrice.isEmpty() || strDownPayment.isEmpty() || strPeriod.isEmpty() || strRate.isEmpty()) {
            Toast.makeText(this, "Error: Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return; // Stop execution here
        }


        // ERROR HANDLING 2: Check if input is just a decimal point "."
        // This happens if a user types "." and nothing else, which crashes Double.parseDouble
        if (strPrice.equals(".") || strDownPayment.equals(".") || strPeriod.equals(".") || strRate.equals(".")) {
            Toast.makeText(this, "Error: Invalid input format (cannot be just '.').", Toast.LENGTH_SHORT).show();
            return; // Stop execution here
        }

        try {
            // Parse strings to numbers now that we know they aren't empty or just "."
            double vehiclePrice = Double.parseDouble(strPrice);
            double downPayment = Double.parseDouble(strDownPayment);
            int loanPeriodYears = Integer.parseInt(strPeriod); // Assuming years is an integer
            double interestRate = Double.parseDouble(strRate);

            // ERROR HANDLING 3: Down Payment cannot be higher than Vehicle Price

            if (downPayment > vehiclePrice) {
                Toast.makeText(this, "Error: Down payment cannot be more than vehicle price.", Toast.LENGTH_SHORT).show();
                return; // Stop execution here
            }

            // ERROR HANDLING 4: Loan Period cannot be more than 30 years
            if( loanPeriodYears > 30){
                Toast.makeText(this, "Error: Loan period cannot be more than 30 years.", Toast.LENGTH_SHORT).show();
                return; // Stop execution here
            }

            // ERROR HANDLING 5: Interest Rate cannot be more than 100%
            if( interestRate > 100){
                Toast.makeText(this, "Error: Interest rate cannot be more than 100%.", Toast.LENGTH_SHORT).show();
                return; // Stop execution here
            }

            // If all checks pass, proceed with calculation
            double loanAmount    = vehiclePrice - downPayment;
            double totalInterest = loanAmount * (interestRate / 100.0) * loanPeriodYears;
            double totalPayment  = loanAmount + totalInterest;
            double monthlyPayment = totalPayment / (loanPeriodYears * 12.0);

            // Display Results
            tvLoanAmount.setText(String.format("Loan Amount: RM %.2f", loanAmount));
            tvTotalInterest.setText(String.format("Total Interest: RM %.2f", totalInterest));
            tvTotalPayment.setText(String.format("Total Payment: RM %.2f", totalPayment));
            tvMonthlyPayment.setText(String.format("Monthly Payment: RM %.2f", monthlyPayment));

        } catch (NumberFormatException e) {
            // Fallback for any other weird formatting issues
            Toast.makeText(this, "Error: Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        }
    }
}