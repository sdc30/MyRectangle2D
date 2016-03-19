/*****************
 * Cartwright II,*
 * Steve         *    
 * CMPSC 221     *  
 * 4/23/2013     *
 ****************/


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;


public class MyRectangle2DTest extends JFrame 
{     private ActionListener listener = new KeyEvent();       
      static double x1, x2, y1, y2, length1, length2, width1, width2;      
      protected static String message1 = new String();
      protected static String message2 = new String();
      protected static String message3 = new String();
      protected static String output = "Two rectangles intersect? ";
      protected static String outputr1 = "r1 contains r2? ";
      protected static String outputr2 = "r2 contains r1? ";
      protected static JButton redraw = new JButton("Redraw Rectangles");
      protected static JTextField centerx1 = new JTextField(5);
      protected static JTextField centery1 = new JTextField(5);
      protected static JTextField wdth1 = new JTextField(5);     
      protected static JTextField height1 = new JTextField(5);
      protected static JTextField centerx2 = new JTextField(5);
      protected static JTextField centery2 = new JTextField(5);
      protected static JTextField wdth2 = new JTextField(5);     
      protected static JTextField height2 = new JTextField(5); 
      
       public MyRectangle2DTest()
       {   
            // Set the layout of the frame with BorderLayout 
            setLayout(new BorderLayout());
         
            // Create new panels for the text fields in two columns
            // with four rows and draw a new canvas 
         
            JPanel p1 = new JPanel(new GridLayout(4,1,0,0));
            JPanel p3 = new JPanel(new GridLayout(4,1,0,0));
            JPanel p2 = new JPanel(new FlowLayout());
            JPanel panelgroup1 = new JPanel(new FlowLayout());
            JPanel panelgroup2 = new JPanel();
            DrawRect r = new DrawRect(output);
            
            JLabel centrx1  = new JLabel("Center x:");
            JLabel centry1 = new JLabel("Center y:");
            JLabel widt1 = new JLabel("Width:");
            JLabel heigh1 = new JLabel("Height:");
            JLabel centrx2  = new JLabel("Center x:");
            JLabel centry2 = new JLabel("Center y:");
            JLabel widt2 = new JLabel("Width:");
            JLabel heigh2 = new JLabel("Height:");
        
            
            // Add textfields to panels and add to frame
            p1.add(centrx1);
            p1.add(centerx1);   
            p1.add(centry1);
            p1.add(centery1);       
            p1.add(widt1);
            p1.add(wdth1);
            p1.add(heigh1);
            p1.add(height1);
            p1.setPreferredSize(new Dimension(180, 100));
            p1.setBorder(BorderFactory.createTitledBorder("Enter info for Rectangle r1"));        
            
            p3.add(centrx2);
            p3.add(centerx2);   
            p3.add(centry2);
            p3.add(centery2);       
            p3.add(widt2);
            p3.add(wdth2);
            p3.add(heigh2);
            p3.add(height2);
            p3.setPreferredSize(new Dimension(180, 100));
            p3.setBorder(BorderFactory.createTitledBorder("Enter info for Rectangle r2"));
            
            panelgroup2.add(r);
          
            add(panelgroup2, BorderLayout.NORTH);
           
            panelgroup1.add(p1);
            panelgroup1.add(p3);
            
            add(panelgroup1);
            
            
            // Add redraw button and listener
            add(redraw, BorderLayout.SOUTH);
            redraw.addActionListener(listener);
      }
  
      
      public static class MyRectangle2D 
      {  public double x, y, width, length; 
         public static MyRectangle2D rect1, rect2;
            
            // Pre condition  - default constructor for MyRectangle2D
            // Post condition - created rectangle object
            public MyRectangle2D() 
            {
                x = y = 0.0;
                width = length = 1.0;
            }
             
            // Pre condition - must take 4 param double values
            // Post condition - newly created object "rectangle"     
            public MyRectangle2D(double x, double y, double width, double length)
            {  
             //sets all values to this object's variables
               this.x = x; 
               this.y = y;
               this.width = width;
               this.length = length;
            
            }
            // Pre condition - take two param and check if they're double
            // Post condition - cut rectangle in half check if coordinate is inside
            public boolean contains(double x, double y)
            {
              return Math.abs(x - this.x) <= width / 2 && Math.abs(y - this.y) <= length / 2;
            }
            
            // Pre conditon - calling rectangle must exist
            // Post condition - calling rect will determine if the recantle is inside 
            public boolean contains(MyRectangle2D rect)
            {
               return contains(rect.x - rect.width / 2, rect.y + rect.length / 2)
                  &&  contains(rect.x - rect.width / 2, rect.y - rect.length / 2)
                  &&  contains(rect.x + rect.width / 2, rect.y + rect.length / 2)
                  &&  contains(rect.x + rect.width / 2, rect.y - rect.length / 2);
            }
            
            // Pre condition - calling rectangle must exist
            // Post condition - will check if the calling rectangle overlaps
            public boolean overlaps(MyRectangle2D rect)
            {
              return( Math.abs(rect.x - x) <= (rect.width + width) / 2 
                  &&  Math.abs(rect.y - y) <= (rect.length + length) / 2);
            }
         
      }
      
      // Class for drawing canvas
      public static class DrawRect extends JPanel 
      {  public static Rectangle2D rectangle1 = new Rectangle2D.Double(x1, y1, width1, length1);
         public static Rectangle2D rectangle2 = new Rectangle2D.Double(x2, y2, width2, length2);
         public static MyRectangle2D rect1, rect2;
         private int getx, gety, xi, yi, xj, yj;
         
            // Pre condition - take string param
            // Post condition - creates new rectangles and draws on the canvas
            public DrawRect(String s)
            {                       
               setPreferredSize(new Dimension(300,300));            
               /*
               // Add new mouse listener to the canvas
               addMouseListener(new MouseAdapter()
               {  
                  // Mouse pressed will check and create new rectangles
                  // based on if the mouse coordinates are dragging which
                  // rectangle
                  public void mousePressed(MouseEvent e)
                  {  getx = e.getX();
                     gety = e.getY();
                     double newx, newy;
                     
                     // Checks if the rect contains mouse coordinates
                     if(rectangle1.contains(getx, gety))
                     {  newx = getx - (width1 / 2);                       
                        newy = gety - (length1 / 2);
                        
                        rectangle1 = new Rectangle2D.Double(newx, newy, width1, length1); 
                        MyRectangle2D.rect1 = new MyRectangle2D(newx, newy, width1, length1); 
                                                   
                        centerx1.setText("" + getx);
                        centery1.setText("" + gety);                        
                         
                         xi = getx;
                         yi = gety;                                 
                         updateRect();  
                      }         
                     // If the other rectangle contains mouse                                     
                     else if(rectangle2.contains(getx, gety))
                     {  newx = getx - (width2 / 2);
                        newy = gety - (length2 / 2);                                    
                        rectangle2 = new Rectangle2D.Double(newx, newy, width2, length2);                                                 
                        MyRectangle2D.rect2 = new MyRectangle2D(newx, newy, width2, length2);
                         
                           
                        centerx2.setText("" + getx);
                        centery2.setText("" + gety);                          
                             
                         xj = getx;
                         yj = gety;     
                         updateRect();           
                     }
                     else  
                      updateRect();   
                   
                   }    
                  
                  // Mouse released will draw the                       
                  public void mouseReleased(MouseEvent e)
                  {  
                     gety = e.getY();
                     getx = e.getX();
                     double newx, newy;
                     
                     if(rectangle1.contains(getx, gety))
                     {  newx = getx - (width1 / 2);
                        newy = gety - (length1 / 2);                        
                        rectangle1 = new Rectangle2D.Double(newx, newy, width1, length1);                        
                        MyRectangle2D.rect1 = new MyRectangle2D(newx, newy, width1, length1); 
                           
                       
                        centerx1.setText("" + getx);
                        centery1.setText("" + gety);                           
                        
                         xi = getx;
                         yi = gety;                                 
                         updateRect();  
                               
                     }
                     else if(rectangle2.contains(getx, gety))
                     {  newx = getx - (width2 / 2);
                        newy = gety - (length2 / 2);
                        
                        rectangle2 = new Rectangle2D.Double(newx, newy, width2, length2);                                               
                        MyRectangle2D.rect2 = new MyRectangle2D(newx, newx, width2, length2);
                        
                           
                        centerx2.setText("" + getx);
                        centery2.setText("" + gety);                             
                             
                         xj = getx;
                         yj = gety; 
                         updateRect();    
                                    
                     }
                     else  
                           updateRect();   
                          
                  }
               });
               */ 
               
               // Pre condition the mouse must be in use / dragging
               // Post condition will drag and draw only the selected rectangle
               //                and update accordingly 
               addMouseMotionListener(new MouseMotionAdapter()
               {
                  public void mouseDragged(MouseEvent e)
                  {     getx = e.getX();
                        gety = e.getY();
                        double newx, newy;
                         
                        // checks which rectangle contains the mouse coordinates
                        // draw the rectangle up and left of center
                        // makes the center of the rectangle the mouse coordinates
                        if(rectangle1.contains(getx, gety))
                        {  newx = getx - (width1 / 2);
                           newy = gety - (length1 / 2);
                                                   
                           rectangle1 = new Rectangle2D.Double(newx, newy, width1, length1); 
                           MyRectangle2D.rect1 = new MyRectangle2D(getx, gety, width1, length1);                           
                            
                                
                           centerx1.setText("" + getx);
                           centery1.setText("" + gety);                               
                                
                            xi = getx;
                            yi = gety;
                            updateRect();
                        }
                        else if(rectangle2.contains(getx, gety))
                        {  newx = getx - (width2 / 2);
                           newy = gety - (length2 / 2);
                           
                           rectangle2 = new Rectangle2D.Double(newx, newy, width2, length2);                     
                           MyRectangle2D.rect2 = new MyRectangle2D(getx, gety, width2, length2);                            
                                                      
                              
                           centerx2.setText("" + getx);
                           centery2.setText("" + gety);
     
                             xj = getx;
                             yj = gety;
                             updateRect();
                       }
                       else if(rectangle2.contains(getx, gety) && rectangle1.contains(getx, gety))
                          updateRect(); 
                  }
              });
                        
            }
                 
            // redraw rectangles and strings             
            @Override
            protected void paintComponent(Graphics g)
            {  
              super.paintComponent(g);
               g.setFont(new Font("CourierNew", Font.PLAIN, 14));
               g.drawString(output + message1, 10, 10);
               g.drawString(outputr1 + message2, 10, 25);
               g.drawString(outputr2 + message3, 10, 40);
               g.drawString("r1",(int)(xi),(int)(yi));
               g.drawString("r2",(int)(xj),(int)(yj));
               Graphics2D g2 = (Graphics2D) g;
               
               g2.draw(rectangle1);
               g2.draw(rectangle2); 
           }
            
            // updates to see if it overlaps or one is contained in the other
            public void updateRect()
            {
               if(MyRectangle2D.rect1.overlaps(MyRectangle2D.rect2))
                  message1 = "Yes";
               else 
                  message1 = "No";
               
               if(MyRectangle2D.rect1.contains(MyRectangle2D.rect2))
                  message2 = "Yes";
               else 
                  message2 = "No";  
                  
               if(MyRectangle2D.rect2.contains(MyRectangle2D.rect1))
                  message3 = "Yes";
               else 
                  message3 = "No";          
               
                 
               repaint();  
           }
         }    
   
      // button listener class will take length width and created new rectangles for use
      // using appropriate coordinates 
      class KeyEvent implements ActionListener
      {  double newx, newy;
      
           // Pre condition - must have filled textfields to use 
           // Post condition - will make and draw specified rectangles
           public void actionPerformed(ActionEvent e)
            {  
            
               if(e.getSource() == redraw)
               {  x1 = Double.parseDouble(centerx1.getText());
                  length1 = Double.parseDouble(height1.getText());
                  y1 = Double.parseDouble(centery1.getText());
                  width1 = Double.parseDouble(wdth1.getText()); 
                  newx = x1 - (width1 / 2 );
                  newy = y1 - (length1 / 2);               
                  centerx1.setText("" + x1);
                  centery1.setText("" + y1);  
                  wdth1.setText("" + width1);
                  height1.setText("" + length1);               
                  DrawRect.rectangle1 = new Rectangle2D.Double(newx, newy, width1, length1);
                  MyRectangle2D.rect1 = new MyRectangle2D(x1, y1, width1, length1);           
                           
                  x2 = Double.parseDouble(centerx2.getText());
                  length2 = Double.parseDouble(height2.getText());
                  y2 = Double.parseDouble(centery2.getText());
                  width2 = Double.parseDouble(wdth2.getText()); 
                  newx = x2 - (width2 / 2);
                  newy = y2 - (length2 / 2);  
                  centerx2.setText("" + x2);
                  centery2.setText("" + y2);
                  wdth2.setText("" + width2);
                  height2.setText("" + length2);                         
                  DrawRect.rectangle2 = new Rectangle2D.Double(newx, newy, width2, length2);
                  MyRectangle2D.rect2 = new MyRectangle2D(x2, y2, width2, length2);            
                  
                  if(MyRectangle2D.rect1.overlaps(MyRectangle2D.rect2))
                     message1 = "Yes";
                  else 
                     message1 = "No";
                    
                  if(MyRectangle2D.rect1.contains(MyRectangle2D.rect2))
                     message2 = "Yes";
                  else 
                     message2 = "No";  
                     
                  if(MyRectangle2D.rect2.contains(MyRectangle2D.rect1))
                     message3 = "Yes";
                  else 
                     message3 = "No"; 
                      
                  
                 DrawRect r = new DrawRect(output);        
                  repaint();
                  
               }
         }  
       }  
}     
