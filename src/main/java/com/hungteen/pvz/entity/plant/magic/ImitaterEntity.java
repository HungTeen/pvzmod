package com.hungteen.pvz.entity.plant.magic;

import java.util.Optional;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.item.tool.card.BlockPlantCardItem;
import com.hungteen.pvz.item.tool.card.ImitaterCardItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
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
import net.minecraft.entity.SharedMonsterAttributes;
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

	private static final DataParameter<ItemStack> IMITATE_CARD = EntityDataManager.createKey(ImitaterEntity.class, DataSerializers.ITEMSTACK);
	public Optional<PVZPlantEntity> targetPlantEntity = Optional.empty();
	public Optional<BlockPos> targetPos = Optional.empty();
	public Direction placeDirection = Direction.NORTH;
	
	public ImitaterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
		this.isImmuneToWeak = true;
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(IMITATE_CARD, ItemStack.EMPTY);
	}
	
	@Override
	protected void registerGoals() {
	}
	
	@Override
	public void startBomb() {
		if(! world.isRemote) {
			ItemStack stack = getImitateCard();
			if(stack.isEmpty()) return ; //empty itemstack
			Optional<Plants> opt = ImitaterCardItem.getImitatePlantType(stack);
			if(! opt.isPresent() || opt.get() == Plants.IMITATER) return ;// no imitate template plant.
			Plants plant = opt.get();
			EntityUtil.playSound(this, SoundRegister.WAKE_UP.get());
			if(plant.isBlockPlant) {// imitate block
				if(getPosition().getY() >= 2) {
					BlockState state = BlockPlantCardItem.getBlockState(placeDirection, plant);
					world.setBlockState(getPosition(), state, 11);
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
					PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(world, plant);
					this.copyDataToPlant(plantEntity);
					plantEntity.plantSunCost = target.plantSunCost + this.plantSunCost;
					if(target.getOuterPlantType().isPresent()) {
						plantEntity.setOuterPlantType(target.getOuterPlantType().get());
					}
					plantEntity.setPumpkinLife(target.getPumpkinLife());
					PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
					world.addEntity(plantEntity);
					this.targetPlantEntity.get().remove();
				} else if(plant == Plants.CAT_TAIL && this.targetPos.isPresent()) {
					BlockPos pos = this.targetPos.get();
					if(world.getBlockState(pos).getBlock() == BlockRegister.LILY_PAD.get()) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState());
						PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(world, plant);
						this.copyDataToPlant(plantEntity);
						plantEntity.plantSunCost = this.plantSunCost + PlantUtil.getPlantSunCost(Plants.LILY_PAD);
						PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
						world.addEntity(plantEntity);
					}
				}
				return ;
			}
			//imitate common plants.
			PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(world, plant);
			this.copyDataToPlant(plantEntity);
			plantEntity.plantSunCost = this.plantSunCost;
			PlantCardItem.summonPlantEntityByCard(plantEntity, stack);
			world.addEntity(plantEntity);
		} else {
			for(int i = 0; i < 3; ++ i) {
			    this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY() + 0.5, this.getPosZ(), 0, 0, 0);
			}
		}
	}
	
	public void copyDataToPlant(PVZPlantEntity plantEntity) {
		if(this.getOwnerUUID().isPresent()) {
			plantEntity.setOwnerUUID(this.getOwnerUUID().get());
		}
		plantEntity.setPlantLvl(this.getPlantLvl());
		plantEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(plantEntity.getPlantHealth());
		plantEntity.heal(plantEntity.getMaxHealth());
		plantEntity.setPosition(getPosX(), getPosY(), getPosZ());
		Entity ridingEntity = this.getRidingEntity();
		if(ridingEntity != null) {
			this.stopRiding();
			plantEntity.startRiding(ridingEntity);
		}
		if(this.getAttackTarget() != null) {
			plantEntity.setAttackTarget(this.getAttackTarget());
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! world.isRemote) {
			if(this.isInWater()) {
				double speed = 0.25D;
				this.setMotion(0, speed, 0);
			} else {
				this.setMotion(0, 0, 0);
			}
		}
	}

	@Override
	public boolean hasNoGravity() {
		return false;
	}
	
	@Override
	public int getReadyTime() {
		return 40;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.7F, 1.25F);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.IMITATER;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("imitate_plant_card")) {
			this.setImitateCard(ItemStack.read(compound.getCompound(("imitate_plant_card"))));
		}
		if(compound.contains("target_plant_entity")) {
			this.targetPlantEntity = Optional.ofNullable((PVZPlantEntity) world.getEntityByID(compound.getInt("target_plant_entity")));
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
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if(! this.getImitateCard().isEmpty()) {
			compound.put("imitate_plant_card", this.getImitateCard().getOrCreateTag());
		}
		if(this.targetPlantEntity.isPresent()) {
			compound.putInt("target_plant_entity", this.targetPlantEntity.get().getEntityId());
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
		this.dataManager.set(IMITATE_CARD, stack);
	}
	
	public ItemStack getImitateCard() {
		return this.dataManager.get(IMITATE_CARD);
	}

}
