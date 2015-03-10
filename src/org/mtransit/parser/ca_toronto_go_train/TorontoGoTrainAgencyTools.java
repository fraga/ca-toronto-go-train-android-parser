package org.mtransit.parser.ca_toronto_go_train;

import org.mtransit.parser.DefaultAgencyTools;
import org.mtransit.parser.Utils;

public class TorontoGoTrainAgencyTools extends DefaultAgencyTools{
	@Override
	public void start(String[] args) {
		System.out.printf("Generating GO train data...\n");
		long start = System.currentTimeMillis();
		super.start(args);
		System.out.printf("Generating GO train data... DONE in %s.\n",
				Utils.getPrettyDuration(System.currentTimeMillis() - start));
	}

	public static void main(String[] args) {
		TorontoGoTrainAgencyTools goTrainTools = new TorontoGoTrainAgencyTools();

		args = new String[3];
		args[0] = "input/gtfs.zip";
		args[1] = "output";
		args[2] = "";

		goTrainTools.start(args);
	}
}
