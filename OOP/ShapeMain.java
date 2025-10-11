import java.util.*;

/**
 * Program Title: OOP Inheritance & Polymorphism
 * Author: @ashwil-colaco
 * Date: 2025-10-07
 *
 * Description: Demonstrates core OOP concepts (inheritance, polymorphism, abstract classes)
 * by creating a Shape abstract class with Circle and Rectangle subclasses.
 * The program calculates and prints the area of shapes using polymorphism.
 * Time Complexity: O(1) for each area calculation.
 * Space Complexity: O(n) for storing Shape objects in an array.
 */

/**
 * Abstract class representing a generic shape.
 */
abstract class Shape {
    /**
     * Calculates and returns the area of the shape.
     * 
     * @return area of the shape
     */
    public abstract double getArea();
}

/**
 * Class representing a circle shape.
 */
class Circle extends Shape {
    private double radius;

    /**
     * Constructor to initialize the radius of the circle.
     * 
     * @param radius radius of the circle
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Calculates and returns the area of the circle.
     * 
     * @return area of the circle
     */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

/**
 * Class representing a rectangle shape.
 */
class Rectangle extends Shape {
    private double length;
    private double width;

    /**
     * Constructor to initialize the length and width of the rectangle.
     * 
     * @param length length of the rectangle
     * @param width width of the rectangle
     */
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    /**
     * Calculates and returns the area of the rectangle.
     * 
     * @return area of the rectangle
     */
    @Override
    public double getArea() {
        return length * width;
    }
}

/**
 * Main class to demonstrate polymorphism with Shape, Circle, and Rectangle.
 */
public class ShapeMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Shape[] shapes = new Shape[2];

        System.out.print("Enter the radius of the Circle: ");
        double radius = sc.nextDouble();

        System.out.print("Enter the length and breadth of the Rectangle: ");
        double length = sc.nextDouble();
        double breadth = sc.nextDouble();

        shapes[0] = new Circle(radius);        
        shapes[1] = new Rectangle(length, breadth);  

        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.getArea());
        }

        sc.close();
    }
}
