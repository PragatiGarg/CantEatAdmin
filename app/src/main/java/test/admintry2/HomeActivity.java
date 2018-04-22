package test.admintry2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button profile;
    Button orders;
    Button menu;


    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();


//        profile = findViewById(R.id.buttonProfile);
        orders = findViewById(R.id.buttonOrders);
        menu = findViewById(R.id.buttonMenu);
        orders.setOnClickListener(this);
        menu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
//            case R.id.buttonProfile:
//                startActivity(new Intent(this,ProfileActivity.class));
//                break;
            case R.id.buttonOrders:
                startActivity(new Intent(this,OrderActivity.class));
                break;
            case R.id.buttonMenu:
                startActivity(new Intent(this,MenuActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,MainActivity.class));

        }
    }

}
