package com.hungteen.pvz.common.container.shop;

import com.hungteen.pvz.common.container.ContainerRegister;
import com.hungteen.pvz.common.entity.npc.AbstractDaveEntity;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.entity.player.PlayerEntity;

public class MysteryShopContainer extends AbstractDaveShopContainer {

	public static final int MAX_MYSTERY_GOOD = 8;
	
	public MysteryShopContainer(int id, PlayerEntity player, int entityId) {
		super(ContainerRegister.MYSTERY_SHOP.get(), id, player, entityId);
		if(! player.level.isClientSide) {
			sendMysteryGoodsPacket(player, -1);
		}
	}

	@Override
	public void buyGood(AbstractDaveEntity.GoodType good) {
		super.buyGood(good);
		PlayerUtil.addResource(player, Resources.GEM_NUM, - good.getGoodPrice());
	}

//	public void buyGood(DaveGoods good, int type) {
//		if(good.toString().toLowerCase().startsWith("enjoy_card")) {
//			int pos = good.toString().toLowerCase().charAt(good.toString().length() - 1) - '0';
//			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
//				l.getPlayerData().getOtherStats().mysteryGoods[pos] = - 1;
//				sendMysteryGoodsPacket(player, pos);
//			});
//			PlayerUtil.addResource(player, Resources.GEM_NUM, - TradeUtil.getGoodCost(good.setType(type)));
//			this.output.setItem(0, TradeUtil.getGoodItemStack(good.setType(type)));
//		}
//		this.player.level.playSound(null, this.player, SoundRegister.DAVE_HAPPY.get(), SoundCategory.AMBIENT, 1f, 1f);
//	}
	
//	public static void genNextGoods(PlayerEntity player) {
//		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
//			List<Integer> array = new ArrayList<>();
//			int sum = 0;
//			for(Plants plant : Plants.values()) {
//				sum += (Ranks.values().length - PlantUtil.getPlantRankByName(plant).ordinal() / 2 + 1);
//				array.add(sum);
//			}
//			for(int i = 0; i < l.getPlayerData().getOtherStats().mysteryGoods.length; ++ i) {
//				int now = player.maxLevel.random.nextInt(sum);
//				for(int j = 0; j < array.size(); ++ j) {
//					if(now < array.get(j)) {
//						l.getPlayerData().getOtherStats().mysteryGoods[i] = j;
//						break;
//					}
//				}
//			}
//			l.getPlayerData().getOtherStats().updateGoodTick = 24000;
//		});
//	}
	
	public static void sendMysteryGoodsPacket(PlayerEntity player, int pos) {
//		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
//			if(pos == - 1) {
//				for(int i = 0; i < MAX_MYSTERY_GOOD; ++ i) {
//			        PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
//				        return (ServerPlayerEntity) player;
//			        }), new OtherStatsPacket(0, i, l.getPlayerData().getOtherStats().mysteryGoods[i]));
//				}
//				PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
//			        return (ServerPlayerEntity) player;
//		        }), new OtherStatsPacket(0, - 1, l.getPlayerData().getOtherStats().updateGoodTick));
//			} else {
//				PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
//			        return (ServerPlayerEntity) player;
//		        }), new OtherStatsPacket(0, pos, l.getPlayerData().getOtherStats().mysteryGoods[pos]));
//			}
//		});
	}
}
