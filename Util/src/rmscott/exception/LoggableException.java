/**
 * RMSCOTT Prototyping code
 */
package rmscott.exception;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Exception occurs when deleting content
 * 
 * @author Richard M. Scott
 */
public class LoggableException extends Exception {
	
	private static final long serialVersionUID = 1548274543930187627L;

	protected String message = null;
	protected boolean isLogged = false;
	
	/**
	 * Get the property isLogged
	 * @return boolean
	 */
	public boolean isLogged() {
		return isLogged;
	}

	/**
	 * Sets the property isLogged
	 * @param pIsLogged
	 */
	public void setLogged(boolean pIsLogged) {
		this.isLogged = pIsLogged;
	}

	/**
	 * This is the constructor for this class.
	 * 
	 * @param pMessage
	 *            A String containing the error message.
	 */
	public LoggableException(String pMessage) {
		super(pMessage);
		this.setMessage(pMessage);
	}

	/**
	 * This is the constructor for this class.
	 * 
	 * @param pThr
	 *            A Throwable Object reference.
	 */
	public LoggableException(Throwable pThr) {
		super(pThr);
	}

	/**
	 * Gets the message
	 * @return the message
	 */
	public String getMessage() {
	    return message;
	}

	/**
	 * Sets the message
	 * @param message the message to set
	 */
	public void setMessage(String message) {
	    this.message = message;
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
