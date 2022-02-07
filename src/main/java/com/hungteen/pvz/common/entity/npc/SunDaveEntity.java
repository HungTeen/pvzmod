package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.block.special.SlotMachineBlock;
import com.hungteen.pvz.common.container.provider.PVZContainerProvider;
import com.hungteen.pvz.common.container.shop.SunShopContainer;
import com.hungteen.pvz.common.datapack.LotteryTypeLoader;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.others.WeightList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SunDaveEntity extends AbstractDaveEntity {

	public SunDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.transactionResource = StringUtil.prefix("sun_dave");
	}

	@Override
	protected void addExtraTransactions(int id) {
		super.addExtraTransactions(id);
		final WeightList<Pair<ResourceLocation, SlotMachineTileEntity.LotteryType>> list = new WeightList<>();
		final int chance = 2;

		LotteryTypeLoader.getLotteries().entrySet().forEach(entry -> {
			list.addItem(Pair.of(entry.getKey(), entry.getValue()), entry.getValue().getTradeWeight());
		});

		for(int i = 0; i < chance; ++ i){
			final Pair<ResourceLocation, SlotMachineTileEntity.LotteryType> pair = list.getRandomItem(this.getRandom()).get();
			final ItemStack stack = new ItemStack(BlockRegister.SLOT_MACHINE.get());
			SlotMachineBlock.setResourceTag(stack, pair.getFirst());
			final GoodType goodType = new GoodType(GoodTypes.SLOT_MACHINE, stack, pair.getSecond().getTradePrice(), 100, 2, false);
			this.addGoodToTransactions(id ++, goodType);
		}
	}

	@Override
	protected void openContainer(ServerPlayerEntity player) {
		NetworkHooks.openGui(player, new PVZContainerProvider() {

			@Override
			public Container createMenu(int id, PlayerInventory inventory,
										PlayerEntity playerEntity) {
				return new SunShopContainer(id, playerEntity, SunDaveEntity.this.getId());
			}

		}, buffer -> {
			buffer.writeInt(SunDaveEntity.this.getId());
		});
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 2.5f);
	}
	
}
