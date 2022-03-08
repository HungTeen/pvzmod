package com.hungteen.pvz.common.impl.challenge.reward;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.interfaces.IChallenge;
import com.hungteen.pvz.api.raid.IRewardComponent;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.utils.PlayerUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;
import java.util.List;

public class UnLockRewardComponent implements IRewardComponent {

    public static final String NAME = "unlocks";
    private final List<IPAZType> list = new ArrayList<>();

    @Override
    public void reward(ServerPlayer player) {
        this.list.forEach(type -> {
            PlayerUtil.setPAZLock(player, type, false);
            PlayerUtil.sendMsgTo(player, new TranslatableComponent("challenge.pvz.unlock", type.getText().getString()).withStyle(ChatFormatting.GREEN));
        });
    }

    @Override
    public void rewardGlobally(IChallenge challenge) {
    }

    @Override
    public void readJson(JsonElement json) {
        if (json.isJsonArray()) {
            final JsonArray array = json.getAsJsonArray();
            array.forEach(e -> {
                if(e.isJsonPrimitive()){
                    final String string = e.getAsString();
                    PVZAPI.get().getTypeByID(string).ifPresent(type -> {
                        list.add(type);
                    });
                }
            });
        }
    }

}
