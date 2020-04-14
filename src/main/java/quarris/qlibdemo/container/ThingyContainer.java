package quarris.qlibdemo.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public class ThingyContainer extends Container {

    public ThingyContainer(int id) {
        super(ModContainers.THINGY_CONTAINER_TYPE, id);
    }

    public ThingyContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
