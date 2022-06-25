package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EmojiParsers {

    public static int length;

    public static String[] EmojiName = {"Á÷º¹»Æ¶¹", "¼ÎÈ»-1", "¼ÎÈ»-2"};
    public static String[] oldChar = {"/liuhan/", "/jiaran-1/", "/jiaran-2/"};

    static {
        length = EmojiName.length;
    }

    public static String[] newChar =
            {
                    "<img src=\"https://img.gejiba.com/images/059465f0dbafda02d3d2a813fbc20334.png\" width=\"20\" height=\"20\">",
                    "<img src=\"https://img.gejiba.com/images/18ca18f6abfd1a5608ec939803607f61.th.jpg\" alt=\"18ca18f6abfd1a5608ec939803607f61.th.jpg\" border=\"0\">",
                    "<img src=\"https://img.gejiba.com/images/13dfbd2375c40fa20cb29458599c255a.th.jpg\" alt=\"13dfbd2375c40fa20cb29458599c255a.th.jpg\" border=\"0\">"
            };

    public static String Decode(String str){
        for(int i = 0; i < length; i++){
            str = str.replace(oldChar[i], newChar[i]);
        }
        return str;
    }
}
