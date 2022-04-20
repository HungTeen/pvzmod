package com.hungteen.pvz.data.tag;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.item.misc.TemplateCardItem;
import com.hungteen.pvz.common.tag.PVZBlockTags;
import com.hungteen.pvz.common.tag.PVZItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:44
 **/
public class ItemTagGen extends ItemTagsProvider {

    public ItemTagGen(DataGenerator generatorIn, BlockTagsProvider provider, ExistingFileHelper helper) {
        super(generatorIn, provider, PVZMod.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        /*
        Minecraft Tags
         */
//        this.tag(PVZItemTags.ARROWS).add(ItemRegister.TARGET_ARROW.get());

        /*
        Forge Tags.
         */
        this.tag(PVZItemTags.PEAS).add(PVZItems.PEA.get());
        this.tag(PVZItemTags.NUTS).add(PVZItems.NUT.get());
//        this.tag(PVZItemTags.CHILIPEPPERS).add(ItemRegister.PEPPER.get());
        this.tag(PVZItemTags.CABBAGES).add(PVZItems.CABBAGE.get());
        this.tag(PVZItemTags.CABBAGE_SEEDS).add(PVZItems.CABBAGE_SEEDS.get());
        this.tag(PVZItemTags.CORNS).add(PVZItems.CORN.get());
        this.tag(PVZItemTags.CORN_SEEDS).add(PVZItems.CORN_SEEDS.get());
        this.tag(PVZItemTags.AMETHYST_INGOTS).add(PVZItems.AMETHYST_INGOT.get());
        this.tag(PVZItemTags.AMETHYST_ORES).add(PVZBlocks.AMETHYST_ORE.get().asItem());

        /*
        PVZ Tags.
         */
        //Essences.
        PVZAPI.get().getEssenceTypes().forEach(e -> {
            this.tag(PVZItemTags.ESSENCES).add(e.getEssenceItem());
        });

        //Cards.
        PVZAPI.get().getPAZTypes().forEach(type -> {
            if(type instanceof IPlantType){
                type.getSummonCard().ifPresent(card -> {
                    this.tag(PVZItemTags.PLANT_SUMMON_CARDS).add(card);
                });
                type.getEnjoyCard().ifPresent(card -> {
                    this.tag(PVZItemTags.PLANT_ENJOY_CARDS).add(card);
                });
            }
        });
        this.tag(PVZItemTags.PLANT_CARDS).addTags(PVZItemTags.PLANT_SUMMON_CARDS).addTags(PVZItemTags.PLANT_ENJOY_CARDS);
        this.tag(PVZItemTags.ALL_CARDS).addTags(PVZItemTags.PLANT_CARDS);
        this.tag(PVZItemTags.TEMPLATE_CARDS).add(this.getFilterItems(i -> i instanceof TemplateCardItem));

        //Card Types.
        PVZAPI.get().getCardTypes().forEach(card -> {
            if (card.getCardTag() != null && card.isReplaceable()) {
                this.tag(card.getCardTag()).add(PVZItems.MEGA_CARD.get()).add(card.getTemplateCard());
            }
        });

        //Rank Materials
        this.tag(PVZItemTags.WHITE_MATERIALS).add(Items.IRON_INGOT).add(Items.QUARTZ);
        this.tag(PVZItemTags.GOLD_MATERIALS).add(Items.GOLD_INGOT);
        this.tag(PVZItemTags.BLUE_MATERIALS).add(Items.DIAMOND).add(Items.LAPIS_BLOCK).add(Items.PRISMARINE_CRYSTALS);
        this.tag(PVZItemTags.BLACK_MATERIALS).add(Items.NETHERITE_SCRAP);
//        for (Item item : ForgeRegistries.ITEMS) {
//            if (item instanceof BlockItem) {
//                Block b = ((BlockItem) item).getBlock();
//                if (b instanceof EssenceOreBlock) {
//                    this.tag(PVZItemTags.ESSENCE_ORES).add(item);
//                }
//            }
//        }

        //        this.tag(PVZItemTags.REACH_ITEMS).add(ItemRegister.RESOURCE_COLLECTOR.get());

        this.tag(PVZItemTags.SHIELD_ITEMS).add(Items.SHIELD);
        this.tag(PVZItemTags.ARMOR_ITEMS).add(PVZItems.BUCKET_HEAD.get());
        this.tag(PVZItemTags.PEA_GUN_BULLETS).add(PVZItems.PEA.get()).add(PVZItems.SNOW_PEA.get()).add(PVZItems.FLAME_PEA.get());

    }

    private Item[] getFilterItems(Predicate<Item> predicate) {
        return registry.stream()
                .filter(predicate)
                .sorted(Comparator.comparing(ForgeRegistries.ITEMS::getKey))
                .toArray(Item[]::new);
    }

    @Override
    public String getName() {
        return "Plants vs Zombies item tags";
    }

}