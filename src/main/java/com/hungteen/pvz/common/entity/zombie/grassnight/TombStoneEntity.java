package com.hungteen.pvz.common.entity.zombie.grassnight;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hungteen.pvz.common.entity.plant.assist.GraveBusterEntity;
import com.hungteen.pvz.common.entity.zombie.other.NobleZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.ZomBossEntity;
import com.hungteen.pvz.common.world.invasion.OverworldInvasion;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class TombStoneEntity extends AbstractTombStoneEntity {

	public static final List<Zombies> DEFAULT_ZOMBIES = Arrays.asList(
			Zombies.NORMAL_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE,
			Zombies.SCREENDOOR_ZOMBIE, Zombies.NEWSPAPER_ZOMBIE, Zombies.OLD_ZOMBIE
			);
	protected int currentSummonCD;
	private final int MinSummonCD = 360;
	private final int MaxSummonCD = 1200;
	
	public TombStoneEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new TombStoneSummonZombieGoal(this));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, NobleZombieEntity.class, true));
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, ZomBossEntity.class, true));
	}

	/**
	 * tombstone summon zombies.
	 * it will select suitable zombie for that day.
	 * used in {@link TombStoneSummonZombieGoal}
	 */
	public void summonZombie() {
		final List<Zombies> list = OverworldInvasion.ZOMBIE_INVADE_SET.isEmpty() ? DEFAULT_ZOMBIES : OverworldInvasion.ZOMBIE_INVADE_SET.stream().collect(Collectors.toList());
		final Zombies zombieType = list.get(this.random.nextInt(list.size()));
		Optional.ofNullable(ZombieUtil.getZombieEntity(level, zombieType)).ifPresent(zombie -> {
			zombie.setZombieRising();
			EntityUtil.onEntitySpawn(level, zombie, blockPosition());
		});
	}
	
	/**
	 * check can summon zombie or not.
	 * it need a valid boss target.
	 */
	protected boolean canSummonZombie() {
		return EntityUtil.isEntityValid(this.getTarget());
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return super.isZombieInvulnerableTo(source) || ! (source.getDirectEntity() instanceof GraveBusterEntity);
	}
	
	/**
	 * next summon zombie cd for next time.
	 */
	protected int genSummonCD() {
		return MathUtil.genRandomMinMax(getRandom(), MinSummonCD, MaxSummonCD);
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOMB_STONE;
	}
	
	static class TombStoneSummonZombieGoal extends Goal{

		protected final TombStoneEntity tomb;
		
		public TombStoneSummonZombieGoal(TombStoneEntity tomb) {
			this.tomb = tomb;
		}
		
		@Override
		public boolean canUse() {
			if(this.tomb.getAttackTime() > 0) {
				return true;
			}
			if(this.tomb.random.nextInt(10) == 0 && this.tomb.canSummonZombie()) {
				this.tomb.setAttackTime(1);
				this.tomb.currentSummonCD = this.tomb.genSummonCD();
				return true;
			}
			return false;
		}
		
		@Override
		public void start() {
			
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.tomb.getAttackTime() > 0 && this.tomb.canSummonZombie();
		}
		
		@Override
		public void tick() {
			int time = this.tomb.getAttackTime();
			if(time >= this.tomb.currentSummonCD) {
				this.tomb.summonZombie();
				this.tomb.setAttackTime(0);
			} else {
				this.tomb.setAttackTime(time + 1);
			}
		}
		
		@Override
		public void stop() {
			this.tomb.setAttackTime(0);
		}
		
	}
	
}
