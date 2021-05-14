package advjetpacks.handlers;

import java.util.List;

import advjetpacks.items.Items;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingHandler {
	public static void AddRecipes() {	
		
		ItemStack cell64 = ic2.api.Items.getItem("cell").copy();
		cell64.stackSize = 64;
		
		GregtechHandler.addCannerRecipe(
				new ItemStack(Items.GyroJetpack, 1, Items.GyroJetpack.getMaxDamage() - 1),
				GregtechHandler.getGregTechItem(2, 64, 18),
				new ItemStack(Items.GyroJetpack, 1),
				cell64,
				40,
				3);
		
		GameRegistry.addShapedRecipe(
				new ItemStack(Items.GyroJetpack, 1, Items.GyroJetpack.getMaxDamage() - 1),
				new Object[] {
						"ICI",
						"ITI",
						"D D",
						'C', ic2.api.Items.getItem("advancedCircuit"),
						'T', ic2.api.Items.getItem("fuelCan"),
						'D', GregtechHandler.getGregTechItem(3, 3, 1),
						'I', ic2.api.Items.getItem("refinedIronIngot")
				});
		
	}
}
