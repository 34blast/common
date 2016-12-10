/**
 * RMSCOTT Prototyping code
 */
package rmscott.exception;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Exception occurs when a supposedly existing content record cannot be found.
 * 
 * @author Richard M. Scott
 */
public class ValidationException extends LoggableException {

	private static final long serialVersionUID = 4342286961546583037L;

	/**
	 * This is the constructor for this class.
	 * 
	 * @param pMessage
	 * @param pIsLogged
	 */
	public ValidationException(String pMessage, boolean pIsLogged) {
		super(pMessage);
		this.setMessage(pMessage);
		this.setLogged(pIsLogged);
	}

	/**
	 * This is the constructor for this class.
	 * 
	 * @param pThr
	 * @param pIsLogged
	 */
	public ValidationException(Throwable pThr, boolean pIsLogged) {
		super(pThr);
		this.setLogged(pIsLogged);
	}

	/**
	 * The purpose of the readObject is to ensure the object is not null during
	 * serialization.
	 * 
	 * @param pObjectInputStream
	 * @throws java.io.IOException
	 * @throws java.lang.ClassNotFoundException
	 */
	protected void readObject(ObjectInputStream pObjectInputStream)
			throws IOException, ClassNotFoundException {

		if (pObjectInputStream == null) {
			StringBuffer sb = new StringBuffer(this.getClass().getName());
			sb.append(": read object is null"); //$NON-NLS-1$
			throw new IOException(sb.toString()); //$NON-NLS-1$
		}
		return;
	} // end of readObject

}

