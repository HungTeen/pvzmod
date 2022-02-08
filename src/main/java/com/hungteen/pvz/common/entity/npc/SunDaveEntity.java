package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.common.container.provider.PVZContainerProvider;
import com.hungteen.pvz.common.container.shop.SunShopContainer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SunDaveEntity extends AbstractDaveEntity {

	public SunDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.transactionResource = StringUtil.prefix("sun_dave");
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
