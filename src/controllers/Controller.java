package controllers;

import models.PartitionReport;
import models.Process;
import models.ProcessManager;
import views.Utilities;
import views.ViewManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.util.ArrayList;

public class Controller implements ActionListener, KeyListener {

    private ViewManager viewManager;
    private ProcessManager processManager;


    public Controller(){
        this.viewManager = new ViewManager(this, this);
        this.processManager = new ProcessManager();
        processManager.initSimulation();
        processManager.addCondensations();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "CrearProceso":
                this.showCreateProcessDialog();
                break;
            case "AñadirProceso":
                this.confirmAddProcess();
                break;
            case "CancelarAñadirProceso":
                this.cancelAddProcess();
                break;
            case "ModificarProceso":
                this.showModifyProcessDialog();
                break;
            case "ConfirmarModificacionProceso":
                this.confirmModifyProcess();
                break;
            case "EliminarProceso":
                this.deleteProcess();
                break;
            case "Reportes":
                this.changeToReportMenu();
                break;
            case "Enviar":
                this.initSimulation();
                break;
            case "Actuales":
                this.setValuesToCurrentProcess();
                break;
            case "Listos":
                this.setValuesToReadyReport();
                break;
            case "Despachados":
                this.setValuesToDispatchReport();
                break;
            case "Ejecucion":
                this.setValuesToExecReport();
                break;
            case "Expirados":
                this.setValuesToExepReport();
                break;
            case "NoEjecutados":
                this.setValuesToNoExepReport();
                break;
            case "Finalizados":
                this.setValuesToFinishedReport();
                break;
            case "FinalizadosPart":
                this.setValuesToFinishedReportSort();
                break;
            case "NoEjecutadosSort":
                this.setValuesToNoExecReportSort();
                break;
            case "Huecos":
                this.setValuesToSpaceReport();
                break;
            case "Condensaciones":
                this.setValuesToCondensationsReport();
                break;
            case "Particiones":
                this.setValuesToPartitionsReport();
                break;
            case "ManualUsuario":
                this.openManual();
                break;
            case "Atras":
                this.changeToMenu();
                break;
            case "Salir":
                System.exit(0);
                break;
        }
    }

    private void initSimulation(){
        if(this.processManager.getInQueue().size() == 0){
            Utilities.showErrorDialog("Debe ingresar al menos un proceso para iniciar la simulación");
        } else{
            int response = Utilities.showConfirmationWarning();
            if(response == 0) {
                processManager.initSimulation();
                processManager.addCondensations();
                Utilities.showDoneCPUProcess();
                processManager.cleanQueueList();
                processManager.copyToCurrentProcess();
                this.cleanMainTableProcess();
                this.loadReportList();
            }
        }
    }

    private ArrayList<PartitionReport> sortExpReport(){
        ArrayList<PartitionReport> list = new ArrayList<>();
        for (int j = 0; j < processManager.getPartitions().size(); j++) {
            for (int i = 0; i < processManager.getExpirationList().size(); i++) {
                if (processManager.getExpirationList().get(i).getPartitionName().equals(processManager.getPartitions().get(j).getName())) {
                    list.add(processManager.getExpirationList().get(i));
                }
            }
        }
        return list;
    }

    private ArrayList<PartitionReport> sortDispReport(){
        ArrayList<PartitionReport> list = new ArrayList<>();
        for (int j = 0; j < processManager.getPartitions().size(); j++) {
            for (int i = 0; i < processManager.getDispatchList().size(); i++) {
                if (processManager.getDispatchList().get(i).getPartitionName().equals(processManager.getPartitions().get(j).getName())) {
                    list.add(processManager.getDispatchList().get(i));
                }
            }
        }
        return list;
    }

    private ArrayList<PartitionReport> sortExecReport(){
        ArrayList<PartitionReport> list = new ArrayList<>();
        for (int j = 0; j < processManager.getPartitions().size(); j++) {
            for (int i = 0; i < processManager.getExecutionList().size(); i++) {
                if (processManager.getExecutionList().get(i).getPartitionName().equals(processManager.getPartitions().get(j).getName())) {
                    list.add(processManager.getExecutionList().get(i));
                }
            }
        }
        return list;
    }


    private ArrayList<PartitionReport> sortNoExecReport(){
        ArrayList<PartitionReport> list = new ArrayList<>();
        for (int j = 0; j < processManager.getPartitions().size(); j++) {
            for (int i = 0; i < processManager.getNoExecutionList().size(); i++) {
                if (processManager.getNoExecutionList().get(i).getPartitionName().equals(processManager.getPartitions().get(j).getName())) {
                    list.add(processManager.getNoExecutionList().get(i));
                }
            }
        }
        return list;
    }

    private ArrayList<PartitionReport> sotFinishedReport(){
        ArrayList<PartitionReport> list = new ArrayList<>();
        for (int j = 0; j < processManager.getPartitions().size(); j++) {
            for (int i = 0; i < processManager.getFinishedList().size(); i++) {
                if (processManager.getFinishedList().get(i).getPartitionName().equals(processManager.getPartitions().get(j).getName())) {
                    list.add(processManager.getFinishedList().get(i));
                }
            }
        }
        return list;
    }


    private void loadReportList(){
        viewManager.setCurrentList(processManager.getProcessListAsMatrixObject(processManager.getCurrentList()));
        viewManager.setInQueueList(processManager.getProcessListAsMatrixObject(processManager.getInQueue()));
        viewManager.setReadyList(processManager.getProcessListAsMatrixObject(processManager.getReadyList()));
        viewManager.setDispatchList(processManager.getProcessListAsMatrixReportObject(this.sortDispReport()));
        viewManager.setExecutionList(processManager.getProcessListAsMatrixReportObject(this.sortExecReport()));
        viewManager.setExpirationList(processManager.getProcessListAsMatrixReportObject(this.sortExpReport()));
        viewManager.setNoExecutionList(processManager.getProcessListAsMatrixReportObject(processManager.getNoExecutionList()));
        viewManager.setNoExecutionListSort(processManager.getProcessListAsMatrixReportObject(this.sortNoExecReport()));
        viewManager.setFinishedList(processManager.getProcessListAsMatrixReportObject(processManager.getFinishedList()));
        viewManager.setFinishedListSort(processManager.getProcessListAsMatrixReportObject(this.sotFinishedReport()));
        viewManager.setSpaceList(processManager.getProcessListAsMatrixReportObject(processManager.getSpaceList()));
        viewManager.setCondensationList(processManager.getProcessListAsMatrixReportCon(processManager.getCondensations()));
        viewManager.setPartitions(processManager.getProcessListAsMatrixReportPart(processManager.getPartitions()));
    }

    private void showCreateProcessDialog(){
        this.viewManager.showCreateProcessDialog();
    }

    private void confirmAddProcess() {
        String processName = this.viewManager.getProcessName().trim();
        BigInteger timeProcess = this.viewManager.getProcessTime();
        BigInteger sizeProcess = this.viewManager.getProcessSize();

        if (processName.trim().isEmpty()) {
            Utilities.showErrorDialog("El nombre del proceso está vacío, ingrese algún valor");
        } else if (this.processManager.isAlreadyProcessName(processName)) {
            Utilities.showErrorDialog("El nombre del proceso ya existe, ingrese otro nombre");
            this.viewManager.cleanAllFields();
        } else if (timeProcess.toString().equals("-1")) {
            Utilities.showErrorDialog("El tiempo del proceso está vacío, ingrese un valor numérico entero");
        } else if (sizeProcess.toString().equals("-1")) {
            Utilities.showErrorDialog("El tamaño del proceso está vacío, ingrese un valor numérico entero");
        } else {
            Process newProcess = new Process(processName, timeProcess, sizeProcess);
            this.processManager.addToInQueue(newProcess);
            this.viewManager.setValuesToTable(this.processManager.getProcessListAsMatrixObject(this.processManager.getInQueue()), "Procesos Existentes");
            this.viewManager.hideCreateAndModifyProcessDialog();
        }
    }

    private void cancelAddProcess(){
        this.viewManager.hideCreateAndModifyProcessDialog();
    }

    private void showModifyProcessDialog(){
        if(this.viewManager.getIndexDataInTable() == -1){
            Utilities.showErrorDialog("Debe seleccionar un proceso");
        }
        else {
            Process processToModify = this.processManager.getProcessInQueue(this.viewManager.getIndexDataInTable());
            this.viewManager.setProcessName(processToModify.getName());
            this.viewManager.setProcessTime(processToModify.getTime());
            this.viewManager.setProcessSize(processToModify.getSize());
            this.viewManager.showModifyProcessDialog();
        }
    }


    private void confirmModifyProcess(){
        Process processToModify = this.processManager.getProcessInQueue(this.viewManager.getIndexDataInTable());
        String modifyNameProcess = this.viewManager.getProcessName().trim();

        if(modifyNameProcess.trim().equals("")){
            Utilities.showErrorDialog("El nombre del proceso está vacío, ingrese algún valor");
        }
        else if(!processToModify.getName().equals(modifyNameProcess)
                && this.processManager.isAlreadyProcessName(modifyNameProcess)){
            Utilities.showErrorDialog("Ya existe un proceso con este nombre");
        }
        else if(this.viewManager.getProcessTime().toString().trim().equals("-1")){
            Utilities.showErrorDialog("El tiempo del proceso está vacío, ingrese un valor numérico entero");
        }
        else if(this.viewManager.getProcessSize().toString().trim().equals("-1")){
            Utilities.showErrorDialog("El tamaño del proceso está vacío, ingrese un valor numérico entero");
        }
        else {
            Process newProcess = new Process(this.viewManager.getProcessName(), this.viewManager.getProcessTime(), this.viewManager.getProcessSize());
            this.processManager.updateProcessInQueue(newProcess, this.viewManager.getIndexDataInTable());
            this.viewManager.hideCreateAndModifyProcessDialog();
            this.viewManager.setValuesToTable(this.processManager.getProcessListAsMatrixObject(this.processManager.getInQueue()), "Procesos Existentes");
        }
    }

    private void deleteProcess(){
        if(this.viewManager.getIndexDataInTable() == -1){
            Utilities.showErrorDialog("Debe seleccionar un proceso");
        }
        else {
            int confirmation = Utilities.showConfirmationWarning();
            if(confirmation == 0){
                this.processManager.deleteProcessInQueue(this.viewManager.getIndexDataInTable());
                this.viewManager.setValuesToTable(this.processManager.getProcessListAsMatrixObject(this.processManager.getInQueue()), "Procesos Existentes");
            }

        }

    }

    private void changeToReportMenu(){
        if(this.viewManager.getInQueueSize() != 0){
            this.viewManager.changeToReportMenu();
            this.viewManager.setValuesToTable(this.processManager.getProcessListAsMatrixObject(this.processManager.getInQueue()), "Procesos Existentes");
        }
        else {
            Utilities.showErrorDialog("Debe haber realizado alguna simulación para usar esta opción");
        }
    }

    private void openManual(){
        try{
            java.lang.Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+"C:\\Users\\julie\\OneDrive\\Escritorio\\2023 I\\SISTEMAS OPERATVOS\\SOFTWARE\\SO-SUS-SW-08-SIERRA-VIASUS-ANDREA-JULIETH_SÁNCHEZ-SALAS-DANIEL-ANTONIO-31-MAY-2023\\SO-Software-08\\Manual de usuario.pdf");
        } catch (Exception e){
            System.out.println("El archivo no se puede abrir");
        }
    }

    private void cleanMainTableProcess(){
        this.viewManager.setValuesToTable(processManager.getProcessListAsMatrixObject(processManager.getInQueue()), "Procesos Existentes");
    }

    public void setValuesToCurrentProcess(){
        this.viewManager.setValuesToCurrentProcess();
    }
    public void setValuesToReadyReport(){
        this.viewManager.setValuesToReadyReport();
    }
    public void setValuesToDispatchReport(){
        this.viewManager.setValuesToDispatchReport();
    }

    public void setValuesToExecReport(){
        this.viewManager.setValuesToExecReport();
    }

    public void setValuesToExepReport(){
        this.viewManager.setValuesToExepReport();
    }

    public void setValuesToNoExepReport(){
        this.viewManager.setValuesToNoExecReport();
    }
    public void setValuesToFinishedReport(){
        this.viewManager.setValuesToFinishedReport();
    }

    public void setValuesToFinishedReportSort(){
        this.viewManager.setValuesToFinishedReportSort();
    }

    public void setValuesToNoExecReportSort(){
        this.viewManager.setValuesToNoExecReportSort();
    }
    public void setValuesToSpaceReport(){
        this.viewManager.setValuesToSpaceReport();
    }
    public void setValuesToCondensationsReport(){
        this.viewManager.setValuesToCondensationsReport();
    }

    public void setValuesToPartitionsReport(){
        this.viewManager.setValuesToPartitionsReport();
    }

    private void changeToMenu(){
        if(this.viewManager.getIsPartitionsMenuActive()){
            this.viewManager.setPartitionsMenuActive(false);

        }
        else
            this.processManager.cleanAllLists();
        this.viewManager.setValuesToTable(this.processManager.getProcessListAsMatrixObject(this.processManager.getInQueue()), "Procesos Existentes");
        this.viewManager.changeToMainMenu();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        new Controller();
    }
}
