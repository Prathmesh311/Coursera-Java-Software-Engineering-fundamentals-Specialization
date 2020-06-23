import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int NumPoints = 0;
        for (Point pt : s.getPoints()){
            NumPoints += 1;
        }
        return NumPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double AvgLength=0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints())
        {
            double currDistance = prevPt.distance(currPt);
            AvgLength = AvgLength + currDistance;
            prevPt=currPt;
        }
        double NumPoints = getNumPoints(s);
        AvgLength = AvgLength/(NumPoints);
        return AvgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double LargeDistance=0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints())
        {
            double currDistance = prevPt.distance(currPt);
            if (currDistance > LargeDistance)
            {
                LargeDistance =currDistance;
            }
            prevPt = currPt;
        }
        return LargeDistance;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double x = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints())
        {
            double currX = currPt.getX();
            if (currX > x)
            {
                x = currX;
            }
            prevPt = currPt;
        }
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        
        double LargestPerim = 0;
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if (perim > LargestPerim)
            {
                LargestPerim = perim;
            }
        }
        return LargestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (largestPerimeter < perimeter) {
                largestPerimeter = perimeter;
                temp = f;
            }
        }
        
        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int NumPoints = getNumPoints(s);
        double AvgLength = getAverageLength(s);
        double LargeDistance = getLargestSide(s);
        double LargestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points = " + NumPoints);
        System.out.println("Average Length = " + AvgLength);
        System.out.println("Largest Side = " + LargeDistance);
        System.out.println("Largest X = " + LargestX);

    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double LargestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter" + LargestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String LargestPerimFile = getFileWithLargestPerimeter();
        System.out.println("File Having largest perimeter = " + LargestPerimFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
