package data.shipsystems.scripts;

import cmu.shaders.BaseRenderPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import data.helper.DEA_DamageBaseClass;
import data.helper.DEA_Helper;
import data.helper.DEA_MagicFakeBeamHelper;
import data.helper.DEA_RenderHelper;
import data.weapons.DEA_Deco_Reactor;
import org.lazywizard.lazylib.opengl.DrawUtils;
import sun.java2d.pipe.DrawImage;

import java.awt.*;

public class DEA_PenteractSystem extends BaseShipSystemScript {

    public static WeaponAPI DEA_Deco;
    public boolean doOnce = true;

    public Color shipColor;

    public float angle = 0f;
    public float angle2 = 360f;

    public static DEA_DamageBaseClass DEA_DamageBaseClass_DEA_PenteractSystem = new DEA_DamageBaseClass(10f, 50f, DamageType.ENERGY);

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        try {

            if (angle > 360f) angle = 0f;
            if (angle2 > 360f) angle2 = 0f;

            angle++;
            angle2 += 2;

            ShipAPI ship = null;

            if (stats.getEntity() instanceof ShipAPI) {
                ship = (ShipAPI) stats.getEntity();

                if (doOnce) {
                    this.shipColor = ship.getSpriteAPI().getAverageColor();
//                    for (WeaponAPI weapon : ship.getAllWeapons()) {
//                        if (weapon.getSlot().getId().equals("FusionDeco")) {
//                            DEA_Deco = weapon;
//                            break;
//                        }
//                    }
                }

                if (effectLevel >= 1) {
//                    DEA_MagicFakeBeamHelper.DEA_CirclePenteractEffect(ship.getLocation(), 6f, 0f, ship.getSpriteAPI().getHeight(), shipColor, shipColor, ship);
                    DEA_RenderHelper.DEA_DrawPolygonWithHeight(ship.getLocation(), 20f, angle + 0f, ship.getSpriteAPI().getHeight(), shipColor);
                    for (float i = 0; i < 90; i += 22.5) {
                        DEA_RenderHelper.DEA_DrawPolygonWithHeight(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle, i), ship.getSpriteAPI().getHeight(), shipColor);
                    }

                    DEA_RenderHelper.DEA_DrawPolygonWithHeight(ship.getLocation(), 20f, angle2 + 0f, (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    for (int i = 30; i < 91; i += 30) {
                        DEA_RenderHelper.DEA_DrawPolygonWithHeight(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle * 2, i), (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    }


                }
            }
        } catch (Exception ex) {
            return;
        }
    }

    @Override
    public void unapply(MutableShipStatsAPI stats, String id) {
        try {

            ShipAPI ship = null;
            if (stats.getEntity() instanceof ShipAPI) {
                ship = (ShipAPI) stats.getEntity();


            } else {
                return;
            }
        } catch (Exception ex) {
            return;
        }
    }


}
