import java.util.Random;
public class De {
    Random random = new Random();
    int min;
    int max;
    public De(int min, int max){
        this.min = min;
        this.max = max;
    }
    public int roll(){
        return random.nextInt(min,max+1);
    }
}
