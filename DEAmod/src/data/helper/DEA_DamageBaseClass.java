package data.helper;

import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.impl.campaign.graid.SpecialItemRaidObjectivePluginImpl;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;

public class DEA_DamageBaseClass {

    public DEA_DamageBaseClass(float damage, float empDamage, DamageType damageType) {
        this.Damage = damage;
        this.EMPDamage = empDamage;
        this.DamageType = damageType;
    }

    public DEA_DamageBaseClass(float damage, float empDamage, DamageType damageType, DamagingExplosionSpec damagingExplosionSpec) {
        this.Damage = damage;
        this.EMPDamage = empDamage;
        this.DamageType = damageType;
        this.DamagingExplosionSpec = damagingExplosionSpec;
    }

    public DEA_DamageBaseClass() {
    }

    public float Damage;
    public float EMPDamage;
    public DamageType DamageType;

    public DamagingExplosionSpec DamagingExplosionSpec;


}
