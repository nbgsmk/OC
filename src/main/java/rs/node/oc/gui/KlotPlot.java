package rs.node.oc.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;


//Extends JPanel class  
public class KlotPlot extends JPanel{
		    //initialize coordinates  
		    int[] cord = {0, 65, 20, 40, 80};
			
		    protected void paintComponent(Graphics grf){  
		        //create instance of the Graphics to use its methods  
		        super.paintComponent(grf);  
		        Graphics2D graph = (Graphics2D)grf;  
		          
		        //Sets the value of a single preference for the rendering algorithms.  
		        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
		          
		        // get width and height  
		        int width = getWidth();  
		        int height = getHeight();  
		          
		        // draw graph  
		        graph.draw(new Line2D.Double(0, 0, 0, height));
		        graph.draw(new Line2D.Double(0, height, width, height));
				
		          
		        //find value of x and scale to plot points  
		        double x = (double)(width)/(cord.length-1);
		        double scale = (double)(height)/getMax();
		          
		        //set color for points  
		        graph.setPaint(Color.RED);  
		          
		        // set points to the graph  
		        for(int i=0; i<cord.length; i++){  
		            double x1 = i*x;
		            double y1 = height-scale*cord[i];
		            graph.fill(new Ellipse2D.Double(x1-2, y1-2, 6, 6));
					graph.drawString(cord[i] + "", (float) x1, (float) y1);
		        }
				
				
		    }  
		      
		    //create getMax() method to find maximum value  
		    private int getMax(){  
		        int max = -Integer.MAX_VALUE;  
		        for(int i=0; i<cord.length; i++){  
		            if(cord[i]>max)  
		                max = cord[i];  
		             
		        }  
		        return max;  
		    }         
		    
			
			
		    //main() method start  
		    public static void main(String args[]){  
				
				JPanel yo = new JPanel();
				yo.setPreferredSize(new Dimension(30, 400));
				yo.setToolTipText("y");
				yo.setBackground(Color.GREEN);
				
				JPanel xo = new JPanel();
			    xo.setPreferredSize(new Dimension(40, 30));
				xo.setToolTipText("x");
				xo.setBackground(Color.PINK);
				
				KlotPlot klotPlot = new KlotPlot();
				klotPlot.setPreferredSize(new Dimension(400, 400));
				
				
				
			    JFrame frame = new JFrame();
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			    Container cont = frame.getContentPane();
				cont.add(klotPlot, BorderLayout.CENTER);
				cont.add(xo, BorderLayout.SOUTH);
			    cont.add(yo, BorderLayout.WEST);
				
				frame.pack();
				frame.setLocationByPlatform(true);
			    // frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
		    }  
		}  
