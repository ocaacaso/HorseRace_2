public class ThreadCavallo implements Runnable
{
    public String name;
    public boolean last;
    public int speed;
    public int roadLength;
    public Corsa race;

    public ThreadCavallo(Corsa raceName, int rl, String n, int ms, boolean l)
    {
        roadLength = rl;
        name = n;
        speed = ms;
        last = l;
        race = raceName;
    }

    @Override
    public void run()
    {
        boolean injured = false;
        int chance = 61;
        int accidentPercent = chance - (speed * 2);
        int accidentPosition = 0;
        for(int i = 0; i <= this.roadLength; i+=speed)
        {
            System.out.println(name + " ha percorso " + i + " metri");
            try
            {
                Thread.sleep(3000-(speed)*100);
            } catch (InterruptedException e){}
            
            int position = (int)(Math.random() * accidentPercent); //random int from 0 to 10, chance for an accident to occur
            int obstacle = (int) (Math.random() * accidentPercent); //sudden obstacle on the field
            if(obstacle == position) //if the obstacle is the same value as the horse's position, the horse gets injured and exits the race
            {
                System.out.println(name + " si è infortunato e deve uscire dalla gara");
                injured = true;
                accidentPosition = i;
                break;
            }
        }

        if(!injured)
        {
            System.out.println("Il cavallo " + name + " è arrivato");
            race.generateRanking(name);
        }
    }
}