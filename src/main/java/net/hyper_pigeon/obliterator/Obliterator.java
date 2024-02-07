package net.hyper_pigeon.obliterator;

import net.fabricmc.api.ModInitializer;
import net.hyper_pigeon.obliterator.enchantment.ObliterateEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Obliterator implements ModInitializer {

    public static Enchantment OBLITERATE;

    @Override
    public void onInitialize() {
        OBLITERATE = Registry.register(Registry.ENCHANTMENT,
                new Identifier("obliterator", "obliterate"),
                new ObliterateEnchantment(Enchantment.Rarity.RARE,
                        EquipmentSlot.MAINHAND));
    }


}
