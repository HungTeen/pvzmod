package com.hungteen.pvz.api.interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public interface IChallenge {

    BlockPos getCenter();

    ServerWorld getWorld();
}
