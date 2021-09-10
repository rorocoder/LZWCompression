
public class LZWTester {

	public static void main(String[] args) {
		Encoder LZW = new Encoder();
		
		System.out.println(LZW.getDictionary());
		//System.out.println(LZW.encode("abcabcabcabc"));
		System.out.println(LZW.encode("thisisthe"));
	}

}
