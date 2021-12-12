package cn.lllllan.android.app_inventory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String CREATE_COMMODITY = "CREATE TABLE IF NOT EXISTS commodity (" +
            "id INT UNSIGNED AUTO_INCREMENT," +
            "type VARCHAR(20) NOT NULL," +
            "name VARCHAR(20) NOT NULL," +
            "PRIMARY KEY (id)" +
            ")";

    public static final String CREATE_RECORD = "CREATE TABLE IF NOT EXISTS record(" +
            "id INT UNSIGNED AUTO_INCREMENT," +
            "type VARCHAR(20) NOT NULL," +
            "company VARCHAR(20) NOT NULL," +
            "commodity VARCHAR(20) NOT NULL," +
            "price INT NOT NULL," +
            "quantity INT NOT NULL," +
            "deal_date VARCHAR(20) NOT NULL," +
            "PRIMARY KEY (id)" +
            ")";

    private Context mContext;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMMODITY);
        db.execSQL(CREATE_RECORD);
        Toast.makeText(mContext, "Create commodity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists commodity");
        db.execSQL("drop table if exists record");
    }

}
