package kz.baqshamninonimi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kz.baqshamninonimi.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private Toolbar mTb;
    private ImageView mImg;
    private TextView mProd_name;
    private TextView mDescription;
    private TextView mAuthor;
    private FloatingActionButton mbtnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        mTb=findViewById(R.id.toolbar);
        mImg= findViewById(R.id.img_product);
        mProd_name = findViewById(R.id.prod_name);
        mDescription = findViewById(R.id.description);
        mbtnCart = findViewById(R.id.btnCart);
        mAuthor = findViewById(R.id.author);
        Product prods= getIntent().getExtras().getParcelable("detail");

        Picasso.get()
                .load(prods.getImage())
                .into(mImg);
        mProd_name.setText(prods.getTitle());
        mTb.setTitle(prods.getTitle());
        mDescription.setText(prods.getShortdesc());
        mAuthor.setText(prods.getFarmer());
        mbtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Добавлен в корзину",Toast.LENGTH_LONG);
            }
        });

    }
}
