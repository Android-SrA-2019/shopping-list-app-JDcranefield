package ca.nbcc.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.nbcc.shoppinglist.models.GroceryListItem;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private List<TextView> txtBoxes;
    private GroceryListItem[] grocRecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View layout = findViewById(R.id.info);

        txtBoxes = new ArrayList<>();
        grocRecs = new GroceryListItem[10];

        for(int i = 0; i < 10; i++){
            TextView txt = new TextView(this);
            txt.setId(i+1);
            txt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            txtBoxes.add(txt);
            ((LinearLayout)layout).addView(txt);
        }

        // Restore the state.
        if (savedInstanceState != null) {
            for(int i = 0; i < 10; i++){
                if(savedInstanceState.getString("txt" + (i + 1)) == null){
                    break;
                }

                grocRecs[i] = new GroceryListItem(
                        savedInstanceState.getString("txt" + (i + 1)),
                        savedInstanceState.getInt("count" + (i + 1))
                );

                String out = grocRecs[i].getItemName() + getString(R.string.count_x) + grocRecs[i].getCount();
                txtBoxes.get(i).setText(out);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i = 0; i < 10; i++){
            if(grocRecs[i] == null){
                break;
            }
            outState.putString("txt" + (i+1), grocRecs[i].getItemName());
            outState.putInt("count" + (i+1), grocRecs[i].getCount());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                for(int i = 0; i < 10; i++){
                    if(grocRecs[i] == null){
                        grocRecs[i] = new GroceryListItem(
                                reply,
                                1
                        );
                    }else if(grocRecs[i].getItemName().equals(reply)){
                        grocRecs[i].addOne();
                    }

                    if(grocRecs[i].getItemName().equals(reply)){
                        String out = grocRecs[i].getItemName() + getString(R.string.count_x) + grocRecs[i].getCount();
                        txtBoxes.get(i).setText(out);
                        break;
                    }
                }
            }
        }
    }

    public void btnClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}
