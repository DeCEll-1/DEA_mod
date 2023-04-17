package data.hullmods.Karabasan;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import data.scripts.util.MagicIncompatibleHullmods;

public class DEA_KarabasanSabotModification extends BaseHullMod {

    @Override
    public String getUnapplicableReason(ShipAPI ship) {
        return "Only one missile modification can be installed at once";
    }

    @Override
    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getVariant().getHullMods().contains("DEA_KarabasanResonatorModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanHarpoonModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanRiftModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanProximyModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanReaperModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanAtroposModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanAMSRMModification")) return false;
        return true;
    }

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getSystemRegenBonus().modifyFlat(id, 0.033f);
        stats.getSystemUsesBonus().modifyFlatAlways(id, -2f, "");


//        ShipAPI ship = null;
//        if (stats.getEntity() instanceof ShipAPI) {
//
//            ship = (ShipAPI) stats.getEntity();
//
//            ship.getSystem().setCooldown(0.0333f);
//
//        }
    }

//    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
//        ProjectileType = "sabot";
////        ShipAPI ship = null;
////        if (stats.getEntity() instanceof ShipAPI) {
////            ship = (ShipAPI) stats.getEntity();
////
////
////            ship.setCustomData("_DEA_KarabasanMissileModifier", "sabot");
////        }
//    }
}
