package com.hungteen.pvz.tileentity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public abstract class PVZMobSpawnerTileEntity extends TileEntity implements ITickable {

	protected List<Pair<EntityType<?>, Integer>> spawnList = new ArrayList<>();
//	private double mobRotation;
//	private double prevMobRotation;
	private int spawnDelay = 20;
	private final int minSpawnDelay = 100;
	private final int maxSpawnDelay = 400;
	private int spawnCount = 2;
	private final int minSpawnCount = 1;
	private final int maxSpawnCount = 4;
	private int maxNearbyEntities = 6;
	private int spawnRange = 4;
	private Entity displayCreature;

	public PVZMobSpawnerTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Override
	public void tick() {
		if (!this.isActivated()) {
//			this.prevMobRotation = this.mobRotation;
			return;
		}
		if (world.isRemote) {
//			double rx = pos.getX() + world.rand.nextFloat();
//			double ry = pos.getY() + world.rand.nextFloat();
//			double rz = pos.getZ() + world.rand.nextFloat();
//			world.addParticle(ParticleTypes.SMOKE, rx, ry, rz, 0.0D, 0.0D, 0.0D);
//			world.addParticle(ParticleTypes.FLAME, rx, ry, rz, 0.0D, 0.0D, 0.0D);
//			if (this.spawnDelay > 0) {
//				--this.spawnDelay;
//			}
//
//			this.prevMobRotation = this.mobRotation;
//			this.mobRotation = (this.mobRotation + (double) (1000.0F / ((float) this.spawnDelay + 200.0F))) % 360.0D;
			return;
		}
		--spawnDelay;
		if (spawnDelay == 0) {
			this.resetTimer();
			this.spawnEntities();
		}
	}

	protected void spawnEntities() {
		for (int i = 0; i < this.spawnCount; i++) {
			int num = world.rand.nextInt(1000);
			spawnList = getSpawnList();
			if (spawnList == null) {
				break;
			}
			for (Pair<EntityType<?>, Integer> pair : spawnList) {
				if (pair.getRight() > num) {
					EntityType<?> type = pair.getLeft();
					double d0 = pos.getX() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double) this.spawnRange + 0.5D;
					double d1 = pos.getY() + world.rand.nextInt(3) - 1;
					double d2 = pos.getZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double) this.spawnRange + 0.5D;
					if (world.hasNoCollisions(type.func_220328_a(d0, d1, d2))&& EntitySpawnPlacementRegistry.func_223515_a(type, world.getWorld(), SpawnReason.SPAWNER,new BlockPos(d0, d1, d2), world.getRandom())) {
						Entity entity = type.create(world);
						entity.setPosition(d0, d1, d2);
						int k = world.getEntitiesWithinAABB(entity.getClass(),
								(new AxisAlignedBB((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(),
										(double) (pos.getX() + 1), (double) (pos.getY() + 1),
										(double) (pos.getZ() + 1))).grow((double) this.spawnRange))
								.size();
						if (k >= this.maxNearbyEntities) {
							return;
						}

						entity.setLocationAndAngles(entity.getPosX(), entity.getPosY(), entity.getPosZ(),
								world.rand.nextFloat() * 360.0F, 0.0F);
						if (entity instanceof MobEntity) {
							((MobEntity) entity).onInitialSpawn(world,world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.SPAWNER,(ILivingEntityData) null, (CompoundNBT) null);
						}

						world.addEntity(entity);
						if (entity instanceof MobEntity) {
							((MobEntity) entity).spawnExplosionParticle();
						}
					}
				}
			}
		}
	}

	public List<Pair<EntityType<?>, Integer>> getSpawnList() {
		return spawnList;
	}

	protected void resetTimer() {
		this.spawnDelay = this.minSpawnDelay + world.rand.nextInt(this.maxSpawnDelay - this.minSpawnDelay);
		this.spawnCount = this.minSpawnCount + world.rand.nextInt(this.maxSpawnCount - this.minSpawnCount);
	}

	/**
	 * Get a temporary copy of the creature we're going to summon for display
	 * purposes
	 */
	public Entity getDisplayEntity() {
		if (this.displayCreature == null) {
			this.displayCreature = makeMyCreature();
		}
		return this.displayCreature;
	}

	protected Entity makeMyCreature() {
		EntityType<?> type = getSpawnList().get(getSpawnList().size()-1).getLeft();
		return type.create(world);
	}

	/**
	 * Returns true if there's a player close enough to this mob spawner to activate
	 * it.
	 */
	private boolean isActivated() {
		return this.isPlayerInRange();
	}

	public boolean isPlayerInRange() {
		return world.isPlayerWithin(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, getRange());
	}

	protected int getRange() {
		return 20;
	}
}
