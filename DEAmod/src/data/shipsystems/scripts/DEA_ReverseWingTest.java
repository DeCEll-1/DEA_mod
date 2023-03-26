package data.shipsystems.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.FighterLaunchBayAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;


import java.util.Iterator;

public class DEA_ReverseWingTest {
    public static String RD_NO_EXTRA_CRAFT = "rd_no_extra_craft";
    public static String RD_FORCE_EXTRA_CRAFT = "rd_force_extra_craft";
    public static float EXTRA_FIGHTER_DURATION = 15.0F;
    public static float RATE_COST = 0.25F;


    public void apply(MutableShipStatsAPI stats, String id, ShipSystemStatsScript.State state, float effectLevel) {
        ShipAPI ship = null;
        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
            if (effectLevel == 1.0F) {
                float minRate = Global.getSettings().getFloat("minFighterReplacementRate");
                Iterator var8 = ship.getLaunchBaysCopy().iterator();

                while (var8.hasNext()) {
                    FighterLaunchBayAPI bay = (FighterLaunchBayAPI) var8.next();
                    if (bay.getWing() != null) {
                        float rate = Math.max(minRate, bay.getCurrRate() - RATE_COST);
                        bay.setCurrRate(rate);
                        bay.makeCurrentIntervalFast();
                        FighterWingSpecAPI spec = bay.getWing().getSpec();
                        int addForWing = getAdditionalFor(spec);
                        int maxTotal = spec.getNumFighters() + addForWing;
                        int actualAdd = maxTotal - bay.getWing().getWingMembers().size();
                        if (actualAdd > 0) {
                            bay.setFastReplacements(bay.getFastReplacements() + addForWing);
                            bay.setExtraDeployments(actualAdd);
                            bay.setExtraDeploymentLimit(maxTotal);
                            bay.setExtraDuration(EXTRA_FIGHTER_DURATION);
                        }
                    }
                }
            }

        }
    }

    public static int getAdditionalFor(FighterWingSpecAPI spec) {
        if (spec.hasTag(RD_NO_EXTRA_CRAFT) || spec.isBomber()) {
            return 0;
        } else {
            int size = spec.getNumFighters();
            if (size <= 3) {
                return 1;
            } else {
                return size <= 5 ? 2 : 3;
            }
        }
    }

    public void unapply(MutableShipStatsAPI stats, String id) {
    }

    public ShipSystemStatsScript.StatusData getStatusData(int index, ShipSystemStatsScript.State state, float effectLevel) {
        return null;
    }

    public boolean isUsable(ShipSystemAPI system, ShipAPI ship) {
        return true;
    }
}