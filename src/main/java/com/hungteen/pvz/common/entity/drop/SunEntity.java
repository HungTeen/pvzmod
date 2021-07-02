package com.hungteen.pvz.common.entity.drop;

import java.util.Map.Entry;
import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.enchantment.EnchantmentUtil;
import com.hungteen.pvz.common.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.common.event.events.PlayerCollectDropEvent;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.PlaySoundPacket;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
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
		if(! this.onGround && ! this.isInWater()) {
			this.setDeltaMovement(this.getDeltaMovement().x(), - fall_speed, this.getDeltaMovement().z());
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		//25 0.6 
		int amount = this.getAmount();
		float w = amount * 1f / 200 + 0.3f, h = amount * 1f / 75 + 0.1f;
		return new EntitySize(w, h, false); //max (0.8w,1.5h) min(0.4w,0.3h)
	}
	
	@Override
	public void playerTouch(PlayerEntity entityIn) {
		if(!this.level.isClientSide && this.isAlive() && this.getDropState() != DropStates.STEAL) {
			this.onCollectedByPlayer(entityIn);
		}
	}
	
	public void onCollectedByPlayer(PlayerEntity player) {
		if(MinecraftForge.EVENT_BUS.post(new PlayerCollectDropEvent.PlayerCollectSunEvent(player, this))) {
			return ;
		}
		Entry<EquipmentSlotType, ItemStack> entry = EnchantmentHelper.getRandomItemWith(EnchantmentRegister.SUN_MENDING.get(), player);
		if(entry != null) {
			ItemStack stack = entry.getValue();
            if (! stack.isEmpty() && stack.isDamaged()) {
               int canRepair = Math.min(EnchantmentUtil.getRepairDamageByAmount(stack, this.getAmount()), stack.getDamageValue());
               this.setAmount(this.getAmount() - EnchantmentUtil.getSunCostByDamage(stack, canRepair));
               stack.setDamageValue(stack.getDamageValue() - canRepair);
            }
		}
		if(this.getAmount() > 0) {
			PlayerUtil.addPlayerStats(player, Resources.SUN_NUM, this.getAmount());
		}
		PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
			return (ServerPlayerEntity) player;
		}), new PlaySoundPacket(0));
		this.remove();
	}
	
	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}
	
	public static void spawnSunsByAmount(World world, BlockPos pos, int amount) {
		spawnSunsByAmount(world, pos, amount, 75, 1);
	}
	
	/**
	 * spawn sun in range, each is set to a specific amount.
	 * {@link SunFlowerEntity#genSuper()} 
	 */
	public static void spawnSunsByAmount(World world, BlockPos pos, int amount, int each, int range) {
		while(amount >= each) {
			amount -= each;
			spawnSunRandomly(world, pos, each, range);
		}
		if(amount != 0) {
			spawnSunRandomly(world, pos, amount, range);
			amount = 0;
		}
	}
	
	/**
	 * spawn sun entity in range randomly with specific amount.
	 */
	public static void spawnSunRandomly(World world, BlockPos pos, int amount, int dis) {
		SunEntity sun = EntityRegister.SUN.get().create(world);
		sun.setAmount(amount);
		EntityUtil.onMobEntityRandomPosSpawn(world, sun, pos, dis);
	}
	
	public static boolean canSunSpawn(EntityType<? extends SunEntity> zombieType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random rand) {
		if(worldIn instanceof ServerWorld) {
			return ! ((ServerWorld)worldIn).isRainingAt(pos) && ((ServerWorld)worldIn).isDay() && worldIn.getBrightness(LightType.SKY, pos) >= 15;
		}
		return worldIn.getBrightness(LightType.SKY, pos) >= 15;
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.SunLiveTick.get();
	}
	
}