package jftp.client;

import jftp.connection.Connection;
import jftp.connection.ConnectionFactory;
import jftp.exception.ClientDisconnectionException;
import jftp.exception.ConnectionInitialisationException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SftpClient extends Client {

	private static final String SFTP = "sftp";
	private static final String CONNECTION_ERROR_MESSAGE = "Unable to connect to host %s on port %d";

	private JSch jsch;
	private ConnectionFactory connectionFactory;

	private Session session;
	private Channel channel;
	
	public SftpClient() {
		this.jsch = new JSch();
		this.connectionFactory = new ConnectionFactory();
	}

	public Connection connect() {

		session = null;
		channel = null;

		try {

			session = jsch.getSession(username, host, port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);

			session.connect();

			channel = session.openChannel(SFTP);
			channel.connect();

		} catch (JSchException e) {
			throw new ConnectionInitialisationException(String.format(CONNECTION_ERROR_MESSAGE, host, port), e);
		}

		return connectionFactory.createSftpConnection(channel);
	}

	public void disconnect() {
		
		if(null == channel || null == session)
			throw new ClientDisconnectionException("The underlying connection was never initially made.");
		
		channel.disconnect();
		session.disconnect();
	}

}