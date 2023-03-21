package data.shipsystems.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript.State;

import java.awt.Color;

public class DEA_ZaWarudo extends BaseShipSystemScript {

    public static final float MAX_TIME_MULT = 8f;
    public static final float MIN_TIME_MULT = 0.1f;

    public static final Color JITTER_COLOR = new Color(90, 200, 255, 50);
    public static final Color JITTER_UNDER_COLOR = new Color(90, 165, 255, 155);

    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        ShipAPI ship = null;
        boolean player = false;
        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
            player = ship == Global.getCombatEngine().getPlayerShip();
            id = id + "_" + ship.getId();
        } else {
            return;
        }

        float jitterLevel = effectLevel;
        float jitterRangeBonus = 0;
        float maxRangeBonus = 10f;

        if (state == State.IN) {

            jitterLevel = effectLevel / (1f / ship.getSystem().getChargeUpDur());

            if (jitterLevel > 1) {
                jitterLevel = 1;
            }

            jitterRangeBonus = jitterLevel * maxRangeBonus;
        } else if (state == State.ACTIVE) {

            jitterLevel = 1f;
            jitterRangeBonus = maxRangeBonus;

        } else if (state == State.OUT) {

            jitterRangeBonus = jitterLevel * maxRangeBonus;

        }

        jitterLevel = (float) Math.sqrt(jitterLevel);
        effectLevel *= effectLevel;

        ship.setJitter(this, JITTER_COLOR, jitterLevel, 3, 0, 0 + jitterRangeBonus);
        ship.setJitterUnder(this, JITTER_UNDER_COLOR, jitterLevel, 25, 0f, 7f + jitterRangeBonus);

        float shipTimeMult = 1f + (MAX_TIME_MULT - 1f) * effectLevel;
        stats.getTimeMult().modifyMult(id, shipTimeMult);
        if (player) {
            Global.getCombatEngine().getTimeMult().modifyMult(id, 1f / shipTimeMult);
        } else {
            Global.getCombatEngine().getTimeMult().unmodifyMult(id);
        }
        ship.getEngineController().fadeToOtherColor(this, JITTER_COLOR, new Color(0, 0, 0, 0), effectLevel, 0.5f);
        ship.getEngineController().extendFlame(this, -0.25f, -0.25f, -0.25f);
    }

    public void unapply(MutableShipStatsAPI stats, String id) {
        ShipAPI ship = null;
        boolean player = false;
        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
            player = ship == Global.getCombatEngine().getPlayerShip();
            id = id + "_" + ship.getId();
        } else {
            return;
        }
        Global.getCombatEngine().getTimeMult().unmodify(id);
        stats.getTimeMult().unmodify(id);
    }

    public ShipSystemStatsScript.StatusData getStatusData(int index, State state, float effectLevel) {
        float shipTimeMult = 1f + (MAX_TIME_MULT - 1f) * effectLevel;
        if (index == 0) {
            return new ShipSystemStatsScript.StatusData("ZAWARUDO \n" + shipTimeMult, false);
        }
        return null;
    }
}
