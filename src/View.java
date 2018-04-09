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
    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    final int bmCount = 8;
    JFrame frame = new JFrame();
    private static int xloc = 0;
    private static int yLoc = 0;
    private static Direction curDir = Direction.SOUTHEAST;
	private static final Color BACKGROUND_COLOR = Color.GRAY;
	static boolean startW;
    static int bCount;
    public View() {
    	
    	
    	BufferedImage[] img = new BufferedImage[bmCount];
    	img[0] = createImage("orc_forward_", Direction.NORTH);
    	img[1] = createImage("orc_forward_", Direction.NORTHEAST);
    	img[2] = createImage("orc_forward_", Direction.EAST);
    	img[3] = createImage("orc_forward_", Direction.SOUTHEAST);
    	img[4] = createImage("orc_forward_", Direction.SOUTH);
    	img[5] = createImage("orc_forward_", Direction.SOUTHWEST);
    	img[6] = createImage("orc_forward_", Direction.WEST);
    	img[7] = createImage("orc_forward_", Direction.NORTHWEST);
    	
    	pics = new BufferedImage[bmCount][frameCount];
    	for(int j = 0; j < bmCount; j++) {
    		for(int i = 0; i < frameCount; i++) {
    			pics[j][i] = img[j].getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    		}  
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
    
    private BufferedImage createImage(String imgName, Direction dir){
    	BufferedImage bufferedImage;
    	System.out.println(imgName + dir.getName());
    	try {
    		bufferedImage = ImageIO.read(new File("images/orc/" + imgName + dir.getName() + ".png"));
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
