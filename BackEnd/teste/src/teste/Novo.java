package teste;

public class Novo {
	public static void main(String[] args) {

		try {
			op(0);
		} catch (IllegalArgumentException e) {
			System.err.println("X");
		} catch (Exception e) {
			System.err.println("Y");
		}finally {
			System.err.println("Z");
		}
	}
	private static void op(int valor) {
		if(valor == 0) {
			throw new IllegalArgumentException("U");
		}
	}
}
