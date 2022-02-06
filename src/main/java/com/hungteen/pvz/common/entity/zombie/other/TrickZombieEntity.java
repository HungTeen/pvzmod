package com.hungteen.pvz.common.entity.zombie.other;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TrickZombieEntity extends PVZZombieEntity{

	public static final int EXPLOSION_CHANCE = 8;
	public static final int SUMMON_CHACNE = 5;
	private int lastSummonTick = 0;
	private final int summonGap = 40;
	
	public TrickZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_FAST);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.NORMAL_DAMAGE);
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(amount >= 1.1F && ! level.isClientSide && this.tickCount - this.lastSummonTick >= this.summonGap && this.getRandom().nextInt(SUMMON_CHACNE) == 0) {
			this.lastSummonTick = this.tickCount;
			TrickZombieEntity zombie = EntityRegister.TRICK_ZOMBIE.get().create(level);
			BlockPos pos = blockPosition().offset(this.getRandom().nextInt(5) - 2, this.getRandom().nextInt(2), this.getRandom().nextInt(5) - 2);
			ZombieUtil.copySummonZombieData(this, zombie);
			EntityUtil.onEntitySpawn(level, zombie, pos);
		}
		return super.hurt(source, amount);
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		if(! level.isClientSide && player.getItemInHand(hand).getItem() == ItemRegister.CANDY.get() && ! this.isCharmed()) {
			if(this.getRandom().nextInt(3) == 0) {
				player.getItemInHand(hand).shrink(1);
			    this.setCharmed(true);
			    return ActionResultType.CONSUME;
			}
		}
		return super.interactAt(player, vec3d, hand);
	}
	
	@Override
	protected void dropAllDeathLoot(DamageSource damageSourceIn) {
		if(! this.hasEffect(EffectRegister.COLD_EFFECT.get()) && ! this.isCharmed()) {
			if(this.getRandom().nextInt(EXPLOSION_CHANCE) == 0) {
				Explosion.Mode mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
				this.level.explode(this, getX(), getY(), getZ(), 0.5f, mode);
			}
		}
		super.dropAllDeathLoot(damageSourceIn);
	}
	
	@Override
	public boolean ignoreExplosion() {
		return true;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.3F, 0.5F);
		return EntitySize.scalable(0.6f, 1.2f);
	}
	
	@Override
	public float getLife() {
		return 10;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.TRICK_ZOMBIE;
	}
	
	@Override
	public ZombieType getZombieType() {
		return CustomZombies.TRICK_ZOMBIE;
	}

}
