package net.lmor.botanicalextramachinery.events;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityCrimsonManaSpark;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityMalachiteManaSpark;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntitySaffronManaSpark;
import net.lmor.botanicalextramachinery.entities.manaSpark.EntityShadowManaSpark;
import net.lmor.botanicalextramachinery.util.NumberFormatter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static net.lmor.botanicalextramachinery.ModBlocks.*;
import static net.lmor.botanicalextramachinery.ModBlocks.jadedAmaranthus;
import static net.lmor.botanicalextramachinery.ModItems.*;
import static net.lmor.botanicalextramachinery.ModItems.crimsonSpark;

@Mod.EventBusSubscriber(modid = ExtraMachinery.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TooltipHandler {

    public static final List<Item> tooltipManaAllBlocks = new ArrayList<>();
    public static final List<Item> tooltipWaterAllBlocks = new ArrayList<>();
    public static final List<Item> tooltipSparks = new ArrayList<>();

    private static void addItem(){
        tooltipManaAllBlocks.add(baseManaPool.asItem());
        tooltipManaAllBlocks.add(upgradedManaPool.asItem());
        tooltipManaAllBlocks.add(advancedManaPool.asItem());
        tooltipManaAllBlocks.add(ultimateManaPool.asItem());

        tooltipManaAllBlocks.add(baseRunicAltar.asItem());
        tooltipManaAllBlocks.add(upgradedRunicAltar.asItem());
        tooltipManaAllBlocks.add(advancedRunicAltar.asItem());
        tooltipManaAllBlocks.add(ultimateRunicAltar.asItem());

        tooltipManaAllBlocks.add(baseIndustrialAgglomerationFactory.asItem());
        tooltipManaAllBlocks.add(upgradedIndustrialAgglomerationFactory.asItem());
        tooltipManaAllBlocks.add(advancedIndustrialAgglomerationFactory.asItem());
        tooltipManaAllBlocks.add(ultimateIndustrialAgglomerationFactory.asItem());

        tooltipManaAllBlocks.add(baseAlfheimMarket.asItem());
        tooltipManaAllBlocks.add(ultimateAlfheimMarket.asItem());
        tooltipManaAllBlocks.add(ultimateAlfheimMarket.asItem());
        tooltipManaAllBlocks.add(ultimateAlfheimMarket.asItem());

        tooltipManaAllBlocks.add(baseOrechid.asItem());
        tooltipManaAllBlocks.add(ultimateOrechid.asItem());
        tooltipManaAllBlocks.add(ultimateOrechid.asItem());
        tooltipManaAllBlocks.add(ultimateOrechid.asItem());

        tooltipManaAllBlocks.add(jadedAmaranthus.asItem());

        tooltipWaterAllBlocks.add(baseApothecary.asItem());
        tooltipWaterAllBlocks.add(upgradedApothecary.asItem());
        tooltipWaterAllBlocks.add(advancedApothecary.asItem());
        tooltipWaterAllBlocks.add(ultimateApothecary.asItem());

        tooltipSparks.add(malachiteSpark);
        tooltipSparks.add(saffronSpark);
        tooltipSparks.add(shadowSpark);
        tooltipSparks.add(crimsonSpark);
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        if (tooltipManaAllBlocks.size() == 0) addItem();

        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty()){
            CompoundTag tag = stack.getTag();

            if (tooltipManaAllBlocks.contains(stack.getItem()) && stack.hasTag()){
                if (tag.getCompound("BlockEntityTag").contains("mana")){
                    int mana = tag.getCompound("BlockEntityTag").getInt("mana");

                    event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.mana", NumberFormatter.formatInteger(mana)));
                }
            }

            else if (tooltipWaterAllBlocks.contains(stack.getItem()) && stack.hasTag()){

                if (tag.getCompound("BlockEntityTag").contains("fluid")){
                    int water = tag.getCompound("BlockEntityTag").getCompound("fluid").getInt("Amount");

                    event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.water", NumberFormatter.formatIntegerWater(water)));
                }
            }

            if (tooltipSparks.contains(stack.getItem().asItem())) {
                int transfer = 0;
                if (stack.getItem() == malachiteSpark) {
                    transfer = EntityMalachiteManaSpark.geRate();
                } else if (stack.getItem() == saffronSpark) {
                    transfer = EntitySaffronManaSpark.geRate();
                } else if (stack.getItem() == shadowSpark) {
                    transfer = EntityShadowManaSpark.geRate();
                } else if (stack.getItem() == crimsonSpark) {
                    transfer = EntityCrimsonManaSpark.geRate();
                }

                if (transfer != 0) {
                    event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.sparkTransfer", NumberFormatter.formatInteger(transfer)));
                }
            }
        }

        if (stack.getItem().asItem() == ModItems.catalystSpeed.asItem()){
            event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.upgrade_speed_1"));
            event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.upgrade_speed_2"));
        }

        if (stack.getItem().asItem() == ModItems.catalystPetal.asItem()){
            event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.catalyst_petal_1"));
            event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.catalyst_petal_3"));
        }

        if (stack.getItem().asItem() == ModItems.catalystPetalBlock.asItem()){
            event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.catalyst_petal_2"));
            event.getToolTip().add(Component.translatable("botanicalextramachinery.tooltip.item.catalyst_petal_3"));
        }
    }
}