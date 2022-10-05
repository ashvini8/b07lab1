import java.io.File;
import java.io.FileNotFoundException;
public class Driver { 
  public static void main(String [] args) throws Exception{ 
    Polynomial p = new Polynomial(); 
    System.out.println(p.evaluate(3)); 

    //double [] c1 = {6,0,0,5}; 
    double [] c1 = {2,4};
    int [] e1 = {2,1};
    Polynomial p1 = new Polynomial(c1, e1);

    //double [] c2 = {0,-2,0,0,-9}; 
    double [] c2 = {3,2,1}; 
    int [] e2 = {1,0,3}; 
    Polynomial p2 = new Polynomial(c2, e2); 

    File test = new File ("/Users/ashvinihunagund/b07lab1/test.txt");
    Polynomial p3 = new Polynomial(test);
   
    Polynomial s = p1.add(p3);
    Polynomial m = p1.multiply(p3);

    p3.saveToFile("test2");

    System.out.println("s(2) of file = " + p3.evaluate(2)); 
    
    System.out.println("s(2) = " + s.evaluate(2)); 

    System.out.println("m(2) = " + m.evaluate(2));


    if(s.hasRoot(1)) 
      System.out.println("1 is a root of s"); 
    else 
      System.out.println("1 is not a root of s"); 
    
  } 
} 