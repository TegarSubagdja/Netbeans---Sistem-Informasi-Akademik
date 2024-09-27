package View.Alert;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.Font;

public class DarkAlert {
    private static JTextField textField;

    public static boolean showConfirmation(String message, String initialText) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        textField = new JTextField(initialText);
        textField.setBackground(new Color(50, 50, 50));
        textField.setForeground(new Color(200, 200, 200));
        textField.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JLabel messageLabel = new JLabel(message);
        messageLabel.setForeground(new Color(23, 23, 26)); 
        messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

        Object[] components = {messageLabel, textField};

        int option = JOptionPane.showOptionDialog(null, components, "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Iya", "Tidak"}, null);
        return option == JOptionPane.YES_OPTION;
    }

    public static String getTextFieldText() {
        return textField.getText();
    }
}
