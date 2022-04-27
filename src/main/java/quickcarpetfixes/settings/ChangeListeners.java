package quickcarpetfixes.settings;

import com.google.common.collect.Interners;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import quickcarpet.api.settings.ChangeListener;
import quickcarpet.api.settings.ParsedRule;
import quickcarpetfixes.QCFSettings;
import quickcarpetfixes.mixins.accessors.TagKeyAccessor;

public class ChangeListeners {
    public static class WitherGolemSpawningFixValidator implements ChangeListener<Boolean> {
        @Override
        public void onChange(ParsedRule<Boolean> rule, Boolean previous) {
            ((CarvedPumpkinBlock)(Blocks.CARVED_PUMPKIN)).ironGolemPattern = null;
            ((CarvedPumpkinBlock)(Blocks.CARVED_PUMPKIN)).ironGolemDispenserPattern = null;
        }
    }

    public static class WorldBorderCollisionRoundingFixValidator implements ChangeListener<Boolean> {
        @Override
        public void onChange(ParsedRule<Boolean> rule, Boolean previous) {
            QCFSettings.scheduleWorldBorderReset = true;
        }
    }

    public static class TagKeyMemoryLeakFixValidator implements ChangeListener<Boolean> {
        @Override
        public void onChange(ParsedRule<Boolean> rule, Boolean previous) {
            if (rule.get() != previous)
                TagKeyAccessor.setInterner(rule.get() ? Interners.newWeakInterner() : Interners.newStrongInterner());
        }
    }
}
