/* com.hungteen.pvz.common.item.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class PVZSignItem extends StandingAndWallBlockItem {
    public PVZSignItem(Properties p_43126_, RegistryObject<Block> p_43127_, RegistryObject<Block> p_43128_) {
        super(p_43127_, p_43128_, p_43126_);
    }

    protected boolean updateCustomBlockEntityTag(BlockPos p_43130_, Level p_43131_, @Nullable Player p_43132_, ItemStack p_43133_, BlockState p_43134_) {
        boolean flag = super.updateCustomBlockEntityTag(p_43130_, p_43131_, p_43132_, p_43133_, p_43134_);
        if (!p_43131_.isClientSide && !flag && p_43132_ != null) {
            p_43132_.openTextEdit((SignBlockEntity)p_43131_.getBlockEntity(p_43130_));
        }

        return flag;
    }
}