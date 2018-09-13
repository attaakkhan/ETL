package etl.context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.test.context.ContextLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class ETLContext.
 */
public class ETLContext implements ContextLoader {

	private static Logger logger = Logger.getLogger(ETLContext.class);
	private static GenericApplicationContext context = init();

	/**
	 * Initiate the Context
	 */
	private static GenericApplicationContext init() {
		ETLContext context = new ETLContext();
		String configLocations[] = new String[] { "classpath:ETLContext.xml" };
		try {
			context.loadContext(configLocations);
		} catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			logger.error(ex.getStackTrace(), ex);
		} catch (Throwable ex) {
			logger.debug(ex.getMessage(), ex);
			logger.error(ex.getStackTrace(), ex);
		}
		return getContext();
	}

	/**
	 * Gets bean.
	 * 
	 */
	public static Object getBean(String name) {
		return context.getBean(name);
	}

	/**
	 * Gets the bean.
	 */
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	/**
	 * Gets the context.
	 */
	public static GenericApplicationContext getContext() {
		return context;
	}

	/**
	 * Sets the context
	 */
	public static GenericApplicationContext setContext(GenericApplicationContext contexts) {
		return context = contexts;
	}

	public static void main(String args[]) {

	}

	/*
	 * 
	 * @see
	 * org.springframework.test.context.ContextLoader#loadContext(java.lang.String[]
	 * )
	 */
	@Override
	public ApplicationContext loadContext(String... locations) throws Exception {
		if (getContext() == null) {
			final GenericApplicationContext context = new GenericApplicationContext();
			createBeanDefinitionReader(context).loadBeanDefinitions(locations);
			AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
			context.refresh();
			context.registerShutdownHook();
			String[] beanNames = context.getBeanDefinitionNames();
			logger.info("Spring Context, length " + beanNames.length + "Beans");
			for (String beanName : beanNames) {
				logger.info("******* " + beanName);
			}
			setContext(context);
		}
		return getContext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.test.context.ContextLoader#processLocations(java.lang.
	 * Class, java.lang.String[])
	 */
	@Override
	public String[] processLocations(Class<?> clazz, String... locations) {
		return locations;
	}

	/**
	 * Creates the bean definition reader.
	 */
	protected static BeanDefinitionReader createBeanDefinitionReader(final GenericApplicationContext context) {
		AbstractBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.setResourceLoader(new FileSystemResourceLoader());
		return reader;
	}

}
