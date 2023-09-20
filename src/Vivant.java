public abstract class Vivant {
    String name;
    int statEnd;
    int statFor;
    int statPV;
    int actualPV;

    int bonusEnd = 0;
    int bonusFor = 0;

    int modEnd;
    int modFor;

    boolean isHero;
    boolean isAlive;
    Inventory inventory;

    int posX;
    int posY;

    public Vivant(String name) {
        this.name = name;
        this.inventory = new Inventory(this.name);
        this.statEnd = randomStat();
        this.statFor = randomStat();
        setOtherStats();


        this.isAlive = true;
    }
    public int getPosX() {
        return this.posX;
    }
    public int getPosY() {
        return this.posY;
    }
    public void setOtherStats(){
        this.modEnd =(this.statEnd + this.bonusEnd)/5-1;
        if (this.modEnd > 2){
            this.modEnd = 2;
        }
        this.modFor =(this.statFor + this.bonusFor)/5-1;
        if (this.modFor > 2){
            this.modFor = 2;
        }
        this.statPV=this.statEnd + this.bonusEnd + this.modEnd;
        this.actualPV = this.statPV;
    }
    private void setStatEnd(int stat) {
        this.statEnd = stat;
    }
    private void setStatFor(int stat) {
        this.statFor = stat;
    }
    private void setStatPV (int stat){
        this.statPV = stat;
    }
    private void setActualPV(int stat){
        this.actualPV = stat;
    }
    public int getStatEnd() {
        return this.statEnd;
    }
    public int getStatFor() {
        return this.statFor;
    }
    private int getStatPV() {
        return this.statPV;
    }
    private int getActualPV() {
        return this.actualPV;
    }
    public void takeDammage(int dammage){
        setActualPV((getActualPV() - dammage));
        if (getActualPV() <= 0) {
            setActualPV(0);
            this.isAlive = false;
        }
    }

    public int frappe(){
        De de =  new De(1,4);
        return de.roll() + this.modFor;
    }
    private int randomStat(){
        int lowest=0;
        int total=0;
        int lancer;
        De de = new De(1,6);
        for (int i=0; i<4; i++) {
            lancer = de.roll();
            if (lancer < lowest)
                lowest = lancer;
            total += lancer;
        }
        total -= lowest;
        return total;

    }

}
