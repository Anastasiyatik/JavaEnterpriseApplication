package holidaybookingdesktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class SubmitNewRequest extends JFrame {

	private JPanel contentPane;
	private String email;
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { SubmitNewRequest frame = new
	 * SubmitNewRequest(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public SubmitNewRequest(String email) {
		this.email = email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Submit New Request");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(63, 11, 321, 23);
		contentPane.add(lblNewLabel);

		//DateFormat format = new SimpleDateFormat("dd/mm/YYYY");
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(175, 88, 125, 20);
		contentPane.add(formattedTextField);

		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(175, 119, 125, 20);
		contentPane.add(formattedTextField_1);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String failedMsg = "Request failed! Please try again.";
				try {
					final String post_params = "{\"userName\":\"" + email + "\",\"fromDate\":\""
							+ formattedTextField.getText() + "\",\"toDate\":\"" + formattedTextField_1.getText()
							+ "\"}";
					URL obj = new URL("http://localhost:8080/HolidayBookingAppWebApplication/rest/submitnewrequest");
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
		btnNewButton.setBounds(197, 161, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("From date");
		lblNewLabel_2.setBounds(96, 91, 69, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("To date");
		lblNewLabel_3.setBounds(96, 122, 56, 14);
		contentPane.add(lblNewLabel_3);
	}
}
