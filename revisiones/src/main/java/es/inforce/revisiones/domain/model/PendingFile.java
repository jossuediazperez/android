package es.inforce.revisiones.domain.model;

/**
 * Created by jossue on 09/02/2017.
 */
public class PendingFile {


    public static int STATUS_PENDING = 0;
    public static int STATUS_DOWNLOADING = 1;
    public static int STATUS_FNISHED = 2;

    private String pathRemote;
    private String pathLocal;
    private String nameFile;
    private int status;


    public PendingFile(String pathRemote, String pathLocal, String nameFile) {
        this.pathRemote = pathRemote;
        this.pathLocal = pathLocal;
        this.nameFile = nameFile;
    }

    public PendingFile() {

    }

    public String getPathRemote() {
        return pathRemote;
    }

    public void setPathRemote(String pathRemote) {
        this.pathRemote = pathRemote;
    }

    public String getPathLocal() {
        return pathLocal;
    }

    public void setPathLocal(String pathLocal) {
        this.pathLocal = pathLocal;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
