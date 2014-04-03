package jftp.client;

import jftp.connection.Connection;

public abstract class Client {

	protected String username;
	protected String password;
	protected String host;
	protected int port;
	
	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void setCredentials(String username, String password) {
		
		this.username = username;
		this.password = password;		
	}

	/**
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract Connection connect();
	
	/**
	 * 
	 */
	public abstract void disconnect();
}