package cn.lllllan.android.app_inventory;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.lllllan.android.app_inventory.commodity.Commodity;
import cn.lllllan.android.app_inventory.commodity.CommodityAdapter;
import cn.lllllan.android.app_inventory.db.DBHelper;

public class CommodityShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_show);

        String type = getIntent().getStringExtra("type");

        DBHelper dbHelper = new DBHelper(CommodityShowActivity.this, "inventory.db", null, 2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("commodity", null, "type=?", new String[]{type}, null, null, null);


        List<Commodity> commodityList = new ArrayList<>();

        int cnt = 0;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            commodityList.add(new Commodity(++cnt, name, String.valueOf(0)));
        } else {
            Toast.makeText(CommodityShowActivity.this, "无", Toast.LENGTH_SHORT).show();
        }

        CommodityAdapter commodityAdapter = new CommodityAdapter(CommodityShowActivity.this, R.layout.listview_item_commodity, commodityList);
        ListView listView = findViewById(R.id.commodity_show_listview);
        listView.setAdapter(commodityAdapter);
    }
}