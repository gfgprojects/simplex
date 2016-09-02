/*
Simplex computes evenly spaced points on the simplex
Copyright (C) 2015  Gianfranco Giulioni

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


import java.io.PrintWriter;
import java.math.BigDecimal;
import java.io.IOException;

public class Simplex{
    public static int dimension;
    public static String passo;
    public static PrintWriter out = null;
    public static void main(String[] args){
	    boolean isBatch=true;
	    String fileName;
	    if(args.length>2){
		    try {
			    dimension = Integer.parseInt(args[0]);
			    if(dimension<2){
				    isBatch=false;
				    System.out.println("Simplex: first argument must be integer greater than 1!");
			    }
		    } catch (NumberFormatException e) {
			    //     System.err.println("Argument" + args[0] + " must be an integer.");
			    //     System.err.println("");
			    System.out.println("Simplex: first argument must be integer!");
			    isBatch=false;
		    }

		    try {
			    double step = Double.parseDouble(args[1]);
			    if(step>0 && step<1){
				    BigDecimal numer =BigDecimal.ONE;
				    BigDecimal denominat =new BigDecimal(args[1]);
				    double rem =(numer.remainder(denominat)).doubleValue();
				    if(rem==0){
					    passo=args[1];				    
				    }
				    else{
					    isBatch=false;
					    System.out.println("Simplex: 1 divided by second argument must be integer!");
				    }
			    }
			    else{
				    isBatch=false;
			    }
		    } catch (NumberFormatException e) {
			    //     System.err.println("Argument" + args[0] + " must be an integer.");
			    //     System.err.println("");
			    isBatch=false;
			    System.out.println("Simplex: second argument must be grater than 0 and lower than 1!");
		    }

		    fileName=args[2];
		    if(isBatch){
			    try{
				    out = new PrintWriter(fileName);
			    }
			    catch (IOException e){
				    e.printStackTrace();
			    }
			    BigDecimal vettore[] = new BigDecimal[dimension];
			    for(int i=0;i<vettore.length;i++){
				    vettore[i]=new BigDecimal("0.0");
			    }
			    BDim dimension=new BDim(0);
			    dimension.iterPrint(vettore);
			    out.close();

		    }
	    }
	    else{
		    if(args.length<1){
			    GuiBuilder programGui=new GuiBuilder();
			    programGui.buildGui();
		    }
		    else{
			    System.out.println("Simplex: three arguments are needed to start Simplex in batch mode!");
		    }
	    }
    }
}
