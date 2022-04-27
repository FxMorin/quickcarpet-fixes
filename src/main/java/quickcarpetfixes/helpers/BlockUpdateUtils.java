package quickcarpetfixes.helpers;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import quickcarpetfixes.QCFSettings;

import java.util.HashSet;
import java.util.Set;

public class BlockUpdateUtils {

    /**
     *  Default Block Update Order:
     *   ~ [West, East, Down, Up, North, South]
     *   ~ [-x, +x, -y, +y, -z, +z]
     *
     *   Default Extended Block Update Order:
     *   ~ [Down, Up, West, East, North, South]
     *   ~ [-y, +y, -x, +x, -z, +z]
     *
     *   Proper Block Update Order:
     *   ~ [West, East, North, South, Down, Up]
     *   ~ [-x, +x, -z, +z, -y, +y]
     */

    //TODO: Needs optimization
    public static void doExtendedBlockUpdates(World world, BlockPos pos, Block block,
                                              boolean removedAndEmitsPower, boolean requiresASelfBlockUpdate) {
        Set<BlockPos> blockPosList = QCFSettings.duplicateBlockUpdatesFix ? new HashSet<>() : null;
        //Set the extended direction update order
        Direction[] extendedDirections = QCFSettings.extendedBlockUpdateOrderFix ?
                DirectionUtils.directions :
                QCFSettings.parityRandomBlockUpdates ?
                        DirectionUtils.randomDirectionArray(pos) :
                        Direction.values();
        //Set the direction update order
        Direction[] directions = QCFSettings.blockUpdateOrderFix ?
                DirectionUtils.directions :
                QCFSettings.parityRandomBlockUpdates ?
                        DirectionUtils.randomDirectionArray(pos) :
                        DirectionUtils.updateDirections;
        //If redstone component should update blocks closer to itself before giving extended block updates
        if (QCFSettings.redstoneComponentUpdateOrderOnBreakFix && removedAndEmitsPower) {
            for(Direction dir : directions) { //Do block updates around block first. Preventing wrong order
                world.updateNeighbor(pos.offset(dir), block, pos);
            }
        }
        for(Direction extendedDir : extendedDirections) { //For each extended block update direction
            BlockPos p = pos.offset(extendedDir);
            for(Direction dir : directions) { //For each block update direction
                BlockPos nextPos = p.offset(dir);
                if (!QCFSettings.uselessSelfBlockUpdateFix || requiresASelfBlockUpdate || !nextPos.equals(pos)) {
                    if (!QCFSettings.duplicateBlockUpdatesFix || !blockPosList.contains(nextPos)) {
                        requiresASelfBlockUpdate = false;
                        world.updateNeighbor(nextPos, block, p);
                        if (QCFSettings.duplicateBlockUpdatesFix) blockPosList.add(nextPos);
                    }
                }
            }
        }
    }

    public static boolean canUpdateNeighborsAlwaysWithOrder(World world, BlockPos p, Block block) {
        if (QCFSettings.blockUpdateOrderFix) {
            for(Direction d : DirectionUtils.directions) world.updateNeighbor(p.offset(d), block, p);
            return true;
        } else if (QCFSettings.parityRandomBlockUpdates) {
            for(Direction d : DirectionUtils.randomDirectionArray(p)) world.updateNeighbor(p.offset(d), block, p);
            return true;
        }
        return false;
    }

    public static boolean canUpdateNeighborsExceptWithOrder(World world, BlockPos pos,Block block, Direction dir) {
        if (QCFSettings.blockUpdateOrderFix) {
            for (Direction d : DirectionUtils.directions)
                if (dir != d) world.updateNeighbor(pos.offset(d), block, pos);
            return true;
        } else if (QCFSettings.parityRandomBlockUpdates) {
            for (Direction d : DirectionUtils.randomDirectionArray(pos))
                if (dir != d) world.updateNeighbor(pos.offset(d), block, pos);
            return true;
        }
        return false;
    }
}
