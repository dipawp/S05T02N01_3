package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.util;

import com.google.gson.Gson;

public class GsonUtils {
	public static String serialize(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src);
	}

	public static <D> D toObject(Object src, Class<D> dClass){
		Gson gson = new Gson();
		String srcJson = gson.toJson(src);
		return gson.fromJson(srcJson,dClass);
	}
}
