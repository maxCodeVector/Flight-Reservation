package plane;

import util.Tool;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class manageAdmin {

	static ArrayList<Admin> admin = new ArrayList<>();

	public static boolean checkAdmin(String user, String name, String pw, String pw2) {
		int j = 0;
		if (!user.trim().equals("") && !name.trim().equals("") && !pw.trim().equals("")) {
			if (pw.equals(pw2)) {
				j = 1;
			} else
				JOptionPane.showMessageDialog(null, "���벻һ��");
		} else
			JOptionPane.showMessageDialog(null, "��ȷ����дȫ����Ϣ");
		return j == 1;
	}

	// ������Ա�������ļ���������admin��
	public static void initial() {
		FileReader fw;
		try {
			fw = new FileReader(Tool.getDataAdpater().getAdminPath());
			BufferedReader input1 = new BufferedReader(fw);
			String s = null;
			while ((s = input1.readLine()) != null) {
				s = s.replace(" ", "");
				String Id = s.substring(0, s.indexOf("&"));
				String pw = s.substring(s.indexOf("&") + 1, s.indexOf("%"));
				String rn = s.substring(s.indexOf("%") + 1, s.indexOf(";"));
				Admin ad = new Admin(Id, pw, rn);
				admin.add(ad);
			}
			input1.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �ж��Ƿ��¼�ɹ�
	public static void logon(String a, String b) {
		int lg = 0, index = 0;
		initial();
		for (int i = 0; i < admin.size(); i++) {
			if (a.equals(admin.get(i).getUserName())) {
				if (b.equals(admin.get(i).getPassword())) {
					lg = 1;
					index = i;
				} else {
					lg = 2;
				}
			}
		}
		switch (lg) {
		case 1:
			Main.adminF(admin.get(index));
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "�������!!");
			break;
		default:
			JOptionPane.showMessageDialog(null, "�˺Ų�����");
		}
	}

	// ������ӹ���Ա����
	public static void addAdmin(String user, String realName, String pw) {
		FileWriter fw;
		try {
			fw = new FileWriter("resource/Admin.dat", true);
			BufferedWriter bw = new BufferedWriter(fw);
			Admin ad = new Admin(user, pw, realName);
			admin.add(ad);
			int a = admin.size() - 1;
			bw.write(admin.get(a).getUserName() + " & ");
			bw.write(admin.get(a).getPassword() + " % ");
			bw.write(admin.get(a).getRealName() + " ; ");
			bw.newLine();
			bw.close();
			fw.close();
			String s = String.format("��ӳɹ�\n�û���:  %s\n��ʵ����:%s\n", ad.getUserName(), ad.getRealName());
			UIManager.put("OptionPane.messageFont", new Font("����", Font.BOLD, 30));
			UIManager.put("OptionPane.buttonFont", new Font("����", Font.BOLD, 30));
			JOptionPane.showMessageDialog(null, s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ������¹���Ա����
	public static void saveAdmin() {
		FileWriter fw;
		try {
			fw = new FileWriter("resource/Admin.dat");
			fw.write("");
			fw.close();
			fw = new FileWriter("resource/Admin.dat", true);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println(admin.size());
			for (int i = 0; i < admin.size(); i++) {
				bw.write(admin.get(i).getUserName() + " & ");
				bw.write(admin.get(i).getPassword() + " % ");
				bw.write(admin.get(i).getRealName() + " ; ");
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
