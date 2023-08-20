package data.shipsystems.scripts;

import DEA.helper.DEAlib_BoundsHelper;
import DEA.helper.render.DEAlib_RendererPlugin;
import DEA.helper.render.renderClassesFolder.DEAlib_BoxData;
import DEA.helper.render.renderClassesFolder.DEAlib_PolygonData;
import DEA.helper.render.renderClassesFolder.DEAlib_PolygonWHeightAndCenterData;
import cmu.shaders.BaseRenderPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.campaign.rulecmd.PLAddEntry;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import DEA.helper.DEAlib_DamageBaseClass;
import DEA.helper.DEAlib_Helper;
import DEA.helper.DEAlib_MagicFakeBeamHelper;
import data.weapons.DEA_Deco_Reactor;
import org.lazywizard.lazylib.opengl.DrawUtils;
import org.lwjgl.util.vector.Vector2f;
import sun.java2d.pipe.DrawImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class DEA_PenteractSystem extends BaseShipSystemScript {

    private static WeaponAPI DEA_Deco;
    private boolean doOnce = true;

    private Color shipColor;

    private float angle = 0f;
    private float angle2 = 360f;

    private List<Vector2f> boundList = null;
//    private List<Vector2f> boundList2 = null;

    private List<Color> colorList = new ArrayList<>();


    private DEAlib_RendererPlugin plugin = null;

    private static DEAlib_DamageBaseClass DEA_DamageBaseClass_DEA_PenteractSystem = new DEAlib_DamageBaseClass(10f, 50f, DamageType.ENERGY);

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        try {

            if (plugin == null) {
                plugin = new DEAlib_RendererPlugin();
                Global.getCombatEngine().addLayeredRenderingPlugin(plugin);
            }

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

                    ship.getExactBounds().update(ship.getLocation(), ship.getFacing());

                    List<DEAlib_PolygonWHeightAndCenterData> dealib_polygonWHeightAndCenterDataList = new ArrayList<>();
                    List<DEAlib_BoxData> dealib_boxDataList = new ArrayList<>();

                    dealib_polygonWHeightAndCenterDataList.add(new DEAlib_PolygonWHeightAndCenterData(ship.getLocation(), 20f, angle + 0f, ship.getSpriteAPI().getHeight(), shipColor));
//                    DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 20f, angle + 0f, ship.getSpriteAPI().getHeight(), shipColor);

                    for (float i = 0; i < 90; i += 22.5) {
                        dealib_polygonWHeightAndCenterDataList.add(new DEAlib_PolygonWHeightAndCenterData(ship.getLocation(), 4f, DEAlib_Helper.DEA_GetPlusMinusAngle(angle, i), ship.getSpriteAPI().getHeight(), shipColor));
//                        DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle, i), ship.getSpriteAPI().getHeight(), shipColor);
                    }

                    dealib_polygonWHeightAndCenterDataList.add(new DEAlib_PolygonWHeightAndCenterData(ship.getLocation(), 20f, angle2 + 0f, (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor));
//                    DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 20f, angle2 + 0f, (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    for (int i = 30; i < 91; i += 30) {
                        dealib_polygonWHeightAndCenterDataList.add(new DEAlib_PolygonWHeightAndCenterData(ship.getLocation(), 4f, DEAlib_Helper.DEA_GetPlusMinusAngle(angle2, i), (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor));
//                        DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle2, i), (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    }


//                    dealib_boxDataList.add(new DEAlib_BoxData(
//                            DEAlib_BoundsHelper.DEAlib_GetRandomBoundLocation(ship),
//                            DEAlib_BoundsHelper.DEAlib_GetRandomBoundLocation(ship),
//                            DEAlib_BoundsHelper.DEAlib_GetRandomBoundLocation(ship),
//                            DEAlib_BoundsHelper.DEAlib_GetRandomBoundLocation(ship),
//                            shipColor,
//                            GL_LINE
//                    ));

                    List<DEAlib_PolygonData> dealib_polygonDataList = new ArrayList<>();

//                    if (boundList == null) {
                    boundList = DEAlib_BoundsHelper.DEAlib_GetListOfBoundLocations(ship);
                    for (int i = 0; i < boundList.size(); i++) {
                        colorList.add(Color.white);
                    }
//                    }

//                    boundList2 = new ArrayList<>();

//                    boundList2.add(new Vector2f(0f, 60f));
//                    boundList2.add(new Vector2f(0f, 0f));
//                    boundList2.add(new Vector2f(60f, 0f));
//                    boundList2.add(new Vector2f(60f, 60f));

                    dealib_polygonDataList.add(new DEAlib_PolygonData(boundList, colorList, GL_FILL));
//                    dealib_polygonDataList.add(new DEAlib_PolygonData(boundList2, colorList, GL_LINE));

//add constructer to the plugin and put layers in it
                    plugin.DEAlib_ChangeRenderLayerInPlugin(CombatEngineLayers.BELOW_SHIPS_LAYER);
                    plugin.DEAlib_DrawBoxInPlugin(dealib_boxDataList);
                    plugin.DEAlib_DrawPolygonWHeightAndCenter(dealib_polygonWHeightAndCenterDataList);
                    plugin.DEAlib_PolygonInPlugin(dealib_polygonDataList);


                }

                if (state == State.OUT) {
                    plugin.DEAlib_DrawPolygonWHeightAndCenter(null);
                    plugin.DEAlib_DrawBoxInPlugin(null);
                    plugin.DEAlib_PolygonInPlugin(null);
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
