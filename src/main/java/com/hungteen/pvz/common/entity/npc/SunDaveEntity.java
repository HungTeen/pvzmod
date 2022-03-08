package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.common.container.provider.PVZContainerProvider;
import com.hungteen.pvz.common.container.shop.SunShopContainer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.inventory.container.Container;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;

public class SunDaveEntity extends AbstractDaveEntity {

	public SunDaveEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
		this.transactionResource = StringUtil.prefix("sun_dave");
	}

	@Override
	protected void openContainer(ServerPlayer player) {
		NetworkHooks.openGui(player, new PVZContainerProvider() {

			@Override
			public Container createMenu(int id, PlayerInventory inventory,
										Player playerEntity) {
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
