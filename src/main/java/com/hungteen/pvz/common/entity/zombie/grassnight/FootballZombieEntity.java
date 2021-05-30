package com.hungteen.pvz.common.entity.zombie.grassnight;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.MetalTypes;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IHasMetal;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FootballZombieEntity extends PVZZombieEntity implements IHasMetal {

	public static final float FOOTBALL_HEALTH = 120;
	
	public FootballZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.hasDirectDefence = true;
		this.increaseMetal();
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.FAST);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.5f, 0.75f);
		return EntitySize.scalable(0.8f, 2.4f);
	}
	
	@Override
	public float getLife() {
		return 20;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.FOOTBALL_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.FOOTBALL_ZOMBIE;
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
		this.setDefenceLife(FOOTBALL_HEALTH);
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.FOOTBALL_HELMET;
	}

}
