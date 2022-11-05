package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import android.content.Context;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "GraspHeartOfHisDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE userTable(chapter_no INTEGER primary key, java_likability INTEGER, total_likability INTEGER);");
        int total =0, likability = 0;
        for(int i = 1; i<=15; i++){
            db.execSQL("INSERT INTO userTable VALUES("+i+","+likability+","+total+");");
        }
        //db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userTable" );
        onCreate(db);
    }
}
