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
import net.waldo.tutorialmod.item.custom.ChiselItem;
import net.waldo.tutorialmod.item.custom.TimeWasterItem;

import java.util.List;
import java.util.Optional;

import static net.minecraft.item.ArmorMaterials.*;
import static net.minecraft.item.ArmorItem.Type.*;

public class ModItems {
    //Standard Items
    public static final Item PINK_GARNET = registerItem( "pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem( "raw_pink_garnet", new Item(new Item.Settings()));
    //Armor
    public static final Item JAMES_WIG = registerItem("james_wig", new ArmorItem(DIAMOND,HELMET,new Item.Settings()));
    //Utility Items
    public static final Item TIME_WASTER = registerItem("time_waster", new TimeWasterItem(new Item.Settings().maxDamage(10).maxCount(1)));
    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Tutorialmod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Tutorialmod.LOGGER.info("Registering Mod Items for " + Tutorialmod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
            entries.add(TIME_WASTER);
            entries.add(CHISEL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(JAMES_WIG);
        });
    }
}
