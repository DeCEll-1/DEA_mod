package data.shipsystems.scripts;


import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

public class DEA_FoePhaseTeleporter extends BaseShipSystemScript {

    public static Object KEY_SHIP = new Object();
    public static Object KEY_TARGET = new Object();

    protected static float RANGE = 1500f;

    public static final Color JITTER_COLOR = new Color(180, 180, 180, 201);
    public static final Color JITTER_UNDER_COLOR = new Color(98, 98, 98, 155);


    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
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
        float maxRangeBonus = 25f;

        float jitterLevel = effectLevel;

        float jitterRangeBonus = 0;

        if (state == State.IN) {
            jitterLevel = effectLevel / (1f / ship.getSystem().getChargeUpDur());
            if (jitterLevel > 1) {
                jitterLevel = 1;
            }

            if (effectLevel >= 1f) {

                Vector2f targetV = ship.getMouseTarget();

                ShipAPI target = ship.getShipTarget();

                target.getLocation().set(targetV);
            }
//            ShipAPI target = this.findTarget(ship);


        } else if (state == State.ACTIVE) {
            jitterLevel = 1f;
            jitterRangeBonus = maxRangeBonus;
        } else if (state == State.OUT) {
            jitterRangeBonus = jitterLevel * maxRangeBonus;
        }


        ship.setJitter(this, JITTER_COLOR, jitterLevel, 3, 0);
        ship.setJitterUnder(this, JITTER_UNDER_COLOR, jitterLevel, 3, 0);
    }

    protected ShipAPI findTarget(ShipAPI ship) {
        float range = getMaxRange(ship);
        boolean player = false;
        if (ship == Global.getCombatEngine().getPlayerShip()) {
            player = true;
        }

        return null;
    }

    public static float getMaxRange(ShipAPI ship) {
        return ship.getMutableStats().getSystemRangeBonus().computeEffective(RANGE);
    }

}
