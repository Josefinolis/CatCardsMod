package catcards.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import catcards.CatCardsMod;

public class PesoMuerto extends CustomCard {

    public static final String ID = CatCardsMod.makeID("PesoMuerto");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = "Peso Muerto";
    private static final String DESCRIPTION = "Gana !B! de Bloqueo. NL Aplica !M! Weak.";
    private static final String UPGRADE_DESCRIPTION = "Gana !B! de Bloqueo. NL Aplica !M! Weak.";

    private static final String IMG_PATH = CatCardsMod.makeCardPath("peso_muerto.png");

    private static final int COST = 2;
    private static final int BLOCK = 16;
    private static final int UPGRADE_BLOCK = 6; // +6 = 22 total
    private static final int WEAK = 1;
    private static final int UPGRADE_WEAK = 1; // +1 = 2 total

    public PesoMuerto() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL,
                CardColor.COLORLESS,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = WEAK;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Ganar bloqueo
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block));

        // Aplicar Weak al enemigo
        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK);
            this.upgradeMagicNumber(UPGRADE_WEAK);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PesoMuerto();
    }
}
