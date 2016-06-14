package alexndr.plugins.Fusion.modsupport;

import net.minecraftforge.fml.common.Loader;
import alexndr.api.helpers.game.RenderItemHelper;
import alexndr.plugins.Fusion.Settings;

public class ModSupport 
{
	private static boolean use_simpleores = false;

	@SuppressWarnings("unused")
	private static boolean use_netherrocks = false;

	/**
	 * find out and initialize what mods are loaded.
	 */
	public static void preInit()
	{
		use_simpleores = Loader.isModLoaded("simpleores") 
							&& Settings.enableSimpleOres.asBoolean();	
		use_netherrocks = Loader.isModLoaded("netherrocks") 
							&& Settings.enableNetherrocks.asBoolean();
	} // end preInit()
	
	/**
	 * called during pre-init phase by ProxyCommon, for Content-related stuff.
	 */
	public static void ContentPreInit() {
		if(use_simpleores) {
			ContentSimpleOres.doItems();
			ContentSimpleOres.doBlocks();
			ContentSimpleOres.doArmor();
			ContentSimpleOres.doTools();
			ContentSimpleOres.doAchievements();
		}
		// TODO netherrocks
	} // end ContentPreInit()
	
	/**
	 * only called by ProxyClient's preinit--that is, client-side pre-init stuff.
	 */
	public static void ClientPreInit(RenderItemHelper renderHelper) 
	{
		if(use_simpleores) {
			renderHelper.addBowRenderDetails(ContentSimpleOres.sinisite_bow);
			renderHelper.addBowRenderDetails(ContentSimpleOres.thyrium_bow);
		}
	} // end ClientPreInit()
	
	/**
	 * called during init phase by ProxyCommon.
	 */
	public static void Init() {
		if (use_simpleores) {
			ContentSimpleOres.setRepairMaterials(); 
			ContentSimpleOres.setAchievementTriggers();
		}
		// TODO add Netherrocks
	} // end Init()
	
	/**
	 * called during post-init phase by ProxyCommon.
	 */
	public static void PostInit() {
		// TODO mod interaction stuff.
	}
	
	/** 
	 * checks if relevant plugins loaded, and calls their 
	 * setToolAndArmorStats() methods.
	 */
	public static void setToolAndArmorStats() {
		if(use_simpleores) {
			ContentSimpleOres.setToolAndArmorStats();
		}
		// TODO Netherrocks
	} // end setToolAndArmorStats
	
	/**
	 * set up plugin ore dictionary entries.
	 */
	public static void doOreDictEntries() {
		if (use_simpleores) {
			RecipesSimpleOres.doOreDictRecipes();
		}
		// TODO Netherrocks
	} // end doOreDictEntries
	
	/**
	 * set up plugin recipes.
	 */
	public static void doRecipes() {
		if (use_simpleores) {
			RecipesSimpleOres.doRecipes();
		}		
		// TODO Netherrocks
	} // end doRecipes()

} // end class