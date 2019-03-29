package kz.baqshamninonimi;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUs extends AppCompatActivity {

    ImageView ove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ove=findViewById(R.id.backdrop1);
        ove.setImageResource(R.drawable.food);
    }
}
