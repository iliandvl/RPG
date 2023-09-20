public class Orque extends Monstre{
    public Orque(String name){
        super(name);
        this.bonusFor = 1;
        De de = new De(1,6);
        this.inventory.or = de.roll();
        setOtherStats();

    }
}
