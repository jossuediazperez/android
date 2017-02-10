package es.inforce.revisiones.presentation.ui.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import es.inforce.revisiones.RevisionsAplication;
import es.inforce.revisiones.domain.model.PendingFile;
import es.inforce.revisiones.util.Constant;
import es.inforce.revisiones.util.FileUtil;

/**
 * Created by jossue on 09/02/2017.
 */
public class DummyPendingFiles {
    public static List<PendingFile> getFiles(Context context){
        List<PendingFile> pendingFiles = new ArrayList<PendingFile>();

        PendingFile pendingFileControl = new PendingFile();
        pendingFileControl.setNameFile(Constant.FILES.CONTROL);
        pendingFileControl.setPathRemote(RevisionsAplication.getInstance().getImei());
        pendingFileControl.setPathLocal(FileUtil.getAppFolder(context) + Constant.FOLDERS.ROOT);

        pendingFiles.add(pendingFileControl);

        PendingFile pendingFileEtiquetas = new PendingFile();
        pendingFileEtiquetas.setNameFile(Constant.FILES.ETIQUETAS);
        pendingFileEtiquetas.setPathRemote(Constant.FOLDERS.GENERAL);
        pendingFileEtiquetas.setPathLocal(FileUtil.getAppFolder(context) + Constant.FOLDERS.GENERAL);

        pendingFiles.add(pendingFileEtiquetas);

        PendingFile pendingFilePlantilla = new PendingFile();
        pendingFilePlantilla.setNameFile(Constant.FILES.PLANTILLA);
        pendingFilePlantilla.setPathRemote(Constant.FOLDERS.GENERAL);
        pendingFilePlantilla.setPathLocal(FileUtil.getAppFolder(context) + Constant.FOLDERS.GENERAL);

        pendingFiles.add(pendingFilePlantilla);

        return pendingFiles;

    }
}
