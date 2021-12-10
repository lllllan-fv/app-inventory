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

public class CommodityGroupAdapter extends ArrayAdapter<CommodityGroup> {


    private int resourceId;

    public CommodityGroupAdapter(Context context, int resource, List<CommodityGroup> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CommodityGroup commodityGroup = getItem(position);

        View view;
        CommodityGroupHolder commodityGroupHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            commodityGroupHolder = new CommodityGroupHolder(view.findViewById(R.id.listview_item_commodity_group_image),
                    view.findViewById(R.id.listview_item_commodity_group_name),
                    view.findViewById(R.id.listview_item_commodity_group_number));
            view.setTag(commodityGroupHolder);
        } else {
            view = convertView;
            commodityGroupHolder = (CommodityGroupHolder) view.getTag();
        }

        commodityGroupHolder.imageView.setImageResource(commodityGroup.getImageId());
        commodityGroupHolder.name.setText(commodityGroup.getName());
        commodityGroupHolder.number.setText(commodityGroup.getNumber());

        return view;
    }

    class CommodityGroupHolder {
        ImageView imageView;
        TextView name;
        TextView number;

        public CommodityGroupHolder(ImageView imageView, TextView name, TextView number) {
            this.imageView = imageView;
            this.name = name;
            this.number = number;
        }
    }
}
