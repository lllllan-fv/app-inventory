package cn.lllllan.android.app_inventory.record;

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

public class RecordAdapter extends ArrayAdapter<Record> {


    private int resourceId;

    public RecordAdapter(Context context, int resource, List<Record> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Record record = getItem(position);

        View view;
        RecordHolder recordHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            recordHolder = new RecordHolder(
                    view.findViewById(R.id.listview_item_record_type),
                    view.findViewById(R.id.listview_item_record_company),
                    view.findViewById(R.id.listview_item_record_date),
                    view.findViewById(R.id.listview_item_record_commodity),
                    view.findViewById(R.id.listview_item_record_price),
                    view.findViewById(R.id.listview_item_record_quantity),
                    view.findViewById(R.id.listview_item_record_amount));
            view.setTag(recordHolder);
        } else {
            view = convertView;
            recordHolder = (RecordHolder) view.getTag();
        }

        recordHolder.type.setText(record.getType());
        recordHolder.company.setText("合作对象：" + record.getCompany());
        recordHolder.date.setText("日期：" + record.getDate());
        recordHolder.commodity.setText("货品：" + record.getCommodity());
        recordHolder.price.setText("单价：" + record.getPrice());
        recordHolder.quantity.setText("数量：" + record.getQuantity());
        recordHolder.amount.setText("共计：" + record.getAmount());

        return view;
    }

    class RecordHolder {
        TextView type;
        TextView company;
        TextView date;
        TextView commodity;
        TextView price;
        TextView quantity;
        TextView amount;

        public RecordHolder(TextView type, TextView company, TextView date, TextView commodity, TextView price, TextView quantity, TextView amount) {
            this.type = type;
            this.company = company;
            this.date = date;
            this.commodity = commodity;
            this.price = price;
            this.quantity = quantity;
            this.amount = amount;
        }
    }
}
