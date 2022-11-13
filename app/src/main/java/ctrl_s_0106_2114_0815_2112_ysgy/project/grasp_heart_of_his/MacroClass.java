package ctrl_s_0106_2114_0815_2112_ysgy.project.grasp_heart_of_his;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MacroClass extends AppCompatActivity {
    public void storyMacro(InputStream inputText, ImageView characterImg, Button nameBox, Button chatBox){
        Scanner sc = new Scanner(inputText, "UTF-8");
        nameBox.setText(sc.nextLine());
        chatBox.setText(sc.nextLine());
        chatBox.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(sc.hasNextLine()){
                     String name = sc.nextLine();
                     switch(name){
                         case "JAVA":
                             characterImg.setImageResource(R.drawable.java);
                             nameBox.setText("한자바");
                             break;
                         case "C":
                             characterImg.setImageResource(R.drawable.c);
                             nameBox.setText("박시언");
                             break;
                         case "C++":
                             characterImg.setImageResource(R.drawable.cpp);
                             nameBox.setText("박시플");
                             break;
                         case "C#":
                             characterImg.setImageResource(R.drawable.cs);
                             nameBox.setText("박시샵");
                             break;
                         case "Python":
                             characterImg.setImageResource(R.drawable.py);
                             nameBox.setText("파이썬");
                             break;
                         case "백히진":
                             characterImg.setImageResource(R.drawable.clear);
                             break;
                         default:
                             nameBox.setText(name);
                             break;
                     }
//                     switch(name){
//                         case "1":
//                             characterImg.setImageResource(R.drawable.java);
//                             break;
//                         case "2":
//                             characterImg.setImageResource(R.drawable.c);
//                             break;
//                         case "3":
//                             characterImg.setImageResource(R.drawable.cpp);
//                             break;
//                         case "4":
//                             characterImg.setImageResource(R.drawable.cs);
//                             break;
//                         case "5":
//                             characterImg.setImageResource(R.drawable.py);
//                             break;
//                         case "6":
//                             characterImg.setImageResource(R.drawable.clear);
//                             break;
//                     }
                     chatBox.setText(sc.nextLine());
                 }
             }
         });
    }

    public void loadingStoryMacro(InputStream inputText, Button tipBtn){
        Scanner sc = new Scanner(inputText, "UTF-8");
        tipBtn.setText(sc.next());
    }

}
