package net.lmor.botanicalextramachinery.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidUpgraded;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarAdvanced;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarUpgraded;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.integration.jei.*;
import vazkii.botania.client.integration.jei.orechid.OrechidRecipeCategory;

import javax.annotation.Nonnull;

@JeiPlugin
public class jei implements IModPlugin {
    public jei() {
    }

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ExtraMachinery.getInstance().modid, "jei_plugin");
    }

    @Override
    @SuppressWarnings("removal")
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        registration.addRecipeClickArea(ScreenManaPoolBase.class, 79, 38, 35, 11, ManaPoolRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenManaPoolUpgraded.class, 79, 38, 35, 11, ManaPoolRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenManaPoolAdvanced.class, 79, 38, 35, 11, ManaPoolRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenManaPoolUltimate.class, 70, 42, 35, 11, ManaPoolRecipeCategory.UID);

        registration.addRecipeClickArea(ScreenRunicAltarBase.class, 87, 31, 11, 37, RunicAltarRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenRunicAltarUpgraded.class, 87, 31, 11, 37, RunicAltarRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenRunicAltarAdvanced.class, 87, 31, 11, 37, RunicAltarRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenRunicAltarUltimate.class, 87, 31, 11, 37, RunicAltarRecipeCategory.UID);

        registration.addRecipeClickArea(ScreenDaisyBase.class, 84, 64, 16, 16, PureDaisyRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenDaisyUpgraded.class, 84, 64, 16, 16, PureDaisyRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenDaisyAdvanced.class, 84, 64, 16, 16, PureDaisyRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenDaisyUltimate.class, 84, 64, 16, 16, PureDaisyRecipeCategory.UID);

        registration.addRecipeClickArea(ScreenApothecaryBase.class, 87, 31, 11, 37, PetalApothecaryRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenApothecaryUpgraded.class, 87, 31, 11, 37, PetalApothecaryRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenApothecaryAdvanced.class, 87, 31, 11, 37, PetalApothecaryRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenApothecaryUltimate.class, 87, 31, 11, 37, PetalApothecaryRecipeCategory.UID);

        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryBase.class, 72, 40, 40, 16, TerraPlateRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryUpgraded.class, 72, 40, 40, 16, TerraPlateRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryAdvanced.class, 72, 40, 40, 16, TerraPlateRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryUltimate.class, 72, 58, 40, 16, TerraPlateRecipeCategory.UID);

        registration.addRecipeClickArea(ScreenAlfheimMarketBase.class, 84, 36, 16, 16, ElvenTradeRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenAlfheimMarketUpgraded.class, 84, 36, 16, 16, ElvenTradeRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenAlfheimMarketAdvanced.class, 84, 42, 16, 16, ElvenTradeRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenAlfheimMarketUltimate.class, 84, 42, 16, 16, ElvenTradeRecipeCategory.UID);

        registration.addRecipeClickArea(ScreenOrechidBase.class, 65, 63, 54, 16, OrechidRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenOrechidUpgraded.class, 65, 63, 54, 16, OrechidRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenOrechidAdvanced.class, 65, 80, 54, 16, OrechidRecipeCategory.UID);
        registration.addRecipeClickArea(ScreenOrechidUltimate.class, 65, 80, 54, 16, OrechidRecipeCategory.UID);

    }

    @Override
    @SuppressWarnings("removal")
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseManaPool), ManaPoolRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedManaPool), ManaPoolRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedManaPool), ManaPoolRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateManaPool), ManaPoolRecipeCategory.UID);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseRunicAltar), RunicAltarRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedRunicAltar), RunicAltarRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedRunicAltar), RunicAltarRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateRunicAltar), RunicAltarRecipeCategory.UID);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseDaisy), PureDaisyRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedDaisy), PureDaisyRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedDaisy), PureDaisyRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateDaisy), PureDaisyRecipeCategory.UID);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseApothecary), PetalApothecaryRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedApothecary), PetalApothecaryRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedApothecary), PetalApothecaryRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateApothecary), PetalApothecaryRecipeCategory.UID);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseIndustrialAgglomerationFactory), TerraPlateRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedIndustrialAgglomerationFactory), TerraPlateRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedIndustrialAgglomerationFactory), TerraPlateRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateIndustrialAgglomerationFactory), TerraPlateRecipeCategory.UID);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseAlfheimMarket), ElvenTradeRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedAlfheimMarket), ElvenTradeRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedAlfheimMarket), ElvenTradeRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateAlfheimMarket), ElvenTradeRecipeCategory.UID);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseOrechid), OrechidRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedOrechid), OrechidRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedOrechid), OrechidRecipeCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateOrechid), OrechidRecipeCategory.UID);
    }
}
