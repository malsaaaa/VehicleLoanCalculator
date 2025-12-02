# Vehicle Loan Calculator

ICT602 – Individual Assignment (20%)  
Author: Syed Danish Aslam Bin Syed Mohd Mahzan (Matric No: 2023226002)

---

## 📱 Overview

Vehicle Loan Calculator is a simple Android application that helps users estimate their car loan details.  
By entering the vehicle price, down payment, loan period, and interest rate, the app will automatically compute:

- Loan Amount  
- Total Interest  
- Total Payment  
- Monthly Payment  

This project is developed as part of the ICT602 course individual assignment.

---

## ✨ Features

- **Input fields**:
  - Vehicle Price (RM)
  - Down Payment (RM)
  - Loan Period (Years)
  - Interest Rate (% per year)

- **Automatic calculations**:
  - **Loan Amount** = Vehicle Price – Down Payment  
  - **Total Interest** = Loan Amount × (Interest Rate / 100) × Loan Period  
  - **Total Payment** = Loan Amount + Total Interest  
  - **Monthly Payment** = Total Payment ÷ (Loan Period × 12)

- **About Page**:
  - App icon
  - Author information (name, matric number, course)
  - Copyright notice
  - Clickable GitHub repository URL

- Simple navigation:
  - **Home screen** → Loan calculator  
  - **About button** → Opens About page  

---

## 🧮 Calculation Details

Given:

- `P` = Vehicle Price  
- `D` = Down Payment  
- `Y` = Loan Period in years  
- `R` = Interest Rate per year (%)  

The app uses:

1. **Loan Amount**  
   `Loan Amount = P - D`

2. **Total Interest**  
   `Total Interest = Loan Amount × (R / 100) × Y`

3. **Total Payment**  
   `Total Payment = Loan Amount + Total Interest`

4. **Monthly Payment**  
   `Monthly Payment = Total Payment ÷ (Y × 12)`

All monetary values are displayed in **RM** with two decimal places.

---

## 🏗 Tech Stack

- **Platform**: Android  
- **Language**: Java  
- **Minimum SDK**: API 21 or higher
- **IDE**: Android Studio  

---

## 📂 Project Structure (Main Parts)

- `MainActivity.java`  
  - Handles user input
  - Performs all loan calculations
  - Displays results
  - Handles navigation to About page

- `AboutActivity.java`  
  - Displays app icon
  - Shows author details and course
  - Shows GitHub URL (clickable)
  - Provides Back navigation

- `res/layout/activity_main.xml`  
  - UI for the loan calculator (Home)

- `res/layout/activity_about.xml`  
  - UI for About page

- `AndroidManifest.xml`  
  - Declares both activities  
  - Sets `MainActivity` as launcher activity

---

## 🚀 How to Run the App

1. Clone or download this repository:
   ```bash
   git clone https://github.com/yourusername/VehicleLoanCalculator.git

## 👤 Author

Name: Syed Danish Aslam bin Syed Mohd Mahzan

Matric No: 2023226002

Course: ICT602

GitHub: https://github.com/malsaaaa/VehicleLoanCalculator

📜 Copyright

© 2025 Syed Danish Aslam. All rights reserved.
This project is created for educational purposes as part of ICT602 Individual Assignment.
