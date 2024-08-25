package lk.ijse.gdse.springboot.notetaker.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createNoteId(){
        return "NOTE "+UUID.randomUUID().toString();
    }

}
