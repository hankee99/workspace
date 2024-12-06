package kh.java.run;

import kh.java.func.BaseballServer;
import kh.java.func.DoServer;
import kh.java.func.FileServer;
import kh.java.func.TcpServer;
import kh.java.func.UdpServer;

import java.util.*;
public class Run {

	public static void main(String[] args) {
		BaseballServer bs = new BaseballServer();
		bs.server();
	}

}
