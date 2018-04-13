import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Animation {
	public static final Animation WALKING = new Animation() {};

	private BufferedImage bi;

	private Animation() {
		// TEMP for testing
		try {
			bi = ImageIO.read(new File("images/orc/orc_forward_east.png"));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	} // Prevent instantiation

	public BufferedImage getCurrentFrameForDirection(Direction d) {
		return bi;
	}
}
