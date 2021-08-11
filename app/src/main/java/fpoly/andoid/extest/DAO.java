package fpoly.andoid.extest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Currency;

public class DAO {

    private SQLiteDatabase database;
    private DbCreate dbCreate;

    public DAO(Context context){
        dbCreate = new DbCreate(context);
    }

    public void open(){
        database = dbCreate.getWritableDatabase();
    }

    public void close(){
        dbCreate.close();
    }

    public long Add(DTO dto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DTO.COL_TIME, dto.getTime());
        contentValues.put(DTO.COL_TITLE, dto.getTitle());
        contentValues.put(DTO.COL_CONTENT, dto.getContent());

        long res = database.insert(DTO.TB_NAME, null, contentValues);
        return res;
    }

    public int Del(DTO dto){
        return database.delete(DTO.TB_NAME, "id = " + dto.getId(), null);
    }

    public int Update(DTO dto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DTO.COL_TIME, dto.getTime());
        contentValues.put(DTO.COL_TITLE, dto.getTitle());
        contentValues.put(DTO.COL_CONTENT, dto.getContent());

        int res = database.update(DTO.TB_NAME, contentValues, "id = " + dto.getId(), null);
        return res;
    }

    public ArrayList<DTO> GetAll(){
        ArrayList<DTO> list = new ArrayList<>();
        String[] allCol = new String[]{DTO.COL_ID, DTO.COL_TITLE, DTO.COL_CONTENT, DTO.COL_TIME};
        Cursor cursor = database.query(DTO.TB_NAME, allCol, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String time = cursor.getString(3);

            DTO dto = new DTO();
            dto.setId(id);
            dto.setTitle(title);
            dto.setContent(content);
            dto.setTime(time);

            list.add(dto);
            cursor.moveToNext();
        }
        return list;
    }
}
