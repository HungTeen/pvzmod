package com.hungteen.pvzmod.items.tools;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.blocks.misc.BlockFlowerPot;
import com.hungteen.pvzmod.blocks.misc.BlockPVZLilyPad;
import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.entities.plants.common.EntityDoubleShooter;
import com.hungteen.pvzmod.entities.plants.common.EntityGatlingPea;
import com.hungteen.pvzmod.entities.plants.common.EntityKernelPult;
import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;
import com.hungteen.pvzmod.entities.plants.common.EntityPeaShooter;
import com.hungteen.pvzmod.entities.plants.common.EntitySplitPea;
import com.hungteen.pvzmod.entities.plants.common.EntityThreePeater;
import com.hungteen.pvzmod.entities.plants.defence.EntityNutWall;
import com.hungteen.pvzmod.entities.plants.defence.EntityPumpkin;
import com.hungteen.pvzmod.entities.plants.defence.EntityTallNut;
import com.hungteen.pvzmod.entities.plants.electricity.EntityLightningRod;
import com.hungteen.pvzmod.entities.plants.explosion.EntityCherryBomb;
import com.hungteen.pvzmod.entities.plants.explosion.EntityPotatoMine;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.entities.plants.fight.EntitySquash;
import com.hungteen.pvzmod.entities.plants.fight.EntityTangleKelp;
import com.hungteen.pvzmod.entities.plants.flame.EntityJalapeno;
import com.hungteen.pvzmod.entities.plants.flame.EntityTorchWood;
import com.hungteen.pvzmod.entities.plants.ice.EntityIceShroom;
import com.hungteen.pvzmod.entities.plants.ice.EntityIcebergLettuce;
import com.hungteen.pvzmod.entities.plants.ice.EntitySnowPea;
import com.hungteen.pvzmod.entities.plants.ice.EntityWinterMelon;
import com.hungteen.pvzmod.entities.plants.light.EntityGoldLeaf;
import com.hungteen.pvzmod.entities.plants.light.EntitySunFlower;
import com.hungteen.pvzmod.entities.plants.light.EntityTwinSunFlower;
import com.hungteen.pvzmod.entities.plants.magic.EntityCatTail;
import com.hungteen.pvzmod.entities.plants.magic.EntityCoffeeBean;
import com.hungteen.pvzmod.entities.plants.magic.EntityHypnoShroom;
import com.hungteen.pvzmod.entities.plants.magic.EntityMariGold;
import com.hungteen.pvzmod.entities.plants.magic.EntityStrangeCat;
import com.hungteen.pvzmod.gui.GuiHandler;
import com.hungteen.pvzmod.items.ItemBase;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.registry.CreativeTabRegister;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemSummonCard extends ItemBase {

	private final Plants plant;

	public ItemSummonCard(String name, Plants plant) {
		super(name, CreativeTabRegister.CARD_TAB);
		this.plant = plant;
		setMaxStackSize(1);
	}

	public Plants getPlant()
	{
		return this.plant;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		RayTraceResult raytraceresult = this.rayTrace(world, player, true);
		if (this.plant == Plants.LILY_PAD) {// 放置睡莲
			if (this.placeLilyPad(world, player, hand)) {
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			}
		} else if (this.plant == Plants.TANGLE_KELP && raytraceresult != null) {// 放置海草
			if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos blockpos = raytraceresult.getBlockPos();
				IBlockState state = world.getBlockState(blockpos);
				if (state.getMaterial() == Material.WATER) {
					int cost = PlantsUtil.getPlantSunCost(this.plant);
					if (PlayerUtil.getPlayerSunNum(player) >= cost) {// 阳光够
						// 放置成功
						if (!world.isRemote) {
							PlayerUtil.addPlayerSunNum(player, -cost);
							EntityTangleKelp kelp = new EntityTangleKelp(world);
							kelp.setPosition(blockpos.getX(), blockpos.getY(), blockpos.getZ());
							world.spawnEntity(kelp);
						}
						player.getCooldownTracker().setCooldown(this, PlantsUtil.getPlantCoolDownTime(plant, 1));
						player.addStat(StatList.getObjectUseStats(this));
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
					}
				}
			}
		}
		if (!world.isRemote) {
			BlockPos pos = new BlockPos(player);
			Item item = itemstack.getItem();
			if (item == ItemRegister.PEA_SHOOTER_CARD) {
				player.openGui(Main.instance, GuiHandler.PEA_SHOOTER_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.SUN_FLOWER_CARD) {
				player.openGui(Main.instance, GuiHandler.SUN_FLOWER_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.CHERRY_BOMB_CARD) {
				player.openGui(Main.instance, GuiHandler.CHERRY_BOMB_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.NUT_WALL_CARD) {
				player.openGui(Main.instance, GuiHandler.NUT_WALL_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.POTATO_MINE_CARD) {
				player.openGui(Main.instance, GuiHandler.POTATO_MINE_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.SNOW_PEA_CARD) {
				player.openGui(Main.instance, GuiHandler.SNOW_PEA_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.DOUBLE_SHOOTER_CARD) {
				player.openGui(Main.instance, GuiHandler.DOUBLE_SHOOTER_CARD, world, pos.getX(), pos.getY(),
						pos.getZ());
			} else if (item == ItemRegister.HYPNO_SHROOM_CARD) {
				player.openGui(Main.instance, GuiHandler.HYPNO_SHROOM_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.ICE_SHROOM_CARD) {
				player.openGui(Main.instance, GuiHandler.ICE_SHROOM_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.LILY_PAD_CARD) {
				player.openGui(Main.instance, GuiHandler.LILY_PAD_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.SQUAHS_CARD) {
				player.openGui(Main.instance, GuiHandler.SQUASH_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.THREE_PEATER_CARD) {
				player.openGui(Main.instance, GuiHandler.THREE_PEATER_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.TANGLE_KELP_CARD) {
				player.openGui(Main.instance, GuiHandler.TANGLE_KELP_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.JALAPENO_CARD) {
				player.openGui(Main.instance, GuiHandler.JALAPENO_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.SPIKE_WEED_CARD) {
				player.openGui(Main.instance, GuiHandler.SPIKE_WEED_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.TORCH_WOOD_CARD) {
				player.openGui(Main.instance, GuiHandler.TORCH_WOOD_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.TALL_NUT_CARD) {
				player.openGui(Main.instance, GuiHandler.TALL_NUT_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.SPLIT_PEA_CARD) {
				player.openGui(Main.instance, GuiHandler.SPLIT_PEA_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.PUMPKIN_CARD) {
				player.openGui(Main.instance, GuiHandler.PUMPKIN_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.CABBAGE_PULT_CARD) {
				player.openGui(Main.instance, GuiHandler.CABBAGE_PULT_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.FLOWER_POT_CARD) {
				player.openGui(Main.instance, GuiHandler.FLOWER_POT_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.KERNEL_PULT_CARD) {
				player.openGui(Main.instance, GuiHandler.KERNEL_PULT_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.COFFEE_BEAN_CARD) {
				player.openGui(Main.instance, GuiHandler.COFFEE_BEAN_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.MARIGOLD_CARD) {
				player.openGui(Main.instance, GuiHandler.MARIGOLD_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.MELON_PULT_CARD) {
				player.openGui(Main.instance, GuiHandler.MELON_PULT_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.GATLING_PEA_CARD) {
				player.openGui(Main.instance, GuiHandler.GATLING_PEA_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.TWIN_SUNFLOWER_CARD) {
				player.openGui(Main.instance, GuiHandler.TWIN_SUNFLOWER_CARD, world, pos.getX(), pos.getY(),
						pos.getZ());
			} else if (item == ItemRegister.CAT_TAIL_CARD) {
				player.openGui(Main.instance, GuiHandler.CAT_TAIL_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.WINTER_MELON_CARD) {
				player.openGui(Main.instance, GuiHandler.WINTER_MELON_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			} else if (item == ItemRegister.SPIKE_ROCK_CARD) {
				player.openGui(Main.instance, GuiHandler.SPIKE_ROCK_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			}else if (item == ItemRegister.ICEBERG_LETTUCE_CARD) {
				player.openGui(Main.instance, GuiHandler.ICEBERG_LETTUCE_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			}else if (item == ItemRegister.GOLD_LEAF_CARD) {
				player.openGui(Main.instance, GuiHandler.GOLD_LEAF_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			}else if (item == ItemRegister.LIGHTNING_ROD_CARD) {
				player.openGui(Main.instance, GuiHandler.LIGHTNING_ROD_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			}else if (item == ItemRegister.STRANGE_CAT_CARD) {
				player.openGui(Main.instance, GuiHandler.STRANGE_CAT_CARD, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		IBlockState state = worldIn.getBlockState(pos);
		boolean itemUsed = false;
		int coolDownTime = 0;
		if (!worldIn.isRemote && facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack)
				&& worldIn.isAirBlock(pos.up())) {
			BlockPos blockPos = pos.offset(facing);
			if (itemstack.getItem() == ItemRegister.LILY_PAD_CARD||PlantsUtil.checkUpgradePlant(plant)) {
				return EnumActionResult.FAIL;
			} else if (itemstack.getItem() == ItemRegister.FLOWER_POT_CARD) {
				if (this.placeFlowerPot(worldIn, player, blockPos)) {
					itemUsed = true;
					coolDownTime = PlantsUtil.getPlantCoolDownTime(plant, 1);
				}
			} else {
				if(plant==Plants.CAT_TAIL) {
					if(state.getBlock()!=BlockRegister.LILY_PAD) {
						return EnumActionResult.FAIL;
					}
				}
				EntityPlantBase entity = getPlant(worldIn);
				if (entity == null) {
					return EnumActionResult.FAIL;
				}
				entity.setPlantLvl(PlayerUtil.getPlantLvl(player, this.plant));
				entity.updateAttributes();
				entity.setOwnerName(player.getName());
				coolDownTime = entity.getCoolDownTime();
				if (PlayerUtil.getPlayerSunNum(player)>= entity.getSunCost()) {
					PlayerUtil.addPlayerSunNum(player, -entity.getSunCost());
					entity.setPosition((double) blockPos.getX() + 0.5D, (double) blockPos.getY(),
							(double) blockPos.getZ() + 0.5D);
					// itemstack.shrink(1);
					worldIn.playSound(null, (double) blockPos.getX() + 0.5D, (double) blockPos.getY(),
							(double) blockPos.getZ() + 0.5D, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1, 1);
					itemUsed = true;
					worldIn.spawnEntity(entity);
				}
			}
		}
		if (itemUsed) {
			player.getCooldownTracker().setCooldown(this, coolDownTime);
//			if (ConfigurationUtil.MainConfig.globalSettings.globalDifficulty == ConfigurationUtil.Difficulty.EASY) {
//				PlayerUtil.addPlayerXp(player, 1);
//			} else if (ConfigurationUtil.MainConfig.globalSettings.globalDifficulty == ConfigurationUtil.Difficulty.NORMAL) {
//				if (this.itemRand.nextInt(2) == 0) {
//					PlayerUtil.addPlayerXp(player, 1);
//				}
//			} else if (ConfigurationUtil.MainConfig.globalSettings.globalDifficulty == ConfigurationUtil.Difficulty.HARD) {
//				if (this.itemRand.nextInt(4) == 0) {
//					PlayerUtil.addPlayerXp(player, 1);
//				}
//			}
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
//
//	private boolean placeBlock(World world, EntityPlayer player, BlockPos pos, Block block) {
//		int cost = 25;
//		if (PVZGuiTabPlayerData.sunNum >= cost) {
//			PlayerUtil.addPlayerSunNum(player, -cost);
//			// itemstack.shrink(1);
//			world.playSound(null, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D,
//					SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1, 1);
//			world.setBlockState(pos, block.getDefaultState());
//			return true;
//		}
//		return false;
//	}

	public EntityPlantBase getPlant(World worldIn) {
		if (this.plant == Plants.PEA_SHOOTER)// 1-1
			return new EntityPeaShooter(worldIn);
		else if (this.plant == Plants.SUN_FLOWER)
			return new EntitySunFlower(worldIn);
		else if (this.plant == Plants.CHERRY_BOMB)
			return new EntityCherryBomb(worldIn);
		else if (this.plant == Plants.NUT_WALL)
			return new EntityNutWall(worldIn);
		else if (this.plant == Plants.POTATO_MINE)
			return new EntityPotatoMine(worldIn);
		else if (this.plant == Plants.SNOW_PEA)
			return new EntitySnowPea(worldIn);
		else if (this.plant == Plants.DOUBLE_SHOOTER)
			return new EntityDoubleShooter(worldIn);
		else if (this.plant == Plants.HYPNO_SHROOM)// 2-1
			return new EntityHypnoShroom(worldIn);
		else if (this.plant == Plants.ICE_SHROOM)
			return new EntityIceShroom(worldIn);
		else if (this.plant == Plants.SQUASH)// 3-1
			return new EntitySquash(worldIn);
		else if (this.plant == Plants.THREE_PEATER)
			return new EntityThreePeater(worldIn);
		else if (this.plant == Plants.TANGLE_KELP)
			return new EntityTangleKelp(worldIn);
		else if (this.plant == Plants.JALAPENO)
			return new EntityJalapeno(worldIn);
		else if (this.plant == Plants.SPIKE_WEED)
			return new EntitySpikeWeed(worldIn);
		else if (this.plant == Plants.TORCH_WOOD)
			return new EntityTorchWood(worldIn);
		else if (this.plant == Plants.TALL_NUT)
			return new EntityTallNut(worldIn);
		else if (this.plant == Plants.SPLIT_PEA)// 4-1
			return new EntitySplitPea(worldIn);
		else if (this.plant == Plants.PUMPKIN)
			return new EntityPumpkin(worldIn);
		else if (this.plant == Plants.CABBAGE_PULT)// 5-1
			return new EntityCabbagePult(worldIn);
		else if (this.plant == Plants.KERNEL_PULT)
			return new EntityKernelPult(worldIn);
		else if (this.plant == Plants.COFFEE_BEAN)
			return new EntityCoffeeBean(worldIn);
		else if (this.plant == Plants.MARIGOLD)
			return new EntityMariGold(worldIn);
		else if (this.plant == Plants.MELON_PULT)
			return new EntityMelonPult(worldIn);
		else if (this.plant == Plants.GATLING_PEA)// upgrade
			return new EntityGatlingPea(worldIn);
		else if (this.plant == Plants.TWIN_SUNFLOWER)
			return new EntityTwinSunFlower(worldIn);
		else if (this.plant == Plants.CAT_TAIL)
			return new EntityCatTail(worldIn);
		else if (this.plant == Plants.WINTER_MELON)
			return new EntityWinterMelon(worldIn);
		else if (this.plant == Plants.SPIKE_ROCK)
			return new EntitySpikeRock(worldIn);
		else if (this.plant == Plants.ICEBERG_LETTUCE)
			return new EntityIcebergLettuce(worldIn);
		else if (this.plant == Plants.GOLD_LEAF)
			return new EntityGoldLeaf(worldIn);
		else if (this.plant == Plants.LIGHTLING_ROD)
			return new EntityLightningRod(worldIn);
		else if (this.plant == Plants.STRANGE_CAT)
			return new EntityStrangeCat(worldIn);
		else {
			System.out.println("NO such plant!");
			return null;
		}
	}

	private boolean placeLilyPad(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		RayTraceResult raytraceresult = this.rayTrace(world, player, true);
		if (this.plant == Plants.LILY_PAD && raytraceresult != null) {// 放置睡莲
			if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos blockpos = raytraceresult.getBlockPos();

				if (world.isBlockModifiable(player, blockpos) && player
						.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) {

					BlockPos blockpos1 = blockpos.up();
					IBlockState iblockstate = world.getBlockState(blockpos);

					if (iblockstate.getMaterial() == Material.WATER
							&& ((Integer) iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0
							&& world.isAirBlock(blockpos1)) {
						int cost = PlantsUtil.getPlantSunCost(this.plant);
						if (PlayerUtil.getPlayerSunNum(player) >= cost) {// 阳光够
							// special case for handling block placement with water lilies
							net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot
									.getBlockSnapshot(world, blockpos1);
							world.setBlockState(blockpos1, BlockRegister.LILY_PAD.getDefaultState());
							if (net.minecraftforge.event.ForgeEventFactory
									.onPlayerBlockPlace(player, blocksnapshot, net.minecraft.util.EnumFacing.UP, hand)
									.isCanceled()) {
								blocksnapshot.restore(true, false);
							} else {
								// 放置成功
								if (!world.isRemote) {
									PlayerUtil.addPlayerSunNum(player, -cost);
									world.setBlockState(blockpos1,
											((BlockPVZLilyPad) BlockRegister.LILY_PAD).getState(player), 2);
								}
								player.getCooldownTracker().setCooldown(this,
										PlantsUtil.getPlantCoolDownTime(plant, 1));

								if (player instanceof EntityPlayerMP) {
									CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, blockpos1,
											itemstack);
								}

								player.addStat(StatList.getObjectUseStats(this));
								world.playSound(player, blockpos, SoundEvents.BLOCK_WATERLILY_PLACE,
										SoundCategory.BLOCKS, 1.0F, 1.0F);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean placeFlowerPot(World world, EntityPlayer player, BlockPos pos) {
		if (world.isAirBlock(pos)) {
			int cost = PlantsUtil.getPlantSunCost(plant);
			if (PlayerUtil.getPlayerSunNum(player) >= cost) {// 钱够
				if (!world.isRemote) {
					PlayerUtil.addPlayerSunNum(player, -cost);
					world.setBlockState(pos, ((BlockFlowerPot) BlockRegister.FLOWER_POT).getState(player));
				}
				return true;
			}
		}
		return false;
	}
}
