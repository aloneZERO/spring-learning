package testdi.knight;

import testdi.quest.RescuePrincessQuest;

public class DamselRescuingKnight implements Knight {

    private RescuePrincessQuest quest;

    public DamselRescuingKnight() {
        this.quest = new RescuePrincessQuest();
    }

    public void embarkOnQuest() {
        quest.embark();
    }

}
