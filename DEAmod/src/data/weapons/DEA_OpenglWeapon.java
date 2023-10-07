package data.weapons;

import VeB.helper.VeBlib_Logger;
import VeB.helper.VeBlib_Statics;
import VeB.helper.render.VeBlib_RendererPlugin;
import VeB.helper.render.renderClassesFolder.VeBlib_TriangleData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.EveryFrameWeaponEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import data.classes.DEA_OpenglTriangleWeaponData;
import data.classes.DEA_Statics;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.ui.LazyFont;
import org.lwjgl.util.vector.Vector2f;

import java.util.*;
import java.util.List;

public class DEA_OpenglWeapon implements EveryFrameWeaponEffectPlugin {
    private VeBlib_RendererPlugin plugin = null;

    private ShipAPI ship = null;

    private boolean doOnce = true;

    private List<DEA_OpenglTriangleWeaponData> rightTriangleSlotsList = new ArrayList<>();

    @Override
    public void advance(float amount, CombatEngineAPI engine, WeaponAPI weapon) {
//        if (!weapon.getShip().isAlive()) return;

        if (plugin == null) {
            plugin = new VeBlib_RendererPlugin();
            Global.getCombatEngine().addLayeredRenderingPlugin(plugin);
        }


        if (weapon.getShip() != null) {
            if (doOnce) {
                ship = weapon.getShip();//gets ship
                ship.getSpriteAPI().setColor(VeBlib_Statics.TRANSPARENT);

//                if (true) return;

                //gets the weapon slots
                DEA_Statics.ShipsOpenGLWeaponSlots.clear();

                for (WeaponSlotAPI weaponSlot : ship.getHullSpec().getAllWeaponSlotsCopy()) {

                    if (weaponSlot.isDecorative() && weaponSlot.getRenderOrderMod() > 5 && Objects.equals(weaponSlot.getSlotSize().getDisplayName(), "Medium")) {//medium size is right triangles
                        rightTriangleSlotsList.add(new DEA_OpenglTriangleWeaponData(weaponSlot));
                    }

                }


                if (DEA_Statics.ShipsOpenGLWeaponSlots == null) DEA_Statics.ShipsOpenGLWeaponSlots = new HashMap<>();

                DEA_Statics.ShipsOpenGLWeaponSlots.put(weapon.getShip().getId(), rightTriangleSlotsList);

                doOnce = false;

            }


            List<VeBlib_TriangleData> triangleList = new ArrayList<>();


            try {


                //right angle slots
                for (DEA_OpenglTriangleWeaponData rightAngleSlotData : rightTriangleSlotsList) {

                    Vector2f corner1 = new Vector2f();

                    Vector2f corner2 = new Vector2f();

                    Vector2f corner3 = new Vector2f();

                    //the arc and order was in places of each other

                    float arc = rightAngleSlotData.renderOrder;//the lenght of the right side of the triangle

                    float order = rightAngleSlotData.arc;//the lenght of the left side of the triangle//the names are changed because i didnt gave the right names before and i dont want to change it in other places, itd confuse me and it doesnt hurt

                    float angle = rightAngleSlotData.angle + ship.getFacing() - 90f;

                    //angle + 90 = order render thing//idk what this means

                    corner1 = MathUtils.getPointOnCircumference(rightAngleSlotData.slot.computePosition(ship), arc, angle);//left corner from the 90 degree one//aka point A

                    corner2 = rightAngleSlotData.slot.computePosition(ship);//point B

                    corner3 = MathUtils.getPointOnCircumference(rightAngleSlotData.slot.computePosition(ship), order, angle + 90);//right corner from the 90 degree one//point C


                    boolean filled = true;//setting the filled

                    if (rightAngleSlotData.isHardpoint()) filled = true;//if its a hardpoint slot it will be filled
                    if (rightAngleSlotData.isTurret()) filled = false;//if its a turret slot it will only be lines
                    if (!rightAngleSlotData.isHidden() && (rightAngleSlotData.color != null || rightAngleSlotData.getTexture() != null)) {//if its a hidden slot it wont be drawn//so that you can change it with a script if you wish to//FUCK

                        if (rightAngleSlotData.getTexture() != null) {
                            triangleList.add(new VeBlib_TriangleData(corner1, corner2, corner3, rightAngleSlotData.color, filled, rightAngleSlotData.getTexture(), VeBlib_TriangleData.BOTTOM_LEFT));//add the triangle with texture
                        }

                        if (rightAngleSlotData.color != null && rightAngleSlotData.getTexture() == null) {
                            triangleList.add(new VeBlib_TriangleData(corner1, corner2, corner3, rightAngleSlotData.color, filled));//add the triangle with only color
                        }

                    }


                }
            } catch (Exception ex) {
//HELP
                VeBlib_Logger.log(DEA_OpenglWeapon.class, ex.toString());
            }


            plugin.TriangleInPlugin(triangleList);

        }

    }


}
