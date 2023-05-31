package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelMenuReports extends JPanel {

    private JLabel titleMenuReports;

    private Button existingProcesses, partitions,NoExecutionList, readyReport, dispatchedReport, executionReport, expirationReport, finishedReport, back, space, coondensations, noExecutionListSort, finishedReportSort;

    public PanelMenuReports(ActionListener listener){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#4a4e69"));
        this.setPreferredSize(new Dimension(350,80));
        this.setMaximumSize(new Dimension(350,80));
        this.setMinimumSize(new Dimension(350,80));
        this.initComponents(listener);
        this.setVisible(true);
    }

    private void initComponents(ActionListener listener) {
        titleMenuReports = new JLabel("Reportes");
        titleMenuReports.setForeground(Color.WHITE);
        titleMenuReports.setFont(ConstantsGUI.FONT_MENU_TITLE);
        Utilities.addComponent(this, this.titleMenuReports, 0, 1);

        existingProcesses = new Button("Procesos actuales");
        existingProcesses.addActionListener(listener);
        existingProcesses.setActionCommand("Actuales");
        Utilities.addComponent(this, this.existingProcesses, 0, 2);

        readyReport = new Button("Listos");
        readyReport.addActionListener(listener);
        readyReport.setActionCommand("Listos");
        Utilities.addComponent(this, this.readyReport, 0, 4);

        dispatchedReport = new Button("Despachados");
        dispatchedReport.addActionListener(listener);
        dispatchedReport.setActionCommand("Despachados");
        Utilities.addComponent(this, this.dispatchedReport, 0, 5);

        executionReport = new Button("Ejecución");
        executionReport.addActionListener(listener);
        executionReport.setActionCommand("Ejecucion");
        Utilities.addComponent(this, this.executionReport, 0, 6);

        expirationReport = new Button("Expirados");
        expirationReport.addActionListener(listener);
        expirationReport.setActionCommand("Expirados");
        Utilities.addComponent(this, this.expirationReport, 0, 7);

        NoExecutionList = new Button("No Ejecutados");
        NoExecutionList.addActionListener(listener);
        NoExecutionList.setActionCommand("NoEjecutados");
        Utilities.addComponent(this, this.NoExecutionList, 0, 8);

        noExecutionListSort = new Button("No Ejecutados Part");
        noExecutionListSort.addActionListener(listener);
        noExecutionListSort.setActionCommand("NoEjecutadosSort");
        Utilities.addComponent(this, this.noExecutionListSort, 0, 9);

        finishedReportSort = new Button("Fin. Partición");
        finishedReportSort.addActionListener(listener);
        finishedReportSort.setActionCommand("FinalizadosPart");
        Utilities.addComponent(this, this.finishedReportSort, 0, 10);

        finishedReport = new Button("Finalizados");
        finishedReport.addActionListener(listener);
        finishedReport.setActionCommand("Finalizados");
        Utilities.addComponent(this, this.finishedReport, 0, 15);

        partitions = new Button("Particiones");
        partitions.addActionListener(listener);
        partitions.setActionCommand("Particiones");
        Utilities.addComponent(this, this.partitions, 0, 16);

        space = new Button("Huecos En Memoria");
        space.addActionListener(listener);
        space.setActionCommand("Huecos");
        Utilities.addComponent(this, this.space, 0, 17);

        coondensations = new Button("Condensaciones");
        coondensations.addActionListener(listener);
        coondensations.setActionCommand("Condensaciones");
        Utilities.addComponent(this, this.coondensations, 0, 18);

        back = new Button("Atrás");
        back.addActionListener(listener);
        back.setActionCommand("Atras");
        Utilities.addComponent(this, this.back, 0, 19);
    }
}
