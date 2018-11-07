package cf.terminator.tiquality.mixin;

import cf.terminator.tiquality.interfaces.TiqualityChunk;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AnvilChunkLoader.class)
public class MixinAnvilChunkLoader {

    @Inject(method = "writeChunkToNBT", at=@At("RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void tiquality_onSaveChunk(Chunk chunk, World world, NBTTagCompound tag, CallbackInfo ci) {
        ((TiqualityChunk) chunk).tiquality_writeToNBT(tag);
    }

    @Inject(method = "readChunkFromNBT", at=@At("RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void tiquality_onChunkLoad(World worldIn, NBTTagCompound tag, CallbackInfoReturnable<Chunk> cir, int i, int j, Chunk chunk, NBTTagList nbttaglist, int k, ExtendedBlockStorage aextendedblockstorage[], boolean flag) {
        ((TiqualityChunk) chunk).tiquality_loadNBT(worldIn, tag);
    }


}
