package controller;

import bean.Passenger;
import frames.MainFrame;
import util.Tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class managePassenger {

	public static ArrayList<Passenger> passengers = new ArrayList<>();

	public static void initial() {
		FileReader fw;
		try {
			fw = new FileReader(Tool.getDataAdpater().getPassagerPath());
			BufferedReader input1 = new BufferedReader(fw);
			String s = null;
			while ((s = input1.readLine()) != null) {
				s = s.replace(" ", "");
				String userName = s.substring(0, s.indexOf("%"));
				String name = s.substring(s.indexOf("%") + 1, s.indexOf("#"));
				String Id = s.substring(s.indexOf("#") + 1, s.indexOf("&"));
				String TeleNum = s.substring(s.indexOf("&") + 1, s.indexOf("@"));
				String password = s.substring(s.indexOf("@") + 1, s.indexOf("^"));
				String passengerId = s.substring(s.indexOf("^") + 1, s.indexOf(";"));
				Passenger passenger = new Passenger(userName, name, TeleNum, Id, password, passengerId);
				passengers.add(passenger);

			}
			input1.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkPassenger(String user, String name, String teleNum, String identityId, String pw) {
		int j = 0;
		if (!user.trim().equals("") && !name.trim().equals("") && !teleNum.trim().equals("")
				&& !identityId.trim().equals("") && !pw.trim().equals("")) {
			if (teleNum.matches("1\\d{10}")) {
				if (identityId.matches("\\d{17}[a-zA-Z[\\d]]{1}")) {
					j = 1;
				} else
					JOptionPane.showMessageDialog(null, "���֤�����ʽ����");
			} else
				JOptionPane.showMessageDialog(null, "�ֻ������ʽ����");
		} else
			JOptionPane.showMessageDialog(null, "������ȫ������");
		return j == 1 ;
	}

	public static void register(String user, String name, String teleNum, String identityId, String pw) {
		FileWriter fw;
		try {
			fw = new FileWriter("resource/Passenger.dat", true);
			BufferedWriter bw = new BufferedWriter(fw);
			String IdentityId = identityId;
			String TeleNum = teleNum;
			String realName = name;
			String password = pw;
			String userName = user;
			String passengerId = "B" + String.format("%ts", new Date());
			Passenger passenger = new Passenger(userName, realName, TeleNum, IdentityId, password, passengerId);
			passengers.add(passenger);
			int a = passengers.size() - 1;
			bw.write(passengers.get(a).getUserName() + " % ");
			bw.write(passengers.get(a).getRealName() + " # ");
			bw.write(passengers.get(a).getIdentityId() + " & ");
			bw.write(passengers.get(a).getTeleNum() + "@");
			bw.write(passengers.get(a).getPassword() + "^");
			bw.write(passengers.get(a).getPassengerId() + ";");
			bw.newLine();
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "ע��ɹ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ������º�ĳ˿���Ϣ
	public static void savePassenger() {
		FileWriter fw;
		try {
			fw = new FileWriter(Tool.getDataAdpater().getPassagerPath());
			fw.write("");
			fw.close();
			fw = new FileWriter(Tool.getDataAdpater().getPassagerPath(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Passenger pg : passengers) {
				bw.write(pg.getUserName() + " % ");
				bw.write(pg.getRealName() + " # ");
				bw.write(pg.getIdentityId() + " & ");
				bw.write(pg.getTeleNum() + "@");
				bw.write(pg.getPassword() + "^");
				bw.write(pg.getPassengerId() + ";");
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �жϵ�¼�Ƿ�ɹ�
	public static void logon(String a, String b) {
		int lg = 0, index = 0;
		for (int i = 0; i < passengers.size(); i++) {
			if (a.equals(passengers.get(i).getUserName())) {
				if (b.equals(passengers.get(i).getPassword())) {
					lg = 1;
					index = i;
				} else {
					lg = 2;
				}
			}
		}
		switch (lg) {
		case 1:
			MainFrame.passF(passengers.get(index));
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "�������");
			break;
		default:
			JOptionPane.showMessageDialog(null, "�˺Ų�����");
		}
	}

}
