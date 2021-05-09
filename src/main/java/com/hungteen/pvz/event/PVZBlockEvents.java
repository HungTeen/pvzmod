package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.zombie.other.CoffinEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZBlockEvents {

	@SubscribeEvent
	public static void onBlockPlacedBy(BlockEvent.EntityPlaceEvent ev) {
		BlockPattern blockpattern = BlockPatternBuilder.start().aisle("~^~", "#^#", "~^~").where('#', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.OBSIDIAN))).where('~', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(Blocks.WITHER_ROSE))).build();
		World worldIn = (World) ev.getWorld();
		if (!worldIn.isClientSide) {
	         Block block = ev.getPlacedBlock().getBlock();
	         BlockPos pos = ev.getPos();
	         boolean flag = block == Blocks.WITHER_ROSE;
	         if (flag && pos.getY() >= 2 && worldIn.getDifficulty() != Difficulty.PEACEFUL) {
	            BlockPattern.PatternHelper blockpattern$patternhelper = blockpattern.find(worldIn, pos);
	            if (blockpattern$patternhelper != null) {
	               for(int i = 0; i < blockpattern.getWidth(); ++i) {
	                  for(int j = 0; j < blockpattern.getHeight(); ++j) {
	                     CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.getBlock(i, j, 0);
	                     worldIn.setBlock(cachedblockinfo.getPos(), Blocks.AIR.defaultBlockState(), 2);
	                     worldIn.levelEvent(2001, cachedblockinfo.getPos(), Block.getId(cachedblockinfo.getState()));
	                  }
	               }
	               CoffinEntity coffin = EntityRegister.COFFIN.get().create(worldIn);
	               BlockPos spawnPos = blockpattern$patternhelper.getBlock(1, 2, 0).getPos();
	               EntityUtil.onMobEntitySpawn(worldIn, coffin, spawnPos);
	               coffin.moveTo(spawnPos.getX() + 0.5, spawnPos.getY() + 0.55, spawnPos.getZ() + 0.5, blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F, 0.0F);
	               coffin.yBodyRot = blockpattern$patternhelper.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;
	               for(ServerPlayerEntity serverplayerentity : worldIn.getEntitiesOfClass(ServerPlayerEntity.class, coffin.getBoundingBox().inflate(50.0D))) {
	                  CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity, coffin);
	               }
	               for(int k = 0; k < blockpattern.getWidth(); ++k) {
	                  for(int l = 0; l < blockpattern.getHeight(); ++l) {
	                	 worldIn.blockUpdated(blockpattern$patternhelper.getBlock(k, l, 0).getPos(), Blocks.AIR);
	                  }
	               }

	            }
	         }
	      }
	}
}
