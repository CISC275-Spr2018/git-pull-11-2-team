

public enum Direction {

	NORTH("north"),
	NORTHEAST("northeast"),
	EAST("east"),
	SOUTHEAST("southeast"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	WEST("west"),
	NORTHWEST("northwest"),
	FACINGEAST("facingeast"),
	FACINGWEST("facingwest"),
	FACINGNORTH("facingnorth"),
	FACINGSOUTH("facingsouth");
	
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}


}
