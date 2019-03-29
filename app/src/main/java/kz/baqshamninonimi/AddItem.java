package kz.baqshamninonimi;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import kz.baqshamninonimi.Adapter.AutoCompleteTypeAdapter;

public class AddItem extends AppCompatActivity {

    private EditText mProdName, mGatherPoint, mStartAge, mEndAge;
    private CheckBox mStayStatus;
    private ImageView mImgUpload;
    private TextView mBtnChoose, mGatherDate;
    private Button mBtnUpload;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FloatingActionButton createButton;
    private List<TypeItem> typeList;
    private  Calendar cal;
    private Bitmap bitmap;
    private final int CODE_GALLERY_REQUEST = 999;
    private   String stay,urlUpload = "http://f0256942.xsph.ru/uploadImg.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup);
        fillTypeList();

        AutoCompleteTextView editText = findViewById(R.id.actv);
        AutoCompleteTypeAdapter adapter = new AutoCompleteTypeAdapter(this, typeList);
        editText.setAdapter(adapter);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProdName = findViewById(R.id.product_name);
        mGatherPoint = findViewById(R.id.GatherPoint);
        mGatherDate = findViewById(R.id.gatherdate);
        mStayStatus = findViewById(R.id.StayStatus);
        mStartAge = findViewById(R.id.vozrast1);
        mImgUpload = findViewById(R.id.imageUpload);
        mBtnChoose = findViewById(R.id.btnChoose);
        mBtnUpload = findViewById(R.id.btnUpload);

        mBtnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "show",Toast.LENGTH_LONG);
                ActivityCompat.requestPermissions(AddItem.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_GALLERY_REQUEST);
            }
        });

        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "show",Toast.LENGTH_LONG);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpload, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "answer "+ response, Toast.LENGTH_LONG);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ошибка"+error.toString(), Toast.LENGTH_LONG);
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new  HashMap<>();
                        String imageData = null;
                        try {
                            imageData = imageToString(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        params.put("image", imageData);

                        return params;
                    }
                };
                RequestQueue mRequestQueue= Volley.newRequestQueue(AddItem.this);
                mRequestQueue.add(stringRequest);
            }
        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==CODE_GALLERY_REQUEST){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "выберите фото"), CODE_GALLERY_REQUEST);

            } else
                Toast.makeText(getApplicationContext(),"У вас нету разрешении" , Toast.LENGTH_LONG).show();
            return;
        }
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CODE_GALLERY_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                     bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                mImgUpload.setImageBitmap(bitmap);
                mBtnUpload.setText("Загрузить");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initUi() {
          stay= "false";
       createButton=findViewById(R.id.addproduct);
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

    private void fillTypeList(){
        typeList = new ArrayList<>();
        typeList.add(new TypeItem("Лук- овощи"));
        typeList.add(new TypeItem("Помидор - овощи"));
        typeList.add(new TypeItem("Картошка - овощи"));
        typeList.add(new TypeItem("Морковь - овощи"));
        typeList.add(new TypeItem("Яблоко - фрукты"));
    }



    private String imageToString(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream );
         byte[] imageBytes = outputStream.toByteArray();
         String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
         outputStream.close();
         return  encodedImage;
    }
}
