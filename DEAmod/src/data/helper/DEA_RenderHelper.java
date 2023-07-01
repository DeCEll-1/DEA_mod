package data.helper;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipAPI;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lwjgl.opengl.GL11;
import com.fs.starfarer.api.combat.ViewportAPI;
import org.lwjgl.util.vector.Vector2f;
import cmu.gui.CMUKitUI;
import org.magiclib.util.MagicFakeBeam;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class DEA_RenderHelper {


    /**
     * makes fake beam circle (i dont know how to make a circle) suggested to use 30 but go balls lol</br>
     * <p>
     * MAX SİDES İS 360F the method wont do anything if you put more than that and return false</br>
     *
     * @param center      center of the circle
     * @param sides       amount of sides the "circle" will have
     * @param circleAngle angle of the circle, yk i named this circle but you can use this to make triangles and stuff too so thats for this, can be 0 but cant be more than 360
     * @param height      height of the circle
     * @param lineColor   color of the line
     * @return if the proccess has been completed successfully it returns COMPLETED, othervise DEA_ERROR + errText so you have to do .includes("DEA_ERROR") or smthin like that for validation
     */
    public static String DEA_DrawPolygonWithHeight(Vector2f center, float sides, float circleAngle, float height, java.awt.Color lineColor) {
        String errText = "    *-*   ";
        try {
            if (sides > 360f || circleAngle > 360f) {
                return "DEA_ERROR please put less than 360 and more than 0";
            }
            errText += "passed side validation";
            errText += "    *-*   ";


            Vector2f from = new Vector2f();
            Vector2f to = new Vector2f();

            from = MathUtils.getPointOnCircumference(center, height, circleAngle);
            errText += "getPointOnCircumference";
            errText += "    *-*   ";
            int z = 0;

            for (float i = circleAngle; i < circleAngle + 360 + sides; i += 360 / sides) {
                if (z % 2 == 0) {
                    to = MathUtils.getPointOnCircumference(center, height, i);
                } else {
                    from = MathUtils.getPointOnCircumference(center, height, i);
                }
                DEA_DrawLine(from, to, lineColor);
                z++;
                errText += "for " + z;
                errText += "    *-*   ";
            }
            errText += "for has ended";
            errText += "    *-*   ";
            return "COMPLETED";
        } catch (Exception ex) {
            return "DEA_ERROR" + errText;
        }
    }

    /**
     * makes fake beam circle (i dont know how to make a circle) suggested to use 30 but go balls lol</br>
     * <p>
     * MAX NUMBER İS 360F the method wont do anything if you put more than that and return false</br>
     *
     * @param from      center of the circle
     * @param to        amount of sides the "circle" will have
     * @param lineColor color of the line
     * @return if the proccess has been completed successfully it returns COMPLETED, othervise DEA_ERROR + errText so you have to do .includes("DEA_ERROR") or smthin like that for validation
     */
    public static String DEA_DrawLine(Vector2f from, Vector2f to, Color lineColor) {
        String errText = "    *-*   ";
        try {

//            DEA_CombatLayeredRenderingPlugin dea_combatLayeredRenderingPlugin = new DEA_CombatLayeredRenderingPlugin();
//
//            Global.getCombatEngine().addLayeredRenderingPlugin(dea_combatLayeredRenderingPlugin);

            ViewportAPI viewport = Global.getCombatEngine().getViewport();


            CMUKitUI.openGLForMisc();

            errText += "CMUKitUI.openGLForMisc();    *-*   ";

            glBegin(GL_LINE_STRIP);

            errText += "GL11.glBegin(GL_LINE_STRIP);    *-*   ";

            glColor4f(255 / lineColor.getRed(), 255 / lineColor.getGreen(), 255 / lineColor.getBlue(), 255 / lineColor.getAlpha());
            glLineWidth(30000f);

            glVertex2f(viewport.convertWorldXtoScreenX(from.x), viewport.convertWorldYtoScreenY(from.y));
            glVertex2f(viewport.convertWorldXtoScreenX(to.x), viewport.convertWorldYtoScreenY(to.y));
            errText += " glVertex2f(viewport.convertWorldXtoScreenX(from.x), viewport.convertWorldYtoScreenY(from.y));, glVertex2f(viewport.convertWorldXtoScreenX(to.x), viewport.convertWorldYtoScreenY(to.y));    *-*   ";
//            GL11.glVertex2f(0f, 0f);
//            GL11.glVertex2f(1000f, 1000f);
//            GL11.glFlush()//this is unneeded since the game already makes this
            glEnd();
            errText += "GL11.glEnd();    *-*   ";

//            DEA_Logger.DEA_log(DEA_RenderHelper.class, "viewport.convertWorldXtoScreenX(from.x):", Float.toString(viewport.convertWorldXtoScreenX(from.x)));
//            DEA_Logger.DEA_log(DEA_RenderHelper.class, "viewport.convertWorldYtoScreenY(from.y)", Float.toString(viewport.convertWorldYtoScreenY(from.y)));
//            DEA_Logger.DEA_log(DEA_RenderHelper.class, "viewport.convertWorldXtoScreenX(to.x):", Float.toString(viewport.convertWorldXtoScreenX(to.x)));
//            DEA_Logger.DEA_log(DEA_RenderHelper.class, "viewport.convertWorldYtoScreenY(to.y):", Float.toString(viewport.convertWorldYtoScreenY(to.y)));

            CMUKitUI.closeGLForMisc();
            errText += "CMUKitUI.closeGLForMisc();    *-*   ";

//            glPushAttrib(GL_ALL_ATTRIB_BITS);
//
//            int blendFunc1 = GL11.GL_SRC_ALPHA;
//            int blendFunc2 = GL11.GL_ONE_MINUS_SRC_ALPHA;
//
//            GL11.glEnable(GL_LINE);
//
//            GL11.glEnable(GL_BLEND);
//
//            GL11.glBlendFunc(blendFunc1, blendFunc2);
//
//            GL11.glBegin(GL_LINE);
//
//
//            GL11.glColor4f(1f, 0f, 0f, 1f);
//            GL11.glVertex2f(0f, 0f);
//            GL11.glColor4f(1f, 0f, 0f, 1f);
//            GL11.glVertex2f(1000f, 1000f);
//            glEnd();
//            GL11.glPopMatrix();
//            glPopAttrib();
            return "COMPLETED";
        } catch (Exception ex) {
            return "DEA_ERROR" + errText;
        }
    }


}
