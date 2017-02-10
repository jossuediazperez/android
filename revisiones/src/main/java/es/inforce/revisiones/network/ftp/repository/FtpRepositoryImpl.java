package es.inforce.revisiones.network.ftp.repository;

import android.content.Context;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;

import es.inforce.revisiones.domain.repository.FtpRepository;

/**
 * Created by jossue on 04/02/2017.
 */
public class FtpRepositoryImpl extends FtpParentRepository implements FtpRepository {
    public FtpRepositoryImpl(Context context) {
        super(context);
    }

    @Override
    public boolean login(String server, String user, String pass) throws Exception {
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(InetAddress.getByName(server));
        if (ftpClient.login(user, pass)) {
            result = true;
        }
        ftpClient.logout();
        ftpClient.disconnect();
        return result;
    }

    @Override
    public boolean downloadFile(String server, String user, String pass, String pathRemote, String pathLocal, String fileName) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(InetAddress.getByName(server));
        if (!ftpClient.login(user, pass)) {
            return false;
        }
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ftpClient.changeWorkingDirectory(pathRemote);
        for (FTPFile file : ftpClient.listFiles()) {
            if (file.isFile() && file.getName().equals(fileName)) {
                OutputStream output = new FileOutputStream(pathLocal + File.separator + file.getName());
                ftpClient.retrieveFile(file.getName(), output);
                output.close();
                return true;
            }
        }
        return false;
    }

}
