package fpoly.andoid.extest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCreate extends SQLiteOpenHelper {
    public static final String DB_NAME = "db_note";
    public static final int VERSION = 1;

    public DbCreate(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cr_tb_note = "CREATE TABLE tb_note (id INTEGER NOT NULL UNIQUE, title TEXT NOT NULL, content TEXT NOT NULL, time TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";
        db.execSQL(cr_tb_note);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
