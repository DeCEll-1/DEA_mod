package data.shipsystems.scripts;

import VeB.helper.VeBlib_Logger;
import VeB.helper.VeBlib_Rainbow;
import VeB.helper.render.VeBlib_RendererPlugin;
import VeB.helper.render.renderClassesFolder.VeBlib_SquareData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.SoundAPI;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipSystemAPI;
import com.fs.starfarer.api.combat.ShipwideAIFlags;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import com.fs.starfarer.api.util.Misc;
import data.classes.DEA_OpenglWeaponFunctions;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DEA_BoundlessSystem extends BaseShipSystemScript {

    //only player can buff themselves and allies othervise its just like entropy

    private VeBlib_RendererPlugin plugin = null;
    private static float EnemyShipTimeMult = 0.3f;

    private static float AllyShipTimeMult = 3f;

    private static float SelfShipTimeMult = 3f;

    private static final Color JITTER_COLOR = new Color(161, 53, 53, 120);
    private static final Color JITTER_UNDER_COLOR = new Color(145, 0, 0, 70);

    private int frame = 0;

    private boolean run = false;
    private boolean flipFlop = true;
    private boolean doOnce2hou = true;
    private boolean doOnce = true;

    private VeBlib_Rainbow rainbow = new VeBlib_Rainbow();

    public void apply(MutableShipStatsAPI stats, String id, ShipSystemStatsScript.State state, float effectLevel) {

        try {

            if (plugin == null) {//2hou nuts
                plugin = new VeBlib_RendererPlugin();
                Global.getCombatEngine().addLayeredRenderingPlugin(plugin);
            }

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

            //rather 2hou acts
            if (Global.getSettings().getBoolean("2houBadApplePlayOnDEA_Boundless")) {

                if (run && flipFlop) {//2hou nuts


                    Vector2f topLeft = new Vector2f(0f * 2, 360f * 2);
                    Vector2f topRight = new Vector2f(480f * 2, 360f * 2);
                    Vector2f bottomLeft = new Vector2f(0f * 2, 0f * 2);
                    Vector2f bottomRight = new Vector2f(480f * 2, 0f * 2);

                    int amountOfZeros = 4 - String.valueOf(frame).length();


                    StringBuilder fileName = new StringBuilder("a/2hou/001_00");

                    for (int i = 0; i < amountOfZeros; i++) {

                        fileName.append("0");

                    }

                    fileName.append(frame);

                    fileName.append(".jpg");

//                Global.getSettings().loadTexture(fileName.toString());

                    VeBlib_SquareData square = new VeBlib_SquareData(
                            topLeft,
                            topRight,
                            bottomLeft,
                            bottomRight,
                            Color.WHITE,
                            true,
                            Global.getSettings().getSprite(fileName.toString())
                    );

                    List<VeBlib_SquareData> squareList = new ArrayList<>();

                    squareList.add(square);

                    plugin.SquareInPlugin(squareList);

                    frame++;
                }

                flipFlop = !flipFlop;

                if (state == State.IN) {//2hou nuts
                    if (doOnce2hou) {
                        run = true;
                        frame = 0;
                        SoundAPI sound = Global.getSoundPlayer().playSound(
                                "DEA_2hou",
                                1f,
                                1f,
                                ship.getLocation(),
                                ship.getVelocity()
                        );

                    }
                    doOnce2hou = false;
                }
            }

            ShipAPI targetship = findTarget(ship);

            float jitterLevel = effectLevel;

            float jitterRangeBonus = 0;

            float maxRangeBonus = 10f;

//            rainbow.advance();//advances the rainbows color

//            DEA_OpenglWeaponFunctions.randomiseColorOfWeaponSlots(ship);


//            VeBlib_Logger.log(this.getClass(),Global.getCombatEngine().getPlayerShip().getLocation().toString());


            if (state == State.OUT) {
                Global.getCombatEngine().getTimeMult().unmodifyMult(id);
            }

            if (targetship != null) {

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

                targetship.getMutableStats().getTimeMult().modifyMult(id, shipTimeMult);

                if (targetship == Global.getCombatEngine().getPlayerShip()) {
                    Global.getCombatEngine().getTimeMult().modifyMult(id, shipTimeMult);
                } else {
                    Global.getCombatEngine().getTimeMult().unmodifyMult(id);
                }

//        }

//        Global.getCombatEngine().maintainStatusForPlayerShip(id,
//                "graphics/icons/hullmods/Test_Image.png",
//                "debug number:",
//                Float.toString(ship.getMutableStats().getTimeMult().getMult()),
//                false
//        );


            }
        } catch (Exception ex) {
            //HELP
        }
    }

    public boolean isUsable(ShipSystemAPI system, ShipAPI ship) {

//        float s = MathUtils.getDistance(ship, findTarget(ship));
        if (true) return true;

        if (ship != null && ship.isAlive() && !ship.isPhased() && findTarget(ship) != null && MathUtils.getDistance(ship, findTarget(ship)) < 3000f) {
            return true;
        }
        return false;
//        return ship != null && ship.isAlive() && ship.isPhased();
    }

    protected ShipAPI findTarget(ShipAPI ship) {//EntropyAmplifierStats code
        float range = ship.getMutableStats().getSystemRangeBonus().computeEffective(3000f);
        boolean player = ship == Global.getCombatEngine().getPlayerShip();
        ShipAPI target = ship.getShipTarget();

        if (ship.getShipAI() != null && ship.getAIFlags().hasFlag(ShipwideAIFlags.AIFlags.TARGET_FOR_SHIP_SYSTEM)) {
            target = (ShipAPI) ship.getAIFlags().getCustom(ShipwideAIFlags.AIFlags.TARGET_FOR_SHIP_SYSTEM);
        }

        if (target != null) {
            float dist = Misc.getDistance(ship.getLocation(), target.getLocation());
            float radSum = ship.getCollisionRadius() + target.getCollisionRadius();
            if (dist > range + radSum) target = null;
        } else {
            if (target == null || target.getOwner() == ship.getOwner()) {
                if (player) {
                    target = Misc.findClosestShipEnemyOf(ship, ship.getMouseTarget(), ShipAPI.HullSize.FRIGATE, range, true);
                } else {
                    Object test = ship.getAIFlags().getCustom(ShipwideAIFlags.AIFlags.MANEUVER_TARGET);
                    if (test instanceof ShipAPI) {
                        target = (ShipAPI) test;
                        float dist = Misc.getDistance(ship.getLocation(), target.getLocation());
                        float radSum = ship.getCollisionRadius() + target.getCollisionRadius();
                        if (dist > range + radSum) target = null;
                    }
                }
            }
            if (target == null) {
                target = Misc.findClosestShipEnemyOf(ship, ship.getLocation(), ShipAPI.HullSize.FRIGATE, range, true);
            }
        }

        return target;
    }

}
