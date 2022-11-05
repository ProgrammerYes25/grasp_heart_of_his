package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class startActivity extends AppCompatActivity {
    ImageView startImg;
    DBHelper dbHelper;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startImg = findViewById(R.id.start_img);

        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();
        startImg.setOnClickListener(startClickListener);
    }
    View.OnClickListener startClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainIntene = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntene);
        }
    };
}