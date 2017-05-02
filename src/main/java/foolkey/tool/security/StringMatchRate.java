package foolkey.tool.security;

/**
 * Created by geyao on 2017/5/2.
 */
public class StringMatchRate {

    public static double getMatchRate(String str1, String str2){
        int len1 = str1.length();
        int len2 = str2.length();
        if (len1 != len2)
            return 0.0;

        double param = 0;
        for (int i = 0; i < len1 ; i ++){
            if (str1.charAt(i) != str2.charAt(i))
                param++;
        }
        return (1 - param/len1);
    }
}
