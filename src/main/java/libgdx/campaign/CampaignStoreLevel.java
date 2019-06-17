package libgdx.campaign;

public class CampaignStoreLevel {

    private int level;
    private int crosswordLevel;
    private int status;
    private int starsWon;

    public CampaignStoreLevel(CampaignLevel campaignLevel) {
        level = campaignLevel.getIndex();
        crosswordLevel = 0;
        starsWon = 0;
        status = CampaignLevelStatusEnum.IN_PROGRESS.getStatus();
    }

    public int getStarsWon() {
        return starsWon;
    }

    public void setStarsWon(int starsWon) {
        this.starsWon = starsWon;
    }

    public int getLevel() {
        return level;
    }

    public int getCrosswordLevel() {
        return crosswordLevel;
    }

    public void setCrosswordLevel(int crosswordLevel) {
        this.crosswordLevel = crosswordLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
