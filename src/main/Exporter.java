package main;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.MBeanServerBuilder;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import jwf.JobExecutor;
import jwf.JobFactory;
import jwf.JobTrigger;

import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.support.MBeanServerFactoryBean;

import com.sun.management.jmx.MBeanServerImpl;

public class Exporter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// MBeanServer jmxServer = new MBeanServerImpl();

		MBeanServerFactoryBean sfb = new MBeanServerFactoryBean();
		sfb.setLocateExistingServerIfPossible(true);
		sfb.afterPropertiesSet();

		AnnotationMBeanExporter exporter = new AnnotationMBeanExporter();
		exporter.setServer((MBeanServer) sfb.getObject());

		String appName = "JobRunner";
		String executorType = "Quartz";
		String executorName = "QuartzJobExecutor";
		String category = "jobs";
		String jobType = "transliteration";
		String triggerName = "transliterationTrigger";
		String factoryName = "transliterationFactory";
		String triggerName2 = "transliterationTrigger2";
		String factoryName2 = "transliterationFactory2";
		String factoryName3 = "transliterationFactory3";

		String executor = MessageFormat.format(
				"{0}:type=JobExecutors,executorType={1},executorName={2}",
				appName, executorType, executorName);
		String trigger = MessageFormat
				.format(
						"{0}:type=JobExecutors,executorType={1},category={2},jobType={3},triggerName={4}",
						appName, executorType, category, jobType, triggerName);
		String factory = MessageFormat
				.format(
						"{0}:type=JobExecutors,executorType={1},category={2},jobType={3},triggerName={4},factoryName={5}",
						appName, executorType, category, jobType, triggerName,
						factoryName);
		String trigger2 = MessageFormat
				.format(
						"{0}:type=JobExecutors,executorType={1},category={2},jobType={3},triggerName={4}",
						appName, executorType, category, jobType, triggerName2);
		String factory2 = MessageFormat
				.format(
						"{0}:type=JobExecutors,executorType={1},category={2},jobType={3},triggerName={4},factoryName={5}",
						appName, executorType, category, jobType, triggerName2,
						factoryName2);
		String factory3 = MessageFormat
				.format(
						"{0}:type=JobExecutors,executorType={1},category={2},jobType={3},triggerName={4},factoryName={5}",
						appName, executorType, category, jobType, triggerName2,
						factoryName3);

		try {
			ObjectName executorON = ObjectName.getInstance(executor);
			ObjectName triggerON = ObjectName.getInstance(trigger);
			ObjectName factoryON = ObjectName.getInstance(factory);
			ObjectName triggerON2 = ObjectName.getInstance(trigger2);
			ObjectName factoryON2 = ObjectName.getInstance(factory2);
			ObjectName factoryON3 = ObjectName.getInstance(factory3);

			exporter.registerManagedResource(new JobExecutor(), executorON);
			exporter.registerManagedResource(new JobTrigger(), triggerON);
			exporter.registerManagedResource(new JobFactory(), factoryON);
			exporter.registerManagedResource(new JobTrigger(), triggerON2);
			exporter.registerManagedResource(new JobFactory(), factoryON2);
			exporter.registerManagedResource(new JobFactory(), factoryON3);

			Thread.sleep(600 * 1000);

		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
