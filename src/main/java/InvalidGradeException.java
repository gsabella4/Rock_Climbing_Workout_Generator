public class InvalidGradeException extends Exception{

    public InvalidGradeException(){
        super();
    }
    public InvalidGradeException(String message){
        super(message);
    }
    public InvalidGradeException(String message, Exception cause){
        super(message, cause);
    }
}
