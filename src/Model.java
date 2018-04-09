import java.awt.event.KeyEvent;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model
{
	int winW;
	int winH;
	int imgW;
	int imgH;

	int xLoc = 0;
	int yLoc = 0;

	final int xIncr = 8;
	final int yIncr = 2;

	Direction curDir = Direction.SOUTHEAST;
	
	int dir = 4;

	public Model(int winW, int winH, int imgW, int imgH) 
	{
		this.winW = winW;
		this.winH = winH;
		this.imgW = imgW;
		this.imgH = imgH;
	}

	public void updateLocationAndDirection()
	{
		
		//System.out.println("xLoc: " + xLoc + " yLoc: " + yLoc);
		updateDirection();
		updateLocation();

	}
	
	public void updateDirection() {
		if((xLoc + imgW) >= winW && curDir == Direction.SOUTHEAST) //1
		{//reaches the right wall 
			dir = 1;
			curDir = Direction.SOUTHWEST;
		}
		else if(xLoc <= 0 && curDir == Direction.NORTHWEST) {//reaches left wall //2
			dir = 2;
			curDir = Direction.NORTHEAST;
		}
		else if((yLoc + imgH) >= winH && curDir == Direction.SOUTHWEST) {//reaches bottom wall //3
			dir = 3;
			curDir = Direction.NORTHWEST;
		}
		else if(yLoc <= 0 && curDir == Direction.NORTHEAST) {//reaches top wall //4
			dir = 4;
			curDir = Direction.SOUTHEAST;
		}else if((yLoc + imgH/5)<= 0 && curDir == Direction.NORTH) {
			dir = 6;
			curDir = Direction.SOUTH;
		}else if((yLoc + imgH) >= winH && curDir == Direction.SOUTH) {
			dir = 5;
			curDir = Direction.NORTH;
		}else if((xLoc + imgW) >= winW && curDir == Direction.EAST) {
			dir = 8;
			curDir = Direction.WEST;
		}else if(xLoc <= 0 && curDir == Direction.WEST) {
			dir = 7;
			curDir = Direction.EAST;
		}
	}
	
	public void updateLocation() {
		switch(dir){
		case 1: //right wall; for him to go sw
			xLoc-=(xIncr/2);
			yLoc+=yIncr;
			break;
		case 2: //left wall; for him to go ne
			xLoc+=(xIncr/2);
			yLoc-=yIncr;
			break;
		case 3: //bottom wall; for him to go nw
			xLoc-=xIncr;
			yLoc-=yIncr; 
			break;
		case 4: //top wall; for him to go se
			xLoc+=xIncr;
			yLoc+=yIncr;
			break;
		case 5: //bottom wall; for him to go n
			yLoc-=yIncr;
			break;
		case 6: //top wall; for him to go s
			yLoc+=yIncr;
			break;
		case 7: //left wall; for him to go e
			xLoc+=xIncr;
			break;
		case 8: //right wall; for him to go w
			xLoc-=xIncr;
			break;
		}
	
	}
	
	public void setDirection(int dir, Direction direction) {
		this.dir = dir;
		this.curDir = direction;
	}

	public int getX()
	{
		return xLoc;
	}

	public int getY()
	{
		return yLoc;
	}

	public Direction getDirect()
	{
		return curDir;
	}


}