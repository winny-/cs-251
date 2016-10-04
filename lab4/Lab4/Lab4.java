package Lab4;

import java.io.PrintStream;
import java.util.Scanner;

public class Lab4 {
    public static void main(String[] args) {
        PrintStream out = System.out;
        Scanner in = new Scanner(System.in);
		
        IceCreamStat stat = new IceCreamStat();
		
        while(true) {
            out.print("Enter a flavor (blank line to exit): ");
            String flavor = in.nextLine();
            if(flavor.equals("")) {
                break;
            }
            out.print("Enter number of scoops: ");
            int scoops = Integer.parseInt(in.nextLine());
            stat.consume(new IceCreamCone(flavor, scoops)); 
        }
        out.println(stat.getStatistics());
        in.close();
    }
 
}

class IceCreamStat {
    private int vanillaScoops = 0, chocolateScoops = 0, strawberryScoops = 0;
	
    public void consume(IceCreamCone cone) {
        String actualFlavor = cone.getFlavor().toLowerCase().trim();
        switch (actualFlavor) {
        case "vanilla":
        case "chocolate":
        case "strawberry":
            consumeWantedFlavor(cone, actualFlavor);
            break;
        default:
            System.out.printf("Flavor %s is not desired...\n", actualFlavor);
            break;
        }
    }

    // Factored out to reduce indentation.
    private void consumeWantedFlavor(IceCreamCone cone, String actualFlavor) {
        while (cone.getScoops() > 0) {
            cone.eat();
            switch (actualFlavor) {
            case "vanilla":
                vanillaScoops++;
                break;
            case "chocolate":
                chocolateScoops++;
                break;
            case "strawberry":
                strawberryScoops++;
                break;
            }
            System.out.printf("MMM, %s\n", actualFlavor);
        }
    }

    public String getStatistics() {
        String ret = "\n";
		
        ret = ret + String.format("%-10s | %-6s\n", "Flavor", "Scoops");
        ret = ret + String.format("-------------------\n");
        ret = ret + String.format("%-10s | %-6d\n", "Vanilla", vanillaScoops);
        ret = ret + String.format("%-10s | %-6d\n", "Chocolate", chocolateScoops);
        ret = ret + String.format("%-10s | %-6d\n", "Strawberry", strawberryScoops);
		
        return ret;
    }
}

class IceCreamCone {
    private String flavor;
    private int scoops;

    IceCreamCone(String flavor, int scoops) {
        this.flavor = flavor;
        this.scoops = scoops;
    }

    String getFlavor() { 
        return this.flavor;
    }
    int getScoops() { 
        return this.scoops;
    }
    void eat() {
        assert(this.scoops > 0);
        this.scoops--;
    }
}

