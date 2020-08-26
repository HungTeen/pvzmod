package com.hungteen.pvz.structure.zombie;

import java.util.List;
import java.util.Random;

import com.hungteen.pvz.misc.loot.PVZLoot;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.StructureRegister;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BucketHouseComponents {

	private static final BlockPos STRUCTURE_OFFSET = new BlockPos(0, 0, 0);
	public static final ResourceLocation res1 = StringUtil.prefix("zombie_house/bucket_house");
	
	public static void generate(TemplateManager manager, BlockPos pos1, Rotation rotation, List<StructurePiece> list, Random rand) {
	      list.add(new BucketHouseComponent(manager, res1, pos1, rotation));
    }
	
	public static class BucketHouseComponent extends TemplateStructurePiece{

		protected final Rotation rotation;
		private final ResourceLocation res;
		
		public BucketHouseComponent(TemplateManager manager, ResourceLocation res,BlockPos pos, Rotation rotation) {
			super(StructureRegister.BUCKET_HOUSE, 0);
			this.templatePosition = pos;
			this.rotation = rotation;
			this.res=res;
			this.setUpTemplate(manager);
		}
		
		public BucketHouseComponent(TemplateManager manager, CompoundNBT nbt) {
			super(StructureRegister.BUCKET_HOUSE, nbt);
			this.res = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.setUpTemplate(manager);
		}

		private void setUpTemplate(TemplateManager p_204754_1_) {
			Template template = p_204754_1_.getTemplateDefaulted(this.res);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
					.setMirror(Mirror.NONE).setCenterOffset(BucketHouseComponents.STRUCTURE_OFFSET)
					.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);
		}
		
		@Override
		protected void readAdditional(CompoundNBT tagCompound) {
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", this.res.toString());
	        tagCompound.putString("Rot", this.rotation.name());
		}
		
		@Override
		public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn,
				MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
			BlockPos mid = BlockPos.ZERO;
			int dx=6,dy=10,dz=5;
			switch(rotation) {
			case CLOCKWISE_90:{mid=this.templatePosition.add(-dz, 0, dx); break;}
			case CLOCKWISE_180:{mid=this.templatePosition.add(-dx, 0, -dz); break;}
			case COUNTERCLOCKWISE_90:{mid=this.templatePosition.add(dz, 0, -dx); break;}
			default:{mid=this.templatePosition.add(dx, 0, dz); break;}
			}
			int height = chunkGeneratorIn.func_222531_c(mid.getX(), mid.getZ(), Type.WORLD_SURFACE_WG);
			this.templatePosition=new BlockPos(this.templatePosition.getX(),height+1,this.templatePosition.getZ());
			mid = new BlockPos(mid.getX(),this.templatePosition.getY()+dy,mid.getZ());
			super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
			//chests
			this.generateChest(worldIn, mutableBoundingBoxIn, worldIn.getRandom(), mid, PVZLoot.BUCKET_HOUSE, null);
			if(randomIn.nextInt(2)==0) {
				this.generateChest(worldIn, mutableBoundingBoxIn, worldIn.getRandom(), mid.add(randomIn.nextInt(2)+1, 0, randomIn.nextInt(2)+1), PVZLoot.BUCKET_HOUSE, null);
			}
			//spawner
			worldIn.setBlockState(mid.add(0, -dy, 0), Blocks.SPAWNER.getDefaultState(), 2);
			TileEntity te = worldIn.getTileEntity(mid.add(0, -dy, 0));
			if(te instanceof MobSpawnerTileEntity) {
				((MobSpawnerTileEntity)te).getSpawnerBaseLogic().setEntityType(getRandomEntityType(randomIn));
			}
			return true;
		}

		protected EntityType<?> getRandomEntityType(Random rand){
			int num=rand.nextInt(3);
			if(num==0) return EntityRegister.CONEHEAD_ZOMBIE.get();
			else if(num==1) return EntityRegister.POLE_ZOMBIE.get();
			else if(num==2) return EntityRegister.BUCKETHEAD_ZOMBIE.get();
			return null;
		}
		
		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			
		}
		
	}
}
