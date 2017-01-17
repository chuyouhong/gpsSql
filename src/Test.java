
public class Test {

	public static void main(String[] args) {
		Double a=2.16;
		Double b=Math.floor(a*10)/10;
		
		String bb=String.format("%.1f", Double.valueOf(a));
		String bab=String.format("%.1f", Double.valueOf(b));
		System.out.println(bb);
		System.out.println(bab);
		String aaa="622 8480660643815310";
		System.out.println(aaa.replace(" ", ""));
		
	}

}
