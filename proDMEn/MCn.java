package proDMEn;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class MCn {

	public  static void main(String[] args) {
		  Scanner readers = new Scanner(System.in);
		  System.out.print("k-locus:");
		  int n=readers.nextInt();
		  System.out.print("PDR:");
		double PDR=readers.nextDouble();
		 System.out.print("bias of PDR:");
			double k1=readers.nextDouble();
		  System.out.print("heritability:");
		double H2=readers.nextDouble();
		System.out.print("bias of heritability:");
		double k2=readers.nextDouble();
		double F[]=new double[100];
		  System.out.print("k MAFs:");
		for(int i=1;i<=n;i++) {
			F[i]=readers.nextDouble();
		}

		System.out.print("SNPs:");
		int X=readers.nextInt();
		System.out.print("samples:");
		int C=readers.nextInt();
		System.out.print("diseased samples:");
		int C1=readers.nextInt();
		System.out.print("control samples:");
		int C0=readers.nextInt();
		
		if (readers.nextLine() != "\n") {
		}
		System.out.print("path1:");
		String PATH01=readers.nextLine();
		System.out.print("path2:");
		String PATH02=readers.nextLine();
		int Class = 0;
		double count=Math.pow(3, n);
		double p[]= new double[10000];
		String sp[]=new String [10000];



		System.out.print("Model:");
		String model=readers.nextLine();
		Model01 model01=new Model01();
		Model02 model02=new Model02();
		Model03 model03=new Model03();

		
		if(model.equals("model01")) {
			System.out.println("Calling Model01......"); 
		model01.get(n,PDR,F,H2,k1,k2, PATH01);

		for(int i=0;i<count;i++)
		{
			p[i]=model01.p[i];
			sp[i]=model01.s[i];

		}
		}

		
				if(model.equals("model02")) {
					System.out.println("Calling Model02......");
				model02.get(n,PDR,F,H2,k1,k2, PATH01);



				for(int i=0;i<count;i++)
				{
					p[i]=model02.p[i];
					sp[i]=model02.s[i];

				}
				}
		
				if(model.equals("model03")) {
					System.out.println("Calling Model03......");
				model03.get(n,PDR,F,H2,k1,k2, PATH01);



				for(int i=0;i<count;i++)
				{
					p[i]=model03.p[i];
					sp[i]=model03.s[i];
//					System.out.println(p[i]);
				}
				}

	
		String path01=".\\model.txt";
		File f01=new File(path01);
		FileWriter fw01=null;
		try {
		if(!f01.exists()) {
		f01.createNewFile();
		}
		fw01=new FileWriter(f01);
		BufferedWriter out=new BufferedWriter(fw01);

		for(int i=0;i<count;i++)
		{
		out.write(sp[i]+"\r\n");
		}
		out.close();
		} catch (IOException e) {
		e.printStackTrace();
		}

	
		 double MAF[]=new double[10000];
		 double x0,x;
		 int j=1;
			 do {
				 x0=(double)Math.random();

			 BigDecimal bg = new BigDecimal(x0);
	         x= bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(x>0.05&&x<0.50)
			{
				MAF[j]=x;
				j++;
			}
			 }while(j<=X-n);
			 
		
		String path02=PATH02;
		File f02=new File(path02);
		FileWriter fw02=null;
		try {
		if(!f02.exists()) {
		f02.createNewFile();
		}
		fw02=new FileWriter(f02);
		BufferedWriter out=new BufferedWriter(fw02);
		for(int i=0;i<=X-n-1;i++)
		{
		out.write("N"+i+"\t");
		}
		for(int i=1;i<=n;i++)
		{
		out.write("M0P"+i+"\t");
		}
		out.write("Class\r\n");
		out.close();
		} catch (IOException e) {
		e.printStackTrace();
		}



		int c=0;
		int count1=0;
		int count0=0;
		do
		{
		int mop[]=new int[n];
		String strM;
		String mops[]=new String[n];
		for(int i=0;i<n;i++)
			 {mop[i]=(int)(Math.random()*3);
			  mops[i]=String.valueOf(mop[i]);
			 }
		String a[]=new String [10000];
		int b[]=new int[100000];
		
			 for(int i=0;i<count;i++)
			 {
				 a[i]=change(i);
			 }
		
			 for(int i=0;i<count;i++)
			 {
				 b[i]=Integer.parseInt(a[i]);
			 }

			
			 strM=mops[0];
			 for(int i=1;i<n;i++)
			 {

				 strM=strM+mops[i];
			 }
		
			 int s = Integer.parseInt(strM);


			 for(int i=0;i<count;i++)
			 {
				 if(s==b[i])
				 {
					 Class=Getdata(p[i]);
				 }
			 }

	
			 String str[]= new String[n];
			 for(int i=0;i<n;i++)
		{str[i]=String.valueOf(mop[i]);}

			 if(Class==1&&count1<C1)
				{

			     int N[]=new int[1000];		 
				 for(int i=1;i<=X-n;i++)
					{
					 N[i]=Getdatan(MAF[i]);
					 }


				    c++;
				    count1++;
				    OutToFile(X,n,N,mop,Class,f02);
				}
				else if(Class==0&&count0<C0)
				{
					int N[]=new int[10000];		
					for(int i=1;i<=X-n;i++)
					{
					 N[i]=Getdatan(MAF[i]);
					 }
				

					c++;
				    count0++;
				    OutToFile(X,n,N,mop,Class,f02);
				}

		}while(c<C&&(count1<C1||count0<C0));
		 System.out.println("Run over, output to file successful!\n The total number of samples:"+c+"   diseased samples:"+count1+"   control samples:"+count0);
		 
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

		public static int Getdata(double q) {
			double r=Math.random();
			if(r>=0&&r<=q)
			{
				return 1;
			}
			else {
				return 0;
			}
	}

	
		public static int Getdatan(double MAF) {
			double p1=MAF*MAF;
			double p2=p1+2*(1-MAF)*MAF;
		double r=Math.random();
		if(r<p1)
		{
			return 2;
		}
		if(r>=p1&&r<p2)
		{
			return 1;
		}
		else
			return 0;
		}

	
		public static void OutToFile( int X,int n,int N[],int mop[],int Class,File file ) {

			String str[]=new String [n];
			for(int i=0;i<n;i++)
			{
			str[i]=String.valueOf(mop[i]);
			}
			File f=file;
			FileWriter fw=null;
			try {
			if(!f.exists()) {
			f.createNewFile();
			}
			fw=new FileWriter(f,true);
			BufferedWriter out=new BufferedWriter(fw);
			for(int i=1;i<=X-n;i++)
			{
			out.write(N[i]+"\t");
			}
			for(int i=0;i<n;i++)
			{
			out.write(str[i]+"\t");
			}
			out.write(Class+"\r\n");
			out.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
			}

				public static double NormalDistribution(double u,float v){
					java.util.Random random = new java.util.Random();
					return Math.sqrt(v)*random.nextGaussian()+u;

					}
}
