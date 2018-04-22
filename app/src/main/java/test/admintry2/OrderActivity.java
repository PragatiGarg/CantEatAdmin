package test.admintry2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderActivity extends AppCompatActivity {


    DatabaseReference databaseOrders;
    List<ItemInOrder> itemList;
    List<Order> orderList;

    ListView listViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        databaseOrders = FirebaseDatabase.getInstance().getReference("Orders");
        listViewOrder = findViewById(R.id.listOrder);

        itemList = new ArrayList<>();
        orderList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        orderList.clear();
        databaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot orderSnapshot: dataSnapshot.getChildren()){
                    Order order= orderSnapshot.getValue(Order.class);
                    orderList.add(order);
                }
                Collections.reverse(orderList);
//                Toast.makeText(OrderActivity.this, "Item List "+orderList.size(), Toast.LENGTH_SHORT).show();

                OrderList adapter = new OrderList(OrderActivity.this,orderList);
                listViewOrder.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onClickStatus(View v){
        RelativeLayout parentRow = (RelativeLayout)v.getParent();
        Button orderStatus = (Button) parentRow.getChildAt(3);
        TextView generatedId = (TextView) parentRow.getChildAt(4);
        if(orderStatus.getText() == "Completed"){
            Toast t = Toast.makeText(this, "Order already completed", Toast.LENGTH_SHORT);
            t.show();
            t.cancel();
        } else{
            databaseOrders.child(generatedId.getText().toString()).child("status").setValue(true);
            orderStatus.setText("Completed");
            orderStatus.setTextColor(Color.parseColor("#000000"));

        }
    }
}
