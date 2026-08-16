package com.shadowkatana.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    private static final Identifier ID = Identifier.of("shadowkatana", "shadow_katana");
    private static final RegistryKey<Item> KEY = RegistryKey.of(RegistryKeys.ITEM, ID);

    public static final Item SHADOW_KATANA = new ShadowKatanaItem(
            new Item.Settings()
                    .registryKey(KEY)
                    .sword(ToolMaterial.NETHERITE, 3.0f, -2.4f)
                    .maxDamage(2031)
    );

    public static void register() {
        Registry.register(Registries.ITEM, KEY, SHADOW_KATANA);
    }
}
