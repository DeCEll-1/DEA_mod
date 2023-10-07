package data.DEATH;

import VeB.helper.VeBlib_BoundsHelper;
import VeB.helper.render.VeBlib_RendererPlugin;
import VeB.helper.render.renderClassesFolder.VeBlib_PolygonData;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FILL;

public class DEA_DrawOverBoundsWFill extends BaseHullMod {

    //is USELESS

    private VeBlib_RendererPlugin plugin = null;

    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {

    }

    public void advanceInCombat(ShipAPI ship, float amount) {
        try {


            if (plugin == null) {
                plugin = new VeBlib_RendererPlugin();
                Global.getCombatEngine().addLayeredRenderingPlugin(plugin);
            }

            ship.getExactBounds().update(ship.getLocation(), ship.getFacing());

            List<VeBlib_PolygonData> VeBlib_polygonDataList = new ArrayList<>();

            List<Vector2f> boundList = VeBlib_BoundsHelper.GetListOfBoundLocations(ship);

            List<Color> colorList = new ArrayList<>();

            for (int i = 0; i < boundList.size(); i++) {
                colorList.add(Color.white);
            }

            VeBlib_polygonDataList.add(new VeBlib_PolygonData(boundList, colorList, true));

            plugin.PolygonInPlugin(VeBlib_polygonDataList);
            plugin.ChangeRenderLayerInPlugin(CombatEngineLayers.ABOVE_PARTICLES);

        } catch (Exception ex) {
            return;
        }
    }

    @Override
    public Color getNameColor() {
        return new Color(999, 999, 999, 999);
        //return Color.red;
    }

//    public boolean showInRefitScreenModPickerFor(ShipAPI ship) {
//        if (ship.getHullSpec().getManufacturer() == "tritachyon") return false;//dont show on refit if its triatch
//        else return true;
//    }

}
