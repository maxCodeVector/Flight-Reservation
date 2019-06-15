package plane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//管理员对自己信息的更新和其他操作的窗口(Creat, Manage)
public class CMAdmin {
	// 创建添加管理员窗口
	public static void creatA() {
		JFrame my = new JFrame();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(5, 2, 100, 40));
		JLabel j1 = new JLabel("用户名", SwingConstants.CENTER);
		JLabel j2 = new JLabel("真实姓名", SwingConstants.CENTER);
		JLabel j3 = new JLabel("密码", SwingConstants.CENTER);
		JLabel j4 = new JLabel("确认密码", SwingConstants.CENTER);
		JButton b1 = new JButton("确认添加");
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JPasswordField t3 = new JPasswordField("");
		JPasswordField t4 = new JPasswordField("");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		j2.setFont(new Font("宋体", Font.BOLD, 30));
		j3.setFont(new Font("宋体", Font.BOLD, 30));
		j4.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		t1.setFont(new Font("宋体", Font.BOLD, 30));
		t2.setFont(new Font("宋体", Font.BOLD, 30));
		t3.setFont(new Font("宋体", Font.BOLD, 30));
		t4.setFont(new Font("宋体", Font.BOLD, 30));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean right = manageAdmin.checkAdmin(t1.getText(), t2.getText(), new String(t3.getPassword()),new String(t4.getPassword()));
				if (right) {
					manageAdmin.addAdmin(t1.getText(), t2.getText(), new String(t3.getPassword()));
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
				}
			}
		});
		con.add(j1);
		con.add(t1);
		con.add(j2);
		con.add(t2);
		con.add(j3);
		con.add(t3);
		con.add(j4);
		con.add(t4);
		con.add(b1);
		my.setResizable(false);
		my.setTitle("添加管理员");
		my.setVisible(true);
		my.setBounds(600, 300, 600, 450);
	}

	// 创建修改管理员资料窗口
	public static void userManage(Admin ad) {
		JFrame my = new JFrame();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(6, 2, 100, 40));
		JLabel j1 = new JLabel("用户名", SwingConstants.CENTER);
		JLabel j2 = new JLabel("真实姓名", SwingConstants.CENTER);
		JLabel j3 = new JLabel("验证密码", SwingConstants.CENTER);
		JLabel j4 = new JLabel("新密码", SwingConstants.CENTER);
		JLabel j5 = new JLabel("确认密码", SwingConstants.CENTER);
		JButton b1 = new JButton("确认修改");
		JLabel t1 = new JLabel(ad.getUserName());
		JTextField t2 = new JTextField(ad.getRealName());
		JPasswordField t3 = new JPasswordField("");
		JPasswordField t4 = new JPasswordField("");
		JPasswordField t5 = new JPasswordField("");
		j1.setFont(new Font("宋体", Font.BOLD, 30));
		j2.setFont(new Font("宋体", Font.BOLD, 30));
		j3.setFont(new Font("宋体", Font.BOLD, 30));
		j4.setFont(new Font("宋体", Font.BOLD, 30));
		j5.setFont(new Font("宋体", Font.BOLD, 30));
		b1.setFont(new Font("宋体", Font.BOLD, 30));
		t1.setFont(new Font("宋体", Font.BOLD, 30));
		t2.setFont(new Font("宋体", Font.BOLD, 30));
		t3.setFont(new Font("宋体", Font.BOLD, 30));
		t4.setFont(new Font("宋体", Font.BOLD, 30));
		t5.setFont(new Font("宋体", Font.BOLD, 30));

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(t3.getPassword()).equals(ad.getPassword())) {
					boolean right = manageAdmin.checkAdmin(ad.getUserName(), t2.getText(), new String(t4.getPassword()),new String(t5.getPassword()));
					if (right) {
						ad.setRealName(t2.getText());
						manageAdmin.saveAdmin();
						String s = String.format("修改成功\n用户名:  %s\n真实姓名:%s\n", ad.getUserName(), ad.getRealName());
						JOptionPane.showMessageDialog(null, s);
						t2.setText("");
						t3.setText("");
						t4.setText("");
						t5.setText("");
					}
				} else
					JOptionPane.showMessageDialog(null, "验证失败，密码错误");
			}
		});
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
		con.add(b1);
		my.setResizable(false);
		my.setVisible(true);
		my.setBounds(600, 300, 600, 500);
	}

}
