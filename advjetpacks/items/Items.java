package advjetpacks.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class Items {

	//ITEMS
	public static Item GyroJetpack;
	
	public static void InitItems() {
		GyroJetpack = new ItemGyroJetpack(16000);
	}

	public static void RegisterLanguage() {
		LanguageRegistry.addName(GyroJetpack, "Gyroscopic Jetpack");
	}
}


