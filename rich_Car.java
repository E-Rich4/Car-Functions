import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

class rich_Car implements CarFunctions
{
	private final String id;
	private final int FuelCapacityInGallons;
	private final int FuelEconomyInMilesPerGallon;
	private double CurrentFuelInGallons;
	

	public rich_Car (String id, int FuelEconomyInMilesPerGallon, int FuelCapacityInGallons, double CurrentFuelInGallons){
		this.id = id;
		this.FuelCapacityInGallons = FuelCapacityInGallons;
		this.FuelEconomyInMilesPerGallon = FuelEconomyInMilesPerGallon;
		this.CurrentFuelInGallons = CurrentFuelInGallons;
		
	}
	public String getId()
	{
		return id;
	}
	
	public  int getFuelCapacityInGallons() {
		return FuelCapacityInGallons;
	}

	public int getFuelEconomyInMilesPerGallon() {
		return FuelEconomyInMilesPerGallon;
	}

	public double getCurrentFuelInGallons() {
		return CurrentFuelInGallons;
	}

	public void setCurrentFuelInGallons(double v) {
		CurrentFuelInGallons = v;
	}

	public String toString()
	{
		return getId() + "\t" + getFuelEconomyInMilesPerGallon() + "\t" + getFuelCapacityInGallons() + "\t" + getCurrentFuelInGallons() + "\t" + getTotalRangeInMiles() + "\t" + getRemainingRangeInMiles();
	}

	public double getTotalRangeInMiles()
	{
		return getFuelCapacityInGallons()*getFuelEconomyInMilesPerGallon();
	}
	
	public double getRemainingRangeInMiles()
	{
		return getCurrentFuelInGallons()*getFuelEconomyInMilesPerGallon();
	}
}
