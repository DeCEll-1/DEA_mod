package data.plugins;

import VeB.helper.VeBlib_Logger;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

import java.io.IOException;

public class DEA_Plugin extends BaseModPlugin {
    @Override
    public void onApplicationLoad() {

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

                    try {
                        Global.getSettings().loadTexture(fileName.toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

            }


            VeBlib_Logger.log(DEA_Plugin.class, "loadedDEA");


    }

    @Override
    public void onGameLoad(boolean newGame) {

    }
}
