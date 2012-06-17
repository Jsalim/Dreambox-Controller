package ca.b02.a01.dbctrl;

import java.text.SimpleDateFormat;

public class Logger {

	public static final String format = "dd.MM.yyyy HH:mm:ss";

	@SuppressWarnings("rawtypes")
	public static void debug(Class clazz, String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String timestamp = sdf.format(System.currentTimeMillis());
		System.out.println(timestamp + " DEBUG " + clazz.getSimpleName() + ": "
				+ msg);
	}

	@SuppressWarnings("rawtypes")
	public static void info(Class clazz, String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String timestamp = sdf.format(System.currentTimeMillis());
		System.out.println(timestamp + " INFO " + clazz.getSimpleName() + ": "
				+ msg);
	}

	@SuppressWarnings("rawtypes")
	public static void error(Class clazz, String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String timestamp = sdf.format(System.currentTimeMillis());
		System.out.println(timestamp + " ERROR " + clazz.getSimpleName() + ": "
				+ msg);
	}

	@SuppressWarnings("rawtypes")
	public static void fatal(Class clazz, String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String timestamp = sdf.format(System.currentTimeMillis());
		System.out.println(timestamp + " FATAL " + clazz.getSimpleName() + ": "
				+ msg);
	}
}
