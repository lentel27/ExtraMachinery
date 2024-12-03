package net.lmor.botanicalextramachinery.entities.manaSpark;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraBotanicalTile;
import net.lmor.botanicalextramachinery.blocks.tiles.BlockEntityGreenhouse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import vazkii.botania.api.block.WandHUD;
import vazkii.botania.api.mana.ManaItem;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.api.mana.ManaReceiver;
import vazkii.botania.api.mana.spark.ManaSpark;
import vazkii.botania.api.mana.spark.SparkAttachable;
import vazkii.botania.api.mana.spark.SparkHelper;
import vazkii.botania.api.mana.spark.SparkUpgradeType;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.entity.SparkBaseEntity;
import vazkii.botania.common.helper.ColorHelper;
import vazkii.botania.common.helper.VecHelper;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.SparkAugmentItem;
import vazkii.botania.common.item.WandOfTheForestItem;
import vazkii.botania.network.EffectType;
import vazkii.botania.network.clientbound.BotaniaEffectPacket;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.*;

public class EntityManaSparkPattern extends SparkBaseEntity implements ManaSpark {

    protected int TRANSFER_RATE;
    private static final String TAG_UPGRADE = "upgrade";
    private static final EntityDataAccessor<Integer> UPGRADE;
    private final Set<ManaSpark> transfers;
    private int removeTransferants;

    static {
        UPGRADE = SynchedEntityData.defineId(EntityManaSparkPattern.class, EntityDataSerializers.INT);
    }


    public EntityManaSparkPattern(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.transfers = Collections.newSetFromMap(new WeakHashMap());
        this.removeTransferants = 2;
    }


    public void tick() {
        super.tick();
        if (!this.getLevel().isClientSide) {
            SparkAttachable tile = this.getAttachedTile();
            if (tile == null) {
                this.dropAndKill();
            } else {
                ManaReceiver receiver = this.getAttachedManaReceiver();
                SparkUpgradeType upgrade = this.getUpgrade();
                Collection<ManaSpark> transfers = this.getTransfers();
                List validSparks;
                switch (upgrade) {
                    case DISPERSIVE:
                        AABB aabb = VecHelper.boxForRange(this.position().with(Direction.Axis.Y, this.getY() + (double)this.getBbHeight() / 2.0), 12.0);
                        List<Player> players = this.getLevel().getEntitiesOfClass(Player.class, aabb, EntitySelector.ENTITY_STILL_ALIVE);
                        Map<Player, Map<ManaItem, Integer>> receivingPlayers = new HashMap<>();
                        ItemStack input = new ItemStack(this.getSparkItem());
                        Iterator<Player> var9 = players.iterator();

                        Player player;
                        while(var9.hasNext()) {
                            player = var9.next();
                            List<ItemStack> stacks = new ArrayList<>();
                            stacks.addAll(player.getInventory().items);
                            stacks.addAll(player.getInventory().armor);
                            Container inv = BotaniaAPI.instance().getAccessoriesInventory(player);

                            for(int i = 0; i < inv.getContainerSize(); ++i) {
                                stacks.add(inv.getItem(i));
                            }

                            Iterator<ItemStack> iterator = stacks.iterator();

                            while(iterator.hasNext()) {
                                ItemStack stack = iterator.next();
                                ManaItem manaItem = XplatAbstractions.INSTANCE.findManaItem(stack);
                                if (!stack.isEmpty() && manaItem != null && manaItem.canReceiveManaFromItem(input)) {
                                    boolean add = false;
                                    Map<ManaItem, Integer> receivingStacks;
                                    if (!receivingPlayers.containsKey(player)) {
                                        add = true;
                                        receivingStacks = new HashMap<>();
                                    } else {
                                        receivingStacks = receivingPlayers.get(player);
                                    }

                                    int recv = Math.min(receiver.getCurrentMana(), Math.min(TRANSFER_RATE, manaItem.getMaxMana() - manaItem.getMana()));
                                    if (recv > 0) {
                                        receivingStacks.put(manaItem, recv);
                                        if (add) {
                                            receivingPlayers.put(player,  receivingStacks);
                                        }
                                    }
                                }
                            }
                        }

                        if (!receivingPlayers.isEmpty()) {
                            List<Player> keys = new ArrayList<>(receivingPlayers.keySet());
                            Collections.shuffle(keys);
                            player = (Player)keys.iterator().next();
                            Map<ManaItem, Integer> items = (Map<ManaItem, Integer>)receivingPlayers.get(player);
                            Map.Entry<ManaItem, Integer> e = (Map.Entry<ManaItem, Integer>)items.entrySet().iterator().next();
                            ManaItem manaItem = (ManaItem)e.getKey();
                            int cost = (Integer)e.getValue();
                            int manaToPut = Math.min(receiver.getCurrentMana(), cost);
                            manaItem.addMana(manaToPut);
                            receiver.receiveMana(-manaToPut);
                            this.particlesTowards(player);
                        }
                        break;
                    case DOMINANT:
                        validSparks = SparkHelper.getSparksAround(this.getLevel(), this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.getNetwork());
                        validSparks.removeIf((s) -> {
                            if (s instanceof ManaSpark) {
                                ManaSpark manaSpark = (ManaSpark) s;
                                SparkUpgradeType otherUpgrade = manaSpark.getUpgrade();
                                return manaSpark == this || otherUpgrade != SparkUpgradeType.NONE || !(manaSpark.getAttachedManaReceiver() instanceof ManaPool);
                            }
                            return true;
                        });
                        if (!validSparks.isEmpty()) {
                            ((ManaSpark)validSparks.get(this.getLevel().random.nextInt(validSparks.size()))).registerTransfer(this);
                        }
                        break;
                    case RECESSIVE:
                        validSparks = SparkHelper.getSparksAround(this.getLevel(), this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.getNetwork());
                        Iterator var6 = validSparks.iterator();

                        while(var6.hasNext()) {
                            ManaSpark otherSpark = (ManaSpark)var6.next();
                            SparkUpgradeType otherUpgrade = otherSpark.getUpgrade();
                            if (otherSpark != this && otherUpgrade != SparkUpgradeType.DOMINANT && otherUpgrade != SparkUpgradeType.RECESSIVE && otherUpgrade != SparkUpgradeType.ISOLATED) {
                                transfers.add(otherSpark);
                            }
                        }
                }

                if (!transfers.isEmpty()) {
                    int manaTotal = Math.min(TRANSFER_RATE * transfers.size(), receiver.getCurrentMana());
                    int count = transfers.size();
                    int manaSpent = 0;
                    if (manaTotal > 0) {
                        Iterator<ManaSpark> var26 = transfers.iterator();

                        while(var26.hasNext()) {
                            ManaSpark spark = var26.next();
                            --count;
                            SparkAttachable attached = spark.getAttachedTile();
                            ManaReceiver attachedReceiver = spark.getAttachedManaReceiver();
                            if (attached != null && attachedReceiver != null && !attachedReceiver.isFull() && !spark.areIncomingTransfersDone() && (!(receiver instanceof ExtraBotanicalTile) || receiver instanceof BlockEntityGreenhouse) && !(attachedReceiver instanceof BlockEntityGreenhouse)) {
                                int spend = Math.min(attached.getAvailableSpaceForMana(), (manaTotal - manaSpent) / (count + 1));
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


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(UPGRADE, 0);
    }


    protected Item getSparkItem() {
        return ModItems.malachiteSpark;
    }

    private void dropAndKill() {
        SparkUpgradeType upgrade = this.getUpgrade();
        this.spawnAtLocation(new ItemStack(this.getSparkItem()), 0.0F);
        if (upgrade != SparkUpgradeType.NONE) {
            this.spawnAtLocation(SparkAugmentItem.getByType(upgrade), 0.0F);
        }

        this.discard();
    }

    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (this.isAlive() && !stack.isEmpty()) {
            SparkUpgradeType upgrade = this.getUpgrade();
            if (stack.getItem() instanceof WandOfTheForestItem) {
                if (!this.getLevel().isClientSide) {
                    if (player.isShiftKeyDown()) {
                        if (upgrade != SparkUpgradeType.NONE) {
                            this.spawnAtLocation(SparkAugmentItem.getByType(upgrade), 0.0F);
                            this.setUpgrade(SparkUpgradeType.NONE);
                            this.transfers.clear();
                            this.removeTransferants = 2;
                        } else {
                            this.dropAndKill();
                        }
                    } else {
                        SparkHelper.getSparksAround(this.getLevel(), this.getX(), this.getY() + (double)(this.getBbHeight() / 2.0F), this.getZ(), this.getNetwork()).forEach((s) -> {
                            particleBeam(player, this, s.entity());
                        });
                    }
                }

                return InteractionResult.sidedSuccess(this.getLevel().isClientSide);
            }

            Item var7 = stack.getItem();
            if (var7 instanceof SparkAugmentItem) {
                SparkAugmentItem newUpgrade = (SparkAugmentItem)var7;
                if (upgrade == SparkUpgradeType.NONE) {
                    if (!this.getLevel().isClientSide) {
                        this.setUpgrade(newUpgrade.type);
                        stack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(this.getLevel().isClientSide);
                }
            }

            if (stack.is(BotaniaItems.phantomInk)) {
                if (!this.getLevel().isClientSide) {
                    this.setInvisible(true);
                }

                return InteractionResult.sidedSuccess(this.getLevel().isClientSide);
            }

            var7 = stack.getItem();
            if (var7 instanceof DyeItem) {
                DyeItem dye = (DyeItem)var7;
                DyeColor color = dye.getDyeColor();
                if (color != this.getNetwork()) {
                    if (!this.getLevel().isClientSide) {
                        this.setNetwork(color);
                        stack.shrink(1);
                    }

                    return InteractionResult.sidedSuccess(this.getLevel().isClientSide);
                }
            }
        }

        return InteractionResult.PASS;
    }

    private void particlesTowards(Entity e) {
        XplatAbstractions.INSTANCE.sendToTracking(this, new BotaniaEffectPacket(EffectType.SPARK_MANA_FLOW, this.getX(), this.getY(), this.getZ(), this.getId(), e.getId(), ColorHelper.getColorValue(this.getNetwork())));
    }

    public static void particleBeam(Player player, Entity e1, Entity e2) {
        if (e1 != null && e2 != null && !e1.getLevel().isClientSide) {
            XplatAbstractions.INSTANCE.sendToPlayer(player, new BotaniaEffectPacket(EffectType.SPARK_NET_INDICATOR, e1.getX(), e1.getY(), e1.getZ(), e1.getId(), e2.getId()));
        }

    }

    protected void readAdditionalSaveData(@NotNull CompoundTag cmp) {
        super.readAdditionalSaveData(cmp);
        this.setUpgrade(SparkUpgradeType.values()[cmp.getInt("upgrade")]);
    }

    protected void addAdditionalSaveData(@NotNull CompoundTag cmp) {
        super.addAdditionalSaveData(cmp);
        cmp.putInt("upgrade", this.getUpgrade().ordinal());
    }

    private void filterTransfers() {
        Iterator<ManaSpark> iter = this.transfers.iterator();

        while(true) {
            ManaSpark spark;
            SparkUpgradeType upgr;
            SparkUpgradeType supgr;
            ManaReceiver arecv;
            do {
                if (!iter.hasNext()) {
                    return;
                }

                spark = (ManaSpark)iter.next();
                upgr = this.getUpgrade();
                supgr = spark.getUpgrade();
                arecv = spark.getAttachedManaReceiver();
            } while(spark != this && ((Entity)spark).isAlive() && !spark.areIncomingTransfersDone() && this.getNetwork() == spark.getNetwork() && arecv != null && !arecv.isFull() && (upgr == SparkUpgradeType.NONE && supgr == SparkUpgradeType.DOMINANT || upgr == SparkUpgradeType.RECESSIVE && (supgr == SparkUpgradeType.NONE || supgr == SparkUpgradeType.DISPERSIVE) || !(arecv instanceof ManaPool)));

            iter.remove();
        }
    }

    private boolean hasTransfer(ManaSpark entity) {
        return this.transfers.contains(entity);
    }

    public @NotNull ItemStack getPickResult() {
        return new ItemStack(this.getSparkItem());
    }

    @Override
    public @Nullable SparkAttachable getAttachedTile() {
        return XplatAbstractions.INSTANCE.findSparkAttachable(this.getLevel(), this.getAttachPos(), this.getLevel().getBlockState(this.getAttachPos()), this.getLevel().getBlockEntity(this.getAttachPos()), Direction.UP);
    }

    @Override
    public Collection<ManaSpark> getTransfers() {
        this.filterTransfers();
        return this.transfers;
    }

    @Override
    public void registerTransfer(ManaSpark manaSpark) {
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
        if (this.getAttachedManaReceiver() instanceof ManaPool) {
            return this.removeTransferants > 0;
        } else {
            SparkAttachable attachable = this.getAttachedTile();
            return attachable != null && attachable.areIncomingTranfersDone();
        }
    }

    public static record WandHud(EntityManaSparkPattern entity) implements WandHUD {
        public WandHud(EntityManaSparkPattern entity) {
            this.entity = entity;
        }

        public void renderHUD(PoseStack ms, Minecraft mc) {
            ItemStack sparkStack = new ItemStack(this.entity.getSparkItem());
            ItemStack augmentStack = SparkAugmentItem.getByType(this.entity.getUpgrade());
            DyeColor networkColor = this.entity.getNetwork();
            String colorName = networkColor.getName();
            Component networkColorName = Component.translatable("color.minecraft." + colorName).withStyle(ChatFormatting.ITALIC);
            int textColor = ColorHelper.getColorLegibleOnGrayBackground(networkColor);
            int width = 4 + (Integer)Collections.max(Arrays.asList(mc.font.width(networkColorName), RenderHelper.itemWithNameWidth(mc, sparkStack), RenderHelper.itemWithNameWidth(mc, augmentStack)));
            int height = augmentStack.isEmpty() ? 30 : 50;
            int networkColorTextStart = mc.font.width(networkColorName) / 2;
            int centerX = mc.getWindow().getGuiScaledWidth() / 2;
            int centerY = mc.getWindow().getGuiScaledHeight() / 2;
            RenderHelper.renderHUDBox(ms, centerX - width / 2, centerY + 8, centerX + width / 2, centerY + 8 + height);
            RenderHelper.renderItemWithNameCentered(ms, mc, sparkStack, centerY + 10, textColor);
            RenderHelper.renderItemWithNameCentered(ms, mc, augmentStack, centerY + 28, textColor);
            mc.font.drawShadow(ms, networkColorName, (float)(centerX - networkColorTextStart), (float)(centerY + (augmentStack.isEmpty() ? 28 : 46)), textColor);
        }

        public EntityManaSparkPattern entity() {
            return this.entity;
        }
    }
}
