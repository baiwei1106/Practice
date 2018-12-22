package xxxy.demo.utils;

import java.util.UUID;

public class UUIDUtils {
	
	public static synchronized String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
