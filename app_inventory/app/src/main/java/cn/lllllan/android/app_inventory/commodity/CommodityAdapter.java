package cn.lllllan.android.app_inventory.commodity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
            commodityHolder = new CommodityHolder(view.findViewById(R.id.listview_item_commodity_id),
                    view.findViewById(R.id.listview_item_commodity_name),
                    view.findViewById(R.id.listview_item_commodity_quantity));
            view.setTag(commodityHolder);
        } else {
            view = convertView;
            commodityHolder = (CommodityHolder) view.getTag();
        }

        commodityHolder.id.setText(String.valueOf(commodity.getId()));
        commodityHolder.name.setText(commodity.getName());
        commodityHolder.quantity.setText("库存：" + commodity.getQuantity());

        return view;
    }

    class CommodityHolder {
        TextView id;
        TextView name;
        TextView quantity;

        public CommodityHolder(TextView id, TextView name, TextView quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }
    }
}
