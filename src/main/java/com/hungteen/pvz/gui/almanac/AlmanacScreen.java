package com.hungteen.pvz.gui.almanac;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.entity.plant.appease.RepeaterEntity;
import com.hungteen.pvz.entity.plant.appease.ThreePeaterEntity;
import com.hungteen.pvz.entity.plant.assist.GraveBusterEntity;
import com.hungteen.pvz.entity.plant.enforce.ChomperEntity;
import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.plant.enforce.TangleKelpEntity;
import com.hungteen.pvz.entity.plant.explosion.CherryBombEntity;
import com.hungteen.pvz.entity.plant.explosion.DoomShroomEntity;
import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.entity.plant.flame.JalapenoEntity;
import com.hungteen.pvz.entity.plant.ice.IceShroomEntity;
import com.hungteen.pvz.entity.plant.ice.SnowPeaEntity;
import com.hungteen.pvz.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.entity.plant.light.SunShroomEntity;
import com.hungteen.pvz.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.utils.ItemUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlmanacScreen extends ContainerScreen<AlmanacContainer> {

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac.png");
	private final AlmanacSearchGui searchGui = new AlmanacSearchGui();
	private int propertyCnt;
	
	public AlmanacScreen(AlmanacContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.xSize = 240;
		this.ySize = 200;
	}

	@Override
	protected void init() {
		super.init();
		this.searchGui.init(this.minecraft, this.container, this.width, this.height);
		this.guiLeft = this.searchGui.updateScreenPosition(this.xSize, this.ySize);
		this.searchGui.initSearchBar();
		this.children.add(this.searchGui);
		this.setFocusedDefault(this.searchGui);
	}

	@Override
	public void tick() {
		super.tick();
		this.searchGui.tick();
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.searchGui.render(mouseX, mouseY, partialTicks);
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		this.renderAlmanac();
		this.searchGui.renderTooltip(mouseX, mouseY);
	}
	
	protected void renderAlmanac(){
		Almanacs current = this.searchGui.getCurrentAlmanac();
		if(this.searchGui.getCurrentAlmanac()==null) {
			current = Almanacs.PLAYER;
		}
		this.renderTitle(current);
		if(!ClientPlayerResources.isAlmanacUnLocked(current)) {
			return ;
		}
		this.renderLogo(current);
		this.renderXpBar(current);
		this.renderShortInfo(current);
		if(Almanacs.isPlant(current)) {
			Plants plant = Plants.getPlantByName(current.toString().toLowerCase());
			int lvl = ClientPlayerResources.getPlayerPlantCardLvl(plant);
//			int maxLvl = PlantUtil.getPlantMaxLvl(plant);
			this.propertyCnt = 0;
			@SuppressWarnings("resource")
			World world = Minecraft.getInstance().player.world;
			this.drawProperty(Properties.SUN_COST, PlantUtil.getPlantSunCost(plant));
			this.drawProperty(Properties.COOL_DOWN, PlantUtil.getPlantCoolDownTime(plant, lvl));
			if(plant == Plants.LILY_PAD) {
				return ;
			}
			PVZPlantEntity plantEntity =  PlantUtil.getPlantEntity(world, plant);
			plantEntity.setPlantLvl(lvl);
			this.drawProperty(Properties.HEALTH, plantEntity.getPlantHealth());
			switch(plant) {
			case PEA_SHOOTER:{
				PeaShooterEntity peaShooter = (PeaShooterEntity)plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, peaShooter.getAttackDamage());
				this.drawProperty(Properties.BULLET_SPEED, peaShooter.getBulletSpeed());
				break;
			}
			case SUN_FLOWER:{
				SunFlowerEntity sunFlower = (SunFlowerEntity) plantEntity;
				this.drawProperty(Properties.GEN_TIME, sunFlower.getGenCD());
				break;
			}
			case CHERRY_BOMB:{
				CherryBombEntity cherryBomb = (CherryBombEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, cherryBomb.getAttackDamage());
				break;
			}
			case WALL_NUT:{
				break;
			}
			case POTATO_MINE:{
				PotatoMineEntity potatoMine = (PotatoMineEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, potatoMine.getAttackDamage());
				this.drawProperty(Properties.PRE_TIME, potatoMine.getReadyTime());
				break;
			}
			case SNOW_PEA:{
				SnowPeaEntity snowPea = (SnowPeaEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, snowPea.getAttackDamage());
				this.drawProperty(Properties.BULLET_SPEED, snowPea.getBulletSpeed());
				this.drawProperty(Properties.COLD_EFFECT, snowPea.getColdEffect().getAmplifier());
				this.drawProperty(Properties.COLD_EFFECT_TIME, snowPea.getColdEffect().getDuration());
				break;
			}
			case CHOMPER:{
				ChomperEntity chomper = (ChomperEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, chomper.getAttackDamage());
				this.drawProperty(Properties.ATTACK_CD, chomper.getRestCD());
				break;
			}
			case REPEATER:{
				RepeaterEntity repeater = (RepeaterEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, repeater.getAttackDamage());
				this.drawProperty(Properties.BULLET_SPEED, repeater.getBulletSpeed());
				break;
			}
			case LILY_PAD:{
				break;
			}
			case SQUASH:{
				SquashEntity squash = (SquashEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, squash.getAttackDamage());
				this.drawProperty(Properties.DEATH_CHANCE, squash.getDeathChance() * 1.0f / 100);
				break;
			}
			case THREE_PEATER:{
				ThreePeaterEntity threePeater = (ThreePeaterEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, threePeater.getAttackDamage());
				this.drawProperty(Properties.BULLET_SPEED, threePeater.getBulletSpeed());
				break;
			}
			case TANGLE_KELP:{
				TangleKelpEntity tangleKelp = (TangleKelpEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, tangleKelp.getAttackDamage());
				break;
			}
			case JALAPENO:{
				JalapenoEntity jalapeno = (JalapenoEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, jalapeno.getAttackDamage());
				this.drawProperty(Properties.ATTACK_RANGE, jalapeno.getFireRange());
				break;
			}
			case SPIKE_WEED:{
				SpikeWeedEntity spikeWeed = (SpikeWeedEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, spikeWeed.getAttackDamage());
				this.drawProperty(Properties.ATTACK_CD, spikeWeed.getAttackCD());
				break;
			}
			case TORCH_WOOD:{
				break;
			}
			case TALL_NUT:{
				break;
			}
			case PUFF_SHROOM:{
				PuffShroomEntity puffShroom = (PuffShroomEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, puffShroom.getAttackDamage());
				this.drawProperty(Properties.LIVE_TICK, puffShroom.getMaxLiveTick());
				break;
			}
			case SUN_SHROOM:{
				SunShroomEntity sunShroom = (SunShroomEntity) plantEntity;
				this.drawProperty(Properties.GEN_TIME, sunShroom.getGenCD());
				break;
			}
			case FUME_SHROOM:{
				FumeShroomEntity fumeShroom = (FumeShroomEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, fumeShroom.getAttackDamage());
				break;
			}
			case GRAVE_BUSTER:{
				GraveBusterEntity graveBuster = (GraveBusterEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_CD, graveBuster.getAttackCD());
				this.drawProperty(Properties.KILL_COUNT, graveBuster.getMaxKillCnt());
				break;
			}
			case HYPNO_SHROOM:{
				HypnoShroomEntity hypnoShroom = (HypnoShroomEntity) plantEntity;
				this.drawProperty(Properties.HEAL_HEALTH, hypnoShroom.getHealHealth());
				break;
			}
			case SCAREDY_SHROOM:{
				ScaredyShroomEntity scaredyShroom = (ScaredyShroomEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, scaredyShroom.getAttackDamage());
				this.drawProperty(Properties.SCARE_RANGE, scaredyShroom.getScareDistance());
				break;
			}
			case ICE_SHROOM:{
				IceShroomEntity iceShroom = (IceShroomEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, iceShroom.getAttackDamage());
				this.drawProperty(Properties.FROZEN_EFFECT_TIME, iceShroom.getFrozenEffect().getDuration());
				this.drawProperty(Properties.COLD_EFFECT, iceShroom.getColdEffect().getAmplifier());
				this.drawProperty(Properties.COLD_EFFECT_TIME, iceShroom.getColdEffect().getDuration());
				this.drawProperty(Properties.ATTACK_RANGE, iceShroom.getAttackRange());
				break;
			}
			case DOOM_SHROOM:{
				DoomShroomEntity doomShroom = (DoomShroomEntity) plantEntity;
				this.drawProperty(Properties.ATTACK_DAMAGE, doomShroom.getAttackDamage());
				this.drawProperty(Properties.ATTACK_RANGE, doomShroom.getAttackRange());
				break;
			}
			default:{
				PVZMod.LOGGER.debug("error plant enum type");
				break;
			}
			}
		}else if(current == Almanacs.PLAYER){//for player
    		int sunNum = ClientPlayerResources.getPlayerStats(Resources.SUN_NUM);
    		int energyNum = ClientPlayerResources.getPlayerStats(Resources.ENERGY_NUM);
    		int money = ClientPlayerResources.getPlayerStats(Resources.MONEY);
    		int gemNum = ClientPlayerResources.getPlayerStats(Resources.GEM_NUM);
    		this.propertyCnt = 0;
    		this.drawProperty(Properties.SUN_NUM, sunNum);
    		this.drawProperty(Properties.ENERGY_NUM, energyNum);
    		this.drawProperty(Properties.MONEY, money);
    		this.drawProperty(Properties.GEM_NUM, gemNum);
		}
	}
	
	protected void drawProperty(Properties prop, float num) {
		this.propertyCnt ++;
		int posX = this.guiLeft + 9 + 2 + ((this.propertyCnt & 1) == 1 ? 0 : 110);
		int posY = this.guiTop + 92 + 2 + ((this.propertyCnt - 1) / 2) * 18;
		String string = prop.getName()+": ";
		if(prop == Properties.ATTACK_DAMAGE) {
			string += num;
		}else {
			string += MathHelper.fastFloor(num);
		}
		StringUtil.drawScaledString(font, string, posX, posY, prop.getColor(), 1f);
	}
	
	protected void renderShortInfo(Almanacs a) {
		if(a==Almanacs.PLAYER) {
			return ;
		}
		RenderSystem.pushMatrix();
//		int posX = this.guiLeft + 82 + 150 / 2;
//		int posY = this.guiTop + 26 + 2;
//		for(int i=0;i<4;i++) {
//			StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz.almanac."+a.toString().toLowerCase()+(i+1)).getFormattedText(), posX, posY, Colors.DARK_BLUE, 0.7f);
//			posY += 12;
//		}
		RenderSystem.popMatrix();
	}
	
	protected void renderXpBar(Almanacs a) {
		RenderSystem.pushMatrix();
		int maxLvl = 1, lvl = 1;
		Plants p = null;
		if(Almanacs.isPlant(a)) {
			p = Plants.getPlantByName(a.toString().toLowerCase());
			maxLvl = PlantUtil.getPlantMaxLvl(p);
			lvl = ClientPlayerResources.getPlayerPlantCardLvl(p);
		}else if(a==Almanacs.PLAYER) {
			maxLvl = PlayerUtil.MAX_TREE_LVL;
			lvl = ClientPlayerResources.getPlayerStats(Resources.TREE_LVL);
		}
		String lvlInfo = Properties.LVL.getName()+":"+lvl;
		int barWidth = 62, barHeight = 9;
		int len = barWidth;
		if(maxLvl != lvl && p != null) { 
			int xp = ClientPlayerResources.getPlayerPlantCardXp(p);
			int maxXp = PlantUtil.getPlantLevelUpXp(p, lvl);
			len = RenderUtil.getRenderBarLen(xp, maxXp, barWidth);
		}else if(a == Almanacs.PLAYER) {
			int xp = ClientPlayerResources.getPlayerStats(Resources.TREE_XP);
			int maxXp = PlayerUtil.getPlayerLevelUpXp(lvl);
			len = RenderUtil.getRenderBarLen(xp, maxXp, barWidth);
		}
		int texX = 0;
		int texY = maxLvl == lvl ? 210 : 200;
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(this.guiLeft + 9, this.guiTop + 66, texX, texY, len, barHeight);
		StringUtil.drawCenteredScaledString(this.font, lvlInfo, this.guiLeft + 9 + barWidth / 2, this.guiTop + 66 + 1, Colors.BLACK, 1f);
		RenderSystem.popMatrix();
	}
	
	protected void renderTitle(Almanacs a) {
		RenderSystem.pushMatrix();
		int dx = this.guiLeft + 82 + 150 / 2, dy = this.guiTop + 10 + 2;
		StringUtil.drawCenteredScaledString(this.font, Almanacs.getAlmanacName(a), dx, dy, Colors.DARK_GREEN, 1.6f);
		RenderSystem.popMatrix();
	}
	
	protected void renderLogo(Almanacs a) {
		int dx = this.guiLeft + 16, dy = this.guiTop + 14;
		int scale = 3;
		RenderSystem.pushMatrix();
		RenderSystem.scaled(scale, scale, scale);
		RenderSystem.translated((dx % scale) * 1.0d / scale , (dy % scale) * 1.0d / scale, 0);
		this.itemRenderer.renderItemIntoGUI(ItemUtil.getItemStackByAlmanac(a), dx / scale, dy / scale);
		RenderSystem.popMatrix();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
		boolean flag = mouseX < guiLeftIn || mouseY < guiTopIn || mouseX >= guiLeftIn + this.xSize || mouseY >= guiTopIn + this.ySize;
	    return this.searchGui.hasClickedOutside(mouseX, mouseY, this.guiLeft, this.guiTop, mouseButton) && flag;
	}
	
	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		if (this.searchGui.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_)) {
			return true;
		} else {
			return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
		}
	}

	@Override
	public void removed() {
		this.searchGui.removed();
		super.removed();
	}
	
	private enum Properties{
		LVL,
		HEALTH,
		SUN_COST,
		COOL_DOWN,
		ATTACK_DAMAGE,
		GEN_TIME,
		//cool down per attack
		ATTACK_CD,
		//prepare time e.g. potato mine
		PRE_TIME,
		ARMOR_HEALTH,
		SUN_NUM,
		ENERGY_NUM,
		MONEY,
		GEM_NUM,
		//bullet speed e.g. pea shooter
		BULLET_SPEED,
		//cold effect lvl
		COLD_EFFECT,
		//cold effect duration
		COLD_EFFECT_TIME,
		//frozen effect duration
		FROZEN_EFFECT_TIME,
		//death chance for each attack e.g. squash
		DEATH_CHANCE,
		ATTACK_RANGE,
		LIVE_TICK,
		KILL_COUNT,
		HEAL_HEALTH,
		SCARE_RANGE;
		
		public String getName() {
			return new TranslationTextComponent("gui.pvz.almanac."+this.toString().toLowerCase()).getFormattedText();
		}
		
		public int getColor() {
			switch (this) {
//			case SUN_COST: return Colors.YELLOW;
//			case HEALTH:
//			case ATTACK_DAMAGE: return Colors.RED;
			default:return Colors.BLACK;
			}
		}
	}

}
