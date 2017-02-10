package es.inforce.revisiones.util;

/**
 * Created by jossue on 07/02/2017.
 */
public class Constant {
    public static final String LANGUAGE_SELECTED = "language";
    public static final String FINALIZAR = "finalizar";

    public static final class AUTHENTICATION {
        public static final String AUTH = "autenticando";
        public static final String AUTH_SUCCES = "autenticado";
        public static final String AUTH_FAIL = "autenticacionFallida";
        public static final String AUTH_ERROR = "autenticacionError";
        public static final String SYNC = "sincronizandoDatos";
        public static final String SYNC_FAIL = "sincronizacionFallida";
        public static final String SYNC_SUCCES = "sincronizado";
        public static final String SYNC_EROR = "sincronizacionError";
    }

    public static final class CONTROL {
        public static final String ID_DISPOSITIVO = "CtlIdDispositivo";
        public static final String USUARIO = "CtlUsuario";
        public static final String PASSWORD = "CtlPassword";
        public static final String DIRECION_FTP = "CtlDireccionFtp";
        public static final String USUARIO_FTP = "CtlUsuarioFtp";
        public static final String PASSWORD_FTP = "CtlPasswordFtp";
        public static final String PASSWORD_DESBLOQUEO = "CtlPasswordDesbloqueo";
        public static final String PASWORD_ADMIN = "CtlPasswordAdmin";
        public static final String BLOQUEO_INMEDATO = "CtlBloqueoInmediato";
        public static final String CREDITO_REVISIONES = "CtlCreditoRevisiones";
        public static final String IDIOMA = "CtlIdioma";
        public static final String NOMBRE = "Nombre";
        public static final String CORREO = "Correo";
    }

    public static final class FILES {
        public static final String CONTROL = "control.json";
        public static final String ETIQUETAS = "etiquetas.json";
        public static final String PLANTILLA = "plantilla.json";
    }

    public static final class FOLDERS {
        public static final String ROOT = "control.json";
        public static final String GENERAL = "etiquetas.json";
        public static final String HISTORY = "plantilla.json";
        public static final String NEW = "plantilla.json";
        public static final String FINISHED = "plantilla.json";

        public static final String ROOT_FOLDER = "ftp_dir";
        public static String GENERAL_FOLDER = "General";
        private String HYSTORY_FOLDER = "Historico";
        private String NEW_FOLDER = "Nuevas";
        private String FINICHED_FOLDER = "Terminadas";
    }
}
