package data.AI;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.combat.entities.Ship;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lwjgl.util.vector.Vector2f;

public class DEA_UseSystemIfInRange implements ShipSystemAIScript {

    private ShipAPI ship = null;
    private ShipSystemAPI system = null;
    private ShipwideAIFlags flags = null;
    private CombatEngineAPI engine = null;

    /**
     * @param ship
     * @param system
     * @param flags
     * @param engine
     */
    @Override
    public void init(ShipAPI ship, ShipSystemAPI system, ShipwideAIFlags flags, CombatEngineAPI engine) {
        this.ship = ship;
        this.system = system;
        this.flags = flags;
        this.engine = engine;
    }

    /**
     * @param amount
     * @param missileDangerDir
     * @param collisionDangerDir
     * @param target
     */
    @Override
    public void advance(float amount, Vector2f missileDangerDir, Vector2f collisionDangerDir, ShipAPI target) {

        for (CombatEntityAPI enemy : CombatUtils.getEntitiesWithinRange(ship.getLocation(), 2500f)) {

            if (enemy instanceof ShipAPI && enemy != ship && !((ShipAPI) enemy).isAlly()) {

                ship.useSystem();
                break;
            }
        }
    }
}
