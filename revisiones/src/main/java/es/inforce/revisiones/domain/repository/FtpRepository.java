package es.inforce.revisiones.domain.repository;

/**
 * Created by jossue on 04/02/2017.
 */
public interface FtpRepository {
    boolean login(String server,String user, String pass) throws Exception;
    boolean downloadFile(String server,String user,String pass,String pathRemote,String pathLocal,String fileName)throws Exception;
}
