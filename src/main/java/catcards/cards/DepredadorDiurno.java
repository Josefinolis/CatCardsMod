package catcards.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import catcards.CatCardsMod;

public class DepredadorDiurno extends CustomCard {

    public static final String ID = CatCardsMod.makeID("DepredadorDiurno");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = "Depredador Diurno";
    private static final String DESCRIPTION = "Inflige !D! de da\u00f1o. NL Si el enemigo tiene Vulnerable, inflige !D! de da\u00f1o adicional.";
    private static final String UPGRADE_DESCRIPTION = "Inflige !D! de da\u00f1o. NL Si el enemigo tiene Vulnerable, inflige !D! de da\u00f1o adicional.";

    private static final String IMG_PATH = CatCardsMod.makeCardPath("depredador_diurno.png");

    private static final int COST = 2;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 4; // +4 = 16 total

    public DepredadorDiurno() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK,
                CardColor.COLORLESS,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY);

        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Primer ataque
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        // Si tiene Vulnerable, atacar de nuevo
        if (m != null && m.hasPower("Vulnerable")) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DepredadorDiurno();
    }
}
