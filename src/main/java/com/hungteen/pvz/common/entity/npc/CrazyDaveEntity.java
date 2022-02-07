package com.hungteen.pvz.common.entity.npc;

import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.api.raid.IChallengeComponent;
import com.hungteen.pvz.common.container.provider.PVZContainerProvider;
import com.hungteen.pvz.common.container.shop.DaveShopContainer;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.display.ChallengeEnvelopeItem;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;
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

public class CrazyDaveEntity extends AbstractDaveEntity {

	public CrazyDaveEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.transactionResource = StringUtil.prefix("crazy_dave");
	}

	@Override
	protected void addExtraTransactions(int id) {
		super.addExtraTransactions(id);
		final WeightList<Pair<ResourceLocation, IChallengeComponent>> list = new WeightList<>();
		final int chance = 6;

		ChallengeManager.getChallengeTypes().entrySet().forEach(entry -> {
			if(entry.getValue().canTrade()){
				list.addItem(Pair.of(entry.getKey(), entry.getValue()), entry.getValue().getTradeWeight());
			}
		});

		for(int i = 0; i < chance; ++ i){
			final Pair<ResourceLocation, IChallengeComponent> pair = list.getRandomItem(this.getRandom()).get();
			final ItemStack envelope = ChallengeEnvelopeItem.setChallengeType(new ItemStack(ItemRegister.CHALLENGE_ENVELOPE.get()), pair.getFirst());
			final GoodType goodType = new GoodType(GoodTypes.CHALLENGE, envelope, pair.getSecond().getTradePrice(), 100, 1, false);
			this.addGoodToTransactions(id ++, goodType);
		}
	}

	@Override
	protected void openContainer(ServerPlayerEntity player) {
		NetworkHooks.openGui(player, new PVZContainerProvider() {

			@Override
			public Container createMenu(int id, PlayerInventory inventory,
										PlayerEntity playerEntity) {
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
