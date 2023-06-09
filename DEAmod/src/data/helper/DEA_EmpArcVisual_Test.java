package data.helper;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.combat.CombatViewport;
import com.fs.starfarer.combat.entities.BaseEntity;
import org.lwjgl.util.vector.Vector2f;
import com.fs.starfarer.prototype.Utils;
import java.lang.Object;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import java.awt.*;
import java.util.EnumSet;

import com.fs.starfarer.loading.specs.W;
import com.fs.starfarer.renderers.damage.C;
import com.fs.starfarer.combat.systems.D;

import com.fs.starfarer.loading.specs.voidsuper.o;
import com.fs.starfarer.loading.specs.voidsuper;

//this was to see how spawnEmpArcVisual was working but the obsfructed code (idk how to write that) is very hard to understand and i dont know what most of the things mean
//see obf.txt for the classes i found
public class DEA_EmpArcVisual_Test{//originally had  extends BaseEntity implements CombatEntityAPI, EmpArcEntityAPI
    private D.oo graphic;
    private C flicker1 = new C();
    private Vector2f from;
    private Vector2f to;
    private Vector2f fromOffset;
    private Vector2f toOffset;
    private CombatEntityAPI fromAnchor;
    private CombatEntityAPI toAnchor;
    private voidsuper fromSlot;
    private voidsuper toSlot;
    private float coreWidthOverride = -1.0F;
    private float startLength;
    private EnumSet<CombatEngineLayers> layers;
    public DEA_EmpArcVisual_Test(Vector2f from, CombatEntityAPI fromAnchor, Vector2f to, CombatEntityAPI toAnchor,
                                 float thickness, Color fringe, Color core) {
        this.layers = EnumSet.of(CombatEngineLayers.ABOVE_SHIPS_AND_MISSILES_LAYER);
        this.from = new Vector2f(from);
        this.to = new Vector2f(to);
        this.fromAnchor = fromAnchor;
        this.toAnchor = toAnchor;
        if (fromAnchor != null) {
            this.fromOffset = Vector2f.sub(from, fromAnchor.getLocation(), new Vector2f());
//            this.fromOffset = Utils.super(this.fromOffset, -fromAnchor.getFacing());//god knows why this gives error
            this.fromSlot = new voidsuper("blah", WeaponType.SYSTEM, WeaponSize.SMALL, o.Ô00000, new Vector2f(), new W("blah", this.fromOffset), 0.0F, 90.0F);
        }

        if (toAnchor != null) {
            this.toOffset = Vector2f.sub(to, toAnchor.getLocation(), new Vector2f());
//            this.toOffset = Utils.super(this.toOffset, -toAnchor.getFacing());//god knows why this gives error
            this.toSlot = new voidsuper("blah", WeaponType.SYSTEM, WeaponSize.SMALL, o.Ô00000, new Vector2f(), new W("blah", this.toOffset), 0.0F, 90.0F);
        }

        this.graphic = new D.oo(from, to, fringe, core);
        this.graphic.o00000(thickness);
        this.graphic.o00000(true);
        this.startLength = Misc.getDistance(from, to);
//        this.getLocation().set(from);//look line 25
    }

    public void advance(float var1) {
        this.flicker1.o00000(var1 * 0.8F);
    }

    public void render(CombatEngineLayers var1, CombatViewport var2) {
        if (var2.isNearViewport(this.from, this.startLength) || var2.isNearViewport(this.to, this.startLength)) {
            float var3 = var2.getAlphaMult();
            float var4 = this.flicker1.Ó00000() * var3;
            this.graphic.Ó00000(this.coreWidthOverride);
            Vector2f var5 = new Vector2f(this.from);
            Vector2f var6 = new Vector2f(this.to);
            if (this.fromOffset != null) {
                var5 = this.fromSlot.computePosition(this.fromAnchor);
            }

            if (this.toOffset != null) {
                var6 = this.toSlot.computePosition(this.toAnchor);
            }

            this.graphic.o00000(var5, var6, Math.min(1.0F, var4 * 1.5F));
        }
    }

    public WeaponGroupAPI applyDamage(Vector2f var1, CombatEntityAPI var2, boolean var3, float var4, Object var5) {
        return null;
    }

    public void cleanup() {
    }

    public EnumSet<CombatEngineLayers> getActiveLayers() {
        return this.layers;
    }

    public void init() {
    }

    public boolean isExpired() {
        return this.flicker1.Ó00000() <= 0.0F;
    }

    public float getCoreWidthOverride() {
        return this.coreWidthOverride;
    }

    public void setCoreWidthOverride(float var1) {
        this.coreWidthOverride = var1;
    }

    public void setTargetToShipCenter(Vector2f var1, ShipAPI var2) {
    }

    public ShipAPI getSource() {
        return null;
    }

    public Vector2f getTargetLocation() {
        Vector2f var1 = new Vector2f(this.to);
        if (this.toOffset != null) {
            var1 = this.toSlot.computePosition(this.toAnchor);
        }

        return var1;
    }

    public void setSingleFlickerMode() {
        this.flicker1.Ô00000();
    }
}
