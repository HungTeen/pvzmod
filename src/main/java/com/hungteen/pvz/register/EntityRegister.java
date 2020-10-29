package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.bullet.FumeEntity;
import com.hungteen.pvz.entity.bullet.PeaEntity;
import com.hungteen.pvz.entity.bullet.PotatoEntity;
import com.hungteen.pvz.entity.bullet.SporeEntity;
import com.hungteen.pvz.entity.creature.FoodieZombieEntity;
import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.entity.drop.EnergyEntity;
import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.misc.BobsleCarEntity;
import com.hungteen.pvz.entity.misc.SmallChomperEntity;
import com.hungteen.pvz.entity.npc.CrazyDaveEntity;
import com.hungteen.pvz.entity.npc.PanneyEntity;
import com.hungteen.pvz.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.entity.plant.appease.RepeaterEntity;
import com.hungteen.pvz.entity.plant.appease.ThreePeaterEntity;
import com.hungteen.pvz.entity.plant.assist.GraveBusterEntity;
import com.hungteen.pvz.entity.plant.defence.TallNutEntity;
import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.entity.plant.enforce.ChomperEntity;
import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.plant.enforce.TangleKelpEntity;
import com.hungteen.pvz.entity.plant.explosion.CherryBombEntity;
import com.hungteen.pvz.entity.plant.explosion.DoomShroomEntity;
import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.entity.plant.ice.IceShroomEntity;
import com.hungteen.pvz.entity.plant.ice.SnowPeaEntity;
import com.hungteen.pvz.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.entity.plant.light.SunShroomEntity;
import com.hungteen.pvz.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.entity.zombie.grassday.BucketHeadZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.ConeHeadZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.FlagZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.NormalZombieEntity;
import com.hungteen.pvz.entity.zombie.grassday.PoleZombieEntity;
import com.hungteen.pvz.entity.zombie.grassnight.NewspaperZombieEntity;
import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.entity.zombie.plantzombie.PumpkinZombieEntity;
import com.hungteen.pvz.entity.zombie.poolday.BobsleTeamEntity;
import com.hungteen.pvz.entity.zombie.poolday.BobsleZombieEntity;
import com.hungteen.pvz.entity.zombie.poolday.DolphinRiderEntity;
import com.hungteen.pvz.entity.zombie.poolday.DolphinRiderZombieEntity;
import com.hungteen.pvz.entity.zombie.poolday.LavaZombieEntity;
import com.hungteen.pvz.entity.zombie.poolday.SnorkelZombieEntity;
import com.hungteen.pvz.entity.zombie.poolday.ZombieDolphinEntity;
import com.hungteen.pvz.entity.zombie.poolday.ZomboniEntity;
import com.hungteen.pvz.render.entity.bullet.FumeRender;
import com.hungteen.pvz.render.entity.bullet.PeaRender;
import com.hungteen.pvz.render.entity.bullet.PotatoRender;
import com.hungteen.pvz.render.entity.bullet.SporeRender;
import com.hungteen.pvz.render.entity.creature.FoodieZombieRender;
import com.hungteen.pvz.render.entity.drop.CoinRender;
import com.hungteen.pvz.render.entity.drop.EnergyRender;
import com.hungteen.pvz.render.entity.drop.SunRender;
import com.hungteen.pvz.render.entity.misc.BobsleCarRender;
import com.hungteen.pvz.render.entity.misc.EmptyRender;
import com.hungteen.pvz.render.entity.misc.SmallChomperRender;
import com.hungteen.pvz.render.entity.npc.CrazyDaveRender;
import com.hungteen.pvz.render.entity.npc.PanneyRender;
import com.hungteen.pvz.render.entity.plant.appease.PeaShooterRender;
import com.hungteen.pvz.render.entity.plant.appease.RepeaterRender;
import com.hungteen.pvz.render.entity.plant.appease.ThreePeaterRender;
import com.hungteen.pvz.render.entity.plant.assist.GraveBusterRender;
import com.hungteen.pvz.render.entity.plant.defence.TallNutRender;
import com.hungteen.pvz.render.entity.plant.defence.WallNutRender;
import com.hungteen.pvz.render.entity.plant.enforce.ChomperRender;
import com.hungteen.pvz.render.entity.plant.enforce.SquashRender;
import com.hungteen.pvz.render.entity.plant.enforce.TangleKelpRender;
import com.hungteen.pvz.render.entity.plant.explosion.CherryBombRender;
import com.hungteen.pvz.render.entity.plant.explosion.DoomShroomRender;
import com.hungteen.pvz.render.entity.plant.explosion.PotatoMineRender;
import com.hungteen.pvz.render.entity.plant.flame.JalapenoRender;
import com.hungteen.pvz.render.entity.plant.flame.TorchWoodRender;
import com.hungteen.pvz.render.entity.plant.ice.IceShroomRender;
import com.hungteen.pvz.render.entity.plant.ice.SnowPeaRender;
import com.hungteen.pvz.render.entity.plant.light.SunFlowerRender;
import com.hungteen.pvz.render.entity.plant.light.SunShroomRender;
import com.hungteen.pvz.render.entity.plant.magic.HypnoShroomRender;
import com.hungteen.pvz.render.entity.plant.spear.SpikeWeedRender;
import com.hungteen.pvz.render.entity.plant.toxic.FumeShroomRender;
import com.hungteen.pvz.render.entity.plant.toxic.PuffShroomRender;
import com.hungteen.pvz.render.entity.plant.toxic.ScaredyShroomRender;
import com.hungteen.pvz.render.entity.zombie.grassday.BucketHeadZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.ConeHeadZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.FlagZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.NormalZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassday.PoleZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassnight.NewspaperZombieRender;
import com.hungteen.pvz.render.entity.zombie.grassnight.TombStoneRender;
import com.hungteen.pvz.render.entity.zombie.plantzombie.PumpkinZombieRender;
import com.hungteen.pvz.render.entity.zombie.poolday.BobsleTeamRender;
import com.hungteen.pvz.render.entity.zombie.poolday.BobsleZombieRender;
import com.hungteen.pvz.render.entity.zombie.poolday.DolphinRiderRender;
import com.hungteen.pvz.render.entity.zombie.poolday.DolphinRiderZombieRender;
import com.hungteen.pvz.render.entity.zombie.poolday.LavaZombieRender;
import com.hungteen.pvz.render.entity.zombie.poolday.SnorkelZombieRender;
import com.hungteen.pvz.render.entity.zombie.poolday.ZombieDolphinRender;
import com.hungteen.pvz.render.entity.zombie.poolday.ZomboniRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegister {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, PVZMod.MOD_ID);
    
	//entitytype
	//drop
	public static final RegistryObject<EntityType<SunEntity>> SUN = registerEntityType(SunEntity::new, "sun", EntityClassification.AMBIENT);
	public static final RegistryObject<EntityType<CoinEntity>> COIN = registerEntityType(CoinEntity::new, "coin", EntityClassification.MISC);
	public static final RegistryObject<EntityType<EnergyEntity>> ENERGY = registerEntityType(EnergyEntity::new, "energy", EntityClassification.MISC,0.9f,2f);
	
	//bullet
	public static final RegistryObject<EntityType<PeaEntity>> PEA = registerEntityType(PeaEntity::new, "pea", EntityClassification.MISC);
	public static final RegistryObject<EntityType<PotatoEntity>> POTATO = registerEntityType(PotatoEntity::new, "potato", EntityClassification.MISC);
	public static final RegistryObject<EntityType<SporeEntity>> SPORE = registerEntityType(SporeEntity::new, "spore", EntityClassification.MISC);
	public static final RegistryObject<EntityType<FumeEntity>> FUME = registerEntityType(FumeEntity::new, "fume", EntityClassification.MISC);
	
	//misc 
	public static final RegistryObject<EntityType<SmallChomperEntity>> SMALL_CHOMPER = registerEntityType(SmallChomperEntity::new, "small_chomper", EntityClassification.MISC);
//	public static final RegistryObject<EntityType<DuckyTubeEntity>> DUCKY_TUBE = registerEntityType(DuckyTubeEntity::new, "duck_tube", EntityClassification.MISC);
	public static final RegistryObject<EntityType<BobsleCarEntity>> BOBSLE_CAR = registerEntityType(BobsleCarEntity::new, "bobsle_car", EntityClassification.MISC);
	public static final RegistryObject<EntityType<PVZZombiePartEntity>> ZOMBIE_PART = registerEntityType(PVZZombiePartEntity::new, "zombie_part", EntityClassification.MISC);
	
	//animal
	public static final RegistryObject<EntityType<FoodieZombieEntity>> FOODIE_ZOMBIE = registerEntityType(FoodieZombieEntity::new, "foodie_zombie", EntityClassification.WATER_CREATURE);
	
	//npc
	public static final RegistryObject<EntityType<CrazyDaveEntity>> CRAZY_DAVE = registerEntityType(CrazyDaveEntity::new, "crazy_dave", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PanneyEntity>> PANNEY = registerEntityType(PanneyEntity::new, "panney", EntityClassification.CREATURE);
	
	//zombie 
	public static final RegistryObject<EntityType<NormalZombieEntity>> NORMAL_ZOMBIE = registerEntityType(NormalZombieEntity::new, "normal_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<FlagZombieEntity>> FLAG_ZOMBIE = registerEntityType(FlagZombieEntity::new, "flag_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ConeHeadZombieEntity>> CONEHEAD_ZOMBIE = registerEntityType(ConeHeadZombieEntity::new, "conehead_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<PoleZombieEntity>> POLE_ZOMBIE = registerEntityType(PoleZombieEntity::new, "pole_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BucketHeadZombieEntity>> BUCKETHEAD_ZOMBIE = registerEntityType(BucketHeadZombieEntity::new, "buckethead_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<SnorkelZombieEntity>> SNORKEL_ZOMBIE = registerEntityType(SnorkelZombieEntity::new, "snorkel_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ZomboniEntity>> ZOMBONI = registerEntityType(ZomboniEntity::new, "zomboni", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BobsleTeamEntity>> BOBSLE_TEAM = registerEntityType(BobsleTeamEntity::new, "bobsle_team", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<BobsleZombieEntity>> BOBSLE_ZOMBIE = registerEntityType(BobsleZombieEntity::new, "bobsle_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<ZombieDolphinEntity>> ZOMBIE_DOLPHIN = registerEntityType(ZombieDolphinEntity::new, "zombie_dolphin", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<DolphinRiderEntity>> DOLPHIN_RIDER = registerEntityType(DolphinRiderEntity::new, "dolphin_rider", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<DolphinRiderZombieEntity>> DOLPHIN_RIDER_ZOMBIE = registerEntityType(DolphinRiderZombieEntity::new, "dolphin_rider_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<LavaZombieEntity>> LAVA_ZOMBIE = registerImmuneFireEntityType(LavaZombieEntity::new, "lava_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<NewspaperZombieEntity>> NEWSPAPER_ZOMBIE = registerImmuneFireEntityType(NewspaperZombieEntity::new, "newspaper_zombie", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<TombStoneEntity>> TOMB_STONE = registerImmuneFireEntityType(TombStoneEntity::new, "tomb_stone", EntityClassification.MONSTER);
	public static final RegistryObject<EntityType<PumpkinZombieEntity>> PUMPKIN_ZOMBIE = registerImmuneFireEntityType(PumpkinZombieEntity::new, "pumpkin_zombie", EntityClassification.MONSTER);
	
	//plant
	public static final RegistryObject<EntityType<PeaShooterEntity>> PEA_SHOOTER = registerEntityType(PeaShooterEntity::new, "pea_shooter", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SunFlowerEntity>> SUN_FLOWER = registerEntityType(SunFlowerEntity::new, "sun_flower", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<CherryBombEntity>> CHERRY_BOMB = registerEntityType(CherryBombEntity::new, "cherry_bomb", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<WallNutEntity>> WALL_NUT = registerEntityType(WallNutEntity::new, "wall_nut", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PotatoMineEntity>> POTATO_MINE = registerEntityType(PotatoMineEntity::new, "potato_mine", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SnowPeaEntity>> SNOW_PEA = registerEntityType(SnowPeaEntity::new, "snow_pea", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ChomperEntity>> CHOMPER = registerEntityType(ChomperEntity::new, "chomper", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<RepeaterEntity>> REPEATER = registerEntityType(RepeaterEntity::new, "repeater", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SquashEntity>> SQUASH = registerEntityType(SquashEntity::new, "squash", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ThreePeaterEntity>> THREE_PEATER = registerEntityType(ThreePeaterEntity::new, "three_peater", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<TangleKelpEntity>> TANGLE_KELP = registerEntityType(TangleKelpEntity::new, "tangle_kelp", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<JalapenoEntity>> JALAPENO = registerEntityType(JalapenoEntity::new, "jalapeno", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SpikeWeedEntity>> SPIKE_WEED = registerEntityType(SpikeWeedEntity::new, "spike_weed", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<TorchWoodEntity>> TORCH_WOOD = registerEntityType(TorchWoodEntity::new, "torch_wood", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<TallNutEntity>> TALL_NUT = registerEntityType(TallNutEntity::new, "tall_nut", EntityClassification.CREATURE);
//	public static final RegistryObject<EntityType<WaterGuardEntity>> WATER_GUARD = registerEntityType(WaterGuardEntity::new, "water_guard", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<PuffShroomEntity>> PUFF_SHROOM = registerEntityType(PuffShroomEntity::new, "puff_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<SunShroomEntity>> SUN_SHROOM = registerEntityType(SunShroomEntity::new, "sun_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<FumeShroomEntity>> FUME_SHROOM = registerEntityType(FumeShroomEntity::new, "fume_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<GraveBusterEntity>> GRAVE_BUSTER = registerEntityType(GraveBusterEntity::new, "grave_buster", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<HypnoShroomEntity>> HYPNO_SHROOM = registerEntityType(HypnoShroomEntity::new, "hypno_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<ScaredyShroomEntity>> SCAREDY_SHROOM = registerEntityType(ScaredyShroomEntity::new, "scaredy_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<IceShroomEntity>> ICE_SHROOM = registerEntityType(IceShroomEntity::new, "ice_shroom", EntityClassification.CREATURE);
	public static final RegistryObject<EntityType<DoomShroomEntity>> DOOM_SHROOM = registerEntityType(DoomShroomEntity::new, "doom_shroom", EntityClassification.CREATURE);
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
		//drop
        RenderingRegistry.registerEntityRenderingHandler(SUN.get(), SunRender::new);
        RenderingRegistry.registerEntityRenderingHandler(COIN.get(), CoinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ENERGY.get(), EnergyRender::new);
        
        //bullet
        RenderingRegistry.registerEntityRenderingHandler(PEA.get(), PeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POTATO.get(), PotatoRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SPORE.get(), SporeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FUME.get(), FumeRender::new);
        
        //misc
        RenderingRegistry.registerEntityRenderingHandler(SMALL_CHOMPER.get(), SmallChomperRender::new);
//        RenderingRegistry.registerEntityRenderingHandler(DUCKY_TUBE.get(), DuckyTubeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(BOBSLE_CAR.get(), BobsleCarRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ZOMBIE_PART.get(), EmptyRender::new);
        
        //animal 
        RenderingRegistry.registerEntityRenderingHandler(FOODIE_ZOMBIE.get(), FoodieZombieRender::new);
        
        //npc
        RenderingRegistry.registerEntityRenderingHandler(CRAZY_DAVE.get(), CrazyDaveRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PANNEY.get(), PanneyRender::new);
        
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
        RenderingRegistry.registerEntityRenderingHandler(PUMPKIN_ZOMBIE.get(), PumpkinZombieRender::new);
        
        //plant
        RenderingRegistry.registerEntityRenderingHandler(PEA_SHOOTER.get(), PeaShooterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SUN_FLOWER.get(), SunFlowerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CHERRY_BOMB.get(), CherryBombRender::new);
        RenderingRegistry.registerEntityRenderingHandler(WALL_NUT.get(), WallNutRender::new);
        RenderingRegistry.registerEntityRenderingHandler(POTATO_MINE.get(), PotatoMineRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SNOW_PEA.get(), SnowPeaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CHOMPER.get(), ChomperRender::new);
        RenderingRegistry.registerEntityRenderingHandler(REPEATER.get(), RepeaterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SQUASH.get(), SquashRender::new);
        RenderingRegistry.registerEntityRenderingHandler(THREE_PEATER.get(), ThreePeaterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TANGLE_KELP.get(), TangleKelpRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JALAPENO.get(), JalapenoRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SPIKE_WEED.get(), SpikeWeedRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TORCH_WOOD.get(), TorchWoodRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TALL_NUT.get(), TallNutRender::new);
//        RenderingRegistry.registerEntityRenderingHandler(WATER_GUARD.get(),WaterGuardRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PUFF_SHROOM.get(), PuffShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SUN_SHROOM.get(), SunShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FUME_SHROOM.get(), FumeShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(GRAVE_BUSTER.get(), GraveBusterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(HYPNO_SHROOM.get(), HypnoShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SCAREDY_SHROOM.get(), ScaredyShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ICE_SHROOM.get(), IceShroomRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DOOM_SHROOM.get(), DoomShroomRender::new);
    }

	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory,String name,EntityClassification classification){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.create(factory, classification).build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerImmuneFireEntityType(IFactory<T> factory,String name,EntityClassification classification){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.create(factory, classification).immuneToFire().build(StringUtil.prefix(name).toString());});
	}
	
	private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(IFactory<T> factory,String name,EntityClassification classification,float w,float h){
		return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.create(factory, classification).size(w, h).build(StringUtil.prefix(name).toString());});
	}
}
