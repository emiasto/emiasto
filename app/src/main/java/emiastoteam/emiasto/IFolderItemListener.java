package emiastoteam.emiasto;

import java.io.File;

/**
 * Created by Marcin on 2015-11-11.
 */
public interface IFolderItemListener {

    void OnCannotFileRead(File file);//implement what to do folder is Unreadable
    void OnFileClicked(File file);//What to do When a file is clicked
}
