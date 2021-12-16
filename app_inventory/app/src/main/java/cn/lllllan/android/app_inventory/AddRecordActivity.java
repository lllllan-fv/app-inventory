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

import cn.lllllan.android.app_inventory.db.DBHelper;

public class AddRecordActivity extends AppCompatActivity {

    ArrayList<String> commodities = new ArrayList<>();
    String commodity;
    String type;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        dbHelper = new DBHelper(AddRecordActivity.this, "inventory.db", null, 2);

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
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                String company = companyET.getText().toString();
                String date = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
                String price = priceET.getText().toString();
                price = String.valueOf(Double.parseDouble(price.equals("") ? "0" : price));
                String quantity = quantityET.getText().toString();

                if (company.equals("")) {
                    Toast.makeText(AddRecordActivity.this, "请填写供应商或客户", Toast.LENGTH_SHORT).show();
                } else if (price.equals("")) {
                    Toast.makeText(AddRecordActivity.this, "请输入单价", Toast.LENGTH_SHORT).show();
                } else if (quantity.equals("")) {
                    Toast.makeText(AddRecordActivity.this, "请输入数量", Toast.LENGTH_SHORT).show();
                } else {

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    String num = "0";
                    if (type.equals("销售出库")) {
                        quantity = String.valueOf(Integer.parseInt(quantity) * -1);

                        Cursor record = db.query("record", new String[]{"sum(quantity)"}, "commodity=?", new String[]{commodity}, null, null, null);
                        if (record.moveToFirst()) {
                            num = record.getString(record.getColumnIndex("sum(quantity)"));
                        }
                    }

                    if (num == null) num = "0";
                    if (type.equals("销售出库") && (Integer.valueOf(num) < (Integer.valueOf(quantity) * -1))) {
                        Toast.makeText(AddRecordActivity.this, "抱歉，库存不足 - " + num, Toast.LENGTH_SHORT).show();
                    } else {
                        values.put("type", type);
                        values.put("company", company);
                        values.put("commodity", commodity);
                        values.put("price", price);
                        values.put("quantity", quantity);
                        values.put("deal_date", date);

                        db.insert("record", null, values);
                        values.clear();

                        Toast.makeText(AddRecordActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        companyET.setText("");
                        priceET.setText("");
                        quantityET.setText("");

                    }
                }
            }
        });
    }

    void initCommodities() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}