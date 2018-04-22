package test.admintry2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    DatabaseReference databaseItems;
    ListView listViewFood;
    List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setContentView(R.layout.activity_menu);
        listViewFood = findViewById(R.id.listViewFood);
        databaseItems = FirebaseDatabase.getInstance().getReference("Items");
        itemList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        itemList.clear();
        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    Item item = itemSnapshot.getValue(Item.class);
                    itemList.add(item);

                }

                ItemList adapter = new ItemList(MenuActivity.this,itemList);
                listViewFood.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void toggleSwitch(View v){
        RelativeLayout parentRow = (RelativeLayout)v.getParent();
        Switch switchItem = (Switch) parentRow.getChildAt(3);
        TextView foodId = (TextView) parentRow.getChildAt(4);
        if(switchItem.isChecked()){
            databaseItems.child(foodId.getText().toString()).child("status").setValue(1);
        } else{
            databaseItems.child(foodId.getText().toString()).child("status").setValue(0);
        }
        itemList.clear();
        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    Item item = itemSnapshot.getValue(Item.class);
                    itemList.add(item);

                }

                ItemList adapter = new ItemList(MenuActivity.this,itemList);
                listViewFood.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
