package es.inforce.revisiones.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by david on 2/7/2017.
 */

public class FileUtil {

    public static boolean existFile(String path){
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    public static String getAppFolder(Context context) {
        return Environment.getDataDirectory() + File.separator + "data" + File.separator
                + context.getPackageName() + File.separator;
    }

    public static String createFolderIfNoExist(String dirToInit) {
        File dir = new File(dirToInit);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                return null;
            }
        }
        return dir.toString() + File.separator;
    }
}
