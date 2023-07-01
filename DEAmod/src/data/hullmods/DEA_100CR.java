package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class DEA_100CR extends BaseHullMod {

    @Override
    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        try {
//            stats.getBaseCRRecoveryRatePercentPerDay().modifyMult(id, 9999f);
            stats.getMaxCombatReadiness().modifyMult(id, 999f);
        } catch (Exception ex) {
            return;
        }
    }

}
