package quarris.qlib.api.data.loottable;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.*;
import net.minecraft.world.storage.loot.functions.*;

import java.util.Set;
import java.util.stream.Stream;

public abstract class BlockLootTableProvider extends AbstractLootTableProvider<Block> {

    public BlockLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    public LootTable.Builder defaultLootTable(Block block) {
        return LootTables.dropping(block);
    }

    @Override
    protected ResourceLocation getLootTableLocation(Block object) {
        return object.getLootTable();
    }

    @Override
    protected LootParameterSet getLootParameterSet() {
        return LootParameterSets.BLOCK;
    }

    public static class LootTables extends BlockLootTables {

        public static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
        public static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
        public static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
        public static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
        public static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();

        public static final Set<Item> IMMUNE_TO_EXPLOSIONS = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(IItemProvider::asItem).collect(ImmutableSet.toImmutableSet());

        public static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
        public static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

        public static <T> T withExplosionDecay(IItemProvider item, ILootFunctionConsumer<T> function) {
            return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? function.acceptFunction(ExplosionDecay.builder()) : function.cast();
        }

        public static <T> T withSurvivesExplosion(IItemProvider item, ILootConditionConsumer<T> condition) {
            return !IMMUNE_TO_EXPLOSIONS.contains(item.asItem()) ? condition.acceptCondition(SurvivesExplosion.builder()) : condition.cast();
        }

        public static LootTable.Builder dropping(IItemProvider item) {
            return LootTable.builder()
                    .addLootPool(withSurvivesExplosion(item, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(item))));
        }

        public static LootTable.Builder dropping(Block block, ILootCondition.IBuilder condition, LootEntry.Builder<?> entry) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(block)
                                    .acceptCondition(condition)
                                    .alternatively(entry)));
        }

        public static LootTable.Builder droppingWithSilkTouch(Block block, LootEntry.Builder<?> entry) {
            return dropping(block, SILK_TOUCH, entry);
        }

        public static LootTable.Builder droppingWithShears(Block block, LootEntry.Builder<?> entry) {
            return dropping(block, SHEARS, entry);
        }

        public static LootTable.Builder droppingWithSilkTouchOrShears(Block block, LootEntry.Builder<?> entry) {
            return dropping(block, SILK_TOUCH_OR_SHEARS, entry);
        }

        public static LootTable.Builder droppingWithSilkTouch(Block block, IItemProvider item) {
            return droppingWithSilkTouch(block, withSurvivesExplosion(block, ItemLootEntry.builder(item)));
        }

        public static LootTable.Builder droppingRandomly(IItemProvider item, IRandomRange range) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(withExplosionDecay(item, ItemLootEntry.builder(item)
                                    .acceptFunction(SetCount.builder(range)))));
        }

        public static LootTable.Builder droppingWithSilkTouchOrRandomly(Block block, IItemProvider item, IRandomRange range) {
            return droppingWithSilkTouch(block, withExplosionDecay(block,
                    ItemLootEntry.builder(item)
                            .acceptFunction(SetCount.builder(range))));
        }

        public static LootTable.Builder onlyWithSilkTouch(IItemProvider item) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .acceptCondition(SILK_TOUCH)
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(item)));
        }

        public static LootTable.Builder droppingAndFlowerPot(IItemProvider item) {
            return LootTable.builder()
                    .addLootPool(withSurvivesExplosion(Blocks.FLOWER_POT, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(Blocks.FLOWER_POT))))
                    .addLootPool(withSurvivesExplosion(item, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(item))));
        }

        public static LootTable.Builder droppingSlab(Block block) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(withExplosionDecay(block, ItemLootEntry.builder(block)
                                    .acceptFunction(SetCount.builder(ConstantRange.of(2))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withProp(SlabBlock.TYPE, SlabType.DOUBLE)))))));
        }

        public static <T extends Comparable<T> & IStringSerializable> LootTable.Builder droppingWhen(Block block, IProperty<T> prop, T value) {
            return LootTable.builder()
                    .addLootPool(withSurvivesExplosion(block, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(block)
                                    .acceptCondition(BlockStateProperty.builder(block)
                                            .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                    .withProp(prop, value))))));
        }

        public static LootTable.Builder droppingWithName(Block block) {
            return LootTable.builder()
                    .addLootPool(withSurvivesExplosion(block, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(block)
                                    .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY)))));
        }

        public static LootTable.Builder droppingWithContents(Block block) {
            return LootTable.builder()
                    .addLootPool(withSurvivesExplosion(block, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(block)
                                    .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                                    .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                            .replaceOperation("Lock", "BlockEntityTag.Lock")
                                            .replaceOperation("LootTable", "BlockEntityTag.LootTable")
                                            .replaceOperation("LootTableSeed", "BlockEntityTag.LootTableSeed"))
                                    .acceptFunction(SetContents.func_215920_b()
                                            .func_216075_a(DynamicLootEntry.func_216162_a(ShulkerBoxBlock.CONTENTS))))));
        }

        public static LootTable.Builder droppingWithPatterns(Block block) {
            return LootTable.builder()
                    .addLootPool(withSurvivesExplosion(block, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(block)
                                    .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                                    .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                            .replaceOperation("Patterns", "BlockEntityTag.Patterns")))));
        }

        public static LootTable.Builder droppingBeesWithSilktouch(Block block) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .acceptCondition(SILK_TOUCH)
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(block)
                                    .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                            .replaceOperation("Bees", "BlockEntityTag.Bees"))
                                    .acceptFunction(CopyBlockState.func_227545_a_(block)
                                            .func_227552_a_(BeehiveBlock.HONEY_LEVEL))));
        }

        public static LootTable.Builder droppingBeesOrSilktouch(Block block) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(block)
                                    .acceptCondition(SILK_TOUCH)
                                    .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                            .replaceOperation("Bees", "BlockEntityTag.Bees"))
                                    .acceptFunction(CopyBlockState.func_227545_a_(block)
                                            .func_227552_a_(BeehiveBlock.HONEY_LEVEL))
                                    .alternatively(ItemLootEntry.builder(block))));
        }

        public static LootTable.Builder droppingItemWithFortune(Block block, Item item) {
            return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(item)
                    .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))));
        }

        /**
         * Creates a builder that drops the given IItemProvider in amounts between 0 and 2, most often 0. Only used in
         * vanilla for huge mushroom blocks.
         */
        public static LootTable.Builder droppingItemRarely(Block block, IItemProvider item) {
            return droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(item)
                    .acceptFunction(SetCount.builder(RandomValueRange.of(-6.0F, 2.0F)))
                    .acceptFunction(LimitCount.func_215911_a(IntClamper.func_215848_a(0)))));
        }

        public static LootTable.Builder droppingSeeds(Block p_218570_0_) {
            return droppingWithShears(p_218570_0_, withExplosionDecay(p_218570_0_, (ItemLootEntry.builder(Items.WHEAT_SEEDS)
                    .acceptCondition(RandomChance.builder(0.125F)))
                    .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, 2))));
        }

        /**
         * Creates a builder that drops the given IItemProvider in amounts between 0 and 3, based on the AGE property. Only
         * used in vanilla for pumpkin and melon stems.
         */
        public static LootTable.Builder droppingByAge(Block block, Item item) {
            return LootTable.builder()
                    .addLootPool(withExplosionDecay(block, LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .addEntry(ItemLootEntry.builder(item)
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.06666667F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 0))))
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.13333334F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 1))))
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.2F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 2))))
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.26666668F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 3))))
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.33333334F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 4))))
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.4F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 5))))
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.46666667F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 6))))
                                    .acceptFunction(SetCount.builder(BinomialRange.of(3, 0.53333336F))
                                            .acceptCondition(BlockStateProperty.builder(block)
                                                    .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                            .withIntProp(BlockStateProperties.AGE_0_7, 7)))))));
        }

        public static LootTable.Builder droppingMutliple(Block block, Item item, int amount) {
            return LootTable.builder().addLootPool(withExplosionDecay(block, LootPool.builder()
                    .rolls(ConstantRange.of(1))
                    .addEntry(ItemLootEntry.builder(item)
                            .acceptFunction(SetCount.builder(BinomialRange.of(amount, 0.53333336F))))));
        }

        public static LootTable.Builder onlyWithShears(IItemProvider item) {
            return LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .acceptCondition(SHEARS)
                            .addEntry(ItemLootEntry.builder(item)));
        }

        /**
         * Used for all leaves, drops self with silk touch, otherwise drops the second Block param with the passed chances
         * for fortune levels, adding in sticks.
         */
        public static LootTable.Builder droppingWithChancesAndSticks(Block self, Block other, float... chances) {
            return droppingWithSilkTouchOrShears(self, withSurvivesExplosion(self, ItemLootEntry.builder(other))
                    .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, chances)))
                    .addLootPool(LootPool.builder()
                            .rolls(ConstantRange.of(1))
                            .acceptCondition(NOT_SILK_TOUCH_OR_SHEARS)
                            .addEntry(withExplosionDecay(self, ItemLootEntry.builder(Items.STICK)
                                    .acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))))
                                    .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
        }

        /**
         * Used for oak and dark oak, same as droppingWithChancesAndSticks but adding in apples.
         */
        public static LootTable.Builder droppingWithChancesSticksAndApples(Block self, Block other, float... chances) {
            return droppingWithChancesAndSticks(self, other, chances)
                    .addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
                            .acceptCondition(NOT_SILK_TOUCH_OR_SHEARS)
                            .addEntry(withSurvivesExplosion(self, ItemLootEntry.builder(Items.APPLE))
                                    .acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
        }

        /**
         * Drops the first item parameter always, and the second item parameter plus more of the first when the loot
         * condition is met, applying fortune to only the second argument.
         */
        public static LootTable.Builder droppingAndBonusWhen(Block block, Item first, Item second, ILootCondition.IBuilder condition) {
            return withExplosionDecay(block, LootTable.builder()
                    .addLootPool(LootPool.builder()
                            .addEntry(ItemLootEntry.builder(first)
                                    .acceptCondition(condition)
                                    .alternatively(ItemLootEntry.builder(second))))
                    .addLootPool(LootPool.builder()
                            .acceptCondition(condition)
                            .addEntry(ItemLootEntry.builder(second)
                                    .acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3)))));
        }


    }
}
