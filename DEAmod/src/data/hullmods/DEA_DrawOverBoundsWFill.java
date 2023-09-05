package data.hullmods;

import DEA.helper.DEAlib_BoundsHelper;
import DEA.helper.render.DEAlib_RendererPlugin;
import DEA.helper.render.renderClassesFolder.DEAlib_PolygonData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.ShipAPI;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FILL;

public class DEA_DrawOverBoundsWFill extends BaseHullMod {

    private DEAlib_RendererPlugin plugin = null;

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

    }

    public void advanceInCombat(ShipAPI ship, float amount) {
        try {

            if (plugin == null) {
                plugin = new DEAlib_RendererPlugin();
                Global.getCombatEngine().addLayeredRenderingPlugin(plugin);
            }

            ship.getExactBounds().update(ship.getLocation(), ship.getFacing());

            List<DEAlib_PolygonData> dealib_polygonDataList = new ArrayList<>();

            List<Vector2f> boundList = DEAlib_BoundsHelper.GetListOfBoundLocations(ship);

            List<Color> colorList = new ArrayList<>();

            for (int i = 0; i < boundList.size(); i++) {
                colorList.add(Color.white);
            }

            dealib_polygonDataList.add(new DEAlib_PolygonData(boundList, colorList, true));

            plugin.PolygonInPlugin(dealib_polygonDataList);
            plugin.ChangeRenderLayerInPlugin(CombatEngineLayers.ABOVE_PARTICLES);


        } catch (Exception ex) {
            return;
        }
    }


}
