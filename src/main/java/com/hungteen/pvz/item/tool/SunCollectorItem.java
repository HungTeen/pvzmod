package com.hungteen.pvz.item.tool;

import javax.annotation.Nullable;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SunCollectorItem extends Item{

	public static final int SINGLE_COLLET_COOL_DOWN = 10;
	public static final int SINGLE_COLLET_RANGE = 20;
	public static final int RANGE_COLLECT_COOL_DOWN = 200;
	
	public SunCollectorItem() {
		super(new Properties().group(GroupRegister.PVZ_MISC).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(! worldIn.isRemote) {
			if(playerIn.isSneaking()) {//range collect
				playerIn.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
					int lvl = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.TREE_LVL);
					int treeLvl = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.TREE_LVL);
					double range = getRangeCollectRange(lvl);
					for(SunEntity sun : worldIn.getEntitiesWithinAABB(SunEntity.class, EntityUtil.getEntityAABB(playerIn, range, range), (sun)->{
						return sun.isAlive();
					})) {
						l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, sun.getAmount());
						if(l.getPlayerData().getPlayerStats().getPlayerStats(Resources.SUN_NUM) == PlayerUtil.getPlayerMaxSunNum(treeLvl)) {
							break;
						}
						sun.remove();
					}
				});
				playerIn.getCooldownTracker().setCooldown(this, RANGE_COLLECT_COOL_DOWN);
			} else {
				playerIn.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
					Vec3d look = playerIn.getLookVec();
				    Vec3d start = playerIn.getPositionVec().add(0, playerIn.getEyeHeight(), 0);
				    double range = SINGLE_COLLET_RANGE;
				    Vec3d end = start.add(look.normalize().mul(range, range, range));
				    RayTraceContext ray = new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, playerIn);
				    RayTraceResult result = worldIn.rayTraceBlocks(ray);
				    if(result.getType() != RayTraceResult.Type.MISS) {// hit something
					    end = result.getHitVec();
				    }
					int lvl = l.getPlayerData().getPlayerStats().getPlayerStats(Resources.TREE_LVL);
					EntityRayTraceResult entityRay = this.rayTraceEntities(worldIn, playerIn, lvl, start, end);
				    if(entityRay != null && entityRay.getType() == Type.ENTITY) {
					    if(entityRay.getEntity() instanceof SunEntity) {
						    SunEntity sun = (SunEntity) entityRay.getEntity();
						    PlayerUtil.addPlayerStats(playerIn, Resources.SUN_NUM, sun.getAmount());
						    sun.remove();
						    playerIn.getCooldownTracker().setCooldown(this, SINGLE_COLLET_COOL_DOWN);
					    }
				    }
				});
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
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
	protected EntityRayTraceResult rayTraceEntities(World world, PlayerEntity player, int lvl, Vec3d startVec, Vec3d endVec) {
		return ProjectileHelper.rayTraceEntities(world, player, startVec, endVec, 
				player.getBoundingBox().grow(getSingleCollectRange(lvl)), (entity) -> {
			return entity instanceof SunEntity && entity.isAlive();
		});
	}
	
}
