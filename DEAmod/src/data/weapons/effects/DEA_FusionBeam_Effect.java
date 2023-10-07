package data.weapons.effects;

import VeB.helper.VeBlib_BoundsHelper;
import VeB.helper.VeBlib_EMPArcHelper;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;
import com.fs.starfarer.api.util.IntervalUtil;
import VeB.helper.helperHelperClasses.VeBlib_DamageBaseClass;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;

import static VeB.helper.VeBlib_EMPArcHelper.*;


public class DEA_FusionBeam_Effect extends BaseCombatLayeredRenderingPlugin implements BeamEffectPlugin {

    public static VeBlib_DamageBaseClass DEA_DamageBaseClass_DEA_Hopelesness = new VeBlib_DamageBaseClass();
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

            //tbh fuck render pluging standalone opengl is gigachad//mf
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

            VeBlib_EMPArcHelper.SpawnEMPArcsBetweenShipsBounds(ship, 4f, shipColor, shipColor);

//            for (Vector2f location : VeBlib_BoundsHelper.VeBlib_GetListOfBoundLocations(ship)) {
//
//                DEA_SpawnEMPArcVisual(location, beam.getWeapon().getLocation(), chargeUp * 15f, this.shipColor, this.shipColor);
//            }

            SpawnEMPArcVisual(VeBlib_BoundsHelper.GetRandomBoundLocation(this.ship), beam.getWeapon().getLocation(), chargeUp * 15f, this.shipColor, this.shipColor);

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


                SpawnEMPArcLineDamaging(
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
