// the powerhouse of the cell

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;

public class Mitochondria {

	private static Timer time;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrameHandler jf = new JFrameHandler();
	    jf.setTitle("Rope Simulation");
	    jf.setBackground(new Color(0,0,0));
	    jf.setBounds(100,50,500,500);
	    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf.setVisible(true);
	}

	static class JFrameHandler extends JFrame implements ActionListener, MouseListener, KeyListener {
		private int pointCount =  14;
		private double mouse_x = 50;
		private double mouse_y = 50;
		
		private boolean showNodes = false;
		private Random gen = new Random();
		private double[] rope_x1 = new double[pointCount];
		private double[] rope_y1 = new double[pointCount];
		private double[] rope_x2 = new double[pointCount];
		private double[] rope_y2 = new double[pointCount];
		private double[] velocity_x = new double[pointCount];
		private double[] velocity_y = new double[pointCount];
		
		public JFrameHandler(){
			time = new Timer(1,this);
			time.start();
			
			addMouseListener(this);
			addKeyListener(this);
			
			// Initialize points in rope_x1 and rope_y1
			System.out.println("Intitalizing");
			for (int i = 0; i < pointCount; i++){
				//if (i != 249) {
					rope_x1[i] = 0 + gen.nextInt(500);//10+(i * 2);
					rope_y1[i] = (double) i;
					rope_x2[i] = rope_x1[i];
					rope_y2[i] = rope_y1[i];
				//}else{
					/*rope_x1[i] = 500;
					rope_y1[i] = -50;
					rope_x2[i] = rope_x1[i];
					rope_y2[i] = rope_y1[i];
					*/
				//}
			}
			System.out.println("Intitalized");
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			// draw stuff here
			for (int i = 0; i < pointCount; i ++){
				if (i != pointCount - 1) {
					if (showNodes == true){
						g.setColor(new Color(0,255,0));
						g.drawOval((int) rope_x1[i]-5, (int) rope_y1[i]-5, 10, 10);
					}
					g.setColor(new Color((i * 255)/pointCount,255,255));
					g.drawLine((int) rope_x1[i],(int) rope_y1[i], (int) rope_x1[i+1], (int) rope_y1[i+1]);
				}
			}
			// stop here
	        repaint();
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			repaint();
			
			for (int i = 0; i < pointCount; i++){
				if (i != 0 && i != pointCount - 1){
					double x_vector1 = rope_x1[i-1] - rope_x1[i];
					double y_vector1 = rope_y1[i-1] - rope_y1[i];
					double magnitude1 = Math.sqrt(Math.pow((double) x_vector1,2.00) + Math.pow((double) y_vector1,2.00));
					double extension1 = magnitude1 + 50;
					
					double x_vector2 = rope_x1[i+1] - rope_x1[i];
					double y_vector2 = rope_y1[i+1] - rope_y1[i];
					double magnitude2 = Math.sqrt(Math.pow((double) x_vector2,2.00) + Math.pow((double) y_vector2,2.00));
					double extension2 = magnitude2 + 50;
					
					double xv = (x_vector1 / magnitude1 * extension1) + (x_vector2 / magnitude2 * extension2);
					double yv = (y_vector1 / magnitude1 * extension1) + (y_vector2 / magnitude2 * extension2);
					if (rope_y1[i] < 500){
						yv += 10;
					}
					
					//rope_x2[i] = rope_x1[i] + (xv * .01);
					//rope_y2[i] = rope_y1[i] + (yv * .01);
					
					velocity_x[i] = velocity_x[i] * .00875 + (xv * .05);
					velocity_y[i] = velocity_y[i] * .00875 + (yv * .05);//.875
					rope_x2[i] = rope_x1[i] + velocity_x[i];
					rope_y2[i] = rope_y1[i] + velocity_y[i];
				} else if (i == pointCount - 1) {
					/*double x_vector1 = rope_x1[i-1] - rope_x1[i];
					double y_vector1 = rope_y1[i-1] - rope_y1[i];
					double magnitude1 = Math.sqrt(Math.pow((double) x_vector1,2.00) + Math.pow((double) y_vector1,2.00));
					double extension1 = magnitude1 - .5;
					
					double xv = (x_vector1 / magnitude1 * extension1);
					double yv = (y_vector1 / magnitude1 * extension1) + 196;
					
					//rope_x2[i] = rope_x1[i] + (xv * 1);
					//rope_y2[i] = rope_y1[i] + (yv * 1);
					
					velocity_x[i] = velocity_x[i] * .001 + (xv * .01);
					velocity_y[i] = velocity_y[i] * .001 + (yv * .01);*/
					//rope_x2[i] = rope_x1[i] + velocity_x[i];
					//rope_y2[i] = rope_y1[i] + velocity_y[i];
					rope_x2[i] = mouse_x;
					rope_y2[i] = mouse_y;
				}
			}
			
			for (int i = 0; i < pointCount; i++){
				rope_x1[i] = rope_x2[i];
				rope_y1[i] = rope_y2[i];
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mouse_x = arg0.getX();
			mouse_y = arg0.getY();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyCode = e.getKeyCode();
			if (KeyEvent.VK_F5 == keyCode){
				showNodes = !showNodes;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}