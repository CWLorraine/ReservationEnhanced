package sg.edu.rp.c346.reservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    EditText txtName;
    EditText txtMN;
    EditText txtPax;
    CheckBox cbSmokeArea;
    Button btnReserve;
    Button btnReset;
    EditText etDay;
    EditText etTime;
    


    @Override
    protected void onPause() {
        super.onPause();

        String name = txtName.getText().toString();
        String telephone = txtMN.getText().toString();
        String size = txtPax.getText().toString();
        String day = etDay.getText().toString();
        String time = etTime.getText().toString();
        boolean chbox = cbSmokeArea.isChecked();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1c:
        prefEdit.putString("Name", name);
        prefEdit.putString("telephone", telephone);
        prefEdit.putString("size", size);
        prefEdit.putString("day", day);
        prefEdit.putString("time", time);
        prefEdit.putBoolean("chbox", chbox);


        prefEdit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String name = prefs.getString("Name","");
        String telephone = prefs.getString("telephone","");
        String size = prefs.getString("size","");
        String day = prefs.getString("day","");
        String time = prefs.getString("time","");
        boolean chbox = prefs.getBoolean("chbox",false);

        txtName.setText(name);
        txtMN.setText(telephone);
        txtPax.setText(size);
        etDay.setText(day);
        etTime.setText(time);
        cbSmokeArea.setChecked(chbox);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtMN = findViewById(R.id.txtMobileNo);
        txtPax = findViewById(R.id.txtNoPax);
        cbSmokeArea = findViewById(R.id.smokeArea);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);
        etTime =findViewById(R.id.editTextTime);
        etDay =findViewById(R.id.editTextday);

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int days = now.get(Calendar.DAY_OF_MONTH);

        etDay.setText("Date: " +days+"/"+(month+1)+"/"+year);

        Calendar nowT = Calendar.getInstance();

        int hour = nowT.get(Calendar.HOUR_OF_DAY);
        int minute = nowT.get(Calendar.MINUTE);
        etTime.setText("Time: " +String.format("%02d",hour)+":"+String.format("%02d",minute));


        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int day) {
                        etDay.setText("Date: "+day+"/"+(monthOfYear+1)+"/"+year);
                    }
                };

                Calendar now = Calendar.getInstance();
                int year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH);
                int days = now.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, year, month, days);

                myDateDialog.show();
            }
        });


        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        etTime.setText("Time: "+String.format("%02d",hourOfDay) + ":"+String.format("%02d",minute));
                    }
                };
                Calendar now = Calendar.getInstance();

                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, hour,minute,true);

                myTimeDialog.show();
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = txtName.getText().toString();
                String MobileNum = txtMN.getText().toString();
                String pax = txtPax.getText().toString();
                String date = etDay.getText().toString();
                String time = etTime.getText().toString();


                String smokeArea ;

                if (cbSmokeArea.isChecked()){
                    smokeArea = "Yes";

                }

                else{
                    smokeArea = "No";

                }


                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Confirm Your Order");
                myBuilder.setMessage("New Reservation"+"\n"+"Name: "+name+"\n"+"Smoking: "+smokeArea+"\n"+"Size: "+ pax+"\n"+date+"\n"+time);
                myBuilder.setNeutralButton("CANCEL",null);
                myBuilder.setPositiveButton("CONFIRM",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }


        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtName.setText(null);
                txtMN.setText(null);
                txtPax.setText(null);

                Calendar now = Calendar.getInstance();
                int year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH);
                int days = now.get(Calendar.DAY_OF_MONTH);

                etDay.setText("Date: " +days+"/"+(month+1)+"/"+year);

                Calendar nowT = Calendar.getInstance();

                int hour = nowT.get(Calendar.HOUR_OF_DAY);
                int minute = nowT.get(Calendar.MINUTE);
                etTime.setText("Time: " +String.format("%02d",hour)+":"+String.format("%02d",minute));

            }
        });


    }
}
