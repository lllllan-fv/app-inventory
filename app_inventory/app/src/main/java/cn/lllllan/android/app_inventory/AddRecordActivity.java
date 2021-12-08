package cn.lllllan.android.app_inventory;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecordActivity extends AppCompatActivity {

    String[] commodities = new String[]{"铅笔", "沙发"};
    String commodity;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        // 初始化货品列表
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, commodities);
        Spinner commoditySP = (Spinner) findViewById(R.id.add_record_commodity);
        commoditySP.setAdapter(adapter);
        commoditySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                commodity = commodities[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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

        Button button = (Button) findViewById(R.id.add_record_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company = companyET.getText().toString();
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
                    Toast.makeText(AddRecordActivity.this, "提交", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}