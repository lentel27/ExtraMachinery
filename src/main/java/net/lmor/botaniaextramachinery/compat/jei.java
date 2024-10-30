package net.lmor.botaniaextramachinery.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.lmor.botaniaextramachinery.ExtraMachinery;
import net.lmor.botaniaextramachinery.ModBlocks;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketAdvanced;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketBase;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketUpgraded;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryAdvanced;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryBase;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryUpgraded;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyAdvanced;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyBase;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalDaisy.ScreenDaisyUpgraded;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryAdvanced;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryBase;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalIndustrialAgglomerationFactory.ScreenIndustrialAgglomerationFactoryUpgraded;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolAdvanced;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolBase;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalManaPool.ScreenManaPoolUpgraded;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidAdvanced;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidBase;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalOrechid.ScreenOrechidUpgraded;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarAdvanced;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarBase;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarUltimate;
import net.lmor.botaniaextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarUpgraded;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.integration.jei.*;
import vazkii.botania.client.integration.jei.orechid.OrechidRecipeCategory;
import vazkii.botania.common.crafting.OrechidRecipe;

import javax.annotation.Nonnull;

@JeiPlugin
public class jei implements IModPlugin {
    public jei() {
    }

    @Nonnull
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ExtraMachinery.getInstance().modid, "jei_plugin");
    }

    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        registration.addRecipeClickArea(ScreenManaPoolBase.class, 79, 41, 35, 11, ManaPoolRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenManaPoolUpgraded.class, 79, 41, 35, 11, ManaPoolRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenManaPoolAdvanced.class, 79, 41, 35, 11, ManaPoolRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenManaPoolUltimate.class, 70, 45, 35, 11, ManaPoolRecipeCategory.TYPE);

        registration.addRecipeClickArea(ScreenRunicAltarBase.class, 87, 34, 11, 37, RunicAltarRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenRunicAltarUpgraded.class, 87, 34, 11, 37, RunicAltarRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenRunicAltarAdvanced.class, 87, 34, 11, 37, RunicAltarRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenRunicAltarUltimate.class, 87, 34, 11, 37, RunicAltarRecipeCategory.TYPE);

        registration.addRecipeClickArea(ScreenDaisyBase.class, 84, 66, 16, 16, PureDaisyRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenDaisyUpgraded.class, 84, 66, 16, 16, PureDaisyRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenDaisyAdvanced.class, 84, 66, 16, 16, PureDaisyRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenDaisyUltimate.class, 84, 66, 16, 16, PureDaisyRecipeCategory.TYPE);

        registration.addRecipeClickArea(ScreenApothecaryBase.class, 87, 34, 11, 37, PetalApothecaryRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenApothecaryUpgraded.class, 87, 34, 11, 37, PetalApothecaryRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenApothecaryAdvanced.class, 87, 34, 11, 37, PetalApothecaryRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenApothecaryUltimate.class, 87, 34, 11, 37, PetalApothecaryRecipeCategory.TYPE);

        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryBase.class, 72, 49, 40, 16, TerrestrialAgglomerationRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryUpgraded.class, 72, 49, 40, 16, TerrestrialAgglomerationRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryAdvanced.class, 72, 49, 40, 16, TerrestrialAgglomerationRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenIndustrialAgglomerationFactoryUltimate.class, 72, 67, 40, 16, TerrestrialAgglomerationRecipeCategory.TYPE);

        registration.addRecipeClickArea(ScreenAlfheimMarketBase.class, 84, 39, 16, 16, ElvenTradeRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenAlfheimMarketUpgraded.class, 84, 39, 16, 16, ElvenTradeRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenAlfheimMarketAdvanced.class, 84, 45, 16, 16, ElvenTradeRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenAlfheimMarketUltimate.class, 84, 46, 16, 16, ElvenTradeRecipeCategory.TYPE);

        registration.addRecipeClickArea(ScreenOrechidBase.class, 65, 66, 54, 16, OrechidRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenOrechidUpgraded.class, 65, 66, 54, 16, OrechidRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenOrechidAdvanced.class, 65, 83, 54, 16, OrechidRecipeCategory.TYPE);
        registration.addRecipeClickArea(ScreenOrechidUltimate.class, 65, 83, 54, 16, OrechidRecipeCategory.TYPE);

    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseManaPool), ManaPoolRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedManaPool), ManaPoolRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedManaPool), ManaPoolRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateManaPool), ManaPoolRecipeCategory.TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseRunicAltar), RunicAltarRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedRunicAltar), RunicAltarRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedRunicAltar), RunicAltarRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateRunicAltar), RunicAltarRecipeCategory.TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseDaisy), PureDaisyRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedDaisy), PureDaisyRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedDaisy), PureDaisyRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateDaisy), PureDaisyRecipeCategory.TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseApothecary), PetalApothecaryRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedApothecary), PetalApothecaryRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedApothecary), PetalApothecaryRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateApothecary), PetalApothecaryRecipeCategory.TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseIndustrialAgglomerationFactory), TerrestrialAgglomerationRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedIndustrialAgglomerationFactory), TerrestrialAgglomerationRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedIndustrialAgglomerationFactory), TerrestrialAgglomerationRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateIndustrialAgglomerationFactory), TerrestrialAgglomerationRecipeCategory.TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseAlfheimMarket), ElvenTradeRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedAlfheimMarket), ElvenTradeRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedAlfheimMarket), ElvenTradeRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateAlfheimMarket), ElvenTradeRecipeCategory.TYPE);

        registration.addRecipeCatalyst(new ItemStack(ModBlocks.baseOrechid), OrechidRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.upgradedOrechid), OrechidRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.advancedOrechid), OrechidRecipeCategory.TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ultimateOrechid), OrechidRecipeCategory.TYPE);
    }
}
