package cn.yunhe.util.pay;

import java.io.IOException;
import java.util.Properties;

public class PayConfig {

	// 商户编号（App ID）
	public static String APP_ID;
	// 商户密钥（App secret）
	public static String APP_SECRET;
	// 回调（响应）地址（Redirect url）
	public static String REDIRECT_URL;

	private static Properties prop = new Properties();

	static {
		try {
			prop.load(PayConfig.class.getClassLoader().getResourceAsStream(
					"cn/yunhe/util/pay/merchantInfo.properties"));
			
			APP_ID=prop.getProperty("APP_ID");
			APP_SECRET=prop.getProperty("APP_SECRET");
			REDIRECT_URL=prop.getProperty("REDIRECT_URL");
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
