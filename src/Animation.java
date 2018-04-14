import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// This class contains useful public static final Animations.
// You cannot create additional Animations in other files; they should all be here.
// The only public method of an Animation is getCurrentFrameForDirection(d)

public abstract class Animation {
	public static final Animation WALKING = new WalkingAnimation();

	// All subclasses must implement these methods.
	protected abstract void load();
	public abstract BufferedImage getCurrentFrameForDirection(Direction d);

	// Will always load on construction.
	private Animation() {
		this.load();
	} // Prevent instantiation

	//---------- Convenience methods for inside subclasses:

	// loadImg(String name): Basically a convenience wrapper around ImageIO.read().
	protected BufferedImage loadImg(String name) {
		try {
			return ImageIO.read(new File("images/orc/"+name+".png"));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	protected BufferedImage[] splitTiled(BufferedImage source, int xframes, int yframes) {
		BufferedImage[] result = new BufferedImage[xframes*yframes];
		int frameW = source.getWidth()/xframes;
		int frameH = source.getHeight()/yframes;
		for(int x=0; x<xframes; x++)
			for(int y=0; y<yframes; y++)
				result[x+xframes*y] = source.getSubimage(
					x*frameW, y*frameH, frameW, frameH);
		return result;
	}

	private static class WalkingAnimation extends Animation {
		private BufferedImage[] east;
		int fnum;

		protected void load() {
			east = splitTiled(loadImg("orc_forward_east"), 10, 1);
		}

		public BufferedImage getCurrentFrameForDirection(Direction d) {
			return east[fnum++ % east.length];
		}
	};
}
