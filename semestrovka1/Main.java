import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int dist2(Point a1, Point a2)
    {
        return (a2.getX() - a1.getX()) * (a2.getX() - a1.getX()) +
                (a2.getY() - a1.getY()) * (a2.getY() - a1.getY());
    }
    static int cross (Point a1, Point a2, Point b1, Point b2)
    {
        return (a2.getX() - a1.getX())*(b2.getY() - b1.getY()) - (b2.getX() - b1.getX())*(a2.getY() - a1.getY());
    }
    static Point[] JarvisConvexHull(Point [] a)
    {
        int m = 0;
        for (int i = 1; i < a.length; i++)
        {
            if (a[i].getX() < a[m].getX()) m = i;
            else if (a[i].getX() == a[m].getX() && a[i].getY() < a[m].getY()) m = i;
        }

        Point[] p = new Point[a.length + 1];

        p[0] = a[m];
        a[m] = a[0];
        a[0] = p[0];

        int k = 0;
        int min = 0;
        System.out.println(p.length);
        System.out.println(a.length);
        do
        {
            for (int j = 1; j < a.length; j++)
            {
                if (cross(p[k], a[min], p[k], a[j]) < 0 ||
                        cross(p[k], a[min], p[k], a[j]) == 0 &&
                                dist2(p[k], a[min]) < dist2(p[k], a[j]))
                {
                    min = j;
                    //System.out.println("last min was found! = " + min);
                }
            }
            if(++k == p.length){
                throw new IndexOutOfBoundsException("Can't make the algorithm, ");
            }
            System.out.println(k);
            p[k] = a[min];
            min = 0;
        }
        while (!(p[k].getX() == p[0].getX() && p[k].getY() == p[0].getY()));


        return Arrays.copyOf(p, k);
    }
/*    public static int[] randomCoordinates(){
        return new int[]{
                (int)(Math.random()*Integer.MAX_VALUE),
                (int)(Math.random()*Integer.MAX_VALUE)
        };
    }*/
    public static int randomCoordinates(){
        return (int)(Math.random()*20000 - 10000);
    }
    public static void newFileWriterWithRandomCoordinates() throws IOException {
        File file = new File("input.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        double randomOuterState = Math.random();
        for (int i = 0; i < (int)(randomOuterState * 50 + 50); i++) {
            fileWriter.write(randomCoordinates() + "," + randomCoordinates());
            double randomState = Math.random();
            for (int j = 0; j < (int)(randomState * 9900 + 99); j++) {
                fileWriter.append(";").append(String.valueOf(randomCoordinates())).append(",").append(String.valueOf(randomCoordinates()));
            }
            fileWriter.append("\n");
        }
        fileWriter.flush();
    }
    public static void newFileWriterWithRandomCoordinates(int n) throws IOException {
        File file = new File("input.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < n; i++) {
            fileWriter.write(randomCoordinates() + "," + randomCoordinates());
            for (int j = 0; j < Math.random() * 9900 + 99; j++) {
                fileWriter.write(";" + randomCoordinates() + "," + randomCoordinates());
            }
            fileWriter.append("\n");
        }
        fileWriter.close();
    }
    public static void main(String[] args) throws IOException {
        newFileWriterWithRandomCoordinates();
        Scanner scanner = new Scanner(new File("input.txt"));
        List<Point> list = new ArrayList<>();
        while(scanner.hasNext()){
            String[] lineSet = scanner.nextLine().split(";");
            for (int i = 0; i < lineSet.length; i++) {
                list.add(new Point(Integer.parseInt(lineSet[i].split(",")[0]), Integer.parseInt(lineSet[i].split(",")[1])));
            }
            Point[] arrayPoint = new Point[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arrayPoint[i] = list.get(i);
            }
            System.nanoTime();
            System.out.println(Arrays.toString(arrayPoint));
            System.out.println(Arrays.toString(JarvisConvexHull((arrayPoint))));
            Point[] testArray = new Point[]{
                    new Point(0,0),
                    new Point(1,0),
                    new Point(2,1),
                    new Point(3,2),
                    new Point(3,3),
                    new Point(2,4),
                    new Point(0,5),
                    new Point(1,1),
                    new Point(2,2),
                    new Point(2,1)
            };
/*            System.out.println(Arrays.toString(testArray));
            System.out.println(Arrays.toString(JarvisConvexHull(testArray)));*/
        }
    }
}
