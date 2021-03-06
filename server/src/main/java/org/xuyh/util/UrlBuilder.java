package org.xuyh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Builder on URL.
 * 
 * @author XuYanhang
 *
 */
public class UrlBuilder implements Cloneable, java.io.Serializable {

	private static String schemaRegex;
	private static String usernameRegex;
	private static String passwordRegex;
	private static String hostnameRegex;
	private static String portRegex;
	private static String pathRegex;
	private static String argumentsRegex;
	private static String anchorRegex;
	private static String urlRegex;

	static {
		schemaRegex = "https|http|ftp|rtsp|rtspt|rtspu|mms|udp|rtmp|file"; // Schema
		usernameRegex = "[0-9a-z_!~*'().&=+$%-]+"; // Username
		passwordRegex = "[0-9a-z_!~*'().&=+$%-]+"; // Password
		hostnameRegex = "(?<ip>(([0-9]{1,3}\\.){3}[0-9]{1,3}))" // Ip
				+ "|(?<domain>(([0-9a-z_!~*'()-]+\\.)*" // DomainName1
				+ "([0-9a-z][0-9a-z_]{0,61})?(([0-9a-z])\\.([a-z]{2,6}))))" // DomainName2
		; // Hostname
		portRegex = "[0-9]{1,5}"; // Port
		pathRegex = "(/([0-9a-z_!~*'().;:@&=+$,%-]+))*/?"; // Path
		argumentsRegex = "([^#]*)"; // Arguments
		anchorRegex = "(.*)"; // Anchor
		urlRegex = "((?<schema>" + schemaRegex + ")://)" // Schema
				+ "((?<username>" + usernameRegex + ")?(:(?<password>" + passwordRegex + "))?(?<userinfoAt>@))?" // UserInfo
				+ "(?<hostname>" + hostnameRegex + ")?" + "(:(?<port>" + portRegex + "))?" // Hostname_Port
				+ "(?<path>" + pathRegex + ")" // Path
				+ "(\\?(?<arguments>" + argumentsRegex + "))?" // Arguments
				+ "(#(?<anchor>" + anchorRegex + "))?" // Anchor
		; // Url
	}

	/**
	 * Parse a url String to the {@link UrlBuilder} or null when failed. <br />
	 */
	public static UrlBuilder from(String url) {

		if (null == url || url.isEmpty()) {
			return null;
		}

		Pattern p = Pattern.compile("^(" + urlRegex + ")$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		if (!matcher.find()) {
			return null;
		}

		UrlBuilder ipcUrl = new UrlBuilder();
		ipcUrl.schema = matcher.group("schema");
		ipcUrl.username = matcher.group("username");
		ipcUrl.password = matcher.group("password");
		ipcUrl.hostname = matcher.group("hostname");
		String port = matcher.group("port");
		ipcUrl.port = (port == null || port.isEmpty()) ? null : Integer.parseInt(port);
		if (null != ipcUrl.port && ipcUrl.port > 0Xffff) {
			return null;
		}
		ipcUrl.path = matcher.group("path");
		ipcUrl.arguments = matcher.group("arguments");
		ipcUrl.anchor = matcher.group("anchor");

		return ipcUrl;
	}

	/**
	 * The schema of the URL.
	 */
	private String schema;

	/**
	 * The username of the URL.
	 */
	private String username;

	/**
	 * The password of the URL.
	 */
	private String password;

	/**
	 * The hostname of the URL.
	 */
	private String hostname;

	/**
	 * The port of the URL.
	 */
	private Integer port;

	/**
	 * The path of the URL.
	 */
	private String path;

	/**
	 * The arguments of the URL.
	 */
	private String arguments;

	/**
	 * The anchor of the URL.
	 */
	private String anchor;

	/**
	 * Create an empty URL. <br />
	 */
	public UrlBuilder() {
		this.schema = "http";
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		StringBuilder builder = new StringBuilder();
		builder.append(schema + "://");
		if (null != username) {
			builder.append(username);
		}
		if (null != password) {
			builder.append(':');
			builder.append(password);
		}
		if (null != username || null != password || (null == hostname && null != port)) {
			builder.append('@');
		}
		if (null != hostname) {
			builder.append(hostname);
		}
		if (null != port) {
			builder.append(':');
			builder.append(port.intValue());
		}
		if (null != path) {
			builder.append(path);
		}
		if (null != arguments) {
			builder.append('?');
			builder.append(arguments);
		}
		if (null != anchor) {
			builder.append('#');
			builder.append(anchor);
		}
		return builder.toString();
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the arguments
	 */
	public String getArguments() {
		return arguments;
	}

	/**
	 * @return the anchor
	 */
	public String getAnchor() {
		return anchor;
	}

	/**
	 * @param schema
	 *            the schema to set
	 */
	public boolean setSchema(String schema) {
		if (!checkUnnullValueChange(schemaRegex, this.schema, schema)) {
			return false;
		}
		this.schema = schema;
		return true;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public boolean setUsername(String username) {
		if (!checkValueChanged(usernameRegex, this.username, username)) {
			return false;
		}
		this.username = username;
		return true;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public boolean setPassword(String password) {
		if (!checkValueChanged(passwordRegex, this.password, password)) {
			return false;
		}
		this.password = password;
		return true;
	}

	/**
	 * @param hostname
	 *            the hostname to set
	 */
	public boolean setHostname(String hostname) {
		if (!checkValueChanged(hostnameRegex, this.hostname, hostname)) {
			return false;
		}
		this.hostname = hostname;
		return true;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public boolean setPort(Integer port) {
		if (null == port) {
			if (null == this.port) {
				return false;
			}
			this.port = null;
			return true;
		}
		if (port > 0Xffff || port < 0) {
			return false;
		}
		this.port = port;
		return true;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public boolean setPath(String path) {
		if (!checkValueChanged(pathRegex, this.path, path)) {
			return false;
		}
		this.path = path;
		return true;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public boolean setArguments(String arguments) {
		if (!checkValueChanged(argumentsRegex, this.arguments, arguments)) {
			return false;
		}
		this.arguments = arguments;
		return true;
	}

	/**
	 * @param anchor
	 *            the anchor to set
	 */
	public boolean setAnchor(String anchor) {
		if (!checkValueChanged(anchorRegex, this.anchor, anchor)) {
			return false;
		}
		this.anchor = anchor;
		return true;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((schema == null) ? 0 : schema.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((arguments == null) ? 0 : arguments.hashCode());
		result = prime * result + ((anchor == null) ? 0 : anchor.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlBuilder other = (UrlBuilder) obj;
		if (schema == null) {
			if (other.schema != null)
				return false;
		} else if (!schema.equals(other.schema))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (arguments == null) {
			if (other.arguments != null)
				return false;
		} else if (!arguments.equals(other.arguments))
			return false;
		if (anchor == null) {
			if (other.anchor != null)
				return false;
		} else if (!anchor.equals(other.anchor))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getUrl();
	}

	/**
	 * @see java.lang.Object#clone()
	 */
	public UrlBuilder clone() {
		try {
			return (UrlBuilder) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	/**
	 * Check if a value should be changed. <br />
	 */
	private static boolean checkValueChanged(String regex, Object currentValue, Object targetValue) {
		if (null == targetValue) {
			if (null == currentValue) {
				return false;
			}
			return true;
		}
		return checkUnnullValueChange(regex, currentValue, targetValue);
	}

	/**
	 * Check if a value should be changed who expect not be null. <br />
	 */
	private static boolean checkUnnullValueChange(String regex, Object currentValue, Object targetValue) {
		if (null == targetValue) {
			return false;
		}
		if (currentValue != null && currentValue.equals(targetValue)) {
			return false;
		}
		Pattern p = Pattern.compile("^(" + regex + ")$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(targetValue.toString());
		return matcher.find();
	}

	private static final long serialVersionUID = 6789936819342581956L;

}
