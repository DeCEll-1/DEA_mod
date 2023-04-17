package data.hullmods.Karabasan;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;

public class DEA_KarabasanHarpoonModification extends BaseHullMod {

    @Override
    public String getUnapplicableReason(ShipAPI ship) {
        return "Only one missile modification can be installed at once";
    }

    @Override
    public boolean isApplicableToShip(ShipAPI ship) {
        if (ship.getVariant().getHullMods().contains("DEA_KarabasanResonatorModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanRiftModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanProximyModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanReaperModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanAtroposModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanAMSRMModification") || ship.getVariant().getHullMods().contains("DEA_KarabasanSabotModification")) return false;
        return true;
    }

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getSystemRegenBonus().modifyFlat(id, 0.025f);
        stats.getSystemUsesBonus().modifyFlatAlways(id, -3f, "");

    }
}
