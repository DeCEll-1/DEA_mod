package data.classes;

import VeB.helper.VeBlib_StringHelper;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.loading.WeaponSlotAPI;

import java.awt.*;
import java.util.UUID;

public class DEA_OpenglTriangleWeaponData {

    //use map data type
    //get the weapons data and put it in a static map in a file (make a custom class)
    //after that change the data of it however you want


    //regarding custom class put the ship , a weapon data list (custom class) and other stuff that i dont think of right now
    public DEA_OpenglTriangleWeaponData(WeaponSlotAPI slot) {
        this.slot = slot;
//        this.ship = ship;
        this.arc = slot.getArc();
        this.renderOrder = slot.getRenderOrderMod();
        this.angle = slot.getAngle();
        this.x = slot.getLocation().x;
        this.y = slot.getLocation().y;

        this.slotIDPrefix = UUID.randomUUID().toString();

        try {//sets the color
            this.color = VeBlib_StringHelper.getColorFromHexString(slot.getId());
        } catch (Exception ex) {
            color = null;
        }

        try {//sets the texture
            this.textureID = VeBlib_StringHelper.getStringBetween2Keys(slot.getId(), '$', '$');

            if (textureID.equals("")) {
                textureID = "NO_TEXTURE_PATH";
                throw new Exception("noPath");
            }

            this.texture = Global.getSettings().getSprite("openGL", textureID);
        } catch (Exception ex) {
            texture = null;
            textureID = "NO_TEXTURE_PATH";
        }


        if (slot.isHardpoint()) hardpoint = true;
        if (slot.isTurret()) turret = true;
        if (slot.isHidden()) hidden = true;

        updateIDVariable();

    }

    public void resetSlot() {
//        this.ship = ship;
        this.arc = slot.getArc();
        this.renderOrder = slot.getRenderOrderMod();
        this.angle = slot.getAngle();
        this.x = slot.getLocation().x;
        this.y = slot.getLocation().y;

        this.slotIDPrefix = UUID.randomUUID().toString();

        try {//sets the color
            this.color = VeBlib_StringHelper.getColorFromHexString(slot.getId());
        } catch (Exception ex) {
            color = null;
        }

        try {//sets the texture
            this.textureID = VeBlib_StringHelper.getStringBetween2Keys(slot.getId(), '$', '$');

            if (textureID.equals("")) {
                textureID = "NO_TEXTURE_PATH";
                throw new Exception("noPath");
            }


            this.texture = Global.getSettings().getSprite(textureID);
        } catch (Exception ex) {
            texture = null;
            textureID = "NO_TEXTURE_PATH";
        }


        if (slot.isHardpoint()) hardpoint = true;
        if (slot.isTurret()) turret = true;
        if (slot.isHidden()) hidden = true;

        updateIDVariable();
    }


    private void updateIDVariable() {
        id = slotIDPrefix +//UUID
                VeBlib_StringHelper.ColorToHexString(color, true) +//color as hex
                "$" + textureID + "$";//texture path
    }


    public WeaponSlotAPI slot;

    private String id;

    public SpriteAPI getTexture() {//returns texture
        return texture;
    }

    private SpriteAPI texture;

    public void changeTexturePath(String newPath) {//changes textures path and updates the texture, doesnt need $ prepostkey
        this.textureID = newPath;
        texture = Global.getSettings().getSprite(newPath);
        updateIDVariable();
    }

    private String textureID;

    public Color color = Color.WHITE;

//    public ShipAPI ship;//

    //region triangle stuff

    public float arc;//
    //for right angle weapons:
    //the lenght of the left side
    //     t |\
    //     h | \
    //     i |  \
    //     s L___\
    //      side is the left side of the right angle

    public float renderOrder;//
    //for right angle weapons:
    //the lenght of the right side
    //      |\
    //      | \
    //      |  \
    //      L___\
    //       ^^this side is the right side of the right angle

    public float angle;//
    //for right angle weapons:
    //left sides angle of the triangle from the right angled corner aka:
    //     t |\
    //     h | \
    //     i |  \
    //     s L___\
    //      side is the left side of the right angle

    //endregion

    public float x;//should be relative to ship

    public float y;//should be relative to ship

    public String slotIDPrefix;


    public boolean isPhaseSlot;//if its a "phase slot" it will do fune stuff while phasing//dont do anything on ships without phase just does another if check(bad)

    //region drawing style (aka slot type)

    private boolean hardpoint = false;//is filled?

    public boolean isHardpoint() {
        return hardpoint;
    }

    public void makeSlotHardpoint() {
        hardpoint = true;
        turret = false;
        hidden = false;
    }

    private boolean turret = false;//is it not filled and only outer lines?

    public boolean isTurret() {
        return turret;
    }

    public void makeSlotTurret() {
        hardpoint = false;
        turret = true;
        hidden = false;
    }

    private boolean hidden = false;//will the thing get rendered?

    public boolean isHidden() {
        return hidden;
    }

    public void makeSlotHidden() {
        hardpoint = false;
        turret = false;
        hidden = true;
    }

    //endregion

}
