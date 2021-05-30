package com.hungteen.pvz.common.container.shop;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.network.OtherStatsPacket;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.network.PacketDistributor;

public class MysteryShopContainer extends AbstractDaveShopContainer {

	public static final int MAX_MYSTERY_GOOD = 8;
	
	public MysteryShopContainer(int id, PlayerEntity player) {
		super(ContainerRegister.MYSTERY_SHOP.get(), id, player);
		if(! player.level.isClientSide) {
			sendMysteryGoodsPacket(player, -1);
		}
	}
	
	public void buyGood(DaveGoods good, int type) {
		if(good.toString().toLowerCase().startsWith("enjoy_card")) {
			int pos = good.toString().toLowerCase().charAt(good.toString().length() - 1) - '0';
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				l.getPlayerData().getOtherStats().mysteryGoods[pos] = - 1;
				sendMysteryGoodsPacket(player, pos);
			});
			PlayerUtil.addPlayerStats(player, Resources.GEM_NUM, - TradeUtil.getGoodCost(good.setType(type)));
			this.output.setItem(0, TradeUtil.getGoodItemStack(good.setType(type)));
		}
		this.player.level.playSound(null, this.player, SoundRegister.DAVE_BUY.get(), SoundCategory.AMBIENT, 1f, 1f);
	}
	
	public static void genNextGoods(PlayerEntity player) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			List<Integer> array = new ArrayList<>();
			int sum = 0;
			for(Plants plant : Plants.values()) {
				sum += (Ranks.values().length - PlantUtil.getPlantRankByName(plant).ordinal() / 2 + 1);
				array.add(sum);
			}
			for(int i = 0; i < l.getPlayerData().getOtherStats().mysteryGoods.length; ++ i) {
				int now = player.level.random.nextInt(sum);
				for(int j = 0; j < array.size(); ++ j) {
					if(now < array.get(j)) {
						l.getPlayerData().getOtherStats().mysteryGoods[i] = j;
						break;
					}
				}
			}
			l.getPlayerData().getOtherStats().updateGoodTick = 24000;
		});
	}
	
	public static void sendMysteryGoodsPacket(PlayerEntity player, int pos) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			if(pos == - 1) {
				for(int i = 0; i < MAX_MYSTERY_GOOD; ++ i) {
			        PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
				        return (ServerPlayerEntity) player;
			        }), new OtherStatsPacket(0, i, l.getPlayerData().getOtherStats().mysteryGoods[i]));
				}
				PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
			        return (ServerPlayerEntity) player;
		        }), new OtherStatsPacket(0, - 1, l.getPlayerData().getOtherStats().updateGoodTick));
			} else {
				PVZPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> {
			        return (ServerPlayerEntity) player;
		        }), new OtherStatsPacket(0, pos, l.getPlayerData().getOtherStats().mysteryGoods[pos]));
			}
		});
	}

}
