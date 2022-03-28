package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.api.interfaces.IPAZEntity;
import com.hungteen.pvz.utils.misc.WeightList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:03
 **/
public abstract class PVZPAZ extends PVZMob implements IPAZEntity {

    private static final EntityDataAccessor<Integer> STATES = SynchedEntityData.defineId(PVZPAZ.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<CompoundTag> SKILLS = SynchedEntityData.defineId(PVZPAZ.class, EntityDataSerializers.COMPOUND_TAG);
    private static final EntityDataAccessor<Integer> EXIST_TICK = SynchedEntityData.defineId(PVZPAZ.class, EntityDataSerializers.INT);
    protected static final WeightList<DropType> NORMAL_DROP_LIST = new WeightList<>();
    protected Player ownerPlayer;

    public PVZPAZ(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SKILLS, new CompoundTag());
        this.entityData.define(EXIST_TICK, 0);
        this.entityData.define(STATES, 0);
    }

    public static AttributeSupplier.Builder createPAZAttributes() {
        return AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH)
                .add(Attributes.FOLLOW_RANGE)
                .add(Attributes.KNOCKBACK_RESISTANCE)
                .add(Attributes.MOVEMENT_SPEED)
                .add(Attributes.FLYING_SPEED)
                .add(Attributes.ATTACK_DAMAGE)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.ATTACK_SPEED)
                .add(Attributes.ARMOR)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(Attributes.LUCK)
                .add(ForgeMod.SWIM_SPEED.get())
                .add(ForgeMod.NAMETAG_DISTANCE.get())
                .add(ForgeMod.ENTITY_GRAVITY.get())
                .add(ForgeMod.REACH_DISTANCE.get())
//                .add(PVZAttributes.INNER_DEFENCE_HP.get())
//                .add(PVZAttributes.OUTER_DEFENCE_HP.get())
                ;
    }

    /**
     * Special Drop Types.
     */
    protected enum DropType{
        COPPER, //drop copper coin.
        SILVER, //drop silver coin.
        GOLD,  //drop gold coin.
        JEWEL, //drop jewel.
        CHOCOLATE, //drop chocolate.
    }

}
