package data.helper.UNUSED.kt;

import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.ShipAPI;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;

public class DEA_UNUSED {

//    public static boolean DEA_SpawnEMPArcLineDamagingUNUSED(Vector2f from, float angle, int lenght, float thiccness, float space, CombatEntityAPI target, ShipAPI DamageSource, DamageType DamageType, float DamageAmount, float EMPDamageAmount, String ImpactSoundID, java.awt.Color fringe, java.awt.Color core) {
//        try {
//            Vector2f to = new Vector2f();
//
//            for (int i = 0; i < lenght; i++) {
//                if (i % 2 == 0) {
//                    to = MathUtils.getPointOnCircumference(from, space, angle);
//                    if (CombatUtils.getEntitiesWithinRange(to, space) != null) {
//
//                        if (DEA_SpawnDamagingEMPArc(DamageSource, from, target, DamageType, DamageAmount, EMPDamageAmount, space, ImpactSoundID, thiccness, fringe, core)) {
//                        }
//
//                    } else {
//                        DEA_SpawnEMPArcVisual(from, to, thiccness, fringe, core);
//                    }
//                } else {
//                    from = MathUtils.getPointOnCircumference(to, space, angle);
//
//                    if (CombatUtils.getEntitiesWithinRange(from, space) != null) {
//                        if (DEA_SpawnDamagingEMPArc(DamageSource, to, target, DamageType, DamageAmount, EMPDamageAmount, space, ImpactSoundID, thiccness, fringe, core)) {
//                        }
//                    } else {
//                        DEA_SpawnEMPArcVisual(to, from, thiccness, fringe, core);
//                    }
//                }
//            }
//
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
//    }

}
