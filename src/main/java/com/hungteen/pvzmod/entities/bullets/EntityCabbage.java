package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.bullets.EntityPea.State;
import com.hungteen.pvzmod.entities.bullets.EntityPea.Type;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.particles.base.PVZParticleType;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCabbage extends EntityPult{
	
	
	public EntityCabbage(World worldIn) {
		super(worldIn);
		setSize(0.7f, 0.7f);
	}
	
	public EntityCabbage(World worldIn, EntityLivingBase throwerIn,Type type)
    {
        super(worldIn, throwerIn, type);
    }

	protected float getAttackDamage()
	{
		float damage=0;
		if(this.shooter instanceof EntityCabbagePult) 
			damage=((EntityCabbagePult)this.shooter).getAttackDamage();
		if(this.getPultType()!=Type.NORMAL) damage+=20;
		return damage;
	}
	
//    @SideOnly(Side.CLIENT)
//    public void handleStatusUpdate(byte id)
//    {
//        if (id == 3)
//        {
//            for (int i = 0; i < 8; ++i)
//            {
//                this.world.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
//            }
//        }
//    }

	@Override
	protected EntityPult getPultBullet(EntityPultBase pult) {
		return new EntityCabbage(world, pult, Type.DOWN);
	}
}
