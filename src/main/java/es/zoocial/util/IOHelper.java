package es.zoocial.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class IOHelper {
	
	public static void closeQuietly(Closeable stream) {
		if (stream == null)
			return;
		
		try {
			stream.close();
		} catch (IOException e) {
			LogHelper.warning(IOHelper.class, "Could not close stream", e);
		}
	}
	
	
	public static File getValidFile(String filename) {
		ArgsHelper.notEmpty("filename", filename);
		File file;
		if (filename.matches("^file:.*$")) {
			try {
				URL url = new URL(filename);
				file = new File(url.getFile());
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format("Invalid filename url %s", filename), e);
			}
		} else {
			file = new File(filename);
		}
		
		if (!file.exists()) {
			throw new IllegalArgumentException(String.format("File %s does not exists", file.getAbsolutePath()));
		}
		
		return file;

	}

}
