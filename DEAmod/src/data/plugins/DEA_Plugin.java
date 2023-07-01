package data.plugins;

import com.fs.starfarer.api.BaseModPlugin;
import data.helper.DEA_Logger;

public class DEA_Plugin extends BaseModPlugin {
    @Override
    public void onApplicationLoad() throws Exception {
        DEA_Logger.DEA_log(DEA_Plugin.class,"loadedDEA");
    }

    @Override
    public void onNewGame() {

    }
}
