package test.admintry2;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;
public class ItemList extends ArrayAdapter<Item> {

    private Activity context;
    private List<Item> itemList;

    public ItemList(Activity context,List<Item> itemList){
        super(context,R.layout.activity_itemlist,itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_itemlist,null,true);

        ImageView imageView = listViewItem.findViewById(R.id.imageFood);
        TextView foodName = listViewItem.findViewById(R.id.foodName);
        TextView foodCost = listViewItem.findViewById(R.id.foodCost);
        TextView foodId = listViewItem.findViewById(R.id.foodId);
        Item item = itemList.get(position);
        Switch switchItem = listViewItem.findViewById(R.id.switchItem);

        foodName.setText(item.getItemName());
        foodCost.setText("Rs. "+(int) item.getItemCost()+".0");
        foodId.setText(item.getItemId()+"");
        foodId.setVisibility(TextView.INVISIBLE);

        if(item.getStatus() == 0){
            switchItem.setChecked(false);
        } else{
            switchItem.setChecked(true);
        }
        if(item.getImageUri()!=null){
            String photoUri = item.getImageUri().toString();
            Glide.with(context)
                    .load(photoUri)
                    .into(imageView);
        }
        return listViewItem;

    }
}
