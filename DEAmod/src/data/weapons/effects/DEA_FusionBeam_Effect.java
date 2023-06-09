package data.weapons.effects;

import com.fs.starfarer.C;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import data.helper.DEA_Helper;
import data.helper.DEA_Logger;
import data.weapons.DEA_Deco_Reactor;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.lazywizard.lazylib.CollisionUtils;
import org.lazywizard.lazylib.LazyLib;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicFakeBeam;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

import static data.helper.DEA_BoundsHelper.DEA_GetRandomBoundLocation;
import static data.helper.DEA_EMPArcHelper.*;
import static data.helper.DEA_Statics.dea_helper;
import static data.helper.DEA_Statics.random;


public class DEA_FusionBeam_Effect implements BeamEffectPlugin {

    public IntervalUtil timer = new IntervalUtil(0f, 1f);

    //dont mind the shit load of comments :alpha:
    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {
        try {

            ShipAPI ship = beam.getSource();

//            Vector2f from = ship.getHullSpec().getAllWeaponSlotsCopy().get(random.nextInt(ship.getHullSpec().getAllWeaponSlotsCopy().size())).computePosition(ship);//random slots in the ship

            Color shipColor = ship.getSpriteAPI().getAverageColor();

//            Vector2f to = dea_helper.DEA_EndOfBeamGiver(beam);//get the hit place

//            Vector2f to = beam.getWeapon().getLocation();

            ship.getExactBounds().update(ship.getLocation(), ship.getFacing());

//            from = CollisionUtils.getNearestPointOnBounds(ship.getLocation(), ship);

//            for (int i = 0; i < 20; i++) {

//            dea_helper.DEA_SpawnEMPArcsBetweenShipsBounds(ship, 4f, shipColor, shipColor);


            DEA_SpawnEMPArcVisual(DEA_GetRandomBoundLocation(ship), beam.getWeapon().getLocation(), 4f, shipColor, shipColor);

            beam.setCoreColor(shipColor);

            beam.setFringeColor(shipColor);

            timer.advance(amount);

            if (timer.intervalElapsed()) {
                beam.getDamageTarget();
                DEA_SpawnEMPArcLineVisual(beam.getWeapon().getLocation(), beam.getWeapon().getCurrAngle(), (int) beam.getLength(), 4f, 50, shipColor, shipColor);


                CombatEntityAPI target = beam.getDamageTarget();


                for (int i = 0; i < random.nextInt(30) + 10; i++) {
                    Vector2f location = MathUtils.getPointOnCircumference(target.getLocation(), target.getCollisionRadius() + 100f, random.nextInt(360));
                    DEA_SpawnDamagingEMPArc(
                            beam.getSource(),
                            location,
                            target,
                            DamageType.ENERGY,
                            300f,
                            500f,
                            500f,
                            "",
                            4f,
                            shipColor,
                            shipColor
                    );

                }

                DEA_SpawnEMPArcVisual(
                        new Vector2f(target.getLocation().x, 0),
                        target.getLocation(),
                        4f,
                        shipColor,
                        shipColor
                );


                //                dea_helper.DEA_SpawnEMPArcLineDamaging(
//                        beam.getWeapon().getLocation(),
//                        beam.getWeapon().getCurrAngle(),
//                        (int) beam.getLength(),
//                        4f,
//                        50f,
//                        beam.getDamageTarget(),
//                        ship,
//                        DamageType.ENERGY,
//                        200f,
//                        500f,
//                        "",
//                        shipColor,
//                        shipColor
//                );
            }


//            dea_helper.DEA_SpawnEMPArcAt(beam.getWeapon().getLocation(), dea_helper.DEA_EndOfBeamGiver(beam), 4f, shipColor, shipColor);

//            }


//            engine.spawnEmpArcVisual(
//                    DEA_Deco_Reactor.from,
//                    beam.getSource(),
//                    to,
////                    MathUtils.getPointOnCircumference(beam.getWeapon().getLocation(), (float) random.nextInt(29), random.nextInt(360)),
//                    beam.getSource(),
//                    0,
//                    dea_helper.DEA_RainbowWithAlpha(60),
//                    dea_helper.DEA_RainbowWithAlpha(255)
//            );


        } catch (Exception ex) {
            return;
        }
    }

}
