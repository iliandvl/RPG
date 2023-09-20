public class Loup extends Monstre{
    public Loup(String name){
        super(name);
        De de = new De(1,4);
        this.inventory.cuir = de.roll();

    }

}
