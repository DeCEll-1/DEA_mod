package data.HELP;

import VeB.helper.render.renderClassesFolder.VeBlib_CircleData;
import VeB.helper.render.renderClassesFolder.VeBlib_RingData;
import VeB.helper.render.renderClassesFolder.VeBlib_RingData;
import VeB.helper.render.renderClassesFolder.VeBlib_SphereData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ViewportAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.opengl.DrawUtils;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class DEA_DrawEye {

    //this one is useless rn cuz doesnt work
//
//            SpriteAPI texture = Global.getSettings().getSprite("combatNuts", "observantEye");
//
//            List<VeBlib_SphereData> sphereList = new ArrayList<>();
//
//            sphereList.add(new VeBlib_SphereData(radius, stacks, slices, sphere, center, texture));

    public static List<VeBlib_CircleData> DEA_DrawEyeFull(Vector2f center, float radius, int segments, ShipAPI ship) {
        try {


            //~~powered by chatgpt~~
//powered by me :tf:
//


            List<VeBlib_CircleData> circleList = new ArrayList();


//            VeBlib_CircleData around = new VeBlib_CircleData(center, radius, segments, true, new Color(255, 255, 255, 100));//otuer eye
            VeBlib_CircleData small = new VeBlib_CircleData(MathUtils.getPointOnCircumference(center, (float) (radius * 0.4), VectorUtils.getAngle(ship.getLocation(), ship.getShipTarget().getLocation())), (float) (radius * 0.4), segments, true, Color.black);


//            circleList.add(around);
            circleList.add(small);

            return circleList;
        } catch (Exception ex) {
            return null;
        }
    }

}
