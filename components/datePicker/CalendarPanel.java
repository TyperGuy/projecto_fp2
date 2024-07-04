package components.datePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarPanel extends JPanel {
    public int currentMonth;
    public int currentYear;
    private JLabel monthLabel;
    private JTable calendarTable;
    private DefaultTableModel tableModel;

    public CalendarPanel() {
        this.setLayout(new BorderLayout());

        // Painel superior com controles
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMonth(-1);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMonth(1);
            }
        });

        monthLabel = new JLabel("", JLabel.CENTER);

        topPanel.add(prevButton, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        // Tabela de calendário
        String[] columnNames = { "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb" };
        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desativa a edição das células
            }
        };

        calendarTable = new JTable(tableModel);
        calendarTable.setRowHeight(40);

        // Permitir a seleção de uma única célula
        calendarTable.setCellSelectionEnabled(true);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(new JScrollPane(calendarTable), BorderLayout.CENTER);

        // Inicializar com o mês e ano atuais
        Calendar cal = Calendar.getInstance();
        currentMonth = cal.get(Calendar.MONTH);
        currentYear = cal.get(Calendar.YEAR);

        updateCalendar();
    }

    private void changeMonth(int amount) {
        currentMonth += amount;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        } else if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        updateCalendar();
    }

    private void updateCalendar() {
        Calendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        monthLabel.setText(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, getLocale()) + " " + currentYear);

        tableModel.setRowCount(0);
        tableModel.setRowCount(6);

        int day = 1;
        for (int i = startDay - 1; i < 7; i++) {
            tableModel.setValueAt(day, 0, i);
            day++;
        }

        int row = 1;
        while (day <= numberOfDays) {
            for (int i = 0; i < 7; i++) {
                if (day > numberOfDays) {
                    break;
                }
                tableModel.setValueAt(day, row, i);
                day++;
            }
            row++;
        }
    }

    public JTable getCalendarTable() {
        return calendarTable;
    }
}
