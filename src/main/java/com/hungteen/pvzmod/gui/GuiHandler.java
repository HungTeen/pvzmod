package com.hungteen.pvzmod.gui;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.blocks.tileentities.TileEntityJuicer;
import com.hungteen.pvzmod.entities.npcs.EntityTrader;
import com.hungteen.pvzmod.entities.npcs.EntityTreeMan;
import com.hungteen.pvzmod.gui.container.ContainerCardTable;
import com.hungteen.pvzmod.gui.container.ContainerJuicer;
import com.hungteen.pvzmod.gui.container.ContainerPanneyShop;
import com.hungteen.pvzmod.gui.container.ContainerPeaGun;
import com.hungteen.pvzmod.gui.container.ContainerPlantBase;
import com.hungteen.pvzmod.gui.container.CrazyDaveContainer;
import com.hungteen.pvzmod.gui.guicontainer.CrazyDaveGuiContainer;
import com.hungteen.pvzmod.gui.guicontainer.GuiCardTable;
import com.hungteen.pvzmod.gui.guicontainer.GuiJuicer;
import com.hungteen.pvzmod.gui.guicontainer.GuiPanneyShop;
import com.hungteen.pvzmod.gui.guicontainer.GuiPeaGun;
import com.hungteen.pvzmod.gui.guicontainer.GuiTrade;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiCabbagePult;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiDoubleShooter;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiGatlingPea;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiKernelPult;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiMelonPult;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiPeaShooter;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiSplitPea;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.common.GuiThreePeater;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.defence.GuiFlowerPot;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.defence.GuiLilyPad;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.defence.GuiNutWall;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.defence.GuiPumpkin;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.defence.GuiTallNut;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.electricity.GuiLightningRod;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.explosion.GuiCherryBomb;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.explosion.GuiPotatoMine;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.fight.GuiSpikeRock;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.fight.GuiSpikeWeed;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.fight.GuiSquash;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.fight.GuiTangleKelp;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.flame.GuiJalapeno;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.flame.GuiTorchWood;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.ice.GuiIceShroom;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.ice.GuiIcebergLettuce;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.ice.GuiSnowPea;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.ice.GuiWinterMelon;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.light.GuiGoldLeaf;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.light.GuiSunFlower;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.light.GuiTwinSunFlower;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.magic.GuiCatTail;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.magic.GuiCoffeeBean;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.magic.GuiHypnoShroom;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.magic.GuiMariGold;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.magic.GuiStrangeCat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler{

	public static final int TRADE=1;
	public static final int CRAZY_DAVE_TRADE=2;
	public static final int JUICER = 3;
	public static final int PEA_SHOOTER_CARD = 4;
	public static final int SUN_FLOWER_CARD = 5;
	public static final int CHERRY_BOMB_CARD = 6;
	public static final int NUT_WALL_CARD = 7;
	public static final int POTATO_MINE_CARD = 8;
	public static final int SNOW_PEA_CARD = 9;
	public static final int DOUBLE_SHOOTER_CARD = 10;
	public static final int HYPNO_SHROOM_CARD = 11;
	public static final int ICE_SHROOM_CARD = 12;
	public static final int LILY_PAD_CARD = 13;
	public static final int SQUASH_CARD = 14;
	public static final int THREE_PEATER_CARD = 15;
	public static final int TANGLE_KELP_CARD = 16;
	public static final int JALAPENO_CARD = 17;
	public static final int SPIKE_WEED_CARD = 18;
	public static final int TORCH_WOOD_CARD = 19;
	public static final int TALL_NUT_CARD =20;
	public static final int SPLIT_PEA_CARD = 21;
	public static final int PUMPKIN_CARD = 22;
	public static final int CABBAGE_PULT_CARD = 23;
	public static final int FLOWER_POT_CARD = 24;
	public static final int KERNEL_PULT_CARD = 25;
	public static final int COFFEE_BEAN_CARD = 26;
	public static final int MARIGOLD_CARD = 27;
	public static final int MELON_PULT_CARD = 28;
	public static final int GATLING_PEA_CARD = 29;
	public static final int TWIN_SUNFLOWER_CARD = 30;
	public static final int CAT_TAIL_CARD = 31;
	public static final int WINTER_MELON_CARD = 32;
	public static final int SPIKE_ROCK_CARD = 33;
	public static final int ICEBERG_LETTUCE_CARD = 34;
	public static final int GOLD_LEAF_CARD = 35;
	public static final int LIGHTNING_ROD_CARD = 36;
	public static final int STRANGE_CAT_CARD = 37;
	public static final int CARD_TABLE = 38;
	public static final int PEA_GUN = 39;
	public static final int PANNEY_SHOP = 40;
	
	public GuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID)
        {
        case TRADE: return getTraderContainer(player, EntityTreeMan.class, x);
        case CRAZY_DAVE_TRADE: return new CrazyDaveContainer();
        case JUICER: return new ContainerJuicer(player.inventory, (TileEntityJuicer) world.getTileEntity(new BlockPos(x, y, z)));
        case PEA_SHOOTER_CARD:
        case SUN_FLOWER_CARD:
        case CHERRY_BOMB_CARD:
        case NUT_WALL_CARD:
        case POTATO_MINE_CARD:
        case SNOW_PEA_CARD: 
        case DOUBLE_SHOOTER_CARD:
        case HYPNO_SHROOM_CARD:
        case ICE_SHROOM_CARD:
        case LILY_PAD_CARD:
        case SQUASH_CARD:
        case THREE_PEATER_CARD:
        case TANGLE_KELP_CARD:
        case JALAPENO_CARD:
        case SPIKE_WEED_CARD:
        case TORCH_WOOD_CARD:
        case TALL_NUT_CARD:
        case SPLIT_PEA_CARD:
        case PUMPKIN_CARD:
        case CABBAGE_PULT_CARD:
        case FLOWER_POT_CARD:
        case KERNEL_PULT_CARD:
        case COFFEE_BEAN_CARD:
        case MARIGOLD_CARD:
        case MELON_PULT_CARD:
        case GATLING_PEA_CARD: 
        case TWIN_SUNFLOWER_CARD:
        case CAT_TAIL_CARD:
        case WINTER_MELON_CARD:
        case SPIKE_ROCK_CARD:
        case ICEBERG_LETTUCE_CARD:
        case GOLD_LEAF_CARD:
        case LIGHTNING_ROD_CARD:
        case STRANGE_CAT_CARD:return new ContainerPlantBase();
        case CARD_TABLE: return new ContainerCardTable(world,player.inventory,new BlockPos(x, y, z));
        case PEA_GUN: return new ContainerPeaGun(player, player.getHeldItem(EnumHand.OFF_HAND));
        case PANNEY_SHOP: return new ContainerPanneyShop(world, player);
        default:return null;
        }
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID)
        {
        case TRADE:return getTraderGui(player,EntityTreeMan.class,x);
        case CRAZY_DAVE_TRADE: return new CrazyDaveGuiContainer(new CrazyDaveContainer());
        case JUICER: return new GuiJuicer(player.inventory,(TileEntityJuicer) world.getTileEntity(new BlockPos(x, y, z)));
        case PEA_SHOOTER_CARD: return new GuiPeaShooter(new ContainerPlantBase());
        case SUN_FLOWER_CARD: return new GuiSunFlower(new ContainerPlantBase());
        case CHERRY_BOMB_CARD: return new GuiCherryBomb(new ContainerPlantBase());
        case NUT_WALL_CARD: return new GuiNutWall(new ContainerPlantBase());
        case POTATO_MINE_CARD: return new GuiPotatoMine(new ContainerPlantBase());
        case SNOW_PEA_CARD: return new GuiSnowPea(new ContainerPlantBase());
        case DOUBLE_SHOOTER_CARD: return new GuiDoubleShooter(new ContainerPlantBase());
        case HYPNO_SHROOM_CARD: return new GuiHypnoShroom(new ContainerPlantBase());
        case ICE_SHROOM_CARD:return new GuiIceShroom(new ContainerPlantBase());
        case LILY_PAD_CARD:return new GuiLilyPad(new ContainerPlantBase());
        case SQUASH_CARD:return new GuiSquash(new ContainerPlantBase());
        case THREE_PEATER_CARD: return new GuiThreePeater(new ContainerPlantBase());
        case TANGLE_KELP_CARD: return new GuiTangleKelp(new ContainerPlantBase());
        case JALAPENO_CARD: return new GuiJalapeno(new ContainerPlantBase());
        case SPIKE_WEED_CARD: return new GuiSpikeWeed(new ContainerPlantBase());
        case TORCH_WOOD_CARD: return new GuiTorchWood(new ContainerPlantBase());
        case TALL_NUT_CARD: return new GuiTallNut(new ContainerPlantBase());
        case SPLIT_PEA_CARD:return new GuiSplitPea(new ContainerPlantBase());
        case PUMPKIN_CARD:return new GuiPumpkin(new ContainerPlantBase());
        case CABBAGE_PULT_CARD:return new GuiCabbagePult(new ContainerPlantBase());
        case FLOWER_POT_CARD:return new GuiFlowerPot(new ContainerPlantBase());
        case KERNEL_PULT_CARD:return new GuiKernelPult(new ContainerPlantBase());
        case COFFEE_BEAN_CARD:return new GuiCoffeeBean(new ContainerPlantBase());
        case MARIGOLD_CARD:return new GuiMariGold(new ContainerPlantBase());
        case MELON_PULT_CARD:return new GuiMelonPult(new ContainerPlantBase());
        case GATLING_PEA_CARD:return new GuiGatlingPea(new ContainerPlantBase());
        case TWIN_SUNFLOWER_CARD:return new GuiTwinSunFlower(new ContainerPlantBase());
        case CAT_TAIL_CARD:return new GuiCatTail(new ContainerPlantBase());
        case WINTER_MELON_CARD:return new GuiWinterMelon(new ContainerPlantBase());
        case SPIKE_ROCK_CARD:return new GuiSpikeRock(new ContainerPlantBase());
        case ICEBERG_LETTUCE_CARD:return new GuiIcebergLettuce(new ContainerPlantBase());
        case GOLD_LEAF_CARD:return new GuiGoldLeaf(new ContainerPlantBase());
        case LIGHTNING_ROD_CARD:return new GuiLightningRod(new ContainerPlantBase());
        case STRANGE_CAT_CARD:return new GuiStrangeCat(new ContainerPlantBase());
        case CARD_TABLE: return new GuiCardTable(world,player.inventory,new BlockPos(x, y, z));
        case PEA_GUN: return new GuiPeaGun(new ContainerPeaGun(player, player.getHeldItem(EnumHand.OFF_HAND)));
        case PANNEY_SHOP: return new GuiPanneyShop(new ContainerPanneyShop(world, player));
        default:return null;
        }
	}
	
	private ContainerMerchant getTraderContainer(EntityPlayer pl, Class<? extends EntityTrader> traderClass, int entityId) {
		EntityTrader trader = getNearbyEntityGuiTarget(pl, traderClass, entityId);

		if (trader != null)
			return new ContainerMerchant(pl.inventory, trader, pl.world);

		return null;
	}

	@SideOnly(Side.CLIENT)
	private Object getTraderGui(EntityPlayer pl, Class<? extends EntityTrader> traderClass, int entityId) {
		EntityTrader trader = getNearbyEntityGuiTarget(pl, traderClass, entityId);

		if (trader != null)
			return new GuiTrade(new ContainerMerchant(pl.inventory, trader, pl.world), trader);

		return null;
	}

	@Nullable
	private <T extends Entity> T getNearbyEntityGuiTarget(EntityPlayer player, Class<? extends T> entityClass, int entityId) {
		for (T e : player.world.getEntitiesWithinAABB(entityClass, player.getEntityBoundingBox().grow(10))) {
			if (e.getEntityId() == entityId)
				return e;
		}

		return null;
	}
	
}
