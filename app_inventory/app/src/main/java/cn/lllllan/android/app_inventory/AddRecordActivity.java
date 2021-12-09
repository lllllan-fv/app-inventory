package cn.lllllan.android.app_inventory;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import cn.lllllan.android.app_inventory.db.CommodityDBHelper;
import cn.lllllan.android.app_inventory.db.RecordDBHelper;

public class AddRecordActivity extends AppCompatActivity {

    ArrayList<String> commodities = new ArrayList<>();
    String commodity;
    String type;

    RecordDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        dbHelper = new RecordDBHelper(AddRecordActivity.this, "inventory.db", null, 1);

        // 初始化货品列表
        initCommodities();

        Spinner typeSP = (Spinner) findViewById(R.id.add_record_type);
        typeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] types = getResources().getStringArray(R.array.record_type);
                type = types[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        EditText companyET = (EditText) findViewById(R.id.add_record_company);
        EditText priceET = (EditText) findViewById(R.id.add_record_price);
        EditText quantityET = (EditText) findViewById(R.id.add_record_quantity);
        DatePicker datePicker = (DatePicker) findViewById(R.id.add_record_date);

        Button button = (Button) findViewById(R.id.add_record_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company = companyET.getText().toString();
                String date = String.valueOf(datePicker.getYear()) + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth();
                String priceString = priceET.getText().toString();
                Double price = Double.parseDouble(priceString.equals("") ? "0" : priceString);
                String quantityString = quantityET.getText().toString();
                int quantity = Integer.parseInt(quantityString.equals("") ? "0" : quantityString);

                if (company.equals("")) {
                    Toast.makeText(AddRecordActivity.this, "请填写供应商或客户", Toast.LENGTH_SHORT).show();
                } else if (priceString.equals("")) {
                    Toast.makeText(AddRecordActivity.this, "请输入单价", Toast.LENGTH_SHORT).show();
                } else if (quantityString.equals("")) {
                    Toast.makeText(AddRecordActivity.this, "请输入数量", Toast.LENGTH_SHORT).show();
                } else {

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put("type", type);
                    values.put("company", company);
                    values.put("commodity", commodity);
                    values.put("price", price);
                    values.put("quantity", quantity);
                    values.put("time", date);

                    db.insert("record", null, values);
                    values.clear();

                }
            }
        });
    }

    void initCommodities() {
        CommodityDBHelper commodityDBHelper = new CommodityDBHelper(AddRecordActivity.this, "inventory.db", null, 1);
        SQLiteDatabase db = commodityDBHelper.getReadableDatabase();
        Cursor cursor = db.query("commodity", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                commodities.add(name);
            } while (cursor.moveToNext());
        }

        Spinner commoditySP = (Spinner) findViewById(R.id.add_record_commodity);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, commodities);
        commoditySP.setAdapter(adapter);
        commoditySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                commodity = commodities.get(i);
                Toast.makeText(AddRecordActivity.this, commodity, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}