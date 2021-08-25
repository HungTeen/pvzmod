package com.hungteen.pvz.register;

import java.util.Arrays;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.render.entity.bullet.BallRender;
import com.hungteen.pvz.client.render.entity.bullet.ButterRender;
import com.hungteen.pvz.client.render.entity.bullet.CabbageRender;
import com.hungteen.pvz.client.render.entity.bullet.CornRender;
import com.hungteen.pvz.client.render.entity.bullet.FireCrackerRender;
import com.hungteen.pvz.client.render.entity.bullet.FumeRender;
import com.hungteen.pvz.client.render.entity.bullet.KernelRender;
import com.hungteen.pvz.client.render.entity.bullet.MelonRender;
import com.hungteen.pvz.client.render.entity.bullet.MetalItemRender;
import com.hungteen.pvz.client.render.entity.bullet.PeaRender;
import com.hungteen.pvz.client.render.entity.bullet.PotatoRender;
import com.hungteen.pvz.client.render.entity.bullet.SporeRender;
import com.hungteen.pvz.client.render.entity.bullet.StarRender;
import com.hungteen.pvz.client.render.entity.bullet.TargetArrowRender;
import com.hungteen.pvz.client.render.entity.bullet.ThornRender;
import com.hungteen.pvz.client.render.entity.creature.FoodieZombieRender;
import com.hungteen.pvz.client.render.entity.drop.CoinRender;
import com.hungteen.pvz.client.render.entity.drop.EnergyRender;
import com.hungteen.pvz.client.render.entity.drop.JewelRender;
import com.hungteen.pvz.client.render.entity.drop.SunRender;
import com.hungteen.pvz.client.render.entity.misc.BobsleCarRender;
import com.hungteen.pvz.client.render.entity.misc.DestroyCarRender;
import com.hungteen.pvz.client.render.entity.misc.DoomFixerRender;
import com.hungteen.pvz.client.render.entity.misc.ElementBallRender;
import com.hungteen.pvz.client.render.entity.misc.EmptyRender;
import com.hungteen.pvz.client.render.entity.misc.FireCrackersRender;
import com.hungteen.pvz.client.render.entity.misc.GardenRakeRender;
import com.hungteen.pvz.client.render.entity.misc.LawnMowerRender;
import com.hungteen.pvz.client.render.entity.misc.SmallChomperRender;
import com.hungteen.pvz.client.render.entity.misc.ZombieHandRender;
import com.hungteen.pvz.client.render.entity.misc.bowling.ExplosionBowlingRender;
import com.hungteen.pvz.client.render.entity.misc.bowling.GiantNutBowlingRender;
import com.hungteen.pvz.client.render.entity.misc.bowling.WallNutBowlingRender;
import com.hungteen.pvz.client.render.entity.npc.CrazyDaveRender;
import com.hungteen.pvz.client.render.entity.npc.PennyRender;
import com.hungteen.pvz.client.render.entity.npc.SunDaveRender;
import com.hungteen.pvz.client.render.entity.plant.appease.AngelStarFruitRender;
import com.hungteen.pvz.client.render.entity.plant.appease.GatlingPeaRender;
import com.hungteen.pvz.client.render.entity.plant.appease.PeaShooterRender;
import com.hungteen.pvz.client.render.entity.plant.appease.RepeaterRender;
import com.hungteen.pvz.client.render.entity.plant.appease.SplitPeaRender;
import com.hungteen.pvz.client.render.entity.plant.appease.StarFruitRender;
import com.hungteen.pvz.client.render.entity.plant.appease.ThreePeaterRender;
import com.hungteen.pvz.client.render.entity.plant.arma.ButterPultRender;
import com.hungteen.pvz.client.render.entity.plant.arma.CabbagePultRender;
import com.hungteen.pvz.client.render.entity.plant.arma.KernelPultRender;
import com.hungteen.pvz.client.render.entity.plant.arma.MelonPultRender;
import com.hungteen.pvz.client.render.entity.plant.assist.BloverRender;
import com.hungteen.pvz.client.render.entity.plant.assist.FlowerPotRender;
import com.hungteen.pvz.client.render.entity.plant.assist.GoldMagnetRender;
import com.hungteen.pvz.client.render.entity.plant.assist.GraveBusterRender;
import com.hungteen.pvz.client.render.entity.plant.assist.LilyPadRender;
import com.hungteen.pvz.client.render.entity.plant.assist.MagnetShroomRender;
import com.hungteen.pvz.client.render.entity.plant.defence.GarlicRender;
import com.hungteen.pvz.client.render.entity.plant.defence.GiantWallNutRender;
import com.hungteen.pvz.client.render.entity.plant.defence.PumpkinRender;
import com.hungteen.pvz.client.render.entity.plant.defence.TallNutRender;
import com.hungteen.pvz.client.render.entity.plant.defence.WallNutRender;
import com.hungteen.pvz.client.render.entity.plant.defence.WaterGuardRender;
import com.hungteen.pvz.client.render.entity.plant.enforce.BonkChoyRender;
import com.hungteen.pvz.client.render.entity.plant.enforce.ChomperRender;
import com.hungteen.pvz.client.render.entity.plant.enforce.SquashRender;
import com.hungteen.pvz.client.render.entity.plant.enforce.TangleKelpRender;
import com.hungteen.pvz.client.render.entity.plant.enforce.UmbrellaLeafRender;
import com.hungteen.pvz.client.render.entity.plant.explosion.BambooLordRender;
import com.hungteen.pvz.client.render.entity.plant.explosion.CherryBombRender;
import com.hungteen.pvz.client.render.entity.plant.explosion.CobCannonRender;
import com.hungteen.pvz.client.render.entity.plant.explosion.DoomShroomRender;
import com.hungteen.pvz.client.render.entity.plant.explosion.ExplodeONutRender;
import com.hungteen.pvz.client.render.entity.plant.explosion.PotatoMineRender;
import com.hungteen.pvz.client.render.entity.plant.flame.JalapenoRender;
import com.hungteen.pvz.client.render.entity.plant.flame.TorchWoodRender;
import com.hungteen.pvz.client.render.entity.plant.ice.IceShroomRender;
import com.hungteen.pvz.client.render.entity.plant.ice.IcebergLettuceRender;
import com.hungteen.pvz.client.render.entity.plant.ice.SnowPeaRender;
import com.hungteen.pvz.client.render.entity.plant.ice.WinterMelonRender;
import com.hungteen.pvz.client.render.entity.plant.light.GoldLeafRender;
import com.hungteen.pvz.client.render.entity.plant.light.PlanternRender;
import com.hungteen.pvz.client.render.entity.plant.light.SunFlowerRender;
import com.hungteen.pvz.client.render.entity.plant.light.SunShroomRender;
import com.hungteen.pvz.client.render.entity.plant.light.TwinSunFlowerRender;
import com.hungteen.pvz.client.render.entity.plant.magic.CoffeeBeanRender;
import com.hungteen.pvz.client.render.entity.plant.magic.HypnoShroomRender;
import com.hungteen.pvz.client.render.entity.plant.magic.ImitaterRender;
import com.hungteen.pvz.client.render.entity.plant.magic.MariGoldRender;
import com.hungteen.pvz.client.render.entity.plant.magic.StrangeCatRender;
import com.hungteen.pvz.client.render.entity.plant.spear.CactusRender;
import com.hungteen.pvz.client.render.entity.plant.spear.CatTailRender;
import com.hungteen.pvz.client.render.entity.plant.spear.SpikeRockRender;
import com.hungteen.pvz.client.render.entity.plant.spear.SpikeWeedRender;
import com.hungteen.pvz.client.render.entity.plant.toxic.FumeShroomRender;
import com.hungteen.pvz.client.render.entity.plant.toxic.GloomShroomRender;
import com.hungteen.pvz.client.render.entity.plant.toxic.PuffShroomRender;
import com.hungteen.pvz.client.render.entity.plant.toxic.ScaredyShroomRender;
import com.hungteen.pvz.client.render.entity.plant.toxic.SeaShroomRender;
import com.hungteen.pvz.client.render.entity.zombie.body.ZombieBodyRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.BackupDancerRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.BucketHeadZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.ConeHeadZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.DancingZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.FlagZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.FootballZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.GigaFootballZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.GigaTombStoneRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.NewspaperZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.NormalZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.OldZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.PoleZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.ScreenDoorZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.SundayEditionZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.grass.TombStoneRender;
import com.hungteen.pvz.client.render.entity.zombie.other.CoffinRender;
import com.hungteen.pvz.client.render.entity.zombie.other.MournerZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.other.NobleZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.other.RaZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.other.TrickZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.BalloonZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.BobsleTeamRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.BobsleZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.DiggerZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.DolphinRiderRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.DolphinRiderZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.JackInBoxZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.LavaZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.PogoZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.SnorkelZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.YetiZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.ZombieDolphinRender;
import com.hungteen.pvz.client.render.entity.zombie.pool.ZomboniRender;
import com.hungteen.pvz.client.render.entity.zombie.roof.BungeeZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.roof.CatapultZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.roof.Edgar090505Render;
import com.hungteen.pvz.client.render.entity.zombie.roof.GargantuarRender;
import com.hungteen.pvz.client.render.entity.zombie.roof.GigaGargantuarRender;
import com.hungteen.pvz.client.render.entity.zombie.roof.ImpRender;
import com.hungteen.pvz.client.render.entity.zombie.roof.LadderZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.zombotany.GatlingPeaZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.zombotany.JalapenoZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.zombotany.PeaShooterZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.zombotany.PumpkinZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.zombotany.SquashZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.zombotany.TallNutZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.zombotany.WallNutZombieRender;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.entity.bullet.BallEntity;
import com.hungteen.pvz.common.entity.bullet.ButterEntity;
import com.hungteen.pvz.common.entity.bullet.CornEntity;
import com.hungteen.pvz.common.entity.bullet.FumeEntity;
import com.hungteen.pvz.common.entity.bullet.KernelEntity;
import com.hungteen.pvz.common.entity.bullet.MelonEntity;
import com.hungteen.pvz.common.entity.bullet.StarEntity;
import com.hungteen.pvz.common.entity.bullet.TargetArrowEntity;
import com.hungteen.pvz.common.entity.bullet.ThornEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.CabbageEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.FireCrackerEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.MetalItemEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.PotatoEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.SporeEntity;
import com.hungteen.pvz.common.entity.creature.FoodieZombieEntity;
import com.hungteen.pvz.common.entity.drop.CoinEntity;
import com.hungteen.pvz.common.entity.drop.EnergyEntity;
import com.hungteen.pvz.common.entity.drop.JewelEntity;
import com.hungteen.pvz.common.entity.drop.SunEntity;
import com.hungteen.pvz.common.entity.misc.BobsleCarEntity;
import com.hungteen.pvz.common.entity.misc.DestroyCarEntity;
import com.hungteen.pvz.common.entity.misc.DoomFixerEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.misc.FireCrackersEntity;
import com.hungteen.pvz.common.entity.misc.GardenRakeEntity;
import com.hungteen.pvz.common.entity.misc.LawnMowerEntity;
import com.hungteen.pvz.common.entity.misc.SmallChomperEntity;
import com.hungteen.pvz.common.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.common.entity.misc.bowling.ExplosionBowlingEntity;
import com.hungteen.pvz.common.entity.misc.bowling.GiantNutBowlingEntity;
import com.hungteen.pvz.common.entity.misc.bowling.WallNutBowlingEntity;
import com.hungteen.pvz.common.entity.npc.CrazyDaveEntity;
import com.hungteen.pvz.common.entity.npc.PennyEntity;
import com.hungteen.pvz.common.entity.npc.SunDaveEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.appease.AngelStarFruitEntity;
import com.hungteen.pvz.common.entity.plant.appease.GatlingPeaEntity;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.common.entity.plant.appease.RepeaterEntity;
import com.hungteen.pvz.common.entity.plant.appease.SplitPeaEntity;
import com.hungteen.pvz.common.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.common.entity.plant.appease.ThreePeaterEntity;
import com.hungteen.pvz.common.entity.plant.arma.ButterPultEntity;
import com.hungteen.pvz.common.entity.plant.arma.CabbagePultEntity;
import com.hungteen.pvz.common.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.common.entity.plant.assist.BloverEntity;
import com.hungteen.pvz.common.entity.plant.assist.FlowerPotEntity;
import com.hungteen.pvz.common.entity.plant.assist.GoldMagnetEntity;
import com.hungteen.pvz.common.entity.plant.assist.GraveBusterEntity;
import com.hungteen.pvz.common.entity.plant.assist.LilyPadEntity;
import com.hungteen.pvz.common.entity.plant.assist.MagnetShroomEntity;
import com.hungteen.pvz.common.entity.plant.defence.GarlicEntity;
import com.hungteen.pvz.common.entity.plant.defence.GiantWallNutEntity;
import com.hungteen.pvz.common.entity.plant.defence.PumpkinEntity;
import com.hungteen.pvz.common.entity.plant.defence.TallNutEntity;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.common.entity.plant.defence.WaterGuardEntity;
import com.hungteen.pvz.common.entity.plant.enforce.BonkChoyEntity;
import com.hungteen.pvz.common.entity.plant.enforce.ChomperEntity;
import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.common.entity.plant.enforce.TangleKelpEntity;
import com.hungteen.pvz.common.entity.plant.enforce.UmbrellaLeafEntity;
import com.hungteen.pvz.common.entity.plant.explosion.BambooLordEntity;
import com.hungteen.pvz.common.entity.plant.explosion.CherryBombEntity;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.common.entity.plant.explosion.DoomShroomEntity;
import com.hungteen.pvz.common.entity.plant.explosion.ExplodeONutEntity;
import com.hungteen.pvz.common.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.common.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.common.entity.plant.ice.IceShroomEntity;
import com.hungteen.pvz.common.entity.plant.ice.IcebergLettuceEntity;
import com.hungteen.pvz.common.entity.plant.ice.SnowPeaEntity;
import com.hungteen.pvz.common.entity.plant.ice.WinterMelonEntity;
import com.hungteen.pvz.common.entity.plant.light.GoldLeafEntity;
import com.hungteen.pvz.common.entity.plant.light.PlanternEntity;
import com.hungteen.pvz.common.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.common.entity.plant.light.SunShroomEntity;
import com.hungteen.pvz.common.entity.plant.light.TwinSunFlowerEntity;
import com.hungteen.pvz.common.entity.plant.magic.CoffeeBeanEntity;
import com.hungteen.pvz.common.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.common.entity.plant.magic.ImitaterEntity;
import com.hungteen.pvz.common.entity.plant.magic.MariGoldEntity;
import com.hungteen.pvz.common.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.common.entity.plant.spear.CactusEntity;
import com.hungteen.pvz.common.entity.plant.spear.CatTailEntity;
import com.hungteen.pvz.common.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.common.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.common.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.common.entity.plant.toxic.GloomShroomEntity;
import com.hungteen.pvz.common.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.common.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.common.entity.plant.toxic.SeaShroomEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.custom.GigaTombStoneEntity;
import com.hungteen.pvz.common.entity.zombie.custom.LavaZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.BackupDancerEntity;
import com.hungteen.pvz.common.entity.zombie.grass.BucketHeadZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.ConeHeadZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.DancingZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.FlagZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.FootballZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.GigaFootballZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.NewspaperZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.NormalZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.OldZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.PoleZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.ScreenDoorZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.SundayEditionZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.TombStoneEntity;
import com.hungteen.pvz.common.entity.zombie.other.CoffinEntity;
import com.hungteen.pvz.common.entity.zombie.other.MournerZombieEntity;
import com.hungteen.pvz.common.entity.zombie.other.NobleZombieEntity;
import com.hungteen.pvz.common.entity.zombie.other.RaZombieEntity;
import com.hungteen.pvz.common.entity.zombie.other.TrickZombieEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BalloonZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BobsleTeamEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BobsleZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.DiggerZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.DolphinRiderEntity;
import com.hungteen.pvz.common.entity.zombie.pool.DolphinRiderZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.JackInBoxZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.PogoZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.SnorkelZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.YetiZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.ZombieDolphinEntity;
import com.hungteen.pvz.common.entity.zombie.pool.ZomboniEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.CatapultZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.Edgar090505Entity;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity;
import com.hungteen.pvz.common.entity.zombie.roof.GigaGargantuarEntity;
import com.hungteen.pvz.common.entity.zombie.roof.ImpEntity;
import com.hungteen.pvz.common.entity.zombie.roof.LadderZombieEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.GatlingPeaZombieEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.JalapenoZombieEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.PeaShooterZombieEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.PumpkinZombieEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.SquashZombieEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.TallNutZombieEntity;
import com.hungteen.pvz.common.entity.zombie.zombotany.WallNutZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =  DeferredRegister.create(ForgeRegistries.ENTITIES, PVZMod.MOD_ID);
    
	//entitytype
	//drop
	public static final RegistryObject<EntityType<SunEntity>> SUN = registerEntityType(SunEntity::new, "sun", EntityClassification.AMBIENT);
	public static final RegistryObject<EntityType<CoinEntity>> COIN = registerEntityType(CoinEntity::new, "coin", EntityClassification.MISC);
	public static final RegistryObject<EntityType<JewelEntity>> JEWEL = registerEntityType(JewelEntity::new, "jewel", EntityClassification.MISC);
	public static final RegistryObject<EntityType<EnergyEntity>> ENERGY = registerEntityType(EnergyEntity::new, "energy", EntityClassification.MISC, 0.9f, 2f);
	
	//bullet
	public static final RegistryObject<EntityType<PeaEntity>> PEA = registerEntityType(PeaEntity::new, "pea", EntityClassification.MISC);
	public static final RegistryObject<EntityType<PotatoEntity>> POTATO = registerEntityType(PotatoEntity::new, "potato", EntityClassification.MISC);
	public static final RegistryObject<EntityType<SporeEntity>> SPORE = registerEntityType(SporeEntity::new, "spore", EntityClassification.MISC);
	public static final RegistryObject<EntityType<FumeEntity>> FUME = registerEntityType(FumeEntity::new, "fume", EntityClassification.MISC);
	public static final RegistryObject<EntityType<MetalItemEntity>> METAL = registerEntityType(MetalItemEntity::new, "metal", EntityClassification.MISC);
	public static final RegistryObject<EntityType<ThornEntity>> THORN = registerEntityType(ThornEntity::new, "thorn", EntityClassification.MISC);
	public static final RegistryObject<EntityType<StarEntity>> STAR = registerEntityType(StarEntity::new, "star", EntityClassification.MISC);
//	public static final RegistryObject<EntityType<NutEntity>> NUT = registerEntityType(NutEntity::new, "nut", EntityClassification.MISC);
	public static final RegistryObject<EntityType<CabbageEntity>> CABBAGE = registerEntityType(CabbageEntity::new, "cabbage", EntityClassification.MISC);
	public static final RegistryObject<EntityType<KernelEntity>> KERNEL = registerEntityType(KernelEntity::new, "kernel", EntityClassification.MISC);
	public static final RegistryObject<EntityType<ButterEntity>> BUTTER = registerEntityType(ButterEntity::new, "butter", EntityClassification.MISC);
	public static final RegistryObject<EntityType<TargetArrowEntity>> TARGET_ARROW = registerEntityType(TargetArrowEntity::new, "target_arrow", EntityClassification.MISC);
	public static final RegistryObject<EntityType<MelonEntity>> MELON = registerEntityType(MelonEntity::new, "melon", EntityClassification.MISC);
	public static final RegistryObject<EntityType<FireCrackerEntity>> FIRE_CRACKER = registerEntityType(FireCrackerEntity::new, "fire_cracker", EntityClassification.MISC);
	public static final RegistryObject<EntityType<BallEntity>> BALL = registerEntityType(BallEntity::new, "ball", EntityClassification.MISC);
	public static final RegistryObject<EntityType<CornEntity>> CORN = registerEntityType(CornEntity::new, "corn", EntityClassification.MISC);
	
	//misc 
	public static final RegistryObject<EntityType<SmallChomperEntity>> SMALL_CHOMPER = registerEntityType(SmallChomperEntity::new, "small_chomper", EntityClassification.MISC);
	public static final RegistryObject<EntityType<BobsleCarEntity>> BOBSLE_CAR = registerEntityType(BobsleCarEntity::new, "bobsle_car", EntityClassification.MISC);
	public static final RegistryObject<EntityType<PVZZombiePartEntity>> ZOMBIE_PART = registerEntityType(PVZZombiePartEntity::new, "zombie_part", EntityClassification.MISC);
	public static final RegistryObject<EntityType<ZombieHandEntity>> ZOMBIE_HAND = registerEntityType(ZombieHandEntity::new, "zombie_hand", EntityClassification.MISC);
	public static final RegistryObject<EntityType<WallNutBowlingEntity>> WALL_NUT_BOWLING = registerEntityType(WallNutBowlingEntity::new, "wall_nut_bowling", EntityClassification.MISC);
	public static final RegistryObject<EntityType<ExplosionBowlingEntity>> EXPLOSION_BOWLING = registerEntityType(ExplosionBowlingEntity::new, "explosion_bowling", EntityClassification.MISC);
	public static final RegistryObject<EntityType<GiantNutBowlingEntity>> GIANT_NUT_BOWLING = registerEntityType(GiantNutBowlingEntity::new, "giant_nut_bowling", EntityClassification.MISC);
	public static final RegistryObject<EntityType<LawnMowerEntity>> LAWN_MOWER = registerEntityType(LawnMowerEntity::new, "lawn_mower", EntityClassification.MISC);
	public static final RegistryObject<EntityType<FireCrackersEntity>> FIRE_CRACKERS = registerEntityType(FireCrackersEntity::new, "fire_crackers", EntityClassification.MISC);
	public static final RegistryObject<EntityType<ElementBallEntity>> ELEMENT_BALL = registerEntityType(ElementBallEntity::new, "element_ball", EntityClassification.MISC);
	public static final RegistryObject<EntityType<DestroyCarEntity>> DESTROY_CAR = registerEntityType(DestroyCarEntity::new, "destroy_car", EntityClassification.MISC);
	public static final RegistryObject<EntityType<GardenRakeEntity>> GARDEN_RAKE = registerEntityType(GardenRakeEntity::new, "garden_rake", EntityClassification.MISC);
	public static final RegistryObject<EntityType<ZombieDropBodyEntity>> ZOMBIE_DROP_BODY = registerEntityType(ZombieDropBodyEntity::new, "zombie_drop_body", EntityClassification.MISC);
	public static final RegistryObject<EntityType<DoomFixerEntity>> DOOM_FIXER = registerEntityType(DoomFixerEntity::new, "doom_fixer", EntityClassification.MISC);
	
	//animal
	public static final RegistryObject<EntityType<FoodieZombieEntity>> FOODIE_ZOMBIE = registerEntityType(FoodieZombieEntity::new, "foodie_zombie", EntityClassification.WATER_CREATURE);
	
	//npc
	public static final RegistryObject<EntityType<CrazyDaveEntity>> CRAZY_DAVE = registerEntityType(CrazyDaveEntity::new, "crazy_dave", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PennyEntity>> PANNEY = registerEntityType(PennyEntity::new, "panney", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SunDaveEntity>> SUN_DAVE = registerEntityType(SunDaveEntity::new, "sun_dave", EntityClassification.CREATURE);
	
	//zombie 
	public static final RegistryObject<EntityType<NormalZombieEntity>> NORMAL_ZOMBIE = registerImmuneFireEntityType(NormalZombieEntity::new, "normal_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<FlagZombieEntity>> FLAG_ZOMBIE = registerImmuneFireEntityType(FlagZombieEntity::new, "flag_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ConeHeadZombieEntity>> CONEHEAD_ZOMBIE = registerImmuneFireEntityType(ConeHeadZombieEntity::new, "conehead_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<PoleZombieEntity>> POLE_ZOMBIE = registerImmuneFireEntityType(PoleZombieEntity::new, "pole_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BucketHeadZombieEntity>> BUCKETHEAD_ZOMBIE = registerImmuneFireEntityType(BucketHeadZombieEntity::new, "buckethead_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<SnorkelZombieEntity>> SNORKEL_ZOMBIE = registerImmuneFireEntityType(SnorkelZombieEntity::new, "snorkel_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ZomboniEntity>> ZOMBONI = registerImmuneFireEntityType(ZomboniEntity::new, "zomboni", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BobsleTeamEntity>> BOBSLE_TEAM = registerImmuneFireEntityType(BobsleTeamEntity::new, "bobsle_team", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BobsleZombieEntity>> BOBSLE_ZOMBIE = registerImmuneFireEntityType(BobsleZombieEntity::new, "bobsle_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ZombieDolphinEntity>> ZOMBIE_DOLPHIN = registerImmuneFireEntityType(ZombieDolphinEntity::new, "zombie_dolphin", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<DolphinRiderEntity>> DOLPHIN_RIDER = registerImmuneFireEntityType(DolphinRiderEntity::new, "dolphin_rider", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<DolphinRiderZombieEntity>> DOLPHIN_RIDER_ZOMBIE = registerImmuneFireEntityType(DolphinRiderZombieEntity::new, "dolphin_rider_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<LavaZombieEntity>> LAVA_ZOMBIE = registerImmuneFireEntityType(LavaZombieEntity::new, "lava_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<NewspaperZombieEntity>> NEWSPAPER_ZOMBIE = registerImmuneFireEntityType(NewspaperZombieEntity::new, "newspaper_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<TombStoneEntity>> TOMB_STONE = registerImmuneFireEntityType(TombStoneEntity::new, "tomb_stone", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ScreenDoorZombieEntity>> SCREENDOOR_ZOMBIE = registerImmuneFireEntityType(ScreenDoorZombieEntity::new, "screendoor_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<FootballZombieEntity>> FOOTBALL_ZOMBIE = registerImmuneFireEntityType(FootballZombieEntity::new, "football_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<DancingZombieEntity>> DANCING_ZOMBIE = registerImmuneFireEntityType(DancingZombieEntity::new, "dancing_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BackupDancerEntity>> BACKUP_DANCER = registerImmuneFireEntityType(BackupDancerEntity::new, "backup_dancer", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<GigaFootballZombieEntity>> GIGA_FOOTBALL_ZOMBIE = registerImmuneFireEntityType(GigaFootballZombieEntity::new, "giga_football_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<PumpkinZombieEntity>> PUMPKIN_ZOMBIE = registerImmuneFireEntityType(PumpkinZombieEntity::new, "pumpkin_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<TrickZombieEntity>> TRICK_ZOMBIE = registerImmuneFireEntityType(TrickZombieEntity::new, "trick_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<CoffinEntity>> COFFIN = registerImmuneFireEntityType(CoffinEntity::new, "coffin", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<MournerZombieEntity>> MOURNER_ZOMBIE = registerImmuneFireEntityType(MournerZombieEntity::new, "mourner_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<NobleZombieEntity>> NOBLE_ZOMBIE = registerImmuneFireEntityType(NobleZombieEntity::new, "noble_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<OldZombieEntity>> OLD_ZOMBIE = registerImmuneFireEntityType(OldZombieEntity::new, "old_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<SundayEditionZombieEntity>> SUNDAY_EDITION_ZOMBIE = registerImmuneFireEntityType(SundayEditionZombieEntity::new, "sunday_edition_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<JackInBoxZombieEntity>> JACK_IN_BOX_ZOMBIE = registerImmuneFireEntityType(JackInBoxZombieEntity::new, "jack_in_box_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<PogoZombieEntity>> POGO_ZOMBIE = registerImmuneFireEntityType(PogoZombieEntity::new, "pogo_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<YetiZombieEntity>> YETI_ZOMBIE = registerImmuneFireEntityType(YetiZombieEntity::new, "yeti_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<DiggerZombieEntity>> DIGGER_ZOMBIE = registerImmuneFireEntityType(DiggerZombieEntity::new, "digger_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BalloonZombieEntity>> BALLOON_ZOMBIE = registerImmuneFireEntityType(BalloonZombieEntity::new, "balloon_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<RaZombieEntity>> RA_ZOMBIE = registerImmuneFireEntityType(RaZombieEntity::new, "ra_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BungeeZombieEntity>> BUNGEE_ZOMBIE = registerImmuneFireEntityType(BungeeZombieEntity::new, "bungee_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<LadderZombieEntity>> LADDER_ZOMBIE = registerImmuneFireEntityType(LadderZombieEntity::new, "ladder_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<CatapultZombieEntity>> CATAPULT_ZOMBIE = registerImmuneFireEntityType(CatapultZombieEntity::new, "catapult_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<GargantuarEntity>> GARGANTUAR = registerImmuneFireEntityType(GargantuarEntity::new, "gargantuar", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ImpEntity>> IMP = registerImmuneFireEntityType(ImpEntity::new, "imp", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<GigaGargantuarEntity>> GIGA_GARGANTUAR = registerImmuneFireEntityType(GigaGargantuarEntity::new, "giga_gargantuar", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<Edgar090505Entity>> EDGAR_090505 = registerImmuneFireEntityType(Edgar090505Entity::new, "edgar_090505", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<PeaShooterZombieEntity>> PEASHOOTER_ZOMBIE = registerImmuneFireEntityType(PeaShooterZombieEntity::new, "peashooter_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<GatlingPeaZombieEntity>> GATLINGPEA_ZOMBIE = registerImmuneFireEntityType(GatlingPeaZombieEntity::new, "gatlingpea_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<SquashZombieEntity>> SQUASH_ZOMBIE = registerImmuneFireEntityType(SquashZombieEntity::new, "squash_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<JalapenoZombieEntity>> JALAPENO_ZOMBIE = registerImmuneFireEntityType(JalapenoZombieEntity::new, "jalapeno_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<WallNutZombieEntity>> WALLNUT_ZOMBIE = registerImmuneFireEntityType(WallNutZombieEntity::new, "wallnut_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<TallNutZombieEntity>> TALLNUT_ZOMBIE = registerImmuneFireEntityType(TallNutZombieEntity::new, "tallnut_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<GigaTombStoneEntity>> GIGA_TOMB_STONE = registerImmuneFireEntityType(GigaTombStoneEntity::new, "giga_tomb_stone", EntityClassification.MONSTER);
	
	//plant
	public static final RegistryObject<EntityType<PeaShooterEntity>> PEA_SHOOTER = registerEntityType(PeaShooterEntity::new, "pea_shooter", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SunFlowerEntity>> SUN_FLOWER = registerEntityType(SunFlowerEntity::new, "sun_flower", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CherryBombEntity>> CHERRY_BOMB = registerEntityType(CherryBombEntity::new, "cherry_bomb", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<WallNutEntity>> WALL_NUT = registerEntityType(WallNutEntity::new, "wall_nut", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PotatoMineEntity>> POTATO_MINE = registerEntityType(PotatoMineEntity::new, "potato_mine", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SnowPeaEntity>> SNOW_PEA = registerEntityType(SnowPeaEntity::new, "snow_pea", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ChomperEntity>> CHOMPER = registerEntityType(ChomperEntity::new, "chomper", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<RepeaterEntity>> REPEATER = registerEntityType(RepeaterEntity::new, "repeater", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<LilyPadEntity>> LILY_PAD = registerEntityType(LilyPadEntity::new, "lily_pad", EntityClassification.MISC);
	public static final RegistryObject<EntityType<SquashEntity>> SQUASH = registerEntityType(SquashEntity::new, "squash", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ThreePeaterEntity>> THREE_PEATER = registerEntityType(ThreePeaterEntity::new, "three_peater", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<TangleKelpEntity>> TANGLE_KELP = registerEntityType(TangleKelpEntity::new, "tangle_kelp", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<JalapenoEntity>> JALAPENO = registerEntityType(JalapenoEntity::new, "jalapeno", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SpikeWeedEntity>> SPIKE_WEED = registerEntityType(SpikeWeedEntity::new, "spike_weed", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<TorchWoodEntity>> TORCH_WOOD = registerEntityType(TorchWoodEntity::new, "torch_wood", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<TallNutEntity>> TALL_NUT = registerEntityType(TallNutEntity::new, "tall_nut", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PuffShroomEntity>> PUFF_SHROOM = registerEntityType(PuffShroomEntity::new, "puff_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SunShroomEntity>> SUN_SHROOM = registerEntityType(SunShroomEntity::new, "sun_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<FumeShroomEntity>> FUME_SHROOM = registerEntityType(FumeShroomEntity::new, "fume_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GraveBusterEntity>> GRAVE_BUSTER = registerEntityType(GraveBusterEntity::new, "grave_buster", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<HypnoShroomEntity>> HYPNO_SHROOM = registerEntityType(HypnoShroomEntity::new, "hypno_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ScaredyShroomEntity>> SCAREDY_SHROOM = registerEntityType(ScaredyShroomEntity::new, "scaredy_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<IceShroomEntity>> ICE_SHROOM = registerEntityType(IceShroomEntity::new, "ice_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<DoomShroomEntity>> DOOM_SHROOM = registerEntityType(DoomShroomEntity::new, "doom_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SeaShroomEntity>> SEA_SHROOM = registerEntityType(SeaShroomEntity::new, "sea_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SplitPeaEntity>> SPLIT_PEA = registerEntityType(SplitPeaEntity::new, "split_pea", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CoffeeBeanEntity>> COFFEE_BEAN = registerEntityType(CoffeeBeanEntity::new, "coffee_bean", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<MariGoldEntity>> MARIGOLD = registerEntityType(MariGoldEntity::new, "marigold", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GatlingPeaEntity>> GATLING_PEA = registerEntityType(GatlingPeaEntity::new, "gatling_pea", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<TwinSunFlowerEntity>> TWIN_SUNFLOWER = registerEntityType(TwinSunFlowerEntity::new, "twin_sunflower", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<WaterGuardEntity>> WATER_GUARD = registerEntityType(WaterGuardEntity::new, "water_guard", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PumpkinEntity>> PUMPKIN = registerEntityType(PumpkinEntity::new, "pumpkin", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PlanternEntity>> PLANTERN = registerEntityType(PlanternEntity::new, "plantern", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<MagnetShroomEntity>> MAGNET_SHROOM = registerEntityType(MagnetShroomEntity::new, "magnet_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CatTailEntity>> CAT_TAIL = registerEntityType(CatTailEntity::new, "cat_tail", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<StrangeCatEntity>> STRANGE_CAT = registerEntityType(StrangeCatEntity::new, "strange_cat", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<StarFruitEntity>> STAR_FRUIT = registerEntityType(StarFruitEntity::new, "star_fruit", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<AngelStarFruitEntity>> ANGEL_STAR_FRUIT = registerEntityType(AngelStarFruitEntity::new, "angel_star_fruit", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CactusEntity>> CACTUS = registerEntityType(CactusEntity::new, "cactus", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<BloverEntity>> BLOVER = registerEntityType(BloverEntity::new, "blover", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GloomShroomEntity>> GLOOM_SHROOM = registerEntityType(GloomShroomEntity::new, "gloom_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GoldMagnetEntity>> GOLD_MAGNET = registerEntityType(GoldMagnetEntity::new, "gold_magnet", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GoldLeafEntity>> GOLD_LEAF = registerEntityType(GoldLeafEntity::new, "gold_leaf", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ExplodeONutEntity>> EXPLODE_O_NUT = registerEntityType(ExplodeONutEntity::new, "explode_o_nut", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GiantWallNutEntity>> GIANT_WALL_NUT = registerEntityType(GiantWallNutEntity::new, "giant_wall_nut", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<FlowerPotEntity>> FLOWER_POT = registerEntityType(FlowerPotEntity::new, "flower_pot", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CabbagePultEntity>> CABBAGE_PULT = registerEntityType(CabbagePultEntity::new, "cabbage_pult", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<KernelPultEntity>> KERNEL_PULT = registerEntityType(KernelPultEntity::new, "kernel_pult", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ButterPultEntity>> BUTTER_PULT = registerEntityType(ButterPultEntity::new, "butter_pult", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GarlicEntity>> GARLIC = registerEntityType(GarlicEntity::new, "garlic", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<UmbrellaLeafEntity>> UMBRELLA_LEAF = registerEntityType(UmbrellaLeafEntity::new, "umbrella_leaf", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<MelonPultEntity>> MELON_PULT = registerEntityType(MelonPultEntity::new, "melon_pult", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<WinterMelonEntity>> WINTER_MELON = registerEntityType(WinterMelonEntity::new, "winter_melon", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<BambooLordEntity>> BAMBOO_LORD = registerEntityType(BambooLordEntity::new, "bamboo_lord", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<IcebergLettuceEntity>> ICEBERG_LETTUCE = registerEntityType(IcebergLettuceEntity::new, "iceberg_lettuce", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SpikeRockEntity>> SPIKE_ROCK = registerEntityType(SpikeRockEntity::new, "spike_rock", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<BonkChoyEntity>> BONK_CHOY = registerEntityType(BonkChoyEntity::new, "bonk_choy", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ImitaterEntity>> IMITATER = registerEntityType(ImitaterEntity::new, "imitater", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CobCannonEntity>> COB_CANNON = registerEntityType(CobCannonEntity::new, "cob_cannon", EntityClassification.CREATURE);
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		//drop
        RenderingRegistry.registerEntityRenderingHandler(SUN.get(), SunRender::new);
        RenderingRegistry.registerEntityRenderingHandler(COIN.get(), CoinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JEWEL.get(),JewelRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ENERGY.get(), EnergyRender::new);
        
        //bullet
        RenderingRegistry.registerEntityRenderingHandler(PEA.get(), PeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POTATO.get(), PotatoRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SPORE.get(), SporeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FUME.get(), FumeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(METAL.get(), MetalItemRender::new);
        RenderingRegistry.registerEntityRenderingHandler(THORN.get(), ThornRender::new);
        RenderingRegistry.registerEntityRenderingHandler(STAR.get(), StarRender::new);
//        RenderingRegistry.registerEntityRenderingHandler(NUT.get(), NutRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CABBAGE.get(), CabbageRender::new);
        RenderingRegistry.registerEntityRenderingHandler(KERNEL.get(), KernelRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BUTTER.get(), ButterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TARGET_ARROW.get(), TargetArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(MELON.get(), MelonRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FIRE_CRACKER.get(), FireCrackerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BALL.get(), BallRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CORN.get(), CornRender::new);
        
        //misc
        RenderingRegistry.registerEntityRenderingHandler(SMALL_CHOMPER.get(), SmallChomperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BOBSLE_CAR.get(), BobsleCarRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ZOMBIE_PART.get(), EmptyRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ZOMBIE_HAND.get(), ZombieHandRender::new);
        RenderingRegistry.registerEntityRenderingHandler(WALL_NUT_BOWLING.get(), WallNutBowlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EXPLOSION_BOWLING.get(), ExplosionBowlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GIANT_NUT_BOWLING.get(), GiantNutBowlingRender::new);
        RenderingRegistry.registerEntityRenderingHandler(LAWN_MOWER.get(), LawnMowerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FIRE_CRACKERS.get(), FireCrackersRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ELEMENT_BALL.get(), ElementBallRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DESTROY_CAR.get(), DestroyCarRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GARDEN_RAKE.get(), GardenRakeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ZOMBIE_DROP_BODY.get(), ZombieBodyRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DOOM_FIXER.get(), DoomFixerRender::new);
        
        //animal 
        RenderingRegistry.registerEntityRenderingHandler(FOODIE_ZOMBIE.get(), FoodieZombieRender::new);
        
        //npc
        RenderingRegistry.registerEntityRenderingHandler(CRAZY_DAVE.get(), CrazyDaveRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PANNEY.get(), PennyRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SUN_DAVE.get(), SunDaveRender::new);
        
        //zombie
        RenderingRegistry.registerEntityRenderingHandler(NORMAL_ZOMBIE.get(), NormalZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FLAG_ZOMBIE.get(), FlagZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CONEHEAD_ZOMBIE.get(), ConeHeadZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POLE_ZOMBIE.get(), PoleZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BUCKETHEAD_ZOMBIE.get(), BucketHeadZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SNORKEL_ZOMBIE.get(), SnorkelZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ZOMBONI.get(), ZomboniRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BOBSLE_TEAM.get(), BobsleTeamRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BOBSLE_ZOMBIE.get(), BobsleZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ZOMBIE_DOLPHIN.get(), ZombieDolphinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DOLPHIN_RIDER.get(), DolphinRiderRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DOLPHIN_RIDER_ZOMBIE.get(), DolphinRiderZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(LAVA_ZOMBIE.get(), LavaZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(NEWSPAPER_ZOMBIE.get(), NewspaperZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TOMB_STONE.get(), TombStoneRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SCREENDOOR_ZOMBIE.get(), ScreenDoorZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FOOTBALL_ZOMBIE.get(), FootballZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GIGA_FOOTBALL_ZOMBIE.get(), GigaFootballZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DANCING_ZOMBIE.get(),DancingZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BACKUP_DANCER.get(), BackupDancerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PUMPKIN_ZOMBIE.get(), PumpkinZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TRICK_ZOMBIE.get(), TrickZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(COFFIN.get(), CoffinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(MOURNER_ZOMBIE.get(), MournerZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(NOBLE_ZOMBIE.get(), NobleZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(OLD_ZOMBIE.get(), OldZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SUNDAY_EDITION_ZOMBIE.get(), SundayEditionZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JACK_IN_BOX_ZOMBIE.get(), JackInBoxZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POGO_ZOMBIE.get(), PogoZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(YETI_ZOMBIE.get(), YetiZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DIGGER_ZOMBIE.get(), DiggerZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BALLOON_ZOMBIE.get(), BalloonZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RA_ZOMBIE.get(), RaZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BUNGEE_ZOMBIE.get(), BungeeZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(LADDER_ZOMBIE.get(), LadderZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CATAPULT_ZOMBIE.get(), CatapultZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GARGANTUAR.get(), GargantuarRender::new);
        RenderingRegistry.registerEntityRenderingHandler(IMP.get(), ImpRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GIGA_GARGANTUAR.get(), GigaGargantuarRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EDGAR_090505.get(), Edgar090505Render::new);
        RenderingRegistry.registerEntityRenderingHandler(PEASHOOTER_ZOMBIE.get(), PeaShooterZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GATLINGPEA_ZOMBIE.get(), GatlingPeaZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SQUASH_ZOMBIE.get(), SquashZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JALAPENO_ZOMBIE.get(), JalapenoZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(WALLNUT_ZOMBIE.get(), WallNutZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TALLNUT_ZOMBIE.get(), TallNutZombieRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GIGA_TOMB_STONE.get(), GigaTombStoneRender::new);
        
        //plant
        RenderingRegistry.registerEntityRenderingHandler(PEA_SHOOTER.get(), PeaShooterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SUN_FLOWER.get(), SunFlowerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CHERRY_BOMB.get(), CherryBombRender::new);
        RenderingRegistry.registerEntityRenderingHandler(WALL_NUT.get(), WallNutRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POTATO_MINE.get(), PotatoMineRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SNOW_PEA.get(), SnowPeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CHOMPER.get(), ChomperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(REPEATER.get(), RepeaterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(LILY_PAD.get(), LilyPadRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SQUASH.get(), SquashRender::new);
        RenderingRegistry.registerEntityRenderingHandler(THREE_PEATER.get(), ThreePeaterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TANGLE_KELP.get(), TangleKelpRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JALAPENO.get(), JalapenoRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SPIKE_WEED.get(), SpikeWeedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TORCH_WOOD.get(), TorchWoodRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TALL_NUT.get(), TallNutRender::new);
        
        RenderingRegistry.registerEntityRenderingHandler(PUFF_SHROOM.get(), PuffShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SUN_SHROOM.get(), SunShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FUME_SHROOM.get(), FumeShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GRAVE_BUSTER.get(), GraveBusterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(HYPNO_SHROOM.get(), HypnoShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SCAREDY_SHROOM.get(), ScaredyShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ICE_SHROOM.get(), IceShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DOOM_SHROOM.get(), DoomShroomRender::new);
        
        RenderingRegistry.registerEntityRenderingHandler(SEA_SHROOM.get(), SeaShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PLANTERN.get(), PlanternRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CACTUS.get(), CactusRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BLOVER.get(), BloverRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SPLIT_PEA.get(), SplitPeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(STAR_FRUIT.get(), StarFruitRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PUMPKIN.get(), PumpkinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(MAGNET_SHROOM.get(), MagnetShroomRender::new);
        
        RenderingRegistry.registerEntityRenderingHandler(CABBAGE_PULT.get(), CabbagePultRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FLOWER_POT.get(), FlowerPotRender::new);
        RenderingRegistry.registerEntityRenderingHandler(KERNEL_PULT.get(), KernelPultRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GARLIC.get(), GarlicRender::new);
        RenderingRegistry.registerEntityRenderingHandler(COFFEE_BEAN.get(), CoffeeBeanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(UMBRELLA_LEAF.get(), UmbrellaLeafRender::new);
        RenderingRegistry.registerEntityRenderingHandler(MARIGOLD.get(), MariGoldRender::new);
        RenderingRegistry.registerEntityRenderingHandler(MELON_PULT.get(), MelonPultRender::new);
        
        RenderingRegistry.registerEntityRenderingHandler(GATLING_PEA.get(), GatlingPeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TWIN_SUNFLOWER.get(), TwinSunFlowerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GLOOM_SHROOM.get(), GloomShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CAT_TAIL.get(), CatTailRender::new);
        RenderingRegistry.registerEntityRenderingHandler(WINTER_MELON.get(), WinterMelonRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GOLD_MAGNET.get(), GoldMagnetRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SPIKE_ROCK.get(), SpikeRockRender::new);
        RenderingRegistry.registerEntityRenderingHandler(COB_CANNON.get(), CobCannonRender::new);
        RenderingRegistry.registerEntityRenderingHandler(IMITATER.get(), ImitaterRender::new);
        
        RenderingRegistry.registerEntityRenderingHandler(WATER_GUARD.get(), WaterGuardRender::new);
        RenderingRegistry.registerEntityRenderingHandler(STRANGE_CAT.get(), StrangeCatRender::new);
        
        RenderingRegistry.registerEntityRenderingHandler(ANGEL_STAR_FRUIT.get(), AngelStarFruitRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GOLD_LEAF.get(), GoldLeafRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EXPLODE_O_NUT.get(), ExplodeONutRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GIANT_WALL_NUT.get(), GiantWallNutRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BUTTER_PULT.get(), ButterPultRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BAMBOO_LORD.get(), BambooLordRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ICEBERG_LETTUCE.get(), IcebergLettuceRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BONK_CHOY.get(), BonkChoyRender::new);
    }
	
	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent ev) {
		PlantType.initPlants();
    	ZombieType.initZombies();
		PlantType.postInitPlants();
		ZombieType.postInitZombies();
		//init all plants' attributes.
		for(PlantType p : PlantType.getPlants()) {
			p.getEntityType().ifPresent(obj -> {
		        ev.put(obj, PVZPlantEntity.createPlantAttributes());
			});
		}
		//init all zombies' attributes.
		for(ZombieType z : ZombieType.getZombies()) {
			z.getEntityType().ifPresent(obj -> {
				ev.put(obj, PVZZombieEntity.createZombieAttributes());
			});
		}
		Arrays.asList(SUN.get(), COIN.get(), JEWEL.get(), ENERGY.get(), 
				CRAZY_DAVE.get(), SUN_DAVE.get(), PANNEY.get(),
				FOODIE_ZOMBIE.get()
				).forEach(obj -> {
			ev.put(obj, CreatureEntity.createMobAttributes().build());
		});
	}

	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory, String name, EntityClassification classification){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerImmuneFireEntityType(IFactory<T> factory, String name, EntityClassification classification){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).fireImmune().build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory, String name, EntityClassification classification, float w, float h){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).sized(w, h).build(StringUtil.prefix(name).toString());});
	}
	
}
