package foolkey.tool;

/**
 * Created by geyao on 2017/5/1.
 */
public class TokenCreator {

    public static String createToken(String userName, String passWord){
        return userName + passWord;
    }
}
