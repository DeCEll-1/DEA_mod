package data.weapons;

import DEA.helper.DEAlib_Logger;
import DEA.helper.DEAlib_Statics;
import DEA.helper.render.DEAlib_RendererPlugin;
import DEA.helper.render.renderClassesFolder.DEAlib_TriangleData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import com.thoughtworks.xstream.converters.Converter;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DEA_OpenglWeapon implements EveryFrameWeaponEffectPlugin {
    private DEAlib_RendererPlugin plugin = null;

    private ShipAPI ship = null;

    private boolean doOnce = true;

    private List<WeaponSlotAPI> rightTriangleSlotsList = new ArrayList<>();

    @Override
    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon) {


        if (plugin == null) {
            plugin = new DEAlib_RendererPlugin();
            Global.getCombatEngine().addLayeredRenderingPlugin(plugin);
        }


        if (weapon.getShip() != null) {
            if (doOnce) {
                ship = weapon.getShip();//gets ship
                ship.getSpriteAPI().setColor(DEAlib_Statics.TRANSPARENT);

//                if (true) return;

                //gets the weapon slots
                for (WeaponSlotAPI weaponSlot : ship.getHullSpec().getAllWeaponSlotsCopy()) {

                    if (weaponSlot.isDecorative() && weaponSlot.getRenderOrderMod() > 5 && Objects.equals(weaponSlot.getSlotSize().getDisplayName(), "Medium")) {//medium size is right triangles
                        rightTriangleSlotsList.add(weaponSlot);

                    }

                }

                doOnce = false;

            }

            List<DEAlib_TriangleData> triangleList = new ArrayList<>();

            try {


                //right angle slots
                for (WeaponSlotAPI rightAngleSlot : rightTriangleSlotsList) {

                    Vector2f corner1 = new Vector2f();

                    Vector2f corner2 = new Vector2f();

                    Vector2f corner3 = new Vector2f();

                    //the arc and order was in places of each other

                    float arc = rightAngleSlot.getRenderOrderMod();//the lenght of the right side of the triangle

                    float order = rightAngleSlot.getArc();//the lenght of the left side of the triangle

                    float angle = rightAngleSlot.getAngle() + ship.getFacing() - 90f;

                    //angle + 90 = order render thing

                    corner1 = MathUtils.getPointOnCircumference(rightAngleSlot.computePosition(ship), arc, angle);//left corner from the 90 degree one//aka point A

                    corner2 = rightAngleSlot.computePosition(ship);//point B

                    corner3 = MathUtils.getPointOnCircumference(rightAngleSlot.computePosition(ship), order, angle + 90);//right corner from the 90 degree one//point C


                    //getting the color
                    char[] slotID = rightAngleSlot.getId().toCharArray();

                    StringBuilder ColorAsHexBuilder = null;
                    StringBuilder AlphaBuilder = null;

                    boolean foundTag = false;

                    for (int i = 0; i < slotID.length; i++) {//getting the hex
//need a custom made value getter from string because hexes can have 2 extra letters that are for alpha and i cant just guess them nor force people to use only non transparent/only colors that have transparency so
                        if (slotID[i] == '#') {

                            foundTag = true;//for later

                            for (int j = 1; j < 7; j++) {//it haves 6 because a hex without alpha is 6 characters

                                if (ColorAsHexBuilder == null) {
                                    ColorAsHexBuilder = new StringBuilder();//makes the builder here for null check of it later in the code
                                    ColorAsHexBuilder.append('#');//decoder needs either 0x of #
                                }


                                ColorAsHexBuilder.append(slotID[i + j]);//add the character to the builder

                                try {//in try catch for hexes without alpha value
                                    if (j == 6) {//at the end of the opaque hex this will get the last 2 characters which carry the alpha value//if it doesnt have alpha value it will crash
                                        AlphaBuilder = new StringBuilder();
                                        AlphaBuilder.append(slotID[i + j + 1]);
                                        AlphaBuilder.append(slotID[i + j + 2]);
                                    }
                                } catch (Exception ex) {
                                }

                            }

                        }

                    }

                    Color ColorOfTheTriangleWOAlpha = null;

                    Color Alpha = new Color(0, 0, 255, 255);//setting so if it doesnt gets set after it will still work
                    if (!foundTag) {
                        ColorOfTheTriangleWOAlpha = Color.white;//if the triangle is colorless it will be white
                    } else {
                        try {//color is rather weird so try catch

                            String ColorAsHexString = "";
                            ColorAsHexString = ColorAsHexBuilder.toString();//get normal color

                            if (AlphaBuilder != null) {
                                Alpha = Color.decode("#000000" + AlphaBuilder.toString());//get alpha//in if because for the hexes without alpha value;
                            }


                            ColorOfTheTriangleWOAlpha = Color.decode(ColorAsHexString);//decodes normal color, didnt combined alpha with this because i changed it to set alpha externall and i dont want to change it// also because of the hexes without alpha it might act weird

//                            String s = "";//debug stuff

                        } catch (Exception ex) {
                            ColorOfTheTriangleWOAlpha = Color.white;//it will be setten white if something happens
                        }
                    }


                    Color ColorOfTheTriangle = new Color(ColorOfTheTriangleWOAlpha.getRed(), ColorOfTheTriangleWOAlpha.getGreen(), ColorOfTheTriangleWOAlpha.getBlue(), Alpha.getBlue());//combines alpha and color


                    boolean filled = true;//setting the filled


                    if (rightAngleSlot.isHardpoint()) filled = true;//if its a hardpoint slot it will be filled
                    if (rightAngleSlot.isTurret()) filled = false;//if its a turret slot it will only be lines
                    if (!rightAngleSlot.isHidden()) {//if its a hidden slot it wont be drawn//so that you can change it with a script if you wish to
                        triangleList.add(new DEAlib_TriangleData(corner1, corner2, corner3, ColorOfTheTriangle, filled));//add the triangle
                    }


                }
            } catch (Exception ex) {
//HELP
                DEAlib_Logger.log(DEA_OpenglWeapon.class, ex.toString());
            }

//            try {
//
//
//                Iterator<DEAlib_TriangleData> triangleDataOldIterator = triangleListOld.listIterator();
//
//                for (DEAlib_TriangleData newTriangle : triangleListNew) {
//
//
//                    DEAlib_TriangleData oldTriangle = null;
//
//                    if (triangleDataOldIterator.hasNext()) {
//                        oldTriangle = triangleDataOldIterator.next();
//                    }
//
//                    triangleList.add(new DEAlib_TriangleData(
//                            AvarageVectors(oldTriangle.corner1, newTriangle.corner1),
//                            AvarageVectors(oldTriangle.corner2, newTriangle.corner2),
//                            AvarageVectors(oldTriangle.corner3, newTriangle.corner3),
//                            Color.WHITE,
//                            true
//                    ));
//
//                }
//            } catch (Exception ex) {
//                DEAlib_Logger.DEA_log(DEA_OpenglWeapon.class, "HELP");
//            }

            plugin.TriangleInPlugin(triangleList);

        }

    }





}
