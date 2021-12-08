package cn.lllllan.android.app_inventory.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cn.lllllan.android.app_inventory.R;
import cn.lllllan.android.app_inventory.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btn = (Button) root.findViewById(R.id.home_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "wahaha", Toast.LENGTH_SHORT).show();
            }
        });

        String[] colors = new String[]{"red", "blue"};

        Spinner spinner = (Spinner) root.findViewById(R.id.home_spinner);
        ArrayAdapter<CharSequence> items = new ArrayAdapter<CharSequence>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, colors);
        spinner.setAdapter(items);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}