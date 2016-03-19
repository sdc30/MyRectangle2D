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


public class MyRectangleGo
{
   public static void main(String[] args)
   {  
      // Create new frame of type MyRectangle2DTest
      JFrame frame = new MyRectangle2DTest();
      
      // Set the title, size, start position, default close, and visibility
      frame.setTitle("Rectangle Checker");
      frame.setSize(600,400);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
   
   }


}
