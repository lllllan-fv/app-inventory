package cn.lllllan.android.app_inventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.lllllan.android.app_inventory.db.DBHelper;

public class AddCommodityActivity extends AppCompatActivity {
    String selectType = "";
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commodity);

        dbHelper = new DBHelper(AddCommodityActivity.this, "inventory.db", null, 2);

        Spinner spinner = (Spinner) findViewById(R.id.add_commodity_spinner);
        EditText editText = (EditText) findViewById(R.id.add_commodity_edit_text);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] type = getResources().getStringArray(R.array.commodity_type);
                selectType = String.valueOf(type[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button = (Button) findViewById(R.id.add_commodity_button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(AddCommodityActivity.this, "货品名称不能为空", Toast.LENGTH_SHORT).show();
                } else if (name.length() > 20) {
                    Toast.makeText(AddCommodityActivity.this, "货品名称不能超过20个字符", Toast.LENGTH_SHORT).show();
                } else {

                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query("commodity", null, "name=?", new String[]{name}, null, null, null);

                    boolean flag = cursor.moveToFirst();

                    if (flag) {
                        Toast.makeText(AddCommodityActivity.this, "货品已存在", Toast.LENGTH_SHORT).show();
                    } else {
                        // 插入数据库
                        db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();

//                    values.put("id", (byte[]) null);
                        values.put("type", selectType);
                        values.put("name", name);
                        db.insert("commodity", null, values);
                        values.clear();

                        editText.setText("");
                        Toast.makeText(AddCommodityActivity.this, "货品添加成功", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}