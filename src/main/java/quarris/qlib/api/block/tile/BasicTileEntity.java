package quarris.qlib.api.block.tile;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quarris.qlib.api.QLibApi;

import java.util.stream.Stream;

public class BasicTileEntity extends TileEntity {

    public BasicTileEntity(TileEntityType<?> type) {
        super(type);
    }

    @Override
    public final SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT compound = new CompoundNBT();
        this.write(compound);
        return new SUpdateTileEntityPacket(this.pos, 0, compound);
    }

    @Override
    public final CompoundNBT getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
        this.read(packet.getNbtCompound());
    }

    public void sendToClients() {
        if (!this.getWorld().isRemote) {
            ServerWorld world = (ServerWorld) this.getWorld();
            Stream<ServerPlayerEntity> entities = world.getChunkProvider().chunkManager.getTrackingPlayers(new ChunkPos(this.getPos()), false);
            SUpdateTileEntityPacket packet = this.getUpdatePacket();
            entities.forEach(e -> e.connection.sendPacket(packet));
        }
    }
}
