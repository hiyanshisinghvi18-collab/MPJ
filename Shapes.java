class Shapes
{
    double area;
    Shapes()
    {
        //default constructor
    }
    Shapes(double side)
    {
        area = side*side;
        System.out.println("Area of Square: "+area);
    }

    Shapes(double length, double breadth)
    {
        area = length* breadth;
        System.out.println("Area of Rectangle: "+area);
    }
    
    Shapes(double radius, boolean isCircle)
    {
        area = Math.PI *radius *radius;
        System.out.println("Area of Cirlcle: "+area);

    }

    void calcArea(int side) 
    {
        System.out.println("Area of Square: " + (side * side));
    }

    void calcArea(int length, int breadth) 
    {
        System.out.println("Area of Rectangle: " + (length * breadth));
    }

    void calcArea(double radius) 
    {
        System.out.println("Area of Circle: " + (Math.PI * radius * radius));
    }

    public static void main(String args[])
    {
        //constructor overloading
        Shapes s1 = new Shapes(5);
        Shapes s2 = new Shapes(4,6);
        Shapes s3 = new Shapes(7,true);

        System.out.println();

        //method overloading
        Shapes obj = new Shapes();
        obj.calcArea(7);
        obj.calcArea(6, 7);
        obj.calcArea(3.5);
    }
}