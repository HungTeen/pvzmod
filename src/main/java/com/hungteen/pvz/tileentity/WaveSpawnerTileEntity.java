package com.hungteen.pvz.tileentity;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.register.TileEntityRegister;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.server.ServerBossInfo;

public class WaveSpawnerTileEntity extends TileEntity implements ITickableTileEntity{

	private static final TranslationTextComponent CHANLLENGE = new TranslationTextComponent("event.minecraft.chanllenge");
	private final ServerBossInfo waveInfo = new ServerBossInfo(CHANLLENGE, BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_10);
	protected int currentWave;
	protected boolean isStarted;
	protected Optional<UUID> attackerUUID;
	protected final float CHALLENGE_VALID_RANGE = 50;
	public int tickTime;
	protected final int MAX_CHANLLENGE_TIME = 48000;
	
	public WaveSpawnerTileEntity() {
		super(TileEntityRegister.WAVE_SPAWNER.get());
		this.waveInfo.setPercent(0.0F);
		this.currentWave=0;
		this.isStarted=false;
		attackerUUID=Optional.empty();
		this.tickTime=0;
	}
	
//	public void nextWave()
//	{
//		this.currentWave++;
//		this.markDirty();
//	}
//	
//	public int getCurrentWave()
//	{
//		return this.currentWave;
//	}
	
	@Override
	public void tick() {
		if(this.world.getDifficulty()==Difficulty.PEACEFUL) {//turn mode to peace cause fail
			this.isStarted=false;
		}
		if(!this.checkCanContinue()) {
			return ;
		}
		this.tickTime++;
		this.waveInfo.setVisible(true);
		if(this.tickTime>=this.MAX_CHANLLENGE_TIME) {
			this.attackerFail();
			return ;
		}
	}

	/**
	 * fail the challenge
	 */
	protected void attackerFail(){
		this.isStarted=false;
		this.setAttackerUUID(null);
		this.tickTime=0;
		BlockPos pos=this.getPos();
		for(PlayerEntity player:this.world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.add(-CHALLENGE_VALID_RANGE, -5, -CHALLENGE_VALID_RANGE), pos.add(CHALLENGE_VALID_RANGE, 5, CHALLENGE_VALID_RANGE)))) {
			if(!this.world.isRemote) {
				player.sendMessage(new TranslationTextComponent("event.pvz.zombie_wave.fail").applyTextStyle(TextFormatting.RED));
			}
		}
	}
	
	/**
	 * win the challenge
	 */
	protected void attackerWin(){
		this.isStarted=false;
		boolean hasSpcAttacker=false;
		if(this.getAttackerUUID()!=null) {//give bonus
			PlayerEntity player = this.world.getPlayerByUuid(this.getAttackerUUID());
			if(player!=null) {
				hasSpcAttacker=true;
				this.giveBonusToPlayer(player);
			}
		}
		this.setAttackerUUID(null);
		this.tickTime=0;
		BlockPos pos=this.getPos();
		for(PlayerEntity player:this.world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos.add(-CHALLENGE_VALID_RANGE, -5, -CHALLENGE_VALID_RANGE), pos.add(CHALLENGE_VALID_RANGE, 5, CHALLENGE_VALID_RANGE)))) {
			if(!this.world.isRemote) {//send msg
				player.sendMessage(new TranslationTextComponent("event.pvz.zombie_wave.win").applyTextStyle(TextFormatting.GREEN));
			}
			if(!hasSpcAttacker) {//give bonus
				hasSpcAttacker=true;
				this.giveBonusToPlayer(player);
			}
		}
	}
	
	/**
	 * give bonus to a specific player or a random player in range
	 */
	protected void giveBonusToPlayer(PlayerEntity player)
	{
		player.giveExperiencePoints(1000);
	}
	
	protected boolean checkCanContinue(){
		if(!this.isStarted) return false;//didnt activate
		BlockPos pos=this.getPos();
		return this.world.isPlayerWithin(pos.getX()+ 0.5D, pos.getY()+ 0.5D, pos.getZ()+ 0.5D, this.CHALLENGE_VALID_RANGE);
	}
	
	public boolean checkCanStart(){
		return !this.isStarted;
	}
    
    public void startChallenge(PlayerEntity player)
    {
    	this.isStarted=true;
    	this.setAttackerUUID(player.getUniqueID());
    	this.markDirty();
    }
    
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.currentWave=compound.getInt("current_wave");
		this.isStarted=compound.getBoolean("is_started");
		String s;
	    if (compound.contains("AttackerUUID", 8)) {
	       s = compound.getString("AttackerUUID");
	    } else {
	         String s1 = compound.getString("Attacker");
	         s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.world.getServer(), s1);
	    }
	    if (!s.isEmpty()) {
	       try {
	          this.setAttackerUUID(UUID.fromString(s));
	       } catch (Throwable var4) {
	       }
	    }
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("current_wave", this.currentWave);
		compound.putBoolean("is_started", this.isStarted);
		if (this.getAttackerUUID() == null) {
	         compound.putString("OwnerUUID", "");
	    } else {
	         compound.putString("OwnerUUID", this.getAttackerUUID().toString());
	    }
		return super.write(compound);
	}
	
	@Nullable
    public UUID getAttackerUUID()
    {
        return this.attackerUUID.orElse((UUID)null);
    }

    public void setAttackerUUID(UUID uuid)
    {
        this.attackerUUID = Optional.ofNullable(uuid);
        this.markDirty();
    }
    
}
