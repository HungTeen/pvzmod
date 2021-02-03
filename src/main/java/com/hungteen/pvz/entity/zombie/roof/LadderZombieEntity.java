package com.hungteen.pvz.entity.zombie.roof;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class LadderZombieEntity extends DefenceZombieEntity implements IHasMetal {
	
	public LadderZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
	}

	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1.7f);
		this.part.setOwner(this);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if(this.hasMetal() && canTargetPutLadder(entityIn)) {
			this.decreaseMetal();
			PVZPlantEntity plant = (PVZPlantEntity) entityIn;
			plant.increaseMetal();
		}
		return super.attackEntityAsMob(entityIn);
	}
	
	public static boolean canTargetPutLadder(Entity target) {
		if(! (target instanceof PVZPlantEntity)) return false;
		if(hasLadderOnEntity(target)) return false;
		if(target instanceof PlantDefenderEntity) return true;
		PVZPlantEntity plant = (PVZPlantEntity) target;
		return plant.getOuterPlantType().isPresent() && plant.getOuterPlantType().get() == Plants.PUMPKIN;
	}
	
	public static boolean hasLadderOnEntity(Entity target) {
		if(! (target instanceof PVZPlantEntity)) return false;
		PVZPlantEntity plant = (PVZPlantEntity) target;
		return plant.hasMetal();
	}
	
	@Override
	public boolean hasMetal() {
		return this.getDefenceLife() > 0;
	}

	@Override
	public void decreaseMetal() {
		this.setDefenceLife(0);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.NORMAL_SPEED);
	}

	@Override
	public void increaseMetal() {
		this.setDefenceLife(this.getPartLife());
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
		this.resetParts();
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.LADDER;
	}
	
	@Override
	public SoundEvent getPartHurtSound() {
		return SoundRegister.METAL_HIT.get();
	}
	
	@Override
	public float getLife() {
		return 65;
	}
	
	@Override
	public float getPartLife() {
		return 250;
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.LADDER_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.LADDER_ZOMBIE;
	}

}
