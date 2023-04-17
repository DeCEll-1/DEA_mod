package data.hullmods.Karabasan;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class DEA_KarabasanHiddenHullmod extends BaseHullMod {

    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        if (stats.getVariant().getHullMods().contains("missleracks")) {
            stats.getSystemUsesBonus().modifyMult(id, 2f);
        }
        ShipAPI ship = null;

        if (stats.getEntity() instanceof ShipAPI) {
//
            ship = (ShipAPI) stats.getEntity();

            if (!(ship.getVariant().getHullMods().contains("DEA_KarabasanReaperModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanAtroposModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanSabotModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanAMSRMModification"))) {
                stats.getSystemRegenBonus().modifyFlatAlways(id, 0.033f, "");
            }


        }


    }

}
