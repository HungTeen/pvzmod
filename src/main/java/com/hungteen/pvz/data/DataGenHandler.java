package com.hungteen.pvz.data;

import com.hungteen.pvz.data.loot.LootTableGen;
import com.hungteen.pvz.data.tag.BlockTagGen;
import com.hungteen.pvz.data.tag.EntityTagGen;
import com.hungteen.pvz.data.tag.ItemTagGen;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:39
 **/
public class DataGenHandler {

    public static void dataGen(GatherDataEvent ev) {
        ExistingFileHelper helper = ev.getExistingFileHelper();
        if(ev.includeServer()) {
            //for tags.
            final BlockTagGen generator = new BlockTagGen(ev.getGenerator(), helper);
            ev.getGenerator().addProvider(generator);
            ev.getGenerator().addProvider(new ItemTagGen(ev.getGenerator(), generator, helper));
            ev.getGenerator().addProvider(new EntityTagGen(ev.getGenerator(), helper));

//            //for recipes.
//            ev.getGenerator().addProvider(new RecipeGenerator(ev.getGenerator()));
            //for loot tables.
            ev.getGenerator().addProvider(new LootTableGen(ev.getGenerator()));
        }
        if(ev.includeClient()) {
            ///for language
//			ev.getGenerator().addProvider(new CNLanguageGenerator(ev.getGenerator()));
//			ev.getGenerator().addProvider(new USLanguageGenerator(ev.getGenerator()));
            //for model
            ev.getGenerator().addProvider(new ItemModelGen(ev.getGenerator(), helper));
//			ev.getGenerator().addProvider(new BlockModelGenerator(ev.getGenerator(), helper));
            //for block state
//			ev.getGenerator().addProvider(new BlockStateGenerator(ev.getGenerator(), helper));
        }
    }
}
