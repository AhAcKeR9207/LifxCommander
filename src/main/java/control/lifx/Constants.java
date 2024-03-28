package control.lifx;

import java.awt.Color;

public class Constants {
	public static final int PORT = 56700;
	
	public class Levels {
		public static final int MIN = 0;
		public static final int MAX = 65535;
	}

	public class Power {
		public static final int ON = Levels.MAX;
		public static final int OFF = Levels.MIN;
	}

	public class Kelvin {
		public static final int WARMEST = 2500;
		public static final int MEDIUM = 4000;
		public static final int COOLEST = 9000;
	}

	// Could implement these.  Probably won't...
	public static class Waveforms{
		public static final int SAWTOOTH = 0;
		public static final int SINUSOID = 1;
		public static final int HALF_SINE = 2;
		public static final int TRIANGLE = 3;
		public static final int PULSE = 4;
	}

	public static class Defaults {
		public static final int BRIGHTNESS = 100;
		public static final Color COLOR = Color.WHITE;
		public static final double DURATION = 10;
		public static final boolean STATE = true;
	}
}