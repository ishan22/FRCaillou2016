package org.usfirst.frc.team115.lib;

public class VisionDataManager {
/*
	public static final long PACKET_DELAY_MS = 20; //tested @akhil99 8/24/16
	
	public static final String SOCKET_SERVER = "ws://localhost:5801";
	
	public static final String ACTIVITY_NAME = "com.mvrt.bullseye/.MainActivity";
	
	public WebSocketClient socketClient;
	
	public volatile VisionPose latestPose;
	
	public VisionDataManager(){
		latestPose = new VisionPose(System.currentTimeMillis(), 0, 0);
	}
	
	public void init() {
		System.out.println("> Initializing RioDroid");
		initRioDroid();
		System.out.println("> Initializing SocketClient");
		initSocketClient();
	}
	
	public void close() {
		if(socketClient != null) socketClient.close();
		RIOdroid.executeCommand("adb shell kill " + ACTIVITY_NAME);
	}
	
	public void initRioDroid() {
		
		try{
			RIOdroid.initUSB();
			RIOadb.init();
		}
		catch (Exception e){
			System.err.println("> Can't init RioADB");
			return;
		}
		
		Timer.delay(1);
		 
		 System.out.println("run adb start-server | " + RIOdroid.executeCommand("adb start-server"));
		 System.out.println("> Waiting 3 seconds for ADB server start");
		 Timer.delay(3);
		 System.out.println("> Waited 3 seconds, adb devices: ");
		 System.out.println("run adb devices | " + RIOdroid.executeCommand("adb devices"));
		 
        Timer.delay(1);
        System.out.println(RIOadb.ForwardAdb(5801,5801));
        Timer.delay(1);
        System.out.println("START APP: " + RIOdroid.executeCommand("adb shell am start " + ACTIVITY_NAME));
        Timer.delay(1);
	}
	
	public void initSocketClient() {
		SmartDashboard.putBoolean("Socket Connected", false);
		
		URI u;
		try {
			u = new URI(SOCKET_SERVER);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return;
		}
		
		socketClient = new WebSocketClient(u) {

			@Override
			public void onOpen(ServerHandshake handshakedata) {
				System.out.println("Connection open!");
				SmartDashboard.putBoolean("Socket Connected", true);
			}

			@Override
			public void onMessage(String message) {
				System.out.println(System.currentTimeMillis() + "| Message: " + message);
			}
			
			@Override
			public void onMessage(ByteBuffer buffer) {
				long delay = buffer.getLong();
				int dist = buffer.getInt();
				double horiz = buffer.getDouble();
				double vert = buffer.getDouble();
				SmartDashboard.putNumber("vert angle", Math.toDegrees(vert));
				synchronized(this) {
					latestPose.timestamp = System.currentTimeMillis() - PACKET_DELAY_MS - delay;
					latestPose.vertAngle = vert;
					latestPose.horizAngle = horiz;
				}
			}
			
			@Override
			public void onClose(int code, String reason, boolean remote) {
				System.out.println("Connection closed: " + reason);
				SmartDashboard.putBoolean("Socket Connected", false);
			}

			@Override
			public void onError(Exception ex) {
				ex.printStackTrace();
			}
			
		};
		
		socketClient.connect();
	}
	*/
}
