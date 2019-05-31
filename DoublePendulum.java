import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.scene.media.AudioClip;

public class DoublePendulum extends JPanel implements KeyListener {
	
	JFrame frame;
	double r1 = 200.0;
	double r2 = 200.0;
	double m1 = 40.0;
	double m2 = 40.0;
	double a1 = Math.PI/2;
	double a2 = Math.PI/2;
	double a1_v = 0.0;
	double a2_v = 0.0;
	double grav = 1.0;
	
	double px2 = -1.0;
	double py2 = -1.0;
	double cx, cy;
	
	double x2 = 0.0;
	double y2 = 0.0;
	
	double x1 = 0.0;
	double y1 = 0.0;
	
	int pointSize = 3;
	
	boolean reset = false;
	boolean swinging = false;
	boolean autoSwing = false;
	
	ArrayList<Point> points = new ArrayList<Point>();
	
	Font font = new Font("Comic Sans MS", Font.PLAIN, 18);
	
//	URL resource = getClass().getResource("music.mp3");
//	AudioClip clip = new AudioClip(resource.toString());
	
	public DoublePendulum() {
		//clip.play();
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(900, 600);
		frame.addKeyListener(this);
		cx = 450.0;
		cy = 200.0;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 900, 600);
		
		if(!autoSwing) {
			if(swinging) {
				double num1 = -grav * (2 * m1 + m2) * Math.sin(a1);
				double num2 = -m2 * grav * Math.sin(a1-2*a2);
				double num3 = -2*Math.sin(a1-a2)*m2;
				double num4 = a2_v*a2_v*r2+a1_v*a1_v*r1*Math.cos(a1-a2);
				double den = r1 * (2*m1+m2-m2*Math.cos(2*a1-2*a2));
				double a1_a = (num1 + num2 + num3*num4) / den;
		
				num1 = 2 * Math.sin(a1-a2);
				num2 = (a1_v*a1_v*r1*(m1+m2));
				num3 = grav * (m1 + m2) * Math.cos(a1);
				num4 = a2_v*a2_v*r2*m2*Math.cos(a1-a2);
				den = r2 * (2*m1+m2-m2*Math.cos(2*a1-2*a2));
				double a2_a = (num1*(num2+num3+num4)) / den;
				
				x1 = r1 * Math.sin(a1);
				y1 = r1 * Math.cos(a1);
		
				x2 = x1 + r2 * Math.sin(a2);
				y2 = y1 + r2 * Math.cos(a2);
				
				points.add(new Point(x2,cx,y2,cy));
				
				g.setColor(Color.GREEN);
				g.drawLine((int)cx, (int)cy, (int)(x1+cx), (int)(y1+cy));
				g.drawLine((int)(x1+cx), (int)(y1+cy), (int)(x2+cx), (int)(y2+cy));
				
				for(int i = 0; i < points.size(); i++) {
					if(i % 2 == 0) {
						g.setColor(Color.GREEN);
					}else {
						g.setColor(Color.GREEN);
					}
					g.fillOval((int)(points.get(i).getX2()+points.get(i).getCX()), (int)(points.get(i).getY2()+points.get(i).getCY()), pointSize, pointSize);
				}
				
				text(g, "Hold ENTER to manually swing", 575, 50);
				
				a1_v += a1_a;
				a2_v += a2_a;
				a1 += a1_v;
				a2 += a2_v;
			}
			text(g, "Hold ENTER to manually swing", 575, 50);
		}else {
			double num1 = -grav * (2 * m1 + m2) * Math.sin(a1);
			double num2 = -m2 * grav * Math.sin(a1-2*a2);
			double num3 = -2*Math.sin(a1-a2)*m2;
			double num4 = a2_v*a2_v*r2+a1_v*a1_v*r1*Math.cos(a1-a2);
			double den = r1 * (2*m1+m2-m2*Math.cos(2*a1-2*a2));
			double a1_a = (num1 + num2 + num3*num4) / den;
	
			num1 = 2 * Math.sin(a1-a2);
			num2 = (a1_v*a1_v*r1*(m1+m2));
			num3 = grav * (m1 + m2) * Math.cos(a1);
			num4 = a2_v*a2_v*r2*m2*Math.cos(a1-a2);
			den = r2 * (2*m1+m2-m2*Math.cos(2*a1-2*a2));
			double a2_a = (num1*(num2+num3+num4)) / den;
			
			x1 = r1 * Math.sin(a1);
			y1 = r1 * Math.cos(a1);
	
			x2 = x1 + r2 * Math.sin(a2);
			y2 = y1 + r2 * Math.cos(a2);
			
			points.add(new Point(x2,cx,y2,cy));
			
			g.setColor(Color.GREEN);
			g.drawLine((int)cx, (int)cy, (int)(x1+cx), (int)(y1+cy));
			g.drawLine((int)(x1+cx), (int)(y1+cy), (int)(x2+cx), (int)(y2+cy));
			
			for(int i = 0; i < points.size(); i++) {
				if(i % 2 == 0) {
					g.setColor(Color.GREEN);
				}else {
					g.setColor(Color.GREEN);
				}
				g.fillOval((int)(points.get(i).getX2()+points.get(i).getCX()), (int)(points.get(i).getY2()+points.get(i).getCY()), pointSize, pointSize);
			}
			
			a1_v += a1_a;
			a2_v += a2_a;
			a1 += a1_v;
			a2 += a2_v;
		}
		
		g.setColor(Color.GREEN);
		g.drawLine((int)cx, (int)cy, (int)(x1+cx), (int)(y1+cy));
		g.drawLine((int)(x1+cx), (int)(y1+cy), (int)(x2+cx), (int)(y2+cy));
		
		for(int i = 0; i < points.size(); i++) {
			if(i % 2 == 0) {
				g.setColor(Color.GREEN);
			}else {
				g.setColor(Color.GREEN);
			}
			g.fillOval((int)(points.get(i).getX2()+points.get(i).getCX()), (int)(points.get(i).getY2()+points.get(i).getCY()), pointSize, pointSize);
		}
		
		text(g, "Double Pendulum", 25, 25);
		text(g, "Press the spacebar to reset", 575, 25);
		text(g, "AutoSwing: "+autoSwing, 575, 75);
		
		if(reset) {
			reset();
		}
		
		repaint();
	}
	
	public void text(Graphics g, String s, int x, int y) {
		g.setFont(font);
		for(int i = 0; i < s.length(); i++) {
			if(i % 2 == 0) {
				g.setColor(Color.GREEN);
			}else {
				g.setColor(Color.GREEN);
			}
			g.drawString(s.charAt(i)+"", x, y);
			x+=10;
		}
	}
	
	public void reset() {
		r1 = 200.0;
		r2 = 200.0;
		m1 = 40.0;
		m2 = 40.0;
		a1 = Math.PI/2;
		a2 = Math.PI/2;
		a1_v = 0.0;
		a2_v = 0.0;
		grav = 1.0;
		
		px2 = -1.0;
		py2 = -1.0;
		cx = 450.0;
		cy = 200.0;
		
		x2 = 0.0;
		y2 = 0.0;
		
		x1 = 0.0;
		y1 = 0.0;
		
		reset = false;
		swinging = false;
		autoSwing = false;
		
		points = new ArrayList<Point>();
		
		//clip.stop();
		//clip.play();
	}
	
	public static void main(String[] args) {
		DoublePendulum app = new DoublePendulum();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			reset = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			swinging = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			if(!autoSwing) {
				autoSwing = true;
			}else if(autoSwing) {
				autoSwing = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			swinging = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
