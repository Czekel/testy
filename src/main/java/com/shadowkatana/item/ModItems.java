package com.shadowkatana.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SHADOW_KATANA = new ShadowKatanaItem(
            ToolMaterials.NETHERITE,
            new FabricItemSettings().maxDamage(2031)
    );

    public static void register() {
        Registry.register(Registries.ITEM, Identifier.of("shadowkatana", "shadow_katana"), SHADOW_KATANA);
    }
}
