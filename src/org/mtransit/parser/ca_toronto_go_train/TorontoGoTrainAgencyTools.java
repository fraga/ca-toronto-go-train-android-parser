package org.mtransit.parser.ca_toronto_go_train;

import org.mtransit.parser.DefaultAgencyTools;
import org.mtransit.parser.Utils;
import org.mtransit.parser.gtfs.data.GRoute;
import org.mtransit.parser.gtfs.data.GStop;

public class TorontoGoTrainAgencyTools extends DefaultAgencyTools{
	@Override
	public void start(String[] args) {
		System.out.printf("Generating GO train data...\n");
		long start = System.currentTimeMillis();
		super.start(args);
		System.out.printf("Generating GO train data... DONE in %s.\n",
				Utils.getPrettyDuration(System.currentTimeMillis() - start));
	}

	@Override
	public long getRouteId(GRoute gRoute) {
		// TODO Auto-generated method stub
		return Long.parseLong(gRoute.route_id.replaceAll("-", ""));
	}

	public static void main(String[] args) {
		TorontoGoTrainAgencyTools goTrainTools = new TorontoGoTrainAgencyTools();

		args = new String[3];
		args[0] = "input/gtfs.zip";
		args[1] = "output";
		args[2] = "";

		goTrainTools.start(args);
	}

	@Override
	public String cleanStopName(String gStopName) {
		return gStopName.replaceAll("GO Station", "");
	}
}
