package catcards.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import catcards.CatCardsMod;

public class CuloGordo extends CustomCard {

    public static final String ID = CatCardsMod.makeID("CuloGordo");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = "Culo Gordo";
    private static final String DESCRIPTION = "Gana !M! Metalizado.";

    private static final String IMG_PATH = CatCardsMod.makeCardPath("culo_gordo.png");

    private static final int COST = 2;
    private static final int METALLICIZE = 5;
    private static final int UPGRADE_METALLICIZE = 2; // +2 = 7 total

    public CuloGordo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER,
                CardColor.RED,  // Ironclad
                CardRarity.RARE,
                CardTarget.SELF);

        this.baseMagicNumber = METALLICIZE;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_METALLICIZE);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CuloGordo();
    }
}
