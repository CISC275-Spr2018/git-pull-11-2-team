import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel{
	private final static int frameWidth = 500;
    private final static int frameHeight = 300;
    private final static int imgWidth = 165;
    private final static int imgHeight = 165;
    JFrame frame = new JFrame();
    private static int xloc = 0;
    private static int yLoc = 0;
    private static Direction curDir = Direction.EAST;
	private static final Color BACKGROUND_COLOR = Color.GRAY;
	static boolean startW;
    static int bCount;
    private Animation animation = Animation.IDLE;
    public View() {
    	// Preload animations
		Animation.preload();
    	
    	JButton b = new JButton("stop/start");
    	b.addActionListener( new ActionListener()
    	{
    	    @Override
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	if(bCount % 2 == 0)
    	    	{
    	    		startW = true;
    	    		bCount++;
    	    	}
    	    	else
    	    	{
    	    		startW = false;
    	    		bCount++;
    	    	}
    	    }
    	});
    	
    	b.setBounds(100,0,90,30);
    	b.setFocusable(false);
    	frame.add(b);
    	frame.setFocusable(true);
    	frame.getContentPane().add(this);
    	frame.getContentPane().setBackground(BACKGROUND_COLOR);
		this.setBackground(BACKGROUND_COLOR);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    }

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
    
    public void setKeyListener(KeyListener listener) {
    	frame.addKeyListener(listener);
    }
    
    public void paint(Graphics g) {
		super.paint(g);
    	g.drawImage(this.animation.getCurrentFrameForDirection(this.curDir), xloc, yLoc, BACKGROUND_COLOR, this);
    }
    
    public int getHeight() {
    	return frameHeight;
    }
    
    public int getWidth() {
    	return frameWidth;
    }
    
    public int getImageHeight() {
    	return imgHeight;
    }
    
    public int getImageWidth() {
    	return imgWidth;
    }
    
    public void update(int x, int y, Direction dir) {
    	xloc = x;
    	yLoc = y;
    	curDir = dir;
    	frame.repaint();
    }
}
    
    
/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
