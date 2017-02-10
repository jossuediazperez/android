package es.inforce.revisiones.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jossue on 06/02/2017.
 */
public class TextUtil {



    public static String readRawById(Context mContext, int id) {
        String result = "";
        InputStream mInputStream = mContext.getResources().openRawResource(id);
        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        boolean end = false;
        try {
            while (!end) {
                String linea = reader.readLine();
                if (linea != null) {
                    result += linea;
                } else
                    end = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        Boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                sb.append(line);
                firstLine = false;
            } else {
                sb.append("\n").append(line);
            }
        }
        reader.close();
        return sb.toString();
    }

    public static String readStringFromFile(String filePath) {
        String result = "";
        File fl = new File(filePath);
        try {
            FileInputStream fin = new FileInputStream(fl);
            result = convertStreamToString(fin);
            fin.close();

        } catch (Exception e) {

        }
        return result;
    }
}
