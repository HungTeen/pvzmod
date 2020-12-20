package com.hungteen.pvz.entity.plant.explosion;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;

public class DoomShroomEntity extends PlantBomberEntity {

	private final int destroyBlockHeight = 4;
	public static final float MAX_EXPLOSION_LEVEL = 500;
	
	public DoomShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void startBomb() {
		if(! world.isRemote) {
			EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, this.getAttackRange(), this.getAttackRange())).forEach((target) -> {
				target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			});
			EntityUtil.playSound(this, SoundRegister.DOOM.get());
			//destroy block and spawn drops
			ObjectArrayList<Pair<ItemStack, BlockPos>> list = new ObjectArrayList<>();
			int len = getDestroyBlockRange();
			if(len == 0) {
				return ;
			} else {
				-- len;
			}
			for(int i = - len;i <= len;i ++) {
				for(int j = - len;j <= len;j ++) {
					for(int k = - destroyBlockHeight;k <= len;k ++) {
						BlockPos pos = new BlockPos(this).add(i, k, j);
						BlockState state = world.getBlockState(pos);
						if (state.isAir(this.world, pos) || state.getBlock().getExplosionResistance() > MAX_EXPLOSION_LEVEL) {
							continue;
						}
						TileEntity tileentity = state.hasTileEntity() ? this.world.getTileEntity(pos) : null;
						LootContext.Builder loot = (new LootContext.Builder((ServerWorld)this.world)).withRandom(this.world.rand).withParameter(LootParameters.POSITION, pos).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withNullableParameter(LootParameters.BLOCK_ENTITY, tileentity).withNullableParameter(LootParameters.THIS_ENTITY, this);
						loot.withParameter(LootParameters.EXPLOSION_RADIUS, (float)len);
						state.getDrops(loot).forEach((stack)->{
							for(int l = 0; l < list.size(); ++l) {
		                        Pair<ItemStack, BlockPos> pair = list.get(l);
		                        ItemStack itemstack = pair.getFirst();
		                        if (ItemEntity.func_226532_a_(itemstack, stack)) {
		                            ItemStack itemstack1 = ItemEntity.func_226533_a_(itemstack, stack, 16);
		                            list.set(l, Pair.of(itemstack1, pair.getSecond()));
		                            if (list.isEmpty()) {
		                               return;
		                            }
		                        }
							}
							list.add(Pair.of(stack, pos));
						});
						world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
					}
				}
			}
			for(Pair<ItemStack, BlockPos> pair : list) {
	            Block.spawnAsEntity(this.world, pair.getSecond(), pair.getFirst());
	        }
		} else {
			world.addParticle(ParticleRegister.DOOM.get(), this.getPosX(), this.getPosY() + 3, this.getPosZ(), 0, 0, 0);
		}
	}
	
	private int getDestroyBlockRange() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.DoomRange.get();
	}

	public float getAttackRange() {
		if(this.isPlantInStage(1)) return 5;
		if(this.isPlantInStage(2)) return 6;
		return 7;
	}
	
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			return 232 + 8 * lvl;
		}
		return 400;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8f, 1.5f);
	}
	
	@Override
	public int getReadyTime() {
		return 40;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.DOOM_SHROOM;
	}

}
