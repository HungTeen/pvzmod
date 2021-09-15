package com.hungteen.pvz.common.entity.plant.magic;

import java.util.Optional;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.WorldUtil;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
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
	public void startBomb(boolean server) {
		if(server) {
			if(! (this.getImitateCard().getItem() instanceof PlantCardItem)) {// no imitate template plant.
				PVZMod.LOGGER.warn("Imitate Error : Wrong Card !");
				return ;
			}
			final PlantType plantType = ((PlantCardItem) this.getImitateCard().getItem()).plantType;
			EntityUtil.playSound(this, SoundRegister.WAKE_UP.get());
			if(plantType == PVZPlants.IMITATER) {
				this.imitateRandomly();
			} else {
				this.imitate(plantType);
			}
//			if(plant.isUpgradePlant()) {// imitate upgrade plant
//				if(this.targetPlantEntity.isPresent()) {
//					PVZPlantEntity target = this.targetPlantEntity.get();
//					PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(level, plant);
//					this.copyDataToPlant(plantEntity);
//					plantEntity.plantSunCost = target.plantSunCost + this.plantSunCost;
//					if(! plant.isBigPlant) {
//						if(target.getOuterPlantType().isPresent()) {
//						    plantEntity.setOuterPlantType(target.getOuterPlantType().get());
//					    }
//					    plantEntity.setPumpkinLife(target.getPumpkinLife());
//					}
//					PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
//					level.addFreshEntity(plantEntity);
//					this.targetPlantEntity.get().remove();
//				} else if(plant == Plants.CAT_TAIL && this.targetPos.isPresent()) {
//					BlockPos pos = this.targetPos.get();
//					if(level.getBlockState(pos).getBlock() == BlockRegister.LILY_PAD.get()) {
//						level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
//						PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(level, plant);
//						this.copyDataToPlant(plantEntity);
//						plantEntity.plantSunCost = this.plantSunCost + PlantUtil.getPlantSunCost(Plants.LILY_PAD);
//						PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
//						level.addFreshEntity(plantEntity);
//					}
//				}
//				return ;
//			}
//			//imitate common plants.
//			PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(level, plant);
//			this.copyDataToPlant(plantEntity);
//			plantEntity.plantSunCost = this.plantSunCost;
//			PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
//			level.addFreshEntity(plantEntity);
		} else {
			for(int i = 0; i < 3; ++ i) {
				WorldUtil.spawnRandomSpeedParticle(level, ParticleTypes.EXPLOSION, position(), 0.01F);
			}
		}
	}
	
	public void imitate(PlantType plantType) {
		if(plantType.isBlockPlant()) {
			if(blockPosition().getY() >= 2) {
				BlockState state = PlantCardItem.getBlockState(placeDirection, plantType);
				PlantCardItem.handlePlantBlock(level, plantType, state, blockPosition());
			}
		} else if(plantType.isOuterPlant()) {
//			if(this.targetPlantEntity.isPresent()) {
//				PlantCardItem.placeOuterPlant(this.targetPlantEntity.get(), plant, getImitateCard());
//				this.targetPlantEntity.get().outerSunCost = this.plantSunCost;
//			}
		} else {
			
			/* available when player is online */
			this.getOwnerPlayer().ifPresent(player -> {System.out.println("haha");
				PlantCardItem.handlePlantEntity(player, plantType, this.getImitateCard(), blockPosition(), plantEntity -> {
				    PlantUtil.copyPlantData(plantEntity, this);
		            /* handle rider */
				    if(this.getVehicle() != null) {
			            this.stopRiding();
			            plantEntity.startRiding(this.getVehicle());
		            }
				    if(this.getTarget() != null) {
				    	plantEntity.setTarget(this.getTarget());
			    	}
			    });
			});
		}
	}
	
	public void imitateRandomly() {
		
	}
	
	@Override
	public void tick() {
		this.noPhysics = true;
		super.tick();
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return false;
	}
	
	@Override
	public boolean isNoGravity() {
		return true;
	}
	
	@Override
	public int getReadyTime() {
		return 30;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.7F, 1.25F);
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
	
	@Override
	public PlantType getPlantType() {
		return PVZPlants.IMITATER;
	}

}
