package com.hungteen.pvz.entity.zombie.other;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TrickZombieEntity extends PVZZombieEntity{

	public static final int EXPLOSION_CHANCE = 8;
	public static final int SUMMON_CHACNE = 3;
	private int lastSummonTick = 0;
	private final int summonGap = 40;
	
	public TrickZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(amount >= 2 && ! world.isRemote && this.ticksExisted - this.lastSummonTick >= this.summonGap && this.getRNG().nextInt(SUMMON_CHACNE) == 0) {
			this.lastSummonTick = this.ticksExisted;
			TrickZombieEntity zombie = EntityRegister.TRICK_ZOMBIE.get().create(world);
			BlockPos pos = getPosition().add(this.getRNG().nextInt(5) - 2, this.getRNG().nextInt(2), this.getRNG().nextInt(5) - 2);
			if(this.isCharmed()) {
				zombie.setCharmed(true);
			}
			EntityUtil.onMobEntitySpawn(world, zombie, pos);
		}
		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if(! world.isRemote && player.getHeldItem(hand).getItem() == ItemRegister.CANDY.get() && ! this.isCharmed()) {
			if(this.getRNG().nextInt(PVZConfig.COMMON_CONFIG.EntitySettings.TrickZombieCharmChance.get()) == 0) {
				player.getHeldItem(hand).shrink(1);
			    this.setCharmed(true);
			    return true;
			}
		}
		return super.processInteract(player, hand);
	}
	
	@Override
	protected void spawnDrops(DamageSource damageSourceIn) {
		if(! this.isPotionActive(EffectRegister.COLD_EFFECT.get()) && ! this.isCharmed()) {
			if(this.getRNG().nextInt(EXPLOSION_CHANCE) == 0) {
				Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
				this.world.createExplosion(this, getPosX(), getPosY(), getPosZ(), 0.5f, mode);
			}
		}
		super.spawnDrops(damageSourceIn);
	}
	
	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.6f, 1.2f);
	}
	
	@Override
	public float getLife() {
		return 10;
	}

	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.TRICK_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TRICK_ZOMBIE;
	}

}
