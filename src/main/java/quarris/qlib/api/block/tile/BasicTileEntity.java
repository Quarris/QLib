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

    public void readFromNBT(CompoundNBT nbt) {
        QLibApi.SERIALIZER.deserialize(this.getClass(), this, nbt);
    }

    public void writeToNBT(CompoundNBT nbt) {
        nbt.put("TileEntity", QLibApi.SERIALIZER.serialize(this));
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        this.readFromNBT(nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public final SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT compound = new CompoundNBT();
        this.writeToNBT(compound);

        return new SUpdateTileEntityPacket(this.pos, 0, compound);
    }

    @Override
    public final CompoundNBT getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.readFromNBT(tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
        this.readFromNBT(packet.getNbtCompound());
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    public void sendToClients() {
        ServerWorld world = (ServerWorld) this.getWorld();
        Stream<ServerPlayerEntity> entities = world.getChunkProvider().chunkManager.getTrackingPlayers(new ChunkPos(this.getPos()), false);
        SUpdateTileEntityPacket packet = this.getUpdatePacket();
        entities.forEach(e -> e.connection.sendPacket(packet));
    }

}
