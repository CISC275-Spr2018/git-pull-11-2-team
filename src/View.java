import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel{
	private final static int frameWidth = 500;
    private final static int frameHeight = 300;
    private final static int imgWidth = 165;
    private final static int imgHeight = 165;
    final int frameCount = 20; //switched this to 20 because 10 and 4 both divide into 20 evenly
    int picNum = 0;
    BufferedImage[][] pics;
    final int bmCount = 12; //changed this to 9
    JFrame frame = new JFrame();
    private static int xloc = 0;
    private static int yLoc = 0;
    private static Direction curDir = Direction.SOUTHEAST;
	private static final Color BACKGROUND_COLOR = Color.GRAY;
	static boolean startW;
    static int bCount;
    public View() {
    	
    	
    	BufferedImage[] img = new BufferedImage[bmCount];
    	img[0] = createImage("orc_forward_north");
    	img[1] = createImage("orc_forward_northeast");
    	img[2] = createImage("orc_forward_east");
    	img[3] = createImage("orc_forward_southeast");
    	img[4] = createImage("orc_forward_south");
    	img[5] = createImage("orc_forward_southwest");
    	img[6] = createImage("orc_forward_west");
    	img[7] = createImage("orc_forward_northwest");
    	img[8] = createImage("orc_idle_ewns");
    	
    	pics = new BufferedImage[bmCount][frameCount];
    	for(int j = 0; j < bmCount - 4; j++) { //this would take care of the first 8 pics but not the rest that is why i subtracted
    		for(int i = 0; i < frameCount; i++) {
    			pics[j][i] = img[j].getSubimage(imgWidth*(i % 10), 0, imgWidth, imgHeight);
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    		}  
    	}
    	
    	for(int c = 0; c < frameCount; c++) {
    		pics[8][c] = img[8].getSubimage(imgWidth*(c % 4), 0, imgWidth, imgHeight);
    		pics[9][c] = img[8].getSubimage(imgWidth*(c % 4), imgHeight, imgWidth, imgHeight);
    		pics[10][c] = img[8].getSubimage(imgWidth*(c % 4), imgHeight * 2, imgWidth, imgHeight);
    		pics[11][c] = img[8].getSubimage(imgWidth*(c % 4), imgHeight * 3, imgWidth, imgHeight);
    	}
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
    
    public void setKeyListener(KeyListener listener) {
    	frame.addKeyListener(listener);
    }
    
    public void paint(Graphics g) {
		super.paint(g);
    	picNum = (picNum + 1) % frameCount;
    	g.drawImage(pics[curDir.ordinal()][picNum], xloc, yLoc, BACKGROUND_COLOR, this);
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
    	
    	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    //changed this because the current setup did not allow other images to be created
    private BufferedImage createImage(String directory){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("images/orc/" + directory + ".png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
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
