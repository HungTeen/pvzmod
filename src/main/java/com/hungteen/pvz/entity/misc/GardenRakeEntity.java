package com.hungteen.pvz.entity.misc;

import java.util.List;

import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GardenRakeEntity extends AbstractOwnerEntity {
	
    private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(GardenRakeEntity.class, DataSerializers.VARINT);
	
	public GardenRakeEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setMotion(Vec3d.ZERO);
	}
	
	public GardenRakeEntity(World worldIn, LivingEntity livingEntityIn) {
		super(EntityRegister.GARDEN_RAKE.get(), worldIn, livingEntityIn);
		this.rotationYaw = livingEntityIn.getHorizontalFacing().getHorizontalAngle();
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(ATTACK_TIME, 0);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! world.isRemote) {
			if(this.isStartAttack()) {
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.getAnimTime()) {
					this.dealDamage();
				}
			} else {
				List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox().grow(0.2D), (target) -> {
			        return EntityUtil.checkCanEntityAttack(this, target);
		        });
				if(! list.isEmpty()) {
			       this.onStartAttack();
				}
			}
		}
		this.tickMove();
	}
	
	private void dealDamage() {
		this.world.getEntitiesWithinAABB(Entity.class, this.getBoundingBox().grow(0.25D), (target) -> {
	        return EntityUtil.checkCanEntityAttack(this, target);
        }).forEach((target) -> {
        	target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), 180F);
        });
		EntityUtil.playSound(this, SoundRegister.SWING.get());
		this.remove();
	}
	
	protected void onStartAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public boolean processInitialInteract(PlayerEntity player, Hand hand) {
		if(! this.isStartAttack() && hand == Hand.MAIN_HAND && player.getHeldItemMainhand().isEmpty()) {
			if(! world.isRemote) {
				player.addItemStackToInventory(new ItemStack(ItemRegister.GARDEN_RAKE.get()));
			    this.remove();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	
	public boolean isStartAttack() {
		return this.getAttackTime() > 0;
	}
	
	public int getAnimTime() {
		return 10;
	}
	
	public void setPlacer(PlayerEntity player) {
		this.setOwner(player);
		this.rotationYaw = player.getHorizontalFacing().getHorizontalAngle();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.9F, 0.8F);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("rake_attack_time")) {
			this.setAttackTime(compound.getInt("rake_attack_time"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("rake_attack_time", this.getAttackTime());
	}
	
	public int getAttackTime() {
		return dataManager.get(ATTACK_TIME);
	}

	public void setAttackTime(int cd) {
		dataManager.set(ATTACK_TIME, cd);
	}

	
}
