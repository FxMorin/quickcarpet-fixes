package quickcarpetfixes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quickcarpet.api.annotation.BugFix;
import quickcarpet.api.settings.Rule;
import quickcarpetfixes.helpers.UpdateScheduler;
import quickcarpetfixes.settings.ChangeListeners;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static quickcarpet.api.settings.RuleCategory.*;

public class QCFSettings {

    //Add your name above the rules so people know who to contact about changing the code. E.x. By FX - PR0CESS

    // Global Variables
    public static boolean scheduleWorldBorderReset = false;
    public static HashMap<World, UpdateScheduler> updateScheduler = new HashMap<>();
    public static final ThreadLocal<Set<BlockPos>> LAST_DIRT = ThreadLocal.withInitial(HashSet::new);
    public static final Predicate<BlockState> IS_REPLACEABLE = (state) -> state.getMaterial().isReplaceable();

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-118429"), category = FIX)
    public static boolean crystalExplodeOnExplodedFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-220636"), category = FIX)
    public static boolean spongeUpdateFix = false;

    //By FX - PR0CESS
    //Recommended since it allows illegal blocks to be made. Suppresses Multiple Updates
    @Rule(category = FIX)
    public static boolean hopperUpdateFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-136566"), category = FIX)
    public static boolean observerUpdateFix = false;

    //By FX - PR0CESS
    // Not putting it in the dupe fix category since technically there is no way to dupe with it anymore.
    // Although there probably will be. This is also the cause of many other problems
    @Rule(bug = @BugFix("MC-134892"), category = {FIX,SURVIVAL})
    public static boolean stringTagExploitFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-123364"), category = FIX)
    public static boolean blockCollisionCheckFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-161402"), category = FIX)
    public static boolean blockUpdateOrderFix = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean extendedBlockUpdateOrderFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-120986"),category = FIX)
    public static boolean comparatorUpdateFix = false;

    //by FX - PR0CESS
    //Recommended since it not only negates a crash but also tried to keep behaviour after it.
    // Technically it's a dupe fix, although it's a lot more than that
    @Rule(category = FIX)
    public static boolean updateSuppressionFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-216985"), category = {FIX,EXPERIMENTAL})
    public static boolean incorrectBounceLogicFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-207866"), category = {FIX,EXPERIMENTAL})
    public static boolean incorrectBubbleColumnLogicFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-202654"), category = FIX)
    public static boolean directionalBlockSlowdownFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-202607"), category = FIX)
    public static boolean catsBreakLeadsDuringGiftFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-173303"), category = FIX)
    public static boolean petsBreakLeadsDuringReloadFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-183054"), category = {FIX,EXPERIMENTAL})
    public static boolean endermanDontUpdateOnPlaceFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-227008"), category = {FIX,SURVIVAL})
    public static boolean endermanUselessMinecartTeleportingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-174864"), category = FIX)
    public static boolean railInvalidUpdateOnPushFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-123311"), category = FIX)
    public static boolean railMissingUpdateOnPushFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-96224"), category = FIX)
    public static boolean railMissingUpdateAfterPushFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-159283"), category = {FIX,OPTIMIZATIONS})
    public static boolean endVoidRingsFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-19830"), category = FIX)
    public static boolean sleepingDelaysFallDamageFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-8983"), category = FIX)
    public static boolean tntCantUseNetherPortalsFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-9644"), category = FIX)
    public static boolean fallingBlocksCantUseNetherPortalsFix = false;

    //by FX - PR0CESS
    //Marked as vanilla since it does not change any behaviour, just keeps spawn chunks loaded
    @Rule(bug = @BugFix("MC-59134"),category = {FIX,SURVIVAL})
    public static boolean spawnChunkEntitiesUnloadingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-197473"), category = FIX)
    public static boolean repairCostItemNotStackingFix = false;

    //by FX - PR0CESS
    @Rule(bug = {@BugFix("MC-1133"),@BugFix("MC-158154")}, category = FIX)
    public static boolean playerBlockCollisionUsingCenterFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-147659"), category = {FIX,SURVIVAL})
    public static boolean witchHutsSpawnIncorrectCatFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-226687"), category = FIX)
    public static boolean hardcodedSeaLevelFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-175544"), category = FIX)
    public static boolean fishingOutsideWaterFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-226961"), category = FIX)
    public static boolean xpOrbCollisionFix = false;

    //by FX - PR0CESS
    //Currently only Slime, Mushroom, Zombie, Zombie Villager, & piglins are supported.
    // More to come eventually when I stop being lazy
    @Rule(bug = @BugFix("MC-88967"), category = {FIX,EXPERIMENTAL})
    public static boolean conversionFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean explosionBreaksItemFrameInWaterFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-161026"), category = FIX)
    public static boolean movingBlocksDestroyPathFix = false;

    //by FX - PR0CESS
    @Rule(
            bug = {@BugFix("MC-60792"),@BugFix("MC-232725")},
            onChange = ChangeListeners.WitherGolemSpawningFixValidator.class,
            category = FIX
    )
    public static boolean witherGolemSpawningFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-188220"), category = FIX)
    public static boolean illegalBreakingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-27056"), category = FIX)
    public static boolean headlessPistonFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean sleepingResetsThunderFix = false;

    //code by FX - PR0CESS
    //solution by DawNemo
    @Rule(bug = @BugFix("MC-232355"), category = {FIX,SURVIVAL})
    public static boolean incorrectExplosionExposureFix = false;

    //by FX - PR0CESS
    //Recommended even thought its experimental since it does save a ton of performance
    //Marked as Vanilla since its very, very hard to run into a situation where it affects vanilla
    //Only technical players would be able to tell the difference if they really tried
    @Rule(bug = @BugFix("MC-231071"), category = {FIX,EXPERIMENTAL,SURVIVAL,OPTIMIZATIONS})
    public static boolean duplicateBlockUpdatesFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-157300"), category = FIX)
    public static boolean trapdoorMissingUpdateFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean uselessDetectorRailUpdateFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-167279"), category = FIX)
    public static boolean beeStuckInVoidFix = false;

    //by FX - PR0CESS
    @Rule(category = {FIX,EXPERIMENTAL})
    public static boolean invulnerableEndCrystalFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-218222"), category = {FIX,EXPERIMENTAL})
    public static boolean sculkSensorPrecisionLossFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-234754"), category = FIX)
    public static boolean creeperPortalFuseResetsFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-200991"), category = FIX)
    public static boolean soulSpeedIncorrectDamageFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-215763"), category = FIX)
    public static boolean endCrystalPlacingTooEarlyFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-102774"), category = FIX)
    public static boolean respawnDragonWithoutAllEndCrystalsFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean treeTrunkLogicFix = false;

    //by FX - PR0CESS
    @Rule(category = {FIX,SURVIVAL,OPTIMIZATIONS})
    public static boolean uselessSelfBlockUpdateFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean tntMinecartExplodesTwiceFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-95467"), category = FIX)
    public static boolean breakAnythingDoorGoalFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-2474"), category = FIX)
    public static boolean transparentBlocksNegateEnchantingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-67844"), category = FIX)
    public static boolean chestUsablePastWorldBorderFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-123450"), category = {FIX,SURVIVAL})
    public static boolean itemFramePlaysSoundOnReadFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-112257"), category = {FIX,SURVIVAL,CREATIVE})
    public static boolean incorrectNbtChecks = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-6431"), category = FIX)
    public static boolean endPortalRemovesEffectsFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-120938"), category = FIX)
    public static boolean inconsistentRedstoneTorchFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-153010"), category = FIX)
    public static boolean foxesDropItemsWithLootOffFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-215636"), category = FIX)
    public static boolean instantFluidFlowingFix = false;

    //by FX - PR0CESS
    //Not a dupe fix, although it was written to prevent dupes in the future
    @Rule(category = FIX)
    public static boolean saferItemTransfers = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-207289"), category = FIX)
    public static boolean sculkSensorBiasFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-168329"), category = FIX)
    public static boolean beeNotLeavingHiveFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-82055"), category = FIX)
    public static boolean hangingEntityTriggersTrapsFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-119369"), category = FIX)
    public static boolean boatsTakeFallDamageFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-98160"), category = FIX)
    public static boolean boatsDontTakeFallDamageFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-227443"), category = FIX)
    public static boolean buriedTreasureAlwaysCenterFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-14800"), category = FIX)
    public static boolean reinforcementsOnlySpawnZombiesFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-14167"), category = FIX)
    public static boolean incorrectFallDamageFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-125755"), category = FIX)
    public static boolean voidKillsLoyaltyTridentsFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-145557"), category = FIX)
    public static boolean endermanLowerPiercingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-209284"), category = FIX)
    public static boolean projectileNotDetectedOnPlaceFix = false;

    //by FX - PR0CESS
    //Might make it so no arrows bypass the totem, unsure yet
    @Rule(bug = @BugFix("MC-206307"), category = FIX)
    public static boolean arrowEffectsBypassTotemsFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-227250"), category = FIX)
    public static boolean mobsConvertingWithoutBlocksFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-120578"), category = FIX)
    public static boolean mobsIgnoreOwnerOnPickupFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-157644"), category = FIX)
    public static boolean redstoneComponentUpdateOrderOnBreakFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-241951"), category = FIX)
    public static boolean velocitySeparateAxisCancellingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-244956"), category = FIX)
    public static boolean armorStandMissingFunctionalityFix = false;

    //by Adryd
    @Rule(bug = @BugFix("MC-65668"), category = FIX)
    public static boolean endermanTeleportWithoutAIFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-3703"), category = FIX)
    public static boolean redstoneRedirectionMissingUpdateFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-130183"), category = FIX)
    public static boolean pistonsPushWaterloggedBlocksFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean tileDropsAffectedByFloatingPointFix = false;

    //by FX - PR0CESS
    @Rule(category = {FIX,OPTIMIZATIONS})
    public static boolean entityRandomCrackingFix = false;

    //by FX - PR0CESS
    @Rule(
            bug = @BugFix("MC-88482"),
            category = FIX,
            onChange = ChangeListeners.WorldBorderCollisionRoundingFixValidator.class
    )
    public static boolean worldBorderCollisionRoundingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-247420"), category = FIX)
    public static boolean detectorRailOffsetUpdateFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-63578"), category = FIX)
    public static boolean placeBlocksOutsideWorldBorderFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-82010"), category = FIX)
    public static boolean incorrectPistonWorldBorderCheckFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-54606"), category = FIX)
    public static boolean explosionsBypassWorldBorderFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-247417"), category = FIX)
    public static boolean playerStepEventFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-208771"), category = FIX)
    public static boolean projectileMissingOcclusionFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-208597"), category = FIX)
    public static boolean boatMissingOcclusionFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-247643"), category = FIX)
    public static boolean spawnEggOffsetEventFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-247645"), category = FIX)
    public static boolean spawnEggMissingOcclusionFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-214472"), category = FIX)
    public static boolean spawnEggMissingEventFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-213823"), category = FIX)
    public static boolean minecartMissingOcclusionFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-247647"), category = FIX)
    public static boolean villagerDiscountIgnoresOfflinePlayersFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-179916"), category = FIX)
    public static boolean foxesGoToOriginDuringThunderFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-167242"), category = FIX)
    public static boolean villagerToWitchBedOccupiedFix = false;

    //by FX - PR0CESS, ported from carpetmod112
    @Rule(category = FIX)
    public static boolean reloadUpdateOrderFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-199210"), category = FIX)
    public static boolean armorStandNegateLavaDamageFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-224420"), category = FIX)
    public static boolean armorStandNegateCactusDamageFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-129055"), category = FIX)
    public static boolean tripwireNotDisarmingFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-205044"), category = FIX)
    public static boolean powderedSnowOpacityFix = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean invisibleHopperFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-81656"), category = FIX)
    public static boolean blueWitherSkullNotSavedFix = false;

    //by FX - PR0CESS
    @Rule(
            bug = @BugFix("MC-248621"),
            category = FIX,
            onChange = ChangeListeners.TagKeyMemoryLeakFixValidator.class
    )
    public static boolean tagKeyMemoryLeakFix = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean nocomExploitFix = false;

    //By FX - PR0CESS
    @Rule(category = {FIX,CREATIVE})
    public static boolean structureManagerCantLoadSnbtFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean stepAndDestroyBlockGoalUsesOriginFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean hopperMinecartSlowerAtOriginFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-160095"), category = FIX)
    public static boolean nonSolidBlocksBreakCactusIfPushedFix = false;


    /*

    OPTIMIZATIONS
    These are all the rules that where made to optimize the game. Rules that are considered OPTIMIZATIONSs
    but that where not intended to be OPTIMIZATIONSs are not put in this category.

     */

    //by FX - PR0CESS
    //Soon this will fix all update suppression with rails
    @Rule(category = OPTIMIZATIONS)
    public static boolean optimizedPoweredRails = false;

    //by 2No2Name, JellySquid
    @Rule(category = OPTIMIZATIONS)
    public static boolean optimizedTicketManager = false;

    //by FX - PR0CESS
    @Rule(category = OPTIMIZATIONS)
    public static boolean optimizedRandom = false;

    //by FX - PR0CESS
    @Rule(category = {OPTIMIZATIONS,SURVIVAL})
    public static boolean optimizedBiomeAccess = false;

    //by FX - PR0CESS
    @Rule(category = {OPTIMIZATIONS,SURVIVAL})
    public static boolean optimizedRecipeManager = false;

    //by FX - PR0CESS
    //Backported to 1.18.2 by Crec0
    @Rule(category = {OPTIMIZATIONS,SURVIVAL})
    public static boolean optimizedFurnaces = false;


    /*

    DUPE BUGS
    Bugs that dupe stuff. This is the cool bug category. They are just like all the
    other bugs, except they deserve a category of their own (for sorting purposes...)

     */

    // QuickCarpet already has a fix for the more well known dupe bugs

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-120507"), category = FIX)
    public static boolean giveCommandDupeFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-112826"), category = {FIX,SURVIVAL})
    public static boolean nbtDataDupeFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-234471"), category = {FIX,EXPERIMENTAL})
    public static boolean beeDupeFix = false;

    // by apple502j
    @Rule(category = FIX)
    public static boolean breakSwapGeneralItemDupeFix = false;

    //by FX - PR0CESS
    @Rule(bug = @BugFix("MC-59471"), category = FIX)
    public static boolean stringDupeFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean tripwireHookDupeFix = false;

    //by Captain_S0L0
    @Rule(category = FIX)
    public static boolean horseDupeFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean mobsSpawnOnMovingPistonsFix = false;

    //by FX - PR0CESS
    @Rule(category = FIX)
    public static boolean geodeLavalogFix = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix("MC-124177"), category = FIX)
    public static boolean crossDimensionTeleportLosesStatsFix = false;


    /*

    RE-INTRODUCE
    Bugs that are no longer Unresolved that we reintroduce into the game
    or Bugs that where fixed in the snapshots that we re-introduce from older versions
    or just re-introducing rules/mechanics because we want to (usually performance)

    Damn these are some long rule names

     */

    //By FX - PR0CESS
    @Rule(bug = @BugFix(value = "MC-173244", fixVersion = "20w10a"), category = FIX)
    public static boolean reIntroduceTargetBlockPermanentlyPowered = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix(value = "MC-203718", fixVersion = "21w19a"), category = FIX)
    public static boolean reIntroduceLightningRodPermanentlyPowered = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean reIntroducePortalGeneralItemDupe = false;

    //By whoImT from carpet-addons
    @Rule(bug = @BugFix(value = "MC-4923", fixVersion = "18w05a"), category = FIX)
    public static boolean reIntroduceFlintAndSteelBehavior = false;

    //By whoImT from carpet-addons
    @Rule(bug = @BugFix(value = "MC-181241", fixVersion = "18w05a"), category = {FIX,EXPERIMENTAL})
    public static boolean reIntroduceDonkeyRidingDupe = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean reIntroduceItemShadowing = false;

    //By FX - PR0CESS
    @Rule(bug = @BugFix(value = "MC-113809", fixVersion = "20w12a"), category = FIX)
    public static boolean reIntroduceZeroTickFarms = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean reIntroduceOnlyAutoSaveSaving = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean reIntroduceVeryAggressiveSaving = false;

    //By FX - PR0CESS
    @Rule(category = FIX)
    public static boolean reIntroduceFallingBlockEntityPhase = false;


    /*

    FABRIC-CARPET & CARPET-EXTRA OVERRIDES
    I want to move these rules over to carpet-fixes

     */

    //By FX - PR0CESS from fabric-carpet
    @Rule(bug = @BugFix("MC-206922"), category = FIX)
    public static boolean lightningKillsDropsFix = false;

    //By DeadlyMC from carpet-extra
    @Rule(category = {FIX,EXPERIMENTAL}, bug = @BugFix("MC-88959"))
    public static boolean doubleRetraction = false;

    //Original By DeadlyMC (from carpet-extra), Fixed By FX - PR0CESS
    //Fixed bug in carpet-extra's implementation
    @Rule(bug = @BugFix("MC-54711"), category = FIX)
    public static boolean repeaterPriorityFix = false;


    /*

    ADVANCED CONFIGURATIONS
    Simple options that you can turn on and off are great, its one of the things I try to
    do. Since it makes it much easier for the end user to use the mod. These options are
    for more CREATIVE users who want some more flexibility!

     */

    //By FX - PR0CESS
    @Rule(options = {"300","200","100","50"}, category = {CREATIVE,OPTIMIZATIONS})
    public static int maxChunksSavedPerAutoSave = 200;

    //By FX - PR0CESS
    @Rule(options = {"100","50","20","10"}, category = {CREATIVE,OPTIMIZATIONS})
    public static int maxChunksSavedPerTick = 20;

    //By FX - PR0CESS
    @Rule(options = {"12000","6000","3600","1200"}, category = {CREATIVE,OPTIMIZATIONS})
    public static int delayBetweenAutoSaves = 6000;

    //By FX - PR0CESS
    @Rule(options = {"12","6","3","1"}, category = {CREATIVE,EXPERIMENTAL})
    public static int maxTickLatency = 3;

    //By FX - PR0CESS
    @Rule(options = {"60000000000","10000000000","5000000000","1000000000"}, category = CREATIVE)
    public static long statusUpdateDelay = 5000000000L;

    //By FX - PR0CESS
    @Rule(options = {"120000","60000","10000","1000","0"}, category = {CREATIVE,OPTIMIZATIONS})
    public static int chunkSaveCooldownDelay = 10000;


    /*

    PARITY
    Parity bugs between java & bedrock edition. This category is basically a meme,
    we probably don't want these to actually be added to java!

     */

    //By FX - PR0CESS
    @Rule(category = FEATURE)
    public static boolean parityRandomBlockUpdates = false;

    //By FX - PR0CESS
    @Rule(category = FEATURE)
    public static boolean parityMovableLightBlocks = false;

    //By FX - PR0CESS
    @Rule(category = FEATURE)
    public static boolean parityTerribleComparators = false;
}
