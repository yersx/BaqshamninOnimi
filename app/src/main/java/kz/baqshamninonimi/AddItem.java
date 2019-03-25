package kz.baqshamninonimi;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity {

    private EditText mGroupName;
    private EditText mGatherPoint;
    private EditText mStartAge;
    private EditText mEndAge;
    private CheckBox mStayStatus;
    private TextView mGatherDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FloatingActionButton createButton;
    private  Calendar cal;
    private   String stay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mGroupName= findViewById(R.id.group_name);
        mGatherPoint= findViewById(R.id.GatherPoint);
        mGatherDate= findViewById(R.id.gatherdate);
        mStayStatus=findViewById(R.id.StayStatus);
        mStartAge=findViewById(R.id.vozrast1);
         cal = Calendar.getInstance();
        mGatherDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddItem.this, mDateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                String date = day + "-" + month + "-" + year;
                mGatherDate.setText(date);
            }
        };
        initUi();
    }


    private void initUi() {
          stay= "false";
        createButton=findViewById(R.id.addnewgroup);
       createButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               if(mStayStatus.isChecked()){
                    stay="true";
                }
                Toast.makeText(AddItem.this, "Продукт добавлено в лист...", Toast.LENGTH_LONG).show();
       //         Room room = new Room(mGatherPoint.getText().toString(),mStartAge.getText().toString(),mEndAge.getText().toString(),stay,mGatherDate.getText().toString(),mGatherTime.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getUid());
        //        FirebaseDatabase.getInstance().getReference("Groups").child(Place.placeName+"/" + mGroupName.getText().toString()).setValue(room).addOnCompleteListener(new OnCompleteListener<Void>() {
            //        @Override
             //       public void onComplete(@NonNull Task<Void> task) {
//
                    }
                });
     //       }
   //     });
    }



}
