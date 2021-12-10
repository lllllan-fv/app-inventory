package cn.lllllan.android.app_inventory.commodity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cn.lllllan.android.app_inventory.R;

public class CommodityAdapter extends ArrayAdapter<Commodity> {


    private int resourceId;

    public CommodityAdapter(Context context, int resource, List<Commodity> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Commodity commodity = getItem(position);

        View view;
        CommodityHolder commodityHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            commodityHolder = new CommodityHolder(view.findViewById(R.id.listview_item_commodity_group_image),
                    view.findViewById(R.id.listview_item_commodity_group_name),
                    view.findViewById(R.id.listview_item_commodity_group_number));
            view.setTag(commodityHolder);
        } else {
            view = convertView;
            commodityHolder = (CommodityHolder) view.getTag();
        }

        commodityHolder.imageView.setImageResource(commodity.getImageId());
        commodityHolder.name.setText(commodity.getName());
        commodityHolder.number.setText(commodity.getNumber());

        return view;
    }

    class CommodityHolder {
        ImageView imageView;
        TextView name;
        TextView number;

        public CommodityHolder(ImageView imageView, TextView name, TextView number) {
            this.imageView = imageView;
            this.name = name;
            this.number = number;
        }
    }
}
