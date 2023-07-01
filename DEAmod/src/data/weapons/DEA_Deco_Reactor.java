package data.weapons;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.util.IntervalUtil;
import data.helper.*;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicFakeBeam;

import java.awt.*;
import java.util.Random;

import static data.helper.DEA_Helper.DEA_Rainbow;
import static data.helper.DEA_Statics.random;
import static data.helper.DEA_EMPArcHelper.DEA_SpawnDamagingEMPArc;


public class DEA_Deco_Reactor implements EveryFrameWeaponEffectPlugin {


    public static Vector2f from;

    public static DEA_DamageBaseClass DEA_Penteract_DamageBaseClass = new DEA_DamageBaseClass();

    public boolean runOnce = true;

    public static ShipAPI THETHING;
    public static String IDOfTheLastShip;

    public Color shipColor;

    public IntervalUtil timer = new IntervalUtil(1f, 1f);

    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon) {
        try {
            if (runOnce) {
                DEA_Penteract_DamageBaseClass.DamageType = DamageType.ENERGY;
                DEA_Penteract_DamageBaseClass.Damage = 20f;
                DEA_Penteract_DamageBaseClass.EMPDamage = 70f;

                this.shipColor = weapon.getShip().getSpriteAPI().getAverageColor();
            }


            timer.advance(amount);
            for (int i = 0; i < 200; i++) {
                float BeamAngle = (float) random.nextInt(360);
                from = MathUtils.getPointOnCircumference(weapon.getLocation(), (float) random.nextInt(29), BeamAngle);
                MakeParticle(amount, engine, weapon, from, BeamAngle);

//                weapon.getShip().getShield().setRingColor(dea_helper.DEA_RainbowWithAlpha(120));
//                weapon.getShip().getShield().setInnerColor(dea_helper.DEA_RainbowWithAlpha(120));
            }
            boolean IsNearSomething = false;


            for (CombatEntityAPI s : CombatUtils.getEntitiesWithinRange(weapon.getLocation(), 1500f)) {//those are for checking if they are near anything but it lags more than without it rn so
                IsNearSomething = false;
                if (s instanceof ShipAPI) {

                    if (!((ShipAPI) s).getId().equals(weapon.getShip().getId())) {
                        if (((ShipAPI) s).isAlive()) {
//                            DEA_Logger.DEA_log(this.getClass(), ((ShipAPI) s).getId() + " - ", weapon.getShip().getId());

                            THETHING = (ShipAPI) s;
                            IsNearSomething = true;
                            break;
                        }
                    }


                }
            }

            if (IsNearSomething) {

                DEA_EMPArcHelper.DEA_SpawnDamagingEMPArc(
                        weapon.getShip(),
                        from,
                        THETHING,
                        DEA_Penteract_DamageBaseClass,
                        1500f,
                        "",
                        4f,
                        shipColor,
                        shipColor
                );

                IDOfTheLastShip = ((ShipAPI) THETHING).getId();
            }
        } catch (Exception ex) {
            return;
        }

    }

    public void MakeParticle(float amount, CombatEngineAPI engine, WeaponAPI weapon, Vector2f from, float BeamAngle) {

        DEA_RenderHelper.DEA_DrawLine(from, MathUtils.getPointOnCircumference(from, 10f, BeamAngle - 90f), shipColor);

//        MagicFakeBeam.spawnFakeBeam(
//                engine,
//                from,
//                1f,
//                BeamAngle - 90f,
//                4,
//                0.1f,
//                0.2f,
//                100,
//                weapon.getShip().getSpriteAPI().getAverageColor(),
//                weapon.getShip().getSpriteAPI().getAverageColor(),
//                0f,
//                DamageType.ENERGY,
//                0f,
//                weapon.getShip()
//        );


    }


}
