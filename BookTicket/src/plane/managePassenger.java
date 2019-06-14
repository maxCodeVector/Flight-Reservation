package plane;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class managePassenger {

	// 乘客全部信息的passenger型的数组arraylist;
	static ArrayList<Passenger> array = new ArrayList<>();

	// 将账号密码导入数组array中
	public static void initial() {
		FileReader fw;
		try {
			fw = new FileReader("Passenger.data");
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
				array.add(passenger);

			}
			input1.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 检查乘客注册信息的格式
	public static boolean checkPassenger(String user, String name, String teleNum, String identityId, String pw) {
		int j = 0;
		if (!user.trim().equals("") && !name.trim().equals("") && !teleNum.trim().equals("")
				&& !identityId.trim().equals("") && !pw.trim().equals("")) {
			if (teleNum.matches("1\\d{10}")) {
				if (identityId.matches("\\d{17}[a-zA-Z[\\d]]{1}")) {
					j = 1;
				} else
					JOptionPane.showMessageDialog(null, "身份证号码格式错误");
			} else
				JOptionPane.showMessageDialog(null, "手机号码格式错误");
		} else
			JOptionPane.showMessageDialog(null, "请填完全部资料");
		return j == 1 ;
	}

	// 注册方法
	public static void zhuce(String user, String name, String teleNum, String identityId, String pw) {
		FileWriter fw;
		try {
			fw = new FileWriter("Passenger.data", true);
			BufferedWriter bw = new BufferedWriter(fw);
			String IdentityId = identityId;
			String TeleNum = teleNum;
			String realName = name;
			String password = pw;
			String userName = user;
			String passengerId = "B" + String.format("%ts", new Date());
			Passenger passenger = new Passenger(userName, realName, TeleNum, IdentityId, password, passengerId);
			array.add(passenger);
			int a = array.size() - 1;
			bw.write(array.get(a).getUserName() + " % ");
			bw.write(array.get(a).getRealName() + " # ");
			bw.write(array.get(a).getIdentityId() + " & ");
			bw.write(array.get(a).getTeleNum() + "@");
			bw.write(array.get(a).getPassword() + "^");
			bw.write(array.get(a).getPassengerId() + ";");
			bw.newLine();
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "注册成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 保存更新后的乘客信息
	public static void savePassenger() {
		FileWriter fw;
		try {
			fw = new FileWriter("Passenger.data");
			fw.write("");
			fw.close();
			fw = new FileWriter("Passenger.data", true);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Passenger pg : array) {
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

	// 判断登录是否成功
	public static void logon(String a, String b) {
		int lg = 0, index = 0;
		for (int i = 0; i < array.size(); i++) {
			if (a.equals(array.get(i).getUserName())) {
				if (b.equals(array.get(i).getPassword())) {
					lg = 1;
					index = i;
				} else {
					lg = 2;
				}
			}
		}
		switch (lg) {
		case 1:
			Main.passF(array.get(index));
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "密码错误");
			break;
		default:
			JOptionPane.showMessageDialog(null, "账号不存在");
		}
	}

	// 创建注册窗体
	public static void zhuce() {
		JFrame my = new JFrame("乘客注册");
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(7, 2, 0, 50));
		JPanel p1 = new JPanel(new GridLayout(1, 2, 100, 50));
		JLabel j1 = new JLabel("用户名", SwingConstants.CENTER);
		JLabel j2 = new JLabel("真实姓名", SwingConstants.CENTER);
		JLabel j3 = new JLabel("手机号码", SwingConstants.CENTER);
		JLabel j4 = new JLabel("身份证号码", SwingConstants.CENTER);
		JLabel j5 = new JLabel("密码", SwingConstants.CENTER);
		JLabel j6 = new JLabel("确认密码", SwingConstants.CENTER);
		JButton b1 = new JButton("提交");
		JButton b2 = new JButton("返回");
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JTextField t3 = new JTextField("");
		JTextField t4 = new JTextField("");
		JPasswordField t5 = new JPasswordField("");
		JPasswordField t6 = new JPasswordField("");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		j2.setFont(new Font("宋体", Font.BOLD, 30));
		j3.setFont(new Font("宋体", Font.BOLD, 30));
		j4.setFont(new Font("宋体", Font.BOLD, 30));
		j5.setFont(new Font("宋体", Font.BOLD, 30));
		j6.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		b2.setFont(new Font("宋体", Font.BOLD, 30));
		t1.setFont(new Font("宋体", Font.BOLD, 30));
		t2.setFont(new Font("宋体", Font.BOLD, 30));
		t3.setFont(new Font("宋体", Font.BOLD, 30));
		t4.setFont(new Font("宋体", Font.BOLD, 30));
		t5.setFont(new Font("宋体", Font.BOLD, 30));
		t6.setFont(new Font("宋体", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(t5.getPassword()).equals(new String(t6.getPassword()))) {
					if (checkPassenger(t1.getText(), t2.getText(), t3.getText(), t4.getText(),new String(t5.getPassword()))) {
						managePassenger.zhuce(t1.getText(), t2.getText(), t3.getText(), t4.getText(), new String(t5.getPassword()));
						t1.setText("");
						t2.setText("");
						t3.setText("");
						t4.setText("");
						t5.setText("");
						t6.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "密码不一致");
					t5.setText("");
					t6.setText("");
				}
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my.setVisible(false);
			}
		});
		p1.add(b1);
		p1.add(b2);
		con.add(j1);
		con.add(t1);
		con.add(j2);
		con.add(t2);
		con.add(j3);
		con.add(t3);
		con.add(j4);
		con.add(t4);
		con.add(j5);
		con.add(t5);
		con.add(j6);
		con.add(t6);
		con.add(p1);
		my.setResizable(false);
		my.setTitle("欢迎进入机票预订系统");
		my.setVisible(true);
		my.setBounds(400, 100, 650, 700);
	}

}
