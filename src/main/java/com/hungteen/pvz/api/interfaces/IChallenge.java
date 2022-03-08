package com.hungteen.pvz.api.interfaces;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;

public interface IChallenge {

    Mth getCenter();

    ServerLevel getWorld();
}
