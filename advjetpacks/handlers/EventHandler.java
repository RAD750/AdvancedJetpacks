package advjetpacks.handlers;

import advjetpacks.items.ItemGyroJetpack;
import advjetpacks.items.Items;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class EventHandler {
	@ForgeSubscribe
    public void onItemToss(ItemTossEvent event)
    {
            if (event.entityItem.getEntityItem().itemID == Items.GyroJetpack.itemID)
            {
             	ItemGyroJetpack.stopJetpack(event.player);   
            }

    }
}
