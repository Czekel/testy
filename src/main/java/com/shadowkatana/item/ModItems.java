package com.shadowkatana.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SHADOW_KATANA = new ShadowKatanaItem(
            new Item.Settings()
                    .sword(ToolMaterial.NETHERITE, 3.0f, -2.4f)
                    .maxDamage(2031)
    );

    public static void register() {
        Registry.register(Registries.ITEM, Identifier.of("shadowkatana", "shadow_katana"), SHADOW_KATANA);
    }
}
