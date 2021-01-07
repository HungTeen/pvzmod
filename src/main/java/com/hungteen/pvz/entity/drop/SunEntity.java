package com.hungteen.pvz.entity.drop;

import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.network.PlaySoundPacket;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;

public class SunEntity extends DropEntity {
	
	private float fall_speed = 0.03f;
	
	public SunEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(25);//default sun amount (nature spawn)
		this.setNoGravity(true);
	}

	@Override
	public void tick() {
		super.tick();
		if(!this.onGround&&!this.isInWater()) {
			this.setMotion(this.getMotion().getX(), - fall_speed, this.getMotion().getZ());
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		//25 0.6 
		int amount = this.getAmount();
		float w = amount * 1f / 200 + 0.3f, h = amount * 1f / 75 + 0.1f;
		return new EntitySize(w, h, false); //max (0.8w,1.5h) min(0.4w,0.3h)
	}
	
	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if(!this.world.isRemote && this.isAlive()) {
			this.onCollectedByPlayer(entityIn);
		}
	}
	
	public void onCollectedByPlayer(PlayerEntity player){
		PlayerUtil.addPlayerStats(player, Resources.SUN_NUM, this.getAmount());
		PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(()->{
			return (ServerPlayerEntity) player;
		}), new PlaySoundPacket(0));
		this.remove();
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
	
	public static void spawnSunsByAmount(World world, BlockPos pos, int amount) {
		while(amount >= 75) {
			amount -= 75;
			spawnSunRandomly(world, pos, 75);
		}
		if(amount != 0) {
			spawnSunRandomly(world, pos, amount);
			amount = 0;
		}
	}
	
	public static void spawnSunRandomly(World world, BlockPos pos, int amount) {
		SunEntity sun = EntityRegister.SUN.get().create(world);
		sun.setAmount(amount);
		EntityUtil.onMobEntityRandomPosSpawn(world, sun, pos, 1);
	}
	
	public static boolean canSunSpawn(EntityType<? extends SunEntity> zombieType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random rand) {
		if(worldIn instanceof ServerWorld) {
			return ! ((ServerWorld)worldIn).isRainingAt(pos) && ((ServerWorld)worldIn).isDaytime() && worldIn.getLightFor(LightType.SKY, pos) >= 15;
		}
		return worldIn.getLightFor(LightType.SKY, pos) >= 15;
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.SunLiveTick.get();
	}
	
}