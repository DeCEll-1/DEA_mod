package data.shipsystems.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;

import java.awt.*;

public class DEA_TemporalShield extends BaseShipSystemScript {
    public static float DAMAGE_MULT = 0.9f;

    public static final float MAX_TIME_MULT = 2f;
    public static final float MIN_TIME_MULT = 0.1f;

    public static Color shipRingColor;

    public static float shipArc;

    public static final Color JITTER_COLOR = new Color(90, 200, 255, 50);
    public static final Color JITTER_UNDER_COLOR = new Color(90, 165, 255, 155);

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

        shipArc = ship.getShield().getArc();

        shipRingColor = ship.getShield().getRingColor();

        ship.getShield().setRingColor(new Color(255, 0, 125, 1));

        ship.getShield().setArc(360);
        stats.getShieldDamageTakenMult().modifyMult(id, 1f - DAMAGE_MULT * effectLevel);
        stats.getShieldUpkeepMult().modifyMult(id, 0f);
        stats.getShieldTurnRateMult().modifyMult(id, 1.75f);
        stats.getShieldArcBonus().modifyMult(id, 1f);
        if (ship == Global.getCombatEngine().getPlayerShip()) {
            float shipTimeMult = 1f + (MAX_TIME_MULT - 1f) * effectLevel;
            Global.getCombatEngine().maintainStatusForPlayerShip(id, "graphics/icons/hullmods/Test_Image.png", "Temporal Shield, Time Flow Altered:",
                    Float.toString(shipTimeMult),
                    false);
        }

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

        float shipTimeMult = 1f + (MAX_TIME_MULT - 1f) * effectLevel;

        stats.getTimeMult().modifyMult(id, shipTimeMult);
        if (player) {
            Global.getCombatEngine().getTimeMult().modifyMult(id, 1f / shipTimeMult);
        } else {
            Global.getCombatEngine().getTimeMult().unmodifyMult(id);
        }


    }

    public void unapply(MutableShipStatsAPI stats, String id) {

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
        Global.getCombatEngine().getTimeMult().unmodify(id);

        stats.getShieldArcBonus().unmodify(id);
        stats.getShieldDamageTakenMult().unmodify(id);
        stats.getShieldTurnRateMult().unmodify(id);
        stats.getShieldUnfoldRateMult().unmodify(id);
        stats.getShieldUpkeepMult().unmodify(id);

        ship.getShield().setRingColor(shipRingColor);
        ship.getShield().setActiveArc(ship.getShield().getArc());
    }

    public ShipSystemStatsScript.StatusData getStatusData(int index, ShipSystemStatsScript.State state, float effectLevel) {

//        if (index == 0) {
//            return new ShipSystemStatsScript.StatusData("Current shield absorbtion:\n" + effectLevel, false);
//        }
        return null;
    }


}
