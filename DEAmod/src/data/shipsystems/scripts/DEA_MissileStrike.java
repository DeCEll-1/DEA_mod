package data.shipsystems.scripts;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.input.InputEventAPI;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import com.fs.starfarer.combat.entities.Missile;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.util.vector.Vector2f;
import com.fs.starfarer.api.impl.combat.MineStrikeStats;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DEA_MissileStrike extends BaseShipSystemScript {

    public static float SystemRange = 1000f;

    public static final float MIN_SPAWN_DIST = 75f;

    public static final float MIN_SPAWN_DIST_FRIGATE = 110f;

    public static final Color JITTER_COLOR = new Color(255, 155, 255, 75);
    public static final Color JITTER_UNDER_COLOR = new Color(255, 155, 255, 155);

    public static String ProjectileType;

    public static float getRange(ShipAPI ship) {
        if (ship != null) {
            return ship.getMutableStats().getSystemRangeBonus().computeEffective(SystemRange);
        }
        return SystemRange;
    }


    public void apply(MutableShipStatsAPI stats, String id, ShipSystemStatsScript.State state, float effectLevel) {
        ShipAPI ship = null;
        if (stats.getEntity() instanceof ShipAPI) {

            ship = (ShipAPI) stats.getEntity();

            Global.getCombatEngine().maintainStatusForPlayerShip(
                    id,
                    "graphics/icons/hullmods/Test_Image.png",
                    "Debug number:",
                    ProjectileType,
                    false
            );
        }

        float jitterLevel = effectLevel;
        if (state == State.OUT) {
            jitterLevel = effectLevel * effectLevel;
        }

        float maxRangeBonus = 25f;

        float jitterRangeBonus = jitterLevel * maxRangeBonus;

        ShipSystemStatsScript.State var10000 = State.OUT;
        ship.setJitterUnder(this, JITTER_UNDER_COLOR, jitterLevel, 11, 0.0F, 3.0F + jitterRangeBonus);
        ship.setJitter(this, JITTER_COLOR, jitterLevel, 4, 0.0F, 0.0F + jitterRangeBonus);

        if (state != State.IN) {

//            if (ship.getCustomData().containsKey("_DEA_KarabasanMissileModifier")) {
//                ProjectileType = (String) ship.getCustomData().get("_DEA_KarabasanMissileModifier");
//            }

            if (effectLevel >= 1f) {

                Vector2f target = ship.getMouseTarget();

                if (ship.getAI() != null && ship.getAIFlags().hasFlag(ShipwideAIFlags.AIFlags.SYSTEM_TARGET_COORDS)) {
                    target = (Vector2f) ship.getAIFlags().getCustom(ShipwideAIFlags.AIFlags.SYSTEM_TARGET_COORDS);
                }

                if (target != null) {
                    float dist = Misc.getDistance(ship.getLocation(), target);
                    float max = this.getRange(ship) + ship.getCollisionRadius();
                    if (dist > max) {
                        float dir = Misc.getAngleInDegrees(ship.getLocation(), target);
                        target = Misc.getUnitVectorAtDegreeAngle(dir);
                        target.scale(max);
                        Vector2f.add(target, ship.getLocation(), target);
                    }
                }

                target = this.findClearLocation(ship, target);
                if (target != null) {

                    this.spawnProjectile(ship, target, stats);
                }

            } else {
                var10000 = State.OUT;
            }
        }
    }


    public void unapply(MutableShipStatsAPI stats, String id) {
    }

    public void spawnProjectile(ShipAPI source, Vector2f projLoc, MutableShipStatsAPI stats) {
        CombatEngineAPI engine = Global.getCombatEngine();
        Vector2f currLoc = Misc.getPointAtRadius(projLoc, 30f + (float) Math.random() * 30f);

        float start = (float) Math.random() * 360f;

        for (float angle = start; angle < start + 390f; angle += 30f) {
            if (angle != start) {
                Vector2f loc = Misc.getUnitVectorAtDegreeAngle(angle);
                loc.scale(50f + (float) Math.random() * 30f);
                currLoc = Vector2f.add(projLoc, loc, new Vector2f());
            }

//            Iterator var8 = Global.getCombatEngine().getMissiles().iterator();
//
//            while (var8.hasNext()) {
//                MissileAPI other = (MissileAPI) var8.next();
//                if (other.isMine()) {
//                    float dist = Misc.getDistance(currLoc, other.getLocation());
//                    if (dist < other.getCollisionRadius() + 40f) {
//                        currLoc = null;
//                        break;
//                    }
//                }
//            }

            if (currLoc != null) {
                break;
            }
        }

        if (currLoc == null) {
            currLoc = Misc.getPointAtRadius(projLoc, 30f + (float) Math.random() * 30f);
        }

        float angle = 0f;

        ShipAPI ship = null;
        if (stats.getEntity() instanceof ShipAPI) {
            ship = (ShipAPI) stats.getEntity();
        }

        MissileAPI proj = null;

        try {
            ProjectileType = "hammer";
            for (String hullmod : ship.getVariant().getHullMods()) {
                if (hullmod.equals("DEA_KarabasanReaperModification")) {
                    ProjectileType = "reaper";
                } else if (hullmod.equals("DEA_KarabasanSabotModification")) {//DEA_KarabasanSabotModification
                    ProjectileType = "sabot";
                } else if (hullmod.equals("DEA_KarabasanAtroposModification")) {//DEA_KarabasanReaperModification
                    ProjectileType = "atropos";
                } else if (hullmod.equals("DEA_KarabasanProximyModification")) {
                    ProjectileType = "phasecl";
                } else if (hullmod.equals("DEA_KarabasanHarpoonModification")) {//DEA_KarabasanAMSRMModification
                    ProjectileType = "harpoon";
                    proj = (MissileAPI) engine.spawnProjectile(source,Global.getCombatEngine().createFakeWeapon(ship,ProjectileType), ProjectileType, currLoc, VectorUtils.getAngle(ship.getMouseTarget(), ship.getShipTarget().getLocation()), (Vector2f) null);
                    proj = (MissileAPI) engine.spawnProjectile(source,Global.getCombatEngine().createFakeWeapon(ship,ProjectileType), ProjectileType, currLoc, VectorUtils.getAngle(ship.getMouseTarget(), ship.getShipTarget().getLocation()), (Vector2f) null);
                    proj = (MissileAPI) engine.spawnProjectile(source,Global.getCombatEngine().createFakeWeapon(ship,ProjectileType), ProjectileType, currLoc, VectorUtils.getAngle(ship.getMouseTarget(), ship.getShipTarget().getLocation()), (Vector2f) null);
                } else if (hullmod.equals("DEA_KarabasanAMSRMModification")) {//DEA_KarabasanAMSRMModification
                    ProjectileType = "amsrm";
                } else if (hullmod.equals("DEA_KarabasanResonatorModification")) {//DEA_KarabasanAMSRMModification
                    ProjectileType = "resonatormrm";
                    proj = (MissileAPI) engine.spawnProjectile(source,Global.getCombatEngine().createFakeWeapon(ship,ProjectileType), ProjectileType, currLoc, VectorUtils.getAngle(ship.getMouseTarget(), ship.getShipTarget().getLocation()), (Vector2f) null);
                    proj = (MissileAPI) engine.spawnProjectile(source,Global.getCombatEngine().createFakeWeapon(ship,ProjectileType), ProjectileType, currLoc, VectorUtils.getAngle(ship.getMouseTarget(), ship.getShipTarget().getLocation()), (Vector2f) null);
                    proj = (MissileAPI) engine.spawnProjectile(source,Global.getCombatEngine().createFakeWeapon(ship,ProjectileType), ProjectileType, currLoc, VectorUtils.getAngle(ship.getMouseTarget(), ship.getShipTarget().getLocation()), (Vector2f) null);
                } else if (hullmod.equals("DEA_KarabasanRiftModification")) {//DEA_KarabasanAMSRMModification
                    ProjectileType = "rifttorpedo";
                }
            }

            proj = (MissileAPI) engine.spawnProjectile(source, Global.getCombatEngine().createFakeWeapon(ship,ProjectileType), ProjectileType, currLoc, VectorUtils.getAngle(ship.getMouseTarget(), ship.getShipTarget().getLocation()), (Vector2f) null);
        } catch (Exception ex) {
            return;
        }


        if (source != null) {
            Global.getCombatEngine().applyDamageModifiersToSpawnedProjectileWithNullWeapon(source, WeaponAPI.WeaponType.MISSILE, false, proj.getDamage());
        }

        float fadeInTime = 0.5f;
        proj.getVelocity().scale(0f);
        proj.fadeOutThenIn(fadeInTime);
//        Global.getCombatEngine().addPlugin(this.createMissileJitterPlugin(proj, fadeInTime));
        float liveTime = 5f;
        proj.setFlightTime(proj.getMaxFlightTime() - liveTime);

        Global.getSoundPlayer().playSound("mine_teleport", 1f, 1f, proj.getLocation(), proj.getVelocity());
    }

//    protected EveryFrameCombatPlugin createMissileJitterPlugin(final MissileAPI mine, final float fadeInTime) {
//        return new BaseEveryFrameCombatPlugin() {
//            float elapsed = 0f;
//
//            @Override
//            public void advance(float amount, List<InputEventAPI> events) {
//                if (Global.getCombatEngine().isPaused()) return;
//
//                elapsed += amount;
//
//                float jitterLevel = mine.getCurrentBaseAlpha();
//                if (jitterLevel < 0.5f) {
//                    jitterLevel *= 2f;
//                } else {
//                    jitterLevel = (1f - jitterLevel) * 2f;
//                }
//
//                float jitterRange = 1f - mine.getCurrentBaseAlpha();
//                //jitterRange = (float) Math.sqrt(jitterRange);
//                float maxRangeBonus = 50f;
//                float jitterRangeBonus = jitterRange * maxRangeBonus;
//                Color c = JITTER_UNDER_COLOR;
//                c = Misc.setAlpha(c, 70);
//                //mine.setJitter(this, c, jitterLevel, 15, jitterRangeBonus * 0.1f, jitterRangeBonus);
//                mine.setJitter(this, c, jitterLevel, 15, jitterRangeBonus * 0, jitterRangeBonus);
//
//                if (jitterLevel >= 1 || elapsed > fadeInTime) {
//                    Global.getCombatEngine().removePlugin(this);
//                }
//            }
//        };
//    }

    @Override
    public String getInfoText(ShipSystemAPI system, ShipAPI ship) {
        if (system.isOutOfAmmo()) return null;
        if (system.getState() != ShipSystemAPI.SystemState.IDLE) return null;

        Vector2f target = ship.getMouseTarget();
        if (target == null) {
            return "NO TARGET";
        } else {
            return "READY";
        }
    }

    @Override
    public boolean isUsable(ShipSystemAPI system, ShipAPI ship) {
        if (ship.getMouseTarget() != null && ship.getShipTarget() != null) {
            return true;
        }
        return false;
    }

    private Vector2f findClearLocation(ShipAPI ship, Vector2f dest) {
        //i dont know how this thing works
        if (isLocationClear(dest)) return dest;

        float incr = 50f;

        WeightedRandomPicker<Vector2f> tested = new WeightedRandomPicker<Vector2f>();
        for (float distIndex = 1; distIndex <= 32f; distIndex *= 2f) {
            float start = (float) Math.random() * 360f;
            for (float angle = start; angle < start + 360; angle += 60f) {
                Vector2f loc = Misc.getUnitVectorAtDegreeAngle(angle);
                loc.scale(incr * distIndex);
                Vector2f.add(dest, loc, loc);
                tested.add(loc);
                if (isLocationClear(loc)) {
                    return loc;
                }
            }
        }

        if (tested.isEmpty()) return dest;//shouldnt happen :clueless:

        return tested.pick();

    }

    private boolean isLocationClear(Vector2f loc) {
        for (ShipAPI other : Global.getCombatEngine().getShips()) {
            if (other.isShuttlePod()) continue;
            ;
            if (other.isFighter()) continue;
            ;

            Vector2f otherLoc = other.getShieldCenterEvenIfNoShield();
            float otherR = other.getShieldRadiusEvenIfNoShield();
            if (other.isPiece()) {
                otherLoc = other.getLocation();
                otherR = other.getCollisionRadius();
            }


            float dist = Misc.getDistance(loc, otherLoc);
            float r = otherR;

            float checkDist = MIN_SPAWN_DIST;
            if (other.isFrigate()) checkDist = MIN_SPAWN_DIST_FRIGATE;
            if (dist < r + checkDist) {
                return false;
            }
        }
        for (CombatEntityAPI other : Global.getCombatEngine().getAsteroids()) {
            float dist = Misc.getDistance(loc, other.getLocation());
            if (dist < other.getCollisionRadius() + MIN_SPAWN_DIST) {
                return false;
            }
        }
        return true;
    }

    public float getFuseTime() {
        return 3f;
    }

}
