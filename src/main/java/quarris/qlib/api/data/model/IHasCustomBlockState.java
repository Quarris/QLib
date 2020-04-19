package quarris.qlib.api.data.model;

import quarris.qlib.mod.data.model.CustomBlockStateProvider;

public interface IHasCustomBlockState {

    void makeStateAndModel(CustomBlockStateProvider provider);

}
