
public class Doors {

	private Door doors[];
	private Door door;
	
	public Doors() {
		doors = new Door[3];
		for (int i = 0; i < doors.length; i++) 
			doors[i] = new Door();
	}
	
	public void hideCar() {
		int car = (int) (Math.random() * 3);
		doors[car].hasCar = true;
	}
	
	public void resetDoors() {
		for (Door d : doors) 
			d.reset();
	}
	
	public void pickDoor() {
		Door door = doors[(int) (Math.random() * 3)];
		this.door = door;
		 
		while (true) {
			Door doorToOpen = doors[(int) (Math.random() * 3)];
			if (doorToOpen != door && doorToOpen.hasCar == false) {
				doorToOpen.open = true;
				break;
			}
		}
		
	}
	
	public boolean switchDoor() {
		Door d;
		//I'm sure there's a better way to do this
		for (Door someDoor : doors) {
			d = someDoor;
			if (d != door && !d.open) {
				door = d;
				break;
			}
		}
		return door.hasCar;
	}
	
	public boolean stay() {
		return door.hasCar;
	}
	
	public static void main(String[] args) {
		Doors d = new Doors();
		
		int switchGameWin, stayGameWin;
		switchGameWin = 0;
		stayGameWin   = 0;
		
		final int totalTrials = 1000000;
		
		for (int i = 0; i < totalTrials; i++) {
			d.hideCar();
			
			d.pickDoor();
			if (d.switchDoor())
				switchGameWin++;
			
			d.pickDoor();
			if (d.stay()) 
				stayGameWin++;
			
			d.resetDoors();
		}
		
		System.out.println("Testing Monty Hall problem with " + totalTrials + " trials.");
		System.out.println("Won " + switchGameWin + " games out of " + totalTrials + " by switching. ");
		System.out.println("Won " + stayGameWin + " games out of " + totalTrials + " by staying. ");
		System.out.println("Better stick with " + (switchGameWin > stayGameWin? "switching" : "staying") + "!");
	}
	
}
