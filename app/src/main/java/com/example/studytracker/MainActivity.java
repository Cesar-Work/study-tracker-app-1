package com.example.studytracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CheckBox sleepCheckbox, nutritionCheckbox, exerciseCheckbox, hydrationCheckbox, mindfulnessCheckbox, journalingCheckbox;
    private EditText sleepHoursInput, hydrationLitersInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        sleepCheckbox = findViewById(R.id.checkbox_sleep);
        nutritionCheckbox = findViewById(R.id.checkbox_nutrition);
        exerciseCheckbox = findViewById(R.id.checkbox_exercise);
        hydrationCheckbox = findViewById(R.id.checkbox_hydration);
        mindfulnessCheckbox = findViewById(R.id.checkbox_mindfulness);
        journalingCheckbox = findViewById(R.id.checkbox_journaling);

        sleepHoursInput = findViewById(R.id.input_sleep_hours);
        hydrationLitersInput = findViewById(R.id.input_hydration_liters);
        saveButton = findViewById(R.id.button_save);

        // Load previously saved values
        loadData();

        saveButton.setOnClickListener(v -> saveData());
    }

    private void saveData() {
        // Retrieve values from UI
        boolean sleepDone = sleepCheckbox.isChecked();
        boolean nutritionDone = nutritionCheckbox.isChecked();
        boolean exerciseDone = exerciseCheckbox.isChecked();
        boolean hydrationDone = hydrationCheckbox.isChecked();
        boolean mindfulnessDone = mindfulnessCheckbox.isChecked();
        boolean journalingDone = journalingCheckbox.isChecked();

        String sleepHours = sleepHoursInput.getText().toString();
        String hydrationLiters = hydrationLitersInput.getText().toString();

        SharedPreferences prefs = getSharedPreferences("daily_tracker", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("sleep_done", sleepDone);
        editor.putBoolean("nutrition_done", nutritionDone);
        editor.putBoolean("exercise_done", exerciseDone);
        editor.putBoolean("hydration_done", hydrationDone);
        editor.putBoolean("mindfulness_done", mindfulnessDone);
        editor.putBoolean("journaling_done", journalingDone);
        editor.putString("sleep_hours", sleepHours);
        editor.putString("hydration_liters", hydrationLiters);
        editor.apply();

        Toast.makeText(this, "Données enregistrées", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        SharedPreferences prefs = getSharedPreferences("daily_tracker", MODE_PRIVATE);
        sleepCheckbox.setChecked(prefs.getBoolean("sleep_done", false));
        nutritionCheckbox.setChecked(prefs.getBoolean("nutrition_done", false));
        exerciseCheckbox.setChecked(prefs.getBoolean("exercise_done", false));
        hydrationCheckbox.setChecked(prefs.getBoolean("hydration_done", false));
        mindfulnessCheckbox.setChecked(prefs.getBoolean("mindfulness_done", false));
        journalingCheckbox.setChecked(prefs.getBoolean("journaling_done", false));
        sleepHoursInput.setText(prefs.getString("sleep_hours", ""));
        hydrationLitersInput.setText(prefs.getString("hydration_liters", ""));
    }
}
