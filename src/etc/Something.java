package code;

import java.util.List;
import java.util.ArrayList;

public class Something {

   public void plot(List<Point> points) {
      // ...
   }

   public void plot(String[] points) {

      if ((points == null) || (points.length == 0) ||
         ((points.length % 2) != 0)) {
         throw new IllegalArgumentException("blah");
      }

      List<Point> list = new ArrayList<Point>();
      for (int i = 0; i < points.length; i += 2) {
         Point p = new Point();
         p.setX(Double.parseDouble(points[i]));
         p.setY(Double.parseDouble(points[i + 1]));
         list.add(p);
      }
      plot(list);
   }
}
