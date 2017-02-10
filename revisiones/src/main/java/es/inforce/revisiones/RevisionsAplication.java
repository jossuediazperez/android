package es.inforce.revisiones;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import es.inforce.revisiones.domain.model.ContentLanguage;
import es.inforce.revisiones.domain.model.Control;
import es.inforce.revisiones.domain.model.Language;
import es.inforce.revisiones.network.ftp.model.Server;
import es.inforce.revisiones.util.Constant;
import es.inforce.revisiones.util.FileUtil;
import es.inforce.revisiones.util.TextUtil;

/**
 * Created by jossue on 07/02/2017.
 */
public class RevisionsAplication extends Application {


    private List<Language> languageList;
    private static RevisionsAplication mInstance;
    private Control control;


    public Server getServer() {
        return server;
    }

    public void setServer() {
        server = new Server();
        server.setIp(getControl().getValue(Constant.CONTROL.DIRECION_FTP));
        server.setUser(getControl().getValue(Constant.CONTROL.USUARIO_FTP));
        server.setPass(getControl().getValue(Constant.CONTROL.PASSWORD_FTP));
    }

    private Server server;

    private int languageSelected;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String imei;

    public static RevisionsAplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        setLanguage();
        setControl();
        setServer();
        /*loadLanguages();
        loadControl();
        initDownloader();*/
        server=new Server();
        server.setIp("192.168.42.1");
        server.setUser("apprevatsa");
        server.setPass("Gr-2735!j");
    }

    private void setLanguage() {
        if (FileUtil.existFile(FileUtil.getAppFolder(this.getApplicationContext()) + Constant.FOLDERS.GENERAL + File.separator + Constant.FILES.ETIQUETAS)) {
            loadCustomLanguages();
        } else {
            loadDefaultLanguages();
        }
    }

    public int getLanguageSelected() {
        return languageSelected;
    }

    public void setLanguageSelected(int languageSelected) {
        this.languageSelected = languageSelected;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putInt(Constant.LANGUAGE_SELECTED, languageSelected).commit();
    }

    private void loadCustomLanguages() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        languageSelected = sp.getInt(Constant.LANGUAGE_SELECTED, 0);
        String leng = TextUtil.readStringFromFile(FileUtil.getAppFolder(this.getApplicationContext()) + Constant.FOLDERS.GENERAL + File.separator + Constant.FILES.ETIQUETAS);
        ContentLanguage contentLanguage = new Gson().fromJson(leng, ContentLanguage.class);
        languageList = contentLanguage.getLanguageList();
    }

    private void loadDefaultLanguages() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        languageSelected = sp.getInt(Constant.LANGUAGE_SELECTED, 0);
        String leng = TextUtil.readRawById(this, R.raw.etiquetas);
        ContentLanguage contentLanguage = new Gson().fromJson(leng, ContentLanguage.class);
        languageList = contentLanguage.getLanguageList();
    }

    public Language getCurrentLanguage() {
        return languageList.get(languageSelected);
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public boolean bootFolders() {
        String ftpDir = FileUtil.createFolderIfNoExist(FileUtil.getAppFolder(this.getApplicationContext()) + Constant.FOLDERS.ROOT);
        if(null == ftpDir){
            return false;
        }
        // General
        if(null == FileUtil.createFolderIfNoExist(ftpDir + Constant.FOLDERS.GENERAL)){
            return false;
        }
        // Historico
        if(null == FileUtil.createFolderIfNoExist(ftpDir + Constant.FOLDERS.HISTORY)){
            return false;
        }
        // Nuevas
        if(null == FileUtil.createFolderIfNoExist(ftpDir + Constant.FOLDERS.NEW)){
            return false;
        }
        // Terminadas
        return null != FileUtil.createFolderIfNoExist(ftpDir + Constant.FOLDERS.FINISHED);
    }

    public Control getControl() {
        return control;
    }

    public void setControl() {
        if (FileUtil.existFile(FileUtil.getAppFolder(this.getApplicationContext()) + Constant.FOLDERS.ROOT + File.separator + Constant.FILES.CONTROL)) {
            loadCustomControl();
        } else {
            loadDefaultControl();
        }
    }

    public void loadCustomControl() {
        String controlStr = TextUtil.readStringFromFile(FileUtil.getAppFolder(this.getApplicationContext()) + Constant.FOLDERS.ROOT + File.separator + Constant.FILES.CONTROL);
        this.control = new Gson().fromJson(controlStr, Control.class);
    }

    public void loadDefaultControl() {
        String controlStr = TextUtil.readRawById(this, R.raw.control);
        this.control = new Gson().fromJson(controlStr, Control.class);
    }

    public boolean setControlValue(String key, String value){
        if(this.control.setValue(key, value)){
            return saveControl();
        }
        return false;
    }

    public boolean saveControl(){
        String controlJson = new Gson().toJson(this.control);
        //TODO Ver como guardo este valor
        return true;
    }

    public String getControlValue(String key){
        return this.control.getValue(key);
    }
}
