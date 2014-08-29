package code;

import java.util.List;

public class Something {

   public void plot(List<Point> points) {
      // ...
   }

   public void plot(String[] points) {
      List<Point> list = new List<Point>();
      Point p = new Point();
      for (String s : points) {
         p.setX(Double.parseDouble(s));
         p.setY(Double.parseDouble(s));
         list.add(p);
      }
      plot(list);
   }

}
