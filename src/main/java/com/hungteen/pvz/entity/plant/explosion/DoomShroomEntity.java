package com.hungteen.pvz.entity.plant.explosion;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.entity.plant.interfaces.IShroomPlant;
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
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;

public class DoomShroomEntity extends PlantBomberEntity implements IShroomPlant{

	private final int destroyBlockHeight = 4;
	public static final float MAX_EXPLOSION_LEVEL = 500;
	
	public DoomShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void startBomb() {
		world.addParticle(ParticleRegister.DOOM.get(), this.getPosX(), this.getPosY() + 3, this.getPosZ(), 0, 0, 0);
//		world.createExplosion(this, PVZDamageSource.causeExplosionDamage(this, this), this.getPosX(), this.getPosY(), this.getPosZ(), 10, false, Mode.DESTROY);
		if(!world.isRemote) {
			for(LivingEntity entity : EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, this.getAttackRange(), this.getAttackRange()))) {
				entity.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			}
			this.playSound(SoundRegister.DOOM.get(), 1f, 1f);
			//destroy block and spawn drops
			ObjectArrayList<Pair<ItemStack, BlockPos>> list = new ObjectArrayList<>();
			int len = getDestroyBlockRange();
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
		}
	}
	
	
	private int getDestroyBlockRange() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.DoomRange.get();
	}

	public float getAttackRange() {
		int lvl = this.getPlantLvl();
		if(lvl <= 6) {
			return 5;
		}else if(lvl <= 13) {
			return 6;
		}else if(lvl <= 20) {
			return 7;
		}
		return 5;
	}
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 5;
			return 250 + 50 * now;
		}
		return 250;
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
