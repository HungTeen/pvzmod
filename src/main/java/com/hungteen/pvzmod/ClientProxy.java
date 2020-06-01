package com.hungteen.pvzmod;

import com.hungteen.pvzmod.client.gui.render.ResourcesRenderer;
import com.hungteen.pvzmod.event.KeyBind;
import com.hungteen.pvzmod.particles.ParticleDarkRedBomb;
import com.hungteen.pvzmod.particles.ParticlePotatoBomb;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.particles.row1.ParticleBlueFire;
import com.hungteen.pvzmod.particles.row1.ParticleDirtBurstOut;
import com.hungteen.pvzmod.particles.row1.ParticleFlame;
import com.hungteen.pvzmod.particles.row1.ParticleSleeping;
import com.hungteen.pvzmod.particles.row1.ParticleSnow;
import com.hungteen.pvzmod.particles.row1.ParticleSpeedPea1;
import com.hungteen.pvzmod.particles.row1.ParticleSpeedPea2;
import com.hungteen.pvzmod.particles.row1.ParticleSuperPlantFood;
import com.hungteen.pvzmod.particles.row1.ParticleYellowFire;
import com.hungteen.pvzmod.render.RenderHandler;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerItemRenderer(Item item,int meta,String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(),id));
	}
	
	@Override
	public void registerVariantRenderer(Item item,int meta,String filename,String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID,filename),id));
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		RenderHandler.registerEntityRenders();// µÃÂ‰÷»æ
		KeyBind.init();
		registerClientEvents();
		//OBJLoader.INSTANCE.addDomain(Reference.MODID);
	}

	@Override
    public void init(FMLInitializationEvent event){
		super.init(event);
	}

	@Override
    public void postInit(FMLPostInitializationEvent event){
		super.postInit(event);
		MinecraftForge.EVENT_BUS.register(new ResourcesRenderer());
	}
	
	@Override
    public void spawnParticle(PVZParticleType particleType, double x, double y, double z, double velX, double velY, double velZ) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;
        if (world == null) return;
        
        if (mc.effectRenderer != null) {
            int i = mc.gameSettings.particleSetting;
            if (i == 1 && world.rand.nextInt(3) == 0) i = 2;
            Particle particle = null;
            switch (particleType) {
            case SPEED_PEA_ONE:
            	particle = new ParticleSpeedPea1(world, x, y, z, velX, velY, velZ);break;
            case SPEED_PEA_TWO:
            	particle = new ParticleSpeedPea2(world, x, y, z, velX, velY, velZ);break;
            case DIRT_BURSTOUT:
                particle = new ParticleDirtBurstOut(world, x, y, z, velX, velY, velZ);break;
            case DARK_RED_BOMB:
            	particle = new ParticleDarkRedBomb(world, x, y, z, velX, velY, velZ);break;
            case POTATO_BOMB:
            	particle = new ParticlePotatoBomb(world, x, y, z, velX, velY, velZ);break;
            case SUPER_PLANT_FOOD:
            	particle = new ParticleSuperPlantFood(world, x, y, z, velX, velY, velZ);break;
            case SNOW:
            	particle = new ParticleSnow(world, x, y, z, velX, velY, velZ);break;
            case SLEEPING:
            	particle = new ParticleSleeping(world, x, y, z, velX, velY, velZ);break;
            case FLAME:
            	particle = new ParticleFlame(world, x, y, z, velX, velY, velZ);break;
            case YELLOW_FIRE:
            	particle = new ParticleYellowFire(world, x, y, z,  velX, velY, velZ);break;
            case BLUE_FIRE:
            	particle = new ParticleBlueFire(world, x, y, z,  velX, velY, velZ);break;
			default:break;
            }

            if (particle != null) {
                mc.effectRenderer.addEffect(particle);
            }

        }
    }
	
	private void registerClientEvents()
	{
		MinecraftForge.EVENT_BUS.register(new KeyBind());
	}
	
}
