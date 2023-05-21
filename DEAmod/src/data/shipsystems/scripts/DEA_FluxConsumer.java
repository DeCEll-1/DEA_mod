package data.shipsystems.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;

import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import org.lazywizard.lazylib.combat.AIUtils;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

public class DEA_FluxConsumer extends BaseShipSystemScript {

    public static float FluxRatio = 0.5f;

    public static float speed;

//    public static Color ShipColor;

    //from tomato
    private final IntervalUtil interval = new IntervalUtil(0.5f, 0.5f); // chooses a random interval between 0.1 and 0.5 seconds

    public void apply(MutableShipStatsAPI stats, String id, ShipSystemStatsScript.State state, float EffectLevel) {

        ShipAPI ship = null;

        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
//            if (ship == Global.getCombatEngine().getPlayerShip()) {
//                player = true;
//                Global.getCombatEngine().maintainStatusForPlayerShip(
//                        id,
//                        "graphics/icons/hullmods/Test_Image.png",
//                        "Debug number:",
//                        Float.toString(EffectLevel),
//                        false
//                        );
//            }
        } else {
            return;
        }

        if (state == State.IN) {
            speed = (ship.getFluxTracker().getCurrFlux() - ship.getFluxTracker().getHardFlux()) * Global.getCombatEngine().getElapsedInLastFrame() * 2;
//            ShipColor = ship.getSpriteAPI().getColor();
        }

        if (state == State.ACTIVE) {
            FluxTrackerAPI shipFluxTracker = ship.getFluxTracker();
//
//            float shipSoftFlux = shipFluxTracker.getCurrFlux() - shipFluxTracker.getHardFlux();
//
//            shipFluxTracker.setCurrFlux(0f);
//
//            shipFluxTracker.increaseFlux(shipSoftFlux * FluxRatio, true);

//shipFluxTracker.getCurrFlux()-shipFluxTracker.getHardFlux();//soft flux


            //tomatos code // thanks to him for showing me the getElapsedInLastFrame its epic :alpha:
            shipFluxTracker.decreaseFlux(speed);
            shipFluxTracker.increaseFlux(speed * FluxRatio, true);


            //from tomato
            float amount = Global.getCombatEngine().getElapsedInLastFrame();
            interval.advance(amount);
            if (interval.intervalElapsed()) {
                Global.getCombatEngine().addSmokeParticle(ship.getLocation(), ship.getVelocity(), ship.getSpriteAPI().getHeight(), 255f, 3, new Color(180, 100, 255, 220));
            }

        }
//
//        if (state == State.OUT) {
//            ship.getSpriteAPI().setColor(ShipColor);
//        }

    }


    //this doesnt fucking works and gives null error
//    public void unapply(MutableShipStatsAPI stats, String id) {
//
//        ShipAPI ship = null;
//        boolean player = false;
//        if (stats.getEntity() instanceof ShipAPI) {
//            ship = (ShipAPI) stats.getEntity();
//            if (ship == Global.getCombatEngine().getPlayerShip()) {
//                player = true;
//            }
//        } else {
//            return;
//        }
//
//        ship.getSpriteAPI().setColor(ShipColor);
//
//    }

    //from tomato for making it only useable while phasing
    @Override
    public boolean isUsable(ShipSystemAPI system, ShipAPI ship) {
        if (ship != null && ship.isAlive() && ship.isPhased() && (ship.getFluxTracker().getCurrFlux() - ship.getFluxTracker().getHardFlux()) != 0) {
            return true;
        }
        return false;
//        return ship != null && ship.isAlive() && ship.isPhased();
    }

    @Override
    public String getInfoText(ShipSystemAPI system, ShipAPI ship) {
        //from mine strike stats
        if ((ship.getFluxTracker().getCurrFlux() - ship.getFluxTracker().getHardFlux()) == 0) {
            return "NO SOFT FLUX";
        } else if (!ship.isPhased()) {
            return "NOT PHASED";
        } else if (system.getState() != ShipSystemAPI.SystemState.IDLE) {
            return "NOT READY";
        } else {
            return "READY";
        }
    }

}
