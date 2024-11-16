package net.scoobis.mixin;

import com.simibubi.create.content.kinetics.waterwheel.LargeWaterWheelBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LargeWaterWheelBlock.class, remap = false)
public class MixinLargeWaterWheel {
	@Unique
    private int ticksPerTick = 60;
    
    @Unique
    private int tickCounter = 0;

    @Inject( method = "tick", at = @At(value = "HEAD"), cancellable = true )
    public void tick(CallbackInfo ci) {
        tickCounter++;
        if (tickCounter % ticksPerTick != 0) {
            ci.cancel();
        }
    }
}