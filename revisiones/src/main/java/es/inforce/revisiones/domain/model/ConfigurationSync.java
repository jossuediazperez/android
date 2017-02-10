package es.inforce.revisiones.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jossue on 09/02/2017.
 */
public class ConfigurationSync {

    public void setPendingFiles(List<PendingFile> pendingFiles) {
        this.pendingFiles = pendingFiles;
    }

    private List<PendingFile> pendingFiles;

    public ConfigurationSync() {
        pendingFiles = new ArrayList<PendingFile>();
    }

    public List<PendingFile> getPendingFiles() {
        return pendingFiles;
    }

    public void addPendingFile(PendingFile pendingFile){
        pendingFiles.add(pendingFile);
    }

}
