import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Iterator;
//import java.util.Comparator<CarFunctions>;

class rich_ManageCarData implements ManageCarDataFunctions
{
    private final ArrayList<CarFunctions> carList;
    private final PriorityQueue<CarFunctions> carListByTotalRange;
    private final PriorityQueue<CarFunctions> carListByRemainingRange;

    public rich_ManageCarData () {
        carList = new ArrayList<>();
        carListByTotalRange = new PriorityQueue<>(new TotalRangeComparator());
        carListByRemainingRange = new PriorityQueue<>(new RemainingRangeComparator());
    }

    public void readData(String filename) {
        try {
            java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(filename)));
            
            String inn;
            while((inn = input.readLine()) != null ) {
                StringTokenizer st = new StringTokenizer(inn, "\t");

                String id = st.nextToken();
                int fuelEconomy = Integer.parseInt(st.nextToken());
                int fuelCapacity = Integer.parseInt(st.nextToken());
                double remainingFuel = Double.parseDouble(st.nextToken());

                CarFunctions c = new rich_Car(id, fuelEconomy, fuelCapacity, remainingFuel);
                carList.add(c);
                carListByRemainingRange.add(c);
                carListByTotalRange.add(c);
            }

            

        } 
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

    public ArrayList<CarFunctions> getCarList() {
        ArrayList<CarFunctions> CopyCarList = new ArrayList<>();
        for( int i = 0; i < carList.size(); i++ ) {
            CopyCarList.add(carList.get(i));
        }
        return CopyCarList;
    }

    public PriorityQueue<CarFunctions> getCarListByTotalRange() {
        PriorityQueue<CarFunctions> QCarListTR = new PriorityQueue<>(new TotalRangeComparator());
        for( int i = 0; i < carList.size(); i++ ) {
            QCarListTR.add(carList.get(i));
        }
        return QCarListTR;
    }

    public ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator() {
        ArrayList<CarFunctions> CopyCarListTR = new ArrayList<>();
        Iterator it = carListByTotalRange.iterator();
        while (it.hasNext() ) {
            CopyCarListTR.add((CarFunctions) it.next());
        }
        return CopyCarListTR;
    }

    public PriorityQueue<CarFunctions> getCarListByRemainingRange() {
        PriorityQueue<CarFunctions> QCarListRR = new PriorityQueue<>(new RemainingRangeComparator());
        for( int i = 0; i < carList.size(); i++ ) {
            QCarListRR.add(carList.get(i));
        }
        return QCarListRR;
    }
    
    public ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator() {
        ArrayList<CarFunctions> CopyCarListRR = new ArrayList<>();
        Iterator it = carListByRemainingRange.iterator();
        while (it.hasNext() ) {
            CopyCarListRR.add((CarFunctions) it.next());
        }
        return CopyCarListRR;
    }

    public ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, double maxTotalRange) {
        ArrayList<String> arsTR = new ArrayList<>();
        
        while (carListByTotalRange.size() > 0) {
            CarFunctions e = carListByTotalRange.poll();
            if (e.getTotalRangeInMiles() >= minTotalRange && e.getTotalRangeInMiles() <= maxTotalRange) {
                CarFunctions currentCar = e;
                String currentCarString = currentCar.toString();
                //int index = carList.indexOf(currentCar);
                if (carList.indexOf(currentCar) != -1) {
                    currentCarString = currentCarString + "\t" + carList.indexOf(currentCar);
                }
                for (CarFunctions r : carList) {
                    if (r.getFuelEconomyInMilesPerGallon() == e.getFuelEconomyInMilesPerGallon())  {
                        currentCarString = currentCarString + "\t" + carList.indexOf(r);
                    }
                }
                arsTR.add(currentCarString);
            }
        }
        for( int i = 0; i < carList.size(); i++ ) {
            carListByTotalRange.add(carList.get(i));
        }
        return arsTR;
    }
    
    public ArrayList<String> getCarListByRemainingRangeViaPoll (double minRemainingRange, double maxRemainingRange) {
        //System.out.println("we got in");
        ArrayList<String> arsRR = new ArrayList<>();
        while (carListByRemainingRange.size() > 0) {
            //System.out.println("we in while");
            CarFunctions e = carListByRemainingRange.poll();
            if (e.getRemainingRangeInMiles() >= minRemainingRange && e.getRemainingRangeInMiles() <= maxRemainingRange) {
                CarFunctions currentCar = e;
                String currentCarString = currentCar.toString();
                //int index = carList.indexOf(currentCar);
                if (carList.indexOf(currentCar) != -1) {
                    currentCarString = currentCarString + "\t" + carList.indexOf(currentCar);
                }
                else {
                    System.out.println("big error lol");
                }
                for (CarFunctions r : carList) {
                    if (r.getFuelEconomyInMilesPerGallon() == e.getFuelEconomyInMilesPerGallon())  {
                        currentCarString = currentCarString + "\t" + carList.indexOf(r);
                    }
                }
                //System.out.println("we adding to ars");
                arsRR.add(currentCarString);
            }
        }
        //System.out.println("we givin back to th eppl");
        for( int i = 0; i < carList.size(); i++ ) {
            carListByRemainingRange.add(carList.get(i));
        }
        
        return arsRR;
    }



}
