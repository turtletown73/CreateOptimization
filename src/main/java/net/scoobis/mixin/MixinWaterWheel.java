package net.scoobis.mixin;

import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WaterWheelBlock.class)
public class MixinWaterWheel {
    @Unique
    private int tickCounter = 0;

    @Inject( method = "tick", at = @At(value = "HEAD"), cancellable = true )
    public void tick(CallbackInfo ci) {
        tickCounter++;
        int ticksPerTick = 60;
        if (tickCounter % ticksPerTick != 0) {
            ci.cancel();
        }
    }
}