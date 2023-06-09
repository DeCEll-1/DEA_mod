package data.helper;

import com.fs.starfarer.api.Global;
import org.apache.log4j.Logger;

public class DEA_Logger {

    /**
     * logs stuff. <br/>
     * returns true if logged, othervise false
     */
    public static boolean DEA_log(Class myClass, String val1, String val2, String val3) {
        try {
            Global.getLogger(myClass).info(val1 + val2 + val3);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * logs stuff. <br/>
     * returns true if logged, othervise false
     */
    public static boolean DEA_log(Class myClass, String val1, String val2) {
        try {
            Global.getLogger(myClass).info(val1);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * logs stuff. <br/>
     * returns true if logged, othervise false
     */
    public static boolean DEA_log(Class myClass, String val1) {
        try {
            Global.getLogger(myClass).info(val1);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
