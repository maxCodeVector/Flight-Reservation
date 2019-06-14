package plane;

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
				JOptionPane.showMessageDialog(null, "密码不一致");
		} else
			JOptionPane.showMessageDialog(null, "请确认填写全部信息");
		return j == 1;
	}

	// 将管理员名单从文件导入数组admin中
	public static void initial() {
		FileReader fw;
		try {
			fw = new FileReader("Admin.dat");
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

	// 判断是否登录成功
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
			JOptionPane.showMessageDialog(null, "密码错误!!");
			break;
		default:
			JOptionPane.showMessageDialog(null, "账号不存在");
		}
	}

	// 设置添加管理员方法
	public static void addAdmin(String user, String realName, String pw) {
		FileWriter fw;
		try {
			fw = new FileWriter("Admin.dat", true);
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
			String s = String.format("添加成功\n用户名:  %s\n真实姓名:%s\n", ad.getUserName(), ad.getRealName());
			UIManager.put("OptionPane.messageFont", new Font("宋体", Font.BOLD, 30));
			UIManager.put("OptionPane.buttonFont", new Font("宋体", Font.BOLD, 30));
			JOptionPane.showMessageDialog(null, s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 保存更新管理员数据
	public static void saveAdmin() {
		FileWriter fw;
		try {
			fw = new FileWriter("Admin.dat");
			fw.write("");
			fw.close();
			fw = new FileWriter("Admin.dat", true);
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
