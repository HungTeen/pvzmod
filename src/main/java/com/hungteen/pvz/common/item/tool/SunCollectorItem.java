package com.hungteen.pvz.common.item.tool;

import javax.annotation.Nullable;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class SunCollectorItem extends Item{

	public static final int SINGLE_COLLET_COOL_DOWN = 10;
	public static final int RANGE_COLLECT_COOL_DOWN = 200;
	
	public SunCollectorItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC).stacksTo(1));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(! worldIn.isClientSide) {
			if(playerIn.isShiftKeyDown()) {//range collect
				playerIn.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
					int lvl = l.getPlayerData().getResource(Resources.TREE_LVL);
					int treeLvl = l.getPlayerData().getResource(Resources.TREE_LVL);
					double range = getRangeCollectRange(lvl);
					boolean has = false;
					for(SunEntity sun : worldIn.getEntitiesOfClass(SunEntity.class, EntityUtil.getEntityAABB(playerIn, range, range), (sun)->{
						return sun.isAlive();
					})) {
						if(l.getPlayerData().getResource(Resources.SUN_NUM) == PlayerUtil.getPlayerMaxSunNum(treeLvl)) {
							break;
						}
						sun.onCollectedByPlayer(playerIn);
						has = true;
					}
					playerIn.getCooldowns().addCooldown(this, has ? RANGE_COLLECT_COOL_DOWN : SINGLE_COLLET_COOL_DOWN);
					if(has) {
						playerIn.awardStat(Stats.ITEM_USED.get(this));
					}
				});
			} else {
				playerIn.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
					Vector3d look = playerIn.getLookAngle();
				    Vector3d start = playerIn.position().add(0, playerIn.getEyeHeight(), 0);
				    int lvl = l.getPlayerData().getResource(Resources.TREE_LVL);
				    double range = getSingleCollectRange(lvl);
				    Vector3d end = start.add(look.normalize().multiply(range, range, range));
				    RayTraceContext ray = new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, playerIn);
				    RayTraceResult result = worldIn.clip(ray);
				    if(result.getType() != RayTraceResult.Type.MISS) {// hit something
					    end = result.getLocation();
				    }
					EntityRayTraceResult entityRay = this.rayTraceEntities(worldIn, playerIn, range, start, end);
				    if(entityRay != null && entityRay.getType() == Type.ENTITY) {
					    if(entityRay.getEntity() instanceof SunEntity) {
						    SunEntity sun = (SunEntity) entityRay.getEntity();
						    sun.onCollectedByPlayer(playerIn);
						    playerIn.awardStat(Stats.ITEM_USED.get(this));
						    playerIn.getCooldowns().addCooldown(this, SINGLE_COLLET_COOL_DOWN);
					    }
				    }
				});
			}
		}
		return super.use(worldIn, playerIn, handIn);
	}
	
	public static float getSingleCollectRange(int lvl) {
		int now = (lvl - 1) / 20;
		return 20 + 5 * now;
	}
	
	public static float getRangeCollectRange(int lvl) {
		if(lvl <= 60) {
			int now = (lvl - 1) / 20;
			return 8 + 2 * now;
		} else if(lvl <= 100){
			int now = (lvl - 1) / 20 - 3;
			return 15 + 3 * now;
		}
		return 18;
	}
	
	/**
	 * Gets the EntityRayTraceResult representing the entity hit
	 */
	@Nullable
	protected EntityRayTraceResult rayTraceEntities(World world, PlayerEntity player, double range, Vector3d startVec, Vector3d endVec) {
		return ProjectileHelper.getEntityHitResult(world, player, startVec, endVec, 
				player.getBoundingBox().inflate(range), (entity) -> {
			return entity instanceof SunEntity && entity.isAlive();
		});
	}
	
}
