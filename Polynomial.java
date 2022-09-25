public class Polynomial {

	public double[] coefficient;
	
	//public Polynomial(){}

	public Polynomial(){
		coefficient = new double[] {0};
	}
	
	public Polynomial(double[] inputs){
		coefficient = new double[inputs.length];
		for(int i = 0; i < inputs.length; i++){
			coefficient[i] = inputs[i];
		}
	}

	public Polynomial add(Polynomial poly1){
		Polynomial added = new Polynomial();
		int lenarr = coefficient.length;
		int abc = 0;
		//it will equal 1 is its poly1
		//it will equal 2 if its coeff
		
		if(lenarr < poly1.coefficient.length){
			//abc = 1;
			int i;
			added = new Polynomial(poly1.coefficient);
			for(i = 0; i < coefficient.length; i++){
				added.coefficient[i] = coefficient[i] + poly1.coefficient[i];
			}
			for(int j = i; j < added.coefficient.length; j++){
				added.coefficient[j] = poly1.coefficient[j];
			}

		}else{
			//abc = 2;
			int a;
			added = new Polynomial(coefficient);
			for(a = 0; a < poly1.coefficient.length; a++){
				added.coefficient[a] = coefficient[a] + poly1.coefficient[a];
			}
			for(int j = a; j < added.coefficient.length; j++){
				added.coefficient[j] = coefficient[j];
			}
		}

		
		return added;
	}

	public double evaluate(double param){
		double num = 0.0;
		int deg = 0;
		for(int i = 0; i < coefficient.length; i++){
			num = num + coefficient[i] * Math.pow(param,deg);
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