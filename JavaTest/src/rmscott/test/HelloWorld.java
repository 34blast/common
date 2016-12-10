/**
 * RMSCOTT Prototyping
 */
package rmscott.test;

/**
 * @author Richard M. Scott
 *
 */
public class HelloWorld implements java.io.Serializable {

	private static final long serialVersionUID = -801955040500678181L;

	private String message = null;

	/**
	 * Constructor for this class
	 */
	public HelloWorld() {

	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HelloWorld [message=" + message + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloWorld obj1 = new HelloWorld();
		obj1.setMessage("Hello World!");
		System.out.println(obj1.getMessage());
		String[] names = { "Bilbo", "Frodo", "Sam", "Gandolf", "Legalos", "Aragon" };

		for (String name : names) {
			System.out.println("Hello " + name);
		}
	}

}
