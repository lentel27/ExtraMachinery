package net.lmor.botanicalextramachinery.entities.manaSpark;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.block.IWandHUD;
import vazkii.botania.api.item.ISparkEntity;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.mana.spark.IManaSpark;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.SparkHelper;
import vazkii.botania.api.mana.spark.SparkUpgradeType;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.entity.EntitySparkBase;
import vazkii.botania.common.entity.ModEntities;
import vazkii.botania.common.helper.ColorHelper;
import vazkii.botania.common.helper.VecHelper;
import vazkii.botania.common.item.ItemSparkUpgrade;
import vazkii.botania.common.item.ItemTwigWand;
import vazkii.botania.network.EffectType;
import vazkii.botania.network.clientbound.PacketBotaniaEffect;
import vazkii.botania.xplat.IXplatAbstractions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityManaSparkPattern extends EntitySparkBase implements IManaSpark {

    protected int TRANSFER_RATE;
    private static final String TAG_UPGRADE = "upgrade";
    private static final EntityDataAccessor<Integer> UPGRADE;
    private final Set<IManaSpark> transfers;
    private int removeTransferants;

    static {
        UPGRADE = SynchedEntityData.defineId(EntityManaSparkPattern.class, EntityDataSerializers.INT);
    }


    public EntityManaSparkPattern(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.transfers = Collections.newSetFromMap(new WeakHashMap());
        this.removeTransferants = 2;
    }

    protected Item getSparkItem() {
        return ModItems.malachiteSpark;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(UPGRADE, 0);
    }

    public @NotNull ItemStack getPickResult() {
        return new ItemStack(this.getSparkItem());
    }

    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            ISparkAttachable tile = this.getAttachedTile();
            if (tile == null) {
                this.dropAndKill();
            } else {
                IManaReceiver receiver = this.getAttachedManaReceiver();
                SparkUpgradeType upgrade = this.getUpgrade();
                Collection<IManaSpark> transfers = this.getTransfers();
                List validSparks;
                Iterator var8;
                int spend;
                switch (upgrade) {
                    case DISPERSIVE:
                        validSparks = SparkHelper.getEntitiesAround(Player.class, this.level, this.getX(), this.getY() + (double)this.getBbHeight() / 2.0, this.getZ());
                        Map<Player, Map<IManaItem, Integer>> receivingPlayers = new HashMap();
                        ItemStack input = new ItemStack(this.getSparkItem());
                        var8 = validSparks.iterator();

                        Player player;
                        while(var8.hasNext()) {
                            player = (Player)var8.next();
                            List<ItemStack> stacks = new ArrayList();
                            stacks.addAll(player.getInventory().items);
                            stacks.addAll(player.getInventory().armor);
                            Container inv = BotaniaAPI.instance().getAccessoriesInventory(player);

                            for(spend = 0; spend < inv.getContainerSize(); ++spend) {
                                stacks.add(inv.getItem(spend));
                            }

                            Iterator var27 = stacks.iterator();

                            while(var27.hasNext()) {
                                ItemStack stack = (ItemStack)var27.next();
                                IManaItem manaItem = IXplatAbstractions.INSTANCE.findManaItem(stack);
                                if (!stack.isEmpty() && manaItem != null && manaItem.canReceiveManaFromItem(input)) {
                                    boolean add = false;
                                    Map<IManaItem, Integer> receivingStacks;
                                    if (!receivingPlayers.containsKey(player)) {
                                        add = true;
                                        receivingStacks = new HashMap();
                                    } else {
                                        receivingStacks = receivingPlayers.get(player);
                                    }

                                    int recv = Math.min(receiver.getCurrentMana(), Math.min(TRANSFER_RATE, manaItem.getMaxMana() - manaItem.getMana()));
                                    if (recv > 0) {
                                        (receivingStacks).put(manaItem, recv);
                                        if (add) {
                                            receivingPlayers.put(player, receivingStacks);
                                        }
                                    }
                                }
                            }
                        }

                        if (!receivingPlayers.isEmpty()) {
                            List<Player> keys = new ArrayList(receivingPlayers.keySet());
                            Collections.shuffle(keys);
                            player = keys.iterator().next();
                            Map<IManaItem, Integer> items = receivingPlayers.get(player);
                            Map.Entry<IManaItem, Integer> e = items.entrySet().iterator().next();
                            IManaItem manaItem = e.getKey();
                            int cost = e.getValue();
                            int manaToPut = Math.min(receiver.getCurrentMana(), cost);
                            manaItem.addMana(manaToPut);
                            receiver.receiveMana(-manaToPut);
                            this.particlesTowards(player);
                        }
                        break;
                    case DOMINANT:
                        validSparks = SparkHelper.getSparksAround(this.level, this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.getNetwork()).filter((s) -> {
                            SparkUpgradeType otherUpgrade = s.getUpgrade();
                            return s != this && otherUpgrade == SparkUpgradeType.NONE && s.getAttachedManaReceiver() instanceof IManaPool;
                        }).collect(Collectors.toList());
                        if (validSparks.size() > 0) {
                            ((IManaSpark)validSparks.get(this.level.random.nextInt(validSparks.size()))).registerTransfer(this);
                        }
                        break;
                    case RECESSIVE:
                        Stream iManaSparkStream = SparkHelper.getSparksAround(this.level, this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.getNetwork()).filter((s) -> {
                            SparkUpgradeType otherUpgrade = s.getUpgrade();
                            return s != this && otherUpgrade != SparkUpgradeType.DOMINANT && otherUpgrade != SparkUpgradeType.RECESSIVE && otherUpgrade != SparkUpgradeType.ISOLATED;
                        });
                        Objects.requireNonNull(transfers);
                        iManaSparkStream.forEach(e -> transfers.add((IManaSpark)e));
                }

                if (!transfers.isEmpty()) {
                    int manaTotal = Math.min(TRANSFER_RATE * transfers.size(), receiver.getCurrentMana());
                    int count = transfers.size();
                    int manaSpent = 0;
                    if (manaTotal > 0) {
                        var8 = transfers.iterator();

                        while(var8.hasNext()) {
                            IManaSpark spark = (IManaSpark)var8.next();
                            --count;
                            ISparkAttachable attached = spark.getAttachedTile();
                            IManaReceiver attachedReceiver = spark.getAttachedManaReceiver();
                            if (attached != null && attachedReceiver != null && !attachedReceiver.isFull() && !spark.areIncomingTransfersDone()) {
                                spend = Math.min(attached.getAvailableSpaceForMana(), (manaTotal - manaSpent) / (count + 1));
                                attachedReceiver.receiveMana(spend);
                                manaSpent += spend;
                                this.particlesTowards(spark.entity());
                            }
                        }

                        receiver.receiveMana(-manaSpent);
                    }
                }

                if (this.removeTransferants > 0) {
                    --this.removeTransferants;
                }

                this.filterTransfers();
            }
        }
    }

    private void particlesTowards(Entity e) {
        IXplatAbstractions.INSTANCE.sendToTracking(this, new PacketBotaniaEffect(EffectType.SPARK_MANA_FLOW, this.getX(), this.getY(), this.getZ(), this.getId(), e.getId(), ColorHelper.getColorValue(this.getNetwork())));
    }

    public static void particleBeam(Player player, Entity e1, Entity e2) {
        if (e1 != null && e2 != null && !e1.getLevel().isClientSide) {
            IXplatAbstractions.INSTANCE.sendToPlayer(player, new PacketBotaniaEffect(EffectType.SPARK_NET_INDICATOR, e1.getX(), e1.getY(), e1.getZ(), e1.getId(), e2.getId()));
        }

    }

    private void dropAndKill() {
        SparkUpgradeType upgrade = this.getUpgrade();
        this.spawnAtLocation(new ItemStack(this.getSparkItem()), 0.0F);
        if (upgrade != SparkUpgradeType.NONE) {
            this.spawnAtLocation(ItemSparkUpgrade.getByType(upgrade), 0.0F);
        }

        this.discard();
    }

    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (this.isAlive() && !stack.isEmpty()) {
            SparkUpgradeType upgrade = this.getUpgrade();
            if (stack.getItem() instanceof ItemTwigWand) {
                if (!this.level.isClientSide) {
                    if (player.isShiftKeyDown()) {
                        if (upgrade != SparkUpgradeType.NONE) {
                            this.spawnAtLocation(ItemSparkUpgrade.getByType(upgrade), 0.0F);
                            this.setUpgrade(SparkUpgradeType.NONE);
                            this.transfers.clear();
                            this.removeTransferants = 2;
                        } else {
                            this.dropAndKill();
                        }
                    } else {
                        SparkHelper.getSparksAround(this.level, this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.getNetwork()).forEach((s) -> {
                            particleBeam(player, this, s.entity());
                        });
                    }
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }

            Item item = stack.getItem();
            if (item instanceof ItemSparkUpgrade) {
                ItemSparkUpgrade newUpgrade = (ItemSparkUpgrade)item;
                if (upgrade == SparkUpgradeType.NONE) {
                    if (!this.level.isClientSide) {
                        this.setUpgrade(newUpgrade.type);
                        stack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(this.level.isClientSide);
                }
            }

            if (stack.is(vazkii.botania.common.item.ModItems.phantomInk)) {
                if (!this.level.isClientSide) {
                    this.setInvisible(true);
                }

                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }

            item = stack.getItem();
            if (item instanceof DyeItem) {
                DyeItem dye = (DyeItem)item;
                DyeColor color = dye.getDyeColor();
                if (color != this.getNetwork()) {
                    if (!this.level.isClientSide) {
                        this.setNetwork(color);
                        stack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(this.level.isClientSide);
                }
            }
        }

        return InteractionResult.PASS;
    }

    protected void readAdditionalSaveData(@NotNull CompoundTag cmp) {
        super.readAdditionalSaveData(cmp);
        this.setUpgrade(SparkUpgradeType.values()[cmp.getInt("upgrade")]);
    }

    protected void addAdditionalSaveData(@NotNull CompoundTag cmp) {
        super.addAdditionalSaveData(cmp);
        cmp.putInt("upgrade", this.getUpgrade().ordinal());
    }

    @Override
    public @Nullable ISparkAttachable getAttachedTile() {
        return IXplatAbstractions.INSTANCE.findSparkAttachable(this.getLevel(), this.getAttachPos(), this.getLevel().getBlockState(this.getAttachPos()), this.getLevel().getBlockEntity(this.getAttachPos()), Direction.UP);
    }

    private void filterTransfers() {
        Iterator<IManaSpark> iter = this.transfers.iterator();

        while(true) {
            IManaSpark spark;
            SparkUpgradeType upgr;
            SparkUpgradeType supgr;
            IManaReceiver arecv;
            do {
                if (!iter.hasNext()) {
                    return;
                }

                spark = (IManaSpark)iter.next();
                upgr = this.getUpgrade();
                supgr = spark.getUpgrade();
                arecv = spark.getAttachedManaReceiver();
            } while(spark != this && ((Entity)spark).isAlive() && !spark.areIncomingTransfersDone() && this.getNetwork() == spark.getNetwork() && arecv != null && !arecv.isFull() && (upgr == SparkUpgradeType.NONE && supgr == SparkUpgradeType.DOMINANT || upgr == SparkUpgradeType.RECESSIVE && (supgr == SparkUpgradeType.NONE || supgr == SparkUpgradeType.DISPERSIVE) || !(arecv instanceof IManaPool)));

            iter.remove();
        }
    }

    @Override
    public Collection<IManaSpark> getTransfers() {
        this.filterTransfers();
        return this.transfers;
    }

    private boolean hasTransfer(IManaSpark entity) {
        return this.transfers.contains(entity);
    }

    @Override
    public void registerTransfer(IManaSpark manaSpark) {
        if (!this.hasTransfer(manaSpark)) {
            this.transfers.add(manaSpark);
        }
    }

    @Override
    public SparkUpgradeType getUpgrade() {
        return SparkUpgradeType.values()[(Integer)this.entityData.get(UPGRADE)];
    }

    @Override
    public void setUpgrade(SparkUpgradeType sparkUpgradeType) {
        this.entityData.set(UPGRADE, sparkUpgradeType.ordinal());
    }

    @Override
    public boolean areIncomingTransfersDone() {
        if (this.getAttachedManaReceiver() instanceof IManaPool) {
            return this.removeTransferants > 0;
        } else {
            ISparkAttachable attachable = this.getAttachedTile();
            return attachable != null && attachable.areIncomingTranfersDone();
        }
    }

    public static record WandHud(EntityManaSparkPattern entity) implements IWandHUD {
        public WandHud(EntityManaSparkPattern entity) {
            this.entity = entity;
        }

        public void renderHUD(PoseStack ms, Minecraft mc) {
            ItemStack sparkStack = new ItemStack(this.entity.getSparkItem());
            ItemStack augmentStack = ItemSparkUpgrade.getByType(this.entity.getUpgrade());
            DyeColor networkColor = this.entity.getNetwork();
            String colorName = networkColor.getName();
            Component networkColorName = new TranslatableComponent("color.minecraft." + colorName).withStyle(ChatFormatting.ITALIC);

            int textColor = ColorHelper.getColorValue(networkColor);
            int width = 4 + Collections.max(Arrays.asList(mc.font.width(networkColorName), itemWithNameWidth(mc, sparkStack), itemWithNameWidth(mc, augmentStack)));
            int height = augmentStack.isEmpty() ? 30 : 50;
            int networkColorTextStart = mc.font.width(networkColorName) / 2;
            int centerX = mc.getWindow().getGuiScaledWidth() / 2;
            int centerY = mc.getWindow().getGuiScaledHeight() / 2;

            renderHUDBox(ms, centerX - width / 2, centerY + 8, centerX + width / 2, centerY + 8 + height);
            renderItemWithNameCentered(ms, mc, sparkStack, centerY + 10, textColor);
            renderItemWithNameCentered(ms, mc, augmentStack, centerY + 28, textColor);
            mc.font.drawShadow(ms, networkColorName, (float)(centerX - networkColorTextStart), (float)(centerY + (augmentStack.isEmpty() ? 28 : 46)), textColor);
        }

        public static int itemWithNameWidth(Minecraft mc, ItemStack itemStack) {
            return 20 + mc.font.width(itemStack.getHoverName());
        }

        public static void renderHUDBox(PoseStack ms, int startX, int startY, int endX, int endY) {
            GuiComponent.fill(ms, startX, startY, endX, endY, 0x44000000);
            GuiComponent.fill(ms, startX - 2, startY - 2, endX + 2, endY + 2, 0x44000000);
        }

        public static void renderItemWithNameCentered(PoseStack ms, Minecraft mc, ItemStack itemStack, int startY, int color) {
            int centerX = mc.getWindow().getGuiScaledWidth() / 2;
            int startX = centerX - (20 + mc.font.width(itemStack.getHoverName())) / 2;
            renderItemWithName(ms, mc, itemStack, startX, startY, color);
        }

        public static void renderItemWithName(PoseStack ps, Minecraft mc, ItemStack itemStack, int startX, int startY, int color) {
            if (!itemStack.isEmpty()) {
                mc.font.drawShadow(ps, itemStack.getHoverName(), (float)(startX + 20), (float)(startY + 4), color);
                mc.getItemRenderer().renderAndDecorateItem(itemStack, startX, startY);
            }
        }

        public EntityManaSparkPattern entity() {
            return this.entity;
        }
    }
}
