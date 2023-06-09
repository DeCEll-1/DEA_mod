package data.weapons;

import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.WeaponAPI;
import data.helper.DEA_Helper;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicFakeBeam;

import java.awt.*;
import java.util.Random;

import static data.helper.DEA_Statics.random;


public class DEA_Deco_Reactor implements EveryFrameWeaponEffectPlugin {


    public static Vector2f from;

    public boolean real = true;

    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon) {
        DEA_Helper.DEA_Rainbow();
        try {
            for (int i = 0; i < 20; i++) {
                float BeamAngle = (float) random.nextInt(360);
                from = MathUtils.getPointOnCircumference(weapon.getLocation(), (float) random.nextInt(29), BeamAngle);
                MakeParticle(amount, engine, weapon, from, BeamAngle);

//                weapon.getShip().getShield().setRingColor(dea_helper.DEA_RainbowWithAlpha(120));
//                weapon.getShip().getShield().setInnerColor(dea_helper.DEA_RainbowWithAlpha(120));

            }
        } catch (Exception ex) {
            return;
        }

    }

    public void MakeParticle(float amount, CombatEngineAPI engine, WeaponAPI weapon, Vector2f from, float BeamAngle) {


        MagicFakeBeam.spawnFakeBeam(
                engine,
                from,
                1f,
                BeamAngle - 90f,
                4,
                0.1f,
                0.2f,
                100,
                weapon.getShip().getSpriteAPI().getAverageColor(),
                weapon.getShip().getSpriteAPI().getAverageColor(),
                0f,
                DamageType.ENERGY,
                0f,
                weapon.getShip()
        );


    }


}
