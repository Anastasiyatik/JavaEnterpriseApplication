package holidaybookingdesktop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class DesktopAppLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesktopAppLogin frame = new DesktopAppLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DesktopAppLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 88, 146, 20);
		contentPane.add(passwordField);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(111, 11, 229, 14);
		contentPane.add(lblNewLabel);
		textField = new JTextField();
		textField.setBounds(141, 57, 146, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setBounds(169, 119, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBounds(68, 58, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(68, 91, 73, 14);
		contentPane.add(lblNewLabel_2);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String failedMsg = "Login failed! Please try again.";
				try {
					final String post_params = "{\"email\":\"" + textField.getText() + "\",\"password\":\""
							+ String.copyValueOf(passwordField.getPassword()) + "\"}";
					URL obj = new URL("http://localhost:8080/HolidayBookingAppWebApplication/rest/login");
					HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
					postConnection.setRequestMethod("POST");
					postConnection.setRequestProperty("Content-Type", "application/json");
					postConnection.setDoOutput(true);
					OutputStream os = postConnection.getOutputStream();
					os.write(post_params.getBytes());
					os.flush();
					os.close();
					int responseCode = postConnection.getResponseCode();

					if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) { // success
						BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();
						JSONParser parser = new JSONParser();
						String respJson = response.toString();
						if (respJson != null && !respJson.isEmpty()) {
							JSONObject jsonObject = (JSONObject) parser.parse(respJson);
							String result = (String) jsonObject.get("result");
							lblNewLabel.setText(result);
							if (result.startsWith(textField.getText())) {
								SubmitNewRequest submitNewRequest = new SubmitNewRequest(textField.getText());
								submitNewRequest.setVisible(true);
								dispose();
							}
						} else {
							lblNewLabel.setText(failedMsg);
						}
					} else {
						lblNewLabel.setText(failedMsg);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					lblNewLabel.setText(failedMsg);
				}
			}
		});
	}
}
