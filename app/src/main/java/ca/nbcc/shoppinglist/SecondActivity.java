package ca.nbcc.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "ca.nbcc.shoppingList.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void btnClick(View view) {
        String groceryItem = (String)((Button)view).getText();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, groceryItem);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
