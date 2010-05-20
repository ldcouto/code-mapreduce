package pt.um.mrc.util.control;

import org.apache.hadoop.conf.Configuration;

public class CheckedJobInfo {

	private String usageMessage;
	private Configuration conf;

	public CheckedJobInfo(Configuration conf, String uM) {
		this.conf = conf;
		this.usageMessage = uM;
	}

	public String getUsageMessage() {
		return usageMessage;
	}

	public void setUsageMessage(String usageMessage) {

		this.usageMessage = usageMessage;
	}

	public Configuration getConf() {
		return conf;
	}

}
