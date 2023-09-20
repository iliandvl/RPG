public abstract class Hero extends Vivant {

    public Hero(String name) {
        super(name);
        this.isHero = true;
    }
    public void repos(){
        this.actualPV=this.statPV;
    }

}
