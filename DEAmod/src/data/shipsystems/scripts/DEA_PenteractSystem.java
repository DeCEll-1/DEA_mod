package data.shipsystems.scripts;

import DEA.helper.*;
import DEA.helper.render.DEAlib_RendererPlugin;
import DEA.helper.render.renderClassesFolder.*;
import DEA.helper.render.renderClassesFolder.DEAlib_RingData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DEA_PenteractSystem extends BaseShipSystemScript {

    private static WeaponAPI DEA_Deco;
    private boolean doOnce = true;

    private Color shipColor;

    private float angle = 0f;
    private float angle2 = 360f;

    private List<Vector2f> boundList = null;
//    private List<Vector2f> boundList2 = null;

    private List<Color> colorList = new ArrayList<>();

//    private Sphere sphere = null;

    private DEAlib_RendererPlugin plugin = null;
//    private float empArcCount = 1;

    private boolean EmpBurst = false;


    private static DEAlib_DamageBaseClass DEA_DamageBaseClass_DEA_PenteractSystem = new DEAlib_DamageBaseClass(4000f, 7000f, DamageType.ENERGY);

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

//                if (this.sphere == null) {
//                    sphere = new Sphere();
//
////"mental retardation"
//                    //i dont have any idea what those things mean
//                    sphere.setTextureFlag(false);
//                    sphere.setDrawStyle(GLU.GLU_LINE);
//                        sphere.setNormals(GLU.GLU_SMOOTH);
//                        sphere.setOrientation(GLU.GLU_INSIDE);
//                }

                if (doOnce) {

                    this.shipColor = ship.getSpriteAPI().getAverageColor();
//                    this.shipColor = new Color(51, 190, 151, 255);
                    for (WeaponAPI weapon : ship.getAllWeapons()) {
                        if (weapon.getSlot().getId().equals("FusionDeco")) {
                            DEA_Deco = weapon;
                            break;
                        }
                    }
                    doOnce = false;
                }

                plugin.DEAlib_Ring(null);
                plugin.DEAlib_BoxInPlugin(null);
                plugin.DEAlib_PolygonInPlugin(null);
                plugin.DEAlib_CircleInPlugin(null);
                plugin.DEAlib_LineWWidthInPlugin(null);

                List<DEAlib_RingData> DEA_Ring = new ArrayList<>();

                if (effectLevel >= 1) {
//                    DEA_MagicFakeBeamHelper.DEA_CirclePenteractEffect(ship.getLocation(), 6f, 0f, ship.getSpriteAPI().getHeight(), shipColor, shipColor, ship);

                    EmpBurst = true;

                    ship.getExactBounds().update(ship.getLocation(), ship.getFacing());

                    List<DEAlib_BoxData> dealib_boxDataList = new ArrayList<>();

                    DEA_Ring.add(new DEAlib_RingData(ship.getLocation(), 20f, angle + 0f, ship.getCollisionRadius() * 2, 4f, shipColor));
//                    DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 20f, angle + 0f, ship.getSpriteAPI().getHeight(), shipColor);

                    for (float i = 0; i < 90; i += 22.5) {
                        DEA_Ring.add(new DEAlib_RingData(ship.getLocation(), 4f, DEAlib_Helper.DEA_GetPlusMinusAngle(angle, i), ship.getCollisionRadius() * 2, 4f, shipColor));
//                        DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle, i), ship.getSpriteAPI().getHeight(), shipColor);
                    }

                    DEA_Ring.add(new DEAlib_RingData(ship.getLocation(), 20f, angle2 + 0f, (float) (ship.getCollisionRadius() * 2 * 0.72), 4f, shipColor));
//                    DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 20f, angle2 + 0f, (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    for (int i = 30; i < 91; i += 30) {
                        DEA_Ring.add(new DEAlib_RingData(ship.getLocation(), 4f, DEAlib_Helper.DEA_GetPlusMinusAngle(angle2, i), (float) (ship.getCollisionRadius() * 2 * 0.72), 4f, shipColor));
//                        DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle2, i), (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    }


//                    empArcCount += Global.getCombatEngine().getElapsedInLastFrame();


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

//                    dealib_polygonDataList.add(new DEAlib_PolygonData(boundList2, colorList, GL_LINE));

//add constructer to the plugin and put layers in it
                    plugin.DEAlib_ChangeRenderLayerInPlugin(CombatEngineLayers.BELOW_SHIPS_LAYER);
                    plugin.DEAlib_BoxInPlugin(dealib_boxDataList);
//                    plugin.DEAlib_PolygonInPlugin(dealib_polygonDataList);
                    plugin.DEAlib_Ring(DEA_Ring);


                }

                Global.getCombatEngine().maintainStatusForPlayerShip(
                        ship.getId(),
                        "graphics/icons/hullmods/Test_Image.png",
                        "Debug number:",
                        String.valueOf(EmpBurst),
                        false
                );

                if (effectLevel < 1 && EmpBurst) {
                    for (float i = 0; i < 2; i += 1f) {
                        for (CombatEntityAPI enemy : CombatUtils.getEntitiesWithinRange(ship.getLocation(), 3000f)) {//those are for checking if they are near anything but it lags more than without it rn so

                            if (!enemy.getLocation().equals(ship.getLocation())) {
                                if (enemy instanceof ShipAPI && !((ShipAPI) enemy).isAlly() && ((ShipAPI) enemy).isAlive()) {
                                    DEAlib_EMPArcHelper.DEA_SpawnDamagingEMPArc(
                                            ship,
                                            ship.getLocation(),
                                            enemy,
                                            DEA_DamageBaseClass_DEA_PenteractSystem,
                                            3000f,
                                            "",
                                            4f,
                                            DEAlib_Statics.TRANSPARENT,
                                            DEAlib_Statics.TRANSPARENT
                                    );
                                }
                            }

                        }
                    }

                    EmpBurst = false;

                }

                if (state == State.OUT) {
//                    empArcCount = 0;


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
