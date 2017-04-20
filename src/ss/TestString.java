package ss;

public class TestString {
	
	public static void main(String[] args){
		String s1="meetting";
		String s2=new String("meetting");
		String s3="meet";
		String s4="ting";
		String s5="meet"+"ting";
		String s6=s3+s4;
		System.out.println(s1==s2);//false
		System.out.println(s1==s5);//true
		System.out.println(s1==s6);//false
		System.out.println(s5==s6);//false
		System.out.println(s1==s6.intern());//true
		System.out.println(s2==s2.intern());//false
		System.out.println(s1==s2.intern());//true
		
		 String[] a1 = new String[5];
		 for(int i=0 ; i< 5; i++ ){a1[i]="";}
		 String a2[ ] = new String[]{"","","","",""} ;
		  String[ ] a3 ={"","","","",""};
		  String a4[ ] = new String[]{"","","","",""};
	}

}
