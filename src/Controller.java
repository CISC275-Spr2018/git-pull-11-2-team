import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class Controller implements KeyListener {
	private Model model;
	private View view;
	private Timer stepTimer;

	private static final int DRAW_DELAY = 5;

	private final Action stepAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			step();
		}
	};

	private void step() {
		// increment the x and y coordinates, alter direction if necessary
		if (view.startW == true) {
			model.updateModel();
			view.update(model.getX(), model.getY(), model.getDirect());
		}
	}

	// run the simulation
	public void start() {
		view = new View();
		view.setKeyListener(this);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				stepTimer = new Timer(DRAW_DELAY, stepAction);
				stepTimer.start();
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			model.setAttributes(1, Direction.NORTH, 0, 10);
			view.setAnimation(Animation.WALKING);
		} else if (key == KeyEvent.VK_DOWN) {
			model.setAttributes(2, Direction.SOUTH, 0, 10);
			view.setAnimation(Animation.WALKING);
		} else if (key == KeyEvent.VK_RIGHT) {
			model.setAttributes(3, Direction.EAST, 10, 0);
			view.setAnimation(Animation.WALKING);
		} else if (key == KeyEvent.VK_LEFT) {
			model.setAttributes(4, Direction.WEST, 10, 0);
			view.setAnimation(Animation.WALKING);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		switch(key) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_LEFT:
			model.stop();
			view.setAnimation(Animation.IDLE);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
