package com.hungteen.pvz.common.entity.zombie.drop;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import com.hungteen.pvz.utils.AnimationUtil;
import com.hungteen.pvz.utils.enums.DropPartTypes;
import com.hungteen.pvz.utils.interfaces.IEntityDropPart;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

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
    public final int headRotation;
    protected IPAZType ownerType;
    protected DropPartTypes dropPartType;
    private int maxExistTick;
    private float friction = 0.3F;

    public ZombieDropPart(EntityType<? extends LivingEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        headRotation = this.random.nextInt(60) - 30;
        this.maxExistTick = 60;
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
        buffer.writeUtf(ownerType.getIdentity());
        buffer.writeInt(dropPartType.ordinal());
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
            this.ownerType = opt.get();
        }
        this.dropPartType = DropPartTypes.values()[additionalData.readInt()];
        for(int i = 0;i < handItems.size(); ++ i){
            handItems.set(i, additionalData.readItem());
        }
        for(int i = 0;i < armorItems.size(); ++ i){
            armorItems.set(i, additionalData.readItem());
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (this.getExistTime() >= this.maxExistTick) {
                this.discard();
            } else {
                this.setExistTime(this.getExistTime() + 1);
            }
        }
//        if(this.onGround) {
//            this.setDeltaMovement(this.getDeltaMovement().scale(this.friction));
//        }
    }

    public void updateInfo(PVZZombie zombie, DropPartTypes type) {
        this.setOwnerType(zombie.getZombieType());
//        this.setMini(zombie.isMiniZombie());
        this.setDropPartType(type);
    }

    /**
     * common drop styles.
     */
    public void onDrop(PVZZombie zombie, DamageSource source, float amount, DropPartTypes type) {
        this.updateInfo(zombie, type);
        switch(type) {
            case LEFT_HAND:
            case RIGHT_HAND:{
                final float j = type == DropPartTypes.LEFT_HAND ? AnimationUtil.byDegree(this.getYRot()) : - AnimationUtil.byDegree(this.getYRot());
                final float dis = 0.6F;
                this.setPos(zombie.position().x + Math.sin(j) * dis, zombie.position().y + zombie.getEyeHeight(), zombie.position().z + Math.cos(j) * dis);
                break;
            }
            case HEAD:{
                this.hitUp(zombie, source, amount);
                break;
            }
//            case WHOLE_BODY:{
//                this.setPos(zombie.position().x, zombie.position().y, zombie.position().z);
//                this.setDeltaMovement(zombie.getDeltaMovement());
//                break;
//            }
            default:
                break;
        }
    }

    public void switchItemStack(PVZZombie zombie, DropPartTypes type){
        switch (type){
            case LEFT_HAND:{
                change(zombie, EquipmentSlot.OFFHAND);
                break;
            }
            case RIGHT_HAND:{
                change(zombie, EquipmentSlot.MAINHAND);
                break;
            }
            case HEAD:{
                change(zombie, EquipmentSlot.HEAD);
                break;
            }
        }
    }

    private void change(LivingEntity entity, EquipmentSlot equipmentSlot){
        this.setItemSlot(equipmentSlot, entity.getItemBySlot(equipmentSlot).copy());
        entity.setItemSlot(equipmentSlot, ItemStack.EMPTY);
    }

    private void hitUp(PVZZombie zombie, DamageSource source, float amount) {
        final double speed = Math.min(0.5F, Math.sqrt(amount) * 0.1F);
        this.hitUp(zombie, source, speed, speed, speed);
    }

    private void hitUp(PVZZombie zombie, DamageSource source, double speed, double speedH, double speedV) {
        this.setPos(zombie.position().x, zombie.position().y + zombie.getEyeHeight(), zombie.position().z);
        double speedX = (this.random.nextDouble() - 0.5D) * speedH;
        double speedZ = (this.random.nextDouble() - 0.5D) * speedH;
        double speedY = this.random.nextDouble() * speedV;
        Optional.ofNullable(source.getSourcePosition()).ifPresent(vec -> {
            Vec3 v = this.position().subtract(vec);
            this.setDeltaMovement(v.normalize().multiply(speed, speed, speed).add(speedX, speedY, speedZ));
        });
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
        if(tag.contains("OwnerEntityType")){
            Optional<IPAZType> opt = PVZAPI.get().getPAZType(tag.getString("OwnerEntityType"));
            if(opt.isPresent()){
                this.ownerType = opt.get();
            }
        }
        if(tag.contains("DropPartType")){
            this.dropPartType = DropPartTypes.values()[tag.getInt("DropPartType")];
        }
        if(tag.contains("ExistTime")){
            this.setExistTime(tag.getInt("ExistTime"));
        }
        if(tag.contains("PartState")){
            this.setBodyState(tag.getInt("PartState"));
        }
        if(tag.contains("MaxExistTime")){
            this.maxExistTick = tag.getInt("MaxExistTime");
        }
        if(tag.contains("Friction")){
            this.friction = tag.getFloat("Friction");
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
        if(this.ownerType != null){
            tag.putString("OwnerEntityType", this.ownerType.getIdentity());
        }
        tag.putInt("DropPartType", this.dropPartType.ordinal());
        tag.putInt("ExistTime", this.getExistTime());
        tag.putInt("PartState", this.getBodyState());
        tag.putInt("MaxExistTime", this.maxExistTick);
        tag.putFloat("Friction", this.friction);
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

    public void setMaxLiveTick(int tick) {
        this.maxExistTick = tick;
    }

    public void setFriction(float f) {
        this.friction = f;
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
    public IPAZType getOwnerType() {
        return this.ownerType;
    }

    public void setOwnerType(IPAZType ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public DropPartTypes getDropPartType() {
        return this.dropPartType;
    }

    public void setDropPartType(DropPartTypes dropPartType) {
        this.dropPartType = dropPartType;
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
        return this.getExistTime();
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
