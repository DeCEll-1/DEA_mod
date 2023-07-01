package data.plugins;

import cmu.gui.CMUKitUI;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.CombatLayeredRenderingPlugin;
import com.fs.starfarer.api.combat.ViewportAPI;
import data.helper.DEA_RenderHelper;
import org.lwjgl.util.vector.Vector2f;

import java.util.EnumSet;

import static org.lwjgl.opengl.GL11.*;

public class DEA_CombatLayeredRenderingPlugin implements CombatLayeredRenderingPlugin {
    //tbf fuck render plugins standalone is the best (bkz DEA_RenderHelper)
    /**
     * @param entity
     */
    @Override
    public void init(CombatEntityAPI entity) {

    }

    /**
     *
     */
    @Override
    public void cleanup() {

    }

    /**
     * @return
     */
    @Override
    public boolean isExpired() {
        return false;
    }

    /**
     * @param amount
     */
    @Override
    public void advance(float amount) {

    }

    /**
     * @return
     */
    @Override
    public EnumSet<CombatEngineLayers> getActiveLayers() {
        return EnumSet.of(CombatEngineLayers.JUST_BELOW_WIDGETS);
    }

    /**
     * @return
     */
    @Override
    public float getRenderRadius() {
        return 1000f;
    }

    /**
     * @param layer
     * @param viewport
     */
    @Override
    public void render(CombatEngineLayers layer, ViewportAPI viewport) {
//
////        DEA_RenderHelper.DEA_DrawLineCombat(new Vector2f(),new Vector2f());
//        CMUKitUI.openGLForMisc();
//
//        glColor4f(1f, 0f, 0f, 1f);
//        glBegin(GL_LINE);
//
//        glLineWidth(1000f);
//
////            glVertex2f(viewport.convertWorldXtoScreenX(from.x), viewport.convertWorldYtoScreenY(from.y));
////            glVertex2f(viewport.convertWorldXtoScreenX(to.x), viewport.convertWorldYtoScreenY(to.y));
//        glVertex2f(0f, 0f);
//        glVertex2f(1000f, 1000f);
////            GL11.glFlush()//this is unneeded since the game already makes this
//        glEnd();
//
//        CMUKitUI.closeGLForMisc();
    }
}
