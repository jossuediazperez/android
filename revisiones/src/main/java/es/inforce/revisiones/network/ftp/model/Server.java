package es.inforce.revisiones.network.ftp.model;

public class Server {
    private String ip;
    private String user;
    private String pass;

    public Server(String ip, String user, String pass) {
        this.ip = ip;
        this.user = user;
        this.pass = pass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Server() {

    }


}