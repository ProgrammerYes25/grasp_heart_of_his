package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import android.content.Context;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "GraspHeartOfHisDB", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE chapterTable(chapter_no INTEGER primary key, total_likability INTEGER);");
        db.execSQL("CREATE TABLE userTable(user_name CHAR(20) primary key, likability INTEGER, play_chapter INTEGER);");
        int total =0, likability = 0;
        for(int i = 0; i<=15; i++){
            db.execSQL("INSERT INTO chapterTable VALUES("+i+","+likability+");");
        }
        //db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS chapterTable" );
        db.execSQL("DROP TABLE IF EXISTS userTable" );
        onCreate(db);
    }
}
