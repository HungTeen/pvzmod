package com.hungteen.pvz.common.entity.plant.explosion;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.misc.DoomFixerEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DoomShroomEntity extends PlantBomberEntity {

	public static final float MAX_EXPLOSION_LEVEL = 500;
	
	public DoomShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! this.level.isClientSide) {
			if(this.getAttackTime() == this.getReadyTime() - 2) {
				DoomFixerEntity fixer = new DoomFixerEntity(this.level, this);
				EntityUtil.onEntitySpawn(level, fixer, this.blockPosition());
			}
		}
	}
	
	@Override
	public void startBomb(boolean server) {
		if(server) {
			//deal damage to targets.
			final float range = 10.5F;
			final AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, range, range);
			EntityUtil.getWholeTargetableEntities(this, aabb).forEach(target -> {
				if(target instanceof EnderDragonEntity) {//make ender_dragon can be damaged by doom shroom. 
					((EnderDragonEntity) target).hurt(((EntityDamageSource)DamageSource.mobAttack(this)).setThorns().setExplosion(), this.getAttackDamage() * 2);
				} else {
					target.hurt(PVZDamageSource.explode(this), this.getAttackDamage());
				}
			});
			PVZPlantEntity.clearLadders(this, aabb);
			EntityUtil.playSound(this, SoundRegister.DOOM.get());
			//destroy block and spawn drops
			if(net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this)) {
				this.destroyBlocks();
			}
		} else {
			for(int i = 0; i < 300; ++ i) {
				WorldUtil.spawnRandomSpeedParticle(level, ParticleRegister.SPORE.get(), this.position().add(0, 1, 0), 0.8F);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	protected void destroyBlocks() {
		ObjectArrayList<Pair<ItemStack, BlockPos>> list = new ObjectArrayList<>();
		List<BlockPos> posList = new ArrayList<>();
		//lower block positions.
		final int len = 2;
		for(int i = - len;i <= len; ++ i) {
			for(int j = - len;j <= len; ++ j) {
				for(int k = - 2; k < 0; ++ k) {
					posList.add(this.blockPosition().offset(i, k, j));
				}
			}
		}
		//upper block positions.
		final int range = 10;
		for(int h = 0; h <= range + 8; ++ h) {
		    for(int i = - range; i <= range; ++ i) {
			    for(int j = - range; j <= range; ++ j) {
			    	if(new Vector3d(i, h - 5, j).lengthSqr() <= range * range) {
			    		posList.add(this.blockPosition().offset(i, h, j));
			    	}
			    }
		    }
		}
		posList.forEach(pos -> {
			BlockState state = level.getBlockState(pos);
			if (state.isAir(this.level, pos) || state.getBlock().getExplosionResistance() > MAX_EXPLOSION_LEVEL) {
				return ;
			}
			TileEntity tileentity = state.hasTileEntity() ? this.level.getBlockEntity(pos) : null;
			LootContext.Builder loot = (new LootContext.Builder((ServerWorld)this.level)).withRandom(this.level.random).withParameter(LootParameters.ORIGIN, Vector3d.atCenterOf(pos)).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withOptionalParameter(LootParameters.BLOCK_ENTITY, tileentity).withOptionalParameter(LootParameters.THIS_ENTITY, this);
			loot.withParameter(LootParameters.EXPLOSION_RADIUS, (float)len);
			state.getDrops(loot).forEach((stack)->{
				for(int l = 0; l < list.size(); ++l) {
                    Pair<ItemStack, BlockPos> pair = list.get(l);
                    ItemStack itemstack = pair.getFirst();
                    if (ItemEntity.areMergable(itemstack, stack)) {
                        ItemStack itemstack1 = ItemEntity.merge(itemstack, stack, 16);
                        list.set(l, Pair.of(itemstack1, pair.getSecond()));
                        if (list.isEmpty()) {
                           return;
                        }
                    }
				}
				list.add(Pair.of(stack, pos));
			});
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		});
		for(Pair<ItemStack, BlockPos> pair : list) {
            Block.popResource(this.level, pair.getSecond(), pair.getFirst());
        }
	}
	
	public float getAttackDamage() {
		return PlantUtil.getPlantAverageProgress(this, 200, 800);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.5f);
	}
	
	@Override
	public int getReadyTime() {
		return 50;
	}

	@Override
	public PlantType getPlantType() {
		return PVZPlants.DOOM_SHROOM;
	}

}
