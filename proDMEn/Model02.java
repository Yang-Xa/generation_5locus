package proDMEn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;  

public class Model02 {
	 double p[]=new double[10000];
	 String s[]=new String[10000];
	 public  void get(int N,double PDR,double F[],double H2,double K1,double K2, String path) {
		 
		int n=N;
		double m=Math.pow(3, n);
		double pdr=PDR;
		String p0[]=new String [10000];
		double p1[]=new double [10000];
		String p01[]=new String [10000];
		double pf[]=new double[10000];
		double q[][]=new double[100][100];
    
 		double f[]=new double[1000];
 		for(int i=1;i<=n;i++) {
 			f[i]=F[i];
 		}
 		
		
		for(int i=1;i<=n;i++) {q[1][i]=(1-f[i])*(1-f[i]);}
		for(int i=1;i<=n;i++) {q[2][i]=2*f[i]*(1-f[i]);}
		for(int i=1;i<=n;i++) {q[3][i]=f[i]*f[i];}
    	 double a=0.0000,b=0.0000;
    	 double k1=K1;
    	 double k2=K2;
    	
    		 for(int i=0;i<m;i++)
    		 {
    	         pf[i]=1;
    			 String s="0";
    			 p0[i]=change(i);
    			 char chs[]=p0[i].toCharArray();
    		     if(chs.length<n) {
    			 for(int j=2;j<=n-chs.length;j++) {
    				 s=s+"0";
    			 }
    			 p0[i]=s+p0[i];
    		     }
    		 char ch[]=p0[i].toCharArray();
    	
    		 for(int j=1;j<=n;j++) {
    			 if (Character.isDigit(ch[j-1])){ 
    				    int l = Integer.parseInt(String.valueOf(ch[j-1]));
    				pf[i]=pf[i]*q[l+1][j];
    				}   
    		 }
    		 
    		 } 
    	 for( a=0.0000;a<=1.0000;a=a+0.0001) {
    		 for(b=0.0000;b<=1.0000;b=b+0.0001) {
		for(int i=0;i<m;i++) {
			int count0=0,count1=0,count2=0;
		  char chs[]=p0[i].toCharArray();
		
		 for(int j=0;j<chs.length;j++) {
			 if(chs.length<n||chs[j]=='0') count0++;
			 if(chs[j]=='1') count1++;
			 if(chs[j]=='2') count2++;
			 }
	
		 if(count0>=1) {p1[i]=a;p01[i]="a";}
		 if(count0==0)  {p1[i]=a*(1+b);p01[i]="a(1+b)";}
	
		
		}
    
    	    	 double pd=Sum(m,pf,p1);
    	    	 double p2[]=new double[10000];
    	    	 for(int i=0;i<m;i++) {
    	    		 p2[i]=(p1[i]-pdr)*(p1[i]-pdr);
    	    	 }
    	    	 double h2=Sum(m,pf,p2)/(pdr*(1-pdr));
    		
    		     if((Math.abs(pd-pdr)<=k1)&&(Math.abs(h2-H2)<=k2)) 
    	    
    			 { 
					 File f01=new File(path);
					 FileWriter fw01=null;
					 try {
						 if(!f01.exists()) {
							 f01.createNewFile();
						 }
						 fw01=new FileWriter(f01);
						 BufferedWriter out=new BufferedWriter(fw01);

						 out.write("a = "+a+"     b = "+b+"\n");
						 out.write("Probability table£º\n");

						 for(int i=0;i<m;i++)
						 {
							 out.write(p1[i]+"\n");
						 }
						 out.close();
					 } catch (IOException e) {
						 e.printStackTrace();
					 }

    		    	 for(int i=0;i<m;i++) {
    		    		 p[i]=p1[i];
    		    		 s[i]=p01[i];
    		    	 }
    		    	 return;
    			 }
    		 }
    			 }
    	
    		 }
    		 
    	 
    public static double Sum(double m,double pm[],double pn[]) {
    	
    	double sum=0;
    	for(int i=0;i<m;i++) {
    		sum=sum+pm[i]*pn[i];
    	}
 		return sum;
 	}
    
 
  		public static String change(int n) {
  			
  	        int remainder;
  	        int sum = 0;
  	        int k = 1;

  	        while(n != 0){

  	            remainder = n % 3;
  	            n /= 3;
  	            sum = sum + remainder * k;
  	            k *= 10;

  	        }
  	        String result=String.valueOf(sum);
  	       return result;
  		}
}
