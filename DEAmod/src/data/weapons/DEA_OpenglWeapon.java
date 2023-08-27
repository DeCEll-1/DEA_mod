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

    private Vector2f corner1Last = new Vector2f();
    private Vector2f corner2Last = new Vector2f();
    private Vector2f corner3Last = new Vector2f();
    private Vector2f ComputedPositionLast = new Vector2f();

    private List<DEAlib_TriangleData> triangleListNew = new ArrayList<>();
    private List<DEAlib_TriangleData> triangleListOld = new ArrayList<>();


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

                    if (weaponSlot.isDecorative() && weaponSlot.getRenderOrderMod() > 5 && Objects.equals(weaponSlot.getSlotSize().getDisplayName(), "Medium")) {
                        rightTriangleSlotsList.add(weaponSlot);

                    }

                }

                doOnce = false;

            }


            //right angle slots
            for (WeaponSlotAPI rightAngleSlot : rightTriangleSlotsList) {

                Vector2f corner1new = new Vector2f();

                Vector2f corner2new = new Vector2f();

                Vector2f corner3new = new Vector2f();

                //the arc and order was in places of each other

                float arc = rightAngleSlot.getRenderOrderMod();//the lenght of the right side of the triangle

                float order = rightAngleSlot.getArc();//the lenght of the left side of the triangle

                float angle = rightAngleSlot.getAngle() + ship.getFacing() - 90f;

                //angle + 90 = order render thing

                corner1new = MathUtils.getPointOnCircumference(rightAngleSlot.computePosition(ship), arc, angle);//left corner from the 90 degree one//aka point A

                corner2new = rightAngleSlot.computePosition(ship);//point B

                corner3new = MathUtils.getPointOnCircumference(rightAngleSlot.computePosition(ship), order, angle + 90);//right corner from the 90 degree one//point C

                triangleListNew.add(new DEAlib_TriangleData(corner1new, corner2new, corner3new, Color.WHITE, false));

            }


            List<DEAlib_TriangleData> triangleList = new ArrayList<>();

            try {


                Iterator<DEAlib_TriangleData> triangleDataOldIterator = triangleListOld.listIterator();

                for (DEAlib_TriangleData newTriangle : triangleListNew) {


                    DEAlib_TriangleData oldTriangle = null;

                    if (triangleDataOldIterator.hasNext()) {
                        oldTriangle = triangleDataOldIterator.next();
                    }

                    triangleList.add(new DEAlib_TriangleData(
                            AvarageVectors(oldTriangle.corner1, newTriangle.corner1),
                            AvarageVectors(oldTriangle.corner2, newTriangle.corner2),
                            AvarageVectors(oldTriangle.corner3, newTriangle.corner3),
                            Color.WHITE,
                            true
                    ));

                }
            } catch (Exception ex) {
                DEAlib_Logger.DEA_log(DEA_OpenglWeapon.class, "HELP");
            }

            plugin.DEAlib_TriangleInPlugin(triangleList);

            triangleListOld = triangleListNew;


            triangleListNew = new ArrayList<>();

        }

    }

    private Vector2f AvarageVectors(Vector2f firstVector, Vector2f secondVector) {

        Vector2f avaragedVector = new Vector2f();


        avaragedVector.x = (firstVector.x + secondVector.x) / 2f;
        avaragedVector.y = (firstVector.y + secondVector.y) / 2f;

        return avaragedVector;

    }

}
