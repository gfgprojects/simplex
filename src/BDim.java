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




import java.math.BigDecimal;

public class BDim extends Object{
    int myDim;
    BDim dimension;
    BigDecimal passo=new BigDecimal(Simplex.passo);
    BigDecimal uno=new BigDecimal("1");
    BigDecimal muno=new BigDecimal("-1");

    public BDim(int myd){
	myDim=myd+1;
	//	System.out.println(myDim);
	if(myDim<Simplex.dimension){
	    dimension=new BDim(myDim);
	}
    }
    public void iterPrint(BigDecimal[] vet){

	if(myDim==1){
	    istruzioniPerPrimo(vet);
	}
	else{
	    if(myDim==Simplex.dimension){
		istruzioniPerUltimo(vet);
	    }
	    else{
		istruzioni(vet);
	    }

	}
	/*
	switch (myDim) {
	case 1: istruzioniPerPrimo(vet);
	    break;
	case Simplex.dimension: istruzioniPerUltimo(vet);
	    break;
	default: istruzioni(vet);
	    break;
	}
	*/
    } 

    public void istruzioniPerPrimo(BigDecimal[] vet){
	    for(int i=0;i<((uno.divide(passo)).add(uno)).intValue();i++){
		vet[myDim-1]=passo.multiply(new BigDecimal(i+""));
		//		System.out.println(vet[0]);
		dimension.iterPrint(vet);
	    }
    }

    public void istruzioni(BigDecimal[] vet){
	BigDecimal tot=new BigDecimal("0.0");
	for(int i=0;i<myDim-1;i++){
	    tot=tot.add(vet[i]);
	}
	if(tot.compareTo(new BigDecimal("1"))<0){
	    for(int i=0;i<(((tot.multiply(muno)).add(uno)).divide(passo)).intValue()+1;i++){
		vet[myDim-1]=passo.multiply(new BigDecimal(i+""));
		//		System.out.println(vet[0]+"  "+vet[1]);
		dimension.iterPrint(vet);
	    }
	}
	else{
	    for(int i=myDim-1;i<Simplex.dimension;i++){
		vet[i]=new BigDecimal("0.0");
	    }
	    //	    System.out.print(vet[0]);
	    Simplex.out.print(vet[0]);
	    for(int i=1;i<vet.length;i++){
		//	    	    System.out.print(";"+vet[i]);
		Simplex.out.print(";"+vet[i]);
	    }
	    //	    System.out.println();
	    Simplex.out.println();
	}
    }

    public void istruzioniPerUltimo(BigDecimal[] vet){
	BigDecimal tot=new BigDecimal("0.0");
	for(int i=0;i<myDim-1;i++){
	    tot=tot.add(vet[i]);
	}
	vet[myDim-1]=(tot.multiply(muno)).add(uno);

	//	    System.out.print(vet[0]);
	    Simplex.out.print(vet[0]);
	    for(int i=1;i<vet.length;i++){
		//	    	    System.out.print(";"+vet[i]);
		Simplex.out.print(";"+vet[i]);
	    }
	    //	    System.out.println();
	    Simplex.out.println();
    }

    public void print(){
	System.out.println(myDim);
    }

}
