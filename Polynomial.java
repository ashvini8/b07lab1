import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial {

	public double[] coefficient;
	public int[] exponent;
	
	//public Polynomial(){}

	public Polynomial(){
		coefficient = new double[] {0};
		exponent = new int[] {0};
	}
	
	public Polynomial(double[] inputc, int[] inpute){
		coefficient = new double[inputc.length];
		for(int i = 0; i < inputc.length; i++){
			coefficient[i] = inputc[i];
		}

		exponent = new int[inpute.length];
		for(int i = 0; i < inpute.length; i++){
			exponent[i] = inpute[i];
		}
	}

	public Polynomial(File inFile) throws FileNotFoundException{
	
		//to read the file in java
		Scanner input = new Scanner(inFile);
		String line = input.nextLine();

		//counting the number of terms in the polynomial
		int terms = 1;

		for(int i = 1; i < line.length(); i++){
			if((line.charAt(i) == '+' || line.charAt(i) == '-')){
				terms = terms + 1;
			}
		}

		double[] fcoef = new double[terms];
		int[] fexpon = new int[terms];  

		//System.out.println("string: " + line);
		//System.out.println("num of terms: " + terms);

		int line_len = line.length();

		int ind = 0;
		String ctemp = "";
		String etemp = "";
		int prior_coef = 0;
		outerloop:
		for(int i = 0; i < terms; i++){
			//prior_coef = 0;
			if(ind >= line_len){
				break;
			}

			//for ceofficientss

			ctemp = "";

			//System.out.println("if anything after NEG: ");

			if(prior_coef == 1){
				ctemp = ctemp + '-';
				//System.out.println("coef prior was neg");
			}

			if(line.charAt(ind)== '-'){ //if the coefficient is negative
				ctemp = ctemp + '-';
				System.out.println("has neg: " + line.charAt(ind));
				System.out.println("ctemp: " + ctemp);

				ind = ind + 1;
				//System.out.println("ind in neg coef case " + ind);

				if(ind >= line_len){
					break outerloop;
				}
			}

			
			while(line.charAt(ind) != 'x'|| line.charAt(ind) != '+'|| line.charAt(ind) != '-'){
				System.out.println("\n Coef \n");
				if(line.charAt(ind) == 'x'|| line.charAt(ind) == '+'|| line.charAt(ind)== '-'){
					System.out.println("theres a thing ");
					break;
				}
				ctemp = ctemp + line.charAt(ind);
				System.out.println("ind in blocking case " + ind);
				System.out.println("the line char is " + line.charAt(ind));

				System.out.println("ctemp: " + ctemp);//3
				System.out.println("this i : " + i);


				ind = ind + 1;

				if(ind >= line_len){

					System.out.println("is broken cause ind is " + ind + " and line len is " + line_len);
					break outerloop;
				}
			}
			System.out.println("final ctemp: " + ctemp);
			fcoef[i] = Double.parseDouble(ctemp);
			
			

			System.out.println("fceof rn \n" + fcoef[i]);

			//for exponents

			System.out.println("\n Exponent \n");

			etemp = "";

			System.out.println("what char we are at: " + line.charAt(ind));
			System.out.println("ind: " + ind);

			if(line.charAt(ind) != 'x'){
				System.out.println("this is the char tha is not x");
				//then it was just a coeffecient so the exponent would be 0
				fexpon[i] = 0;
				if(line.charAt(ind) == '-'){
					System.out.println("next coef is neg");
					prior_coef = 1;
				}else{
					System.out.println("next coef is POSTIVE");
					prior_coef = 0;
				}
				ind = ind + 1;
				if(ind >= line_len){
					break outerloop;
				}
			}else{
				//if it was an x the coefficent would be after
				//sandwitched b/n x and another operator
				ind = ind + 1;
				//System.out.println("what extra char we are at: " + line.charAt(ind));
				while(line.charAt(ind)!= '+' || line.charAt(ind)!= '-'){
					System.out.println("char at now exp: " + line.charAt(ind)); //- 
					
					if(line.charAt(ind)== '+' || line.charAt(ind)== '-'){
						System.out.println("there's a thing");
						//add the negative to the next coef
						if(line.charAt(ind) == '-'){
							System.out.println("next coef is neg");
							prior_coef = 1;
						}else{
							System.out.println("next coef is POSTIVE");
							prior_coef = 0;
						}
						ind = ind + 1;
						break;
					}
					etemp = etemp + line.charAt(ind);
					ind = ind + 1;
					System.out.println("ind in exp while x: " + ind);
					if(ind >= line_len){
						break outerloop;
					}
				}

				System.out.println("this is final etemp: " + etemp);	
				System.out.println("ind is: " + ind);
				fexpon[i] = Integer.parseInt(etemp);

			}


		}//end of for

		if(ind == line_len){
			System.out.println("this is cemp when taken: " + ctemp);
			fcoef[terms - 1] = Double.parseDouble(ctemp);
			//System.out.println("this is etemp: " + etemp);
			if(etemp == ""){
				fexpon[terms - 1] = 0;
			}else{
				fexpon[terms - 1] = Integer.parseInt(etemp);
			}

		}

		coefficient = new double[terms];
		exponent = new int[terms];
		for(int i = 0; i < terms; i++){
			coefficient[i] = fcoef[i];
		}

		//exponent = new int[inpute.length];
		for(int i = 0; i < terms; i++){
			exponent[i] = fexpon[i];
		}
		input.close();

		System.out.println("\n Printing the coeficents and exponents");
		//printing the array
		for(int j = 0; j < terms; j++){
			System.out.println("this is fcoef: " + fcoef[j]);
		}
		for(int j = 0; j < terms; j++){
			System.out.println("this is fexpon: " + fexpon[j]);
		}

		//coefficient = new double[inputc.length];
		
		
	}

	void saveToFile(String fname) throws Exception{
		/*
		 * takes  one  argument  of  type  String representing  a  file  name  
		 * and  saves  the  polynomial  in  textual  format  in  the  corresponding file (similar to the format used in the constructor) 
		 */

		//taking the double array we put it as a string

		String poly = "";
		int size = coefficient.length;

		//String.valueOf(obj)
		for(int i = 0; i < size; i++){

			//System.out.println("this is i: " + i); 

			if(i != 0){
				//add +
				if(coefficient[i] > 0){
					poly = poly + '+';
				}
			}

			poly = poly + String.valueOf(coefficient[i]);
			//System.out.println("value " + String.valueOf(coefficient[i]));
			//System.out.println("num: " + coefficient[i]);

			if(exponent[i] != 0){
				poly = poly + 'x';
				poly = poly + String.valueOf(exponent[i]);
			}

		}

		//System.out.println("this is final poly: " + poly);
		PrintStream out = new PrintStream(fname);
		out.println(poly);
		out.close();

	}	


	public Polynomial removeRedundant(double [] coef, int [] expon){

		for(int i = 0; i < (expon.length - 1); i++){
			for(int j = i+1; j < expon.length; j++){
				if(expon[i] == expon[j]){
					coef[i] = coef[i] + coef[j];
					coef[j] = 0;
				}
			}
		}

		//now that things are added, we have to remove the indexes that have coeff 0

		//counting how many zeroes in coef
		int numz = 0;
		for(int i = 0; i < coef.length; i++){
			if(coef[i]==0){
				numz = numz + 1;
			}
		}

		double [] newc = new double[coef.length - numz];
		int [] newe = new int[expon.length - numz];
		
		int newi = 0;

		for(int i = 0; i < coef.length; i++){
			if(coef[i] != 0){
				newc[newi] = coef[i];
				newe[newi] = expon[i];	
				newi = newi + 1;
			}
		}


		Polynomial noredun = new Polynomial(newc, newe);

		// for(int i = 0; i < newc.length; i++){
		// 	System.out.println("newc: " + newc[i]);
		// }for(int i = 0; i < newc.length; i++){
		// 	System.out.println("newe: " + newe[i]);
		// }

		
		return noredun;

		
	}

	public Polynomial multiply(Polynomial mpoly){

		int aCLen = coefficient.length;
		int bCLen = mpoly.coefficient.length;
		double[] multC = new double[aCLen * bCLen];

		int idx = 0;
		for(int i = 0; i < aCLen; i++){
			for(int j = 0; j < bCLen; j++){
				multC[idx] = (coefficient[i] * mpoly.coefficient[j]);
				idx = idx + 1;
			}
		}
		int[] multE = new int[aCLen * bCLen];
		idx = 0;
		for(int i = 0; i < aCLen; i++){
			for(int j = 0; j < bCLen; j++){
				multE[idx] = (exponent[i] + mpoly.exponent[j]);
				idx = idx + 1;
			}
		}

		
		return removeRedundant(multC, multE);

	}

	public Polynomial add(Polynomial poly1){
		
		//concatenating the coeffecients
		int aCLen = coefficient.length;
		int bCLen = poly1.coefficient.length;
		double[] addC = new double[aCLen + bCLen];
		System.arraycopy(coefficient, 0, addC, 0, aCLen);
        System.arraycopy(poly1.coefficient, 0, addC, aCLen, bCLen);

		//concatenating the exponents
		//int aELen = exponent.length;
		//int bELen = poly1.exponent.length;
		int[] addE = new int[aCLen + bCLen];
		System.arraycopy(exponent, 0, addE, 0, aCLen);
        System.arraycopy(poly1.exponent, 0, addE, aCLen, bCLen);

		return removeRedundant(addC, addE);
		
	}

	public double evaluate(double param){
		double num = 0.0;
		int deg = 0;
		for(int i = 0; i < coefficient.length; i++){
			num = num + coefficient[i] * Math.pow(param,exponent[i]);
			deg = deg + 1;
		}
		return num;
	}

	public boolean hasRoot(double isroot){

		if(evaluate(isroot) == 0){
			return true;
		}else{
			return false;
		}
			
	}



}
