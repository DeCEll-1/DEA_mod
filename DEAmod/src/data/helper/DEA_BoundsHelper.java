package data.helper;

import com.fs.starfarer.api.combat.BoundsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;

import static data.helper.DEA_Statics.random;

public class DEA_BoundsHelper {

    /**
     * gets a random bound </br>
     *
     * @param ship the ship
     * @return if the proccess completed successfully returns the location of the bound,othervise null. so instead of crashing it just, doesnt work
     */
    public static Vector2f DEA_GetRandomBoundLocation(ShipAPI ship) {
        try {
            BoundsAPI.SegmentAPI segment = ship.getExactBounds().getSegments().get(random.nextInt(ship.getExactBounds().getSegments().size()));
            Vector2f segmentLocation = MathUtils.getNearestPointOnLine(ship.getLocation(), segment.getP1(), segment.getP2());
            return segmentLocation;
        } catch (Exception ex) {
            return null;
        }
    }
}
