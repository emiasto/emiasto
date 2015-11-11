package emiastoteam.emiasto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class LoadFileActivity extends Activity implements IFolderItemListener {

    FolderLayout localFolders;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_file);

        localFolders = (FolderLayout)findViewById(R.id.localfolders);
        localFolders.setIFolderItemListener(this);
        localFolders.setDir("./sys");//change directory if u want,default is root

    }

    //Your stuff here for Cannot open Folder
    public void OnCannotFileRead(File file) {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(this)
                .setTitle(
                        "[" + file.getName()
                                + "] folder can't be read!")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {


                            }
                        }).show();

    }


    //Your stuff here for file Click
    public void OnFileClicked(File file) {
        // TODO Auto-generated method stub

        String data = file.getPath();//.getName();
        Intent intent = new Intent();
        intent.putExtra("FileName", data );
        setResult(RESULT_OK, intent);
        finish();
    }
}
