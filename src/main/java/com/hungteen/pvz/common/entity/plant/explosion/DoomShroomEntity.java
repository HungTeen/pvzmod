package com.hungteen.pvz.common.entity.plant.explosion;

import com.hungteen.pvz.common.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
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
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DoomShroomEntity extends PlantBomberEntity {

	private final int destroyBlockHeight = 1;
	public static final float MAX_EXPLOSION_LEVEL = 500;
	
	public DoomShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void startBomb() {
		if(! level.isClientSide) {
			EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, this.getAttackRange(), this.getAttackRange())).forEach((target) -> {
				target.hurt(PVZDamageSource.causeExplosionDamage(this, this), this.getAttackDamage());
			});
			EntityUtil.playSound(this, SoundRegister.DOOM.get());
			//destroy block and spawn drops
			ObjectArrayList<Pair<ItemStack, BlockPos>> list = new ObjectArrayList<>();
			int len = 2;
			for(int i = - len;i <= len;i ++) {
				for(int j = - len;j <= len;j ++) {
					for(int k = - destroyBlockHeight;k <= len;k ++) {
						BlockPos pos = this.blockPosition().offset(i, k, j);
						BlockState state = level.getBlockState(pos);
						if (state.isAir(this.level, pos) || state.getBlock().getExplosionResistance() > MAX_EXPLOSION_LEVEL) {
							continue;
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
					}
				}
			}
			for(Pair<ItemStack, BlockPos> pair : list) {
	            Block.popResource(this.level, pair.getSecond(), pair.getFirst());
	        }
		} else {
			level.addParticle(ParticleRegister.DOOM.get(), this.getX(), this.getY() + 3, this.getZ(), 0, 0, 0);
		}
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
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.5f);
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
