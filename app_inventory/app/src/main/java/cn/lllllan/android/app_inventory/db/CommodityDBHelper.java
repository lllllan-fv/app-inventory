package cn.lllllan.android.app_inventory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CommodityDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_CONTACT = "CREATE TABLE IF NOT EXISTS commodity (" +
            "id INT UNSIGNED AUTO_INCREMENT," +
            "type VARCHAR(20) NOT NULL," +
            "name VARCHAR(20) NOT NULL," +
            "PRIMARY KEY (id)" +
            ")";

    private Context mContext;

    public CommodityDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT);
        Toast.makeText(mContext, "Create commodity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
