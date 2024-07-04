package components.datePicker;

import javax.swing.*;

public class DatePickerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("DatePicker");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);

                DatePicker datePicker = new DatePicker();
                frame.add(datePicker);

                frame.setVisible(true);
            }
        });
    }
}
