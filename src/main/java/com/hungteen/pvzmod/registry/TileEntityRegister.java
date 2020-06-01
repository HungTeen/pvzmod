package com.hungteen.pvzmod.registry;

import com.hungteen.pvzmod.blocks.tileentities.TileEntityCardTable;
import com.hungteen.pvzmod.blocks.tileentities.TileEntityJuicer;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegister {

	public TileEntityRegister(FMLPreInitializationEvent event)
    {
        registerTileEntity(TileEntityJuicer.class, "pvz_juicer");
        registerTileEntity(TileEntityCardTable.class, "pvz_card_table");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, Reference.MODID + ":" + id);
    }
}
