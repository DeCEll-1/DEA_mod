package data.weapons;

import VeB.helper.VeBlib_EMPArcHelper;
import VeB.helper.helperHelperClasses.VeBlib_DamageBaseClass;
import VeB.helper.render.VeBlib_RendererPlugin;
import VeB.helper.render.renderClassesFolder.VeBlib_LineData;
import VeB.helper.render.renderClassesFolder.VeBlib_LineWWidthData;
import VeB.helper.render.renderClassesFolder.VeBlib_RingData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.util.IntervalUtil;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicFakeBeam;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static VeB.helper.VeBlib_Statics.random;


public class DEA_Deco_Reactor implements EveryFrameWeaponEffectPlugin {


    public static Vector2f from;

    public static VeBlib_DamageBaseClass DEA_Penteract_DamageBaseClass = new VeBlib_DamageBaseClass();

    public boolean runOnce = true;

    public static ShipAPI THETHING;
    public static String IDOfTheLastShip;

    public Color shipColor;

    public IntervalUtil timer = new IntervalUtil(1f, 1f);

    private VeBlib_RendererPlugin plugin = null;


    private float currentAngle = 999;

    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon) {
        try {

            if (plugin == null) {
                plugin = new VeBlib_RendererPlugin();
                Global.getCombatEngine().addLayeredRenderingPlugin(plugin);
            }

            if (runOnce) {
                DEA_Penteract_DamageBaseClass.DamageType = DamageType.ENERGY;
                DEA_Penteract_DamageBaseClass.Damage = 20f;
                DEA_Penteract_DamageBaseClass.EMPDamage = 70f;

                this.shipColor = weapon.getShip().getSpriteAPI().getAverageColor();
            }

            List<VeBlib_LineWWidthData> VeBlib_lineDataList = new ArrayList<>();

            timer.advance(amount);
            if (!engine.isPaused()) {
                for (int i = 0; i < 200; i++) {
                    float BeamAngle = (float) random.nextInt(360);
                    from = MathUtils.getPointOnCircumference(weapon.getLocation(), (float) random.nextInt(29), BeamAngle);
                    VeBlib_lineDataList = MakeParticle(amount, engine, weapon, from, BeamAngle, VeBlib_lineDataList);


//                weapon.getShip().getShield().setRingColor(dea_helper.DEA_RainbowWithAlpha(120));
//                weapon.getShip().getShield().setInnerColor(dea_helper.DEA_RainbowWithAlpha(120));
                }
                plugin.LineWWidthInPlugin(VeBlib_lineDataList);
                plugin.ChangeRenderLayerInPlugin(CombatEngineLayers.ABOVE_PARTICLES);


                boolean IsNearSomething = false;


                for (CombatEntityAPI s : CombatUtils.getEntitiesWithinRange(weapon.getLocation(), 1500f)) {//those are for checking if they are near anything but it lags more than without it rn so
                    IsNearSomething = false;
                    if (s instanceof ShipAPI) {

                        if (!((ShipAPI) s).getId().equals(weapon.getShip().getId())) {
                            if (((ShipAPI) s).isAlive() && !((ShipAPI) s).isAlly()) {
//                            DEA_Logger.DEA_log(this.getClass(), ((ShipAPI) s).getId() + " - ", weapon.getShip().getId());

                                THETHING = (ShipAPI) s;
                                IsNearSomething = true;
                                break;
                            }
                        }


                    }
                }

                if (IsNearSomething) {

                    VeBlib_EMPArcHelper.SpawnDamagingEMPArc(
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

            }


//region s
//            Vector2f EyeCenter = weapon.getLocation();
//
//            float radius = 100f;
//
//
//            float enemyAngle = VectorUtils.getAngle(THETHING.getLocation(), EyeCenter) + 6f;
//
//            if (currentAngle == 999) {
//                currentAngle = weapon.getShip().getFacing();
//            }
//
//            if (currentAngle <= enemyAngle) {
//                currentAngle += 1f;
//            }
//
//            if (currentAngle >= enemyAngle) {
//
//
//                currentAngle -= 1f;
//            }


//                currentAngle = enemyAngle;

//                plugin.VeBlib_CircleInPlugin(DEA_DrawEye.DEA_DrawEyeFull(ship.getLocation(), 200f, 26, ship));//unneeded

//            List<VeBlib_RingData> DEA_Ring = new ArrayList<>();
//
//
//            VeBlib_RingData ring = new VeBlib_RingData(EyeCenter, 30, currentAngle, radius, 4f, new Color(255, 255, 255, 50));
//
//            DEA_Ring.add(ring);
//
//            plugin.VeBlib_Ring(DEA_Ring);
//
//            List<VeBlib_LineWWidthData> EyeCornea = new ArrayList<>();
//
//            EyeCornea.add(new VeBlib_LineWWidthData(
//                    MathUtils.getPointOnCircumference(EyeCenter, radius, currentAngle + 180),
//                    MathUtils.getPointOnCircumference(EyeCenter, radius, currentAngle + 12f + 180),
//                    4f,
//                    Color.RED
//            ));
//
//            EyeCornea.add(new VeBlib_LineWWidthData(
//                    MathUtils.getPointOnCircumference(EyeCenter, radius, currentAngle - 12f + 180),
//                    MathUtils.getPointOnCircumference(EyeCenter, radius, currentAngle - 24f + 180),
//                    4f,
//                    Color.RED
//            ));
//
//            plugin.LineWWidthInPlugin(EyeCornea);
//endregion

        } catch (Exception ex) {
            return;
        }

    }

    public List<VeBlib_LineWWidthData> MakeParticle(float amount, CombatEngineAPI engine, WeaponAPI weapon, Vector2f from, float BeamAngle, List<VeBlib_LineWWidthData> VeBlib_lineDataList) {

        VeBlib_lineDataList.add(new VeBlib_LineWWidthData(from, MathUtils.getPointOnCircumference(from, 10f, BeamAngle - 90f), 1f, shipColor));

        return VeBlib_lineDataList;
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
