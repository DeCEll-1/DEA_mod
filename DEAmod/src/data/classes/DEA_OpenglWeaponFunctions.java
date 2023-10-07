package data.classes;

import VeB.helper.VeBlib_ColorHelper;
import VeB.helper.VeBlib_Statics;
import com.fs.starfarer.api.combat.ShipAPI;

import java.awt.*;
import java.util.List;
import java.util.UUID;

import static data.classes.DEA_Statics.ShipsOpenGLWeaponSlots;

public class DEA_OpenglWeaponFunctions {

    public static void randomiseAlphaOfWeaponSlots(ShipAPI ship) {

        if (ShipsOpenGLWeaponSlots.get(ship.getId()) == null) return;

        List<DEA_OpenglTriangleWeaponData> openglWeaponsList = ShipsOpenGLWeaponSlots.get(ship.getId());


        for (DEA_OpenglTriangleWeaponData weaponSlot : openglWeaponsList) {

            weaponSlot.color = new Color(weaponSlot.color.getRed(), weaponSlot.color.getGreen(), weaponSlot.color.getBlue(), VeBlib_Statics.random.nextInt(255));

//            weaponSlot.makeSlotHardpoint();

        }
    }

    public static void randomiseColorOfWeaponSlots(ShipAPI ship) {

        if (ShipsOpenGLWeaponSlots.get(ship.getId()) == null) return;

        List<DEA_OpenglTriangleWeaponData> openglWeaponsList = ShipsOpenGLWeaponSlots.get(ship.getId());


        for (DEA_OpenglTriangleWeaponData weaponSlot : openglWeaponsList) {

            weaponSlot.color = VeBlib_ColorHelper.randomColor(true);

//            weaponSlot.makeSlotHardpoint();

        }
    }

    public static void resetSlots(ShipAPI ship) {

        if (ShipsOpenGLWeaponSlots.get(ship.getId()) == null) return;

        List<DEA_OpenglTriangleWeaponData> openglWeaponsList = ShipsOpenGLWeaponSlots.get(ship.getId());


        for (DEA_OpenglTriangleWeaponData weaponSlot : openglWeaponsList) {

            weaponSlot.resetSlot();

        }
    }

    public static void makeAllSlotsHidden(ShipAPI ship) {
        if (ShipsOpenGLWeaponSlots.get(ship.getId()) == null) return;

        List<DEA_OpenglTriangleWeaponData> openglWeaponsList = ShipsOpenGLWeaponSlots.get(ship.getId());


        for (DEA_OpenglTriangleWeaponData weaponSlot : openglWeaponsList) {

            weaponSlot.makeSlotHidden();

        }
    }

    public static void makeAllSlotsTurrets(ShipAPI ship) {
        if (ShipsOpenGLWeaponSlots.get(ship.getId()) == null) return;

        List<DEA_OpenglTriangleWeaponData> openglWeaponsList = ShipsOpenGLWeaponSlots.get(ship.getId());


        for (DEA_OpenglTriangleWeaponData weaponSlot : openglWeaponsList) {

            weaponSlot.makeSlotTurret();

        }
    }

    public static void makeAllSlotsHardpoints(ShipAPI ship) {
        if (ShipsOpenGLWeaponSlots.get(ship.getId()) == null) return;

        List<DEA_OpenglTriangleWeaponData> openglWeaponsList = ShipsOpenGLWeaponSlots.get(ship.getId());


        for (DEA_OpenglTriangleWeaponData weaponSlot : openglWeaponsList) {

            weaponSlot.makeSlotHardpoint();

        }
    }

}
