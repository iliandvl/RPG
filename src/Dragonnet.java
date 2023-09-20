public class Dragonnet extends Monstre{
    public Dragonnet(String name){
        super(name);
        De de = new De(1,4);
        this.inventory.cuir = de.roll();
        de = new De(1,6);
        this.inventory.or = de.roll();
        this.bonusEnd = 1;
        setOtherStats();

    }
}
