package cn.lllllan.android.app_inventory;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CommodityShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_show);

        String type = getIntent().getStringExtra("type");
        Toast.makeText(CommodityShowActivity.this, type, Toast.LENGTH_SHORT).show();
    }
}