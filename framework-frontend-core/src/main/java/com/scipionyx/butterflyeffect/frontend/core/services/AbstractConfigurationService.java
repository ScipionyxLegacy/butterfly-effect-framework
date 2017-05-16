package com.scipionyx.butterflyeffect.frontend.core.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * 
 * @author Renato Mendes
 *
 */
public abstract class AbstractConfigurationService<T> {

	private List<T> configurations;

	@PostConstruct
	public void init() throws IOException {
		//
		// objectMapper = new ObjectMapper();
		//
		if (configurations == null) {
			configurations = new ArrayList<>();
		}
		//
		readConfigurations();

	}

	public abstract void readConfigurations() throws IOException;

	/**
	 * Read a resource from the class path or from within a jar.<br>
	 * TODO - it seems that it is not working when distributing the software as
	 * a jar, most likelly it can not find the files within jars.
	 * 
	 * @param name
	 *            resource name,
	 * @param classLoader
	 *            classloader
	 * @return list of files as input stream
	 * @throws IOException
	 *             if any error
	 */
	protected List<InputStream> loadResources(final String name, final ClassLoader classLoader) throws IOException {
		final List<InputStream> list = new ArrayList<InputStream>();
		final Enumeration<URL> systemResources = (classLoader == null ? ClassLoader.getSystemClassLoader()
				: classLoader).getResources(name);
		while (systemResources.hasMoreElements()) {
			list.add(systemResources.nextElement().openStream());
		}
		return list;
	}

	public List<T> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<T> configurations) {
		this.configurations = configurations;
	}

}
