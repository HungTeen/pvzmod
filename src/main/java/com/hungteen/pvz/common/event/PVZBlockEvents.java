package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.BlockUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-05-07 17:52
 **/
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZBlockEvents {

    @SubscribeEvent
    public static void onToolModifyBlock(BlockEvent.BlockToolModificationEvent event){
        if(event.getToolAction() == ToolActions.AXE_STRIP){
            if(! event.isSimulated() && BlockUtil.STRIPPABLES.containsKey(event.getFinalState().getBlock())){
                final Block newBlock = BlockUtil.STRIPPABLES.get(event.getFinalState().getBlock());
                final BlockState newState = newBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, event.getFinalState().getValue(RotatedPillarBlock.AXIS));
                event.setFinalState(newState);
            }
        }
    }

}
