package data.shipsystems.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

import java.awt.*;

public class DEA_ShipTimeManipulator extends BaseShipSystemScript {

    //only player can buff themselves and allies othervise its just like entropy

    public static float EnemyShipTimeMult = 0.5f;

    public static float AllyShipTimeMult = 3f;

    public static float SelfShipTimeMult = 3f;

    public static final Color JITTER_COLOR = new Color(180, 180, 180, 201);
    public static final Color JITTER_UNDER_COLOR = new Color(98, 98, 98, 155);


    public void apply(MutableShipStatsAPI stats, String id, ShipSystemStatsScript.State state, float effectLevel) {

        ShipAPI ship = null;

        boolean player = false;

        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
            if (ship == Global.getCombatEngine().getPlayerShip()) {
                player = true;
            }
        } else {
            return;
        }

        if (ship.getShipTarget() != null) {

            float jitterLevel = effectLevel;

            float jitterRangeBonus = 0;

            float maxRangeBonus = 10f;

            if (state == State.IN) {
                jitterLevel = effectLevel / (1f / ship.getSystem().getChargeUpDur());
                if (jitterLevel > 1) {
                    jitterLevel = 1;
                }
            } else if (state == State.ACTIVE) {
                jitterLevel = 1f;
                jitterRangeBonus = maxRangeBonus;
            } else if (state == State.OUT) {
                jitterRangeBonus = jitterLevel * maxRangeBonus;
            }


            jitterLevel = (float) Math.sqrt(jitterLevel);
            effectLevel *= effectLevel;

            ship.setJitter(this, JITTER_COLOR, jitterLevel, 3, 0);
            ship.setJitterUnder(this, JITTER_UNDER_COLOR, jitterLevel, 3, 0);

            float shipTimeMult = 1f + (SelfShipTimeMult - 1f) * effectLevel;

            stats.getTimeMult().modifyMult(id, shipTimeMult);
            if (player) {
                Global.getCombatEngine().getTimeMult().modifyMult(id, 1f / shipTimeMult);
            } else {
                Global.getCombatEngine().getTimeMult().unmodifyMult(id);
            }

        } else if (ship.getShipTarget().isAlly()) {

            ShipAPI targetship = ship.getShipTarget();

            float jitterLevel = effectLevel;

            float jitterRangeBonus = 0;

            float maxRangeBonus = 10f;

            if (state == State.IN) {
                jitterLevel = effectLevel / (1f / targetship.getSystem().getChargeUpDur());
                if (jitterLevel > 1) {
                    jitterLevel = 1;
                }
            } else if (state == State.ACTIVE) {
                jitterLevel = 1f;
                jitterRangeBonus = maxRangeBonus;
            } else if (state == State.OUT) {
                jitterRangeBonus = jitterLevel * maxRangeBonus;
            }

            jitterLevel = (float) Math.sqrt(jitterLevel);
            effectLevel *= effectLevel;

            targetship.setJitter(this, JITTER_COLOR, jitterLevel, 3, 0);
            targetship.setJitterUnder(this, JITTER_UNDER_COLOR, jitterLevel, 3, 0);

            float shipTimeMult = 1f + (AllyShipTimeMult - 1f) * effectLevel;

            stats.getTimeMult().modifyMult(id, shipTimeMult);
            if (!player) {
                Global.getCombatEngine().getTimeMult().unmodifyMult(id);
            }
        } else{
            ShipAPI targetship = ship.getShipTarget();

            float jitterLevel = effectLevel;

            float jitterRangeBonus = 0;

            float maxRangeBonus = 10f;

            if (state == State.IN) {
                jitterLevel = effectLevel / (1f / targetship.getSystem().getChargeUpDur());
                if (jitterLevel > 1) {
                    jitterLevel = 1;
                }
            } else if (state == State.ACTIVE) {
                jitterLevel = 1f;
                jitterRangeBonus = maxRangeBonus;
            } else if (state == State.OUT) {
                jitterRangeBonus = jitterLevel * maxRangeBonus;
            }

            jitterLevel = (float) Math.sqrt(jitterLevel);
            effectLevel *= effectLevel;

            targetship.setJitter(this, JITTER_COLOR, jitterLevel, 3, 0);
            targetship.setJitterUnder(this, JITTER_UNDER_COLOR, jitterLevel, 3, 0);

            float shipTimeMult = 1f + (EnemyShipTimeMult - 1f) * effectLevel;

            stats.getTimeMult().modifyMult(id, shipTimeMult);
            if (!player) {
                Global.getCombatEngine().getTimeMult().unmodifyMult(id);
            }
        }


    }

    public boolean isUsable(ShipSystemAPI system, ShipAPI ship) {
        if (ship != null && ship.isAlive() && ship.isPhased() && (ship.getFluxTracker().getCurrFlux() - ship.getFluxTracker().getHardFlux()) != 0) {
            return true;
        }
        return false;
//        return ship != null && ship.isAlive() && ship.isPhased();
    }

}
