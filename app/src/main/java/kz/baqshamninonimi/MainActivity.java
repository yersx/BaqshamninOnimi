package kz.baqshamninonimi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.ExceptionHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import com.google.android.material.textfield.TextInputEditText;
import com.santalu.maskedittext.MaskEditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    private Spinner mSpinner;
    private
    LinearLayout llsignin,llsignup;

    public static String TYPE;
    private TextInputEditText mName;
    private TextInputEditText mPasswd;
    private TextInputEditText mRepPasswd;
    private MaskEditText mPhoneNumber;
    String phoneNumber , fullname, password, repPassword;

    private TextInputEditText mPhone;
    private TextInputEditText mPwd;

    String phoneStored = "", passwordStored = "";
    String choice=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llSignin =  findViewById(R.id.llSignin);
        llSignin.setOnClickListener(this);
        //LinearLayout singnin =(LinearLayout)findViewById(R.id.signin);
        llsignup =findViewById(R.id.llSignup);
        llsignup.setOnClickListener(this);
        tvSignupInvoker =  findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker =  findViewById(R.id.tvSigninInvoker);
        mSpinner= findViewById(R.id.usertype);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);


        llSignup =  findViewById(R.id.llSignup);
        llSignin =  findViewById(R.id.llSignin);

        btnSignup= findViewById(R.id.btnSignup);
        btnSignin= findViewById(R.id.btnSignin);
        mPhoneNumber = findViewById(R.id.signup_phone);
        mName   = findViewById(R.id.signup_name);
        mPasswd =findViewById(R.id.signup_passwd);
        mPhone = findViewById(R.id.signin_phone);
        mPwd = findViewById(R.id.signin_pwd);
        mRepPasswd = findViewById(R.id.repeat_passwd);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                phoneStored = pref.getString("phone", null);
                passwordStored = pref.getString("password", null);

                if (phoneStored == null) {

                    tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isSigninScreen = false;
                            showSignupForm();
                        }
                    });

                    tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isSigninScreen = true;
                            showSigninForm();
                        }
                    });
                    showSigninForm();

                    btnSignin.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            phoneNumber = mPhone.getText().toString();
                            password = mPwd.getText().toString();

                            if (phoneNumber.isEmpty() || password.isEmpty() ) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Введите все поля", Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                HashMap<String, String> loginData = new HashMap<>();
                                loginData.put("phone", phoneNumber);
                                loginData.put("password", password);

                                PostResponseAsyncTask loginTask = new PostResponseAsyncTask(view.getContext(), loginData, new AsyncResponse() {
                                    @Override
                                    public void processFinish(String s) {
                                        Log.d("response", s);
                                        if(s.contains("LoginSuccess") ){
                                            SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = pref.edit();
                                            editor.putString("phone", phoneNumber);
                                            editor.putString("password", password);
                                            String[] parts = s.split("#");
                                            String part1 = parts[1]; // 004
                                            String part2 = parts[2]; // 034556
                                            editor.putString("usertype", part1);
                                            editor.putString("full_name", part2);
                                            editor.commit();
                                            Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(in);
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "Что-то пошло не так.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                loginTask.setExceptionHandler(new ExceptionHandler() {
                                    @Override
                                    public void handleException(Exception e) {
                                        if(e != null && e.getMessage() != null){
                                            Log.d("msg: ", e.getMessage());
                                        }
                                    }
                                });
                                loginTask.execute("http://f0256942.xsph.ru/login.php");
                            }
                        }
                    });

                    btnSignup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            phoneNumber = mPhoneNumber.getText().toString();
                            password = mPasswd.getText().toString();
                            fullname = mRepPasswd.getText().toString();
                            repPassword = mPasswd.getText().toString();

                            if (phoneNumber.isEmpty() || password.isEmpty() || fullname.isEmpty()  ) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Введите все поля", Toast.LENGTH_SHORT);
                                toast.show();
                            } else if (password.equals(repPassword)){
                                Toast toast = Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                                else {
                                HashMap<String, String> postData = new HashMap<>();
                                postData.put("phone", phoneNumber);
                                postData.put("usertype", choice);
                                postData.put("password", password);
                                postData.put("full_name", fullname);
                                PostResponseAsyncTask task1 = new PostResponseAsyncTask(MainActivity.this,
                                        postData, new AsyncResponse() {

                                    @Override
                                    public void processFinish(String s) {
                                        Log.d("response", s);
                                        if(s.contains("ErrorInsert")){
                                            Toast.makeText(MainActivity.this,
                                                    "Что-то пошло не так.",
                                                    Toast.LENGTH_LONG).show();
                                        }else {
                                            Toast.makeText(getApplicationContext(), "Аккаунт создано", Toast.LENGTH_SHORT);
                                            Intent in = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(in);
                                            finish();
                                        }
                                    }
                                });
                                task1.execute("http://f0256942.xsph.ru/register.php");

                            }
                            Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
                            if (isSigninScreen)
                                btnSignup.startAnimation(clockwise);
                        }
                    });
                } else{
                    Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(in);
                    finish();
                }
            }
        }, 3000);
    }
    private void showSignupForm() {
        ((LinearLayout.LayoutParams) llSignin.getLayoutParams()).weight = 0.15f;
        llSignin.requestLayout();
        ((LinearLayout.LayoutParams) llSignup.getLayoutParams()).weight = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {
        ((LinearLayout.LayoutParams) llSignin.getLayoutParams()).weight = 0.85f;
        llSignin.requestLayout();
        ((LinearLayout.LayoutParams) llSignup.getLayoutParams()).weight = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.llSignin || v.getId() ==R.id.llSignup){
            // Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         choice = adapterView.getItemAtPosition(i).toString();
       // ((TextView) adapterView.getChildAt(0)).setTextColor(#FFFFFF);
       // Toast.makeText(adapterView.getContext(),text ,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}