import java.io.IOException;

public class LZWTester {

	public static void main(String[] args) throws IOException {
		Encoder LZW = new Encoder();
		
		
		System.out.println(LZW.encode("lzw-file1.txt"));
		System.out.println(LZW.getOutput());
		
		
		
		
	}

}
