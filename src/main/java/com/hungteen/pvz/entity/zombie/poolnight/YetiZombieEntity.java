package com.hungteen.pvz.entity.zombie.poolnight;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.drop.JewelEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class YetiZombieEntity extends PVZZombieEntity{

	private final int DROP_JEWEL_NUM = 4;
	private final double [] DD = new double[]{- 0.5D, 0.5D, 0.5D, -0.5D};
	private int live_tick = 0;
	private boolean hasInvis = false;
	
	public YetiZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getLife() {
		return 135;
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! world.isRemote) {
			++ this.live_tick;
			if(this.live_tick > this.getYetiMaxLiveTick() / 2) {
				if(! this.hasInvis) {
				    this.hasInvis = true;
				    this.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 2000, 2, false, true));
				}
				this.heal(0.5f);
			} else if(this.live_tick >= this.getYetiMaxLiveTick()) {
				this.remove();
			}
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1f, 2.6f);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(amount >= 100) {
			amount /= 3;
		}
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	protected void dropCoinOrSpecial() {
		for(int i = 0; i < this.DROP_JEWEL_NUM; ++ i) {
			JewelEntity jewel = EntityRegister.JEWEL.get().create(world);
		    EntityUtil.onMobEntitySpawn(world, jewel, getPosition().add(this.DD[i], getRNG().nextDouble(), this.DD[(i + 1) % this.DROP_JEWEL_NUM]));
		}
	}

	public int getYetiMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.YetiLiveTick.get();
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("yeti_live_tick", this.live_tick);
		compound.putBoolean("yeti_invis", this.hasInvis);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.live_tick = compound.getInt("yeti_live_tick");
		this.hasInvis = compound.getBoolean("yeti_invis");
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.YETI_ZOMBIE;
	}

}
