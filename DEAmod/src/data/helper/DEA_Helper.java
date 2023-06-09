package data.helper;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.campaign.abilities.GraviticScanData;
import data.weapons.effects.DEA_FusionBeam_Effect;
import org.apache.log4j.Logger;
import org.lazywizard.lazylib.CollisionUtils;
import org.lazywizard.lazylib.MathUtils;
import org.lazywizard.lazylib.VectorUtils;
import org.lazywizard.lazylib.combat.CombatUtils;
import org.lazywizard.lazylib.combat.entities.SimpleEntity;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;
import org.magiclib.plugins.MagicFakeBeamPlugin;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import org.magiclib.util.MagicFakeBeam;
import org.w3c.dom.ranges.Range;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

import static data.helper.DEA_Statics.random;


public class DEA_Helper {

    public static int colorRed = 254;
    public static int colorGreen = 0;
    public static int colorBlue = 0;

    public static Color RainbowColor;

    /**
     * makes rainbow </br>
     * changes the color every time its called (will speed up if different ships use it at the same time(i suppose))
     */
    public static void DEA_Rainbow() {
        int b = 1;

        if (colorRed == 254 && !(colorGreen >= 254) && colorBlue == 0) {
            colorGreen = colorGreen + b;
        } else if (colorRed <= 254 && colorRed != 0 && colorGreen == 254 && colorBlue == 0) {
            colorRed = colorRed - b;
        } else if (colorRed <= 254 && colorGreen == 254 && colorBlue >= 0 && colorBlue < 254) {
            colorBlue = colorBlue + b;
        } else if (colorRed == 0 && colorGreen <= 254 && colorGreen > 0 && colorBlue == 254) {
            colorGreen = colorGreen - b;
        } else if (colorRed < 254 && colorGreen == 0 && colorBlue == 254) {
            colorRed = colorRed + b;
        } else if (colorRed == 254 && colorGreen == 0 && colorBlue > 0) {
            colorBlue = colorBlue - b;
        }
        RainbowColor = new Color(colorRed, colorGreen, colorBlue, 255);
    }

    /**
     * gets the color rainbow </br>
     *
     * @param alpha the alpha value MUST be between 0-255 AND int (0 being min 255 being max)
     * @return the current color of rainbow(gay)
     */
    public static Color DEA_RainbowWithAlpha(int alpha) {
        return new Color(RainbowColor.getRed(), RainbowColor.getGreen(), RainbowColor.getBlue(), alpha);
    }

    /**
     * did this because i didnt knew beam.getRayEndPrevFrame() gets the end point of the beam
     */
    public static Vector2f DEA_EndOfBeamGiver(BeamAPI beam) {
        return MathUtils.getPointOnCircumference(beam.getWeapon().getLocation(), beam.getLength(), beam.getWeapon().getCurrAngle());
    }



}
