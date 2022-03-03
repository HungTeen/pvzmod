package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.event.handler.BlockEventHandler;

import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZBlockEvents {

	@SubscribeEvent
	public static void onPlayerBreakBlock(BlockEvent.BreakEvent ev) {
		BlockEventHandler.checkAndDropSeeds(ev);
		BlockEventHandler.triggerAmethystAround(ev);
	}

	@SubscribeEvent
	public static void onBlockPlacedBy(BlockEvent.EntityPlaceEvent ev) {
		//TODO coffincarriers(Coffin) summoning method deleted temporaryly
//		BlockPattern blockpattern = BlockPatternBuilder.start().aisle("~^~", "#^#", "~^~").where('#', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.OBSIDIAN))).where('~', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.WITHER_ROSE))).build();
//		World worldIn = (World) ev.getWorld();
//		if (!worldIn.isClientSide) {
//	         Block block = ev.getPlacedBlock().getBlock();
//	         BlockPos pos = ev.getPos();
//	         boolean flag = block == Blocks.WITHER_ROSE;
//	         if (flag && pos.getY() >= 2 && worldIn.getDifficulty() != Difficulty.PEACEFUL) {
//	            BlockPattern.PatternHelper blockpattern$patternhelper = blockpattern.find(worldIn, pos);
//	            if (blockpattern$patternhelper != null) {
//	               for(int i = 0; i < blockpattern.getWidth(); ++i) {
//	                  for(int j = 0; j < blockpattern.getHeight(); ++j) {
//	                     CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.getBlock(i, j, 0);
//	                     worldIn.setBlock(cachedblockinfo.getPos(), Blocks.AIR.defaultBlockState(), 2);
//	                     worldIn.levelEvent(2001, cachedblockinfo.getPos(), Block.getId(cachedblockinfo.getState()));
//	                  }
//	               }
//	               CoffinEntity coffin = EntityRegister.COFFIN.get().create(worldIn);
//	               BlockPos spawnPos = blockpattern$patternhelper.getBlock(1, 2, 0).getPos();
//	               EntityUtil.onEntitySpawn(worldIn, coffin, spawnPos);
//	               coffin.moveTo(spawnPos.getX() + 0.5, spawnPos.getY() + 0.55, spawnPos.getZ() + 0.5, blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F, 0.0F);
//	               coffin.yBodyRot = blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;
//	               for(ServerPlayerEntity serverplayerentity : worldIn.getEntitiesOfClass(ServerPlayerEntity.class, coffin.getBoundingBox().inflate(50.0D))) {
//	                  CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, coffin);
//	               }
//	               for(int k = 0; k < blockpattern.getWidth(); ++k) {
//	                  for(int l = 0; l < blockpattern.getHeight(); ++l) {
//	                	 worldIn.blockUpdated(blockpattern$patternhelper.getBlock(k, l, 0).getPos(), Blocks.AIR);
//	                  }
//	               }
//
//	            }
//	         }
//	      }
	}
}
