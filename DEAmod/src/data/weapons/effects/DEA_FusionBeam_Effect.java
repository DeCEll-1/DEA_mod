package data.weapons.effects;

import com.fs.starfarer.C;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import data.helper.DEA_DamageBaseClass;
import data.helper.DEA_Helper;
import data.helper.DEA_Logger;
import data.helper.DEA_RenderHelper;
import data.helper.UNUSED.kt.DEA_OpenGLHelper;
import data.plugins.DEA_CombatLayeredRenderingPlugin;
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
import static data.helper.DEA_Statics.*;


public class DEA_FusionBeam_Effect extends BaseCombatLayeredRenderingPlugin implements BeamEffectPlugin {

    public static DEA_DamageBaseClass DEA_DamageBaseClass_DEA_Hopelesness = new DEA_DamageBaseClass();
    public IntervalUtil timer = new IntervalUtil(1.9f, 1.9f);

    public boolean doOnce = true;
    public boolean doOnce2 = true;

    public ShipAPI ship;
    public Color shipColor;
    public static float chargeUp = 0f;

    //dont mind the shit load of comments :alpha:
    public void advance(float amount, CombatEngineAPI engine, BeamAPI beam) {
        try {


            if (doOnce) {
                this.ship = beam.getSource();
                this.shipColor = this.ship.getSpriteAPI().getAverageColor();
                chargeUp = 0f;
                DEA_DamageBaseClass_DEA_Hopelesness.DamageType = DamageType.ENERGY;
                DEA_DamageBaseClass_DEA_Hopelesness.EMPDamage = 500f;
                DEA_DamageBaseClass_DEA_Hopelesness.Damage = 9500f;
                DEA_DamageBaseClass_DEA_Hopelesness.DamagingExplosionSpec = new DamagingExplosionSpec(
                        0f,
                        50f,
                        50f,
                        DEA_DamageBaseClass_DEA_Hopelesness.Damage,
                        DEA_DamageBaseClass_DEA_Hopelesness.Damage,
                        CollisionClass.HITS_SHIPS_AND_ASTEROIDS,
                        CollisionClass.NONE,
                        0f,
                        0f,
                        0f,
                        0,
                        shipColor,
                        shipColor
                );
                beam.setCoreColor(this.shipColor);
                beam.setFringeColor(this.shipColor);
                beam.setWidth(0f);
                doOnce = false;

            }

            //tbh fuck render pluging standalone opengl is gigachad
//            this.render(getActiveLayers().iterator().next(), Global.getCombatEngine().getViewport());
//            DEA_RenderHelper.DEA_DrawLine(ship.getLocation(), beam.getWeapon().getLocation());
//            DEA_CombatLayeredRenderingPlugin dea_combatLayeredRenderingPlugin = new DEA_CombatLayeredRenderingPlugin();
//
//            ViewportAPI viewport = Global.getCombatEngine().getViewport();
//            Global.getCombatEngine().addLayeredRenderingPlugin(dea_combatLayeredRenderingPlugin);
//
//            dea_combatLayeredRenderingPlugin.render(CombatEngineLayers.ABOVE_SHIPS_AND_MISSILES_LAYER,viewport);


//            Vector2f from = ship.getHullSpec().getAllWeaponSlotsCopy().get(random.nextInt(ship.getHullSpec().getAllWeaponSlotsCopy().size())).computePosition(ship);//random slots in the ship


//            Vector2f to = dea_helper.DEA_EndOfBeamGiver(beam);//get the hit place

//            Vector2f to = beam.getWeapon().getLocation();

            ship.getExactBounds().update(ship.getLocation(), ship.getFacing());

//            from = CollisionUtils.getNearestPointOnBounds(ship.getLocation(), ship);

//            for (int i = 0; i < 20; i++) {

//            dea_helper.DEA_SpawnEMPArcsBetweenShipsBounds(ship, 4f, shipColor, shipColor);


            DEA_SpawnEMPArcVisual(DEA_GetRandomBoundLocation(this.ship), beam.getWeapon().getLocation(), chargeUp * 15f, this.shipColor, this.shipColor);

            Global.getCombatEngine().maintainStatusForPlayerShip(
                    ship.getId(),
                    "graphics/icons/hullmods/Test_Image.png",
                    "Debug number:",
                    String.valueOf(chargeUp),
                    false
            );

            timer.advance(amount);

            chargeUp += amount;

            if (doOnce2 && chargeUp >= 0.9f) {
                Global.getSoundPlayer().playSound(
                        "DEA_JustKillYourself",
                        1f,
                        1f,
                        beam.getWeapon().getLocation(),
                        ship.getVelocity()
                );
                doOnce2 = false;
            }

            if (timer.intervalElapsed()) {
                beam.getDamageTarget();
//                DEA_SpawnEMPArcLineVisual(beam.getWeapon().getLocation(), beam.getWeapon().getCurrAngle(), (int) beam.getLength(), 4f, 50, this.shipColor, this.shipColor);


                CombatEntityAPI target = beam.getDamageTarget();


//                DEA_SpawnEMPArcLineDamaging(
//                        beam.getWeapon().getLocation(),
//                        beam.getWeapon().getCurrAngle(),
//                        (int) beam.getLength(),
//                        4f,
//                        50f,
//                        new DEA_DamageBaseClass(3000f, 5000f, DamageType.ENERGY),
//                        this.ship,
//                        this.shipColor,
//                        this.shipColor
//                );


                DEA_SpawnEMPArcLineDamaging(
                        beam.getWeapon().getLocation(),
                        beam.getWeapon().getCurrAngle(),
                        (int) beam.getLength(),
                        100f,
                        50f,
                        DEA_DamageBaseClass_DEA_Hopelesness,
                        ship,
                        shipColor,
                        shipColor
                );

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

    @Override
    public void render(CombatEngineLayers layer, ViewportAPI viewport) {
    }

}
