package com.hungteen.pvzmod.entities.zombies.grassday;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.entities.ai.EntityAIDestroyBlocks;
import com.hungteen.pvzmod.entities.ai.EntityAIZombieEat;
import com.hungteen.pvzmod.entities.npcs.EntityTreeMan;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.defence.EntityNutWall;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.LootTableHandler;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import io.netty.buffer.ByteBuf;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.client.model.IMultipassModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityNormalZombie extends EntityZombieBase {
	
	private final int BEARD_CHANCE = 1000;
	
	public EntityNormalZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 2.0f);
		if(this.getZombieType()==Type.BEARD){
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.BIT_EAT);
		}
	}
	
	@Override
	public float getLife() {
		return 20;
	}
	
	@Override
	public void onNormalZombieUpdate() {
		super.onNormalZombieUpdate();
		if(!this.world.isRemote&&this.getRidingEntity()==null&&this.getHealth()>0) {
			if(this.isInWater()) {
		        EntityDuckyTube duck = new EntityDuckyTube(this.world);
		        duck.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
		        this.world.spawnEntity(duck);
		        this.startRiding(duck);
			}
		}
	}
	
	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}
	
	@Override
	protected Type getType() {
		if(this.getRNG().nextInt(BEARD_CHANCE)==0) return Type.BEARD;
		return super.getType();
	}
	
//	@Override
//	protected void initEntityAI() {
//		super.initEntityAI();
//		this.tasks.addTask(9, new EntityAIDestroyBlocks(this));
//	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NORMAL_ZOMBIE;
	}

}
