package com.hungteen.pvz.client;

import com.hungteen.pvz.client.gui.screen.EssenceAltarScreen;
import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.creature.GrassCarpModel;
import com.hungteen.pvz.client.model.entity.bullet.CommonBulletModel;
import com.hungteen.pvz.client.model.entity.component.SurroundDirtModel;
import com.hungteen.pvz.client.model.entity.creature.SproutModel;
import com.hungteen.pvz.client.model.entity.misc.DropEntityModel;
import com.hungteen.pvz.client.model.entity.plant.PeaShooterModel;
import com.hungteen.pvz.client.model.entity.plant.PotatoMineModel;
import com.hungteen.pvz.client.model.entity.plant.SnowPeaModel;
import com.hungteen.pvz.client.model.entity.plant.SunFlowerModel;
import com.hungteen.pvz.client.model.entity.plant.WallNutModel;
import com.hungteen.pvz.client.model.entity.zombie.HumanoidZombieModel;
import com.hungteen.pvz.client.model.item.BucketArmorModel;
import com.hungteen.pvz.client.particle.MelonSliceParticle;
import com.hungteen.pvz.client.particle.PVZParticles;
import com.hungteen.pvz.client.particle.multi.PotatoExplosionParticle;
import com.hungteen.pvz.client.render.blockentity.EssenceAltarRender;
import com.hungteen.pvz.client.render.entity.creature.GardenPlantRender;
import com.hungteen.pvz.client.render.entity.creature.GrassCarpRender;
import com.hungteen.pvz.client.render.entity.bullet.PeaBulletRender;
import com.hungteen.pvz.client.render.entity.drop.DropItemRender;
import com.hungteen.pvz.client.render.entity.drop.OriginOrbRender;
import com.hungteen.pvz.client.render.entity.drop.PlantFoodRender;
import com.hungteen.pvz.client.render.entity.drop.SunRender;
import com.hungteen.pvz.client.render.entity.effect.OriginEffectRender;
import com.hungteen.pvz.client.render.entity.plant.PeaShooterRender;
import com.hungteen.pvz.client.render.entity.plant.PotatoMineRender;
import com.hungteen.pvz.client.render.entity.plant.SnowPeaRender;
import com.hungteen.pvz.client.render.entity.plant.SunFlowerRender;
import com.hungteen.pvz.client.render.entity.plant.WallNutRender;
import com.hungteen.pvz.client.render.entity.zombie.NormalZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.ZombieDropPartRender;
import com.hungteen.pvz.common.blockentity.PVZBlockEntities;
import com.hungteen.pvz.common.entity.PVZEntities;

import com.hungteen.pvz.common.menu.PVZMenus;
import com.hungteen.pvz.common.misc.PVZWoodType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 09:52
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegister {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        /* drop entity */
        event.registerEntityRenderer(PVZEntities.SUN.get(), SunRender::new);
        event.registerEntityRenderer(PVZEntities.COPPER_COIN.get(), DropItemRender::new);
        event.registerEntityRenderer(PVZEntities.SILVER_COIN.get(), DropItemRender::new);
        event.registerEntityRenderer(PVZEntities.GOLD_COIN.get(), DropItemRender::new);
        event.registerEntityRenderer(PVZEntities.JEWEL.get(), DropItemRender::new);
        event.registerEntityRenderer(PVZEntities.PLANT_FOOD.get(), PlantFoodRender::new);
        event.registerEntityRenderer(PVZEntities.ORIGIN_ORB.get(), OriginOrbRender::new);

        /* misc entity */
        event.registerEntityRenderer(PVZEntities.ORIGIN_EFFECT.get(), OriginEffectRender::new);
        event.registerEntityRenderer(PVZEntities.ZOMBIE_DROP_PART.get(), ZombieDropPartRender::new);

        /* bullet */
        event.registerEntityRenderer(PVZEntities.PEA_BULLET.get(), PeaBulletRender::new);

        /* creature */
        event.registerEntityRenderer(PVZEntities.GRASS_CARP.get(), GrassCarpRender::new);
        event.registerEntityRenderer(PVZEntities.COMMON_GARDEN_PLANT.get(), GardenPlantRender::new);

        /* plant entity */
        event.registerEntityRenderer(PVZEntities.PEA_SHOOTER.get(), PeaShooterRender::new);
        event.registerEntityRenderer(PVZEntities.SUN_FLOWER.get(), SunFlowerRender::new);
        event.registerEntityRenderer(PVZEntities.WALL_NUT.get(), WallNutRender::new);
        event.registerEntityRenderer(PVZEntities.POTATO_MINE.get(), PotatoMineRender::new);
        event.registerEntityRenderer(PVZEntities.SNOW_PEA.get(), SnowPeaRender::new);

        /* zombie entity */
        event.registerEntityRenderer(PVZEntities.NORMAL_ZOMBIE.get(), NormalZombieRender::new);
        event.registerEntityRenderer(PVZEntities.BUCKET_HEAD_ZOMBIE.get(), NormalZombieRender::new);

        event.registerBlockEntityRenderer(PVZBlockEntities.ESSENCE_ALTAR.get(), EssenceAltarRender::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LayerDefinition INNER_ARMOR = LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F), 64, 32);
        LayerDefinition OUTER_ARMOR = LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32);

        /* common */
        event.registerLayerDefinition(PVZModelLayers.BUCKET_INNER_ARMOR, () -> BucketArmorModel.createBodyLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION));
        event.registerLayerDefinition(PVZModelLayers.BUCKET_OUTER_ARMOR, () -> BucketArmorModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION));

        /* drop entity */
        event.registerLayerDefinition(PVZModelLayers.SUN, DropEntityModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.PLANT_FOOD, DropEntityModel::createBodyLayer);
        /* misc entity */

        /* bullet */
        event.registerLayerDefinition(PVZModelLayers.PEA_BULLET, CommonBulletModel::createBodyLayer);

        /* creature */
        event.registerLayerDefinition(PVZModelLayers.GRASS_CARP, GrassCarpModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.SPROUT, SproutModel::createBodyLayer);

        /* plant entity */
        event.registerLayerDefinition(PVZModelLayers.PEA_SHOOTER, PeaShooterModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.SUN_FLOWER, SunFlowerModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.WALL_NUT, WallNutModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.WALL_NUT_ARMOR, WallNutModel.WallNutArmorModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.POTATO_MINE, PotatoMineModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.SURROUND_DIRT, SurroundDirtModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.SNOW_PEA, SnowPeaModel::createBodyLayer);

        /* zombie entity */
        event.registerLayerDefinition(PVZModelLayers.NORMAL_ZOMBIE, HumanoidZombieModel::createBodyLayer);
        event.registerLayerDefinition(PVZModelLayers.NORMAL_ZOMBIE_INNER_ARMOR, () -> INNER_ARMOR);
        event.registerLayerDefinition(PVZModelLayers.NORMAL_ZOMBIE_OUTER_ARMOR, () -> OUTER_ARMOR);

    }

    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
        ParticleEngine manager = Minecraft.getInstance().particleEngine;
        manager.register(PVZParticles.POTATO_EXPLOSION.get(), (sprite) -> new PotatoExplosionParticle.Provider(sprite));

//        manager.register(ParticleRegister.RED_BOMB.get(), (sprite) -> {return new CherryBombParticle.Factory(sprite);});
//        manager.register(ParticleRegister.YELLOW_BOMB.get(), (sprite) -> {return new PotatoMineParticle.Factory(sprite);});
//        manager.register(ParticleRegister.DIRT_BURST_OUT.get(), (sprite) -> {return new DirtBurstOutParticle.Factory(sprite);});
//        manager.register(ParticleRegister.YELLOW_FLAME.get(), (sprite) -> {return new YellowFlameParticle.Factory(sprite);});
//        manager.register(ParticleRegister.BLUE_FLAME.get(), (sprite) -> {return new BlueFlameParticle.Factory(sprite);});
//        manager.register(ParticleRegister.SLEEP.get(), (sprite) -> {return new SleepParticle.Factory(sprite);});
//        manager.register(ParticleRegister.SPORE.get(), (sprite) -> {return new SporeParticle.Factory(sprite);});
//        manager.register(ParticleRegister.FUME.get(), (sprite) -> {return new FumeParticle.Factory(sprite);});
//        manager.register(ParticleRegister.SNOW_FLOWER.get(), (sprite) -> {return new SnowFlowerParticle.Factory(sprite);});
//        manager.register(ParticleRegister.DOOM.get(), (sprite) -> {return new DoomParticle.Factory(sprite);});
        manager.register(PVZParticles.MELON_SLICE.get(), (sprite) -> {return new MelonSliceParticle.Factory(sprite);});
//        manager.register(ParticleRegister.FROZEN_MELON_SLICE.get(), (sprite) -> {return new FrozenMelonSliceParticle.Factory(sprite);});
//        manager.register(ParticleRegister.GREEN_SWEEP.get(), (sprite) -> {return new GreenSweepParticle.Factory(sprite);});
//        manager.register(ParticleRegister.POP_CORN.get(), (sprite) -> {return new PopCornParticle.Factory(sprite);});

    }

    @SubscribeEvent
    public static void setUpClient(FMLClientSetupEvent ev){
        ev.enqueueWork(() -> {
            PVZKeyBinds.register();
            PVZWoodType.register();
            registerScreen();
        });
//        RenderTypeLookup.setRenderLayer(BlockRegister.PEA_PLANT.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.NUT_LEAVES.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.NUT_SAPLING.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.TOXIC_SHROOM.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.LANTERN.get(), RenderType.translucent());
//        RenderTypeLookup.setRenderLayer(BlockRegister.FLOWER_POT.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.LILY_PAD.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.CABBAGE.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.BUTTER_BLOCK.get(), RenderType.translucent());
//        RenderTypeLookup.setRenderLayer(BlockRegister.CORN.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.ESSENCE_ALTAR.get(), RenderType.translucent());
//        RenderTypeLookup.setRenderLayer(BlockRegister.STEEL_LADDER.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.SILVER_SUNFLOWER_TROPHY.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.GOLD_SUNFLOWER_TROPHY.get(), RenderType.cutout());
//        RenderTypeLookup.setRenderLayer(BlockRegister.DIAMOND_SUNFLOWER_TROPHY.get(), RenderType.cutout());
//        TileEntityRegister.bindRenderers(ev);
    }

    public static void registerScreen() {
//        ScreenManager.register(ALMANAC.get(), AlmanacScreen::new);
//        ScreenManager.register(PEA_GUN.get(), PeaGunScreen::new);
//        ScreenManager.register(DAVE_SHOP.get(), DaveShopScreen::new);
//        ScreenManager.register(SUN_SHOP.get(), SunShopScreen::new);
//        ScreenManager.register(SUN_CONVERTER.get(), SunConverterScreen::new);
//        ScreenManager.register(FRAGMENT_SPLICE.get(), FragmentSpliceScreen::new);
//        ScreenManager.register(SLOT_MACHINE.get(), SlotMachineScreen::new);
//        ScreenManager.register(PENNY_SHOP.get(), PennyShopScreen::new);
        MenuScreens.register(PVZMenus.ESSENCE_ALTAR.get(), EssenceAltarScreen::new);
//        ScreenManager.register(CARD_FUSION.get(), CardFusionScreen::new);
//        ScreenManager.register(IMITATER.get(), ImitaterScreen::new);
//        ScreenManager.register(CARD_PACK.get(), CardPackScreen::new);
    }

//    private static Pair<IBakedModel, ModelResourceLocation> getBakedModel(ModelBakeEvent ev, Item item) {
//        Map<ResourceLocation, IBakedModel> modelRegistry = ev.getModelRegistry();
//        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
//        IBakedModel model = modelRegistry.get(location);
//        if(model == null) throw new RuntimeException("Did not find Obsidian Hidden in registry");
//        else if(model instanceof PVZBakedModel) throw new RuntimeException("Tried to replaceObsidian Hidden twice");
//        return Pair.of(model, location);
//    }

}
