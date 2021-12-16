package cn.lllllan.android.app_inventory.ui.notifications;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cn.lllllan.android.app_inventory.R;
import cn.lllllan.android.app_inventory.databinding.FragmentNotificationsBinding;
import cn.lllllan.android.app_inventory.db.DBHelper;
import cn.lllllan.android.app_inventory.record.Record;
import cn.lllllan.android.app_inventory.record.RecordAdapter;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView listView = (ListView) root.findViewById(R.id.record_listview);
        List<Record> list = new ArrayList<>();

        DBHelper dbHelper = new DBHelper(getContext(), "inventory.db", null, 2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("record", null, null, null, null, null, "deal_date desc");
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
                @SuppressLint("Range") String company = cursor.getString(cursor.getColumnIndex("company"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("deal_date"));
                @SuppressLint("Range") String commodity = cursor.getString(cursor.getColumnIndex("commodity"));
                @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String quantity = cursor.getString(cursor.getColumnIndex("quantity"));
                String amount = String.valueOf(-1 * Integer.parseInt(price) * Integer.parseInt(quantity));


                Record record = new Record(type, company, date, commodity, price, quantity, amount);
                list.add(record);
            } while (cursor.moveToNext());
        }

        RecordAdapter recordAdapter = new RecordAdapter(getContext(), R.layout.listview_item_record, list);
        listView.setAdapter(recordAdapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}