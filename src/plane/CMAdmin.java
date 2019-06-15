package plane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//����Ա���Լ���Ϣ�ĸ��º����������Ĵ���(Creat, Manage)
public class CMAdmin {
	// ������ӹ���Ա����
	public static void creatA() {
		JFrame my = new JFrame();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(5, 2, 100, 40));
		JLabel j1 = new JLabel("�û���", SwingConstants.CENTER);
		JLabel j2 = new JLabel("��ʵ����", SwingConstants.CENTER);
		JLabel j3 = new JLabel("����", SwingConstants.CENTER);
		JLabel j4 = new JLabel("ȷ������", SwingConstants.CENTER);
		JButton b1 = new JButton("ȷ�����");
		JTextField t1 = new JTextField("");
		JTextField t2 = new JTextField("");
		JPasswordField t3 = new JPasswordField("");
		JPasswordField t4 = new JPasswordField("");
		j1.setFont(new Font("����", Font.BOLD, 30));
		j2.setFont(new Font("����", Font.BOLD, 30));
		j3.setFont(new Font("����", Font.BOLD, 30));
		j4.setFont(new Font("����", Font.BOLD, 30));
		b1.setFont(new Font("����", Font.BOLD, 30));
		t1.setFont(new Font("����", Font.BOLD, 30));
		t2.setFont(new Font("����", Font.BOLD, 30));
		t3.setFont(new Font("����", Font.BOLD, 30));
		t4.setFont(new Font("����", Font.BOLD, 30));
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
		my.setTitle("��ӹ���Ա");
		my.setVisible(true);
		my.setBounds(600, 300, 600, 450);
	}

	// �����޸Ĺ���Ա���ϴ���
	public static void userManage(Admin ad) {
		JFrame my = new JFrame();
		Container con = my.getContentPane();
		con.setLayout(new GridLayout(6, 2, 100, 40));
		JLabel j1 = new JLabel("�û���", SwingConstants.CENTER);
		JLabel j2 = new JLabel("��ʵ����", SwingConstants.CENTER);
		JLabel j3 = new JLabel("��֤����", SwingConstants.CENTER);
		JLabel j4 = new JLabel("������", SwingConstants.CENTER);
		JLabel j5 = new JLabel("ȷ������", SwingConstants.CENTER);
		JButton b1 = new JButton("ȷ���޸�");
		JLabel t1 = new JLabel(ad.getUserName());
		JTextField t2 = new JTextField(ad.getRealName());
		JPasswordField t3 = new JPasswordField("");
		JPasswordField t4 = new JPasswordField("");
		JPasswordField t5 = new JPasswordField("");
		j1.setFont(new Font("����", Font.BOLD, 30));
		j2.setFont(new Font("����", Font.BOLD, 30));
		j3.setFont(new Font("����", Font.BOLD, 30));
		j4.setFont(new Font("����", Font.BOLD, 30));
		j5.setFont(new Font("����", Font.BOLD, 30));
		b1.setFont(new Font("����", Font.BOLD, 30));
		t1.setFont(new Font("����", Font.BOLD, 30));
		t2.setFont(new Font("����", Font.BOLD, 30));
		t3.setFont(new Font("����", Font.BOLD, 30));
		t4.setFont(new Font("����", Font.BOLD, 30));
		t5.setFont(new Font("����", Font.BOLD, 30));

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (new String(t3.getPassword()).equals(ad.getPassword())) {
					boolean right = manageAdmin.checkAdmin(ad.getUserName(), t2.getText(), new String(t4.getPassword()),new String(t5.getPassword()));
					if (right) {
						ad.setRealName(t2.getText());
						manageAdmin.saveAdmin();
						String s = String.format("�޸ĳɹ�\n�û���:  %s\n��ʵ����:%s\n", ad.getUserName(), ad.getRealName());
						JOptionPane.showMessageDialog(null, s);
						t2.setText("");
						t3.setText("");
						t4.setText("");
						t5.setText("");
					}
				} else
					JOptionPane.showMessageDialog(null, "��֤ʧ�ܣ��������");
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
