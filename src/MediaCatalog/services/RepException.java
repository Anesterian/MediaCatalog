package MediaCatalog.services;
//class for error messages
public class RepException  extends Exception {
    public static final int ERROR_GET_DATA=0;
    public static final int ERROR_SET_DATA=1;
    public static final int ERROR_DELETE_DATA=2;
    public static final int ERROR_DATA_NOT_FOUND =404; // =)
    public static final int CONNECTON_ERROR=3;
    public int code;
    public RepException(int errCode){
        this.code = errCode;
    }
    public RepException(int errCode,String msg){
        super(msg);
        this.code = errCode;
    }

    public int getCode(){
        return code;
    };
}

