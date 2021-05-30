package com.hungteen.pvz.common.entity.plant.magic;

import java.util.Optional;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.item.card.BlockPlantCardItem;
import com.hungteen.pvz.common.item.card.ImitaterCardItem;
import com.hungteen.pvz.common.item.card.PlantCardItem;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ImitaterEntity extends PlantBomberEntity {

	private static final DataParameter<ItemStack> IMITATE_CARD = EntityDataManager.defineId(ImitaterEntity.class, DataSerializers.ITEM_STACK);
	public Optional<PVZPlantEntity> targetPlantEntity = Optional.empty();
	public Optional<BlockPos> targetPos = Optional.empty();
	public Direction placeDirection = Direction.NORTH;
	
	public ImitaterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
		this.isImmuneToWeak = true;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IMITATE_CARD, ItemStack.EMPTY);
	}
	
	@Override
	protected void registerGoals() {
	}
	
	@Override
	public void startBomb() {
		if(! level.isClientSide) {
			ItemStack stack = getImitateCard();
			if(stack.isEmpty()) return ; //empty itemstack
			Optional<Plants> opt = ImitaterCardItem.getImitatePlantType(stack);
			if(! opt.isPresent() || opt.get() == Plants.IMITATER) return ;// no imitate template plant.
			Plants plant = opt.get();
			EntityUtil.playSound(this, SoundRegister.WAKE_UP.get());
			if(plant.isBlockPlant) {// imitate block
				if(blockPosition().getY() >= 2) {
					BlockState state = BlockPlantCardItem.getBlockState(placeDirection, plant);
					level.setBlock(blockPosition(), state, 11);
				}
				return ;
			}
			if(plant.isOuterPlant) {// imitate outer plant
				if(this.targetPlantEntity.isPresent()) {
					PlantCardItem.placeOuterPlant(this.targetPlantEntity.get(), plant, getImitateCard());
					this.targetPlantEntity.get().outerSunCost = this.plantSunCost;
				}
				return ;
			}
			if(plant.isUpgradePlant) {// imitate upgrade plant
				if(this.targetPlantEntity.isPresent()) {
					PVZPlantEntity target = this.targetPlantEntity.get();
					PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(level, plant);
					this.copyDataToPlant(plantEntity);
					plantEntity.plantSunCost = target.plantSunCost + this.plantSunCost;
					if(! plant.isBigPlant) {
						if(target.getOuterPlantType().isPresent()) {
						    plantEntity.setOuterPlantType(target.getOuterPlantType().get());
					    }
					    plantEntity.setPumpkinLife(target.getPumpkinLife());
					}
					PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
					level.addFreshEntity(plantEntity);
					this.targetPlantEntity.get().remove();
				} else if(plant == Plants.CAT_TAIL && this.targetPos.isPresent()) {
					BlockPos pos = this.targetPos.get();
					if(level.getBlockState(pos).getBlock() == BlockRegister.LILY_PAD.get()) {
						level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
						PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(level, plant);
						this.copyDataToPlant(plantEntity);
						plantEntity.plantSunCost = this.plantSunCost + PlantUtil.getPlantSunCost(Plants.LILY_PAD);
						PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
						level.addFreshEntity(plantEntity);
					}
				}
				return ;
			}
			//imitate common plants.
			PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(level, plant);
			this.copyDataToPlant(plantEntity);
			plantEntity.plantSunCost = this.plantSunCost;
			PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
			level.addFreshEntity(plantEntity);
		} else {
			for(int i = 0; i < 3; ++ i) {
			    this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 0, 0, 0);
			}
		}
	}
	
	public void copyDataToPlant(PVZPlantEntity plantEntity) {
		if(this.getOwnerUUID().isPresent()) {
			plantEntity.setOwnerUUID(this.getOwnerUUID().get());
		}
		plantEntity.setPlantLvl(this.getPlantLvl());
		plantEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(plantEntity.getPlantHealth());
		plantEntity.heal(plantEntity.getMaxHealth());
		plantEntity.setPos(getX(), getY(), getZ());
		Entity ridingEntity = this.getVehicle();
		if(ridingEntity != null) {
			this.stopRiding();
			plantEntity.startRiding(ridingEntity);
		}
		if(this.getTarget() != null) {
			plantEntity.setTarget(this.getTarget());
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! level.isClientSide) {
			if(this.isInWater()) {
				double speed = 0.25D;
				this.setDeltaMovement(0, speed, 0);
			} else {
				this.setDeltaMovement(0, 0, 0);
			}
		}
	}

	@Override
	public boolean isNoGravity() {
		return false;
	}
	
	@Override
	public int getReadyTime() {
		return 40;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7F, 1.25F);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.IMITATER;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("imitate_plant_card")) {
			this.setImitateCard(ItemStack.of(compound.getCompound(("imitate_plant_card"))));
		}
		if(compound.contains("target_plant_entity")) {
			this.targetPlantEntity = Optional.ofNullable((PVZPlantEntity) level.getEntity(compound.getInt("target_plant_entity")));
		}
		if(compound.contains("target_blockpos")) {
			CompoundNBT nbt = compound.getCompound("target_blockpos");
			this.targetPos = Optional.of(new BlockPos(nbt.getInt("posX"), nbt.getInt("posY"), nbt.getInt("posZ")));
		}
		if(compound.contains("imitate_facing")) {
			this.placeDirection = Direction.values()[compound.getInt("imitate_facing")];
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		if(! this.getImitateCard().isEmpty()) {
			compound.put("imitate_plant_card", this.getImitateCard().getOrCreateTag());
		}
		if(this.targetPlantEntity.isPresent()) {
			compound.putInt("target_plant_entity", this.targetPlantEntity.get().getId());
		}
		if(this.targetPos.isPresent()) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("posX", this.targetPos.get().getX());
			nbt.putInt("posY", this.targetPos.get().getY());
			nbt.putInt("posZ", this.targetPos.get().getZ());
		}
		compound.putInt("imitate_facing", this.placeDirection.ordinal());
	}
	
	public void setImitateCard(ItemStack stack) {
		this.entityData.set(IMITATE_CARD, stack);
	}
	
	public ItemStack getImitateCard() {
		return this.entityData.get(IMITATE_CARD);
	}

}
