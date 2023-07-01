package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.WeaponAPI;

public class DEA_NoWeaponDisabling  extends BaseHullMod {

    @Override
    public void advanceInCombat(ShipAPI ship, float amount) {

        for (WeaponAPI weapon : ship.getAllWeapons()) {

            if (weapon.isDisabled()){
                weapon.repair();
            }

        }

    }


}
