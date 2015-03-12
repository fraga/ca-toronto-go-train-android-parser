package org.mtransit.parser.ca_toronto_go_train;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.mtransit.parser.DefaultAgencyTools;
import org.mtransit.parser.Utils;
import org.mtransit.parser.gtfs.data.GRoute;
import org.mtransit.parser.gtfs.data.GStop;
import org.mtransit.parser.gtfs.data.GTrip;
import org.mtransit.parser.mt.data.MRoute;
import org.mtransit.parser.mt.data.MTrip;

public class TorontoGoTrainAgencyTools extends DefaultAgencyTools{
	private static final String _DIGITS = "[\\d]+";

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

	@Override
	public void setTripHeadsign(MRoute mRoute, MTrip mTrip, GTrip gTrip) {
		if(StringUtils.isEmpty(gTrip.trip_headsign) || StringUtils.isBlank(gTrip.trip_headsign))
			mTrip.setHeadsignString("Unknown", mTrip.getHeadsignId());
		else
			super.setTripHeadsign(mRoute, mTrip, gTrip);
	}

	@Override
	public String cleanStopName(String gStopName) {
		return gStopName.replaceAll("GO Station", "");
	}

	@Override
	public long getRouteId(GRoute gRoute) {
		return Long.parseLong(MatchNumber(gRoute.route_id));
	}

	@Override
	public int getStopId(GStop gStop) {
		return Integer.parseInt(MatchNumber(gStop.stop_id));
	}

	@Override
	public String cleanTripHeadsign(String tripHeadsign) {
		return super.cleanTripHeadsign(tripHeadsign);
	}

	public String MatchNumber(String toMatch) {
		if(toMatch == null || StringUtils.isBlank(toMatch))
			return "";

		Matcher match = Pattern.compile(_DIGITS).matcher(toMatch);
		match.find();

		return match.group();
	}
}
