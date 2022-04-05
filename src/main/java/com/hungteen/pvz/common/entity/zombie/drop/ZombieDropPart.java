package com.hungteen.pvz.common.entity.zombie.drop;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.common.entity.PVZEntity;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.utils.interfaces.IEntityDropPart;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;

import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 08:32
 **/
public class ZombieDropPart extends LivingEntity implements IEntityDropPart, IEntityAdditionalSpawnData {

    private static final EntityDataAccessor<Integer> EXIST_TIME = SynchedEntityData.defineId(ZombieDropPart.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(ZombieDropPart.class, EntityDataSerializers.INT);
    private final NonNullList<ItemStack> handItems = NonNullList.withSize(2, ItemStack.EMPTY);
    private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);
    public final int HEAD_ROT;
    protected IPAZType type;
    protected String dropType;

    public ZombieDropPart(EntityType<? extends LivingEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        HEAD_ROT = this.random.nextInt(60) - 30;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EXIST_TIME, 0);
        this.entityData.define(STATE, 0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AttributeSupplier.builder()
                .add(Attributes.MAX_HEALTH, 2)
                .add(Attributes.FOLLOW_RANGE)
                .add(Attributes.KNOCKBACK_RESISTANCE)
                .add(Attributes.MOVEMENT_SPEED)
                .add(Attributes.ATTACK_DAMAGE)
                .add(Attributes.ATTACK_KNOCKBACK)
                .add(Attributes.ARMOR)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(ForgeMod.SWIM_SPEED.get())
                .add(ForgeMod.NAMETAG_DISTANCE.get())
                .add(ForgeMod.ENTITY_GRAVITY.get())
                ;
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeUtf(type.getIdentity());
        buffer.writeUtf(dropType);
        for(int i = 0;i < handItems.size(); ++ i){
            buffer.writeItem(handItems.get(i));
        }
        for(int i = 0;i < armorItems.size(); ++ i){
            buffer.writeItem(armorItems.get(i));
        }
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        Optional<IPAZType> opt = PVZAPI.get().getPAZType(additionalData.readUtf());
        if(opt.isPresent()){
            this.type = opt.get();
        }
        this.dropType = additionalData.readUtf();
        for(int i = 0;i < handItems.size(); ++ i){
            handItems.set(i, additionalData.readItem());
        }
        for(int i = 0;i < armorItems.size(); ++ i){
            armorItems.set(i, additionalData.readItem());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("ArmorItems", 9)) {
            ListTag listtag = tag.getList("ArmorItems", 10);

            for(int i = 0; i < this.armorItems.size(); ++i) {
                this.armorItems.set(i, ItemStack.of(listtag.getCompound(i)));
            }
        }
        if (tag.contains("HandItems", 9)) {
            ListTag listtag1 = tag.getList("HandItems", 10);

            for(int j = 0; j < this.handItems.size(); ++j) {
                this.handItems.set(j, ItemStack.of(listtag1.getCompound(j)));
            }
        }
        if(tag.contains("DropEntityType")){
            Optional<IPAZType> opt = PVZAPI.get().getPAZType(tag.getString("DropEntityType"));
            if(opt.isPresent()){
                this.type = opt.get();
            }
        }
        if(tag.contains("DropPartType")){
            this.dropType = tag.getString("DropPartType");
        }
        if(tag.contains("ExistTime")){
            this.setExistTime(tag.getInt("ExistTime"));
        }
        if(tag.contains("PartState")){
            this.setBodyState(tag.getInt("PartState"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        {
            ListTag listtag = new ListTag();
            for (ItemStack itemstack : this.armorItems) {
                CompoundTag compoundtag = new CompoundTag();
                if (!itemstack.isEmpty()) {
                    itemstack.save(compoundtag);
                }

                listtag.add(compoundtag);
            }
            tag.put("ArmorItems", listtag);
        }
        {
            ListTag listtag1 = new ListTag();
            for(ItemStack itemstack1 : this.handItems) {
                CompoundTag compoundtag1 = new CompoundTag();
                if (!itemstack1.isEmpty()) {
                    itemstack1.save(compoundtag1);
                }

                listtag1.add(compoundtag1);
            }
            tag.put("HandItems", listtag1);
        }
        if(this.type != null){
            tag.putString("DropEntityType", this.type.getIdentity());
        }
        tag.putString("DropPartType", this.dropType);
        tag.putInt("ExistTime", this.getExistTime());
        tag.putInt("PartState", this.getBodyState());
    }

    public int getBodyState() {
        return entityData.get(STATE);
    }

    public void setBodyState(int state) {
        entityData.set(STATE, state);
    }

    public int getExistTime() {
        return entityData.get(EXIST_TIME);
    }

    public void setExistTime(int tick) {
        entityData.set(EXIST_TIME, tick);
    }

    @Override
    public Iterable<ItemStack> getHandSlots() {
        return this.handItems;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return this.armorItems;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        switch(slot.getType()) {
            case HAND:
                return this.handItems.get(slot.getIndex());
            case ARMOR:
                return this.armorItems.get(slot.getIndex());
            default:
                return ItemStack.EMPTY;
        }
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        this.verifyEquippedItem(stack);
        switch(slot.getType()) {
            case HAND:
                this.handItems.set(slot.getIndex(), stack);
                break;
            case ARMOR:
                this.armorItems.set(slot.getIndex(), stack);
        }

    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public IPAZType getPAZType() {
        return this.type;
    }

    @Override
    public String getDropPartType() {
        return null;
    }

    @Override
    public boolean hasHandDefence() {
        return false;
    }

    @Override
    public boolean isMini() {
        return false;
    }

    @Override
    public int getAnimTime() {
        return 0;
    }

}
