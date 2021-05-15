package com.hungteen.pvz.gui.screen;

import java.util.Arrays;
import java.util.List;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.appease.GatlingPeaEntity;
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
import com.hungteen.pvz.entity.plant.light.TwinSunFlowerEntity;
import com.hungteen.pvz.entity.plant.magic.CoffeeBeanEntity;
import com.hungteen.pvz.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.entity.plant.magic.MariGoldEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.entity.plant.toxic.PuffShroomEntity;
import com.hungteen.pvz.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.gui.container.AlmanacContainer;
import com.hungteen.pvz.gui.search.SearchCategories;
import com.hungteen.pvz.gui.search.SearchOption;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlmanacScreen extends AbstractOptionScreen<AlmanacContainer> {

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/gui/container/almanac.png");
	private int propertyCnt;
	
	public AlmanacScreen(AlmanacContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 240;
		this.imageHeight = 200;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderAlmanac(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderTooltip(stack, mouseX, mouseY);
	}
	
	@Override
	protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
	}
	
	protected void renderAlmanac(MatrixStack stack){
		if(! this.searchGui.getCurrentOption().isPresent()) return ;
		SearchOption option = this.searchGui.getCurrentOption().get();
		this.renderTitle(stack, option);
		if(! this.isOptionUnLocked(option)) return ;
		this.renderLogo(stack, option);
		this.renderXpBar(stack, option);
		this.renderShortInfo(stack, option);
		option.getPlant().ifPresent((plant) -> {
			int lvl = ClientPlayerResources.getPlayerPlantCardLvl(plant);
//			int maxLvl = PlantUtil.getPlantMaxLvl(plant);
			this.propertyCnt = 0;
			@SuppressWarnings("resource")
			World world = Minecraft.getInstance().player.level;
			this.drawProperty(stack, Properties.SUN_COST, PlantUtil.getPlantSunCost(plant));
			this.drawProperty(stack, Properties.COOL_DOWN, PlantUtil.getPlantCoolDownTime(plant, lvl));
			if(plant.isBlockPlant) {
				return ;
			} else if(plant.isOuterPlant) {
				this.drawProperty(stack, Properties.HEALTH, PlantUtil.PUMPKIN_LIFE);
				return ;
			}
			PVZPlantEntity plantEntity =  PlantUtil.getPlantEntity(world, plant);
			plantEntity.setPlantLvl(lvl);
			this.drawProperty(stack, Properties.HEALTH, plantEntity.getPlantHealth());
			switch(plant) {
			case PEA_SHOOTER:{
				PeaShooterEntity peaShooter = (PeaShooterEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, peaShooter.getAttackDamage());
				this.drawProperty(stack, Properties.BULLET_SPEED, peaShooter.getBulletSpeed());
				break;
			}
			case SUN_FLOWER:{
				SunFlowerEntity sunFlower = (SunFlowerEntity) plantEntity;
				this.drawProperty(stack, Properties.GEN_AMOUNT, sunFlower.getSunAmount());
				break;
			}
			case CHERRY_BOMB:{
				CherryBombEntity cherryBomb = (CherryBombEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, cherryBomb.getAttackDamage());
				this.drawProperty(stack, Properties.ATTACK_RANGE, cherryBomb.getExpRange());
				break;
			}
			case WALL_NUT:{
				break;
			}
			case POTATO_MINE:{
				PotatoMineEntity potatoMine = (PotatoMineEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, potatoMine.getAttackDamage());
				this.drawProperty(stack, Properties.PRE_TIME, potatoMine.getReadyTime());
				break;
			}
			case SNOW_PEA:{
				SnowPeaEntity snowPea = (SnowPeaEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, snowPea.getAttackDamage());
				this.drawProperty(stack, Properties.BULLET_SPEED, snowPea.getBulletSpeed());
				this.drawProperty(stack, Properties.COLD_EFFECT, snowPea.getColdEffect().getAmplifier());
				this.drawProperty(stack, Properties.COLD_EFFECT_TIME, snowPea.getColdEffect().getDuration());
				break;
			}
			case CHOMPER:{
				ChomperEntity chomper = (ChomperEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, chomper.getAttackDamage());
				this.drawProperty(stack, Properties.ATTACK_CD, chomper.getRestCD());
				break;
			}
			case REPEATER:{
				RepeaterEntity repeater = (RepeaterEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, repeater.getAttackDamage());
				this.drawProperty(stack, Properties.BULLET_SPEED, repeater.getBulletSpeed());
				break;
			}
			case PUFF_SHROOM:{
				PuffShroomEntity puffShroom = (PuffShroomEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, puffShroom.getAttackDamage());
				this.drawProperty(stack, Properties.LIVE_TICK, puffShroom.getMaxLiveTick());
				break;
			}
			case SUN_SHROOM:{
				SunShroomEntity sunShroom = (SunShroomEntity) plantEntity;
				this.drawProperty(stack, Properties.GEN_AMOUNT, sunShroom.getSunAmount());
				break;
			}
			case FUME_SHROOM:{
				FumeShroomEntity fumeShroom = (FumeShroomEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, fumeShroom.getAttackDamage());
				break;
			}
			case GRAVE_BUSTER:{
				GraveBusterEntity graveBuster = (GraveBusterEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_CD, graveBuster.getAttackCD());
				this.drawProperty(stack, Properties.KILL_COUNT, graveBuster.getMaxKillCnt());
				break;
			}
			case HYPNO_SHROOM:{
				HypnoShroomEntity hypnoShroom = (HypnoShroomEntity) plantEntity;
				this.drawProperty(stack, Properties.HEAL_HEALTH, hypnoShroom.getHealHealth());
				break;
			}
			case SCAREDY_SHROOM:{
				ScaredyShroomEntity scaredyShroom = (ScaredyShroomEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, scaredyShroom.getAttackDamage());
				this.drawProperty(stack, Properties.SCARE_RANGE, scaredyShroom.getScareDistance());
				break;
			}
			case ICE_SHROOM:{
				IceShroomEntity iceShroom = (IceShroomEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, iceShroom.getAttackDamage());
				this.drawProperty(stack, Properties.FROZEN_EFFECT_TIME, iceShroom.getFrozenEffect().getDuration());
				this.drawProperty(stack, Properties.COLD_EFFECT, iceShroom.getColdEffect().getAmplifier());
				this.drawProperty(stack, Properties.COLD_EFFECT_TIME, iceShroom.getColdEffect().getDuration());
				this.drawProperty(stack, Properties.ATTACK_RANGE, iceShroom.getAttackRange());
				break;
			}
			case DOOM_SHROOM:{
				DoomShroomEntity doomShroom = (DoomShroomEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, doomShroom.getAttackDamage());
				this.drawProperty(stack, Properties.ATTACK_RANGE, doomShroom.getAttackRange());
				break;
			}
			case LILY_PAD:{
				break;
			}
			case SQUASH:{
				SquashEntity squash = (SquashEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, squash.getAttackDamage());
				this.drawProperty(stack, Properties.DEATH_CHANCE, squash.getDeathChance() * 1.0f / 100);
				break;
			}
			case THREE_PEATER:{
				ThreePeaterEntity threePeater = (ThreePeaterEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, threePeater.getAttackDamage());
				this.drawProperty(stack, Properties.BULLET_SPEED, threePeater.getBulletSpeed());
				break;
			}
			case TANGLE_KELP:{
				TangleKelpEntity tangleKelp = (TangleKelpEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, tangleKelp.getAttackDamage());
				break;
			}
			case JALAPENO:{
				JalapenoEntity jalapeno = (JalapenoEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, jalapeno.getAttackDamage());
				this.drawProperty(stack, Properties.ATTACK_RANGE, jalapeno.getFireRange());
				break;
			}
			case SPIKE_WEED:{
				SpikeWeedEntity spikeWeed = (SpikeWeedEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, spikeWeed.getAttackDamage());
				this.drawProperty(stack, Properties.ATTACK_CD, spikeWeed.getAttackCD());
				break;
			}
			case TORCH_WOOD:{
				break;
			}
			case TALL_NUT:{
				break;
			}
			case PUMPKIN:{
				break;
			}
			case COFFEE_BEAN:{
				CoffeeBeanEntity coffeeBean = (CoffeeBeanEntity) plantEntity;
				this.drawProperty(stack, Properties.DURATION, coffeeBean.getAwakeTime());
				break;
			}
			case MARIGOLD:{
				MariGoldEntity marigold = (MariGoldEntity) plantEntity;
				this.drawProperty(stack, Properties.AVE_GEN_AMOUNT, marigold.getAveGenAmount());
				break;
			}
			case GATLING_PEA:{
				GatlingPeaEntity gatlingPea = (GatlingPeaEntity) plantEntity;
				this.drawProperty(stack, Properties.ATTACK_DAMAGE, gatlingPea.getAttackDamage());
				this.drawProperty(stack, Properties.BULLET_SPEED, gatlingPea.getBulletSpeed());
				break;
			}
			case TWIN_SUNFLOWER:{
				TwinSunFlowerEntity twinSunflower = (TwinSunFlowerEntity) plantEntity;
				this.drawProperty(stack, Properties.GEN_AMOUNT, twinSunflower.getSunAmount() * 2);
				break;
			}
			case WATER_GUARD:{
				break;
			}
			default:{
				System.out.println("Warning: forget to fill the almanac info");
				break;
			}
			}
		});
		option.getZombie().ifPresent((zombie) -> {
			
		});
		if(option.isPlayer()){//for player
    		int sunNum = ClientPlayerResources.getPlayerStats(Resources.SUN_NUM);
    		int energyNum = ClientPlayerResources.getPlayerStats(Resources.ENERGY_NUM);
    		int money = ClientPlayerResources.getPlayerStats(Resources.MONEY);
    		int gemNum = ClientPlayerResources.getPlayerStats(Resources.GEM_NUM);
    		this.propertyCnt = 0;
    		this.drawProperty(stack, Properties.SUN_NUM, sunNum);
    		this.drawProperty(stack, Properties.ENERGY_NUM, energyNum);
    		this.drawProperty(stack, Properties.MONEY, money);
    		this.drawProperty(stack, Properties.GEM_NUM, gemNum);
		}
	}
	
	protected void drawProperty(MatrixStack stack, Properties prop, float num) {
		this.propertyCnt ++;
		int posX = this.leftPos + 9 + 2 + ((this.propertyCnt & 1) == 1 ? 0 : 110);
		int posY = this.topPos + 92 + 2 + ((this.propertyCnt - 1) / 2) * 18;
		String string = prop.getName()+": ";
		int floorNum = MathHelper.fastFloor(num);
		if(floorNum == num) {
			string += floorNum;
		} else {
			string += num;
		}
		StringUtil.drawScaledString(stack, font, string, posX, posY, prop.getColor(), 1f);
	}
	
	protected void renderShortInfo(MatrixStack stack, SearchOption a) {
		if(a.isPlayer()) return ;
		stack.pushPose();
//		int posX = this.guiLeft + 82 + 150 / 2;
//		int posY = this.guiTop + 26 + 2;
//		for(int i=0;i<4;i++) {
//			StringUtil.drawCenteredScaledString(font, new TranslationTextComponent("gui.pvz.almanac."+a.toString().toLowerCase()+(i+1)).getFormattedText(), posX, posY, Colors.DARK_BLUE, 0.7f);
//			posY += 12;
//		}
		stack.popPose();
	}
	
	protected void renderXpBar(MatrixStack stack, SearchOption a) {
		stack.pushPose();
		int maxLvl = 1, lvl = 1;
		Plants p = null;
		if(a.isPlant()) {
			p = a.getPlant().get();
			maxLvl = PlantUtil.getPlantMaxLvl(p);
			lvl = ClientPlayerResources.getPlayerPlantCardLvl(p);
		} else if(a.isPlayer()) {
			maxLvl = PlayerUtil.MAX_TREE_LVL;
			lvl = ClientPlayerResources.getPlayerStats(Resources.TREE_LVL);
		} else {
		}
		String lvlInfo = Properties.LVL.getName() + ":" + lvl;
		int barWidth = 62, barHeight = 9;
		int len = barWidth;
		if(maxLvl != lvl && p != null) { 
			int xp = ClientPlayerResources.getPlayerPlantCardXp(p);
			int maxXp = PlantUtil.getPlantLevelUpXp(p, lvl);
			len = RenderUtil.getRenderBarLen(xp, maxXp, barWidth);
		} else if(a.isPlayer()) {
			int xp = ClientPlayerResources.getPlayerStats(Resources.TREE_XP);
			int maxXp = PlayerUtil.getPlayerLevelUpXp(lvl);
			len = RenderUtil.getRenderBarLen(xp, maxXp, barWidth);
		}
		int texX = 0;
		int texY = (maxLvl == lvl ? 210 : 200);
		stack.pushPose();
		stack.translate(0, 0, 100);
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos + 9, this.topPos + 66, texX, texY, len, barHeight);
		StringUtil.drawCenteredScaledString(stack, this.font, lvlInfo, this.leftPos + 9 + barWidth / 2, this.topPos + 66 + 1, Colors.BLACK, 1f);
		stack.popPose();
		stack.popPose();
	}
	
	protected void renderTitle(MatrixStack stack, SearchOption a) {
		stack.pushPose();
		int dx = this.leftPos + 82 + 150 / 2, dy = this.topPos + 10 + 2;
		StringUtil.drawCenteredScaledString(stack, this.font, SearchOption.getOptionName(a).getString(), dx, dy, Colors.DARK_GREEN, 1.6f);
		stack.popPose();
	}
	
	protected void renderLogo(MatrixStack stack, SearchOption a) {
		int dx = this.leftPos + 16, dy = this.topPos + 14;
		int scale = 3;
		stack.pushPose();
		stack.scale(scale, scale, scale);
		stack.translate((dx % scale) * 1.0d / scale , (dy % scale) * 1.0d / scale, 0);
		this.itemRenderer.renderGuiItem(SearchOption.getItemStackByOption(a), dx / scale, dy / scale);
		stack.popPose();
	}

	@Override
	protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
		this.minecraft.getTextureManager().bind(TEXTURE);
		blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

	private enum Properties{
		LVL,
		HEALTH,
		SUN_COST,
		COOL_DOWN,
		ATTACK_DAMAGE,
		GEN_TIME,
		GEN_AMOUNT,
		AVE_GEN_AMOUNT,
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
		SCARE_RANGE,
		DURATION;
		
		public String getName() {
			return new TranslationTextComponent("gui.pvz.almanac."+this.toString().toLowerCase()).getString();
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

	@Override
	public boolean isOptionUnLocked(SearchOption option) {
		return ClientPlayerResources.isAlmanacUnLocked(option);
	}

	@Override
	public List<SearchCategories> getSearchCategories() {
		return Arrays.asList(SearchCategories.ALL, SearchCategories.PLANTS, SearchCategories.ZOMBIES);
	}

}
