package data.shipsystems.scripts;

import VeB.helper.*;
import VeB.helper.helperHelperClasses.VeBlib_DamageBaseClass;
import VeB.helper.render.VeBlib_RendererPlugin;
import VeB.helper.render.renderClassesFolder.*;
import VeB.helper.render.renderClassesFolder.VeBlib_RingData;
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

    private VeBlib_RendererPlugin plugin = null;
//    private float empArcCount = 1;

    private boolean EmpBurst = false;


    private static VeBlib_DamageBaseClass DEA_DamageBaseClass_DEA_PenteractSystem = new VeBlib_DamageBaseClass(4000f, 7000f, DamageType.ENERGY);

    @Override
    public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
        try {

            if (plugin == null) {
                plugin = new VeBlib_RendererPlugin();
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

                plugin.Ring(null);
                plugin.SquareInPlugin(null);
                plugin.PolygonInPlugin(null);
                plugin.CircleInPlugin(null);
                plugin.LineWWidthInPlugin(null);

                List<VeBlib_RingData> DEA_Ring = new ArrayList<>();

                if (effectLevel >= 1) {
//                    DEA_MagicFakeBeamHelper.DEA_CirclePenteractEffect(ship.getLocation(), 6f, 0f, ship.getSpriteAPI().getHeight(), shipColor, shipColor, ship);

                    EmpBurst = true;

                    ship.getExactBounds().update(ship.getLocation(), ship.getFacing());

                    List<VeBlib_SquareData> VeBlib_boxDataList = new ArrayList<>();

                    DEA_Ring.add(new VeBlib_RingData(ship.getLocation(), 20f, angle + 0f, ship.getCollisionRadius() * 2, 4f, shipColor));
//                    DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 20f, angle + 0f, ship.getSpriteAPI().getHeight(), shipColor);

                    for (float i = 0; i < 90; i += 22.5) {
                        DEA_Ring.add(new VeBlib_RingData(ship.getLocation(), 4f, VeBlib_Helper.GetPlusMinusAngle(angle, i), ship.getCollisionRadius() * 2, 4f, shipColor));
//                        DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle, i), ship.getSpriteAPI().getHeight(), shipColor);
                    }

                    DEA_Ring.add(new VeBlib_RingData(ship.getLocation(), 20f, angle2 + 0f, (float) (ship.getCollisionRadius() * 2 * 0.72), 4f, shipColor));
//                    DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 20f, angle2 + 0f, (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    for (int i = 30; i < 91; i += 30) {
                        DEA_Ring.add(new VeBlib_RingData(ship.getLocation(), 4f, VeBlib_Helper.GetPlusMinusAngle(angle2, i), (float) (ship.getCollisionRadius() * 2 * 0.72), 4f, shipColor));
//                        DEA_RenderHelper.DEA_DrawPolygonWHeightAndCenter(ship.getLocation(), 4f, DEA_Helper.DEA_GetPlusMinusAngle(angle2, i), (float) (ship.getSpriteAPI().getHeight() * 0.72), shipColor);
                    }


//                    empArcCount += Global.getCombatEngine().getElapsedInLastFrame();


//                    VeBlib_boxDataList.add(new VeBlib_BoxData(
//                            VeBlib_BoundsHelper.VeBlib_GetRandomBoundLocation(ship),
//                            VeBlib_BoundsHelper.VeBlib_GetRandomBoundLocation(ship),
//                            VeBlib_BoundsHelper.VeBlib_GetRandomBoundLocation(ship),
//                            VeBlib_BoundsHelper.VeBlib_GetRandomBoundLocation(ship),
//                            shipColor,
//                            GL_LINE
//                    ));

                    List<VeBlib_PolygonData> VeBlib_polygonDataList = new ArrayList<>();

//                    if (boundList == null) {
                    boundList = VeBlib_BoundsHelper.GetListOfBoundLocations(ship);
                    for (int i = 0; i < boundList.size(); i++) {
                        colorList.add(Color.white);
                    }
//                    }

//                    boundList2 = new ArrayList<>();

//                    boundList2.add(new Vector2f(0f, 60f));
//                    boundList2.add(new Vector2f(0f, 0f));
//                    boundList2.add(new Vector2f(60f, 0f));
//                    boundList2.add(new Vector2f(60f, 60f));

//                    VeBlib_polygonDataList.add(new VeBlib_PolygonData(boundList2, colorList, GL_LINE));

//add constructer to the plugin and put layers in it
                    plugin.ChangeRenderLayerInPlugin(CombatEngineLayers.BELOW_SHIPS_LAYER);
                    plugin.SquareInPlugin(VeBlib_boxDataList);
//                    plugin.VeBlib_PolygonInPlugin(VeBlib_polygonDataList);
                    plugin.Ring(DEA_Ring);


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
                                    VeBlib_EMPArcHelper.SpawnDamagingEMPArc(
                                            ship,
                                            ship.getLocation(),
                                            enemy,
                                            DEA_DamageBaseClass_DEA_PenteractSystem,
                                            3000f,
                                            "",
                                            4f,
                                            VeBlib_Statics.TRANSPARENT,
                                            VeBlib_Statics.TRANSPARENT
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
