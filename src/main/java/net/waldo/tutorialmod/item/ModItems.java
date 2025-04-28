package net.waldo.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.waldo.tutorialmod.Tutorialmod;

import java.util.List;
import java.util.Optional;

import static net.minecraft.item.ArmorMaterials.*;
import static net.minecraft.item.ArmorItem.Type.*;

public class ModItems {
    public static final Item PINK_GARNET = registerItem( "pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem( "raw_pink_garnet", new Item(new Item.Settings()));
    public static final Item ALI = registerItem( "ali", new Item(new Item.Settings().food(new FoodComponent(1,1,true,1, Optional.empty(), List.of()))));
    public static final Item TIME_WASTER = registerItem("time_waster", new Item(new Item.Settings()));
    public static final Item JAMES_WIG = registerItem("james_wig", new ArmorItem(DIAMOND,HELMET,new Item.Settings()));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Tutorialmod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Tutorialmod.LOGGER.info("Registering Mod Items for " + Tutorialmod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
            entries.add(TIME_WASTER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(ALI);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(JAMES_WIG);
        });
    }
}
