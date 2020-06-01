package com.hungteen.pvzmod.entities.plants.light;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGoldLeaf extends EntityPlantBase{

	public static final int CD = 2400;
	public static final int MINUS1 =1;
	public static final int MINUS2 =2;
	public static final int MINUS3 =3;
	
	public EntityGoldLeaf(World worldIn) {
		super(worldIn);
		this.setSize(0.5f, 0.7f);
	}

	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		this.setAttackTime(this.getAttackTime()+1);
		if(!this.world.isRemote&&this.getAttackTime()>=100) {
			if(this.world.getBlockState(new BlockPos(posX, posY-1, posZ)).getBlock()==Blocks.GOLD_BLOCK) {
				this.world.setBlockState(new BlockPos(posX, posY-1, posZ), this.getGoldenTile());
			}
			this.setDead();
		}
	}
	
	private IBlockState getGoldenTile()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return BlockRegister.GOLDENTILE1.getDefaultState();
		else if(lvl<=13) return BlockRegister.GOLDENTILE2.getDefaultState();
		else if(lvl<=20) return BlockRegister.GOLDENTILE3.getDefaultState();
		return BlockRegister.GOLDENTILE1.getDefaultState();
	}
	
	@Override
	protected boolean checkWeak() {
		if(this.isImmuneToWeak) return false;
		Entity entity =this.getRidingEntity();
		int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.posY-1);
        int k = MathHelper.floor(this.posZ);
    	BlockPos blockpos = new BlockPos(i, j, k);
    	IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        return block!=Blocks.GOLD_BLOCK;
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.WEAK) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 0;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.GOLD_LEAF;
	}

}
