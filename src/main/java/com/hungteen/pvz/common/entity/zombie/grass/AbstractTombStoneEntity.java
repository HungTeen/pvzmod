package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.entity.plant.assist.GraveBusterEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class AbstractTombStoneEntity extends PVZZombieEntity {

	public AbstractTombStoneEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setImmuneAllEffects();
		this.setIsWholeBody();
		this.canBeMini = false;
		this.canBeStealByBungee = false;
		this.canCollideWithZombie = false;
		this.canHelpAttack = false;
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(0);
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(50);
	}
	
	public void tick() {
		super.tick();
		if(! this.level.isClientSide) {
			BlockPos pos = this.blockPosition();
			this.setPos(pos.getX() + 0.5, this.getY(), pos.getZ() + 0.5);
		}
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if (! level.isClientSide) {
			ItemStack stack = player.getItemInHand(hand);
			if (stack.getItem() instanceof PlantCardItem) {// plant card right click plant entity
				PlantCardItem item = (PlantCardItem) stack.getItem();
				if(PlantCardItem.checkSunAndInteractEntity(player, this, item, stack, type -> {
					return type == PVZPlants.GRAVE_BUSTER;
				}, plantEntity -> {
					if(plantEntity instanceof GraveBusterEntity) {
						plantEntity.setTarget(this);
					}
				})) {
					
				}
				return ActionResultType.SUCCESS;
			}
		}
		return super.interactAt(player, vec3d, hand);
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return false;
	}
	
	@Override
	protected WeightList<DropType> getDropSpecialList() {
		return super.getDropSpecialList().setLeftItem(DropType.COPPER);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.6f);
	}
	
	@Override
	public float getLife() {
		return 70;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 0;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return null;
	}
	
}
