package catcards;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;

import catcards.cards.CuloGordo;
import catcards.cards.DepredadorDiurno;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class CatCardsMod implements EditCardsSubscriber, EditStringsSubscriber, PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(CatCardsMod.class.getName());
    public static final String MOD_ID = "CatCardsMod";
    private static final String VERSION = "1.1.2";

    // Paths
    public static final String CARD_IMAGES_PATH = "catcards/images/cards/";

    public CatCardsMod() {
        logger.info("Cat Cards Mod v" + VERSION + " cargando...");
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new CatCardsMod();
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Cat Cards Mod - Post Initialize");
        logger.info("Cartas de gatos cargadas: Culo Gordo y Depredador Diurno (Buggy)");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Registrando cartas de gatos...");

        BaseMod.addCard(new CuloGordo());
        BaseMod.addCard(new DepredadorDiurno());

        logger.info("Cartas registradas correctamente");
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class,
                "catcards/localization/CardStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                "catcards/localization/PowerStrings.json");
    }

    public static String makeCardPath(String resourcePath) {
        return CARD_IMAGES_PATH + resourcePath;
    }

    public static String makeID(String idText) {
        return MOD_ID + ":" + idText;
    }
}
