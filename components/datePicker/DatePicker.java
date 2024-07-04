package components.datePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePicker extends JPanel {
    private JTextField dateField;
    private JButton calendarButton;
    private JDialog calendarDialog;
    private CalendarPanel calendarPanel;
    private SimpleDateFormat dateFormat;

    public DatePicker() {
        this.setLayout(new BorderLayout());

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dateField = new JTextField();
        calendarButton = new JButton("...");

        calendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCalendarDialog();
            }
        });

        this.add(dateField, BorderLayout.CENTER);
        this.add(calendarButton, BorderLayout.EAST);

        calendarPanel = new CalendarPanel();
        calendarDialog = new JDialog();
        calendarDialog.setUndecorated(true);
        calendarDialog.setLayout(new BorderLayout());
        calendarDialog.add(calendarPanel, BorderLayout.CENTER);
        calendarDialog.pack();

        calendarPanel.getCalendarTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = calendarPanel.getCalendarTable().getSelectedRow();
                int col = calendarPanel.getCalendarTable().getSelectedColumn();
                if (row >= 0 && col >= 0) {
                    Object value = calendarPanel.getCalendarTable().getValueAt(row, col);
                    if (value != null) {
                        int day = (int) value;
                        Calendar cal = new GregorianCalendar(calendarPanel.currentYear, calendarPanel.currentMonth,
                                day);
                        Date selectedDate = cal.getTime();
                        dateField.setText(dateFormat.format(selectedDate));
                        calendarDialog.setVisible(false);
                    }
                }
            }
        });
    }

    private void showCalendarDialog() {
        Point location = dateField.getLocationOnScreen();
        calendarDialog.setLocation(location.x, location.y + dateField.getHeight());
        calendarDialog.setVisible(true);
    }

    public Date getSelectedDate() {
        try {
            return dateFormat.parse(dateField.getText());
        } catch (Exception e) {
            return null;
        }
    }

    public void setSelectedDate(Date date) {
        if (date != null) {
            dateField.setText(dateFormat.format(date));
        } else {
            dateField.setText("");
        }
    }
}
