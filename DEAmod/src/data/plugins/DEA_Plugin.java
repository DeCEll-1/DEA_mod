package data.plugins;

import DEA.helper.DEAlib_Logger;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class DEA_Plugin extends BaseModPlugin {
    @Override
    public void onApplicationLoad() {
        StringBuilder errText = new StringBuilder("    *-*   ");
        try {
//2hou
            if (Global.getSettings().getBoolean("2houBadApplePlayOnDEA_Boundless")) {
                for (int i = 0; i < 6573; i++) {
                    int amountOfZeros = 4 - String.valueOf(i).length();


                    StringBuilder fileName = new StringBuilder("a/2hou/001_00");

                    for (int s = 0; s < amountOfZeros; s++) {

                        fileName.append("0");

                    }

                    fileName.append(i);

                    fileName.append(".jpg");

                    Global.getSettings().loadTexture(fileName.toString());

                }

            }


            errText.append("DEA_Logger.DEA_log(DEA_Plugin.class, \"loadedDEA\");");
            DEAlib_Logger.log(DEA_Plugin.class, "loadedDEA");
            errText.append("DEA_Logger.DEA_log(DEA_Plugin.class, \"loadedDEA\");    *-*    worked");
        } catch (Exception ex) {
            DEAlib_Logger.log(DEA_Plugin.class, "DEA_ERROR" + errText + "\n ERROR MESSAGE: " + ex.getMessage());
        }


    }

    @Override
    public void onGameLoad(boolean newGame) {

    }
}
