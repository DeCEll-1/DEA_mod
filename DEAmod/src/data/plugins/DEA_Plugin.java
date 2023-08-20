package data.plugins;

import DEA.helper.DEAlib_Logger;
import com.fs.starfarer.api.BaseModPlugin;

public class DEA_Plugin extends BaseModPlugin {
    @Override
    public void onApplicationLoad() {
        StringBuilder errText = new StringBuilder("    *-*   ");
        try {
            errText.append("DEA_Logger.DEA_log(DEA_Plugin.class, \"loadedDEA\");");
            DEAlib_Logger.DEA_log(DEA_Plugin.class, "loadedDEA");
            errText.append("DEA_Logger.DEA_log(DEA_Plugin.class, \"loadedDEA\");    *-*    worked");
        } catch (Exception ex) {
            DEAlib_Logger.DEA_log(DEA_Plugin.class, "DEA_ERROR" + errText + "\n ERROR MESSAGE: " + ex.getMessage());
        }
    }

    @Override
    public void onGameLoad(boolean newGame) {

    }
}
