public class Inventory {
    String name;
    double or=0;
    double cuir=0;

    public Inventory(String name){
        this.name=name;
    }
    public Inventory(String name, double or, double cuir){
        this.name=name;
        this.or=or;
        this.cuir=cuir;
    }
    private void setOr(double or){
        this.or=or;
    }
    private void setCuir(double cuir){
        this.cuir=cuir;
    }
    public double getOr(){
        return or;
    }
    public double getCuir(){
        return cuir;
    }
    public void addOr(double or){
        this.or+=or;
    }
    public void addCuir(double cuir){
        this.cuir+=cuir;
    }
}
