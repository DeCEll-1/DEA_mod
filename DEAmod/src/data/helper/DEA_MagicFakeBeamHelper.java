package data.helper;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.ShipAPI;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicFakeBeam;

//powered by magiclib
public class DEA_MagicFakeBeamHelper {

    /**
     * İF YOU GONNA MAKE A VİSUAL DONT USE THİS LOOK AT DEA_RenderHelper
     * makes fake beam circle (i dont know how to make a circle) suggested to use 30 but go balls lol</br>
     * <p>
     * MAX SİDES İS 360F the method wont do anything if you put more than that and return false</br>
     *
     * @param center      center of the circle
     * @param sides       amount of sides the "circle" will have
     * @param circleAngle angle of the circle, yk i named this circle but you can use this to make triangles and stuff too so thats for this, can be 0 but cant be more than 360
     * @param height      height of the circle
     * @param fringe      fringe color
     * @param core        core color
     * @param source      source ship
     * @return if the proccess completed successfully returns true, false othervise. so instead of crashing it just, doesnt work
     */
    public static boolean DEA_BeamPolygon(Vector2f center, float sides, float circleAngle, float height, DEA_DamageBaseClass DamageBaseClass, java.awt.Color core, java.awt.Color fringe, ShipAPI source) {
        try {
            if (sides > 360f || circleAngle > 360f) {
                return false;
            }

            Vector2f from = new Vector2f();
            Vector2f to = new Vector2f();

            from = MathUtils.getPointOnCircumference(center, height, circleAngle);

            int z = 0;

            for (float i = circleAngle; i < circleAngle + 360 + sides; i += 360 / sides) {
                if (z % 2 == 0) {
                    to = MathUtils.getPointOnCircumference(center, height, i);
                } else {
                    from = MathUtils.getPointOnCircumference(center, height, i);
                }
                MagicFakeBeam.spawnFakeBeam(
                        Global.getCombatEngine(),
                        from,
                        MathUtils.getDistance(from, to),
                        VectorUtils.getAngle(from, to),
                        4f,
                        0.1f,
                        0.1f,
                        1f,
                        core,
                        fringe,
                        DamageBaseClass.Damage,
                        DamageBaseClass.DamageType,
                        DamageBaseClass.EMPDamage,
                        source
                );
                z++;
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    /**
     * makes fake beam circle (i dont know how to make a circle) suggested to use 30 but go balls lol</br>
     * <p>
     * MAX NUMBER İS 360F the method wont do anything if you put more than that and return false</br>
     *
     * @param center      center of the circle
     * @param angle       angle between every beam
     * @param circleAngle angle of the circle, yk i named this circle but you can use this to make triangles and stuff too so thats for this, can be 0 but cant be more than 360
     * @param height      height of the circle
     * @param fringe      fringe color
     * @param core        core color
     * @param source      source ship
     * @return if the proccess completed successfully returns true, false othervise. so instead of crashing it just, doesnt work
     */
    public static boolean DEA_CirclePenteractEffect(Vector2f center, float angle, float circleAngle, float height, java.awt.Color core, java.awt.Color fringe, ShipAPI source) {
        try {
            if (angle > 360f) {
                return false;
            }

            Vector2f from = new Vector2f();
            Vector2f fromLast = null;
            Vector2f to = new Vector2f();
            Vector2f toLast = null;

            from = MathUtils.getPointOnCircumference(center, height, circleAngle);

            int z = 0;

            for (float i = circleAngle; i < circleAngle + 360 + angle; i += 360 / angle) {
                if (z % 2 == 0) {
                    toLast = to;
                    to = MathUtils.getPointOnCircumference(center, height, i);
                } else {
                    fromLast = from;
                    from = MathUtils.getPointOnCircumference(center, height, i);
                }
                MagicFakeBeam.spawnFakeBeam(
                        Global.getCombatEngine(),
                        from,
                        MathUtils.getDistance(from, to),
                        VectorUtils.getAngle(from, to),
                        4f,
                        0.1f,
                        0.1f,
                        1f,
                        core,
                        fringe,
                        0f,
                        DamageType.ENERGY,
                        0f,
                        source
                );

                if (fromLast != null) {
                    MagicFakeBeam.spawnFakeBeam(
                            Global.getCombatEngine(),
                            fromLast,
                            MathUtils.getDistance(fromLast, to),
                            VectorUtils.getAngle(fromLast, to),
                            4f,
                            0.1f,
                            0.1f,
                            1f,
                            core,
                            fringe,
                            0f,
                            DamageType.ENERGY,
                            0f,
                            source
                    );
                }
                z++;
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }


}
