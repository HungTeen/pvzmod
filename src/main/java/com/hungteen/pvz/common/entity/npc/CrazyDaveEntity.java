package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.common.container.provider.PVZContainerProvider;
import com.hungteen.pvz.common.container.shop.DaveShopContainer;
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

public class CrazyDaveEntity extends AbstractDaveEntity {

	public CrazyDaveEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
		this.transactionResource = StringUtil.prefix("crazy_dave");
	}

	@Override
	protected void openContainer(ServerPlayer player) {
		NetworkHooks.openGui(player, new PVZContainerProvider() {

			@Override
			public Container createMenu(int id, PlayerInventory inventory,
										Player playerEntity) {
				return new DaveShopContainer(id, playerEntity, CrazyDaveEntity.this.getId());
			}

		}, buffer -> {
			buffer.writeInt(CrazyDaveEntity.this.getId());
		});
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 2.6f);
	}
	
	@Override
	public PVZGroupType getEntityGroupType() {
		return PVZGroupType.PLANTS;
	}

}
