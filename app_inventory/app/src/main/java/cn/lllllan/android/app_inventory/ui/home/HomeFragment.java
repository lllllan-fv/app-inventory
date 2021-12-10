package cn.lllllan.android.app_inventory.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cn.lllllan.android.app_inventory.CommodityShowActivity;
import cn.lllllan.android.app_inventory.R;
import cn.lllllan.android.app_inventory.commodity.CommodityGroup;
import cn.lllllan.android.app_inventory.commodity.CommodityGroupAdapter;
import cn.lllllan.android.app_inventory.databinding.FragmentHomeBinding;
import cn.lllllan.android.app_inventory.db.DBHelper;

public class HomeFragment extends Fragment {

    private int[] imageId = new int[]{
            R.drawable.dianqi, R.drawable.diannao, R.drawable.yundong,
            R.drawable.yifu, R.drawable.riyong, R.drawable.shipin
    };
    private String[] names;
    private int[] numbers = new int[imageId.length];

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        names = getResources().getStringArray(R.array.commodity_type);

        DBHelper dbHelper = new DBHelper(getContext(), "inventory.db", null, 2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("commodity", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
                for (int i = 0; i < names.length; ++i) {
                    if (type.equals(names[i])) {
                        numbers[i]++;
                        break;
                    }
                }
            } while (cursor.moveToNext());
        }

        List<CommodityGroup> commodityGroupList = new ArrayList<>();
        for (int i = 0, len = imageId.length; i < len; ++i) {
            commodityGroupList.add(new CommodityGroup(imageId[i], names[i], String.valueOf(numbers[i])));
        }

        CommodityGroupAdapter adapter = new CommodityGroupAdapter(getContext(), R.layout.listview_item_commodity_group, commodityGroupList);
        ListView listView = root.findViewById(R.id.commodity_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CommodityGroup commodityGroup = commodityGroupList.get(i);
                Intent intent = new Intent(getContext(), CommodityShowActivity.class);
                intent.putExtra("type", commodityGroup.getName());
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}