package frc.robot.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class OzoneLogger {
	
	private static OzoneLogger instance	= null;
	private long startupTime = System.currentTimeMillis();
	private Logger logger	= Logger.getLogger("frc.robot");
	private boolean	initted	= false;

	private OzoneLogger() {
		
	}
	
	public static OzoneLogger getInstance() {
		if (instance == null) {
			instance	= new OzoneLogger();
		}
		return instance;
	}

	public void init(Level all) {
		if (initted)
				return;
		
		logger.setUseParentHandlers(false);
		
		try {
			logger.setLevel(all);
			
			Handler	consoleHandler	= new ConsoleHandler();
			consoleHandler.setFormatter(new OzoneLogFormatter());
			consoleHandler.setLevel(Level.ALL);
			logger.addHandler(consoleHandler);
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, "XX", e);
		} finally {
			initted	= true;
		}
	}
	
	
	public class OzoneLogFormatter extends Formatter {
		StringBuffer	b	= new StringBuffer();
		SimpleDateFormat dateFormat	= new SimpleDateFormat("HH.mm:ss");

		@Override
		public String format(LogRecord record)  {
			String []parts	= record.getSourceClassName().split("\\.");

			b.setLength(0);
		
			b.append("[" + record.getLevel()).append("]")
				.append('[').append(LocalDateTime.now().minus(startupTime, ChronoUnit.MILLIS).format(DateTimeFormatter.ofPattern("mm:ss.SSS"))).append(']')
				.append('[').append(parts[parts.length-1]).append(']')
				.append('[').append(record.getMessage()).append(']')
				.append('\n');
			 return b.toString();
		}		
	}
	
	public class LogTest {
		final Logger	logger	= Logger.getLogger(LogTest.class.getName());
		public LogTest() {
			
		}
		
		public void test() {
			logger.info("LogTest info");
			logger.fine("LogTest fine");
		}
	}

	public static void main(String[] args) {
		
		OzoneLogger.getInstance().init(Level.FINE);
		
		Logger logger = Logger.getLogger("frc.robot.main");
		logger.info("Information");
		logger.severe("Severe");
		LogTest logTest	=  OzoneLogger.getInstance().new LogTest();
		logTest.test();
		
		logger.finest("main finest");
	}

}
