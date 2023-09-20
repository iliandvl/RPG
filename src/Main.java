import java.util.Random;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        boolean validPosition;
        int i = 0;
        List<Monstre> monstres = new ArrayList<>();
        int x=0;
        int y=0;
        int race;
        while (i < 10){
            validPosition = false;
            while (!validPosition){
                x=random.nextInt(15);
                y=random.nextInt(15);
                validPosition = true;
                for (Monstre monstre : monstres) {
                    if (monstre.posX - x < 2 && monstre.posX - x > -2 && monstre.posY - y < 2 && monstre.posY - y > -2) {
                        validPosition = false;
                    }
                }

            }
            race = random.nextInt(3);
            switch (race) {
                case 0 -> monstres.add(new Loup("Loup"));
                case 1 -> monstres.add(new Orque("Orque"));
                case 2 -> monstres.add(new Dragonnet("Dragonnet"));
            }
            monstres.get(i).posX = x;
            monstres.get(i).posY = y;
            i++;

        }
        validPosition = false;
        while (!validPosition){
            x=random.nextInt(15);
            y=random.nextInt(15);
            validPosition = true;
            for (Monstre monstre : monstres) {
                if (monstre.posX - x < 2 && monstre.posX - x > -2 && monstre.posY - y < 2 && monstre.posY - y > -2) {
                    validPosition = false;
                }
            }

        }
        race = random.nextInt(2);
        Hero hero = switch (race) {
            case 0 -> new Humain("Hero");
            case 1 -> new Nain("Hero");
            default -> new Humain("Hero");
        };
        hero.posX=x;
        hero.posY=y;

        boolean gameOver = false;
        afficheZone(monstres,hero);
        while (!gameOver){
            boolean isMonster = false;
            for (i = 0; i < monstres.size(); i++){
                if (monstres.get(i).posX - hero.posX < 2 && monstres.get(i).posX - hero.posX > - 2 && monstres.get(i).posY - hero.posY < 2 && monstres.get(i).posY - hero.posY > - 2){
                    if (!monstres.get(i).isVisible){
                        monstres.get(i).isVisible = true;
                        System.out.println("Un "+monstres.get(i).name+" sauvage est apparu!");
                        afficheZone(monstres,hero);
                        wait(1000);
                    }
                    isMonster=true;
                }
            }

            if (!isMonster){
                hero.repos();
                deplace(hero);
                afficheZone(monstres,hero);
            } else {
                for (i = 0; i < monstres.size(); i++){
                    if (monstres.get(i).posX - hero.posX < 2 && monstres.get(i).posX - hero.posX > - 2 && monstres.get(i).posY - hero.posY < 2 && monstres.get(i).posY - hero.posY > - 2){
                        attaque(hero, monstres.get(i));
                        wait(1000);
                        if (!monstres.get(i).isAlive){
                            loot(hero, monstres.get(i));
                            monstres.remove(i);
                            i--;
                            System.out.println(monstres.size()+" monstres encore en vie");
                            wait(1000);
                            afficheZone(monstres,hero);
                        } else {
                            attaque(monstres.get(i), hero);
                            wait(1000);
                            if (!hero.isAlive){
                                System.out.println("Vous avez perdu!\n"+monstres.size()+" monstres encore en vie"+"\n"+hero.inventory.getCuir()+" cuirs et "+hero.inventory.getOr()+" or sont tombés au sol");
                                gameOver = true;
                            }
                        }
                    }
                }
            }
            if (monstres.isEmpty()){
                System.out.println("Vous avez gagné!\nVous avez ramassé "+hero.inventory.getCuir()+" cuirs et "+hero.inventory.getOr()+" or");
                gameOver = true;
            }

        }

    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void deplace(Hero hero){
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean validInput = false;
        while (!validInput){
            System.out.println("Dans quelle direction voulez-vous déplacer ?\nl - left\nr - right\nu - up\nd - down");
            input = scanner.nextLine();
            if (input.equals("l")){
                if (hero.posX != 0){
                    hero.posX--;
                    validInput = true;
                }
            } else if (input.equals("r")){
                if (hero.posX!= 14){
                    hero.posX++;
                    validInput = true;
                }
            } else if (input.equals("u")){
                if (hero.posY!= 0){
                    hero.posY--;
                    validInput = true;
                }
            } else if (input.equals("d")){
                if (hero.posY!= 14){
                    hero.posY++;
                    validInput = true;
                }
            } else {
                System.out.println("deplacement invalide");
            }
        }
    }
    public static void afficheZone(List<Monstre> monstres,Hero hero){
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                boolean isMonster = false;
                for (Monstre monstre : monstres) {
                    if (monstre.posX == j && monstre.posY == i) {
                        isMonster = true;
                        if (monstre.isVisible) {
                            System.out.print(monstre.name.charAt(0));
                        } else {
                            System.out.print(".");
                        }
                    }
                }
                if(hero.posX == j && hero.posY == i){
                    System.out.print("H");
                } else if (!isMonster) {
                    System.out.print(".");
                }
                System.out.print("  ");

            }

            System.out.println();
        }
    }
    public static void loot(Hero hero,Monstre monstre){
        hero.inventory.addCuir(monstre.inventory.getCuir());
        hero.inventory.addOr(monstre.inventory.getOr());
        System.out.println(hero.name + " a gagné " + monstre.inventory.getCuir() + " cuir et " + monstre.inventory.getOr() + " or");
    }
    public static void attaque(Vivant attaquant,Vivant deffenceur){
        int dammage = attaquant.frappe();
        deffenceur.takeDammage(dammage);
        System.out.println(attaquant.name+" attaque "+deffenceur.name+" et cause "+dammage+" points de dégats");
        if (!deffenceur.isAlive){
            System.out.println(deffenceur.name+" est mort");
        }
    }
}