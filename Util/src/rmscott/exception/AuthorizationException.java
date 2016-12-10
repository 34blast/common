/**
 * RMSCOTT Prototyping code
 */
package rmscott.exception;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Occurs when a user attempts to access or do something they do not have
 * permission to access or do.
 * 
 * @author Richard M. Scott
 */
public class AuthorizationException extends LoggableException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6678050294962123253L;
	private String pMessage = null;

	/**
	 * This is the constructor for this class.
	 * 
	 * @param pMessage
	 * @param pIsLogged
	 */
	public AuthorizationException(String pMessage, boolean pIsLogged) {
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
	public AuthorizationException(Throwable pThr, boolean pIsLogged) {
		super(pThr);
		this.setLogged(pIsLogged);
	}
	/**
	 * Gets the message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return pMessage;
	}

	/**
	 * Sets the message
	 * 
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.pMessage = message;
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
