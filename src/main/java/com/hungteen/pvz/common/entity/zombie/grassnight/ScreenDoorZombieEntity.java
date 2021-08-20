package com.hungteen.pvz.common.entity.zombie.grassnight;

import com.hungteen.pvz.common.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.remove.Zombies;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ScreenDoorZombieEntity extends DefenceZombieEntity implements IHasMetal {

	public ScreenDoorZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1.7f);
		this.part.setOwner(this);
	}
	
	@Override
	public boolean hasMetal() {
		return this.getDefenceLife() > 0;
	}

	@Override
	public void decreaseMetal() {
		this.setDefenceLife(0);
	}

	@Override
	public void increaseMetal() {
		this.setDefenceLife(this.getPartLife());
		this.resetParts();
	}
	
	@Override
	public boolean canLostHand() {
		return super.canLostHand() && ! this.hasMetal();
	}
	
	@Override
	protected void setBodyStates(ZombieDropBodyEntity body) {
		super.setBodyStates(body);
		body.setHandDefence(this.hasMetal());
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.SCREEN_DOOR;
	}
	
	@Override
	public SoundEvent getPartHurtSound() {
		return SoundRegister.METAL_HIT.get();
	}
	
	@Override
	public float getLife() {
		return 24;
	}
	
	@Override
	public float getPartLife() {
		return 200;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.SCREENDOOR_ZOMBIE;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SCREENDOOR_ZOMBIE;
	}

}
