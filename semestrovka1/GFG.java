import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.util.*;

class GFG {

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are collinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    public static int orientation(Point p, Point q, Point r) {
        int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0;  // collinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }
    // Prints convex hull of a set of n points.
    public static int convexHull(Point []points, int n) {
        /*long startTime = System.currentTimeMillis();*/
        // There must be at least 3 points
        if (n < 3) return -1;
        int count = 0;

        // Initialize Result
        Vector<Point> hull = new Vector<Point>();

        // Find the leftmost point
        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].getX() < points[l].getX())
                l = i;

        // Start from leftmost point, keep moving
        // counterclockwise until reach the start point
        // again. This loop runs O(h) times where h is
        // number of points in result or output.
        int p = l, q;
        do {
            // Add current point to result
            hull.add(points[p]);

            // Search for a point 'q' such that
            // orientation(p, q, x) is counterclockwise
            // for all points 'x'. The idea is to keep
            // track of last visited most counterclock-
            // wise point in q. If any point 'i' is more
            // counterclock-wise than q, then update q.
            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                count++;
                // If i is more counterclockwise than
                // current q, then update q
                if (orientation(points[p], points[i], points[q])
                        == 2)
                    q = i;
            }

            // Now q is the most counterclockwise with
            // respect to p. Set p as q for next iteration,
            // so that q is added to result 'hull'
            p = q;
/*            System.out.println(p);
            System.out.println(l);*/
        } while (p != l);   //While we don't come to first
        // point

        // Print Result
/*        for (Point temp : hull)
            System.out.println("(" + temp.getX() + ", " +
                    temp.getY()
                    + ")");*/
        return count;
    }

    /* Driver program to test above function */
    public static void main(String[] args) throws IOException {
        /*Main.newFileWriterWithRandomCoordinates();*/
        Scanner scanner = new Scanner(new File("input.txt"));
        List<Point> list = new ArrayList<>();
        List<Long> listOfTimeEachIteration = new ArrayList<>();
        Map<Long, Integer> mapOfPointSets = new TreeMap<>();
        List<Analyse> listAnalyse = new ArrayList<>();
        long startTime = 0;
        long endTime = 0;
        while (scanner.hasNext()) {
            String[] lineSet = scanner.nextLine().split(";");
            for (int i = 0; i < lineSet.length; i++) {
                list.add(new Point(Integer.parseInt(lineSet[i].split(",")[0]), Integer.parseInt(lineSet[i].split(",")[1])));
            }
            Point[] arrayPoint = new Point[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arrayPoint[i] = list.get(i);
            }
            startTime = System.nanoTime();
            int counter = convexHull(arrayPoint, arrayPoint.length);
            endTime = System.nanoTime();
            mapOfPointSets.put((endTime - startTime)/1000, arrayPoint.length);
            listAnalyse.add(new Analyse((endTime - startTime)/1000, arrayPoint.length, counter));
            list.clear();
        }
        mapOfPointSets.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(x->{
            System.out.println(x.getKey());
        });
        System.out.println("Values;");
        mapOfPointSets.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(x->{
            System.out.println(x.getValue());
        });
        System.out.println("-------");
        listAnalyse.stream().sorted(Comparator.comparingInt(Analyse::getArrayLength)).forEach(System.out::println);
    }
}
