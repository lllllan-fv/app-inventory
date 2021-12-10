package cn.lllllan.android.app_inventory.ui.home;

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
import cn.lllllan.android.app_inventory.commodity.Commodity;
import cn.lllllan.android.app_inventory.commodity.CommodityAdapter;
import cn.lllllan.android.app_inventory.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private int[] imageId = new int[]{
            R.drawable.dianqi, R.drawable.diannao, R.drawable.yundong,
            R.drawable.yifu, R.drawable.riyong, R.drawable.shipin
    };

    private String[] names;
    private ListView listView;
    private List<Commodity> commodityList = new ArrayList<>();

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        names = getResources().getStringArray(R.array.commodity_type);
        for (int i = 0, len = imageId.length; i < len; ++i) {
            commodityList.add(new Commodity(imageId[i], names[i], String.valueOf(0)));
        }

        CommodityAdapter adapter = new CommodityAdapter(getContext(), R.layout.listview_item_commodity_group, commodityList);
        listView = root.findViewById(R.id.commodity_listview);
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}