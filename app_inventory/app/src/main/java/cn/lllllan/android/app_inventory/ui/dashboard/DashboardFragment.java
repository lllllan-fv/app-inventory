package cn.lllllan.android.app_inventory.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cn.lllllan.android.app_inventory.AddCommodityActivity;
import cn.lllllan.android.app_inventory.AddRecordActivity;
import cn.lllllan.android.app_inventory.R;
import cn.lllllan.android.app_inventory.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button commodityBtn = (Button) root.findViewById(R.id.action_button_add_commodity);
        commodityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddCommodityActivity.class);
                startActivity(intent);
            }
        });

        Button recordBtn = (Button) root.findViewById(R.id.action_button_add_record);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddRecordActivity.class);
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